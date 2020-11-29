package repositories;

import java.util.List;

import dto.taller.OrdenDeTrabajoDTO;

public interface OrdenesDeTrabajoDao extends GenericDao<OrdenDeTrabajoDTO, Integer> {

	OrdenDeTrabajoDTO readByIdVehiculoConOtNoCerrada(Integer idVehiculo);

	List<OrdenDeTrabajoDTO> readByVehiculoId(Integer idVehiculo);

	List<OrdenDeTrabajoDTO> readAllOrdenesSinEntregar();
}
