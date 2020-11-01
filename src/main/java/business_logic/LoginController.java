package business_logic;

import business_logic.exceptions.NotFoundException;
import dto.UserCrendentialsDTO;
import dto.UserDTO;
import repositories.UsuariosDao;

public class LoginController {
	
	private UsuariosDao usersDao;
		
	public LoginController(UsuariosDao userDao) {
		this.usersDao = userDao;
	}
	
	public UserDTO authenticate(UserCrendentialsDTO crenditals) {
		UserDTO usuario = usersDao.readByCredentials(crenditals.getEmail(), crenditals.getPassword());
		if(usuario == null) throw new NotFoundException("Usuario no encontrado");
		return usuario;
	}
}
