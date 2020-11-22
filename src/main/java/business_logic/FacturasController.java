package business_logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import business_logic.exceptions.ForbiddenException;
import business_logic.exceptions.NotFoundException;
import dto.ClienteDTO;
import dto.EstadoPresupuesto;
import dto.FacturaDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.PresupuestoDTO;
import dto.ResumenDeFacturaDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import presentacion.views.utils.FacturaTallerReport;
import repositories.ClientesDao;
import repositories.DaosFactory;
import repositories.FacturasDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.PresupuestosDao;
import repositories.RepuestosPlanificadosDao;
import repositories.TrabajosPresupuestadosDao;
import repositories.VehiculosConOrdenDeTrabajoDao;

public class FacturasController {
	
	private static final String NOT_FOUND = "La orden de trabajo no tiene una factura.";

	private static final String FORBIDDEN_CAMBIO_ESTADO = "No puede editar el estado del presupuesto.";
		
	private FacturasDao facturaDao;

	private PresupuestosDao presDao;
	
	private TrabajosPresupuestadosDao trabajosPresuDao;
	
	private RepuestosPlanificadosDao repuPresuDao;
	
	private OrdenesDeTrabajoDao ordenTrabajoDao;
	private ClientesDao clienteDao;
	private FichaTecnicaVehiculoDao fichaTecnicaDao;
	private VehiculosConOrdenDeTrabajoDao vehiculoConOrdenTrabajoDao;
	
	public FacturasController(DaosFactory daos) {
		this.facturaDao = daos.makeFacturasDao();
		this.presDao = daos.makePresupuestoDao();
		this.trabajosPresuDao = daos.makeTrabajosPlanificadosDao();
		this.repuPresuDao = daos.makeRepuestosPlanificadosDao();
		this.ordenTrabajoDao = daos.makeOrdenDeTrabajoDao();
		this.clienteDao = daos.makeClienteDao();
		this.fichaTecnicaDao = daos.makeFichaTecnicaVehiculoDao();
		this.vehiculoConOrdenTrabajoDao = daos.makeVehiculoConOrdeDeTrabajoDao();
	}
	
	public List<FacturaDTO> readFacturaByOrdenDeTrabajoId(Integer id) {
		assert id != null;
		return facturaDao.readByOrdenDeTrabajoId(id);
	}
	
	public void updateEstadoPresupuestos(Map<Integer, Boolean> presupuestos) throws ForbiddenException {
		assert presupuestos != null;
		assert !presupuestos.isEmpty();
		presupuestos.forEach((k, v) -> {
			PresupuestoDTO presupuesto = presDao.readByID(k);
			if(presupuesto.getEstado().equals(EstadoPresupuesto.PENDIENTE)) {
				if(v.booleanValue() == true) {
					presDao.updateStateById(k,new Date(), EstadoPresupuesto.APROBADO);	
				} else {
					presDao.updateStateById(k,new Date(), EstadoPresupuesto.RECHAZADO);	
				}
			} else {
				throw new ForbiddenException(FORBIDDEN_CAMBIO_ESTADO);
			}
		});
	}
	
	public FacturaDTO generarFactura(Map<Integer, Boolean> presupuestos) {
		FacturaDTO factura = null;
		if (!presupuestos.isEmpty()) {
			Object[] keys = presupuestos.keySet().toArray();
			Integer ordenDeTrabajoId = presDao.readByID((Integer) keys[0]).getIdOT();
			List<PresupuestoDTO> ps = new ArrayList<PresupuestoDTO>();
			double total = 0;
			for (int i = 0; i < keys.length; i++) {
				if(presupuestos.get(keys[i]) == true) {
					int idPresupuesto = (Integer) keys[i];
					PresupuestoDTO p = readPresupuestoById(idPresupuesto);
					ps.add(p);
					total += p.getPrecio();
				}
			}
			boolean esOrdenDeTrabajoRechazada = esRechazada(ordenDeTrabajoId);
			if (!esOrdenDeTrabajoRechazada) {
				factura = new FacturaDTO();
				factura.setIdOrdenDeTrabajo(ordenDeTrabajoId);
				factura.setFechaDeAlta(new Date());
				factura.setTotal(total);

				factura.setCliente(getCliente(factura));

				facturaDao.insert(factura);

				List<FacturaDTO> facturas = facturaDao.readByOrdenDeTrabajoId(ordenDeTrabajoId);
				int idFactura = facturas.get(0).getIdFactura();
				for (FacturaDTO f : facturas) {
					if (idFactura < f.getIdFactura()) {
						idFactura = f.getIdFactura();
					}
				}

				for (PresupuestoDTO p : ps) {
					p.setIdFactura(idFactura);
					presDao.update(p);
				}
				factura.setPresupuestosFacturados(ps);
				factura.setIdFactura(idFactura);
			} 
		}
		return factura;
	}

	private PresupuestoDTO readPresupuestoById(Integer idPresupuesto) {
		PresupuestoDTO ret = presDao.readByID(idPresupuesto);
		ret.setTrabajos(trabajosPresuDao.readByPresupuestoId(idPresupuesto));
		ret.setRepuestos(repuPresuDao.readByIdPresupuesto(idPresupuesto));
		return ret;
	}
	
	private boolean esRechazada(Integer ordenDeTrabajoId) {
		List<PresupuestoDTO> presu = presDao.readByOrdenDeTrabajoId(ordenDeTrabajoId);
		int cantPresupuestos = presu.size();
		int cantRechazadas = 0;
		for(PresupuestoDTO temp: presu) {
			if(temp.getEstado().equals(EstadoPresupuesto.RECHAZADO)) {
				cantRechazadas++;
			}
		}
		return cantRechazadas == cantPresupuestos;
	}
		
	public void registrarPagoDeFacturaById(Integer IdOrdenDeTrabajo) throws NotFoundException {
		assert IdOrdenDeTrabajo != null;
		List<FacturaDTO> facturas = facturaDao.readByOrdenDeTrabajoId(IdOrdenDeTrabajo);
		for (FacturaDTO f : facturas) {
			if(!f.estaPagada()) {
				facturaDao.updateFechaCierrePorPago(IdOrdenDeTrabajo, new Date());
			}
		}
		List<PresupuestoDTO> presupuestos = presDao.readByOrdenDeTrabajoId(IdOrdenDeTrabajo);
		for (PresupuestoDTO p : presupuestos) {
			if(p.getEstado().name() == EstadoPresupuesto.APROBADO.name()) {
				presDao.updateState(p.getIdPresupuesto(), EstadoPresupuesto.PAGADO);
			}
		}
	}
	
	public FacturaDTO readByFactura(Integer id) {
		FacturaDTO ret = facturaDao.readById(id);
		if(ret != null) {
			ret.setPresupuestosFacturados(presDao.readByFacturaId(id));
		}
		return ret;
	}

	public List<FacturaDTO> readAll() {
		List<FacturaDTO> ret = facturaDao.readAll();
		return ret;
	}
	
	public boolean updatePorPago(Integer id) {
		assert id != null;
		return facturaDao.updatePorPago(id);
	}
		
	public ResumenDeFacturaDTO generarResumenFactura(Integer idFactura) {
		List<PresupuestoDTO> presupuestos = presDao.readByFacturaId(idFactura);
		ResumenDeFacturaDTO resumen = new ResumenDeFacturaDTO();
		for (PresupuestoDTO presupuesto : presupuestos) {
			if(presupuesto.getEstado().equals(EstadoPresupuesto.APROBADO)) {
				resumen.agregarRepuestos(this.repuPresuDao.readByIdPresupuesto(presupuesto.getIdPresupuesto()));
				resumen.agregarTrabajos(this.trabajosPresuDao.readByPresupuestoId(presupuesto.getIdPresupuesto()));
			}
		}
		return resumen;
	}


	public void generarFacturaCarrito(FacturaDTO facturaCarrito) {
		facturaDao.insertFacturaCarrito(facturaCarrito);
	}
	
	public FacturaTallerReport make(FacturaDTO factura) {
		
		FacturaTallerReport ret = new FacturaTallerReport();
		OrdenDeTrabajoDTO ordenTrabajo = ordenTrabajoDao.readByID(factura.getIdOrdenDeTrabajo());
		VehiculoConOrdenDeTrabajoDTO vehiculoConOrdenTrabajo = vehiculoConOrdenTrabajoDao.readByID(ordenTrabajo.getIdOrdenTrabajo());
		FichaTecnicaVehiculoDTO fichaTecnica = fichaTecnicaDao.readByID(vehiculoConOrdenTrabajo.getIdFichaTecnica());
		ClienteDTO cliente = getCliente(factura);
		
		ret.setCliente(cliente.getDatosPersonalesDTO());
		ret.setVehiculo(fichaTecnica);
		ret.setTrabajos(factura.getTabajos());
		ret.setRepuestos(factura.getRepuestosPlanificados());
		ret.setTotal(factura.getTotal());
		ret.setFecha(factura.getFechaDeAlta());
		ret.setNumero(factura.getIdFactura());
		
		return ret;
	}
	
	private ClienteDTO getCliente(FacturaDTO factura) {
		
		ClienteDTO cliente = null;
		OrdenDeTrabajoDTO ordenTrabajo = ordenTrabajoDao.readByID(factura.getIdOrdenDeTrabajo());
		VehiculoConOrdenDeTrabajoDTO vehiculoConOrdenTrabajo = vehiculoConOrdenTrabajoDao.readByID(ordenTrabajo.getIdOrdenTrabajo());
		cliente = clienteDao.readByID(vehiculoConOrdenTrabajo.getIdCliente());
		
		return cliente;
	}
}
