package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import business_logic.ClientesController;
import business_logic.MantenimientosController;
import business_logic.OrdenesTrabajoController;
import business_logic.PresupuestosController;
import business_logic.RepuestosController;
import business_logic.VehiculosConOrdenDeTrabajoController;
import dto.ClienteDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.MantenimientoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.PresupuestoDTO;
import dto.taller.RepuestoDTO;
import dto.taller.RepuestoPlanificadoDTO;
import dto.taller.TrabajoPresupuestadoDTO;
import dto.taller.VehiculoConOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.tecnico.AltaPresupuestoFormView;
import presentacion.views.tecnico.PanelGestionPresupuestoView;
import presentacion.views.tecnico.PlanificarRepuestosFormView;
import presentacion.views.tecnico.PlanificarTrabajosFormView;
import presentacion.views.utils.MessageDialog;

public class PresupuestosPresenter {

	private PanelGestionPresupuestoView view;
	private AltaPresupuestoFormView altaPresupuesto;
	private PlanificarRepuestosFormView planRepuestosView;
	private PlanificarTrabajosFormView planTrabajosView;
	private PresupuestoDTO nuevoPresupuesto;
	private VehiculosConOrdenDeTrabajoController vehiculosController;
	private PresupuestosController presupuestosController;
	private RepuestosController repuestosController;
	private OrdenesTrabajoController ordenDeTrabajoController;
	private ClientesController clienteController;
	private MantenimientosController mantenimientosController;
	
	public PresupuestosPresenter(PresupuestosController presupuestosController, RepuestosController repuestosController,
			OrdenesTrabajoController ordenDetranajoController, VehiculosConOrdenDeTrabajoController vehiculoController,
			ClientesController clienteController, MantenimientosController mantenimientosController) {

		this.mantenimientosController = mantenimientosController;
		this.clienteController = clienteController;
		this.vehiculosController = vehiculoController;
		this.presupuestosController = presupuestosController;
		this.repuestosController = repuestosController;
		this.ordenDeTrabajoController = ordenDetranajoController;
		this.view = PanelGestionPresupuestoView.getInstance();
		this.altaPresupuesto = AltaPresupuestoFormView.getInstance();
		this.planRepuestosView = altaPresupuesto.getRepuestosPanel();
		this.planTrabajosView = altaPresupuesto.getTrabajosPanel();
		
		this.altaPresupuesto.setActionOnAceptar(a -> onRegistrar(a));
		this.altaPresupuesto.setActionOnCancelar(a -> onCancelar(a));
		
		this.view.setActionOnSeleccionarPresupuesto(a -> onSelecionarPresupuesto(a));
		this.view.setActionOnNuevoPresupuesto(a -> onNuevoPresupuesto(a));
		this.planTrabajosView.setActionOnAgregarTrabajo(a -> onAgregarTrabajos(a));
		this.planTrabajosView.setActionOnQuitarTrabajo(a -> onQuitarTrabajo(a));
		this.planRepuestosView.setActionOnAgregarRepuesto(a -> onAgregarRepuesto(a));
		this.planRepuestosView.setActionOnQuitarRepuesto(a -> onQuitarRepuesto(a));
		this.planRepuestosView.setActionOnBuscarRepuesto(a -> onBuscarRepuesto(a));
		
		this.altaPresupuesto.setActionOnMantenimiento(a -> onMantenimiento(a));
		this.altaPresupuesto.setActionOnSeleccionar(a -> onSeleccionar(a));
		this.view.setActionOnBuscar(a -> onBuscar(a));
		this.view.setActionSelectVehiculoCliente(a -> onSelectVehiculoDeCliente(a));
		this.altaPresupuesto.setActionOnClose(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				nuevoPresupuesto = null;
				altaPresupuesto.clearData();
				super.windowClosing(e);
			}
		});
	}
	
	private void onSeleccionar(ActionEvent a) {
		Integer id = altaPresupuesto.getMantenimiento();
		MantenimientoDTO mantenimiento = mantenimientosController.readByID(id);
		devolverStock();
		nuevoPresupuesto = new PresupuestoDTO(mantenimiento);
		Integer idOT = view.getIdOrdenDeTrabajo();
		nuevoPresupuesto.setIdOT(idOT);
		for(RepuestoPlanificadoDTO repuesto : nuevoPresupuesto.getRepuestos()) {
			RepuestoDTO r = repuesto.getRepuesto();
			r.setStockRepuesto(r.getStockRepuesto()-repuesto.getCantRequerida());
			repuestosController.update(r);
		}
		onBuscarRepuesto(a);
		this.altaPresupuesto.setData(nuevoPresupuesto);
	}

	private void onMantenimiento(ActionEvent a) {
		if(this.altaPresupuesto.esMantenimiento()) {
			this.altaPresupuesto.deshabilitarEdicion();
		} else {
			this.altaPresupuesto.habilitarEdicion();
		}
	}

	private void onCancelar(ActionEvent a) {
		devolverStock();
		nuevoPresupuesto = null;
		altaPresupuesto.clearData();
		altaPresupuesto.close();
	}
	
	private void devolverStock() {
		if(nuevoPresupuesto != null) {
			for (RepuestoPlanificadoDTO repuestoPlanificado : nuevoPresupuesto.getRepuestos()) {
				RepuestoDTO repuesto = repuestoPlanificado.getRepuesto();
				repuesto.setStockRepuesto(repuestoPlanificado.getCantRequerida() + repuesto.getStockRepuesto());
				repuestosController.update(repuesto);
			}
		}
	}

	//Quita un repuesto del presupuesto
	private void onQuitarRepuesto(ActionEvent a) {
		Integer fila = this.planRepuestosView.getSeleccionado();
		if(fila >= 0) {
			RepuestoPlanificadoDTO repuestoPlanificado = nuevoPresupuesto.getRepuestos().get(fila);
			RepuestoDTO repuesto = repuestosController.readById(repuestoPlanificado.getRepuesto().getIdRepuesto());
			repuesto.setStockRepuesto(repuestoPlanificado.getCantRequerida() + repuesto.getStockRepuesto());
			repuestosController.update(repuesto);
			onBuscarRepuesto(a);
			this.nuevoPresupuesto.quitarRepuesto(fila);
			this.planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
			this.altaPresupuesto.setPrecio(nuevoPresupuesto.getPrecio());
		}
	}

	//Quita un trabajo del presupuesto
	private void onQuitarTrabajo(ActionEvent a) {
		Integer fila = this.planTrabajosView.getSeleccionado();
		if(fila >= 0) {
			this.nuevoPresupuesto.quitarTrabajo(fila);
			this.planTrabajosView.setDataTrabajosPlanificados(nuevoPresupuesto.getTrabajos());
			this.altaPresupuesto.setPrecio(nuevoPresupuesto.getPrecio());
		}
	}

	//Crea un nuevo presupuesto y lo guarda
	private void onNuevoPresupuesto(ActionEvent a) {
		Integer idOT = view.getIdOrdenDeTrabajo();
		if(idOT != null) {
			nuevoPresupuesto = new PresupuestoDTO();
			nuevoPresupuesto.setIdOT(idOT);
			onDisplayForPlanRepuesto(a);
			onDisplayForPlanTrabajos(a);
			this.altaPresupuesto.setData(nuevoPresupuesto);
			mostrarMantenimientos();
			this.altaPresupuesto.display();
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
			if(marca == "Todas") {
				repuestos = repuestosController.readAll();
			} else {
				repuestos = repuestosController.readByMarca(marca);
			}
		} else {
			if(marca == "Todas") {
				repuestos = repuestosController.readByDescripcion(descripcion);
			} else {
				repuestos = repuestosController.readbyMarcaYDescripcion(marca, descripcion);
			}
		}
		planRepuestosView.setDataRepuestos(repuestos);
	}

	//registra los repuestos y trabajos planificados
	private void onRegistrar(ActionEvent a) {
		String comentario = altaPresupuesto.getComentario();
		if(!comentario.isEmpty() && !nuevoPresupuesto.getTrabajos().isEmpty() && !nuevoPresupuesto.getRepuestos().isEmpty()) {
			nuevoPresupuesto.setComentarioAltaPresu(comentario);
			presupuestosController.save(nuevoPresupuesto);
			nuevoPresupuesto = null;
			this.view.setDataPresupuestos(presupuestosController.readByIdOt(view.getIdOrdenDeTrabajo()));
			this.altaPresupuesto.close();
		} else {
			List<String> errors = new ArrayList<>();
			if(comentario.isEmpty()) {
				errors.add("Ingrece un comentario");
			}
			if(nuevoPresupuesto.getTrabajos().isEmpty()) {
				errors.add("Agregue al menos un trabajo");
			}
			if(nuevoPresupuesto.getRepuestos().isEmpty()) {
				errors.add("Agregue al menos un repuesto");
			}
			new MessageDialog().showMessages(errors);
		}
	}

	//Agrega repuesto a el nuevo repuesto
	private void onAgregarRepuesto(ActionEvent a) {
		String cantidad = planRepuestosView.getCantidad();
		String idRepuesto = planRepuestosView.getIdRepuesto();
		List<String> errors = new StringValidator(idRepuesto).number("Debe seleccionar un repuesto.").validate();
		errors.addAll(new StringValidator(cantidad).notBlank("Debe ingresar una cantidad.")
				.positiveInteger("La cantidad debe ser un número mayor a 0.").validate());
		if (errors.isEmpty()) {
			RepuestoDTO repuesto = repuestosController.readById(Integer.parseInt(idRepuesto));
			int cant = Integer.parseInt(cantidad);
			int stock = repuesto.getStockRepuesto();
			if(cant <= stock) {
				RepuestoPlanificadoDTO repuestoPlanificado = new RepuestoPlanificadoDTO();
				repuestoPlanificado.setCantRequerida(cant);
				repuestoPlanificado.setRepuesto(repuesto);
				repuesto.setStockRepuesto(stock - cant);
				repuestosController.update(repuesto);
				nuevoPresupuesto.agregarRepuestos(repuestoPlanificado);
				altaPresupuesto.setPrecio(nuevoPresupuesto.getPrecio());
				onBuscarRepuesto(a);
				planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
			} else {
				new MessageDialog().showMessages("No hay stock suficiente.");
			}
		} else {
			new MessageDialog().showMessages(errors);
		}
	}
	
	//Agrega trabajos al nuevo repuesto
	private void onAgregarTrabajos(ActionEvent a) {
		String descripcion = planTrabajosView.getDescripcion();
		String monto = planTrabajosView.getMonto();
		String esfuerzo = planTrabajosView.getEsfuerzo();
		List<String> errors = new StringValidator(descripcion).notBlank("Ingrese una descripción.").validate();
		errors.addAll(new StringValidator(monto).notBlank("Ingrese un monto.").positiveDouble("El monto debe ser un número mayor a 0.").validate());
		errors.addAll(new StringValidator(esfuerzo).notBlank("Ingrese un esfuerzo estimado.").positiveInteger("El esfuerzo debe ser un número entero mayor a 0.").validate());
		if(errors.isEmpty()) {
			TrabajoPresupuestadoDTO trabajo = this.planTrabajosView.getDataNuevoTrabajo();
			trabajo.setFechaAlta(new Date());
			nuevoPresupuesto.agregarTrabajo(trabajo);
			altaPresupuesto.setPrecio(nuevoPresupuesto.getPrecio());
			this.planTrabajosView.setDataTrabajosPlanificados(nuevoPresupuesto.getTrabajos());
			this.planTrabajosView.clearDataNuevoTrabajo();
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	//Muestra pantalla de planificacion de repuestos
	private void onDisplayForPlanRepuesto(ActionEvent a) {
		if (nuevoPresupuesto != null) {
			List<String> marcas = new ArrayList<>();
			marcas.add("Todas");
			marcas.addAll(repuestosController.readMarcas());
			this.planRepuestosView.setDataMarcas(marcas);
			this.planRepuestosView.setDataRepuestos(repuestosController.readAll());
			this.planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
			this.planRepuestosView.display();
		} else {
			new MessageDialog().showMessages("Seleccione un presupuesto");
		}
	}
	
	//Muestra pantalla de planificacion de trabajos
	private void onDisplayForPlanTrabajos(ActionEvent a) {
		if (nuevoPresupuesto != null) {
			this.planTrabajosView.clearData();
			this.planTrabajosView.setDataTrabajosPlanificados(nuevoPresupuesto.getTrabajos());
			this.planTrabajosView.display();
		} else {
			new MessageDialog().showMessages("Seleccione un presupuesto");
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
					List<PresupuestoDTO> presupuestos = this.presupuestosController.readByIdOt(ordenDeTrabajo.getIdOrdenTrabajo());
					view.setDataPresupuestos(presupuestos);
				} else {
					view.clearDataOrdenDeTrabajo();
					view.clearDataPresupuestos();
				}
			}
		}
	}
	
	private void mostrarMantenimientos() {
		List<MantenimientoDTO> datos = mantenimientosController.readAll();
		this.altaPresupuesto.setDataMantenimientos(datos);
	}
}
