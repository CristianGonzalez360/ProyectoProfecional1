package services;

import java.util.Date;

import business_logic.exceptions.ForbiddenException;
import dto.SucursalDTO;
import dto.UsuarioDTO;
import dto.temporal.SessionDTO;

public class SessionServiceImpl implements SessionService {

	private static final String FORBIDDEN = "Ya hay un usuario logueado.";

	private SessionDTO session;

	private static SessionServiceImpl instance;

	private SessionServiceImpl() {
	}

	public static SessionServiceImpl getInstance() {
		if (instance == null)
			instance = new SessionServiceImpl();
		return instance;
	}

	@Override
	public void openSession(UsuarioDTO user, SucursalDTO sucursal) throws ForbiddenException {
		assert user != null;
		assert sucursal != null;
		if (session != null)
			throw new ForbiddenException(FORBIDDEN);
		session = new SessionDTO();
		session.setInitSession(new Date());
		session.setUser(user);
		session.setSucursal(sucursal);
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