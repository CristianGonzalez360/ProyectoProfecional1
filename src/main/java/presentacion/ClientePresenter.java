package presentacion;

import java.awt.event.ActionEvent;

import business_logic.ClientesController;
import dto.ClienteDTO;
import presentacion.views.ClienteFormView;
import presentacion.views.Presenter;

public class ClientePresenter implements Presenter{
	
	private ClienteFormView view = ClienteFormView.getInstance();
	private ClientesController clientesController;
	
	public ClientePresenter(ClientesController clientesController) {
		this.view = ClienteFormView.getInstance();
		view.setActionOnSave(a -> onSave(a));
		this.clientesController = clientesController;
	}
	
	private void onSave(ActionEvent a) {
		ClienteDTO cliente = ClienteFormView.getInstance().getData();
		this.clientesController.save(cliente);
	}

	@Override
	public void onInit() {
		ClienteFormView.getInstance().setVisible(true);
	}

	
}
