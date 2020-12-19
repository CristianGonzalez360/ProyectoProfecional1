package repositories;

import java.util.Date;
import java.util.List;

import dto.CompraVehiculoDTO;
import dto.VehiculoDTO;

public interface VehiculosDao extends GenericDao<VehiculoDTO, Integer> {

	List<VehiculoDTO> readByCriteria(String marca, Boolean usado);

	List<String> readAllMarcasVehiculos();

	boolean updateDisponibilidadVehiculo(Integer id, Boolean boolean1);

	List<VehiculoDTO> readVehiculosUsados();

	Integer getIdMaximo();

	boolean updateIdFichaTecnica(VehiculoDTO entity);

	List<VehiculoDTO> readNuevosNoVendidos();

	void registrarCompra(CompraVehiculoDTO c);

	List<CompraVehiculoDTO> readCompras(Date desde, Date hasta);

}
