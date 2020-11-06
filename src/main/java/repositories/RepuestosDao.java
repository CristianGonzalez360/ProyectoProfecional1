package repositories;

import java.util.List;

import dto.RepuestoDTO;

public interface RepuestosDao extends GenericDao<RepuestoDTO, Integer> {

	List<RepuestoDTO> readByMarca(String marca);
	
	List<RepuestoDTO> readByDescripcion(String descripcion);
}
