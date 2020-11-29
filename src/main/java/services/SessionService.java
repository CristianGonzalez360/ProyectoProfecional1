package services;

import business_logic.exceptions.ForbiddenException;
import dto.UsuarioDTO;
import dto.temporal.SessionDTO;

public interface SessionService {

	void openSession(UsuarioDTO user) throws ForbiddenException;

	void closeSession();

	SessionDTO getActiveSession();
}
