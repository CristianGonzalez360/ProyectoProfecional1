package services;

import business_logic.exceptions.ForbiddenException;
import dto.SessionDTO;
import dto.UsuarioDTO;

public interface SessionService {

	void openSession(UsuarioDTO user) throws ForbiddenException;

	void closeSession();

	SessionDTO getActiveSession();

}
