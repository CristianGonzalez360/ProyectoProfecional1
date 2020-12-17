package repositories;

import java.util.Date;
import java.util.List;

import dto.CompraRepuestoDTO;
import dto.taller.RepuestoDTO;

public interface RepuestosDao extends GenericDao<RepuestoDTO, Integer> {

	List<RepuestoDTO> readByMarca(String marca);

	List<RepuestoDTO> readByDescripcion(String descripcion);

	List<RepuestoDTO> readByMarcaYDescripcion(String marca, String descripcion);

	List<String> readMarcas();

	RepuestoDTO readByCodigo(Integer codigo);

	void updateByCodigo(RepuestoDTO repuesto);

	List<RepuestoDTO> readRepuestosSinStock();

	void registrarCompra(CompraRepuestoDTO compra);

	List<CompraRepuestoDTO> readCompras(Date desde, Date hasta);
}
