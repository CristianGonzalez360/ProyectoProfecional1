package business_logic;

import dto.UserCrendentialsDTO;
import dto.UsuarioDTO;
import dto.temporal.SessionDTO;
import services.SessionServiceImpl;

public class AdminLoggerController {

	public SessionDTO logUser(UserCrendentialsDTO credentials) {
		UsuarioDTO usuario = UsuarioDTO.makeTestDTO();
		SessionServiceImpl.getInstance().openSession(usuario, null);
		if (credentials.getName().equals(usuario.getCuenta().getNombreUsuario())
				&& credentials.getPassword().equals(usuario.getCuenta().getPassword())) {
			return SessionServiceImpl.getInstance().getActiveSession();
		}
		return null;
	}

	public void logout() {
		SessionServiceImpl.getInstance().closeSession();
	}
}
