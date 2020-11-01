package business_logic;

import business_logic.exceptions.ForbiddenException;
import dto.SessionDTO;
import dto.UserCrendentialsDTO;
import dto.UsuarioDTO;
import repositories.UsuariosDao;
import services.SessionService;

public class LoginController {
	
	private static final String FORBIDDEN = "Operación no permitida";
	
	private UsuariosDao dao;
	
	private SessionService service;
		
	public LoginController(UsuariosDao dao, SessionService service) {
		this.dao = dao;
		this.service = service;
	}
	
	public SessionDTO logUser(UserCrendentialsDTO credentials) {
		assert credentials != null;
		UsuarioDTO usuario = dao.readByCredentials(credentials.getName(), credentials.getPassword());
		if(usuario == null) throw new ForbiddenException(FORBIDDEN);
		if(usuario.getCuenta().getFechaDeBaja() != null) throw new ForbiddenException(FORBIDDEN);
		if(service.getActiveSession() != null) throw new ForbiddenException(FORBIDDEN);
		service.openSession(usuario);
		return service.getActiveSession();
	}
}
