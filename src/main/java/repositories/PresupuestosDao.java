package repositories;

import java.util.Date;
import java.util.List;

import dto.taller.EstadoPresupuesto;
import dto.taller.PresupuestoDTO;

public interface PresupuestosDao extends GenericDao<PresupuestoDTO, Integer> {

	List<PresupuestoDTO> readByOrdenDeTrabajoId(Integer id);

	boolean updateStateById(Integer k, Date date, EstadoPresupuesto aprobado);

	boolean updateState(Integer id, EstadoPresupuesto rechazado);

	boolean delete(Integer id);

	List<PresupuestoDTO> readByFacturaId(Integer idFactura);

	boolean registrarAprobacion(PresupuestoDTO presupuesto);
}
