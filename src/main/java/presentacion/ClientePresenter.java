package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business_logic.ClientesController;
import business_logic.OrdenesTrabajoController;
import business_logic.VehiculosController;
import business_logic.exceptions.ConflictException;
import dto.AltaDeVehiculoDTO;
import dto.AltaOrdenDeTrabajoDTO;
import dto.ClienteDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import dto.validators.Patterns;
import dto.validators.StringValidator;
import presentacion.views.AltaOrdenTrabajoFormView;
import presentacion.views.ClienteFormView;
import presentacion.views.PanelClientesView;
import presentacion.views.VehiculoFormView;
import presentacion.views.utils.ErrorDialog;

public class ClientePresenter {
	
	private PanelClientesView view;
	
	private ClientesController clienteController;

	private VehiculosController vehiculosController;
	
	private OrdenesTrabajoController ordenDeTrabajoController;
	
	public ClientePresenter(PanelClientesView view, ClientesController controller, VehiculosController vehiculoController, OrdenesTrabajoController otController) {
		this.view = view;
		this.clienteController = controller;
		this.vehiculosController = vehiculoController;
		this.ordenDeTrabajoController = otController;
		
		this.view.setActionBuscar((a)->onBuscar(a));
		this.view.setActionSelectVehiculoCliente(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				onSelectVehiculoDeCliente();
			}
		});
		
		this.view.setActionRegistrarCliente((a)->onDisplayClienteFormView(a));
		this.view.setActionRegistrarVehiculo((a)->onDisplayVehiculoFormView(a));
		this.view.setActionRegistrarOrdenDeTrabajo((a)->onDisplayOrdenDeTrabajoForm(a));
		
		ClienteFormView.getInstance().setActionOnSave((a)->onRegistrarCliente(a));
		VehiculoFormView.getInstance().setActionSave((a)->onRegistrarVehiculo(a));
		AltaOrdenTrabajoFormView.getInstance().setActionGuardar((a)->onRegistrarOrdenDeTrabajo(a));
	}
	
	private void onDisplayOrdenDeTrabajoForm(ActionEvent a) {
		if(view.getidVehiculoSeleccionado() != null) {
			AltaOrdenTrabajoFormView.getInstance().clearData();
			AltaOrdenTrabajoFormView.getInstance().display();	
		}
	}

	private void onDisplayClienteFormView(ActionEvent e) {
		ClienteFormView.getInstance().clearData();
		ClienteFormView.getInstance().display();
	}
	
	private void onDisplayVehiculoFormView(ActionEvent e) {
		if(view.getIdCliente() != null) {
			VehiculoFormView.getInstance().clearData();
			VehiculoFormView.getInstance().display();	
		}
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
	
	private void onRegistrarCliente(ActionEvent e) {
		ClienteDTO cliente = ClienteFormView.getInstance().getData();
		List<String> errors = cliente.validate();
		if(errors.isEmpty()) {
			try {
				clienteController.save(cliente);
				ClienteFormView.getInstance().clearData();
				ClienteFormView.getInstance().close();
			}catch(ConflictException e1) {
				new ErrorDialog().showMessages(e1.getMessage());
			}
		}else {
			new ErrorDialog().showMessages(errors);
		}
	}
	
	private void onRegistrarVehiculo(ActionEvent e) {
		Integer idCliente = view.getIdCliente();
		if(idCliente != null) {
			AltaDeVehiculoDTO vehiculoDeAlta = VehiculoFormView.getInstance().getData();
			List<String> errors = vehiculoDeAlta.validate();
			if(errors.isEmpty()) {
				vehiculosController.save(idCliente, vehiculoDeAlta);
				ClienteFormView.getInstance().clearData();
				ClienteFormView.getInstance().close();
			}else {
				new ErrorDialog().showMessages(errors);
			}	
		}
	}
	
	private void onRegistrarOrdenDeTrabajo(ActionEvent e) {
		Integer idVehiculo = view.getidVehiculoSeleccionado();
		if(idVehiculo != null) {
			AltaOrdenDeTrabajoDTO ordenDeTrabajo = AltaOrdenTrabajoFormView.getInstance().getData();
			List<String> errors = ordenDeTrabajo.validate();
			if(errors.isEmpty()) {
				this.ordenDeTrabajoController.save(idVehiculo, ordenDeTrabajo);
				ClienteFormView.getInstance().clearData();
				ClienteFormView.getInstance().close();
			}else {
				new ErrorDialog().showMessages(errors);
			}
		}
	}
}
