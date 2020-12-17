
package presentacion;

import java.util.Date;
import java.util.List;

import business_logic.FacturasController;
import business_logic.ReportesController;
import dto.taller.FacturaDTO;
import presentacion.views.gerente.PanelReportes;
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
		Date fechaDesde = view.getFechaVentasDesde().getDate();
		Date fechaHasta = view.getFechaVentasHasta().getDate();

		if (fechaDesde == null || fechaHasta == null)
			return;

		List<VentasReport> autosVendidos = reportesController.readAutosVendidos(fechaDesde, fechaHasta);
		ReporteViewImpl reporte = new ReporteViewImpl();
		reporte.setDataVentas(autosVendidos);
		reporte.open();
	}

	private void generarReporteIngresos() {
		Date fechaDesde = view.getFechaIngresoDesde().getDate();
		Date fechaHasta = view.getFechaIngresoHasta().getDate();

		if (fechaDesde == null || fechaHasta == null)
			return;

		ReporteViewImpl reporte = new ReporteViewImpl();
		List<FacturaDTO> ingresos = reportesController.readFacturasPagas(fechaDesde, fechaHasta);
		reporte.setDataIngresos(ingresos);
		reporte.open();
	}

	private void generarReporteEgresos() {
		System.out.println("Generar Reporte");
	}

}