package presentacion;

import java.awt.event.ActionEvent;
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

	private static final String MENSAGE_DNI_INCORRECTO = "No se encontraron turnos para DNI: %s";
	private static final String MENSAGE_NO_TURNOS = "No se encontraron turnos para DNI: %s";

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

			if (idTurnoSeleccionado == null) {
				JOptionPane.showMessageDialog(supervisorView, "Para CANCELAR, debe seleccionar un solo turno.");
				return;
			}
			TurnoDTO turnoSeleccionado = controller.readByIdTurno(idTurnoSeleccionado);

			if (turnoSeleccionado == null)
				return;

			turnoSeleccionado.setFechaCancelado(new Date());
			controller.update(turnoSeleccionado);
			supervisorView.clearTurnos();

			JOptionPane.showMessageDialog(supervisorView,
					String.format("Turno con Nro. Turno: %s fué cancelado.", turnoSeleccionado.getIdTurno()));
		}
	}

	private void onDisplayTurnosFormView(ActionEvent e) {
		TurnoFormView.getInstance().clearData();
		TurnoFormView.getInstance().display();
	}

	private void onBuscarTurnos(ActionEvent e) {
		String dniBuscado = supervisorView.getDniClienteBusquedaTurno();

		if (dniValido(dniBuscado)) {
			if (dniBuscado.trim().isEmpty()) {
				List<TurnoDTO> turnos = controller.readAllDisponibles();

				supervisorView.clearTurnos();
				supervisorView.setTurnos(turnos);
			} else {
				Integer dniCliente = Integer.parseInt(dniBuscado);
				List<TurnoDTO> turnos = controller.readAllByDNI(dniCliente);

				if (turnos == null || turnos.isEmpty()) {
					JOptionPane.showMessageDialog(supervisorView, String.format(MENSAGE_NO_TURNOS, dniBuscado));
				} else {
					supervisorView.clearTurnos();
					supervisorView.setTurnos(turnos);
				}
			}
		}
	}

	private boolean dniValido(String dni) {
		if (dni.trim().isEmpty())
			return true;

		try {
			Integer.parseInt(dni);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(supervisorView, String.format(MENSAGE_NO_TURNOS, dni));
			return false;
		}
		return true;
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