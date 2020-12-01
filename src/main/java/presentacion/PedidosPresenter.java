package presentacion;

import java.awt.event.ActionEvent;
import java.util.Date;

import business_logic.PedidosController;
import dto.PedidoVehiculoDTO;
import dto.validators.StringValidator;
import presentacion.views.gerente.PedidosPanelView;
import repositories.PedidoVehiculoDao;

public class PedidosPresenter {

	private static final String CONFIRMATION = "¿Está seguro que desea cancelar el pedido?";
	private static final String MENSAGE_NUEVO_PEDIDO = "Se registró un nuevo pedido.";

	private PedidosPanelView view;
	private PedidosController controller;

	public PedidosPresenter(PedidosPanelView view, PedidosController controller) {
		this.view = view;
		this.controller = controller;
		
		view.setActionBuscar((a) -> onBuscar(a));
		view.setActionRegistrarPedido((a) -> onRegistrar(a));
		view.setActionCancelarPedido((a) -> onCancelar(a));
	}

	private void onBuscar(ActionEvent a) {
		String dniBuscado = view.getDniBusqueda();
		if (dniBuscado.trim().isEmpty()) {
			view.clear();
			view.setData(controller.readAllPedidos());
		} else {
			boolean esDniConFormatoCorrecto = new StringValidator(dniBuscado).number("").validate().isEmpty();
			if (esDniConFormatoCorrecto) {
				Integer dniCliente = Integer.parseInt(dniBuscado);
				view.clear();
				view.setData(controller.readAllByDniCliente(dniCliente));
			}
		}
	}

	private Object onCancelar(ActionEvent a) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object onRegistrar(ActionEvent a) {
		// TODO Auto-generated method stub
		return null;
	}

}
