
package presentacion;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

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
import presentacion.views.gerente.HistorialVentasGerenteView;
import presentacion.views.utils.MessageDialog;
import presentacion.views.vendedor.HistorialVentasView;

public class HistorialVentasGerentePresenter {

	private HistorialVentasGerenteView view;
	private VentasVehiculosController ventasVehiculosController;
	

	public HistorialVentasGerentePresenter(VentasVehiculosController ventasVehiculosController) {

		this.ventasVehiculosController = ventasVehiculosController;
		this.view = HistorialVentasGerenteView.getInstance();
		

		this.view.setActionOnBuscar(a -> onCargar(a));

		
	}

	

	private void onCargar(ActionEvent a) {
	
		this.view.clear();
		
		 this.view.cargarTabla(this.ventasVehiculosController.readFechas(view.getVentaDesde(),view.getVentaHasta()));
		 
		
		 
	
	}

	


}