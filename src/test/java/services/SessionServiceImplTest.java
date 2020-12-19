package services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import business_logic.exceptions.ForbiddenException;
import dto.SucursalDTO;
import dto.UsuarioDTO;
import dto.temporal.SessionDTO;

class SessionServiceImplTest {

	@Test
	void testGetInstance() {
		Assertions.assertNotNull(SessionServiceImpl.getInstance());
	}

	@Test
	void testGetActiveSessionIsNull() {
		SessionDTO session = SessionServiceImpl.getInstance().getActiveSession();
		Assertions.assertNull(session);
		SessionServiceImpl.getInstance().openSession(new UsuarioDTO(), new SucursalDTO());
		Assertions.assertNotNull(SessionServiceImpl.getInstance().getActiveSession());
		SessionServiceImpl.getInstance().closeSession();
	}

	@Test
	void testOpenSession() {
		@SuppressWarnings("static-access")
		UsuarioDTO dto = new UsuarioDTO().makeTestDTO();
		Assertions.assertNotNull(dto);
		SessionServiceImpl.getInstance().openSession(dto, new SucursalDTO());
		Assertions.assertThrows(ForbiddenException.class, () -> {
			SessionServiceImpl.getInstance().openSession(dto, new SucursalDTO());
		});
		SessionServiceImpl.getInstance().closeSession();
	}
}