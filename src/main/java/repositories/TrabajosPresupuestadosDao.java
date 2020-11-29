package repositories;

import java.util.List;

import dto.taller.TrabajoPresupuestadoDTO;

public interface TrabajosPresupuestadosDao extends GenericDao<TrabajoPresupuestadoDTO, Integer> {

	List<TrabajoPresupuestadoDTO> readByPresupuestoId(Integer id);
}
