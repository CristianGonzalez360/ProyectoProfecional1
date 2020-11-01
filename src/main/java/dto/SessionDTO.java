package dto;

import java.util.Date;

public class SessionDTO {
	
	private Date initSession;

	private Date closeSession;

	private Integer idUsuario;
	
	private String nombreUsuario;

	private String role;
	
	public SessionDTO() {}

	public Date getInitSession() {
		return initSession;
	}

	public SessionDTO setInitSession(Date initSession) {
		this.initSession = initSession;
		return this;
	}

	public Date getCloseSession() {
		return closeSession;
	}

	public SessionDTO setCloseSession(Date closeSession) {
		this.closeSession = closeSession;
		return this;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}


	public SessionDTO setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
		return this;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public SessionDTO setNombreUsuario(String nombreUsuari) {
		this.nombreUsuario = nombreUsuari;
		return this;
	}

	public String getRole() {
		return role;
	}

	public SessionDTO setRole(String role) {
		this.role = role;
		return this;
	}
}
