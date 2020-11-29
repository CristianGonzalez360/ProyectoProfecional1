package repositories;

import dto.taller.FichaTecnicaVehiculoDTO;

public interface FichaTecnicaVehiculoDao extends GenericDao<FichaTecnicaVehiculoDTO, Integer> {

	FichaTecnicaVehiculoDTO readByNroMotor(Integer nroMotor);
}
