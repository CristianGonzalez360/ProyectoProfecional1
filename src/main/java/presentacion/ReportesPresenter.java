
package presentacion;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

import business_logic.FacturasController;
import business_logic.ReportesController;
import dto.taller.FacturaDTO;
import presentacion.views.gerente.PanelReportes;
import presentacion.views.gerente.ReporteAutosVendidosFormView;
import presentacion.views.gerente.ReporteEgresoDiarioFormView;
import presentacion.views.gerente.ReporteIngresoDiarioInputFormView;
import presentacion.views.utils.ReporteViewImpl;
import presentacion.views.utils.VentasReport;

public class ReportesPresenter {

	private PanelReportes view;
	private ReporteAutosVendidosFormView reporteAutosVendidosFormView;
	private ReporteIngresoDiarioInputFormView reporteIngresoDiarioInputFormView;
	private ReporteEgresoDiarioFormView reporteEgresoDiarioFormView;

	private FacturasController facturasController;
	private ReportesController reportesController;

	public ReportesPresenter(FacturasController facturasController, ReportesController reportesController) {
		this.view = PanelReportes.getInstance();
		this.reporteAutosVendidosFormView = ReporteAutosVendidosFormView.getInstance();
		this.reporteIngresoDiarioInputFormView = ReporteIngresoDiarioInputFormView.getInstance();
		this.reporteEgresoDiarioFormView = ReporteEgresoDiarioFormView.getInstance();
		this.facturasController = facturasController;
		this.reportesController = reportesController;
		
		this.view.setActionDisplayReporteAutosVendidos((a) -> onDisplayReporteAutosVendidos(a));
		this.reporteAutosVendidosFormView.setActionGenerarReporte((a) -> generarReporteAutosVendidos());

		this.view.setActionDisplayIngresosDiarios((a) -> onDisplayIngresosDiarios(a));
		this.reporteIngresoDiarioInputFormView.setActionGenerarReporte((a) -> generarReporteIngresos());

//		this.view.setActionDisplayEgresosDiarios((a) -> onDisplayEgresosDiarios(a));
//		this.autosVendidosView.setActionGenerarReporte((a) -> generarReporteEgresos());
	}

	private void onDisplayIngresosDiarios(ActionEvent e) {
		ReporteIngresoDiarioInputFormView.getInstance().clearData();
		ReporteIngresoDiarioInputFormView.getInstance().display();
	}

	private void onDisplayEgresosDiarios(ActionEvent e) {
		ReporteEgresoDiarioFormView.getInstance().clearData();
		ReporteEgresoDiarioFormView.getInstance().display();
	}

	private void onDisplayReporteAutosVendidos(ActionEvent e) {
		ReporteAutosVendidosFormView.getInstance().clearData();
		ReporteAutosVendidosFormView.getInstance().display();
	}

	private void generarReporteAutosVendidos() {
		Date fechaDesde = reporteAutosVendidosFormView.getFechaDesde();
		Date fechaHasta = reporteAutosVendidosFormView.getFechaHasta();

		if (fechaDesde == null || fechaHasta == null)
			return;

		List<VentasReport> autosVendidos = reportesController.readAutosVendidos(fechaDesde, fechaHasta);
		ReporteViewImpl reporte = new ReporteViewImpl();
		reporte.setDataVentas(autosVendidos);
		reporte.open();
	}

	private void generarReporteIngresos() {
		Date fechaDesde = reporteIngresoDiarioInputFormView.getFechaDesde();
		Date fechaHasta = reporteIngresoDiarioInputFormView.getFechaHasta();
		
		if (fechaDesde == null || fechaHasta == null)
			return;

		ReporteViewImpl reporte = new ReporteViewImpl();
		List<FacturaDTO> ingresos = reportesController.readFacturasPagas(fechaDesde, fechaHasta);
		reporte.setDataIngresos(ingresos);
		reporte.open();
	}

	private void generarReporteEgresos() {
		// reportesController
		System.out.println("Generar Reporte");
	}

}