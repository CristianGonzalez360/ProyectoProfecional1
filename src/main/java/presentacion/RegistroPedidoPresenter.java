package presentacion;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
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
	private int ventaSeleccionada;
	
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
		
		this.ventaSeleccionada = -1;
		mostrarVentas();
	}

	private void onRegistrar(ActionEvent a) {
		List<String> errors = new LinkedList<>();
		if(ventaSeleccionada == -1) { 
			errors.add("Debe seleccionar una venta.");
		}
		else if(ventas.get(ventaSeleccionada).isPedido()) {
			errors.add("El vehículo ya fue pedido");
		}
		
		if(errors.isEmpty()) { 
			pedidosController.save(ventas.get(ventaSeleccionada).getIdVentaVehiculo());
			onRefrescar(a);
			new MessageDialog().showMessages("Pedido Registrado");
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	private void onRefrescar(ActionEvent a) {
		this.ventaSeleccionada = -1;
		this.view.clearData();
		mostrarVentas();
	}

	private void onSeleccionarVenta(ListSelectionEvent a) {
		this.ventaSeleccionada = view.getFilaSeleciconada();
		if(ventaSeleccionada != -1) { 
			CaracteristicaVehiculoDTO vehiculo = ventasVehiculosController.readCaracteristicaVehiculoByIdVehiculo(ventas.get(ventaSeleccionada).getIdVehiculo());
			this.view.setdata(vehiculo);
			ClienteDTO cliente = clientesController.readById(ventas.get(ventaSeleccionada).getIdCliente());
			this.view.setData(cliente);
		}
	}
	
	private void mostrarVentas() {
		this.ventas = ventasVehiculosController.readVentasVehiculosNoDisponibles();
		this.view.setData(ventas);
	}
	
}
