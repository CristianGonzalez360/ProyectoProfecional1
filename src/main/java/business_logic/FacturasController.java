package business_logic;

import java.util.Date;
import java.util.List;
import business_logic.exceptions.NotFoundException;
import dto.EstadoPresupuesto;
import dto.FacturaDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.PresupuestoDTO;
import dto.ResumenDeFacturaDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import presentacion.views.utils.FacturaRepuestosReport;
import presentacion.views.utils.FacturaTallerReport;
import repositories.DaosFactory;
import repositories.FacturasDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.PresupuestosDao;
import repositories.RepuestosPlanificadosDao;
import repositories.TrabajosPresupuestadosDao;
import repositories.VehiculosConOrdenDeTrabajoDao;

public class FacturasController {
	
	private FacturasDao facturaDao;

	private PresupuestosDao presDao;
	
	private TrabajosPresupuestadosDao trabajosPresuDao;
	
	private RepuestosPlanificadosDao repuPresuDao;
	
	private OrdenesDeTrabajoDao ordenTrabajoDao;
	private FichaTecnicaVehiculoDao fichaTecnicaDao;
	private VehiculosConOrdenDeTrabajoDao vehiculoConOrdenTrabajoDao;
	
	public FacturasController(DaosFactory daos) {
		this.facturaDao = daos.makeFacturasDao();
		this.presDao = daos.makePresupuestoDao();
		this.trabajosPresuDao = daos.makeTrabajosPlanificadosDao();
		this.repuPresuDao = daos.makeRepuestosPlanificadosDao();
		this.ordenTrabajoDao = daos.makeOrdenDeTrabajoDao();
		this.fichaTecnicaDao = daos.makeFichaTecnicaVehiculoDao();
		this.vehiculoConOrdenTrabajoDao = daos.makeVehiculoConOrdeDeTrabajoDao();
	}
	
	public List<FacturaDTO> readFacturaByOrdenDeTrabajoId(Integer id) {
		assert id != null;
		return facturaDao.readByOrdenDeTrabajoId(id);
	}
		
	public void save(FacturaDTO factura) {
		facturaDao.insert(factura);
		factura.setIdFactura(readIdUltimaFacturaGuardada());
	}
		
	private int  readIdUltimaFacturaGuardada() {
		List<FacturaDTO> facturas = facturaDao.readAll();
		int idFactura = facturas.get(0).getIdFactura();
		for (FacturaDTO f : facturas) {
			if (idFactura < f.getIdFactura()) {
				idFactura = f.getIdFactura();
			}
		}
		return idFactura;
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
		facturaCarrito.setIdFactura(readIdUltimaFacturaGuardada());
	}
	
	public FacturaTallerReport makeFacturaTaller(FacturaDTO factura) {
		FacturaTallerReport ret = new FacturaTallerReport();
		ret.setCliente(factura.getCliente().getDatosPersonalesDTO());
		ret.setVehiculo(getFichaTecnica(factura));
		ret.setTrabajos(factura.getTabajos());
		ret.setRepuestos(factura.getRepuestosPlanificados());
		ret.setTotal(factura.getTotal());
		ret.setFecha(factura.getFechaDeAlta());
		ret.setNumero(factura.getIdFactura());
		
		return ret;
	}
		
	private FichaTecnicaVehiculoDTO getFichaTecnica(FacturaDTO factura) {
		OrdenDeTrabajoDTO ordenTrabajo = ordenTrabajoDao.readByID(factura.getIdOrdenDeTrabajo());
		VehiculoConOrdenDeTrabajoDTO vehiculoConOrdenTrabajo = vehiculoConOrdenTrabajoDao.readByID(ordenTrabajo.getIdOrdenTrabajo());
		return fichaTecnicaDao.readByID(vehiculoConOrdenTrabajo.getIdFichaTecnica());
	}
	
	public FacturaRepuestosReport makeFacturaRepuestos(FacturaDTO factura) {
		
		FacturaRepuestosReport ret = new FacturaRepuestosReport();
		
		ret.setCliente(factura.getCliente().getDatosPersonalesDTO());
		ret.setRepuestos(factura.getRepuestosComprados());
		ret.setTotal(factura.getTotal());
		ret.setFecha(factura.getFechaDeAlta());
		ret.setNumero(factura.getIdFactura());
		
		return ret;
	}
}