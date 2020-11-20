package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import business_logic.ClientesController;
import business_logic.FacturasController;
import business_logic.OrdenesTrabajoController;
import business_logic.PresupuestosController;
import business_logic.RepuestosController;
import business_logic.VehiculosController;
import dto.ClienteDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.PresupuestoDTO;
import dto.RepuestoCompradoDTO;
import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.cajero.PanelCarritoRepuestoView;
import presentacion.views.supervisor.ClienteFormView;
import presentacion.views.tecnico.AltaPresupuestoFormView;
import presentacion.views.tecnico.PanelGestionPresupuestoView;
import presentacion.views.tecnico.PlanificarRepuestosFormView;
import presentacion.views.tecnico.PlanificarTrabajosFormView;
import presentacion.views.utils.MessageDialog;

public class CarritoPresenter {

	private PanelCarritoRepuestoView view;
	private AltaPresupuestoFormView altaPresupuesto;
	private PlanificarRepuestosFormView planRepuestosView;
	private PresupuestoDTO nuevoPresupuesto;
	private RepuestosController repuestosController;
	private ClientesController clienteController;
	private FacturasController facturasController;
	
	private List<RepuestoCompradoDTO> repuestos;

	public CarritoPresenter(RepuestosController repuestosController, ClientesController clientesController,
			FacturasController facturasController) {
		this.repuestosController = repuestosController;
		this.clienteController = clientesController;
		this.facturasController = facturasController;

		this.view = PanelCarritoRepuestoView.getInstance();
		this.altaPresupuesto = AltaPresupuestoFormView.getInstance();
		this.planRepuestosView = PlanificarRepuestosFormView.getInstance();

		this.altaPresupuesto.setActionOnCancelar(a -> onCancelar(a));
		this.planRepuestosView.setActionOnAgregar(a -> onAgregarRepuesto(a));
		this.planRepuestosView.setActionOnQuitar(a -> onQuitarRepuesto(a));
		this.planRepuestosView.setActionOnBuscar(a -> onBuscarRepuesto(a));

		this.view.setActionOnAgregarCliente(a -> onDisplayClienteForm(a));

		this.view.setActionOnBuscar(a -> onBuscar(a));

		repuestos = new ArrayList<>();
	}

	private void onDisplayClienteForm(ActionEvent a) {
		// TODO Auto-generated method stub
		ClienteFormView.getInstance().display();
		;
	}

	// Cuando se cancela, borra el presupuesto para que no quede vacio.
	private void onCancelar(ActionEvent a) {
		nuevoPresupuesto = null;
		altaPresupuesto.clearData();
		altaPresupuesto.close();
	}

	// Quita un repuesto del presupuesto
	private void onQuitarRepuesto(ActionEvent a) {
		Integer fila = this.planRepuestosView.getSeleccionado();
		if (fila >= 0) {
			RepuestoPlanificadoDTO repuestoPlanificado = nuevoPresupuesto.getRepuestos().get(fila);
			RepuestoDTO repuesto = repuestoPlanificado.getRepuesto();
			repuesto.setStockRepuesto(repuestoPlanificado.getCantRequerida() + repuesto.getStockRepuesto());
			repuestosController.update(repuesto);
			onBuscarRepuesto(a);
			this.nuevoPresupuesto.quitarRepuesto(fila);
			this.planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
			this.altaPresupuesto.setPrecio(nuevoPresupuesto.getPrecio());
		}
	}

	

	// Crea un nuevo presupuesto y lo guarda
//	private void onNuevoPresupuesto(ActionEvent a) {
////		Integer idOT = view.getIdOrdenDeTrabajo();
//		if(idOT != null) {
//				nuevoPresupuesto = new PresupuestoDTO();
//				nuevoPresupuesto.setIdOT(idOT);
//				onDisplayForPlanRepuesto(a);
//				onDisplayForPlanTrabajos(a);
//				this.altaPresupuesto.setData(nuevoPresupuesto);
//				this.altaPresupuesto.display();
//		}
//	}

	// Busca repuestos segun criterio seleccionado
	private void onBuscarRepuesto(ActionEvent a) {
		String marca = planRepuestosView.getMarca();
		String descripcion = planRepuestosView.getDescripcion();
		List<RepuestoDTO> repuestos;
		if (descripcion.isEmpty()) {
			if (marca == "Todas") {
				repuestos = repuestosController.readAll();
			} else {
				repuestos = repuestosController.readByMarca(marca);
			}
		} else {
			if (marca == "Todas") {
				repuestos = repuestosController.readByDescripcion(descripcion);
			} else {
				repuestos = repuestosController.readbyMarcaYDescripcion(marca, descripcion);
			}
		}
		planRepuestosView.setDataRepuestos(repuestos);
	}

//	//registra los repuestos y trabajos planificados
//	private void onRegistrar(ActionEvent a) {
//		String comentario = altaPresupuesto.getComentario();
//		if(!comentario.isEmpty() && !nuevoPresupuesto.getTrabajos().isEmpty() && !nuevoPresupuesto.getRepuestos().isEmpty()) {
//			nuevoPresupuesto.setComentarioAltaPresu(comentario);
//			presupuestosController.save(nuevoPresupuesto);
//			nuevoPresupuesto = null;
//			this.view.setDataPresupuestos(presupuestosController.readByIdOt(view.getIdOrdenDeTrabajo()));
//			this.altaPresupuesto.close();
//		} else {
//			List<String> errors = new ArrayList<>();
//			if(comentario.isEmpty()) {
//				errors.add("Ingrece un comentario");
//			}
//			if(nuevoPresupuesto.getTrabajos().isEmpty()) {
//				errors.add("Agregue al menos un trabajo");
//			}
//			if(nuevoPresupuesto.getRepuestos().isEmpty()) {
//				errors.add("Agregue al menos un repuesto");
//			}
//			new MessageDialog().showMessages(errors);
//		}
//	}

	// Agrega repuesto a el carrito
	private void onAgregarRepuesto(ActionEvent a) {
		String cantidad = view.getCantidad();
		String idRepuesto = view.getIdRepuesto();
		List<String> errors = new StringValidator(idRepuesto).number("Debe seleccionar un repuesto.").validate();
		errors.addAll(new StringValidator(cantidad).notBlank("Debe ingresar una cantidad.")
				.positiveInteger("La cantidad debe ser un número mayor a 0.").validate());
		if (errors.isEmpty()) {
			RepuestoDTO repuesto = repuestosController.readById(Integer.parseInt(idRepuesto));
			int cant = Integer.parseInt(cantidad);
			int stock = repuesto.getStockRepuesto();
			if (cant <= stock) {
				RepuestoCompradoDTO repuestoComprado = new RepuestoCompradoDTO();
				repuestoComprado.setCantRequerida(cant);
				repuestoComprado.setRepuesto(repuesto);
				repuesto.setStockRepuesto(stock - cant);
				repuestosController.update(repuesto);
				repuestos.add(repuestoComprado);
				//view.setPrecio(nuevoPresupuesto.getPrecio());
				onBuscarRepuesto(a);
				view.setDataRepuestosPlanificados(repuestos);
			} else {
				new MessageDialog().showMessages("No hay stock suficiente.");
			}
		} else {
			new MessageDialog().showMessages(errors);
			;
		}
	}

	// Carga los repuestos y las marcas
	private void cargarBuscadorRepuestos() {
		List<String> marcas = new ArrayList<>();
		marcas.add("Todas");
		marcas.addAll(repuestosController.readMarcas());
		this.view.setDataMarcas(marcas);
		this.view.setDataRepuestos(repuestosController.readAll());
	}

	// Busca vehiculos con orden de trabajo abiertas
	private void onBuscar(ActionEvent a) {
		view.clearClienteData();
		String inputDni = view.getDniCliente();
		if (new StringValidator(inputDni).number("").validate().isEmpty()) {
			ClienteDTO cliente = clienteController.readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				view.setDataCliente(cliente);
			}
		}
	}

	// Muestra la orden de trabajo del vehiculo seleccionado.
//	private void onSelectVehiculoDeCliente(ListSelectionEvent a) {
//		VehiculoConOrdenDeTrabajoDTO idVehiculo = view.getidVehiculoSeleccionado();
//		if (idVehiculo != null) {
//			FichaTecnicaVehiculoDTO fichaVehiculo = vehiculosController
//					.readFichaTecnicaById(idVehiculo.getIdFichaTecnica());
//			if (fichaVehiculo != null) {
//				view.clearDataFichaTecnicaVehiculo();
//				view.clearDataOrdenDeTrabajo();
//				view.setData(fichaVehiculo);
//				OrdenDeTrabajoDTO ordenDeTrabajo = this.ordenDeTrabajoController.readByIdVehiculo(idVehiculo.getId());				
//				if (ordenDeTrabajo != null) {
//					view.setData(ordenDeTrabajo);
//					List<PresupuestoDTO> presupuestos = this.presupuestosController.readByIdOt(ordenDeTrabajo.getIdOrdenTrabajo());
//					view.setDataPresupuestos(presupuestos);
//				} else {
//					view.clearDataOrdenDeTrabajo();
//					view.clearDataPresupuestos();
//				}
//			}
//		}
//	}
}
