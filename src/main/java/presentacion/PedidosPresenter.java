package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.PedidosController;
import business_logic.VehiculosController;
import dto.PedidoVehiculoDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import presentacion.views.gerente.PedidosPanelView;
import presentacion.views.gerente.FichaTecnicaFormView;
import presentacion.views.utils.MessageDialog;
import services.SessionServiceImpl;

public class PedidosPresenter {

	private static final String CONFIRMATION = "¿Está seguro que desea registrar el pedido?";

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
			this.fichaFormDisplay();//va aca
		}
	}

	private boolean puedoRegistrarIngreso(Integer idFila) {
		return hayFilaSeleccionada(idFila);//&& new ConfirmationDialog(CONFIRMATION).open() == 0
	}

	private boolean hayFilaSeleccionada(Integer idFila) {
		return (idFila != -1);
	}
	
	private void onRegistrarNuevaFicha(ActionEvent a) {//guarda ficha tecnica con 
		FichaTecnicaVehiculoDTO fichaNueva = formNuevaFicha.getData();
		Integer idUsuario = SessionServiceImpl.getInstance().getActiveSession().getIdUsuario();
		Integer idPedido = view.getIdSelectedPedido();

		if(isNroMotorNuevo(fichaNueva.getNroMotor())) {
			if(isNroChasisNuevo(fichaNueva.getNroChasis())) {//todo Ok
			
				PedidoVehiculoDTO pedido = controller.readPedidoById(idPedido);//obtengo el pedido, necesito el idventa
				VentaVehiculoDTO venta = controller.readByVentaId(pedido.getIdVentaVehiculo());
				VehiculoDTO vehiculo = controller.readByVehiculoId(venta.getIdVehiculo());
				vehiculo.setIdFichaTecnica(vehiculo.getIdFichaTecnica());
				vehiculo.setIdFichaTecnica(vehiculosController.guardarFichaTecnicaNueva(formNuevaFicha.getData()));
				controller.updateIdFichaTecnicaDeVehiculo(vehiculo);

				if (controller.registrarIngresoPedidoById(idPedido, idUsuario)) {
					
					new MessageDialog().showMessages("Vehiculo ingresado!");
					formNuevaFicha.close();
				} else
					new MessageDialog().showMessages("No se puedo registrar el ingreso del Vehiculo!");
				
			} else {
				new MessageDialog().showMessages("El numero de chasis no esta disponible");
			}
		} else {
			new MessageDialog().showMessages("El numero de motor no esta disponible");
		}
	}

	private boolean isNroMotorNuevo(Integer nroMotor) {
		if (vehiculosController.isNroMotorExistente(nroMotor) == true) {// hay un nro motor existente																		
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
		formNuevaFicha.clearData();
		formNuevaFicha.display();
	}
}
