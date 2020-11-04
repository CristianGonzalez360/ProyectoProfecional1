package business_logic;

import business_logic.exceptions.NotFoundException;
import dto.UserCrendentialsDTO;
import dto.UsuarioDTO;
import repositories.UsuariosDao;

public class UsuariosController {

	private UsuariosDao dao;

	public UsuariosController(UsuariosDao dao) {
		super();
		this.dao = dao;
	}
}
