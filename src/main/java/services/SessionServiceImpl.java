package services;

import java.util.Date;

import business_logic.exceptions.ForbiddenException;
import dto.SessionDTO;
import dto.UsuarioDTO;

public class SessionServiceImpl implements SessionService {

	private static final String FORBIDDEN = "Ya hay un usuario logueado.";
	
	private Date initSession;

	private Date closeSession;

	private UsuarioDTO loggedUser;
	
	private static SessionServiceImpl instance;
	
	private SessionServiceImpl() {
	}
	
	public static SessionServiceImpl getInstance() {
		if(instance == null) instance = new SessionServiceImpl();
		return instance;
	}
	
	private boolean isLoggedUser() { 
		return loggedUser != null;
	}

	@Override
	public void openSession(UsuarioDTO user) throws ForbiddenException {
		assert user != null;
		if(isLoggedUser()) throw new ForbiddenException(FORBIDDEN);
		loggedUser = user;
		initSession = new Date();
	}
	
	@Override
	public void closeSession() {
		loggedUser = null;
		closeSession = new Date();
	}
	
	@Override
	public SessionDTO getActiveSession() {
		return new SessionDTO()
				.setInitSession(initSession)
				.setIdUsuario(loggedUser.getId())
				.setRole(loggedUser.getCuenta().getRole())
				.setNombreUsuario(loggedUser.getDatos().getNombreCompleto());
	}
}