package dto;

import java.util.Date;

public class CuentaDTO {

	private Integer idCuenta;

	private Date fechaDeAlta;

	private Date fechaDeBaja;

	private String nombreUsuario;

	private String password;

	private String role;

	public CuentaDTO() {	
	}
		
	public CuentaDTO makeTestDTO() {
		CuentaDTO cuenta = new CuentaDTO();
		cuenta.setFechaDeAlta(new Date());
		cuenta.setFechaDeBaja(new Date());
		cuenta.setNombreUsuario("u001");
		cuenta.setPassword("pu001");
		cuenta.setRole("test");
		return cuenta;
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

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "CuentaDTO [idCuenta=" + idCuenta + ", fechaDeAlta=" + fechaDeAlta + ", fechaDeBaja=" + fechaDeBaja
				+ ", nombreUsuario=" + nombreUsuario + ", password=" + password + ", role=" + role + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuentaDTO other = (CuentaDTO) obj;
		if (fechaDeAlta == null) {
			if (other.fechaDeAlta != null)
				return false;
		} else if (!fechaDeAlta.equals(other.fechaDeAlta))
			return false;
		if (fechaDeBaja == null) {
			if (other.fechaDeBaja != null)
				return false;
		} else if (!fechaDeBaja.equals(other.fechaDeBaja))
			return false;
		if (idCuenta == null) {
			if (other.idCuenta != null)
				return false;
		} else if (!idCuenta.equals(other.idCuenta))
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
}
