
package presentacion;

import java.awt.event.ActionEvent;

import business_logic.FacturasController;
import presentacion.views.cajero.BitcoinFormView;
import presentacion.views.cajero.MercadoPagoFormView;
import presentacion.views.cajero.TarjetaCreditoFormView;
import presentacion.views.cajero.TarjetaDebitoFormView;
import presentacion.views.gerente.PanelReportes;
import presentacion.views.utils.ReporteViewImpl;
import presentacion.views.utils.TicketReport;

public class ReportesPresenter {

	private PanelReportes view;
	private FacturasController facturasController;
//	private TarjetaCreditoFormView viewCredito;
//	private TarjetaDebitoFormView viewDebito;
//	private MercadoPagoFormView viewMercadoPago;
//	private BitcoinFormView viewBitcoin;

	public ReportesPresenter(FacturasController facturasController) {

		this.facturasController = facturasController;
		this.view = PanelReportes.getInstance();
//		this.viewCredito = TarjetaCreditoFormView.getInstance();
//		this.viewDebito = TarjetaDebitoFormView.getInstance();
//		this.viewMercadoPago = MercadoPagoFormView.getInstance();
//		this.viewBitcoin = BitcoinFormView.getInstance();


		this.view.setActionDisplayIngresosDiarios((a) -> onDisplayTarjetaCreditoFormView(a));
		this.view.setActionDisplayEgresosDiarios((a) -> onDisplayTarjetaDebitoFormView(a));
		this.view.setActionDisplayIngresoMensual((a) -> onDisplayMercadoPagoFormView(a));
		this.view.setActionDisplayIngresoSemanal((a) -> onDisplayBitcoinsFormView(a));
		this.view.setActionDisplayreportes((a) -> onDisplayBitcoinsFormView(a));

//		this.viewCredito.setActionOnRegistrar(a -> onRegistrar(a));
//		this.viewDebito.setActionOnRegistrar(a -> onRegistrarDebito(a));
//		this.viewMercadoPago.setActionOnRegistrar(a -> onRegistrarMercadoPago(a));
//		this.viewBitcoin.setActionOnRegistrar(a -> onRegistrarBitcoin(a));
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

//	private void onRegistrar(ActionEvent a) {
//		TarjetaCreditoDTO tarjeta = TarjetaCreditoFormView.getInstance().getData();
//		List<String> errors = tarjeta.validate();
//		if (errors.isEmpty()) {
//			try {
//
//				int idPresupuesto = this.view.getIdPresupuestoSeleccionada();
//				String IdEstado = this.view.getEstadoSeleccionada();
//				Double idTotal = this.view.getTotalSeleccionada();
//
//				if (idPresupuesto != -1) {
//					int resp = JOptionPane.showOptionDialog(null,
//							"¿Estás seguro que quieres pagar la factura seleccionada por un total de: " + idTotal
//									+ " ?",
//							"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
//					if (resp == 0 && IdEstado.equals("PAGA")) {
//						JOptionPane.showMessageDialog(null, "La factura ya esta paga!", "Error",
//								JOptionPane.ERROR_MESSAGE);
//					} else if (resp == 0) {
//						facturasController.updatePorPago(idPresupuesto);
//						this.onCargar(a);
//						mostrarTicket(idPresupuesto, "Tarjeta de Crédito");
//						JOptionPane.showMessageDialog(null, "Operación realizada correctamente", "Exito",
//								JOptionPane.INFORMATION_MESSAGE);
//						this.viewCredito.setVisible(false);
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "Debe seleccionar una factura", "Error",
//							JOptionPane.ERROR_MESSAGE);
//				}
//
//			} catch (ConflictException e1) {
//				new MessageDialog().showMessages(e1.getMessage());
//			}
//		} else {
//			new MessageDialog().showMessages(errors);
//		}
//	}
//
//	private void onRegistrarDebito(ActionEvent a) {
//		TarjetaDebitoDTO tarjeta = TarjetaDebitoFormView.getInstance().getData();
//		List<String> errors = tarjeta.validate();
//		if (errors.isEmpty()) {
//			try {
//
//				int idPresupuesto = this.view.getIdPresupuestoSeleccionada();
//				String IdEstado = this.view.getEstadoSeleccionada();
//				Double idTotal = this.view.getTotalSeleccionada();
//
//				if (idPresupuesto != -1) {
//					int resp = JOptionPane.showOptionDialog(null,
//							"¿Estás seguro que quieres pagar la factura seleccionada por un total de: " + idTotal
//									+ " ?",
//							"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
//					if (resp == 0 && IdEstado.equals("PAGA")) {
//						JOptionPane.showMessageDialog(null, "La factura ya esta paga!", "Error",
//								JOptionPane.ERROR_MESSAGE);
//					} else if (resp == 0) {
//						facturasController.updatePorPago(idPresupuesto);
//						this.onCargar(a);
//						mostrarTicket(idPresupuesto, "Tarjeta de Debito");
//						JOptionPane.showMessageDialog(null, "Operación realizada correctamente", "Exito",
//								JOptionPane.INFORMATION_MESSAGE);
//						this.viewDebito.setVisible(false);
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "Debe seleccionar una factura", "Error",
//							JOptionPane.ERROR_MESSAGE);
//				}
//
//			} catch (ConflictException e1) {
//				new MessageDialog().showMessages(e1.getMessage());
//			}
//		} else {
//			new MessageDialog().showMessages(errors);
//		}
//	}
//
//	private void onRegistrarMercadoPago(ActionEvent a) {
//		int idPresupuesto = this.view.getIdPresupuestoSeleccionada();
//		String IdEstado = this.view.getEstadoSeleccionada();
//		Double idTotal = this.view.getTotalSeleccionada();
//
//		if (idPresupuesto != -1) {
//			int resp = JOptionPane.showOptionDialog(null,
//					"¿Desea confirmar la recepcion a traves de Mercado Pago de: " + idTotal + " ?", "Confirmación",
//					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
//			if (resp == 0 && IdEstado.equals("PAGA")) {
//				JOptionPane.showMessageDialog(null, "La factura ya esta paga!", "Error", JOptionPane.ERROR_MESSAGE);
//			} else if (resp == 0) {
//				facturasController.updatePorPago(idPresupuesto);
//				this.onCargar(a);
//				mostrarTicket(idPresupuesto, "MercadoPago");
//				JOptionPane.showMessageDialog(null, "Operación realizada correctamente", "Exito",
//						JOptionPane.INFORMATION_MESSAGE);
//				this.viewMercadoPago.setVisible(false);
//			}
//		} else {
//			JOptionPane.showMessageDialog(null, "Debe seleccionar una factura", "Error", JOptionPane.ERROR_MESSAGE);
//		}
//	}
//
//	private void onRegistrarBitcoin(ActionEvent a) {
//		int idPresupuesto = this.view.getIdPresupuestoSeleccionada();
//		String IdEstado = this.view.getEstadoSeleccionada();
//		Double idTotal = this.view.getTotalSeleccionada();
//
//		if (idPresupuesto != -1) {
//			int resp = JOptionPane.showOptionDialog(null,
//					"¿Desea confirmar la recepcion a traves de Bitcoins de: " + idTotal + " ?", "Confirmación",
//					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
//			if (resp == 0 && IdEstado.equals("PAGA")) {
//				JOptionPane.showMessageDialog(null, "La factura ya esta paga!", "Error", JOptionPane.ERROR_MESSAGE);
//			} else if (resp == 0) {
//				facturasController.updatePorPago(idPresupuesto);
//				this.onCargar(a);
//				mostrarTicket(idPresupuesto, "BitCoin");
//				JOptionPane.showMessageDialog(null, "Operación realizada correctamente", "Exito",
//						JOptionPane.INFORMATION_MESSAGE);
//				this.viewBitcoin.setVisible(false);
//			}
//		} else {
//			JOptionPane.showMessageDialog(null, "Debe seleccionar una factura", "Error", JOptionPane.ERROR_MESSAGE);
//		}
//	}
//
//	private void onRegistrarEfectivo(ActionEvent a) {
//		int idPresupuesto = this.view.getIdPresupuestoSeleccionada();
//		String IdEstado = this.view.getEstadoSeleccionada();
//		Double idTotal = this.view.getTotalSeleccionada();
//
//		if (idPresupuesto != -1) {
//			int resp = JOptionPane.showOptionDialog(null, "¿Desea confirmar el pago de: " + idTotal + " ?",
//					"Pago en Efectivo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
//			if (resp == 0 && IdEstado.equals("PAGA")) {
//				JOptionPane.showMessageDialog(null, "La factura ya esta paga!", "Error", JOptionPane.ERROR_MESSAGE);
//			} else if (resp == 0) {
//				facturasController.updatePorPago(idPresupuesto);
//				this.onCargar(a);
//				mostrarTicket(idPresupuesto, "Efectivo");
//				JOptionPane.showMessageDialog(null, "Operación realizada correctamente", "Exito",
//						JOptionPane.INFORMATION_MESSAGE);
//			}
//		} else {
//			JOptionPane.showMessageDialog(null, "Debe seleccionar una factura", "Error", JOptionPane.ERROR_MESSAGE);
//		}
//	}
	
	private void mostrarTicket(Integer idFactura, String medioPago) {
		ReporteViewImpl report = new ReporteViewImpl();
		TicketReport ticket = new TicketReport(facturasController.readByFactura(idFactura));
		ticket.setMedioDePago(medioPago);
		report.setData(ticket);
		report.open();
	}
}