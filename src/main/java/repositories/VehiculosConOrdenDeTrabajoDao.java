package repositories;

import java.util.List;

import dto.taller.IngresoOrdenDeTrabajoDTO;

public interface VehiculosConOrdenDeTrabajoDao extends GenericDao<IngresoOrdenDeTrabajoDTO, Integer> {

	List<IngresoOrdenDeTrabajoDTO> readByClienteId(Integer idCliente);

	IngresoOrdenDeTrabajoDTO readByPatente(String patente);
}
