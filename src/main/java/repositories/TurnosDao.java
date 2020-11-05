package repositories;

import java.util.List;

import dto.TurnoDTO;

public interface TurnosDao extends GenericDao<TurnoDTO, Integer> {

	TurnoDTO readByDni(Integer dni);

	TurnoDTO readByIdTurno(Integer id);

	List<TurnoDTO> readAllTurnosDisponibles();

	List<TurnoDTO> readAllByDNI(Integer dni);
}
