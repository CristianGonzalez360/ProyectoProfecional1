package business_logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dto.SucursalDTO;
import services.PropertiesServiceImpl;

public class ConfiguradorTerminalController implements SucursalPredeterminadaReader {
	
	private final String file = "conf/terminal.properties";
	
	public ConfiguradorTerminalController() {		
	}
	
	public void establecerSucursalPredetermidada(SucursalDTO dto) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("idSucursal", dto.getIdSucursal().toString());
		map.put("pais", dto.getPais());
		map.put("calle", dto.getCalle());
		map.put("localidad", dto.getLocalidad());
		map.put("altura", dto.getAltura().toString());
		PropertiesServiceImpl service = new PropertiesServiceImpl(file);
		try {
			service.updateValues(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public SucursalDTO readSucursalPredeterminada() {
		SucursalDTO suc = new SucursalDTO();
		PropertiesServiceImpl service = new PropertiesServiceImpl(file);
		try {
			Map<String, String> map = service.read();
			suc.setIdSucursal(Integer.parseInt(map.get("idSucursal")));
			suc.setPais(map.get("pais"));
			suc.setCalle(map.get("calle"));
			suc.setLocalidad(map.get("localidad"));
			suc.setAltura(Integer.parseInt(map.get("altura")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return suc;
	}
}

