package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ClientesController;
import business_logic.FacturasController;
import business_logic.RepuestosController;
import business_logic.exceptions.ConflictException;
import dto.AltaClienteDTO;
import dto.ClienteDTO;
import dto.PresupuestoDTO;
import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.cajero.PanelCarritoRepuestoView;
import presentacion.views.supervisor.ClienteFormView;
import presentacion.views.utils.MessageDialog;

public class CarritoPresenter {

	private static final String CLIENTE_NO_SELECCIONADO = "Debe seleccionar un cliente";

	
	private PanelCarritoRepuestoView planRepuestosView;
	private PresupuestoDTO nuevoPresupuesto;
	private RepuestosController repuestosController;
	private ClientesController clientesController;
	private FacturasController facturasController;
	private PanelCarritoRepuestoView view;
	private ClienteFormView clienteFormView;
	
	public CarritoPresenter(RepuestosController repuestosController, ClientesController clientesController, 
		FacturasController facturasController ) {
		this.repuestosController = repuestosController;
		this.clientesController = clientesController;
		this.facturasController = facturasController;
		this.view = PanelCarritoRepuestoView.getInstance();
		this.clienteFormView = clienteFormView.getInstance();
//		this.planRepuestosView = PanelCarritoRepuestoView.getInstance();
//		this.planRepuestosView.setActionOnAgregar(a -> onAgregarRepuesto(a));
//		this.planRepuestosView.setActionOnCancelar(a -> onCancelarRepuestosPlanificados(a));
//		this.planRepuestosView.setActionOnAceptar(a -> onAceptarRepuestosPlanificados(a));
//		this.planRepuestosView.setActionOnQuitar(a -> onQuitarRepuesto(a));
//		this.planRepuestosView.setActionOnBuscar(a -> onBuscarRepuesto(a));
		//------------------------------
		List<String> marcas = repuestosController.readMarcas();
		marcas.add("todas");
//		this.planRepuestosView.setDataMarcas(marcas);
//		this.planRepuestosView.setDataRepuestos(repuestosController.readAll());
		//-------------------------------------
//		this.view.setActionOnBuscar(a -> onBuscar(a));
//		this.view.setActionSelectVehiculoCliente(a -> onSelectVehiculoDeCliente(a));
		
		this.view.setActionRegistrarCliente(a -> onDisplayClienteFormView(a));
		view.setActionOnEditarCliente(a -> onDisplayFormForUpdate(a));
		view.setActionBuscar((a) -> onBuscar(a));
		ClienteFormView.getInstance().setActionOnUpdate(a -> onUpdate(a));

	}
	
	//Quita un repuesto del presupuesto
	private void onQuitarRepuesto(ActionEvent a) {
//		Integer fila = this.planRepuestosView.getSeleccionado();
//		if(fila >= 0) {
//			this.nuevoPresupuesto.quitarRepuesto(fila);
//			this.planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
//			this.view.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
//		}
	}

	//Busca repuestos segun criterio seleccionado
	private void onBuscarRepuesto(ActionEvent a) {
//		String marca = planRepuestosView.getMarca();
//		String descripcion = planRepuestosView.getDescripcion();
		List<RepuestoDTO> repuestos;
//		if(descripcion.isEmpty()) {
//			if(marca == "todas") {
//				repuestos = repuestosController.readAll();
//			} else {
//				repuestos = repuestosController.readByMarca(marca);
//			}
//		} else {
//			if(marca == "todas") {
//				repuestos = repuestosController.readByDescripcion(descripcion);
//			} else {
//				repuestos = repuestosController.readbyMarcaYDescripcion(marca, descripcion);
//			}
//		}
//		planRepuestosView.setDataRepuestos(repuestos);
	}

	//Muestra los repuestos planificados
	private void onAceptarRepuestosPlanificados(ActionEvent a) {
		//this.view.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
//		this.planRepuestosView.close();
	}

	//Borra los repuestos planificados si se cancela la operacion de planificacion
	private void onCancelarRepuestosPlanificados(ActionEvent a) {
		this.nuevoPresupuesto.borrarRepuestosPlanificados();
	}

	//Agrega repuesto a el nuevo repuesto
	private void onAgregarRepuesto(ActionEvent a) {
//		String cantidad = planRepuestosView.getCantidad();
//		String idRepuesto = planRepuestosView.getIdRepuesto();
//		List<String> errors = new StringValidator(idRepuesto).number("Debe seleccionar un repuesto.").validate();
//		errors.addAll(new StringValidator(cantidad).notBlank("Debe ingresar una cantidad")
//				.number("La cantidad debe ser un número").validate());
//
//		if (errors.isEmpty()) {
//			RepuestoPlanificadoDTO repuestoPlanificado = new RepuestoPlanificadoDTO();
//			repuestoPlanificado.setCantRequerida(Integer.parseInt(cantidad));
//			RepuestoDTO repuesto = repuestosController.readById(Integer.parseInt(idRepuesto));
//			repuestoPlanificado.setRepuesto(repuesto);
//			nuevoPresupuesto.agregarRepuestos(repuestoPlanificado);
//			planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
//			this.view.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
//		} else {
//			new MessageDialog().showMessages(errors);
//			;
//		}
	}

	//Muestra pantalla de planificacion de repuestos
	private void onDisplayForPlanRepuesto(ActionEvent a) {
//		if (nuevoPresupuesto != null) {
//			List<String> marcas = repuestosController.readMarcas();
//			marcas.add("todas");
//			this.planRepuestosView.setDataMarcas(marcas);
//			this.planRepuestosView.setDataRepuestos(repuestosController.readAll());
//			this.planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
//			this.planRepuestosView.display();
//		} else {
//			new MessageDialog().showMessages("Seleccione un presupuesto");
//		}
	}

//Supervisor reutilizados----------------------------------------------------------	
	private void onDisplayClienteFormView(ActionEvent e) {
		view.clearAll();
		ClienteFormView.getInstance().clearData();
		ClienteFormView.getInstance().display();
	}
	
	private void onRegistrarCliente(ActionEvent e) {
		AltaClienteDTO cliente = ClienteFormView.getInstance().getData();
		List<String> errors = cliente.validate();
		if (errors.isEmpty()) {
			try {
				clientesController.save(new ClienteDTO(cliente));
				ClienteFormView.getInstance().clearData();
				ClienteFormView.getInstance().close();
			} catch (ConflictException e1) {
				new MessageDialog().showMessages(e1.getMessage());
			}
		} else {
			new MessageDialog().showMessages(errors);
		}
	}	
	
	private void onDisplayFormForUpdate(ActionEvent a) {
		if (view.getIdCliente() != null) {
			ClienteFormView.getInstance().clearData();
			ClienteFormView.getInstance().setData(clientesController.readByDni(Integer.parseInt(view.getDniCliente())));
			ClienteFormView.getInstance().display();
		}
		else {
			new MessageDialog().showMessages(CLIENTE_NO_SELECCIONADO);
		}
	}
	
	private void onBuscar(ActionEvent a) {
		String inputDni = view.getDniCliente();
		if (new StringValidator(inputDni).number("").validate().isEmpty()) {
			ClienteDTO cliente = clientesController.readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				view.clearDataCliente();
				view.setData(cliente);
//				view.clearDataListadoVehiculosCliente();
//				view.clearDataFichaTecnicaVehiculo();
//				view.clearDataOrdenDeTrabajo();
			} else {
				view.clearAll();
			}
		} else {
			view.clearAll();
		}
	}
	
	private void onUpdate(ActionEvent a) {
		AltaClienteDTO clienteAux = ClienteFormView.getInstance().getData();
		List<String> errores = clienteAux.validate();
		if (errores.isEmpty()) {
			ClienteDTO cliente = new ClienteDTO(clienteAux);
			cliente.setIdCliente(view.getIdCliente());
			cliente.getDatosPersonalesDTO().setId(view.getIdDatosPersonalesCliente());
			clientesController.update(cliente);
			view.clearDataCliente();
			view.setData(cliente);
			ClienteFormView.getInstance().close();
		} else {
			new MessageDialog().showMessages(errores);
		}
	}
	
}
