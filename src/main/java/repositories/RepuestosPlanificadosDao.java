package repositories;

import java.util.List;

import dto.taller.RepuestoPlanificadoDTO;

public interface RepuestosPlanificadosDao extends GenericDao<RepuestoPlanificadoDTO, Integer> {

	List<RepuestoPlanificadoDTO> readByIdPresupuesto(Integer id);

}
