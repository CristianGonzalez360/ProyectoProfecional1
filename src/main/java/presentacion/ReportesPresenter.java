
package presentacion;

import java.awt.event.ActionEvent;

import business_logic.FacturasController;
import business_logic.ReportesController;
import presentacion.views.cajero.BitcoinFormView;
import presentacion.views.cajero.MercadoPagoFormView;
import presentacion.views.cajero.TarjetaCreditoFormView;
import presentacion.views.cajero.TarjetaDebitoFormView;
import presentacion.views.gerente.PanelReportes;
import presentacion.views.utils.ReporteViewImpl;
import presentacion.views.utils.TicketReport;

public class ReportesPresenter {

	private PanelReportes view;
	private FacturasController facturasController;
	private ReportesController reportesController;

	public ReportesPresenter(FacturasController facturasController, ReportesController reportesController) {
		this.view = PanelReportes.getInstance();

		this.facturasController = facturasController;
		this.reportesController = reportesController;

//		this.viewCredito = TarjetaCreditoFormView.getInstance();
//		this.viewDebito = TarjetaDebitoFormView.getInstance();
//		this.viewMercadoPago = MercadoPagoFormView.getInstance();
//		this.viewBitcoin = BitcoinFormView.getInstance();

//		this.view.setActionDisplayIngresosDiarios((a) -> onDisplayTarjetaCreditoFormView(a));
//		this.view.setActionDisplayEgresosDiarios((a) -> onDisplayTarjetaDebitoFormView(a));
//		this.view.setActionDisplayIngresoMensual((a) -> onDisplayMercadoPagoFormView(a));
//		this.view.setActionDisplayIngresoSemanal((a) -> onDisplayBitcoinsFormView(a));
//		this.view.setActionDisplayreportes((a) -> onDisplayBitcoinsFormView(a));

		this.view.setActionReporteAutosVendidos((a) -> generarReporteAutosVendidos());
	}

	private void onDisplayTarjetaCreditoFormView(ActionEvent e) {
		TarjetaCreditoFormView.getInstance().clearData();
		TarjetaCreditoFormView.getInstance().display();
	}

	private void onDisplayTarjetaDebitoFormView(ActionEvent e) {
		TarjetaDebitoFormView.getInstance().clearData();
		TarjetaDebitoFormView.getInstance().display();
	}

	private void onDisplayMercadoPagoFormView(ActionEvent e) {
		MercadoPagoFormView.getInstance().clearData();
		MercadoPagoFormView.getInstance().display();
	}

	private void onDisplayBitcoinsFormView(ActionEvent e) {
		BitcoinFormView.getInstance().clearData();
		BitcoinFormView.getInstance().display();
	}

	
	
	private void generarReporteAutosVendidos() {
		System.out.println("Reporte de autos vendidos");
	}

	/*
	 * -----------------------------------------------------------------------------
	 * ---------
	 */
	private void mostrarTicket(Integer idFactura, String medioPago) {
		ReporteViewImpl report = new ReporteViewImpl();
		TicketReport ticket = new TicketReport(facturasController.readByFactura(idFactura));
		ticket.setMedioDePago(medioPago);
		report.setData(ticket);
		report.open();
	}
}