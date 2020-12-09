package dto.temporal;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import dto.validators.NotDateBefore;
import dto.validators.StringValidator;

public class AltaDeTurnoDTO {

	private Integer idTurno;
	private Integer idCliente;
	private String dniCliente;
	private Date fechaAlta;
	private Date fechaCancelado;
	private Date fechaProgramada;
	private String nombreCliente;
	private String apellidoCliente;
	private String telefonoCliente;
	private String emailCliente;

	public AltaDeTurnoDTO() {
	}

	public AltaDeTurnoDTO makeTestDTO() {
		AltaDeTurnoDTO ret = new AltaDeTurnoDTO();
		ret.setIdCliente(2345333);
		ret.setDniCliente("12345678");
		ret.setFechaAlta(new Date());
		ret.setFechaProgramada(new Date());
		ret.setNombreCliente("Susan");
		ret.setApellidoCliente("Doe");
		ret.setTelefonoCliente("96385274");
		ret.setEmailCliente("ejemplo@dominio.com");
		return ret;
	}

	public List<String> validate() {
		List<String> errors = new LinkedList<>();
		errors.addAll(
				new StringValidator(nombreCliente).regex("El Nombre debe ser un nombre válido.", "[a-zA-Záéíóú ]+")
						.max(40, "Max 40 caracteres para el Nombre.").validate());
		errors.addAll(
				new StringValidator(apellidoCliente).regex("El Apellido debe ser un nombre válido.", "[a-zA-Záéíóú ]+")
						.max(40, "Max 40 caracteres para el Apellido.").validate());
		errors.addAll(new StringValidator(dniCliente).max(8, "Max 8 caracteres para el DNI.")
				.number("El DNI debe ser un dni.").validate());
		errors.addAll(
				new StringValidator(telefonoCliente).number("El Teléfono debe ser un nro telefónico.").validate());
		errors.addAll(new StringValidator(emailCliente).email("El Email debe ser un email.").validate());
		if (fechaProgramada != null) {
			errors.addAll(new NotDateBefore("La fecha debe ser posterior a la actual").validate(fechaProgramada));
		} else {
			errors.add("Programar una fecha es obligatorio");
		}
		return errors;
	}

	public Integer getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(Integer idTurno) {
		this.idTurno = idTurno;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaCancelado() {
		return fechaCancelado;
	}

	public void setFechaCancelado(Date fechaCancelado) {
		this.fechaCancelado = fechaCancelado;
	}

	public Date getFechaProgramada() {
		return fechaProgramada;
	}

	public void setFechaProgramada(Date fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	@Override
	public String toString() {
		return "AltaDeTurnoDTO [idTurno=" + idTurno + ", idCliente=" + idCliente + ", dniCliente=" + dniCliente
				+ ", fechaAlta=" + fechaAlta + ", fechaCancelado=" + fechaCancelado + ", fechaProgramada="
				+ fechaProgramada + ", nombreCliente=" + nombreCliente + ", apellidoCliente=" + apellidoCliente
				+ ", telefonoCliente=" + telefonoCliente + ", emailCliente=" + emailCliente + "]";
	}

}
