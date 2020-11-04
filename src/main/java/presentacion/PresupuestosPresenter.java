package presentacion;

import java.awt.event.ActionEvent;

import business_logic.PresupuestosController;
import presentacion.views.AgregarTrabajoFormView;
import presentacion.views.AltaPresupuestoFormView;
import presentacion.views.PlanificarRepuestosFormView;
import presentacion.views.PlanificarTrabajosFormView;
import presentacion.views.TecnicoControlView;

public class PresupuestosPresenter {
	
	private AltaPresupuestoFormView altaPresupuestoView;
	private PlanificarRepuestosFormView planRepuestosView;
	private PlanificarTrabajosFormView planTrabajosView;
	private AgregarTrabajoFormView agregarTrabajoFormView;
	
	public PresupuestosPresenter(PresupuestosController controller) {
		this.altaPresupuestoView = AltaPresupuestoFormView.getInstance();
		this.planRepuestosView = PlanificarRepuestosFormView.getInstance();
		this.planTrabajosView = PlanificarTrabajosFormView.getInstance();
		this.agregarTrabajoFormView = AgregarTrabajoFormView.getInstance();
		
		this.altaPresupuestoView.setActionOnPlanificarRepuestos(a -> onDisplayForPlanRepuesto(a));
		this.altaPresupuestoView.setActionOnPlanificarTrabajos(a -> onDisplayForPlanTrabajos(a));
		this.planTrabajosView.setActionOnAgregarTrabajo(a -> onDisplayForAgregarTrabajo(a));
		TecnicoControlView.getInstance().setActionPresupuestar(a -> onPresupuestar());//Provisorio, para test.
	}

	private void onPresupuestar() {
		this.altaPresupuestoView.clearData();
		this.altaPresupuestoView.display();
	}

	private void onDisplayForAgregarTrabajo(ActionEvent a) {
		this.agregarTrabajoFormView.clearData();
		this.agregarTrabajoFormView.display();
	}

	private void onDisplayForPlanRepuesto(ActionEvent a) {
		this.planRepuestosView.clearData();
		this.planRepuestosView.display();
	}
	
	private void onDisplayForPlanTrabajos(ActionEvent a) {
		this.planTrabajosView.clearData();
		this.planTrabajosView.display();
	}
	
	
}
