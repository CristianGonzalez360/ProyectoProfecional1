package presentacion;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import business_logic.TurnosController;
import dto.TurnoDTO;
import presentacion.views.SupervisorControlView;

public class TurnosPresenter {

	private SupervisorControlView supervisorView = SupervisorControlView.getInstance();
	
	private TurnosController controller;
	
	public TurnosPresenter(TurnosController controller) {
		this.controller = controller;
		supervisorView.setActionBuscar((a)->onBuscarTurnos(a));
	}
	
	private void onBuscarTurnos(ActionEvent e) {
		String dni = supervisorView.getDniClienteBusquedaTurno();
		if(dni.trim().isEmpty()) {
			List<TurnoDTO> turnos = controller.readAll();
			supervisorView.clearTurnos();
			supervisorView.setTurnos(turnos);
		} else {
			TurnoDTO turno = controller.readByDniCliente(dni);
			if(turno != null) {
				supervisorView.clearTurnos();
				supervisorView.setTurnos(Arrays.asList(new TurnoDTO [] {turno}));
			}
		}
	}
}