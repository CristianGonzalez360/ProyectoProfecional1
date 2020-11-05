package presentacion;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import business_logic.TurnosController;
import dto.AltaDeTurnoDTO;
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
		int cancelado = new ConfirmationDialog("¿Está seguro que desea cancelar el turno?").open();

		if (cancelado == 0) {
			Integer idTurnoSeleccionado = supervisorView.getIdSelectedTurno();
			
			TurnoDTO turnoSeleccionado = controller.readByIdTurno(idTurnoSeleccionado);
			
			if(turnoSeleccionado == null)
				return;
			
			turnoSeleccionado.setFechaCancelado(new Date());
			controller.update(turnoSeleccionado);
			supervisorView.clearTurnos();

			JOptionPane.showMessageDialog(supervisorView, String.format("Turno con Nro. Turno: %s fué cancelado.", turnoSeleccionado.getIdTurno()));
		}
		
		for(TurnoDTO turnos : controller.readAll()) {
			System.out.println(turnos.toString());
		}
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
		AltaDeTurnoDTO turno = turnoForm.getData();

		if (validarDatosPersonales(turno) && validarFechaTurno(turno)) {
			Integer dniCliente = Integer.parseInt(turno.getDniCliente());
			TurnoDTO nuevoTurno = new TurnoDTO(dniCliente, turno.getFechaAlta(), turno.getFechaCancelado(),
					turno.getFechaProgramada(), turno.getNombreCliente(), turno.getTelefonoCliente(),
					turno.getEmailCliente());

			this.controller.save(nuevoTurno);
			turnoForm.dispose();
		}
	}

	private boolean validarDatosPersonales(AltaDeTurnoDTO turno) {
		if (!turno.validate().isEmpty()) {
			new ErrorDialog().showMessages(turno.validate());
			return false;
		}
		return true;

	}

	private boolean validarFechaTurno(AltaDeTurnoDTO turno) {
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