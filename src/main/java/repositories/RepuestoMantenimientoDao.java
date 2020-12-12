package repositories;

import java.util.List;

import dto.taller.RepuestoMantenimientoDTO;

public interface RepuestoMantenimientoDao extends GenericDao<RepuestoMantenimientoDTO, Integer> {

	public List<RepuestoMantenimientoDTO> readByIdMantenimiento(Integer id);

}
