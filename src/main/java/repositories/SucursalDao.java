package repositories;

import dto.SucursalDTO;

public interface SucursalDao extends GenericDao<SucursalDTO, Integer> {

	SucursalDTO readByName(String name);

}
