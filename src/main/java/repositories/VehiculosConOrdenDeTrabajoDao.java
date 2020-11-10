package repositories;

import java.util.List;

import dto.VehiculoConOrdenDeTrabajoDTO;

public interface VehiculosConOrdenDeTrabajoDao extends GenericDao<VehiculoConOrdenDeTrabajoDTO, Integer> {

	List<VehiculoConOrdenDeTrabajoDTO> readByClienteId(Integer idCliente);

	VehiculoConOrdenDeTrabajoDTO readByPatente(String patente);
}
