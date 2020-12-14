package business_logic;

import java.util.Date;
import java.util.List;

import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import repositories.DaosFactory;

public class ReportesController {

	private DaosFactory daos;

	public ReportesController(DaosFactory daos) {
		super();
		this.daos = daos;
	}
	
	public List<VentaVehiculoDTO> readAll() {
		return daos.makeVentaVehiculoDao().readAll();
	}
	
	public List<VentaVehiculoDTO> readAll(Date desde, Date hasta) {
		return daos.makeVentaVehiculoDao().readFechas(desde, hasta);
	}
	
	public List<VehiculoDTO> readAllVehiculos(){
		return daos.makeVehiculoDao().readAll();
	}
	
}
