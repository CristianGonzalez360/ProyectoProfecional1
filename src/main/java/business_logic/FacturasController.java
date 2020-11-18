package business_logic;

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
	
	public FacturaDTO readFacturaByOrdenDeTrabajoId(Integer id) {
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
		FacturaDTO factura = null;
		boolean esOrdenDeTrabajoRechazada = esRechazada(ordenDeTrabajoId);
		if(!esOrdenDeTrabajoRechazada) {
			factura = new FacturaDTO();
			factura.setIdOrdenDeTrabajo(ordenDeTrabajoId);
			factura.setFechaDeAlta(new Date());
			facturaDao.insert(factura);	
		}
		return factura;
	}
	
	private boolean esRechazada(Integer ordenDeTrabajoId) {
		List<PresupuestoDTO> presu = presDao.readByOrdenDeTrabajoId(ordenDeTrabajoId);
		int cantPresupuestos = presu.size();
		int cantAprobados = 0;
		for(PresupuestoDTO temp: presu) {
			if(temp.getEstado().equals(EstadoPresupuesto.RECHAZADO)) {
				cantAprobados++;
			}
		}
		return cantAprobados == cantPresupuestos;
	}
	
	public void registrarPagoDeFacturaById(Integer id) throws NotFoundException {
		assert id != null;
		FacturaDTO factura = facturaDao.readByOrdenDeTrabajoId(id);
		if(factura == null) throw new NotFoundException(NOT_FOUND);
		if(factura.getFechaDeCierrePorPago() != null) throw new ForbiddenException("No se puede registrar dos pagos para la misma factura");
		facturaDao.updateFechaCierrePorPago(id, new Date());
		List<PresupuestoDTO> presupuestos = presDao.readByOrdenDeTrabajoId(id);
		presupuestos.forEach((k)-> {
			Integer idPresupuesto = k.getIdPresupuesto();
			if(k.getEstado().equals(EstadoPresupuesto.APROBADO)) {
				presDao.updateState(idPresupuesto, EstadoPresupuesto.PAGADO);
			}
		});
	}
	
	
	public List<FacturaDTO> readAll() {
		List<FacturaDTO> ret = facturaDao.readAll();
		return ret;
	}
	
	public List<FacturaDTO> readByFactura(Integer id) {
		List<FacturaDTO> ret = facturaDao.readByFactura(id);
		return ret;
	}
	
	public boolean updatePorPago(Integer id) {
		assert id != null;
		return facturaDao.updatePorPago(id);
	}
	
	public ResumenDeFacturaDTO generarResumenFactura(Integer idOrdenDeTrabajo) {
		FacturaDTO factura = readFacturaByOrdenDeTrabajoId(idOrdenDeTrabajo);
		if(factura == null) throw new NotFoundException(NOT_FOUND);
		List<PresupuestoDTO> presupuestos = presDao.readByOrdenDeTrabajoId(idOrdenDeTrabajo);
		ResumenDeFacturaDTO resumen = new ResumenDeFacturaDTO();
		for (PresupuestoDTO presupuesto : presupuestos) {
			if(presupuesto.getEstado().equals(EstadoPresupuesto.APROBADO)) {
				resumen.setRepuestos(this.repuPresuDao.readByIdPresupuesto(presupuesto.getIdPresupuesto()));
				resumen.setTrabajos(this.trabajosPresuDao.readByPresupuestoId(presupuesto.getIdPresupuesto()));
			}
		}
		return resumen;
	}
}
