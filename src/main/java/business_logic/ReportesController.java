package business_logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.taller.FacturaDTO;
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

	public List<VehiculoDTO> readAutosVendidos(Date desde, Date hasta) {
		return null;

	}

	public List<VehiculoDTO> readAutosVendidos() {
		List<VehiculoDTO> autosVendidos = new ArrayList<>();
		for (VentaVehiculoDTO venta : daos.makeVentaVehiculoDao().readAllOrderByFabricante()) {
			VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(venta.getIdVehiculo());
			autosVendidos.add(vehiculo);
		}
		return autosVendidos;
	}
	
	public List<FacturaDTO> readFacturasPagas(Date desde ,Date hasta) {
		List<FacturaDTO> facturasPagas = new ArrayList<>();
		for (FacturaDTO facturaPaga : daos.makeFacturasDao().readByDates(desde,hasta)) {
			FacturaDTO factura = daos.makeFacturasDao().readByID(facturaPaga.getIdFactura());
			facturasPagas.add(factura);
		}
		return facturasPagas;
	}
}
