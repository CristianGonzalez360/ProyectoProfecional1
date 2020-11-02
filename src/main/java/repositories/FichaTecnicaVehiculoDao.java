package repositories;

import dto.FichaTecnicaVehiculoDTO;

public interface FichaTecnicaVehiculoDao extends GenericDao<FichaTecnicaVehiculoDTO, Integer>{

	FichaTecnicaVehiculoDTO readByNroMotor(Integer nroMotor);
}
