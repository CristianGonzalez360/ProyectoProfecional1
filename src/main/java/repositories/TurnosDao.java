package repositories;

import java.util.Date;
import java.util.List;

import dto.taller.TurnoDTO;

public interface TurnosDao extends GenericDao<TurnoDTO, Integer> {

	TurnoDTO readByDni(Integer dni);

	List<TurnoDTO> readAllTurnosDisponibles();

	List<TurnoDTO> readAllByDNI(Integer dni);

	List<TurnoDTO> readAllByFechaProgramada(Date fechaProgramada);
}
