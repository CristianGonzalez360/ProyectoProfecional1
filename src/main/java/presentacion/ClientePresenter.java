package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business_logic.ClientesController;
import business_logic.OrdenesTrabajoController;
import business_logic.VehiculosController;
import business_logic.exceptions.ConflictException;
import business_logic.exceptions.ForbiddenException;
import dto.AltaClienteDTO;
import dto.AltaDeVehiculoDTO;
import dto.AltaOrdenDeTrabajoDTO;

import dto.ClienteDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
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

	public ClientePresenter(PanelClientesView view, ClientesController controller,
			VehiculosController vehiculoController, OrdenesTrabajoController otController) {
		this.view = view;
		clienteController = controller;
		this.vehiculosController = vehiculoController;
		ordenDeTrabajoController = otController;
		
		view.setActionBuscar((a) -> onBuscar(a));
		view.setActionSelectVehiculoCliente(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				onSelectVehiculoDeCliente();
			}
		});
		view.setActionRegistrarCliente((a) -> onDisplayClienteFormView(a));
		view.setActionRegistrarVehiculo((a) -> onDisplayVehiculoFormView(a));
		view.setActionRegistrarOrdenDeTrabajo((a) -> onDisplayOrdenDeTrabajoForm(a));
		
		ClienteFormView.getInstance().setActionOnSave((a) -> onRegistrarCliente(a));
		VehiculoFormView.getInstance().setActionSave((a) -> onRegistrarVehiculo(a));
		AltaOrdenTrabajoFormView.getInstance().setActionGuardar((a) -> onRegistrarOrdenDeTrabajo(a));
	}

	private void onDisplayOrdenDeTrabajoForm(ActionEvent a) {
		if (view.getidVehiculoSeleccionado() != null) {
			AltaOrdenTrabajoFormView.getInstance().clearData();
			AltaOrdenTrabajoFormView.getInstance().display();
		}
	}

	private void onDisplayClienteFormView(ActionEvent e) {
		view.clearAll();
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
		if(new StringValidator(inputDni).number("").validate().isEmpty()) {
			ClienteDTO cliente = clienteController.readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				view.clearDataCliente();
				view.setData(cliente);
				List<VehiculoConOrdenDeTrabajoDTO> vehiculos = vehiculosController
						.readByIdCliente(cliente.getIdCliente());
				view.clearDataListadoVehiculosCliente();
				view.setData(vehiculos);
				view.clearDataFichaTecnicaVehiculo();
				view.clearDataOrdenDeTrabajo();
			} else {
				view.clearAll();
			}
		} else {
			view.clearAll();
		}
	}
	
	private void onSelectVehiculoDeCliente() {
		Integer idVehiculo = view.getidVehiculoSeleccionado();
		if (idVehiculo != null) {
			FichaTecnicaVehiculoDTO fichaVehiculo = vehiculosController.readFichaTecnicaById(idVehiculo);
			if (fichaVehiculo != null) {
				view.clearDataFichaTecnicaVehiculo();
				view.clearDataOrdenDeTrabajo();
				view.setData(fichaVehiculo);
				OrdenDeTrabajoDTO ordenDeTrabajo = this.ordenDeTrabajoController.readByIdVehiculo(idVehiculo);
				if(ordenDeTrabajo != null) {
					view.setData(ordenDeTrabajo);
				} else {
					view.clearDataOrdenDeTrabajo();
				}
			}
		}
	}

	private void onRegistrarCliente(ActionEvent e) {
		AltaClienteDTO cliente = ClienteFormView.getInstance().getData();
		List<String> errors = cliente.validate();
		if (errors.isEmpty()) {
			try {
				clienteController.save(new ClienteDTO(cliente));
				ClienteFormView.getInstance().clearData();
				ClienteFormView.getInstance().close();
			} catch (ConflictException e1) {
				new ErrorDialog().showMessages(e1.getMessage());
			}
		} else {
			new ErrorDialog().showMessages(errors);
		}
	}

	private void onRegistrarVehiculo(ActionEvent e) {
		AltaDeVehiculoDTO vehiculoDeAlta = VehiculoFormView.getInstance().getData();
		List<String> errors = vehiculoDeAlta.validate();
		if (errors.isEmpty()) {
			try {
				vehiculosController.save(view.getIdCliente(), vehiculoDeAlta);
				VehiculoFormView.getInstance().close();
				view.clearDataListadoVehiculosCliente();
				view.setData(vehiculosController.readByIdCliente(view.getIdCliente()));
			} catch (ConflictException e1) {
				new ErrorDialog().showMessages(e1.getMessage());
			}
		} else {
			new ErrorDialog().showMessages(errors);
		}
	}

	private void onRegistrarOrdenDeTrabajo(ActionEvent e) {
		Integer idVehiculo = view.getidVehiculoSeleccionado();
		if (idVehiculo != null) {
			AltaOrdenDeTrabajoDTO ordenDeTrabajo = AltaOrdenTrabajoFormView.getInstance().getData();
			List<String> errors = ordenDeTrabajo.validate();
			if (errors.isEmpty()) {
				try {
					ordenDeTrabajoController.save(idVehiculo, ordenDeTrabajo);
					OrdenDeTrabajoDTO dto = ordenDeTrabajoController.readByIdVehiculo(idVehiculo);
					view.setData(dto);
					AltaOrdenTrabajoFormView.getInstance().close();
				} catch (ForbiddenException e1) {
					new ErrorDialog().showMessages(e1.getMessage());
				}
			} else {
				new ErrorDialog().showMessages(errors);
			}
		}
	}
}
