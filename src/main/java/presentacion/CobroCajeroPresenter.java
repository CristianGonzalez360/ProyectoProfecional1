package presentacion;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import business_logic.FacturasController;
import presentacion.views.cajero.PanelCobroCajeroView;

public class CobroCajeroPresenter {

	private PanelCobroCajeroView view;
	private FacturasController facturasController;

	public CobroCajeroPresenter(FacturasController facturasController) {
		
		this.facturasController = facturasController;
		this.view = PanelCobroCajeroView.getInstance();
		
		this.view.setActionOnBuscar(a -> onCargar(a));
		this.view.setActionOnRegistrar(a -> onRegistrar(a));
	}

	private void onCargar(ActionEvent a) {
		
		String nroFactura = view.getFactura();
		
		
		
		this.view.clear();
		
		if (nroFactura.isEmpty()) {
		this.view.cargarTabla(this.facturasController.readAll());
		
		}
		else {
			int i=Integer.parseInt(nroFactura);
			this.view.cargarTabla(this.facturasController.readByFactura(i));
		}
	}
	
	
	
	private void onRegistrar(ActionEvent a) {
		
		int idPresupuesto =this.view.getIdPresupuestoSeleccionada();
		if(idPresupuesto!=-1) {
			int resp =JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres pagar la factura seleccionada?", "Confirmación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(resp==0) {
				facturasController.updatePorPago(idPresupuesto);
				this.onCargar(a);
			}			
		}
	}
	
}
