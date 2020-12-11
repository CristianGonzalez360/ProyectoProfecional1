package dto.temporal;

public class ConfigSmtpServerDTO {
	
	private String correoRemitente;
	
	private String passwordRemitente;
	
	private String port;
	
	private String host;

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

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "ConfigSmtpServerDTO [correoRemitente=" + correoRemitente + ", passwordRemitente=" + passwordRemitente
				+ ", port=" + port + ", host=" + host + "]";
	}	
}
