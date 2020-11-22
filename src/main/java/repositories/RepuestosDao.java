package repositories;

import java.util.List;

import dto.RepuestoDTO;

public interface RepuestosDao extends GenericDao<RepuestoDTO, Integer> {

	List<RepuestoDTO> readByMarca(String marca);

	List<RepuestoDTO> readByDescripcion(String descripcion);

	List<RepuestoDTO> readByMarcaYDescripcion(String marca, String descripcion);

	List<String> readMarcas();

	RepuestoDTO readByCodigo(Integer codigo);

	void updateByCodigo(RepuestoDTO repuesto);
}
