package presentacion;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

import business_logic.ConfiguradorCapacidadTurnosController;
import business_logic.TurnosController;
import dto.temporal.AltaDeTurnoDTO;
import dto.validators.StringValidator;
import presentacion.views.supervisor.ConfiguracionView;
import presentacion.views.supervisor.FormTurno;
import presentacion.views.supervisor.PanelTurnos;
import presentacion.views.utils.ConfirmationDialog;
import presentacion.views.utils.MessageDialog;

public class TurnosPresenter {

	private static final String CONFIRMATION = "¿Está seguro que desea cancelar el turno?";

	private static final String MENSAGE_NUEVO_TURNO = "Se registró nuevo Turno.";

	private static final String LIMITE_TURNOS = "¡No hay Turno disponible para ese día!";

	private PanelTurnos view;

	private FormTurno turnoForm = FormTurno.getInstance();
	private ConfiguracionView configuracionForm = ConfiguracionView.getInstance();

	private TurnosController controller;
	private ConfiguradorCapacidadTurnosController configurador;

	public TurnosPresenter(PanelTurnos view, TurnosController controller,
			ConfiguradorCapacidadTurnosController configController) {
		this.view = view;
		this.controller = controller;
		this.configurador = configController;
		view.setActionBuscar((a) -> onBuscar(a));
		view.setActionRegistrarTurno((a) -> onDisplayTurnosFormView(a));
		view.setActionConfiguracion((a) -> onDisplayConfiguracionFormView(a));
		view.setActionCancelarTurno((a) -> onCancelar(a));
		turnoForm.setActionSave(a -> onSave(a));
		turnoForm.setActionCancel(a -> {
			turnoForm.dispose();
		});
		configuracionForm.setActionSave(a -> onSaveConfiguracion(a));
		configuracionForm.setActionCancel(a -> {
			configuracionForm.dispose();
		});
	}

	private void onDisplayTurnosFormView(ActionEvent e) {
		FormTurno.getInstance().clearData();
		FormTurno.getInstance().display();
	}

	private void onDisplayConfiguracionFormView(ActionEvent e) {
		ConfiguracionView.getInstance().clearData();
		ConfiguracionView.getInstance().display();
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
		if (view.getIdSelectedTurno() == null)
			return;

		if (new ConfirmationDialog(CONFIRMATION).open() == 0) {
			Integer idTurno = view.getIdSelectedTurno();
			if (idTurno != null) {
				controller.cancelById(idTurno);
				view.clear();
				new MessageDialog().showMessages("El Turno con Nro.: " + idTurno + " fué cancelado.");
			}
		}
	}

	private void onSave(ActionEvent a) {
		AltaDeTurnoDTO turno = turnoForm.getData();
		List<String> errors = turno.validate();

		if (errors.isEmpty()) {
			if (hayEspacioEnTaller(turno.getFechaProgramada())) {
				controller.save(turno);
				new MessageDialog().showMessages(MENSAGE_NUEVO_TURNO);
				turnoForm.dispose();
			} else {
				new MessageDialog().showMessages(LIMITE_TURNOS);
			}
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	private void onSaveConfiguracion(ActionEvent a) {
		String capacidadTurnos = configuracionForm.getCapacidadTurnos();
		configurador.establecerCapacidadDeTurnos(capacidadTurnos);
		configuracionForm.dispose();
	}

	private boolean hayEspacioEnTaller(Date fechaProgramada) {
		int cantidadDeTurnos = controller.readCantidadDeTurnos(fechaProgramada).size();
		int capacidadDeTurnos = configurador.readCapacidadDeTurnos();

		return cantidadDeTurnos < capacidadDeTurnos || capacidadDeTurnos == 0;
	}
}