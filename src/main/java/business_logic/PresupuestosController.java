package business_logic;

import java.util.Date;

import dto.PresupuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;
import repositories.PresupuestosDao;
import repositories.RepuestosPlanificadosDao;
import repositories.TrabajosPresupuestadosDao;
import services.SessionServiceImpl;

public class PresupuestosController {
	
	private PresupuestosDao Pdao;
	private TrabajosPresupuestadosDao TPDao;
	private RepuestosPlanificadosDao RPDao;
	

	public PresupuestosController(PresupuestosDao presupuestosDao, TrabajosPresupuestadosDao trabajosPresupuestadosDao, RepuestosPlanificadosDao repuestosPlanificadosDao) {
		this.Pdao = presupuestosDao;
		this.TPDao = trabajosPresupuestadosDao;
		this.RPDao = repuestosPlanificadosDao;
	}

	public void save(PresupuestoDTO presupuesto) {
		presupuesto.setIdUsuAltaPresu(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		presupuesto.setFechaAltaPresu(new Date());
		Pdao.insert(presupuesto);
		for (TrabajoPresupuestadoDTO t : presupuesto.getTrabajos()) {
			t.setIdPresupuesto(presupuesto.getIdPresupuesto());//TODO Esta mal, como obtengo el id?
			TPDao.insert(t);
		}
		for (RepuestoPlanificadoDTO r : presupuesto.getRepuestos()) {
			r.setIdPresu(presupuesto.getIdPresupuesto());//TODO Esta mal, como obtengo el id?
			RPDao.insert(r);
		}
	}
}
