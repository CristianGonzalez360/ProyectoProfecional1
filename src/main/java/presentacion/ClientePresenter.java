package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business_logic.ControllersFactory;
import business_logic.exceptions.ConflictException;
import business_logic.exceptions.ForbiddenException;
import business_logic.exceptions.NotFoundException;
import dto.ClienteDTO;
import dto.GarantiaVehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.IngresoOrdenDeTrabajoDTO;
import dto.temporal.AltaClienteDTO;
import dto.temporal.AltaDeVehiculoDTO;
import dto.temporal.AltaOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.supervisor.FormAltaOrdenTrabajo;
import presentacion.views.supervisor.FormCliente;
import presentacion.views.supervisor.FormVehiculo;
import presentacion.views.supervisor.PanelClientesView;
import presentacion.views.utils.MessageDialog;
import presentacion.views.utils.ReporteViewImpl;

public class ClientePresenter {

	private static final String CLIENTE_NO_SELECCIONADO = "Debe seleccionar un cliente";

	private PanelClientesView view;

	private ControllersFactory controllers;
	
	private Integer idClientePresentado;

	public ClientePresenter(PanelClientesView view, ControllersFactory controllers) {
		this.view = view;
		this.controllers = controllers;

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
		view.setActionReporteDeVehiculo((a) -> onMostrarHistorialDeVehiculo(a));
		
		FormCliente.getInstance().setActionOnSave((a) -> onRegistrarCliente(a));
		FormVehiculo.getInstance().setActionSave((a) -> onRegistrarVehiculo(a));
		FormAltaOrdenTrabajo.getInstance().setActionGuardar((a) -> onRegistrarOrdenDeTrabajo(a));
		view.setActionOnEditarCliente(a -> onDisplayFormForUpdate(a));
		FormCliente.getInstance().setActionOnUpdate(a -> onUpdate(a));
	}

	private void onDisplayOrdenDeTrabajoForm(ActionEvent a) {
		if (view.getidVehiculoSeleccionado() != null) {
			FormAltaOrdenTrabajo.getInstance().clearData();
			Integer kilometraje = view.getDataFichaTechica().getKilometraje();
			FormAltaOrdenTrabajo.getInstance().setKilometraje(kilometraje.toString());
			Integer idVehiculo = controllers.makeVehiculosConOrdenDeTrabajoController().readById(view.getidVehiculoSeleccionado()).getIdVehiculo();
			if (!controllers.makeGarantiasController().estaEnGarantia(idVehiculo) && view.getDataGarantia().getAniosDeGarantia() == null) {
				FormAltaOrdenTrabajo.getInstance().deshabilitarGarantia();
			} else {
				FormAltaOrdenTrabajo.getInstance().habilitarGarantia();
			}
			FormAltaOrdenTrabajo.getInstance().display();
		}
	}

	private void onDisplayClienteFormView(ActionEvent e) {
		view.clearAll();
		FormCliente.getInstance().clearData();
		FormCliente.getInstance().display();
	}

	private void onDisplayVehiculoFormView(ActionEvent e) {
		if (idClientePresentado != null) {
			FormVehiculo.getInstance().clearData();
			FormVehiculo.getInstance().display();
		}
	}

	private void onBuscar(ActionEvent a) {
		view.clearAll();
		String inputDni = view.getDniCliente();
		if (new StringValidator(inputDni).number("").validate().isEmpty()) {
			ClienteDTO cliente = controllers.makeClientesController().readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				idClientePresentado = cliente.getIdCliente();
				view.setData(cliente);
				view.setData(controllers.makeVehiculosConOrdenDeTrabajoController().readVehiculosConFichaTecnicaByIdCliente(cliente.getIdCliente()));
			}
		}
	}

	private void onSelectVehiculoDeCliente() {
		view.clearDataFichaTecnicaVehiculo();
		view.clearDataOrdenDeTrabajo();
		view.clearDataGarantia();
		
		Integer idVehiculo = view.getidVehiculoSeleccionado();
		if (idVehiculo != null) {
			IngresoOrdenDeTrabajoDTO vehiConOT = controllers.makeVehiculosConOrdenDeTrabajoController().readById(idVehiculo);
			FichaTecnicaVehiculoDTO fichaVehiculo = controllers.makeVehiculosConOrdenDeTrabajoController().readFichaTecnicaById(vehiConOT.getIdFichaTecnica());
			if (fichaVehiculo != null) {
				view.setData(fichaVehiculo);
				OrdenDeTrabajoDTO ordenDeTrabajo = controllers.makeOrdenesDeTrabajoController().readByIdVehiculo(idVehiculo);
				if (ordenDeTrabajo != null) {
					view.setData(ordenDeTrabajo);
					view.lockButtonRegistrarOrdenDeTrabajo();
				} else {
					view.clearDataOrdenDeTrabajo();
					view.unlockButtonRegistrarOrdenDeTrabajo();
				}
			}
			if(vehiConOT.getIdVehiculo() != null ) {
				GarantiaVehiculoDTO garantia = controllers.makeGarantiasController().readByIdVehiculo(vehiConOT.getIdVehiculo());
				if (garantia != null) {
					view.setDataGarantia(garantia);
				}
			}
		}
	}

	private void onRegistrarCliente(ActionEvent e) {
		AltaClienteDTO cliente = FormCliente.getInstance().getData();
		List<String> errors = cliente.validate();
		if (errors.isEmpty()) {
			try {
				controllers.makeClientesController().save(new ClienteDTO(cliente));
				FormCliente.getInstance().clearData();
				FormCliente.getInstance().close();
			} catch (ConflictException e1) {
				new MessageDialog().showMessages(e1.getMessage());
			}
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	private void onRegistrarVehiculo(ActionEvent e) {
		AltaDeVehiculoDTO vehiculoDeAlta = FormVehiculo.getInstance().getData();
		List<String> errors = vehiculoDeAlta.validate();
		if (errors.isEmpty()) {
			try {
				controllers.makeVehiculosConOrdenDeTrabajoController().save(idClientePresentado, vehiculoDeAlta);
				FormVehiculo.getInstance().close();
				view.clearDataListadoVehiculosCliente();
				view.setData(controllers.makeVehiculosConOrdenDeTrabajoController().readVehiculosConFichaTecnicaByIdCliente(idClientePresentado));
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
			AltaOrdenDeTrabajoDTO ordenDeTrabajo = FormAltaOrdenTrabajo.getInstance().getData();
			List<String> errors = ordenDeTrabajo.validate();
			if (errors.isEmpty()) {
				try {
					controllers.makeOrdenesDeTrabajoController().save(idVehiculo, ordenDeTrabajo);
					OrdenDeTrabajoDTO dto = controllers.makeOrdenesDeTrabajoController().readByIdVehiculo(idVehiculo);
					
					FichaTecnicaVehiculoDTO ficha = view.getDataFichaTechica();
					controllers.makeVehiculosController().updateKilometraje(ficha, ordenDeTrabajo);
					view.setUpdateKilometrajeOnFichaTecnica(ordenDeTrabajo.getKilometrajeActual());
					
					view.setData(dto);
					FormAltaOrdenTrabajo.getInstance().close();
				} catch (ForbiddenException | ConflictException | NotFoundException e1) {
					new MessageDialog().showMessages(e1.getMessage());
				}
			} else {
				new MessageDialog().showMessages(errors);
			}
		}
	}

	private void onDisplayFormForUpdate(ActionEvent a) {
		if (idClientePresentado != null) {
			FormCliente.getInstance().clearData();
			FormCliente.getInstance().setData(controllers.makeClientesController().readById(this.idClientePresentado));
			FormCliente.getInstance().display();
		} else {
			new MessageDialog().showMessages(CLIENTE_NO_SELECCIONADO);
		}
	}

	private void onUpdate(ActionEvent a) {
		AltaClienteDTO clienteAux = FormCliente.getInstance().getData();
		List<String> errores = clienteAux.validate();
		if (errores.isEmpty()) {
			ClienteDTO cliente = new ClienteDTO(clienteAux);
			cliente.setIdCliente(idClientePresentado);
			cliente.getDatosPersonalesDTO().setId(controllers.makeClientesController().readById(idClientePresentado).getIdDatosPersonales());
			controllers.makeClientesController().update(cliente);
			view.clearDataCliente();
			view.setData(cliente);
			FormCliente.getInstance().close();
		} else {
			new MessageDialog().showMessages(errores);
		}
	}
	
	private void onMostrarHistorialDeVehiculo(ActionEvent e) {
		Integer idVehiculo = view.getidVehiculoSeleccionado();
		if (idVehiculo != null) {
			List<OrdenDeTrabajoDTO> trabajos = controllers.makeOrdenesDeTrabajoController().readAllByIdVehiculo(idVehiculo);
			ReporteViewImpl reporte = new ReporteViewImpl();
			reporte.setDataTrabajos(trabajos);
			reporte.open();
		}
	}
}
