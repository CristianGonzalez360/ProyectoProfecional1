package business_logic;

import business_logic.exceptions.NotFoundException;
import dto.UserCrendentialsDTO;
import dto.UsuarioDTO;
import repositories.UsuariosDao;

public class LoginController {
	
	private UsuariosDao usersDao;
		
	public LoginController(UsuariosDao userDao) {
		this.usersDao = userDao;
	}
	
	public UsuarioDTO authenticate(UserCrendentialsDTO crenditals) {
		UsuarioDTO usuario = usersDao.readByCredentials(crenditals.getEmail(), crenditals.getPassword());
		if(usuario == null) throw new NotFoundException("Usuario no encontrado");
		return usuario;
	}
}
