package dto;

import java.util.Date;

public class GarantiaVehiculoDTO {

	private Integer id;

	private Integer idVehiculo;

	private Integer aniosDeGarantia;

	private Integer kilometrajeInicialDelVehiculo;

	private Integer kilometrajeGarantizado;

	private Date fechaInicioDeLaGarantia;

	private Date fechaDeCaducidadDeLaGarantia;

	private Double costoFinalConIVA;

	public GarantiaVehiculoDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public Date getFechaInicioDeLaGarantia() {
		return fechaInicioDeLaGarantia;
	}

	public void setFechaInicioDeLaGarantia(Date fechaInicioDeLaGarantia) {
		this.fechaInicioDeLaGarantia = fechaInicioDeLaGarantia;
	}

	public Date getFechaDeCaducidadDeLaGarantia() {
		return fechaDeCaducidadDeLaGarantia;
	}

	public void setFechaDeCaducidadDeLaGarantia(Date fechaDeCaducidadDeLaGarantia) {
		this.fechaDeCaducidadDeLaGarantia = fechaDeCaducidadDeLaGarantia;
	}

	public Double getCostoFinalConIVA() {
		return costoFinalConIVA;
	}

	public void setCostoFinalConIVA(Double costoFinalConIVA) {
		this.costoFinalConIVA = costoFinalConIVA;
	}

	public Integer getAniosDeGarantia() {
		return aniosDeGarantia;
	}

	public void setAniosDeGarantia(Integer aniosDeGarantia) {
		this.aniosDeGarantia = aniosDeGarantia;
	}

	public Integer getKilometrajeInicialDelVehiculo() {
		return kilometrajeInicialDelVehiculo;
	}

	public void setKilometrajeInicialDelVehiculo(Integer kilometrajeInicialDelVehiculo) {
		this.kilometrajeInicialDelVehiculo = kilometrajeInicialDelVehiculo;
	}

	public Integer getKilometrajeGarantizado() {
		return kilometrajeGarantizado;
	}

	public void setKilometrajeGarantizado(Integer kilometrajeGarantizado) {
		this.kilometrajeGarantizado = kilometrajeGarantizado;
	}

	@Override
	public String toString() {
		return "GarantiaVehiculoDTO [id=" + id + ", idVehiculo=" + idVehiculo + ", aniosDeGarantia=" + aniosDeGarantia
				+ ", kilometrajeInicialDelVehiculo=" + kilometrajeInicialDelVehiculo + ", kilometrajeGarantizado="
				+ kilometrajeGarantizado + ", fechaInicioDeLaGarantia=" + fechaInicioDeLaGarantia
				+ ", fechaDeCaducidadDeLaGarantia=" + fechaDeCaducidadDeLaGarantia + ", costoFinalConIVA="
				+ costoFinalConIVA + "]";
	}
}
