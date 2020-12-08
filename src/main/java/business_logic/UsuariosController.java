package business_logic;

import java.util.List;

import dto.UsuarioDTO;
import repositories.DaosFactory;

public class UsuariosController {
	
	private DaosFactory daos;

	public UsuariosController(DaosFactory daos) {
		super();
		this.daos = daos;
	}

	public UsuarioDTO readById(Integer id) {
		return daos.makeUsuariosDao().readByID(id);
	}

	public List<UsuarioDTO> readAll() {
		return daos.makeUsuariosDao().readAll();
	}
}
