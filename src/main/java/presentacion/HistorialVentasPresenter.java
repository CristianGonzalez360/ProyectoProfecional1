
package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import business_logic.FacturasController;
import business_logic.VentasVehiculosController;
import business_logic.exceptions.ConflictException;
import dto.TarjetaCreditoDTO;
import dto.TarjetaDebitoDTO;
import presentacion.views.cajero.BitcoinFormView;
import presentacion.views.cajero.MercadoPagoFormView;
import presentacion.views.cajero.PanelCobroCajeroView;
import presentacion.views.cajero.TarjetaCreditoFormView;
import presentacion.views.cajero.TarjetaDebitoFormView;
import presentacion.views.utils.MessageDialog;
import presentacion.views.vendedor.HistorialVentasView;

public class HistorialVentasPresenter {

	private HistorialVentasView view;
	private VentasVehiculosController ventasVehiculosController;
	

	public HistorialVentasPresenter(VentasVehiculosController ventasVehiculosController) {

		this.ventasVehiculosController = ventasVehiculosController;
		this.view = HistorialVentasView.getInstance();
		

		this.view.setActionOnBuscar(a -> onCargar(a));

		
	}

	

	private void onCargar(ActionEvent a) {
	
				 this.view.cargarTabla(this.ventasVehiculosController.readAll());
	
	}

	


}