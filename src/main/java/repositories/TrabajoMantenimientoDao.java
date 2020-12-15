package repositories;

import java.util.List;

import dto.taller.TrabajoMantenimientoDTO;

public interface TrabajoMantenimientoDao extends GenericDao<TrabajoMantenimientoDTO, Integer> {

	public List<TrabajoMantenimientoDTO> readByIdMantennimiento(int id);

}
