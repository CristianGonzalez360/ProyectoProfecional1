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
		errors.addAll(valNum(this.kilometrajeGarantia, "El kilometraje en garantía debe ser un número"));
		errors.addAll(valNum(this.kilometraje, "El kilometraje debe ser un número"));
		errors.addAll(valNum(this.nroPolizaSeguro, "El nro. de la poliza debe ser un número"));
		errors.addAll(valNum(this.nroChasis, "El número de chasis debe ser un número"));
		errors.addAll(valNum(this.nroMotor, "El número de motor debe ser un número"));
		errors.addAll(new StringValidator(this.modelo).number("El modelo debe ser un número").validate());
		errors.addAll(new StringValidator(this.marca).notBlank("La marca es obligatoria.").validate());
		errors.addAll(new StringValidator(this.asegurador).notBlank("El asegurador es obligatorio").validate());
		errors.addAll(new StringValidator(this.patente).notBlank("La patente es obligatoria").validate());
		return errors;
	}
		
	public List<String> valNum(String atr, String mess) {
		return new StringValidator(atr).regex(mess, Patterns.NON_NEGATIVE_INTEGER_FIELD).validate();
	}
	
	public AltaDeVehiculoDTO() {
		super();
		nroChasis = "";
		nroMotor = "";
		kilometraje ="";
		marca= "";
		modelo= "";
		patente="";
		color="";
		combustion="";
		descripcion="";
		asegurador="";
		nroPolizaSeguro="";
		kilometrajeGarantia="";
	}

	public String getNroChasis() {
		return this.nroChasis;
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

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
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
}
