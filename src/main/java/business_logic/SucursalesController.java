package business_logic;

import java.util.List;

import dto.MonedaDTO;
import dto.SucursalDTO;
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
		SucursalDTO sucursal = readByPais(pais).get(0);
		MonedaDTO moneda = null;
		if (sucursal != null) {
			Integer idMoneda = sucursal.getIdMoneda();
			moneda = daos.makeMonedasDao().readByID(idMoneda);
		}
		return moneda;
	}
}
