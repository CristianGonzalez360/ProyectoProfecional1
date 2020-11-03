package presentacion;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import business_logic.TurnosController;
import dto.TurnoDTO;
import presentacion.views.SupervisorControlView;
import presentacion.views.TurnoFormView;
import presentacion.views.utils.ConfirmationDialog;

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
		this.controller.save(turno);
	}
}