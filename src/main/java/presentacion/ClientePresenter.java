package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business_logic.ClientesController;
import business_logic.GarantiasController;
import business_logic.OrdenesTrabajoController;
import business_logic.VehiculosConOrdenDeTrabajoController;
import business_logic.exceptions.ConflictException;
import business_logic.exceptions.ForbiddenException;
import dto.ClienteDTO;
import dto.GarantiaVehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.VehiculoConOrdenDeTrabajoDTO;
import dto.temporal.AltaClienteDTO;
import dto.temporal.AltaDeVehiculoDTO;
import dto.temporal.AltaOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.supervisor.AltaOrdenTrabajoFormView;
import presentacion.views.supervisor.ClienteFormView;
import presentacion.views.supervisor.PanelClientesView;
import presentacion.views.supervisor.VehiculoFormView;
import presentacion.views.utils.MessageDialog;

public class ClientePresenter {

	private static final String CLIENTE_NO_SELECCIONADO = "Debe seleccionar un cliente";

	private PanelClientesView view;

	private ClientesController clienteController;

	private VehiculosConOrdenDeTrabajoController vehiculosController;

	private OrdenesTrabajoController ordenDeTrabajoController;
	
	private GarantiasController garantiasController;

	private Integer idClientePresentado;

	public ClientePresenter(PanelClientesView view, ClientesController controller,
			VehiculosConOrdenDeTrabajoController vehiculoController, OrdenesTrabajoController otController,
			GarantiasController garantiasController) {
		this.view = view;
		clienteController = controller;
		this.vehiculosController = vehiculoController;
		ordenDeTrabajoController = otController;
		this.garantiasController = garantiasController;

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
			if (!garantiasController.estaEnGarantia(view.getidVehiculoSeleccionado())) {
				AltaOrdenTrabajoFormView.getInstance().deshabilitarGarantia();
			} else {
				AltaOrdenTrabajoFormView.getInstance().habilitarGarantia();
			}
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
		if (idClientePresentado != null) {
			VehiculoFormView.getInstance().clearData();
			VehiculoFormView.getInstance().display();
		}
	}

	private void onBuscar(ActionEvent a) {
		view.clearAll();
		String inputDni = view.getDniCliente();
		if (new StringValidator(inputDni).number("").validate().isEmpty()) {
			ClienteDTO cliente = clienteController.readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				idClientePresentado = cliente.getIdCliente();
				view.setData(cliente);
				view.setData(vehiculosController.readVehiculosConFichaTecnicaByIdCliente(cliente.getIdCliente()));
			}
		}
	}

	private void onSelectVehiculoDeCliente() {
		view.clearDataFichaTecnicaVehiculo();
		view.clearDataOrdenDeTrabajo();
		view.clearDataGarantia();
		
		Integer idVehiculo = view.getidVehiculoSeleccionado();
		if (idVehiculo != null) {
			VehiculoConOrdenDeTrabajoDTO vehiConOT = vehiculosController.readById(idVehiculo);
			FichaTecnicaVehiculoDTO fichaVehiculo = vehiculosController.readFichaTecnicaById(vehiConOT.getIdFichaTecnica());
			if (fichaVehiculo != null) {
				view.setData(fichaVehiculo);
				OrdenDeTrabajoDTO ordenDeTrabajo = this.ordenDeTrabajoController.readByIdVehiculo(idVehiculo);
				if (ordenDeTrabajo != null) {
					view.setData(ordenDeTrabajo);
					view.lockButtonRegistrarOrdenDeTrabajo();
				} else {
					view.clearDataOrdenDeTrabajo();
					view.unlockButtonRegistrarOrdenDeTrabajo();
				}
				GarantiaVehiculoDTO garantia = garantiasController.readByIdVehiculo(idVehiculo);
				if (garantia != null) {
					view.setDataGarantia(garantia);
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
				vehiculosController.save(idClientePresentado, vehiculoDeAlta);
				VehiculoFormView.getInstance().close();
				view.clearDataListadoVehiculosCliente();
				view.setData(vehiculosController.readVehiculosConFichaTecnicaByIdCliente(idClientePresentado));
			} catch (ConflictException e1) {
				new MessageDialog().showMessages(e1.getMessage());
			}
		} else {
			new MessageDialog().showMessages(errors);
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
					new MessageDialog().showMessages(e1.getMessage());
				}
			} else {
				new MessageDialog().showMessages(errors);
			}
		}
	}

	private void onDisplayFormForUpdate(ActionEvent a) {
		if (idClientePresentado != null) {
			ClienteFormView.getInstance().clearData();
			ClienteFormView.getInstance().setData(clienteController.readById(this.idClientePresentado));
			ClienteFormView.getInstance().display();
		} else {
			new MessageDialog().showMessages(CLIENTE_NO_SELECCIONADO);
		}
	}

	private void onUpdate(ActionEvent a) {
		AltaClienteDTO clienteAux = ClienteFormView.getInstance().getData();
		List<String> errores = clienteAux.validate();
		if (errores.isEmpty()) {
			ClienteDTO cliente = new ClienteDTO(clienteAux);
			cliente.setIdCliente(idClientePresentado);
			cliente.getDatosPersonalesDTO().setId(this.clienteController.readById(idClientePresentado).getIdDatosPersonales());
			clienteController.update(cliente);
			view.clearDataCliente();
			view.setData(cliente);
			ClienteFormView.getInstance().close();
		} else {
			new MessageDialog().showMessages(errores);
		}
	}
}
