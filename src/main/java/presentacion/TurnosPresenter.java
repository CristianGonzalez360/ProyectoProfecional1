package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.TurnosController;
import dto.temporal.AltaDeTurnoDTO;
import dto.validators.StringValidator;
import presentacion.views.supervisor.TurnoFormView;
import presentacion.views.supervisor.TurnosPanelView;
import presentacion.views.utils.ConfirmationDialog;
import presentacion.views.utils.MessageDialog;

public class TurnosPresenter {

	private static final String CONFIRMATION = "¿Está seguro que desea cancelar el turno?";

	private static final String MENSAGE_NUEVO_TURNO = "Se registró Turno.";
	
	private TurnosPanelView view;

	private TurnoFormView turnoForm = TurnoFormView.getInstance();

	private TurnosController controller;

	public TurnosPresenter(TurnosPanelView view, TurnosController controller) {
		this.view = view;
		this.controller = controller;
		view.setActionBuscar((a) -> onBuscar(a));
		view.setActionRegistrarTurno((a) -> onDisplayTurnosFormView(a));
		view.setActionCancelarTurno((a) -> onCancelar(a));
		turnoForm.setActionSave(a -> onSave(a));
		turnoForm.setActionCancel(a ->{turnoForm.dispose();});
	}

	private void onDisplayTurnosFormView(ActionEvent e) {
		TurnoFormView.getInstance().clearData();
		TurnoFormView.getInstance().display();
	}
	
	private void onBuscar(ActionEvent e) {
		String dniBuscado = view.getDniBusqueda();
		if (dniBuscado.trim().isEmpty()) {
			view.clear();
			view.setData(controller.readAll());
		} else {
			boolean esDniConFormatoCorrecto = new StringValidator(dniBuscado).number("").validate().isEmpty();
			if (esDniConFormatoCorrecto) {
				Integer dniCliente = Integer.parseInt(dniBuscado);
				view.clear();
				view.setData(controller.readByDniCliente(dniCliente));
			}	
		}
	}
	
	private void onCancelar(ActionEvent e) {
		if(view.getIdSelectedTurno() == null)
			return;
		
		if (new ConfirmationDialog(CONFIRMATION).open() == 0) {
			Integer idTurno = view.getIdSelectedTurno();
			if (idTurno != null) {
				controller.cancelById(idTurno);
				view.clear();
				new MessageDialog().showMessages("Turno con Nro.: " + idTurno + " fué cancelado.");
			}
		}
	}

	private void onSave(ActionEvent a) {
		AltaDeTurnoDTO turno = turnoForm.getData();
		List<String> errors = turno.validate();
		if (errors.isEmpty()) {
			controller.save(turno);
			new MessageDialog().showMessages(MENSAGE_NUEVO_TURNO);
			turnoForm.dispose();
		} else {
			new MessageDialog().showMessages(errors);
		}
	}
}