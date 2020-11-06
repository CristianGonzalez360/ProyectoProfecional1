package presentacion;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import business_logic.OrdenesTrabajoController;
import dto.OrdenDeTrabajoDTO;
import presentacion.views.SupervisorControlView;

public class OrdenDeTrabajoPresenter {

	private SupervisorControlView supervisorView = SupervisorControlView.getInstance();

	private OrdenesTrabajoController controller;

	public OrdenDeTrabajoPresenter(OrdenesTrabajoController controller) {
		this.controller = controller;
		supervisorView.setActionBuscarOT((a) -> onBuscarOT(a));
	}

	public void onBuscarOT(ActionEvent e) {
		String dni = supervisorView.getDniClienteBusquedaOT();

		if (dni.trim().isEmpty()) {
			List<OrdenDeTrabajoDTO> ordenes = controller.readAll();
			supervisorView.clearOrdenesDeTrabajo();
			supervisorView.setOrdenesDeTrabajo(ordenes);
		} else {
			OrdenDeTrabajoDTO orden = controller.readByDniCliente(dni);
			if (orden != null) {
				supervisorView.clearOrdenesDeTrabajo();
				supervisorView.setOrdenesDeTrabajo(Arrays.asList(new OrdenDeTrabajoDTO[] { orden }));
			}

		}
	}
}
