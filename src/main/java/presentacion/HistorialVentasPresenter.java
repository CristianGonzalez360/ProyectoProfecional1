
package presentacion;

import java.awt.event.ActionEvent;
import java.util.Date;

import business_logic.VentasVehiculosController;
import presentacion.views.vendedor.HistorialVentasView;
import services.SessionServiceImpl;

public class HistorialVentasPresenter {

	private HistorialVentasView view;
	private VentasVehiculosController ventasVehiculosController;

	public HistorialVentasPresenter(VentasVehiculosController ventasVehiculosController) {

		this.ventasVehiculosController = ventasVehiculosController;
		this.view = HistorialVentasView.getInstance();

		this.view.setActionOnBuscar(a -> onCargar(a));
	}

	private void onCargar(ActionEvent a) {
		this.view.clear();

		Integer userID = SessionServiceImpl.getInstance().getActiveSession().getIdUsuario();
		Date ventaDesde = view.getVentaDesde();
		Date ventaHasta = view.getVentaHasta();

		if (ventaDesde == null || ventaHasta == null)
			return;

		this.view.cargarTabla(ventasVehiculosController.readVentas(userID, ventaDesde, ventaHasta));
	}

}