package repositories;

import dto.CaracteristicaVehiculoDTO;

public interface CaracteristicasVehiculoDao extends GenericDao<CaracteristicaVehiculoDTO, Integer> {

	Integer getIdMaximo();

}
