package repositories;

import java.util.List;

import dto.TrabajoPlanificadoDTO;

public interface TrabajosPlanificadosDao extends GenericDao<TrabajoPlanificadoDTO, Integer> {

	List<TrabajoPlanificadoDTO> readByPresupuestoId(Integer id);
}
