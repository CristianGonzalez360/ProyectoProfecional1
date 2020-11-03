package dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;
import presentacion.views.utils.ErrorDialog;

public class TurnoDTO {

	private Integer idTurno;

	private Integer idCliente;

	private Integer dniCliente;

	private Date fechaAlta;

	private Date fechaCancelado;

	private Date fechaProgramada;

	private String nombreCliente;

	public TurnoDTO() {

	}

	public TurnoDTO makeTestDTO() {
		TurnoDTO ret = new TurnoDTO();
		ret.setIdCliente(2345333);
		ret.setDniCliente(13231);
		ret.setFechaAlta(new Date());
		ret.setFechaProgramada(new Date());
		ret.setNombreCliente("Susan Doe");
		return ret;
	}

	public List<String> validate() {
		List<String> errors = new LinkedList<>();
		errors.addAll(new StringValidator(nombreCliente).notBlank("El Nombre es obligatorio.")
				.max(40, "Max 40 caracteres para el Nombre.").validate());
		errors.addAll(new StringValidator(String.valueOf(dniCliente)).notBlank("El DNI es obligatorio.")
				.regex("Debe ser un DNI correcto", Patterns.DNI).validate());
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

	@Override
	public String toString() {
		return "TurnoDTO [idTurno=" + idTurno + ", idCliente=" + idCliente + ", dniCliente=" + dniCliente
				+ ", fechaAlta=" + fechaAlta + ", fechaCancelado=" + fechaCancelado + ", fechaProgramada="
				+ fechaProgramada + ", nombreCliente=" + nombreCliente + "]";
	}

	public void setDniCliente(Integer dni) {
		this.dniCliente = dni;
	}

	public Integer getDniCliente() {
		return this.dniCliente;
	}
}
