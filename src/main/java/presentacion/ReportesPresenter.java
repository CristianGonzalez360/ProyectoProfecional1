
package presentacion;

import java.awt.event.ActionEvent;

import business_logic.FacturasController;
import business_logic.ReportesController;
import presentacion.views.gerente.PanelReportes;
import presentacion.views.gerente.ReporteAutosVendidosFormView;
import presentacion.views.gerente.ReporteEgresoDiarioFormView;
import presentacion.views.gerente.ReporteIngresoDiarioInputFormView;
import presentacion.views.gerente.ReporteIngresoMensualFormView;
import presentacion.views.gerente.ReporteRepuestos;

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
		
		this.view.setActionDisplayIngresosDiarios((a) -> onDisplayIngresosDiarios(a));
		this.autosVendidosView.setActionGenerarReporte((a) -> generarReporteIngresosDiarios());
		
		this.view.setActionDisplayEgresosDiarios((a) -> onDisplayEgresosDiarios(a));
		this.autosVendidosView.setActionGenerarReporte((a) -> generarReporteEgresosDiarios());
		
		this.view.setActionDisplayIngresoMensual((a) -> onDisplayEgresosMensuales(a));
		this.autosVendidosView.setActionGenerarReporte((a) -> generarReporteEgresosMensuales());
		
		this.view.setActionDisplayIngresoMensual((a) -> onDisplayEgresosMensuales(a));
		this.autosVendidosView.setActionGenerarReporte((a) -> generarReporteEgresosMensuales());
		
		this.view.setActionDisplayreportes((a) -> onDisplayRepuestos(a));
		this.autosVendidosView.setActionGenerarReporte((a) -> generarReporteRepuestos());
		
	}


	private void onDisplayIngresosDiarios(ActionEvent e) {
		reporteIngresoDiarioInputFormView.getInstance().clearData();
		reporteIngresoDiarioInputFormView.getInstance().display();
	}
	
	private void onDisplayEgresosDiarios(ActionEvent e) {
		reporteEgresoDiarioFormView.getInstance().clearData();
		reporteEgresoDiarioFormView.getInstance().display();
	}
	
	private void onDisplayEgresosMensuales(ActionEvent e) {
		reporteEgresoDiarioFormView.getInstance().clearData();
		reporteEgresoDiarioFormView.getInstance().display();
	}
	
	private void onDisplayRepuestos(ActionEvent e) {
		reporteEgresoDiarioFormView.getInstance().clearData();
		reporteEgresoDiarioFormView.getInstance().display();
	}
	
	private void onDisplayReporteAutosVendidos(ActionEvent e) {
		ReporteAutosVendidosFormView.getInstance().clearData();
		ReporteAutosVendidosFormView.getInstance().display();
	}

	private void generarReporteAutosVendidos() {
		ReporteViewImpl reporte = new ReporteViewImpl();
		reporte.setData(reportesController.readAllVehiculos());
		reporte.open();
	}
	
	private void generarReporteIngresosDiarios() {
		//reportesController
		System.out.println("Generar Reporte");
	}

	private void generarReporteEgresosDiarios() {
		//reportesController
		System.out.println("Generar Reporte");
	}
	
	private void generarReporteEgresosMensuales() {
		//reportesController
		System.out.println("Generar Reporte");
	}
	
	private void generarReporteRepuestos() {
		//reportesController
		System.out.println("Generar Reporte");
	}

}