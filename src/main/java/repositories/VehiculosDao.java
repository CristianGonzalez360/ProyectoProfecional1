package repositories;

import java.util.List;

import dto.VehiculoDTO;

public interface VehiculosDao extends GenericDao<VehiculoDTO, Integer> {

	List<VehiculoDTO> readDisponiblesByCriteria(String marca, Boolean usado);
	
	List<String> readAllMarcasVehiculos();
}
