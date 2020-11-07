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
		if(presupuesto.getIdPresupuesto() == null)
			Pdao.insert(presupuesto);
		else
			update(presupuesto);
	}
	
	private void update(PresupuestoDTO presupuesto) {
		for (TrabajoPresupuestadoDTO t : presupuesto.getTrabajos()) {
			t.setIdPresupuesto(presupuesto.getIdPresupuesto());// TODO Esta mal, como obtengo el id?
			TPDao.insert(t);
		}
		for (RepuestoPlanificadoDTO r : presupuesto.getRepuestos()) {
			r.setIdPresu(presupuesto.getIdPresupuesto());// TODO Esta mal, como obtengo el id?
			RPDao.insert(r);
		}
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
		PresupuestoDTO ret = Pdao.readByID(idPresupuesto);
		ret.setTrabajos(TPDao.readByPresupuestoId(idPresupuesto));
		ret.setRepuestos(RPDao.readByIdPresupuesto(idPresupuesto));
		return ret;
	}
}
