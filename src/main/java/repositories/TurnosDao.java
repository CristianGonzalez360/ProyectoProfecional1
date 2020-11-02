package repositories;

import dto.TurnoDTO;

public interface TurnosDao extends GenericDao<TurnoDTO, Integer> {

	TurnoDTO readByDni(Integer dni);
}
