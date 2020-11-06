package business_logic;

import java.util.Date;

import dto.PresupuestoDTO;
import repositories.PresupuestosDao;
import repositories.RepuestosPlanificadosDao;
import repositories.TrabajosPresupuestadosDao;
import services.SessionServiceImpl;

public class PresupuestosController {
	
	private PresupuestosDao Pdao;
	private TrabajosPresupuestadosDao TPDao;
	private RepuestosPlanificadosDao RPDap;
	

	public PresupuestosController(PresupuestosDao presupuestosDao, TrabajosPresupuestadosDao trabajosPresupuestadosDao, RepuestosPlanificadosDao repuestosPlanificadosDao) {
		this.Pdao = presupuestosDao;
		this.TPDao = trabajosPresupuestadosDao;
		this.RPDap = repuestosPlanificadosDao;
	}

	public void save(PresupuestoDTO presupuesto) {
		presupuesto.setIdUsuAltaPresu(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		presupuesto.setFechaAltaPresu(new Date());
	}
}
