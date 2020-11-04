package repositories;

import dto.OrdenDeTrabajoDTO;

public interface OrdenesDeTrabajoDao extends GenericDao<OrdenDeTrabajoDTO, Integer> {

	OrdenDeTrabajoDTO readByIdVehiculo(Integer idVehiculo);

}
