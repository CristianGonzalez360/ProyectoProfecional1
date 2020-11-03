package presentacion;

import java.awt.event.ActionEvent;
import business_logic.ClientesController;
import dto.ClienteDTO;
import presentacion.views.ClienteFormView;
import presentacion.views.PanelClientesView;
import presentacion.views.Presenter;
import presentacion.views.utils.ErrorDialog;

public class ClientePresenter implements Presenter {
	
	private static String CLIENTE_NO_SELECCIONADO = "Debe seleccionar un cliente.";
	
	private ClienteFormView clienteFormview;
	private PanelClientesView panelClientesView;
	
	private ClienteDTO cliente;
	
	private ClientesController clientesController;
	
	public ClientePresenter(ClientesController clientesController) {
		this.clienteFormview = ClienteFormView.getInstance();
		this.panelClientesView = PanelClientesView.getInstance();
		this.clienteFormview.setActionOnSave(a -> onSave(a));
		this.clienteFormview.setActionOnUpdate(a-> onUpdate(a));
		this.panelClientesView.setOnSearchAction(a -> onSearch(a));
		this.panelClientesView.setOnCreateAction(a -> onDisplayFormforSave(a));
		this.panelClientesView.setOnUpdateAction(a -> onDisplayFormForUpdate(a));
		this.clientesController = clientesController;
	}
	
	private void onDisplayFormforSave(ActionEvent a) {
		this.clienteFormview.clearData();
		this.clienteFormview.display();
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		this.clienteFormview.clearData();
		if(this.cliente != null) {
			this.clienteFormview.setData(cliente);
			this.clienteFormview.display();
		} else {
			new ErrorDialog().showMessages(CLIENTE_NO_SELECCIONADO);
		}
	}
	
	private void onSearch(ActionEvent a) {
		this.cliente = this.clientesController.readByDni(panelClientesView.getDniCliente());
		panelClientesView.setData(cliente);
	}

	private void onUpdate(ActionEvent a) {
		ClienteDTO cliente = clienteFormview.getData();
		cliente.setIdCliente(this.cliente.getIdCliente());
		this.clientesController.update(cliente);
	}

	private void onSave(ActionEvent a) {
		ClienteDTO cliente = clienteFormview.getData();
		this.clientesController.save(cliente);
	}

	@Override
	public void onInit() {
		//ClienteFormView.getInstance().setVisible(true);
	}
}
