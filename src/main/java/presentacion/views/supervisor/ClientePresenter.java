package presentacion.views.supervisor;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business_logic.ClientesController;
import business_logic.OrdenesTrabajoController;
import business_logic.VehiculosController;
import business_logic.exceptions.ConflictException;
import business_logic.exceptions.ForbiddenException;
import dto.ClienteDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.VehiculoConOrdenDeTrabajoDTO;
import dto.temporal.AltaClienteDTO;
import dto.temporal.AltaDeVehiculoDTO;
import dto.temporal.AltaOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.utils.MessageDialog;

public class ClientePresenter {

	private static final String CLIENTE_NO_SELECCIONADO = "Debe seleccionar un cliente";

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
		view.setActionOnEditarCliente(a -> onDisplayFormForUpdate(a));
		ClienteFormView.getInstance().setActionOnUpdate(a -> onUpdate(a));
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
		if (view.getIdCliente() != null) {
			VehiculoFormView.getInstance().clearData();
			VehiculoFormView.getInstance().display();
		}
	}

	private void onBuscar(ActionEvent a) {
		String inputDni = view.getDniCliente();
		if (new StringValidator(inputDni).number("").validate().isEmpty()) {
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
		VehiculoConOrdenDeTrabajoDTO idVehiculo = view.getidVehiculoSeleccionado();
		if (idVehiculo != null) {
			FichaTecnicaVehiculoDTO fichaVehiculo = vehiculosController
					.readFichaTecnicaById(idVehiculo.getIdFichaTecnica());
			if (fichaVehiculo != null) {
				view.clearDataFichaTecnicaVehiculo();
				view.clearDataOrdenDeTrabajo();
				view.setData(fichaVehiculo);
				OrdenDeTrabajoDTO ordenDeTrabajo = this.ordenDeTrabajoController.readByIdVehiculo(idVehiculo.getId());
				if (ordenDeTrabajo != null) {
					view.setData(ordenDeTrabajo);
					view.lockButtonRegistrarOrdenDeTrabajo();
				} else {
					view.clearDataOrdenDeTrabajo();
					view.unlockButtonRegistrarOrdenDeTrabajo();
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
				new MessageDialog().showMessages(e1.getMessage());
			}
		} else {
			new MessageDialog().showMessages(errors);
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
				new MessageDialog().showMessages(e1.getMessage());
			}
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	private void onRegistrarOrdenDeTrabajo(ActionEvent e) {
		Integer idVehiculo = view.getidVehiculoSeleccionado().getId();
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
					new MessageDialog().showMessages(e1.getMessage());
				}
			} else {
				new MessageDialog().showMessages(errors);
			}
		}
	}
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		if (view.getIdCliente() != null) {
			ClienteFormView.getInstance().clearData();
			ClienteFormView.getInstance().setData(clienteController.readByDni(Integer.parseInt(view.getDniCliente())));
			ClienteFormView.getInstance().display();
		}
		else {
			new MessageDialog().showMessages(CLIENTE_NO_SELECCIONADO);
		}
	}
	
	private void onUpdate(ActionEvent a) {
		AltaClienteDTO clienteAux = ClienteFormView.getInstance().getData();
		List<String> errores = clienteAux.validate();
		if (errores.isEmpty()) {
			ClienteDTO cliente = new ClienteDTO(clienteAux);
			cliente.setIdCliente(view.getIdCliente());
			cliente.getDatosPersonalesDTO().setId(view.getIdDatosPersonalesCliente());
			clienteController.update(cliente);
			view.clearDataCliente();
			view.setData(cliente);
			ClienteFormView.getInstance().close();
		} else {
			new MessageDialog().showMessages(errores);
		}
	}
}
