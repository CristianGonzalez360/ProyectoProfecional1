package presentacion;

import java.awt.event.ActionEvent;

import presentacion.views.supervisor.EntregaVehiculosPanelView;

public class EntregaDeVehiculoPresenter {

	private EntregaVehiculosPanelView view;

	public EntregaDeVehiculoPresenter(EntregaVehiculosPanelView view) {
		this.view = view;

		this.view.setActionBuscarEntregas((a) -> onBuscarEntregas(a));
		this.view.setRegistrarEntrega((a) -> onRegistrarEntrega(a));
	}

	private void onBuscarEntregas(ActionEvent event) {

	}

	private void onRegistrarEntrega(ActionEvent event) {

	}

}
