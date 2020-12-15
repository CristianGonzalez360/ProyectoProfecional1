package dto.temporal;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class ConfigDatabaseDTO {

	private String user;

	private String password;

	private String port;

	private String ip;

	public ConfigDatabaseDTO() {
		super();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "ConfigDatabaseDTO [user=" + user + ", password=" + password + ", port=" + port + ", ip=" + ip + "]";
	}

	public List<String> validate() {
		List<String> userErrors = new StringValidator(user).notBlank("El usuario es obligatorio").validate();
		List<String> passwordErrors = new StringValidator(password).notBlank("El password es obligatorio").validate();
		List<String> portErrors = new StringValidator(port).notBlank("El port es obligatorio")
				.number("El port debe ser un numero.").validate();
		List<String> ipErrors = new LinkedList<>();
		if (ip.isEmpty()) {
			ipErrors = new StringValidator(ip).notBlank("El ip es obligatorio").validate();
		}
		if (!ip.contains("localhost")) {
			ipErrors = new StringValidator(ip).number("Si el ip no es localhost debe ser un numero natural").validate();
		}
		userErrors.addAll(passwordErrors);
		userErrors.addAll(portErrors);
		userErrors.addAll(ipErrors);
		return userErrors;
	}
}
