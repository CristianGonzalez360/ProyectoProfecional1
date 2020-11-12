package presentacion;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import business_logic.PresupuestosController;
import presentacion.views.tecnico.PanelRegistrarTrabajoRealizadoView;

public class GestionTrabajosPresenter {

	private PanelRegistrarTrabajoRealizadoView view;
	private PresupuestosController presupuestoController;

	public GestionTrabajosPresenter(PresupuestosController presupuestoController) {
		
		this.presupuestoController = presupuestoController;
		this.view = PanelRegistrarTrabajoRealizadoView.getInstance();
		
		this.view.setActionOnBuscar(a -> onCargar(a));
		this.view.setActionOnRegistrar(a -> onRegistrar(a));
	}

	private void onCargar(ActionEvent a) {
		this.view.clear();
		this.view.cargarTabla(this.presupuestoController.readAll());
	}
	
	private void onRegistrar(ActionEvent a) {
		
		int idPresupuesto =this.view.getIdPresupuestoSeleccionada();
		if(idPresupuesto!=-1) {
			int resp =JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres registrar el presupuesto realizado?", "Confirmación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(resp==0) {
				presupuestoController.updateEstadoPresupuesto(idPresupuesto);
				this.onCargar(a);
			}			
		}
	}
	
}
