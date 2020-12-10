package dto;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

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

	public List<String> validate() {
		List<String> errors = new LinkedList<>();
		errors.addAll(new StringValidator(this.nombreUsuario).notBlank("El nombre es obligatorio")
				.max(20, "Maximo una longitud de 8 caracteres para el nombre").validate());
		errors.addAll(new StringValidator(this.password).notBlank("El password es obligatorio")
				.max(20, "Maximo una longitud de 8 caracteres para el password").validate());
		errors.addAll(new StringValidator(this.role).notBlank("El rol es obligatorio")
				.max(20, "Maximo una longitud de 12 caracteres para el rol").validate());		
		return errors;
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
