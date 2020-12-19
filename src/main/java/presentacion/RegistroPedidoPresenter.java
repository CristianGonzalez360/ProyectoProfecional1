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
import dto.temporal.VehiculoParaEntregar;
import presentacion.views.gerente.PanelRegistroPedido;
import presentacion.views.utils.MessageDialog;

public class RegistroPedidoPresenter {

	private PanelRegistroPedido view;

	private List<VehiculoParaEntregar> ventas;
	private int ventaSeleccionada;

	private VentasVehiculosController ventasVehiculosController;
	private ClientesController clientesController;
	private PedidosController pedidosController;

	public RegistroPedidoPresenter(VentasVehiculosController ventasVehiculosController,
			ClientesController clientesController, PedidosController pedidosController) {
		this.view = PanelRegistroPedido.getInstance();
		this.view.setActionOnSeleccionarVenta(a -> onSeleccionarVenta(a));
		this.view.setActionOnRefrescar(a -> onRefrescar(a));
		this.view.setActionOnRegistrar(a -> onRegistrar(a));

		this.ventasVehiculosController = ventasVehiculosController;
		this.clientesController = clientesController;
		this.pedidosController = pedidosController;

		this.ventaSeleccionada = -1;
	}

	private void onRegistrar(ActionEvent a) {
		List<String> errors = new LinkedList<>();
		if (ventaSeleccionada == -1) {
			errors.add("¡Por faovr, debe seleccionar una venta!");
		} else if (ventas.get(ventaSeleccionada).isPedido()) {
			errors.add("!El vehículo ya fue pedido!");
		}

		if (errors.isEmpty()) {
			pedidosController.save(ventas.get(ventaSeleccionada).getVenta().getIdVentaVehiculo());
			onRefrescar(a);
			new MessageDialog().showMessages("Pedido Registrado.");
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
		if (ventaSeleccionada != -1) {
			CaracteristicaVehiculoDTO vehiculo = ventasVehiculosController.readCaracteristicaVehiculoByIdVehiculo(
					ventas.get(ventaSeleccionada).getVehiculo().getIdVehiculo());
			this.view.setdata(vehiculo);
			ClienteDTO cliente = clientesController.readById(ventas.get(ventaSeleccionada).getVenta().getIdCliente());
			this.view.setData(cliente);
		}
	}

	private void mostrarVentas() {
		this.ventas = ventasVehiculosController.readVentasVehiculosNoDisponibles();
		this.view.setData(ventas);
	}

}
