package repositories;

import dto.taller.MantenimientoDTO;

public interface MantenimientoDao extends GenericDao<MantenimientoDTO, Integer> {

	public int getIdMaximo();
}
