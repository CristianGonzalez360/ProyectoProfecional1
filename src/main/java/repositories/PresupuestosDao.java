package repositories;

import java.util.Date;
import java.util.List;

import dto.EstadoPresupuesto;
import dto.PresupuestoDTO;

public interface PresupuestosDao extends GenericDao<PresupuestoDTO, Integer> {

	List<PresupuestoDTO> readByOrdenDeTrabajoId(Integer id);

	boolean updateStateById(Integer k, Date date, EstadoPresupuesto aprobado);

	boolean updateState(Integer id, EstadoPresupuesto rechazado);
	
	boolean delete(Integer id);
}
