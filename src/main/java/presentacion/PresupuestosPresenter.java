package presentacion;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import business_logic.PresupuestosController;
import business_logic.RepuestosController;
import dto.PresupuestoDTO;
import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;
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
	
	private PresupuestosController presupuestosController;
	private RepuestosController repuestosController;
	
	public PresupuestosPresenter(PresupuestosController presupuestosController, RepuestosController repuestosController) {
		this.presupuestosController = presupuestosController;
		this.repuestosController = repuestosController;
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
		
		this.nuevoPresupuesto = new PresupuestoDTO();
	}

	
	private void onAceptarTrabajosPlanificados(ActionEvent a) {
		this.gestionPresupuestosView.setDataTrabajosPlanificados(nuevoPresupuesto.getTrabajos());
		this.planTrabajosView.close();
	}
	
	private void onAceptarRepuestosPlanificados(ActionEvent a) {
		this.gestionPresupuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
		this.planRepuestosView.close();
	}

	private void onCancelarRepuestosPlanificados(ActionEvent a) {
		this.nuevoPresupuesto.borrarRepuestosPlanificados();
	}

	private void onRegistrar(ActionEvent a) {
		presupuestosController.save(nuevoPresupuesto);//TODO no esta implementado en el controller
	}

	private void onAgregarRepuesto(ActionEvent a) {		
		String cantidad = planRepuestosView.getCantidad();
		String idRepuesto = planRepuestosView.getIdRepuesto();
		List<String>  errors = new StringValidator(idRepuesto)
				.number("Debe seleccionar un repuesto.").validate();
		errors.addAll(new StringValidator(cantidad)
				.notBlank("Debe ingresar una cantidad")
				.number("La cantidad debe ser un número").validate());
		
		if(errors.isEmpty()) {
			RepuestoPlanificadoDTO repuestoPlanificado = new RepuestoPlanificadoDTO();
			repuestoPlanificado.setCantRequerida(Integer.parseInt(cantidad));		
			RepuestoDTO repuesto = repuestosController.readById(Integer.parseInt(idRepuesto));
			repuestoPlanificado.setRepuesto(repuesto);
			nuevoPresupuesto.agregarRepuestos(repuestoPlanificado);
			planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
		} else {
			new ErrorDialog().showMessages(errors);;
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
}
