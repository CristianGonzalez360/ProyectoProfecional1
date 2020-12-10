package repositories;

import dto.DatosPersonalesDTO;

public interface DatosPersonalesDao extends GenericDao<DatosPersonalesDTO, Integer> {

	DatosPersonalesDTO readByDni(Integer dni);

	DatosPersonalesDTO readByTelefono(String telefono);

	DatosPersonalesDTO readByEmail(String email);
}
