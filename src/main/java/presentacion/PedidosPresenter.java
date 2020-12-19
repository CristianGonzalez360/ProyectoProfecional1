package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;
import business_logic.PedidosController;
import business_logic.VehiculosController;
import dto.PedidoVehiculoDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.taller.AltaFichaNuevaDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import presentacion.views.gerente.PedidosPanelView;
import presentacion.views.gerente.FichaTecnicaFormView;
import presentacion.views.utils.MessageDialog;
import services.SessionServiceImpl;

public class PedidosPresenter {

	private PedidosPanelView view;
	private PedidosController controller;
	private FichaTecnicaFormView formNuevaFicha;
	private VehiculosController vehiculosController;
	
	public PedidosPresenter(PedidosPanelView view, PedidosController controller, VehiculosController vehiculosController) {
		this.view = view;
		this.controller = controller;
		this.vehiculosController = vehiculosController;
		formNuevaFicha = FichaTecnicaFormView.getInstance();
		view.setActionBuscar((a) -> onBuscar(a));
		view.setActionRegistrarIngreso((a) -> onRegistrar(a));
		formNuevaFicha.setActionSave((a) -> onRegistrarNuevaFicha(a));
	}

	private void onBuscar(ActionEvent a) {
		Integer idSucursal = SessionServiceImpl.getInstance().getActiveSession().getIdSucursal();

		view.clear();
		view.setData(controller.readAllPedidos(idSucursal));
	}

	private void onRegistrar(ActionEvent event) {
		Integer idFila = view.getIdSelectedRow();
		
		if (puedoRegistrarIngreso(idFila)) {
			this.fichaFormDisplay();
		}
	}

	private boolean puedoRegistrarIngreso(Integer idFila) {
		return hayFilaSeleccionada(idFila);
	}

	private boolean hayFilaSeleccionada(Integer idFila) {
		return (idFila != -1);
	}
	
	private void onRegistrarNuevaFicha(ActionEvent a) {
				
		Integer idUsuario = SessionServiceImpl.getInstance().getActiveSession().getIdUsuario();
		Integer idPedido = view.getIdSelectedPedido();
		
		AltaFichaNuevaDTO altaFicha = formNuevaFicha.getAltaFichaNueva();
		List<String> errors = altaFicha.validateNuevaFicha();
		
		if (errors.isEmpty()) {
			
			FichaTecnicaVehiculoDTO fichaNueva = formNuevaFicha.getData();
			
			if(isNroMotorNuevo(fichaNueva.getNroMotor())) {
				
				if(isNroChasisNuevo(fichaNueva.getNroChasis())) {
					
					PedidoVehiculoDTO pedido = controller.readPedidoById(idPedido);
					VentaVehiculoDTO venta = controller.readByVentaId(pedido.getIdVentaVehiculo());
					VehiculoDTO vehiculo = controller.readByVehiculoId(venta.getIdVehiculo());
					vehiculo.setIdFichaTecnica(vehiculo.getIdFichaTecnica());
					vehiculo.setIdFichaTecnica(vehiculosController.guardarFichaTecnicaNueva(formNuevaFicha.getData()));
					controller.updateIdFichaTecnicaDeVehiculo(vehiculo);
	
					if (controller.registrarIngresoPedidoById(idPedido, idUsuario)) {
						
						formNuevaFicha.close();
						onBuscar(a);
						new MessageDialog().showMessages("Vehículo ingresado con éxito.");
					} else
						new MessageDialog().showMessages("ERROR: ¡No se puedo registrar el ingreso del vehículo!");

				} else {
					new MessageDialog().showMessages("ERROR: ¡El número de chasis ingresado no esta disponible!");
				}
			} else {
				new MessageDialog().showMessages("ERROR: ¡El número de motor ingresado no esta disponible!");
			}
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	private boolean isNroMotorNuevo(Integer nroMotor) {
		
		if (vehiculosController.isNroMotorExistente(nroMotor) == true) {																	
			return false;
		}
		return true;
	}
	
	private boolean isNroChasisNuevo(Integer nroChasis) {

		List<FichaTecnicaVehiculoDTO> fichas = vehiculosController.readAllFichas();
		for (FichaTecnicaVehiculoDTO fichaTecnica : fichas) {
			
			if(fichaTecnica.getNroChasis().intValue()==nroChasis.intValue()) 
				return false;	
		}
		return true;
	}
	
	private void fichaFormDisplay() {
		Integer idPedido = view.getIdSelectedPedido();
		PedidoVehiculoDTO pedido = controller.readPedidoById(idPedido);
		VentaVehiculoDTO venta = controller.readByVentaId(pedido.getIdVentaVehiculo());
		VehiculoDTO vehiculo = controller.readByVehiculoId(venta.getIdVehiculo());

		formNuevaFicha.clearData();
		formNuevaFicha.setData(vehiculo);
		formNuevaFicha.display();
	}
}
