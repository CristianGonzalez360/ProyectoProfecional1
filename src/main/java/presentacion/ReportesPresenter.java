
package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.FacturasController;
import business_logic.ReportesController;
import dto.VehiculoDTO;
import dto.taller.FacturaDTO;
import presentacion.views.gerente.PanelReportes;
import presentacion.views.gerente.ReporteAutosVendidosFormView;
import presentacion.views.gerente.ReporteEgresoDiarioFormView;
import presentacion.views.gerente.ReporteIngresoDiarioInputFormView;
import presentacion.views.gerente.ReporteIngresoMensualFormView;
import presentacion.views.gerente.ReporteRepuestos;
import presentacion.views.utils.ReporteViewImpl;

public class ReportesPresenter {

	private PanelReportes view;
	private FacturasController facturasController;
	private ReportesController reportesController;
	private ReporteAutosVendidosFormView autosVendidosView;
	private ReporteIngresoDiarioInputFormView reporteIngresoDiarioInputFormView;
	private ReporteEgresoDiarioFormView reporteEgresoDiarioFormView;
	private ReporteIngresoMensualFormView reporteIngresoMensualFormView;
	private ReporteRepuestos reporteRepuestos;

	public ReportesPresenter(FacturasController facturasController, ReportesController reportesController) {
		this.view = PanelReportes.getInstance();
		this.facturasController = facturasController;
		this.reportesController = reportesController;

		this.autosVendidosView = ReporteAutosVendidosFormView.getInstance();

		this.view.setActionDisplayReporteAutosVendidos((a) -> onDisplayReporteAutosVendidos(a));
		this.autosVendidosView.setActionGenerarReporte((a) -> generarReporteAutosVendidos());
		//TODO jere trabajando
//		this.view.setActionDisplayIngresosDiarios((a) -> onDisplayIngresosDiarios(a));
//		this.autosVendidosView.setActionGenerarReporte((a) -> generarReporteIngresos());

		this.view.setActionDisplayEgresosDiarios((a) -> onDisplayEgresosDiarios(a));
		this.autosVendidosView.setActionGenerarReporte((a) -> generarReporteEgresos());

	}

	private void onDisplayIngresosDiarios(ActionEvent e) {
		reporteIngresoDiarioInputFormView.getInstance().clearData();
		reporteIngresoDiarioInputFormView.getInstance().display();
	}

	private void onDisplayEgresosDiarios(ActionEvent e) {
		reporteEgresoDiarioFormView.getInstance().clearData();
		reporteEgresoDiarioFormView.getInstance().display();
	}

	private void onDisplayReporteAutosVendidos(ActionEvent e) {
		ReporteAutosVendidosFormView.getInstance().clearData();
		ReporteAutosVendidosFormView.getInstance().display();
	}

	private void generarReporteAutosVendidos() {
		ReporteViewImpl reporte = new ReporteViewImpl();
		List<VehiculoDTO> autosVendidos = reportesController.readAutosVendidos();
		reporte.setDataVentas(autosVendidos);
		reporte.open();
	}

	//TODO jere trabajando
//	private void generarReporteIngresos() {
//		reporteIngresoDiarioInputFormView.getSWW
//		ReporteViewImpl reporte = new ReporteViewImpl();
//		List<FacturaDTO> autosVendidos = reportesController.readFacturasPagas(desde, hasta);
//		reporte.setDataVentas(autosVendidos);
//		reporte.open();
//	}

	private void generarReporteEgresos() {
		// reportesController
		System.out.println("Generar Reporte");
	}
//
//	private void generarReporteEgresosMensuales() {
//		// reportesController
//		System.out.println("Generar Reporte");
//	}
//
//	private void generarReporteRepuestos() {
//		// reportesController
//		System.out.println("Generar Reporte");
//	}

}