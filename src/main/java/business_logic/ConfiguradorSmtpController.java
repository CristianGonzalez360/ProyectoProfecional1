package business_logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dto.temporal.ConfigSmtpServerDTO;
import services.PropertiesServiceImpl;

public class ConfiguradorSmtpController {

	private static final String file = "conf/config_smtp.properties";

	public ConfiguradorSmtpController() {
	}

	public void save(ConfigSmtpServerDTO dto) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("correo_remitente", dto.getCorreoRemitente());
		map.put("password_remitente", dto.getPasswordRemitente());
		map.put("fechaRecordatorio", "");
		PropertiesServiceImpl service = new PropertiesServiceImpl(file);
		service.updateValues(map);

	}

	public ConfigSmtpServerDTO read() throws IOException {
		ConfigSmtpServerDTO dto = new ConfigSmtpServerDTO();
		PropertiesServiceImpl service = new PropertiesServiceImpl(file);
		Map<String, String> map = service.read();
		dto.setCorreoRemitente(map.get("correo_remitente"));
		dto.setPasswordRemitente(map.get("password_remitente"));
		return dto;
	}
}
