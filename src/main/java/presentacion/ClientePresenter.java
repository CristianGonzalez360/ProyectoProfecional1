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
import dto.Patterns;
import dto.VehiculoConOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.AltaOrdenTrabajoFormView;
import presentacion.views.ClienteFormView;
import presentacion.views.PanelClientesView;
import presentacion.views.VehiculoFormView;
import presentacion.views.utils.ConfirmationDialog;
import presentacion.views.utils.ErrorDialog;

public class ClientePresenter {

	private static String CLIENTE_NO_SELECCIONADO = "Debe seleccionar un cliente.";
	
	private static final String CONFIRMATION_OT_CREATION = "Ud. está por crear una orden de trabajo ¿Desea confirmar la operación?";

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

		ClienteFormView.getInstance().setActionOnUpdate(a -> onUpdate(a));

		view.setActionRegistrarCliente((a) -> onDisplayClienteFormView(a));
		view.setActionRegistrarVehiculo((a) -> onDisplayVehiculoFormView(a));
		view.setActionRegistrarOrdenDeTrabajo((a) -> onDisplayOrdenDeTrabajoForm(a));
		view.setActionOnEditarCliente((a)->onDisplayFormForUpdate(a));
		
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
		ClienteFormView.getInstance().clearData();
		ClienteFormView.getInstance().display();
	}

	private void onDisplayVehiculoFormView(ActionEvent e) {
		if (view.getIdCliente() != null) {
			VehiculoFormView.getInstance().clearData();
			VehiculoFormView.getInstance().display();
		}
	}

	private void onDisplayFormForUpdate(ActionEvent a) {
		if (view.getIdCliente() != null) {
			ClienteFormView.getInstance().clearData();
			ClienteFormView.getInstance().setData(clienteController.readByDni(Integer.parseInt(view.getDniCliente())));
			ClienteFormView.getInstance().display();
		}
		else {
			new ErrorDialog().showMessages(CLIENTE_NO_SELECCIONADO);
		}
	}

	private void onBuscar(ActionEvent a) {
		String inputDni = view.getDniCliente();
		List<String> errors = new StringValidator(inputDni).regex("El dni debe ser un numero de dni", Patterns.NON_NEGATIVE_INTEGER_FIELD).validate();
		if (errors.isEmpty()) {
			ClienteDTO cliente = clienteController.readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				refreshData(cliente);
			}
		} else {
			new ErrorDialog().showMessages(errors);
		}
	}

	private void onSelectVehiculoDeCliente() {
		Integer idVehiculo = view.getidVehiculoSeleccionado();
		if (idVehiculo != null) {
			FichaTecnicaVehiculoDTO fichaVehiculo = vehiculosController.readFichaTecnicaById(idVehiculo);
			if (fichaVehiculo != null) {
				view.clearDataFichaTecnicaVehiculo();
				view.setData(fichaVehiculo);
			}
		}
	}


	private void refreshData(ClienteDTO cliente) {
		view.clearDataCliente();
		view.setData(cliente);
		List<VehiculoConOrdenDeTrabajoDTO> vehiculos = vehiculosController.readByIdCliente(cliente.getIdCliente());
		view.clearDataListadoVehiculosCliente();
		view.setData(vehiculos);
		view.clearDataFichaTecnicaVehiculo();
	}

	private void onRegistrarCliente(ActionEvent e) {
		ClienteDTO cliente = ClienteFormView.getInstance().getData();
		List<String> errors = cliente.validate();
		if (errors.isEmpty()) {
			try {
				clienteController.save(cliente);
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
		Integer idCliente = view.getIdCliente();
		if (idCliente != null) {
			AltaDeVehiculoDTO vehiculoDeAlta = VehiculoFormView.getInstance().getData();
			List<String> errors = vehiculoDeAlta.validate();
			if (errors.isEmpty()) {
				try {
					vehiculosController.save(idCliente, vehiculoDeAlta);
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
	}

	private void onRegistrarOrdenDeTrabajo(ActionEvent e) {
		Integer idVehiculo = view.getidVehiculoSeleccionado();
		if (idVehiculo != null) {
			AltaOrdenDeTrabajoDTO ordenDeTrabajo = AltaOrdenTrabajoFormView.getInstance().getData();
			List<String> errors = ordenDeTrabajo.validate();
			if (errors.isEmpty()) {
				if (new ConfirmationDialog(CONFIRMATION_OT_CREATION).open() == 0) {
					this.ordenDeTrabajoController.save(idVehiculo, ordenDeTrabajo);
					AltaOrdenTrabajoFormView.getInstance().close();
				}
			} else {
				new ErrorDialog().showMessages(errors);
			}
		}
	}
	
	private void onUpdate(ActionEvent a) {
		ClienteDTO cliente = ClienteFormView.getInstance().getData();
		List<String> errores = cliente.validate();
		if (errores.isEmpty()) {
			cliente.setIdCliente(view.getIdCliente());
			cliente.getDatosPersonalesDTO().setId(view.getIdDatosPersonalesCliente());
			clienteController.update(cliente);
			view.clearDataCliente();
			view.setData(cliente);
			ClienteFormView.getInstance().close();
		} else {
			new ErrorDialog().showMessages(errores);
		}
	}
}

