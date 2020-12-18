package dto;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class MonedaDTO {

	private Integer idMoneda;

	private String nombre;

	private String simbolo;

	private Double cotizacionDolar;

	private String cotizacion;
	
	public MonedaDTO() {
	}

	public Integer getIdMoneda() {
		return idMoneda;
	}

	public void setIdMoneda(Integer idMoneda) {
		this.idMoneda = idMoneda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public Double getCotizacionDolar() {
		return cotizacionDolar;
	}

	public void setCotizacionDolar(Double cotizacionDolar) {
		this.cotizacionDolar = cotizacionDolar;
	}	
	
	public String getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	public List<String> validate() {
		List<String> errors = new LinkedList<String>();
		errors.add(error(cotizacion));
		errors.addAll(new StringValidator(getNombre()).notBlank("El nombre de la moneda es obligatorio").validate());
		errors.addAll(new StringValidator(getSimbolo()).notBlank("El simbolo de la moneda es obligatorio").validate());
		return errors;
	}
	
	private String error(String str) {
		String err = "";
		try {
			Double.parseDouble(str);
		}catch(NumberFormatException e) {
			err = "La cotización debe ser un número decimal.";
		}
		return err;
	}

	@Override
	public String toString() {
		return "MonedaDTO [idMoneda=" + idMoneda + ", nombre=" + nombre + ", simbolo=" + simbolo + ", cotizacionDolar="
				+ cotizacionDolar + ", cotizacion=" + cotizacion + "]";
	}
}
