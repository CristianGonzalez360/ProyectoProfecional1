package business_logic;

import dto.UsuarioDTO;
import repositories.UsuariosDao;

public class UsuariosController {
	
	private UsuariosDao dao;

	public UsuariosController(UsuariosDao dao) {
		super();
		this.dao = dao;
	}
	
	public UsuarioDTO readByCredentials(String email, String password) {
		return dao.readByCredentials(email, password);
	}
}
