
package presentacion;

import java.awt.event.ActionEvent;

import business_logic.VentasVehiculosController;
import presentacion.views.gerente.HistorialVentasGerenteView;

public class HistorialVentasGerentePresenter {

	private HistorialVentasGerenteView view;
	private VentasVehiculosController ventasVehiculosController;

	public HistorialVentasGerentePresenter(VentasVehiculosController ventasVehiculosController) {
		this.ventasVehiculosController = ventasVehiculosController;
		this.view = HistorialVentasGerenteView.getInstance();

		this.view.setActionOnBuscar(a -> onCargar(a));
	}

	private void onCargar(ActionEvent a) {
		this.view.clear();
		this.view.cargarTabla(this.ventasVehiculosController.readFechas(view.getVentaDesde(), view.getVentaHasta()));

	}
}