package dto;

import java.util.LinkedList;
import java.util.List;

import dto.validators.Patterns;
import dto.validators.StringValidator;

public class TarjetaCreditoDTO {

	private String numeroTarjeta;
	
	private String nombreyapellido;

	private String dni;

	private String fechaExpiracion;

	private String codSeguridad;

	private String cuotas;

	

	

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getNombreyapellido() {
		return nombreyapellido;
	}

	public void setNombreyapellido(String nombreyapellido) {
		this.nombreyapellido = nombreyapellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public String getCodSeguridad() {
		return codSeguridad;
	}

	public void setCodSeguridad(String codSeguridad) {
		this.codSeguridad = codSeguridad;
	}

	public String getCuotas() {
		return cuotas;
	}

	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}

	

	public List<String> validate() {
		LinkedList<String> ret = new LinkedList<>();
		
		
		ret.addAll(new StringValidator(numeroTarjeta).max(16, "El numero de tarjeta debe tener 16 digitos")
				.min(16, "El numero de tarjeta debe tener 16 digitos").validate());
		
			
		ret.addAll(new StringValidator(nombreyapellido).notBlank("Debe ingresar un Nombre y apellido.")
				.regex("El Nombre y apellido debe tener solo letras.", "[a-zA-Záéíóú ]+").validate());
				
		ret.addAll(new StringValidator(dni).notBlank("Debe ingresar un numero de dni.")
				.number("El DNI solo de tener números.").validate());
		
	
		
		ret.addAll(new StringValidator(codSeguridad).notBlank("Debe ingresar un codigo de seguridad")
				.min(3, "El codigo de seguridad debe ser de 3 digitos").max(3, "El codigo de seguridad debe ser de 3 digitos").validate());
		

		


		return ret;
	}
}