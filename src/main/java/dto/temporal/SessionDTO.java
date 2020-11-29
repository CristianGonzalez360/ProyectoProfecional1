package dto.temporal;

import java.util.Date;

import dto.UsuarioDTO;

public class SessionDTO {

	private Date initSession;

	private UsuarioDTO user;

	public SessionDTO() {
	}

	public Date getInitSession() {
		return initSession;
	}

	public UsuarioDTO getUser() {
		return user;
	}

	public void setInitSession(Date initSession) {
		this.initSession = initSession;
	}

	public String getRole() {
		return user.getCuenta().getRole();
	}

	public String getNombreUsuario() {
		return user.getDatos().getNombreCompleto();
	}

	public Integer getIdUsuario() {
		return this.user.getId();
	}

	public void setUser(UsuarioDTO user) {
		this.user = user;
	}
}
