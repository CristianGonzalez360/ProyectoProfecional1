package presentacion;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import business_logic.TurnosController;
import dto.TurnoDTO;
import presentacion.views.SupervisorControlView;
import presentacion.views.TurnoFormView;
import presentacion.views.utils.ConfirmationDialog;
import presentacion.views.utils.ErrorDialog;

public class TurnosPresenter {

	private SupervisorControlView supervisorView = SupervisorControlView.getInstance();

	private TurnoFormView turnoForm = TurnoFormView.getInstance();

	private TurnosController controller;

	public TurnosPresenter(TurnosController controller) {
		this.controller = controller;
		supervisorView.setActionBuscar((a) -> onBuscarTurnos(a));
		supervisorView.setActionRegistrarTurno((a) -> onDisplayTurnosFormView(a));
		supervisorView.setActionCancelarTurno((a) -> onCancelarTurno(a));

		turnoForm.setActionSave(a -> onSave(a));
		turnoForm.setActionCancel(a -> onCancel(a));
	}

	private void onCancelarTurno(ActionEvent e) {
		new ConfirmationDialog("¿Está seguro que desea cancelar el turno?").open();
	}

	private void onDisplayTurnosFormView(ActionEvent e) {
		TurnoFormView.getInstance().clearData();
		TurnoFormView.getInstance().display();
	}

	private void onBuscarTurnos(ActionEvent e) {
		String dni = supervisorView.getDniClienteBusquedaTurno();
		if (dni.trim().isEmpty()) {
			List<TurnoDTO> turnos = controller.readAll();
			supervisorView.clearTurnos();
			supervisorView.setTurnos(turnos);
		} else {
			TurnoDTO turno = controller.readByDniCliente(dni);
			if (turno != null) {
				supervisorView.clearTurnos();
				supervisorView.setTurnos(Arrays.asList(new TurnoDTO[] { turno }));
			}
		}
	}

	private void onSave(ActionEvent a) {
		TurnoDTO turno = turnoForm.getData();

		if (datosValidos(turno)) {
			this.controller.save(turno);
			turnoForm.dispose();
		}

	}

	private boolean datosValidos(TurnoDTO turno) {
		return validarDatosPersonales(turno) && validarFechaTurno(turno);
	}

	private boolean validarDatosPersonales(TurnoDTO turno) {
		if (!turno.validate().isEmpty()) {
			new ErrorDialog().showMessages(turno.validate());
			return false;
		}
		return true;

	}

	private boolean validarFechaTurno(TurnoDTO turno) {
		if (turno.getFechaProgramada() == null) {
			new ErrorDialog().showMessages("Debe indicar una fecha para el turno");
			return false;
		}

		if (turno.getFechaProgramada() != null && turno.getFechaProgramada().before(new Date())) {
			new ErrorDialog().showMessages("La fecha del turno no puede ser menor al dia de hoy.");
			return false;
		}
		return true;
	}

	private void onCancel(ActionEvent a) {
		turnoForm.dispose();
	}

}