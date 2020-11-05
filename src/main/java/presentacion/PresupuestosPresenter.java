package presentacion;

import java.awt.event.ActionEvent;
import business_logic.PresupuestosController;
import dto.PresupuestoDTO;
import dto.RepuestoPlanificadoDTO;
import presentacion.views.AgregarTrabajoFormView;
import presentacion.views.AltaPresupuestoFormView;
import presentacion.views.PanelGestionPresupuestoView;
import presentacion.views.PlanificarRepuestosFormView;
import presentacion.views.PlanificarTrabajosFormView;
import presentacion.views.TecnicoControlView;

public class PresupuestosPresenter {
	
	private PanelGestionPresupuestoView gestionPresupuestosView;
	private PlanificarRepuestosFormView planRepuestosView;
	private PlanificarTrabajosFormView planTrabajosView;
	private AgregarTrabajoFormView agregarTrabajoFormView;
	
	private PresupuestoDTO nuevoPresupuesto;
	
	public PresupuestosPresenter(PresupuestosController controller) {
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
	}

	private void onRegistrar(ActionEvent a) {
		System.out.println("Registrar");
		// TODO Controller.save(nuevoPresupuesto)
	}

	private void onAgregarRepuesto(ActionEvent a) {
		System.out.println("Agregar Repuesto");
		RepuestoPlanificadoDTO repuesto = new RepuestoPlanificadoDTO();
		repuesto.setCantRequerida(planRepuestosView.getCantidad());
		int idRepuesto = planRepuestosView.getIdRepuesto();
		//TODO pedir al controlador el repuesto por id y repuesto.setRepuesto(consulta);
		nuevoPresupuesto.agregarRepuestos(repuesto);
	}

	private void onAgregarTrabajos(ActionEvent a) {
		System.out.println("Agregar Trabajo");
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
		System.out.println("Planrepuestos");
		this.planRepuestosView.clearData();
		this.planRepuestosView.display();
	}
	
	private void onDisplayForPlanTrabajos(ActionEvent a) {
		this.planTrabajosView.clearData();
		this.planTrabajosView.display();
	}	
}
