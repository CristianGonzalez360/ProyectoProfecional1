package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.PedidosController;
import business_logic.VehiculosController;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import dto.validators.StringValidator;
import presentacion.views.gerente.PedidosPanelView;
import presentacion.views.gerente.RegistroPedidoView;

public class PedidosPresenter {

	private static final String CONFIRMATION = "¿Está seguro que desea cancelar el turno?";
	private static final String MENSAGE_NUEVO_PEDIDO = "Se registró Turno.";

	private PedidosPanelView view;
	private PedidosController controller;
	private RegistroPedidoView regPedidoView;
	private VehiculosController vehiculosController;
	

	public PedidosPresenter(PedidosPanelView view, PedidosController controller) {
		this.view = view;
		this.controller = controller;
		regPedidoView = RegistroPedidoView.getInstance();

		view.setActionBuscar((a) -> onBuscar(a));
		view.setActionRegistrarPedido((a) -> onRegistrar(a));
		view.setActionCancelarPedido((a) -> onCancelar(a));
	}

	private Object onBuscar(ActionEvent a) {
//		String dniBuscado = view.getDniBusqueda();
//		if (dniBuscado.trim().isEmpty()) {
//			view.clear();
//			view.setData(controller.readAll());
//		} else {
//			boolean esDniConFormatoCorrecto = new StringValidator(dniBuscado).number("").validate().isEmpty();
//			if (esDniConFormatoCorrecto) {
//				Integer dniCliente = Integer.parseInt(dniBuscado);
//				view.clear();
//				view.setData(controller.readByDniCliente(dniCliente));
//			}	
//		}
		return null;
	}

	private Object onCancelar(ActionEvent a) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object onRegistrar(ActionEvent a) {
		regPedidoView.display();
		return null;
	}

	
	private void onBuscarVehiculo(ActionEvent a) {
		ConsultaVehiculoParaVentaDTO consulta = regPedidoView.getData();
		regPedidoView.clearData();
		if(consulta.validate().isEmpty()) {
			List<OutputConsultaVehiculoEnVentaDTO> vehiculos = vehiculosController.readByCriteria(consulta);
			regPedidoView.setData(vehiculos);
		}
	}
}
