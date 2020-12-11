package business_logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dto.temporal.ConfigDatabaseDTO;
import services.PropertiesServiceImpl;

public class ConfiguradorBaseDeDatosController {

	private static final String file = "conf/config_db.properties";
	
	public ConfiguradorBaseDeDatosController() {}
		
	public void save(ConfigDatabaseDTO dto) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user", dto.getUser());
		map.put("password", dto.getPassword());
		map.put("port", dto.getPort());
		map.put("ip", dto.getIp());
		PropertiesServiceImpl service = new PropertiesServiceImpl(file);
		try {
			service.updateValues(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ConfigDatabaseDTO read() {
		ConfigDatabaseDTO dto = new ConfigDatabaseDTO();
		PropertiesServiceImpl service = new PropertiesServiceImpl(file);
		try {
			Map<String, String> map = service.read();
			dto.setUser(map.get("user"));
			dto.setPassword(map.get("password"));
			dto.setIp(map.get("ip"));
			dto.setPort(map.get("port"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}
}
