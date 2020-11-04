package business_logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import business_logic.exceptions.ForbiddenException;
import dto.SessionDTO;
import dto.UserCrendentialsDTO;
import dto.UsuarioDTO;
import repositories.UsuariosDao;
import services.SessionService;

class LoginControllerTest {

	private SessionService service = Mockito.mock(SessionService.class);
	private UsuariosDao dao = Mockito.mock(UsuariosDao.class);
	private LoginController controller = new LoginController(dao, service);

	UsuarioDTO target = new UsuarioDTO().makeTestDTO();
	String nombreDeUsuario = target.getCuenta().getNombreUsuario();
	String password = target.getCuenta().getPassword();

	@Test
	void testLoginController() {
		Assertions.assertNotNull(dao);
		Assertions.assertNotNull(controller);
	}

	@Test
	void testLogUserThrowsNotFoundException() {
		Mockito.when(dao.readByCredentials(nombreDeUsuario, password)).thenReturn(null);
		Assertions.assertThrows(ForbiddenException.class, () -> {
			controller.logUser(new UserCrendentialsDTO(nombreDeUsuario, password));
		});
	}

	@Test
	void testLogUserThrowsForbiddenException() {
		Mockito.when(dao.readByCredentials(nombreDeUsuario, password)).thenReturn(target);
		Assertions.assertThrows(ForbiddenException.class, () -> {
			controller.logUser(new UserCrendentialsDTO(nombreDeUsuario, password));
		});
	}

	@Test
	void testLogUserThrowsForbiddenExceptionBySessionActive() {
		Mockito.when(dao.readByCredentials(nombreDeUsuario, password)).thenReturn(target);
		Mockito.when(service.getActiveSession()).thenReturn(new SessionDTO());
		Assertions.assertThrows(ForbiddenException.class, () -> {
			controller.logUser(new UserCrendentialsDTO(nombreDeUsuario, password));
		});
	}
}
