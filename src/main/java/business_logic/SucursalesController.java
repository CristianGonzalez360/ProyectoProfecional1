package business_logic;

import java.util.List;

import dto.MonedaDTO;
import dto.SucursalDTO;
import dto.temporal.AltaSucursalDTO;
import repositories.DaosFactory;

public class SucursalesController {

	private DaosFactory daos;

	public SucursalesController(DaosFactory factory) {
		assert factory != null;
		daos = factory;
	}

	public List<SucursalDTO> readByPais(String pais) {
		return daos.makeSucursalesDao().readByPais(pais);
	}

	public List<String> readFinancierasByPais(String pais) {
		return daos.makeSucursalesDao().readFinancierasByPais(pais);
	}

	public MonedaDTO readMonedaByPais(String pais) {
		List<SucursalDTO> sucursal = readByPais(pais);
		MonedaDTO moneda = null;
		if(!sucursal.isEmpty()) {
			if (sucursal.get(0) != null) {
				Integer idMoneda = sucursal.get(0).getIdMoneda();
				if(idMoneda != null) moneda = daos.makeMonedasDao().readByID(idMoneda);
			}	
		}
		return moneda;
	}

	public void save(AltaSucursalDTO nuevaSucursal) {
		MonedaDTO moneda = readMonedaByPais(nuevaSucursal.getPais());
		SucursalDTO sucursal = new SucursalDTO();
		sucursal.setPais(nuevaSucursal.getPais());
		sucursal.setLocalidad(nuevaSucursal.getLocalidad());
		sucursal.setCalle(nuevaSucursal.getCalle());
		sucursal.setAltura(Integer.parseInt(nuevaSucursal.getAltura()));
		if(moneda != null) sucursal.setIdMoneda(moneda.getIdMoneda());
		daos.makeSucursalesDao().insert(sucursal);
	}
}
