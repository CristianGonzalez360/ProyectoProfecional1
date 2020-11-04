package services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import business_logic.exceptions.ForbiddenException;
import dto.SessionDTO;
import dto.UsuarioDTO;

class SessionServiceImplTest {

	@Test
	void testGetInstance() {
		Assertions.assertNotNull(SessionServiceImpl.getInstance());
	}

	@Test
	void testGetActiveSessionIsNull() {
		SessionDTO session = SessionServiceImpl.getInstance().getActiveSession();
		Assertions.assertNull(session);
		SessionServiceImpl.getInstance().openSession(new UsuarioDTO());
		Assertions.assertNotNull(SessionServiceImpl.getInstance().getActiveSession());
		SessionServiceImpl.getInstance().closeSession();
	}

	@Test
	void testOpenSession() {
		UsuarioDTO dto = new UsuarioDTO().makeTestDTO();
		Assertions.assertNotNull(dto);
		SessionServiceImpl.getInstance().openSession(dto);
		Assertions.assertThrows(ForbiddenException.class, () -> {
			SessionServiceImpl.getInstance().openSession(dto);
		});
		SessionServiceImpl.getInstance().closeSession();
	}
}