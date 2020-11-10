package repositories;

import dto.DatosPersonalesDTO;

public interface DatosPersonalesDao extends GenericDao<DatosPersonalesDTO, Integer> {

	DatosPersonalesDTO readByDni(Integer dni);

}
