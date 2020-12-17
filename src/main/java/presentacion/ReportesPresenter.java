
package presentacion;

import java.util.Date;
import java.util.List;

import business_logic.FacturasController;
import business_logic.ReportesController;
import presentacion.views.gerente.PanelReportes;
import presentacion.views.utils.IngresosReport;
import presentacion.views.utils.ReporteViewImpl;
import presentacion.views.utils.VentasReport;

public class ReportesPresenter {

	private PanelReportes view;

	private ReportesController reportesController;

	public ReportesPresenter(FacturasController facturasController, ReportesController reportesController) {
		this.view = PanelReportes.getInstance();
		this.reportesController = reportesController;

		this.view.setActionReporteIngresos((a) -> generarReporteIngresos());
		this.view.setActionReporteEgresos((a) -> generarReporteEgresos());
		this.view.setActionReporteVentas((a) -> generarReporteAutosVendidos());
	}

	private void generarReporteAutosVendidos() {
		Date fechaDesde = view.getFechaVentasDesde();
		Date fechaHasta = view.getFechaVentasHasta();

		if (fechaDesde == null || fechaHasta == null)
			return;

		List<VentasReport> autosVendidos = reportesController.readAutosVendidos(fechaDesde, fechaHasta);
		ReporteViewImpl reporte = new ReporteViewImpl();
		reporte.setDataVentas(autosVendidos);
		reporte.open();
	}

	private void generarReporteIngresos() {
		Date fechaDesde = view.getFechaIngresoDesde();
		Date fechaHasta = view.getFechaIngresoHasta();

		if (fechaDesde == null || fechaHasta == null)
			return;

		ReporteViewImpl reporte = new ReporteViewImpl();
		List<IngresosReport> ingresos = reportesController.readIngresos(fechaDesde, fechaHasta);
		reporte.setDataIngresos(ingresos);
		reporte.open();
	}

	// TODO en este metodo en lugar de recolectarse en la lista de ingresosReport
	// facturas y ventas de vehiculos se
	// se buscaran compraVehiculo y compraRepuesto

	private void generarReporteEgresos() {
		Date fechaDesde = view.getFechaEgresoDesde();
		Date fechaHasta = view.getFechaEgresoHasta();

		if (fechaDesde == null || fechaHasta == null)
			return;

		ReporteViewImpl reporte = new ReporteViewImpl();
		List<IngresosReport> ingresos = reportesController.readEgresos(fechaDesde, fechaHasta);
		reporte.setDataEgresos(ingresos);
		reporte.open();
	}

}
