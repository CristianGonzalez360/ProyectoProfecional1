package repositories;

import java.util.List;

import dto.SucursalDTO;

public interface SucursalesDao extends GenericDao<SucursalDTO, Integer> {

	SucursalDTO readByName(String name);

	List<String> readFinancierasByPais(String pais);

	List<SucursalDTO> readByPais(String pais);
}
