package business_logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import business_logic.exceptions.ForbiddenException;
import business_logic.exceptions.NotFoundException;
import dto.EstadoPresupuesto;
import dto.FacturaDTO;
import dto.PresupuestoDTO;
import dto.ResumenDeFacturaDTO;
import repositories.FacturasDao;
import repositories.PresupuestosDao;
import repositories.RepuestosPlanificadosDao;
import repositories.TrabajosPresupuestadosDao;

public class FacturasController {
	
	private static final String NOT_FOUND = "La orden de trabajo no tiene una factura.";

	private static final String FORBIDDEN_CAMBIO_ESTADO = "No puede editar el estado del presupuesto.";
		
	private FacturasDao facturaDao;

	private PresupuestosDao presDao;
	
	private TrabajosPresupuestadosDao trabajosPresuDao;
	
	private RepuestosPlanificadosDao repuPresuDao;
	
	public FacturasController(FacturasDao facturasDao, PresupuestosDao presupuestos, TrabajosPresupuestadosDao trabajos,
			RepuestosPlanificadosDao repuDao) {
		this.facturaDao = facturasDao;
		this.presDao = presupuestos;
		this.trabajosPresuDao = trabajos;
		this.repuPresuDao = repuDao;
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
		Object [] keys = presupuestos.keySet().toArray();
		Integer ordenDeTrabajoId = presDao.readByID((Integer) keys[0]).getIdOT();
		List<PresupuestoDTO> ps = new ArrayList<PresupuestoDTO>();
		double total = 0;
		for (int i = 0; i < keys.length; i++) {
				int idPresupuesto = (Integer) keys[i];
				PresupuestoDTO p = readPresupuestoById(idPresupuesto);
				ps.add(p);
				total += p.getPrecio();
		}
		FacturaDTO factura = null;
		boolean esOrdenDeTrabajoRechazada = esRechazada(ordenDeTrabajoId);
		if(!esOrdenDeTrabajoRechazada) {
			factura = new FacturaDTO();
			factura.setIdOrdenDeTrabajo(ordenDeTrabajoId);
			factura.setFechaDeAlta(new Date());
			factura.setTotal(total);
			facturaDao.insert(factura);	
			
			List<FacturaDTO> facturas = facturaDao.readByOrdenDeTrabajoId(ordenDeTrabajoId);
			int idFactura = facturas.get(0).getIdFactura();
			for (FacturaDTO f : facturas) {
				if(idFactura<f.getIdFactura()) {
					idFactura = f.getIdFactura();
				}
			}
			
			for (PresupuestoDTO p : ps) {
				p.setIdFactura(idFactura);
				presDao.update(p);
			}
			factura.setIdFactura(idFactura);
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
		ret.setPresupuestosFacturados(presDao.readByFacturaId(id));
		return ret;
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
}
