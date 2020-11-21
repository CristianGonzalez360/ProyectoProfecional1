package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;
import business_logic.FacturasController;
import business_logic.exceptions.ConflictException;
import dto.AltaClienteDTO;
import dto.ClienteDTO;
import dto.TarjetaCreditoDTO;
import dto.TarjetaDebitoDTO;
import presentacion.views.cajero.BitcoinFormView;
import presentacion.views.cajero.MercadoPagoFormView;
import presentacion.views.cajero.PanelCobroCajeroView;
import presentacion.views.cajero.TarjetaCreditoFormView;
import presentacion.views.cajero.TarjetaDebitoFormView;
import presentacion.views.supervisor.ClienteFormView;
import presentacion.views.utils.MessageDialog;

public class CobroCajeroPresenter {

	private PanelCobroCajeroView view;
	private FacturasController facturasController;
	private TarjetaCreditoFormView viewCredito;
	private TarjetaDebitoFormView viewDebito;
	private MercadoPagoFormView viewMercadoPago;
	private BitcoinFormView viewBitcoin;

	public CobroCajeroPresenter(FacturasController facturasController) {
		
		this.facturasController = facturasController;
		this.view = PanelCobroCajeroView.getInstance();
		this.viewCredito = TarjetaCreditoFormView.getInstance();
		this.viewDebito = TarjetaDebitoFormView.getInstance();
		this.viewMercadoPago = MercadoPagoFormView.getInstance();
		this.viewBitcoin = BitcoinFormView.getInstance();
		
		
		this.view.setActionOnBuscar(a -> onCargar(a));
		
		this.view.setActionRegistrarTarjetaCredito((a) -> onDisplayTarjetaCreditoFormView(a));
		this.view.setActionRegistrarTarjetaDebito((a) -> onDisplayTarjetaDebitoFormView(a));
		this.view.setActionRegistrarMercadoPago((a) -> onDisplayMercadoPagoFormView(a));
		this.view.setActionRegistrarBitcoins((a) -> onDisplayBitcoinsFormView(a));
		
		
		this.viewCredito.setActionOnRegistrar(a -> onRegistrar(a));
		this.viewDebito.setActionOnRegistrar(a -> onRegistrarDebito(a));
		this.viewMercadoPago.setActionOnRegistrar(a -> onRegistrarMercadoPago(a));
		this.viewBitcoin.setActionOnRegistrar(a -> onRegistrarBitcoin(a));
		this.view.setActionRegistrarEfectivo(a -> onRegistrarEfectivo(a));
	}

	private void onDisplayTarjetaCreditoFormView(ActionEvent e) {
		
		TarjetaCreditoFormView.getInstance().clearData();
		TarjetaCreditoFormView.getInstance().display();
	}
	
	private void onDisplayTarjetaDebitoFormView(ActionEvent e) {
			
			TarjetaDebitoFormView.getInstance().clearData();
			TarjetaDebitoFormView.getInstance().display();
		}
	
	private void onDisplayMercadoPagoFormView(ActionEvent e) {
		
		MercadoPagoFormView.getInstance().clearData();
		MercadoPagoFormView.getInstance().display();
	}
	
	private void onDisplayBitcoinsFormView(ActionEvent e) {
		
		BitcoinFormView.getInstance().clearData();
		BitcoinFormView.getInstance().display();
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
		
		TarjetaCreditoDTO tarjeta = TarjetaCreditoFormView.getInstance().getData();
		List<String> errors = tarjeta.validate();
		if (errors.isEmpty()) {
			try {
					
		int idPresupuesto =this.view.getIdPresupuestoSeleccionada();
		String IdEstado = this.view.getEstadoSeleccionada();
		Double idTotal = this.view.getTotalSeleccionada();
		
		if(idPresupuesto!=-1) {
			int resp =JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres pagar la factura seleccionada por un total de: "+idTotal+" ?", "Confirmación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(resp==0 && IdEstado.equals("PAGA")) {
				JOptionPane.showMessageDialog(null, "La factura ya esta paga!","Error", JOptionPane.ERROR_MESSAGE);
			}			
			else if(resp==0) {
				facturasController.updatePorPago(idPresupuesto);
				this.onCargar(a);
				JOptionPane.showMessageDialog(null, "Operación realizada correctamente","Exito", JOptionPane.INFORMATION_MESSAGE);
				this.viewCredito.setVisible(false);
			}			
		}
		else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una factura","Error", JOptionPane.ERROR_MESSAGE);
		}
		
			} catch (ConflictException e1) {
				new MessageDialog().showMessages(e1.getMessage());
			}
		} else {
			new MessageDialog().showMessages(errors);
		}
		
	}
	
	
	
	private void onRegistrarDebito(ActionEvent a) {
		
		TarjetaDebitoDTO tarjeta = TarjetaDebitoFormView.getInstance().getData();
		List<String> errors = tarjeta.validate();
		if (errors.isEmpty()) {
			try {
		
		int idPresupuesto =this.view.getIdPresupuestoSeleccionada();
		String IdEstado = this.view.getEstadoSeleccionada();
		Double idTotal = this.view.getTotalSeleccionada();
		
		if(idPresupuesto!=-1) {
			int resp =JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres pagar la factura seleccionada por un total de: "+idTotal+" ?", "Confirmación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(resp==0 && IdEstado.equals("PAGA")) {
				JOptionPane.showMessageDialog(null, "La factura ya esta paga!","Error", JOptionPane.ERROR_MESSAGE);
			}			
			else if(resp==0) {
				facturasController.updatePorPago(idPresupuesto);
				this.onCargar(a);
				JOptionPane.showMessageDialog(null, "Operación realizada correctamente","Exito", JOptionPane.INFORMATION_MESSAGE);
				this.viewDebito.setVisible(false);
			}			
		}
		else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una factura","Error", JOptionPane.ERROR_MESSAGE);
		}
		
			} catch (ConflictException e1) {
				new MessageDialog().showMessages(e1.getMessage());
			}
		} else {
			new MessageDialog().showMessages(errors);
		}
		
	}
	
	
private void onRegistrarMercadoPago(ActionEvent a) {
		
		
		
		int idPresupuesto =this.view.getIdPresupuestoSeleccionada();
		String IdEstado = this.view.getEstadoSeleccionada();
		Double idTotal = this.view.getTotalSeleccionada();
		
		if(idPresupuesto!=-1) {
			int resp =JOptionPane.showOptionDialog(null, "¿Desea confirmar la recepcion a traves de Mercado Pago de: "+idTotal+" ?", "Confirmación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(resp==0 && IdEstado.equals("PAGA")) {
				JOptionPane.showMessageDialog(null, "La factura ya esta paga!","Error", JOptionPane.ERROR_MESSAGE);
			}			
			else if(resp==0) {
				facturasController.updatePorPago(idPresupuesto);
				this.onCargar(a);
				JOptionPane.showMessageDialog(null, "Operación realizada correctamente","Exito", JOptionPane.INFORMATION_MESSAGE);
				this.viewMercadoPago.setVisible(false);
			}			
		}
		else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una factura","Error", JOptionPane.ERROR_MESSAGE);
		}
		
			} 
		
private void onRegistrarBitcoin(ActionEvent a) {
	
	
	
	int idPresupuesto =this.view.getIdPresupuestoSeleccionada();
	String IdEstado = this.view.getEstadoSeleccionada();
	Double idTotal = this.view.getTotalSeleccionada();
	
	if(idPresupuesto!=-1) {
		int resp =JOptionPane.showOptionDialog(null, "¿Desea confirmar la recepcion a traves de Bitcoins de: "+idTotal+" ?", "Confirmación",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(resp==0 && IdEstado.equals("PAGA")) {
			JOptionPane.showMessageDialog(null, "La factura ya esta paga!","Error", JOptionPane.ERROR_MESSAGE);
		}			
		else if(resp==0) {
			facturasController.updatePorPago(idPresupuesto);
			this.onCargar(a);
			JOptionPane.showMessageDialog(null, "Operación realizada correctamente","Exito", JOptionPane.INFORMATION_MESSAGE);
			this.viewBitcoin.setVisible(false);
		}			
	}
	else {
		JOptionPane.showMessageDialog(null, "Debe seleccionar una factura","Error", JOptionPane.ERROR_MESSAGE);
	}
	
		} 
	
private void onRegistrarEfectivo(ActionEvent a) {
	
	
	
	int idPresupuesto =this.view.getIdPresupuestoSeleccionada();
	String IdEstado = this.view.getEstadoSeleccionada();
	Double idTotal = this.view.getTotalSeleccionada();
	
	if(idPresupuesto!=-1) {
		int resp =JOptionPane.showOptionDialog(null, "¿Desea confirmar el pago de: "+idTotal+" ?", "Confirmación",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(resp==0 && IdEstado.equals("PAGA")) {
			JOptionPane.showMessageDialog(null, "La factura ya esta paga!","Error", JOptionPane.ERROR_MESSAGE);
		}			
		else if(resp==0) {
			facturasController.updatePorPago(idPresupuesto);
			this.onCargar(a);
			JOptionPane.showMessageDialog(null, "Operación realizada correctamente","Exito", JOptionPane.INFORMATION_MESSAGE);
			this.viewBitcoin.setVisible(false);
		}			
	}
	else {
		JOptionPane.showMessageDialog(null, "Debe seleccionar una factura","Error", JOptionPane.ERROR_MESSAGE);
	}
	
		} 	
}
