package repositories;

import dto.GarantiaVehiculoDTO;

public interface GarantiasDao extends GenericDao<GarantiaVehiculoDTO, Integer> {

	GarantiaVehiculoDTO readByIdVehiculo(Integer id);
}
