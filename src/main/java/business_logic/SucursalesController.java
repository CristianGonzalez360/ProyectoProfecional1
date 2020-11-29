package business_logic;

import java.util.List;

import dto.SucursalDTO;
import repositories.SucursalDao;

public class SucursalesController {

	private SucursalDao sucursalDao;
	
	public SucursalesController(SucursalDao sucDao) {
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
