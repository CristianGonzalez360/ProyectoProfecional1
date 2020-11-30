package business_logic;

import business_logic.exceptions.ForbiddenException;
import dto.SucursalDTO;
import dto.UserCrendentialsDTO;
import dto.UsuarioDTO;
import dto.temporal.SessionDTO;
import repositories.SucursalesDao;
import repositories.UsuariosDao;
import services.SessionService;

public class LoginController {

	private static final String FORBIDDEN = "Operación no permitida";

	private UsuariosDao dao;

	private SucursalesDao sucursalesDao;
	
	private SessionService service;
	
	public LoginController(UsuariosDao dao, SessionService service, SucursalesDao sucDao) {
		this.dao = dao;
		this.service = service;
		this.sucursalesDao = sucDao;	
	}

	public SessionDTO logUser(UserCrendentialsDTO credentials) {
		assert credentials != null;
		UsuarioDTO usuario = dao.readByCredentials(credentials.getName(), credentials.getPassword());
		if (usuario == null)
			throw new ForbiddenException(FORBIDDEN);
		if (usuario.getCuenta().getFechaDeBaja() != null)
			throw new ForbiddenException(FORBIDDEN);
		if (service.getActiveSession() != null)
			throw new ForbiddenException(FORBIDDEN);
		
		SucursalDTO sucursal = sucursalesDao.readByID(0);
		
		service.openSession(usuario, sucursal);
		return service.getActiveSession();
	}

	public void logout() {
		service.closeSession();
	}
}
