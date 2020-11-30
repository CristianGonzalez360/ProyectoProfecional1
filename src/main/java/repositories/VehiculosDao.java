package repositories;

import java.util.List;

import dto.VehiculoDTO;

public interface VehiculosDao extends GenericDao<VehiculoDTO, Integer> {

	List<VehiculoDTO> readByCriteria(boolean tipo, String marca, String familia, String linea);
	
	List<String> readAllMarcasVehiculos();
}
