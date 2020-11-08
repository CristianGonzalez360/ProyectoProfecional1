package business_logic;

import java.util.Date;
import java.util.List;
import dto.PresupuestoDTO;
import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;
import repositories.PresupuestosDao;
import repositories.RepuestosDao;
import repositories.RepuestosPlanificadosDao;
import repositories.TrabajosPresupuestadosDao;
import services.SessionServiceImpl;

public class PresupuestosController {

	private PresupuestosDao Pdao;
	
	private TrabajosPresupuestadosDao TPDao;
	
	private RepuestosPlanificadosDao RPDao;

	private RepuestosDao repuestosDao;
	
	public PresupuestosController(PresupuestosDao presupuestosDao, TrabajosPresupuestadosDao trabajosPresupuestadosDao,
			RepuestosPlanificadosDao repuestosPlanificadosDao, RepuestosDao repuestosDao) {
		this.Pdao = presupuestosDao;
		this.TPDao = trabajosPresupuestadosDao;
		this.RPDao = repuestosPlanificadosDao;
		this.repuestosDao = repuestosDao;
	}

	public void save(PresupuestoDTO presupuesto) {
		presupuesto.setIdUsuAltaPresu(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		presupuesto.setFechaAltaPresu(new Date());
		Pdao.insert(presupuesto);
	}
		
	public void update(PresupuestoDTO presupuesto) {
		PresupuestoDTO actual = readById(presupuesto.getIdPresupuesto());
		for(RepuestoPlanificadoDTO nuevoRP : presupuesto.getRepuestos()) {
			if(nuevoRP.getIdRepuestoPlanificado() == null) {//Es un repuesto planificado nuevo
				nuevoRP.setIdPresu(presupuesto.getIdPresupuesto());
				RPDao.insert(nuevoRP);
			} else if(actual.getRepuestos().contains(nuevoRP)){//Si el repuesto planificado ya estaba guardado
				RPDao.update(nuevoRP);
			}			
		}
		//Si alguno de los repuestos planificados fue quitado lo borra de la BD
		for(RepuestoPlanificadoDTO rp : actual.getRepuestos()) {
			if(!presupuesto.getRepuestos().contains(rp)) {        
				RPDao.deleteById(rp.getIdRepuestoPlanificado());
			}
		}
		
		for(TrabajoPresupuestadoDTO nuevoT : presupuesto.getTrabajos()) {
			if(nuevoT.getIdTrabajoPresu() == null) {//Es un trabajo planificado nuevo
				nuevoT.setIdPresupuesto(presupuesto.getIdPresupuesto());
				TPDao.insert(nuevoT);
			} else if(actual.getTrabajos().contains(nuevoT)){//Si el trabajo planificado ya estaba guardado
				TPDao.update(nuevoT);
			}			
		}
		//Si alguno de los trabjas planificados fue quitado lo borra de la BD
		for(TrabajoPresupuestadoDTO tp : actual.getTrabajos()) {
			if(!presupuesto.getTrabajos().contains(tp)) {        
				RPDao.deleteById(tp.getIdTrabajoPresu());
			}
		}
	}
	
	public PresupuestoDTO readByOrdenDeTrabajoId(Integer id) {
		return Pdao.readByOrdenDeTrabajoId(id).isEmpty()? null : Pdao.readByOrdenDeTrabajoId(id).get(0);
	}

	public List<PresupuestoDTO> readByIdOt(Integer idOrdenTrabajo) {
		assert idOrdenTrabajo != null;
		List<PresupuestoDTO> ret  = Pdao.readByOrdenDeTrabajoId(idOrdenTrabajo);
		for (PresupuestoDTO presupuesto : ret) {
			presupuesto.setRepuestos(RPDao.readByIdPresupuesto(presupuesto.getIdPresupuesto()));
			presupuesto.setTrabajos(TPDao.readByPresupuestoId(presupuesto.getIdPresupuesto()));
		}
		return ret;
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
		PresupuestoDTO ret = Pdao.readByID(idPresupuesto);
		ret.setTrabajos(TPDao.readByPresupuestoId(idPresupuesto));
		ret.setRepuestos(RPDao.readByIdPresupuesto(idPresupuesto));
		return ret;
	}
}