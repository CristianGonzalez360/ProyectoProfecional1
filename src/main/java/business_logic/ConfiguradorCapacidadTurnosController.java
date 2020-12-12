package business_logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import services.PropertiesServiceImpl;

public class ConfiguradorCapacidadTurnosController {

	private final String file = "conf/turnos.properties";

	public ConfiguradorCapacidadTurnosController() {
	}

	public void establecerCapacidadDeTurnos(String capacidad) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cantidad", capacidad);
		PropertiesServiceImpl service = new PropertiesServiceImpl(file);
		try {
			service.updateValues(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Integer readCapacidadDeTurnos() {
		PropertiesServiceImpl propertyService = new PropertiesServiceImpl(file);
		Integer capacidadDeTurnos = 0;
		try {
			capacidadDeTurnos = Integer.valueOf(propertyService.readProperty("cantidad"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return capacidadDeTurnos;
	}

}
