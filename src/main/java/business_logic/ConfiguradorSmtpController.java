package business_logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dto.temporal.ConfigSmtpServerDTO;
import services.PropertiesServiceImpl;

public class ConfiguradorSmtpController {
	
	private static final String file = "conf/config_smtp.properties";
	
	public ConfiguradorSmtpController() {}
		
	public void save(ConfigSmtpServerDTO dto) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("correo_remitente", dto.getCorreoRemitente());
		map.put("password_remitente", dto.getPasswordRemitente());
		map.put("host", dto.getHost());
		map.put("port", dto.getPort());
		PropertiesServiceImpl service = new PropertiesServiceImpl(file);
		try {
			service.updateValues(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ConfigSmtpServerDTO read() {
		ConfigSmtpServerDTO dto = new ConfigSmtpServerDTO();
		PropertiesServiceImpl service = new PropertiesServiceImpl(file);
		try {
			Map<String, String> map = service.read();
			dto.setCorreoRemitente(map.get("correo_remitente"));
			dto.setPasswordRemitente(map.get("password_remitente"));
			dto.setHost(map.get("host"));
			dto.setPort(map.get("port"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}
}
