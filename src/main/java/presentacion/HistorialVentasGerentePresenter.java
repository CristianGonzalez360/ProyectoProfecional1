
package presentacion;

import java.awt.event.ActionEvent;
import java.util.Date;

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
		Date desde = view.getVentaDesde();
		Date hasta = view.getVentaHasta();

		if (desde == null || hasta == null)
			return;

		this.view.clear();
		this.view.cargarTabla(ventasVehiculosController.readVentas(desde, hasta));
	}

}