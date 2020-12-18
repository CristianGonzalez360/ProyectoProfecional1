package business_logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.CompraRepuestoDTO;
import dto.CompraVehiculoDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.taller.FacturaDTO;
import presentacion.views.utils.IngresosReport;
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
	
	public List<IngresosReport> readIngresos(Date desde ,Date hasta) {
		List<IngresosReport> ingresosReport = new ArrayList<>();
		Double sumaParcial = 0.00;
		for (FacturaDTO facturaPaga : daos.makeFacturasDao().readByDates(desde,hasta)) {
			IngresosReport ingreso = new IngresosReport();
			ingreso.setFechaReporte(new Date());
			ingreso.setDescripcion(facturaPaga.getDescripcion());
			ingreso.setFechaDePago(facturaPaga.getFechaDeCierrePorPago());
			ingreso.setMontoTotal(facturaPaga.getTotal());
			ingreso.setId(facturaPaga.getIdFactura());
			ingresosReport.add(ingreso);
		}
		
		for (VentaVehiculoDTO ventas : daos.makeVentaVehiculoDao().readFechas(desde, hasta)){			
			IngresosReport ingreso = new IngresosReport();
			ingreso.setFechaReporte(new Date());
			ingreso.setDescripcion("Venta Vehiculo");
			ingreso.setFechaDePago(ventas.getFechaVentaVN());
			ingreso.setMontoTotal(ventas.getPrecioVenta());
			ingreso.setId(ventas.getIdVentaVehiculo());
			ingresosReport.add(ingreso);
		}
		
		for (IngresosReport ingresos : ingresosReport) {
			sumaParcial += ingresos.getMontoTotal();
		}
		for (IngresosReport ingresos : ingresosReport) {
			ingresos.setTotal(sumaParcial);
		}
			
		return ingresosReport;
	}
	
	public List<IngresosReport> readEgresos(Date desde ,Date hasta) {
		List<IngresosReport> ingresosReport = new ArrayList<>();
		Double sumaParcial = 0.00;
		for (CompraVehiculoDTO comprasVehiculo : daos.makeCompraVehiculosDao().readFechas(desde, hasta)){
			IngresosReport ingreso = new IngresosReport();
			ingreso.setFechaReporte(new Date());
			ingreso.setDescripcion("Compra de vehiculo usados");
			ingreso.setFechaDePago(comprasVehiculo.getFechaCompra());
			ingreso.setMontoTotal(comprasVehiculo.getPrecioCompra());
			ingreso.setId(comprasVehiculo.getIdCompraVehiculo());
			
			ingresosReport.add(ingreso);
		}
		
		for (CompraRepuestoDTO compraRepuesto : daos.makeCompraRepuestosDao().readFechas(desde, hasta)){			
			IngresosReport ingreso = new IngresosReport();
			ingreso.setFechaReporte(new Date());
			ingreso.setDescripcion("Compra de Repuestos");
			ingreso.setFechaDePago(compraRepuesto.getFechaCompra());
			ingreso.setMontoTotal(compraRepuesto.getPrecioCompra()*compraRepuesto.getCantidad());
			ingreso.setId(compraRepuesto.getCodigoRepuesto());
			ingresosReport.add(ingreso);
		}
		
			for (IngresosReport ingresos : ingresosReport) {
				sumaParcial += ingresos.getMontoTotal();
			}
			for (IngresosReport ingresos : ingresosReport) {
				ingresos.setTotal(sumaParcial);
			}
			
		return ingresosReport;
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
