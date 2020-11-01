package dto;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class UserCrendentialsDTO {

	private String email;

	private String password;

	public UserCrendentialsDTO(String email, String password) {
		setEmail(email);
		setPassword(password);
	}
			
	public List<String> validate() {
		List<String> errors = new LinkedList<>();
		errors.addAll(new StringValidator(email)
				.max(50, "Max 50 caracteres para el email.")
				.email("El email debe ser un email.")
				.validate());
		errors.addAll(new StringValidator(password)
				.notBlank("El password es requerido")
				.max(8, "Max 8 caracteres para el password")
				.min(6, "Min 6 caraceteres para el password")
				.notSpaces("El password no debe poseer caracteres en blanco.")
				.notInLowerCase("El password debe estar en minuscula")
				.validate());
		return null;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
}
