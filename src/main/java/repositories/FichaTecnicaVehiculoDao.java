package repositories;

import dto.taller.FichaTecnicaVehiculoDTO;

public interface FichaTecnicaVehiculoDao extends GenericDao<FichaTecnicaVehiculoDTO, Integer> {

	FichaTecnicaVehiculoDTO readByNroMotor(Integer nroMotor);

	FichaTecnicaVehiculoDTO readByNroChasis(Integer nroChasis);

	public Integer getIdMaximo();
}
