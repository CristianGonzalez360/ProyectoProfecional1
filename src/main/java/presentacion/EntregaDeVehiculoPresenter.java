package presentacion;

import java.awt.event.ActionEvent;

import business_logic.EntregaDeVehiculoController;
import dto.DatosPersonalesDTO;
import dto.validators.StringValidator;
import presentacion.views.supervisor.EntregaVehiculosPanelView;
import presentacion.views.utils.ConfirmationDialog;
import presentacion.views.utils.MessageDialog;
import repositories.jdbc.DaosFactoryImpl;

public class EntregaDeVehiculoPresenter {

	private static final String CONFIRMATION = "¿Está seguro que desea registrar la entrega?";

	private EntregaVehiculosPanelView view;
	private EntregaDeVehiculoController controller;

	public EntregaDeVehiculoPresenter(EntregaVehiculosPanelView view, EntregaDeVehiculoController controller) {
		this.view = view;
		this.controller = controller;

		this.view.setActionBuscarEntregas((a) -> onBuscarEntregas(a));
		this.view.setRegistrarEntrega((a) -> onRegistrarEntrega(a));
	}

	private void onBuscarEntregas(ActionEvent event) {
		String dniBuscado = view.getDniBusqueda();

		if (dniBuscado.trim().isEmpty()) {
			view.clear();
			view.setData(controller.readAllOrdenesRealizadas());
		} else {
			boolean esDniConFormatoCorrecto = new StringValidator(dniBuscado).number("").validate().isEmpty();
			if (esDniConFormatoCorrecto) {
				Integer dniCliente = Integer.parseInt(dniBuscado);
				view.clear();
				view.setData(controller.readByDniCliente(dniCliente));
			}
		}
	}

	private void onRegistrarEntrega(ActionEvent event) {
		Integer idFila = view.getIdSelectedRow(); // ID de la fila seleccionada

		if (hayFilaSeleccionada(idFila)) {
			if (new ConfirmationDialog(CONFIRMATION).open() == 0) {
				Integer idOt = view.getIdSelectedEntrega();// obtener id de la OT
				Integer dni = view.getDNISelectedEntrega();

				controller.registrarEntregaById(idOt);

				if (!enviarEmail(dni))
					new MessageDialog().showMessages("¡Entrega Registrada!.\n¡No se pudo enviar el mail de Calificación!");
				else
					new MessageDialog().showMessages("¡Entrega Registrada!.\n¡Se envió un mail de Calificación!");
				view.clear();
			}
		}
	}

	private boolean enviarEmail(Integer dni) {
		DatosPersonalesDTO datos = DaosFactoryImpl.getFactory().makeDatosPersonalesDao().readByDni(dni);
		String correoDestinatario = datos.getEmail();

		if (datos == null || correoDestinatario.isEmpty())
			return false;

		return this.controller.enviarCorreoDeSatisfaccion(correoDestinatario);
	}

	private boolean hayFilaSeleccionada(Integer idFila) {
		if (idFila == -1 || idFila == null)
			return false;
		return true;
	}

}
