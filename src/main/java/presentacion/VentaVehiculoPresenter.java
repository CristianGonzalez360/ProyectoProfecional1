package presentacion;

import java.awt.event.ActionEvent;

import business_logic.TurnosController;
import presentacion.views.gerente.RegistrarEntregaVehiculoFormView;
import presentacion.views.supervisor.PanelTurnos;

public class VentaVehiculoPresenter {

	private static final String CONFIRMATION = "¿Está seguro que desea salir?";

	private static final String MENSAGE_NUEVO_TURNO = "Se registró Entrega de vehiculo.";

//	private TurnosPanelView view;

	private RegistrarEntregaVehiculoFormView entregaVehiculoVentaForm = RegistrarEntregaVehiculoFormView.getInstance();

	private TurnosController controller;

	public VentaVehiculoPresenter(PanelTurnos view, TurnosController controller) {
//		this.view = view;
		this.controller = controller;
		entregaVehiculoVentaForm.setActionSave(a -> onSave(a));
		entregaVehiculoVentaForm.setActionCancel(a -> {
			entregaVehiculoVentaForm.dispose();
		});
	}

	private void onSave(ActionEvent a) {
// TODO validar que datos le llegan con pablo
//		AltaDeTurnoDTO turno = entregaVehiculoVentaForm.getData();
//		List<String> errors = turno.validate();
//		if (errors.isEmpty()) {
//			controller.save(turno);//TODO armar el venta de vehiculoController o pedirlo que lo pongan en el repositorio
//			new MessageDialog().showMessages(MENSAGE_NUEVO_TURNO);
//			entregaVehiculoVentaForm.dispose();
//		} else {
//			new MessageDialog().showMessages(errors);
//		}
	}
}