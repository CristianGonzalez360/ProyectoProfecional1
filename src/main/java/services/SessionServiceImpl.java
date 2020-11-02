package services;

import java.util.Date;

import business_logic.exceptions.ForbiddenException;
import dto.SessionDTO;
import dto.UsuarioDTO;

public class SessionServiceImpl implements SessionService {

	private static final String FORBIDDEN = "Ya hay un usuario logueado.";
	
	private SessionDTO session;
	
	private static SessionServiceImpl instance;
	
	private SessionServiceImpl() {
	}
	
	public static SessionServiceImpl getInstance() {
		if(instance == null) instance = new SessionServiceImpl();
		return instance;
	}
	
	@Override
	public void openSession(UsuarioDTO user) throws ForbiddenException {
		assert user != null;
		if(session != null) throw new ForbiddenException(FORBIDDEN);
		session = new SessionDTO();
		session.setInitSession(new Date());
		session.setUser(user);
	}
	
	@Override
	public void closeSession() {
		session = null;
	}
	
	@Override
	public SessionDTO getActiveSession() {
		return session;
	}
}