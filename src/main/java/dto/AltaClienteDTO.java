package dto;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class AltaClienteDTO {
	
	private String nombreCompleto;
	
	private String dni;
	
	private String telefono;
	
	private String email;
	
	private String calle;
	
	private String altura;
	
	private String piso;
	
	private String dpto;
	
	private String localidad;

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}
	
	public List<String> validate() {
		LinkedList<String> ret = new LinkedList<>();
		
		ret.addAll(new StringValidator(nombreCompleto)
				.notBlank("Debe ingresar un nombre.")
				.regex("El nombre debe tener solo letras.", "[a-zA-Záéíóú ]+")
				.validate());
		
		ret.addAll(new StringValidator(dni)
				.regex("El DNI solo de tener números.", Patterns.NON_NEGATIVE_INTEGER_FIELD)
				.validate());
		
		ret.addAll(new StringValidator(telefono)
				.notBlank("Debe ingresar un teléfono.")
				.regex("El teléfono solo puede tener números, espacios y signos + y -)", "[0-9\\-\\+ ]+")
				.validate());
		
		ret.addAll(new StringValidator(email)
				.notBlank("Debe ingresar una dirección de email.")
				.regex("Introduzca una dirección de email válida.", Patterns.EMAIL)
				.validate());
		
		ret.addAll(new StringValidator(calle)
				.regex("La calle solo puede tener letras y números", Patterns.TEXT_FIELD)
				.validate());
		
		ret.addAll(new StringValidator(altura)
				.regex("La altura solo debe tener números", Patterns.NON_NEGATIVE_INTEGER_FIELD)
				.validate());
		
		ret.addAll(new StringValidator(piso)
				.regex("El piso solo debe tener números", Patterns.NON_NEGATIVE_INTEGER_FIELD)
				.validate());
		
		ret.addAll(new StringValidator(dpto)
				.regex("El piso solo debe tener números", Patterns.NON_NEGATIVE_INTEGER_FIELD)
				.validate());
		
		ret.addAll(new StringValidator(localidad)
				.regex("La localidad solo debe letras y números", Patterns.TEXT_FIELD)
				.validate());
		
		return ret;
	}
}


	