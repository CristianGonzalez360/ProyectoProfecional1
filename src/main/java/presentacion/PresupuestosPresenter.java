package presentacion;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.ErrorManager;

import javax.swing.event.ListSelectionEvent;
import business_logic.ClientesController;
import business_logic.OrdenesTrabajoController;
import business_logic.PresupuestosController;
import business_logic.RepuestosController;
import business_logic.VehiculosController;
import dto.ClienteDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.PresupuestoDTO;
import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.InputComentarioDialog;
import presentacion.views.PanelGestionPresupuestoView;
import presentacion.views.PlanificarRepuestosFormView;
import presentacion.views.PlanificarTrabajosFormView;
import presentacion.views.utils.ErrorDialog;

public class PresupuestosPresenter {

	private PanelGestionPresupuestoView view;
	private PlanificarRepuestosFormView planRepuestosView;
	private PlanificarTrabajosFormView planTrabajosView;
	private PresupuestoDTO nuevoPresupuesto;
	private VehiculosController vehiculosController;
	private PresupuestosController presupuestosController;
	private RepuestosController repuestosController;
	private OrdenesTrabajoController ordenDeTrabajoController;
	private ClientesController clienteController;

	public PresupuestosPresenter(PresupuestosController presupuestosController, RepuestosController repuestosController,
			OrdenesTrabajoController ordenDetranajoController, VehiculosController vehiculoController,
			ClientesController clienteController) {

		this.clienteController = clienteController;
		this.vehiculosController = vehiculoController;
		this.presupuestosController = presupuestosController;
		this.repuestosController = repuestosController;
		this.ordenDeTrabajoController = ordenDetranajoController;
		this.view = PanelGestionPresupuestoView.getInstance();
		this.planRepuestosView = PlanificarRepuestosFormView.getInstance();
		this.planTrabajosView = PlanificarTrabajosFormView.getInstance();

		this.view.setActionOnPlanificarRepuestos(a -> onDisplayForPlanRepuesto(a));
		this.view.setActionOnPlanificarTrabajos(a -> onDisplayForPlanTrabajos(a));
		this.view.setActionOnRegistrarPresupuesto(a -> onRegistrar(a));
		this.view.setActionOnSeleccionarPresupuesto(a -> onSelecionarPresupuesto(a));
		this.view.setActionOnNuevoPresupuesto(a -> onNuevoPresupuesto(a));
		this.planTrabajosView.setActionOnAgregar(a -> onAgregarTrabajos(a));
		this.planTrabajosView.setActionOnQuitar(a -> onQuitarTrabajo(a));
		this.planRepuestosView.setActionOnAgregar(a -> onAgregarRepuesto(a));
		this.planRepuestosView.setActionOnCancelar(a -> onCancelarRepuestosPlanificados(a));
		this.planRepuestosView.setActionOnAceptar(a -> onAceptarRepuestosPlanificados(a));
		this.planRepuestosView.setActionOnQuitar(a -> onQuitarRepuesto(a));
		this.planTrabajosView.setActionOnAceptar(a -> onAceptarTrabajosPlanificados(a));
		this.planRepuestosView.setActionOnBuscar(a -> onBuscarRepuesto(a));

		this.view.setActionOnBuscar(a -> onBuscar(a));
		this.view.setActionSelectVehiculoCliente(a -> onSelectVehiculoDeCliente(a));
	}
	
	//Quita un repuesto del presupuesto
	private void onQuitarRepuesto(ActionEvent a) {
		Integer fila = this.planRepuestosView.getSeleccionado();
		if(fila >= 0) {
			this.nuevoPresupuesto.quitarRepuesto(fila);
			this.planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
		}
	}

	//Quita un trabajo del presupuesto
	private void onQuitarTrabajo(ActionEvent a) {
		Integer fila = this.planTrabajosView.getSeleccionado();
		if(fila >= 0) {
			this.nuevoPresupuesto.quitarTrabajo(fila);
			this.planTrabajosView.setDataTrabajosPlanificados(nuevoPresupuesto.getTrabajos());
		}
	}

	//Crea un nuevo presupuesto y lo guarda
	private void onNuevoPresupuesto(ActionEvent a) {
		Integer idOT = view.getIdOrdenDeTrabajo();
		if(idOT != null) {
			String comentario = new InputComentarioDialog()
					.title("Ingrese un comentario")
					.open();
			PresupuestoDTO presupuesto = new PresupuestoDTO();
			presupuesto.setIdOT(idOT);
			presupuesto.setComentarioAltaPresu(comentario);
			presupuestosController.save(presupuesto);
			view.setDataPresupuestos(presupuestosController.readByIdOt(idOT));
		}
	}

	//Muestra datos presupuesto seleccionado
	private void onSelecionarPresupuesto(ListSelectionEvent a) {
		if(view.getIdPresupuesto() >= 0) {
			this.nuevoPresupuesto = presupuestosController.readById(view.getIdPresupuesto());
			view.setDataPresupuesto(nuevoPresupuesto);
		}
	}

	//Busca repuestos segun criterio seleccionado
	private void onBuscarRepuesto(ActionEvent a) {
		String marca = planRepuestosView.getMarca();
		String descripcion = planRepuestosView.getDescripcion();
		List<RepuestoDTO> repuestos;
		if(descripcion.isEmpty()) {
			if(marca == "todas") {
				repuestos = repuestosController.readAll();
			} else {
				repuestos = repuestosController.readByMarca(marca);
			}
		} else {
			if(marca == "todas") {
				repuestos = repuestosController.readByDescripcion(descripcion);
			} else {
				repuestos = repuestosController.readbyMarcaYDescripcion(marca, descripcion);
			}
		}
		planRepuestosView.setDataRepuestos(repuestos);
	}

	//Muestra los trabajos planificados
	private void onAceptarTrabajosPlanificados(ActionEvent a) {
		this.view.setDataTrabajosPlanificados(nuevoPresupuesto.getTrabajos());
		this.planTrabajosView.close();
	}

	//Muestra los repuestos planificados
	private void onAceptarRepuestosPlanificados(ActionEvent a) {
		this.view.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
		this.planRepuestosView.close();
	}

	//Borra los repuestos planificados si se cancela la operacion de planificacion
	private void onCancelarRepuestosPlanificados(ActionEvent a) {
		this.nuevoPresupuesto.borrarRepuestosPlanificados();
	}

	//registra los repuestos y trabajos planificados
	private void onRegistrar(ActionEvent a) {
		presupuestosController.update2(nuevoPresupuesto);
		this.view.setDataPresupuestos(presupuestosController.readByIdOt(view.getIdOrdenDeTrabajo()));
	}

	//Agrega repuesto a el nuevo repuesto
	private void onAgregarRepuesto(ActionEvent a) {
		String cantidad = planRepuestosView.getCantidad();
		String idRepuesto = planRepuestosView.getIdRepuesto();
		List<String> errors = new StringValidator(idRepuesto).number("Debe seleccionar un repuesto.").validate();
		errors.addAll(new StringValidator(cantidad).notBlank("Debe ingresar una cantidad")
				.number("La cantidad debe ser un número").validate());

		if (errors.isEmpty()) {
			RepuestoPlanificadoDTO repuestoPlanificado = new RepuestoPlanificadoDTO();
			repuestoPlanificado.setCantRequerida(Integer.parseInt(cantidad));
			RepuestoDTO repuesto = repuestosController.readById(Integer.parseInt(idRepuesto));
			repuestoPlanificado.setRepuesto(repuesto);
			nuevoPresupuesto.agregarRepuestos(repuestoPlanificado);
			planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
		} else {
			new ErrorDialog().showMessages(errors);
			;
		}
	}

	//Agrega trabajos al nuevo repuesto
	private void onAgregarTrabajos(ActionEvent a) {
		TrabajoPresupuestadoDTO trabajo = this.planTrabajosView.getDataNuevoTrabajo();
		trabajo.setFechaAlta(new Date());
		nuevoPresupuesto.agregarTrabajo(trabajo);
		this.planTrabajosView.setDataTrabajosPlanificados(nuevoPresupuesto.getTrabajos());
	}

	//Muestra pantalla de planificacion de repuestos
	private void onDisplayForPlanRepuesto(ActionEvent a) {
		if (nuevoPresupuesto != null) {
			List<String> marcas = repuestosController.readMarcas();
			marcas.add("todas");
			this.planRepuestosView.setDataMarcas(marcas);
			this.planRepuestosView.setDataRepuestos(repuestosController.readAll());
			this.planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
			this.planRepuestosView.display();
		} else {
			new ErrorDialog().showMessages("Seleccione un presupuesto");
		}
	}
	
	//Muestra pantalla de planificacion de trabajos
	private void onDisplayForPlanTrabajos(ActionEvent a) {
		if (nuevoPresupuesto != null) {
			this.planTrabajosView.clearData();
			this.planTrabajosView.setDataTrabajosPlanificados(nuevoPresupuesto.getTrabajos());
			this.planTrabajosView.display();
		} else {
			new ErrorDialog().showMessages("Seleccione un presupuesto");
		}
	}

	//Busca vehiculos con orden de trabajo abiertas
	private void onBuscar(ActionEvent a) {
		view.clearAll();
		String inputDni = view.getTxtDni();
		if (new StringValidator(inputDni).number("").validate().isEmpty()) {
			ClienteDTO cliente = clienteController.readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				List<VehiculoConOrdenDeTrabajoDTO> vehiculos = vehiculosController.readVehicleWithClientIdWhereOtIsOpen(cliente.getIdCliente());
				view.setData(vehiculos);
			}
		}
	}

	//Muestra la orden de trabajo del vehiculo seleccionado.
	private void onSelectVehiculoDeCliente(ListSelectionEvent a) {
		Integer idVehiculo = view.getidVehiculoSeleccionado();
		if (idVehiculo != null) {
			FichaTecnicaVehiculoDTO fichaVehiculo = vehiculosController.readFichaTecnicaById(idVehiculo);
			if (fichaVehiculo != null) {
				view.clearDataFichaTecnicaVehiculo();
				view.clearDataOrdenDeTrabajo();
				view.setData(fichaVehiculo);
				OrdenDeTrabajoDTO ordenDeTrabajo = this.ordenDeTrabajoController.readByIdVehiculo(idVehiculo);				
				if (ordenDeTrabajo != null) {
					view.setData(ordenDeTrabajo);
					List<PresupuestoDTO> presupuestos = this.presupuestosController.readByIdOt(ordenDeTrabajo.getIdOrdenTrabajo());
					view.setDataPresupuestos(presupuestos);
				} else {
					view.clearDataOrdenDeTrabajo();
					view.clearDataPresupuestos();
				}
			}
		}
	}
}
