package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business_logic.ClientesController;
import business_logic.VehiculosController;
import dto.ClienteDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import dto.validators.Patterns;
import dto.validators.StringValidator;
import presentacion.views.PanelClientesView;
import presentacion.views.utils.ErrorDialog;

public class ClientePresenter {
	
	private PanelClientesView view;
	
	private ClientesController clienteController;

	private VehiculosController vehiculosController;
	
	public ClientePresenter(PanelClientesView view, ClientesController controller, VehiculosController vehiculoController) {
		this.view = view;
		this.clienteController = controller;
		this.vehiculosController = vehiculoController;
		
		this.view.setActionBuscar((a)->onBuscar(a));
		this.view.setActionSelectVehiculoCliente(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				onSelectVehiculoDeCliente();
			}
		});
	}
	
	private void onBuscar(ActionEvent a) {
		String inputDni = view.getDniCliente();
		List<String> errors = new StringValidator(inputDni).regex("El dni debe ser un numero de dni", Patterns.POSITIVE_INTEGER).validate();
		if(errors.isEmpty()) {
			ClienteDTO cliente = clienteController.readByDni(Integer.parseInt(inputDni));
			if(cliente != null) {
				view.clearDataCliente();
				view.setData(cliente);
				List<VehiculoConOrdenDeTrabajoDTO> vehiculos = vehiculosController.readByIdCliente(cliente.getIdCliente());
				view.clearDataListadoVehiculosCliente();
				view.setData(vehiculos);
				view.clearDataFichaTecnicaVehiculo();
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
}
