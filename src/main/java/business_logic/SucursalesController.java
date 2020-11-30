package business_logic;

import java.util.List;

import dto.SucursalDTO;
import repositories.SucursalesDao;

public class SucursalesController {

	private SucursalesDao sucursalDao;
	
	public SucursalesController(SucursalesDao sucDao) {
		assert sucDao != null;
		sucursalDao = sucDao;
	}
	
	public SucursalDTO readByName(String name) {
		return sucursalDao.readByName(name);		
	}
	
	public List<SucursalDTO> readAll() {
		return sucursalDao.readAll();
	}
	
	public List<String> readFinancierasByPais(String pais) {
		return sucursalDao.readFinancierasByPais(pais);
	}
}
