package presentacion;

import java.awt.event.ActionEvent;

import business_logic.PedidosController;
import dto.validators.StringValidator;
import presentacion.views.gerente.PedidosPanelView;
import services.SessionServiceImpl;

public class PedidosPresenter {

	private static final String CONFIRMATION = "¿Está seguro que desea cancelar el pedido?";

	private PedidosPanelView view;
	private PedidosController controller;

	public PedidosPresenter(PedidosPanelView view, PedidosController controller) {
		this.view = view;
		this.controller = controller;

		view.setActionBuscar((a) -> onBuscar(a));
		view.setActionRegistrarIngreso((a) -> onRegistrar(a));
	}

	private void onBuscar(ActionEvent a) {
		String dniBuscado = view.getDniBusqueda();
		Integer idSucursal = SessionServiceImpl.getInstance().getActiveSession().getIdSucursal();

		if (dniBuscado.trim().isEmpty()) {
			view.clear();
			view.setData(controller.readAllPedidos(idSucursal));
		} else {
			boolean esDniConFormatoCorrecto = new StringValidator(dniBuscado).number("").validate().isEmpty();
			if (esDniConFormatoCorrecto) {
				Integer dniCliente = Integer.parseInt(dniBuscado);
				view.clear();
				view.setData(controller.readAllByDniCliente(dniCliente, idSucursal));
			}
		}
	}

	private void onRegistrar(ActionEvent a) {
		System.out.println("registra ingreso");
	}

}
