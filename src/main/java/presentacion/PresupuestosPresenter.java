package presentacion;

import java.awt.event.ActionEvent;
import business_logic.PresupuestosController;
import dto.PresupuestoDTO;
import dto.RepuestoPlanificadoDTO;
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
	
	private PresupuestoDTO nuevoPresupuesto;
	
	public PresupuestosPresenter(PresupuestosController controller) {
		this.altaPresupuestoView = AltaPresupuestoFormView.getInstance();
		this.planRepuestosView = PlanificarRepuestosFormView.getInstance();
		this.planTrabajosView = PlanificarTrabajosFormView.getInstance();
		this.agregarTrabajoFormView = AgregarTrabajoFormView.getInstance();
		
		this.altaPresupuestoView.setActionOnPlanificarRepuestos(a -> onDisplayForPlanRepuesto(a));
		this.altaPresupuestoView.setActionOnPlanificarTrabajos(a -> onDisplayForPlanTrabajos(a));
		this.altaPresupuestoView.setActionOnGuardar(a -> onGuardar(a));
		this.planTrabajosView.setActionOnAgregarTrabajo(a -> onDisplayForAgregarTrabajo(a));
		this.agregarTrabajoFormView.setActionOnGuardar(a -> onAgregarTrabajos(a));
		this.planRepuestosView.setActionOnAgregar(a -> onAgregarRepuesto(a));
		
		TecnicoControlView.getInstance().setActionPresupuestar(a -> onPresupuestar());//Provisorio, para test.
	}

	private void onGuardar(ActionEvent a) {
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

	private void onPresupuestar() {
		this.nuevoPresupuesto = new PresupuestoDTO();
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
