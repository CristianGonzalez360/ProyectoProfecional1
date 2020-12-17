package dto.taller;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class AltaFichaNuevaDTO {
	
	private String nroChasis;

	private String nroMotor;

	private String marca;

	private String modelo;

	private String patente;

	private String color;

	private String combustion;
	
	public AltaFichaNuevaDTO () {
		
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

	public List<String> validateNuevaFicha() {
		List<String> errors = new LinkedList<>();

		errors.addAll(new StringValidator(this.nroChasis.toString()).number("El número de chasis debe ser un número").validate());
		errors.addAll(new StringValidator(this.nroMotor.toString()).number("El número de motor debe ser un número").validate());
		errors.addAll(new StringValidator(this.patente).notBlank("La patente es obligatoria").validate());
		errors.addAll(new StringValidator(this.modelo.toString()).number("El modelo debe ser un número").validate());
		errors.addAll(new StringValidator(this.marca).notBlank("La marca es obligatoria.").validate());
		errors.addAll(new StringValidator(this.color).notBlank("El color es obligatorio.").validate());
		errors.addAll(new StringValidator(this.combustion).notBlank("La combustión es obligatoria.").validate());
		return errors;
	}
	
}
