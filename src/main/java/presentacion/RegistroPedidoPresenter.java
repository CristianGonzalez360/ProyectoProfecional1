package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import business_logic.ClientesController;
import business_logic.PedidosController;
import business_logic.VentasVehiculosController;
import dto.CaracteristicaVehiculoDTO;
import dto.ClienteDTO;
import dto.PedidoVehiculoDTO;
import dto.VentaVehiculoDTO;
import presentacion.views.gerente.PanelRegistroPedido;
import presentacion.views.utils.MessageDialog;

public class RegistroPedidoPresenter {

	private PanelRegistroPedido view;
	
	private List<VentaVehiculoDTO> ventas;
	private Integer ventaSeleccionada;
	
	private VentasVehiculosController ventasVehiculosController;
	private ClientesController clientesController;
	private PedidosController pedidosController;
	
	public RegistroPedidoPresenter(VentasVehiculosController ventasVehiculosController, ClientesController clientesController, PedidosController pedidosController) {
		this.view = PanelRegistroPedido.getInstance();
		this.view.setActionOnSeleccionarVenta(a -> onSeleccionarVenta(a));
		this.view.setActionOnRefrescar(a -> onRefrescar(a));
		this.view.setActionOnRegistrar(a -> onRegistrar(a));
		
		this.ventasVehiculosController = ventasVehiculosController;
		this.clientesController = clientesController;
		this.pedidosController = pedidosController;
		
		mostrarVentas();
	}

	private void onRegistrar(ActionEvent a) {
		if(ventaSeleccionada != null) { 
			PedidoVehiculoDTO pedido = new PedidoVehiculoDTO();
			pedido.setIdVentaVehiculo(ventas.get(ventaSeleccionada).getIdVentaVehiculo());
			pedidosController.save(pedido);
		} else {
			new MessageDialog().showMessages("Debe seleccionar un venta");
		}
	}

	private void onRefrescar(ActionEvent a) {
		this.ventaSeleccionada = null;
		this.view.clearData();
		mostrarVentas();
	}

	private void onSeleccionarVenta(ListSelectionEvent a) {
		this.ventaSeleccionada = view.getFilaSeleciconada();
		CaracteristicaVehiculoDTO vehiculo = ventasVehiculosController.readCaracteristicaVehiculoByIdVehiculo(ventas.get(ventaSeleccionada).getIdVehiculo());
		this.view.setdata(vehiculo);
		ClienteDTO cliente = clientesController.readById(ventas.get(ventaSeleccionada).getIdCliente());
		this.view.setData(cliente);
	}
	
	private void mostrarVentas() {
		this.ventas = ventasVehiculosController.readVentasVehiculosNoDisponibles();
		this.view.setData(ventas);
	}
	
}
