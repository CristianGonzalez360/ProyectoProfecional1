package business_logic;

import java.util.Iterator;
import java.util.List;

import dto.taller.MantenimientoDTO;
import dto.taller.PresupuestoDTO;
import dto.taller.RepuestoMantenimientoDTO;
import dto.taller.RepuestoPlanificadoDTO;
import dto.taller.TrabajoMantenimientoDTO;
import dto.taller.TrabajoPresupuestadoDTO;
import repositories.DaosFactory;

public class MantenimientosController {

	private DaosFactory daos;

	public MantenimientosController(DaosFactory daos) {
		this.daos = daos;
	}

	public void save(MantenimientoDTO mantenimiento) {
		daos.makeMantenimientoDao().insert(mantenimiento);
		Integer idMantenimiento = daos.makeMantenimientoDao().getIdMaximo();

		for (RepuestoMantenimientoDTO nuevoRM : mantenimiento.getRepuestos()) {
			nuevoRM.setidMantenimiento(idMantenimiento);
			;
			daos.makeRepuestoMantenimientoDao().insert(nuevoRM);
		}
		for (TrabajoMantenimientoDTO nuevoT : mantenimiento.getTrabajos()) {
			nuevoT.setidMantenimiento(idMantenimiento);
			daos.makeTrabajoMantenimientoDao().insert(nuevoT);
		}
	}

	public List<MantenimientoDTO> readAll() {
		return daos.makeMantenimientoDao().readAll();
	}

	public MantenimientoDTO readByID(int id) {
		MantenimientoDTO ret = daos.makeMantenimientoDao().readByID(id);
		List<RepuestoMantenimientoDTO> repuestos = daos.makeRepuestoMantenimientoDao()
				.readByIdMantenimiento(ret.getId());
		for (RepuestoMantenimientoDTO r : repuestos) {
			r.setRepuesto(daos.makeRepuestoDao().readByID(r.getIdRepuesto()));
		}
		ret.setTrabajos(daos.makeTrabajoMantenimientoDao().readByIdMantennimiento(id));
		ret.setRepuestos(repuestos);
		return ret;
	}

	public void update(MantenimientoDTO mantenimiento) {
		daos.makeMantenimientoDao().update(mantenimiento);
		MantenimientoDTO actual = readByID(mantenimiento.getId());

		for (RepuestoMantenimientoDTO nuevoRM : mantenimiento.getRepuestos()) {
			if (nuevoRM.getIdRepuestoMantenimiento() == null) {
				nuevoRM.setidMantenimiento(mantenimiento.getId());
				daos.makeRepuestoMantenimientoDao().insert(nuevoRM);
			}
		}

		for (RepuestoMantenimientoDTO rp : actual.getRepuestos()) {
			if (!mantenimiento.getRepuestos().contains(rp)) {
				daos.makeRepuestoMantenimientoDao().deleteById(rp.getIdRepuestoMantenimiento());
			}
		}

		for (TrabajoMantenimientoDTO nuevoT : mantenimiento.getTrabajos()) {
			if (nuevoT.getIdTrabajoMantenimiento() == null) {
				nuevoT.setidMantenimiento(mantenimiento.getId());
				daos.makeTrabajoMantenimientoDao().insert(nuevoT);
			}
		}

		for (TrabajoMantenimientoDTO tp : actual.getTrabajos()) {
			if (!mantenimiento.getTrabajos().contains(tp)) {
				daos.makeTrabajoMantenimientoDao().deleteById(tp.getIdTrabajoMantenimiento());
			}
		}

	}
}
