package repositories;

import java.util.List;

import dto.PresupuestoDTO;

public interface PresupuestosDao extends GenericDao<PresupuestoDTO, Integer> {

	List<PresupuestoDTO> readByOrdenDeTrabajoId(Integer id);

}
