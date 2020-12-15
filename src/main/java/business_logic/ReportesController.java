package business_logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.temporal.VentaDTO;
import presentacion.views.utils.VentasReport;
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

	public List<VehiculoDTO> readAllVehiculos() {
		return daos.makeVehiculoDao().readAll();
	}

	public List<VentasReport> readAutosVendidos(Date desde, Date hasta) {
		List<VentasReport> autosVendidos = new ArrayList<>();

		for (VentaVehiculoDTO venta : readVentas(desde, hasta)) {
			VentasReport vehiculo = readInformacionDeVenta(venta);
			autosVendidos.add(vehiculo);
		}
		return autosVendidos;
	}

	public List<VentaVehiculoDTO> readVentas(Date desde, Date hasta) {
		return daos.makeVentaVehiculoDao().readAllOrderByFabricante(desde, hasta);
	}

	private VentasReport readInformacionDeVenta(VentaVehiculoDTO venta) {
		VehiculoDTO vehiculo = readVehiculoByID(venta.getIdVehiculo());
		return new VentasReport(venta.getFechaVentaVN(), vehiculo.getMarca(), vehiculo.getFamilia(),
				vehiculo.getLinea(), vehiculo.getColor());
	}

	private VehiculoDTO readVehiculoByID(Integer idVehiculo) {
		return daos.makeVehiculoDao().readByID(idVehiculo);
	}

}
