package repositories;

import dto.ClienteDTO;

public interface ClientesDao extends GenericDao<ClienteDTO, Integer>{
	
	ClienteDTO readByDNI(int dni);

}
