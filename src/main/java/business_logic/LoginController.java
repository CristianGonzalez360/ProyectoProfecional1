package business_logic;

import business_logic.exceptions.ForbiddenException;
import dto.SucursalDTO;
import dto.UserCrendentialsDTO;
import dto.UsuarioDTO;
import dto.temporal.SessionDTO;
import repositories.UsuariosDao;
import services.PropertiesServiceImpl;
import services.SessionService;

public class LoginController {

	private static final String FORBIDDEN = "Operación no permitida";

	private UsuariosDao dao;
	
	private SessionService service;
	
	private SucursalPredeterminadaReader reader;
	
	public LoginController(UsuariosDao dao, SessionService service, SucursalPredeterminadaReader reader) {
		this.dao = dao;
		this.service = service;
		this.reader = reader;	
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
		
		service.openSession(usuario, readActiveSucursal());
		return service.getActiveSession();
	}

	public SucursalDTO readActiveSucursal() {
		SucursalDTO sucursal = reader.readSucursalPredeterminada();
		return sucursal;
	}
	
	public void logout() {
		service.closeSession();
	}
}
