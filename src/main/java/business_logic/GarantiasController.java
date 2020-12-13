package business_logic;

import dto.GarantiaVehiculoDTO;
import repositories.DaosFactory;

public class GarantiasController {

	private DaosFactory daos;

	public GarantiasController(DaosFactory daos) {
		super();
		this.daos = daos;
	}
	
	public GarantiaVehiculoDTO readByIdVehiculo(Integer id) {
		return daos.makeGarantiasVehiculosDao().readByIdVehiculo(id);
	}
}
