package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business_logic.ClientesController;
import business_logic.VehiculosController;
import dto.ClienteDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.Patterns;
import dto.VehiculoConOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.ClienteFormView;
import presentacion.views.PanelClientesView;
import presentacion.views.utils.ErrorDialog;

public class ClientePresenter {
	
	private static String CLIENTE_NO_SELECCIONADO = "Debe seleccionar un cliente.";
	
	private PanelClientesView view;
	
	private ClientesController clienteController;

	private VehiculosController vehiculosController;
	
	private ClienteDTO cliente;
	
	private ClienteFormView clienteFormview;
	
	public ClientePresenter(PanelClientesView view, ClientesController controller, VehiculosController vehiculoController) {
		this.view = view;
		this.clienteController = controller;
		this.vehiculosController = vehiculoController;
		this.clienteFormview = ClienteFormView.getInstance();
		
		this.view.setActionBuscar((a)->onBuscar(a));
		this.view.setActionSelectVehiculoCliente(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				onSelectVehiculoDeCliente();
			}
		});
		
		this.view.setActionEditar(a -> onDisplayFormForUpdate(a));
		this.clienteFormview.setActionOnUpdate(a -> onUpdate(a));
	}
	
	private void onBuscar(ActionEvent a) {
		String inputDni = view.getDniCliente();
		List<String> errors = new StringValidator(inputDni).regex("El dni debe ser un numero de dni", Patterns.NON_NEGATIVE_INTEGER_FIELD).validate();
		if(errors.isEmpty()) {
			ClienteDTO cliente = clienteController.readByDni(Integer.parseInt(inputDni));
			if(cliente != null) {
				refreshData(cliente);
			}
		} else {
			new ErrorDialog().showMessages(errors);
		}
	}
	
	private void onSelectVehiculoDeCliente() {
		Integer idVehiculo = view.getidVehiculoSeleccionado();
		if(idVehiculo != null) {
			FichaTecnicaVehiculoDTO fichaVehiculo =  vehiculosController.readFichaTecnicaById(idVehiculo);
			if(fichaVehiculo != null) {
				view.clearDataFichaTecnicaVehiculo();
				view.setData(fichaVehiculo);
			}
		}
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		if(this.cliente != null) {
			this.clienteFormview.clearData();
			this.clienteFormview.setData(cliente);
			this.clienteFormview.display();
		} else {
			new ErrorDialog().showMessages(CLIENTE_NO_SELECCIONADO);
		}
	}
	
	private void onUpdate(ActionEvent a) {
		ClienteDTO cliente = clienteFormview.getData();
		cliente.setIdCliente(this.cliente.getIdCliente());
		cliente.getDatosPersonalesDTO().setId(this.cliente.getIdDatosPersonales());
		this.clienteController.update(cliente);
		refreshData(cliente);
		this.clienteFormview.close();
	}
	
	private void refreshData(ClienteDTO cliente) {
		view.clearDataCliente();
		view.setData(cliente);
		List<VehiculoConOrdenDeTrabajoDTO> vehiculos = vehiculosController.readByIdCliente(cliente.getIdCliente());
		view.clearDataListadoVehiculosCliente();
		view.setData(vehiculos);
		view.clearDataFichaTecnicaVehiculo();
		this.cliente = cliente;
	}
}
