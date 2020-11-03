package dto;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class AltaDeVehiculoDTO {
	
	private String nroChasis;
	
	private String nroMotor;
	
	private String kilometraje;
	
	private String marca;
	
	private String modelo;
	
	private String patente;
	
	private String color;
	
	private String combustion;
	
	private String descripcion;
	
	private String asegurador;
	
	private String nroPolizaSeguro;
	
	private String kilometrajeGarantia;
	
	public List<String> validate() {
		List<String> errors = new LinkedList<>();
		errors.addAll(new StringValidator(nroMotor).regex("El nro de motor debe ser un número", Patterns.NON_NEGATIVE_INTEGER_FIELD)
				.validate());
		errors.addAll(numberValidtion(nroChasis, "El nro de chasis debe ser un número"));
		errors.addAll(numberValidtion(kilometraje, "El kilometraje debe ser un número"));
		errors.addAll(new StringValidator(marca).notBlank("La marca del vehículo no debe ser vacía").validate());
		errors.addAll(new StringValidator(patente).notBlank("La patente no debe ser vacía").validate());
		errors.addAll(new StringValidator(asegurador).notBlank("El asegurador no puede ser vacio").validate());
		errors.addAll(numberValidtion(nroPolizaSeguro, "El nro. de la poliza del seguro debe ser un nummero"));
		if(!kilometrajeGarantia.isEmpty()) errors.addAll(new StringValidator(kilometrajeGarantia).regex("El kilometraje en garantía debe ser un numero", Patterns.NON_NEGATIVE_INTEGER_FIELD).validate());
		return errors;
	}
	
	public List<String> numberValidtion(String field, String message) {
		return new StringValidator(field).regex(message, Patterns.NON_NEGATIVE_INTEGER_FIELD).validate();
	}
	public AltaDeVehiculoDTO() {
		super();
	}

	public String getNroChasis() {
		return nroChasis;
	}

	public void setNroChasis(String nroChasis) {
		this.nroChasis = nroChasis;
	}

	public String getNroMotor() {
		return nroMotor;
	}

	public void setNroMotor(String nroMotor) {
		this.nroMotor = nroMotor;
	}

	public String getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(String kilometraje) {
		this.kilometraje = kilometraje;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCombustion() {
		return combustion;
	}

	public void setCombustion(String combustion) {
		this.combustion = combustion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAsegurador() {
		return asegurador;
	}

	public void setAsegurador(String asegurador) {
		this.asegurador = asegurador;
	}

	public String getNroPolizaSeguro() {
		return nroPolizaSeguro;
	}

	public void setNroPolizaSeguro(String nroPolizaSeguro) {
		this.nroPolizaSeguro = nroPolizaSeguro;
	}

	public String getKilometrajeGarantia() {
		return kilometrajeGarantia;
	}

	public void setKilometrajeGarantia(String kilometrajeGarantia) {
		this.kilometrajeGarantia = kilometrajeGarantia;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}
}
