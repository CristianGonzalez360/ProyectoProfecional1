package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ClientesController;
import business_logic.VehiculosController;
import dto.ClienteDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import presentacion.views.PanelClientesView;

public class ClientePresenter {
	
	private PanelClientesView view;
	
	private ClientesController clienteController;

	private VehiculosController vehiculosController;
	
	public ClientePresenter(PanelClientesView view, ClientesController controller, VehiculosController vehiculoController) {
		this.view = view;
		this.clienteController = controller;
		this.vehiculosController = vehiculoController;
		
		this.view.setActionBuscar((a)->onBuscar(a));
	}
	
	private void onBuscar(ActionEvent a) {
		ClienteDTO cliente = clienteController.readByDni(111);
		List<VehiculoConOrdenDeTrabajoDTO> vehiculos = vehiculosController.readByClienteId(cliente.getIdCliente());
		if(cliente != null) {
			view.setData(cliente);
			view.setData(vehiculos);
		}
	}
}
