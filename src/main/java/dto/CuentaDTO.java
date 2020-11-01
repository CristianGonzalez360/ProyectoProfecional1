package dto;

import java.util.Date;

public class CuentaDTO {
	
	private Date fechaDeAlta;
	
	private Date fechaDeBaja;
	
	private boolean activa;
	
	private String nombreUsuario;
	
	private String password;
	
	public CuentaDTO(Date fechaDeAlta, Date fechaDeBaja, boolean activa, String nombreUsuario, String password) {
		super();
		this.fechaDeAlta = fechaDeAlta;
		this.fechaDeBaja = fechaDeBaja;
		this.activa = activa;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}

	public Date getFechaDeAlta() {
		return fechaDeAlta;
	}

	public void setFechaDeAlta(Date fechaDeAlta) {
		this.fechaDeAlta = fechaDeAlta;
	}

	public Date getFechaDeBaja() {
		return fechaDeBaja;
	}

	public void setFechaDeBaja(Date fechaDeBaja) {
		this.fechaDeBaja = fechaDeBaja;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
