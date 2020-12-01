package business_logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dto.SucursalDTO;
import services.PropertiesServiceImpl;

public class ConfiguradorTerminalController {
	
	private final String archivoConfiguracion = "conf/terminal.properties";
	
	public ConfiguradorTerminalController() {		
	}
	
	public void establecerSucursalPredetermidada(SucursalDTO dto) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("idSucursal", dto.getIdSucursal().toString());
		map.put("pais", dto.getPais());
		map.put("calle", dto.getCalle());
		map.put("altura", dto.getAltura().toString());
		PropertiesServiceImpl service = new PropertiesServiceImpl(archivoConfiguracion);
		try {
			service.updateValues(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SucursalDTO readSucursalPredeterminada() {
		SucursalDTO suc = new SucursalDTO();
		PropertiesServiceImpl service = new PropertiesServiceImpl(archivoConfiguracion);
		try {
			Map<String, String> map = service.read();
			suc.setIdSucursal(Integer.parseInt(map.get("idSucursal")));
			suc.setPais(map.get("pais"));
			suc.setCalle(map.get("calle"));
			suc.setAltura(Integer.parseInt(map.get("altura")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return suc;
	}
}
