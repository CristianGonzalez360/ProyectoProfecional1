package business_logic;

import dto.ClienteDTO;

public interface ClientesController extends Controller<ClienteDTO, Integer>{

	public ClienteDTO readByDni(int dni);
	
}
