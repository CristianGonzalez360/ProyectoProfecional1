package presentacion;

import java.awt.event.ActionEvent;

import business_logic.EntregaDeVehiculoController;
import dto.validators.StringValidator;
import presentacion.views.supervisor.EntregaVehiculosPanelView;
import presentacion.views.utils.ConfirmationDialog;
import presentacion.views.utils.MessageDialog;

public class EntregaDeVehiculoPresenter {
	
	private static final String CONFIRMATION = "¿Está seguro que desea registrar la entrega?";

	private EntregaVehiculosPanelView view;
	private EntregaDeVehiculoController controller;

	public EntregaDeVehiculoPresenter(EntregaVehiculosPanelView view) {
		this.view = view;

		this.view.setActionBuscarEntregas((a) -> onBuscarEntregas(a));
		this.view.setRegistrarEntrega((a) -> onRegistrarEntrega(a));
	}

	private void onBuscarEntregas(ActionEvent event) {
		String dniBuscado = view.getDniBusqueda();
		
		if(dniBuscado.trim().isEmpty()) {
			view.clear();
			view.setData(controller.readAll());
		} else {
			boolean esDniConFormatoCorrecto = new StringValidator(dniBuscado).number("").validate().isEmpty();
			if(esDniConFormatoCorrecto) {
				Integer dniCliente = Integer.parseInt(dniBuscado);
				view.clear();
				view.setData(controller.readByDniCliente(dniCliente));
			}
		}
	}

	private void onRegistrarEntrega(ActionEvent event) {
		Integer idEntrega = view.getIdSelectedEntrega();
		if(idEntrega != null) {
			if(new ConfirmationDialog(CONFIRMATION).open() == 0) {
				controller.registrarEntregaById(idEntrega);
				view.clear();
				new MessageDialog().showMessages("Entrega Registrada");	
			}
		}
	}

}
