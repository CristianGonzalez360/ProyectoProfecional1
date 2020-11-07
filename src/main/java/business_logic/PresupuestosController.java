package business_logic;

import java.util.Date;
import java.util.List;
import java.util.Map;

import business_logic.exceptions.ForbiddenException;
import dto.EstadoPresupuesto;
import dto.FacturaDTO;
import dto.PresupuestoDTO;
import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;
import repositories.FacturasDao;
import repositories.PresupuestosDao;
import repositories.RepuestosDao;
import repositories.RepuestosPlanificadosDao;
import repositories.TrabajosPresupuestadosDao;
import services.SessionServiceImpl;

public class PresupuestosController {

	private static final String FORBIDDEN_CAMBIO_ESTADO = "No puede editar el estado del presupuesto.";

	private PresupuestosDao Pdao;
	
	private TrabajosPresupuestadosDao TPDao;
	
	private RepuestosPlanificadosDao RPDao;

	private RepuestosDao repuestosDao;
	
	private FacturasDao facturasDao;
	
	public PresupuestosController(PresupuestosDao presupuestosDao, TrabajosPresupuestadosDao trabajosPresupuestadosDao,
			RepuestosPlanificadosDao repuestosPlanificadosDao, RepuestosDao repuestosDao,
			FacturasDao facDao) {
		this.Pdao = presupuestosDao;
		this.TPDao = trabajosPresupuestadosDao;
		this.RPDao = repuestosPlanificadosDao;
		this.repuestosDao = repuestosDao;
		this.facturasDao = facDao;
	}

	public void save(PresupuestoDTO presupuesto) {
		presupuesto.setIdUsuAltaPresu(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		presupuesto.setFechaAltaPresu(new Date());
		if(presupuesto.getIdPresupuesto() == null)
			Pdao.insert(presupuesto);
		else
			update(presupuesto);
	}
	
	private void update(PresupuestoDTO presupuesto) {
	}
	
	public PresupuestoDTO readByOrdenDeTrabajoId(Integer id) {
		return Pdao.readByOrdenDeTrabajoId(id).isEmpty()? null : Pdao.readByOrdenDeTrabajoId(id).get(0);
	}

	public List<PresupuestoDTO> readByIdOt(Integer idOrdenTrabajo) {
		assert idOrdenTrabajo != null;
		return Pdao.readByOrdenDeTrabajoId(idOrdenTrabajo);
	}
	
	public List<TrabajoPresupuestadoDTO> readTrabajosByIdPresupuesto(Integer idPresupuesto) {
		assert idPresupuesto != null;
		return TPDao.readByPresupuestoId(idPresupuesto);
	}
	
	public List<RepuestoPlanificadoDTO> readRepuestoByIdPresupuesto(Integer idPresupuesto) {
		List<RepuestoPlanificadoDTO> repuestosplanificados = RPDao.readByIdPresupuesto(idPresupuesto);
		for(RepuestoPlanificadoDTO dto : repuestosplanificados) {
			Integer idRepuesto = dto.getIdRepuesto();
			RepuestoDTO repuesto = repuestosDao.readByID(idRepuesto);
			dto.setRepuesto(repuesto);
		}
		return repuestosplanificados;
	}

	public PresupuestoDTO readById(Integer idPresupuesto) {
		return Pdao.readByID(idPresupuesto);
	}
	
	public void generarFactura(Map<Integer, Boolean> presupuestos) throws ForbiddenException {
		assert presupuestos != null;
		assert !presupuestos.isEmpty();
		presupuestos.forEach((k, v) -> {
			PresupuestoDTO presupuesto = Pdao.readByID(k);
			if(presupuesto.getEstado().equals(EstadoPresupuesto.PENDIENTE)) {
				if(v.booleanValue() != true) {
					Pdao.updateStateById(k,new Date(), EstadoPresupuesto.APROBADO);	
				} else {
					Pdao.updateStateById(k,new Date(), EstadoPresupuesto.RECHAZADO);	
				}
			} else {
				throw new ForbiddenException(FORBIDDEN_CAMBIO_ESTADO);
			}
		});
		Object [] keys = presupuestos.keySet().toArray();
		Integer ordenDeTrabajoId = (Integer) keys[0];
		FacturaDTO factura = new FacturaDTO();
		factura.setIdOrdenDeTrabajo(ordenDeTrabajoId);
		factura.setFechaDeAlta(new Date());
		facturasDao.insert(factura);
	}
}