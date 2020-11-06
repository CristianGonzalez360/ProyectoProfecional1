package presentacion;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import presentacion.views.AgregarTrabajoFormView;
import presentacion.views.PanelGestionPresupuestoView;
import presentacion.views.PlanificarRepuestosFormView;
import presentacion.views.PlanificarTrabajosFormView;
import presentacion.views.utils.ErrorDialog;

public class PresupuestosPresenter {

	private PanelGestionPresupuestoView gestionPresupuestosView;
	private PlanificarRepuestosFormView planRepuestosView;
	private PlanificarTrabajosFormView planTrabajosView;
	private AgregarTrabajoFormView agregarTrabajoFormView;
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
		this.gestionPresupuestosView = PanelGestionPresupuestoView.getInstance();
		this.planRepuestosView = PlanificarRepuestosFormView.getInstance();
		this.planTrabajosView = PlanificarTrabajosFormView.getInstance();
		this.agregarTrabajoFormView = AgregarTrabajoFormView.getInstance();

		this.gestionPresupuestosView.setActionOnPlanificarRepuestos(a -> onDisplayForPlanRepuesto(a));
		this.gestionPresupuestosView.setActionOnPlanificarTrabajos(a -> onDisplayForPlanTrabajos(a));
		this.gestionPresupuestosView.setActionOnRegistrarPresupuesto(a -> onRegistrar(a));
		this.planTrabajosView.setActionOnAgregarTrabajo(a -> onDisplayForAgregarTrabajo(a));
		this.agregarTrabajoFormView.setActionOnGuardar(a -> onAgregarTrabajos(a));
		this.planRepuestosView.setActionOnAgregar(a -> onAgregarRepuesto(a));
		this.planRepuestosView.setActionOnCancelar(a -> onCancelarRepuestosPlanificados(a));
		this.planRepuestosView.setActionOnAceptar(a -> onAceptarRepuestosPlanificados(a));
		this.planTrabajosView.setActionOnAceptar(a -> onAceptarTrabajosPlanificados(a));

		this.gestionPresupuestosView.setActionOnBuscar(a -> onBuscar(a));
		this.gestionPresupuestosView.setActionSelectVehiculoCliente(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				onSelectVehiculoDeCliente();
			}
		});

		this.nuevoPresupuesto = new PresupuestoDTO();
	}

	private void onAceptarTrabajosPlanificados(ActionEvent a) {
		this.gestionPresupuestosView.setDataTrabajosPlanificados(nuevoPresupuesto.getTrabajos());
		if (!nuevoPresupuesto.getTrabajos().isEmpty()) {
			this.gestionPresupuestosView.habilitarBotonRegistrar();
		} else {
			this.gestionPresupuestosView.deshabilitarBotonRegistrar();
		}
		this.planTrabajosView.close();
	}

	private void onAceptarRepuestosPlanificados(ActionEvent a) {
		this.gestionPresupuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
		if (!nuevoPresupuesto.getRepuestos().isEmpty()) {
			this.gestionPresupuestosView.habilitarBotonRegistrar();
		} else {
			this.gestionPresupuestosView.deshabilitarBotonRegistrar();
		}
		this.planRepuestosView.close();
	}

	private void onCancelarRepuestosPlanificados(ActionEvent a) {
		this.nuevoPresupuesto.borrarRepuestosPlanificados();
	}

	private void onRegistrar(ActionEvent a) {
		nuevoPresupuesto.setIdOT(gestionPresupuestosView.getIdOrdenDetrabajo());
		presupuestosController.save(nuevoPresupuesto);
	}

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

	private void onAgregarTrabajos(ActionEvent a) {
		TrabajoPresupuestadoDTO trabajo = this.agregarTrabajoFormView.getData();
		trabajo.setFechaAlta(new Date());
		nuevoPresupuesto.agregarTrabajo(trabajo);
		this.planTrabajosView.setData(nuevoPresupuesto.getTrabajos());
		this.agregarTrabajoFormView.close();
	}

	private void onDisplayForAgregarTrabajo(ActionEvent a) {
		this.agregarTrabajoFormView.clearData();
		this.agregarTrabajoFormView.display();
	}

	private void onDisplayForPlanRepuesto(ActionEvent a) {
		this.planRepuestosView.clearDataRepuestos();
		this.planRepuestosView.setDataRepuestos(repuestosController.readAll());
		this.planRepuestosView.display();
	}

	private void onDisplayForPlanTrabajos(ActionEvent a) {
		this.planTrabajosView.clearData();
		this.planTrabajosView.display();
	}

	private void onBuscar(ActionEvent a) {
		String inputDni = gestionPresupuestosView.getTxtDni();
		if (new StringValidator(inputDni).number("").validate().isEmpty()) {
			ClienteDTO cliente = clienteController.readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				List<VehiculoConOrdenDeTrabajoDTO> vehiculos = vehiculosController
						.readByIdCliente(cliente.getIdCliente());
				gestionPresupuestosView.clearDataListadoVehiculosCliente();
				gestionPresupuestosView.setData(vehiculos);
				gestionPresupuestosView.clearDataFichaTecnicaVehiculo();
				gestionPresupuestosView.clearDataOrdenDeTrabajo();
			} else {
				gestionPresupuestosView.clearAll();
			}
		} else {
			gestionPresupuestosView.clearAll();
		}
	}

	private void onSelectVehiculoDeCliente() {
		Integer idVehiculo = gestionPresupuestosView.getidVehiculoSeleccionado();
		if (idVehiculo != null) {
			FichaTecnicaVehiculoDTO fichaVehiculo = vehiculosController.readFichaTecnicaById(idVehiculo);
			if (fichaVehiculo != null) {
				gestionPresupuestosView.clearDataFichaTecnicaVehiculo();
				gestionPresupuestosView.clearDataOrdenDeTrabajo();
				gestionPresupuestosView.setData(fichaVehiculo);
				OrdenDeTrabajoDTO ordenDeTrabajo = this.ordenDeTrabajoController.readByIdVehiculo(idVehiculo);
				if (ordenDeTrabajo != null) {
					gestionPresupuestosView.setData(ordenDeTrabajo);
				} else {
					gestionPresupuestosView.clearDataOrdenDeTrabajo();
				}
			}
		}
	}
}
