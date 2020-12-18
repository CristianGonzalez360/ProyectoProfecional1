package dto.temporal;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class ConfigSmtpServerDTO {

	private String correoRemitente;

	private String passwordRemitente;

	public ConfigSmtpServerDTO() {
	}

	public String getCorreoRemitente() {
		return correoRemitente;
	}

	public void setCorreoRemitente(String correoRemitente) {
		this.correoRemitente = correoRemitente;
	}

	public String getPasswordRemitente() {
		return passwordRemitente;
	}

	public void setPasswordRemitente(String passwordRemitente) {
		this.passwordRemitente = passwordRemitente;
	}

	@Override
	public String toString() {
		return "ConfigSmtpServerDTO [correoRemitente=" + correoRemitente + ", passwordRemitente=" + passwordRemitente
				+ "]";
	}

	public List<String> validate() {
		List<String> errors = new LinkedList<>();
		errors.addAll(
				new StringValidator(this.correoRemitente).email("El correo del remitente es obligatorio").validate());
		errors.addAll(new StringValidator(this.passwordRemitente).notBlank("El password del remitente es obligatorio")
				.validate());
		return errors;
	}
}
