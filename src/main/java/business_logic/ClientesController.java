package business_logic;

import dto.ClienteDTO;
import repositories.ClientesDao;

public class ClientesController {

	private ClientesDao clientesDao;
	
	public ClientesController(ClientesDao clientes) {
		this.clientesDao = clientes;
	}
	
	public ClienteDTO readByDni(Integer dni) {
		assert dni != null;
		return clientesDao.readByDNI(dni);
	}
	
	public void update(ClienteDTO cliente) {
		assert cliente != null;
		clientesDao.update(cliente);
	}
}
