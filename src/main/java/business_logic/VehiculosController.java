package business_logic;

import java.util.List;

import dto.CaracteristicaVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import repositories.DaosFactory;

public class VehiculosController {

	private DaosFactory daos;

	public VehiculosController(DaosFactory daos) {
		assert daos != null;
		this.daos = daos;
	}

	public List<VehiculoDTO> readVehiculosUsados() {
		return daos.makeVehiculoDao().readVehiculosUsados();
	}

	public VehiculoDTO readVehiculoById(Integer idVehiculo) {
		return daos.makeVehiculoDao().readByID(idVehiculo);
	}
	
	public CaracteristicaVehiculoDTO readCaracteristicaVehiculoByIdVehiculo(Integer idVehiculo) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(idVehiculo);
		return daos.makeCaracteristicasVehiculoDao().readByID(vehiculo.getIdCaracteristicas());
	}

	public FichaTecnicaVehiculoDTO readFichaTecnicaByIdVehiculo(Integer idVehiculo) {
		return daos.makeFichaTecnicaVehiculoDao().readByID(idVehiculo);
	}
}
