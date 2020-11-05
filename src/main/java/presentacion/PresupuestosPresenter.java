package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.OrdenesTrabajoController;
import business_logic.PresupuestosController;
import business_logic.RepuestosController;
import dto.validators.Patterns;
import dto.OrdenDeTrabajoDTO;
import dto.PresupuestoDTO;
import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TurnoDTO;
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
	private OrdenesTrabajoController ordenDetrabajoController;
	
	public PresupuestosPresenter(PresupuestosController presupuestosController, RepuestosController repuestosController, OrdenesTrabajoController ordenDetranajoController) {
		this.presupuestosController = presupuestosController;
		this.repuestosController = repuestosController;
		this.ordenDetrabajoController = ordenDetranajoController;
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
		this.gestionPresupuestosView.setActionOnBuscar(a -> onBuscar(a));
	}


	private void onRegistrar(ActionEvent a) {
		System.out.println("Registrar");
		presupuestosController.save(nuevoPresupuesto);//TODO no esta implementado el controller
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
			RepuestoDTO repuesto = repuestosController.readById(Integer.parseInt(idRepuesto));//TODO no esta implementdo el controller
			//repuestoPlanificado.setRepuesto(repuesto);
			nuevoPresupuesto.agregarRepuestos(repuestoPlanificado);
			planRepuestosView.clearDataRepuestosPlanificados();
			planRepuestosView.setDataRepuestosPlanificados(nuevoPresupuesto.getRepuestos());
		} else {
			new ErrorDialog().showMessages(errors);;
		}
	}

	private void onAgregarTrabajos(ActionEvent a) {
		nuevoPresupuesto.agregarTrabajo(this.agregarTrabajoFormView.getData());
		this.planTrabajosView.clearData();
		this.planTrabajosView.setData(nuevoPresupuesto.getTrabajos());
		this.agregarTrabajoFormView.close();
	}

	private void onDisplayForAgregarTrabajo(ActionEvent a) {
		this.agregarTrabajoFormView.clearData();
		this.agregarTrabajoFormView.display();
	}

	private void onDisplayForPlanRepuesto(ActionEvent a) {
		this.planRepuestosView.clearDataRepuestos();
		this.planRepuestosView.setDataRepuestos(repuestosController.getAll());//TODO no esta implementado el controller
		this.planRepuestosView.display();
	}
	
	private void onDisplayForPlanTrabajos(ActionEvent a) {
		this.planTrabajosView.clearData();
		this.planTrabajosView.display();
	}	
	
	private void onBuscar(ActionEvent a) {
		System.out.println("hola");
		List<OrdenDeTrabajoDTO> ordenes = ordenDetrabajoController.readAll();
		for (OrdenDeTrabajoDTO orden : ordenes) {
			System.out.println(orden.getTipoOrdeTrabajo());
		}
	}
	
}
