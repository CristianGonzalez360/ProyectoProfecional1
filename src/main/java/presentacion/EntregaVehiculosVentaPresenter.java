package presentacion;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import business_logic.ClientesController;
import business_logic.VentasVehiculosController;
import dto.CaracteristicaVehiculoDTO;
import dto.ClienteDTO;
import dto.VentaVehiculoDTO;
import presentacion.views.gerente.PanelEntregaDeVehiculos;
import presentacion.views.utils.MessageDialog;

public class EntregaVehiculosVentaPresenter {

	private PanelEntregaDeVehiculos view;
	
	private List<VentaVehiculoDTO> ventas;
	private int ventaSeleccionada;
	
	private VentasVehiculosController ventasVehiculosController;
	private ClientesController clientesController;
	
	public EntregaVehiculosVentaPresenter(VentasVehiculosController ventasVehiculosController, ClientesController clientesController) {
		this.view = PanelEntregaDeVehiculos.getInstance();
		
		this.view.setActionOnSeleccionarVenta(a -> onSeleccionarVenta(a));
		this.view.setActionOnRefrescar(a -> onRefrescar(a));
		this.view.setActionOnRegistrar(a -> onRegistrar(a));

		this.ventasVehiculosController = ventasVehiculosController;
		this.clientesController = clientesController;
		
		this.ventaSeleccionada = -1;
		mostrarVentas();
	}

	private void onRegistrar(ActionEvent a) {
		List<String> errors = new LinkedList<>();
		if(ventaSeleccionada == -1) { 
			errors.add("Debe seleccionar una venta.");
		}
		else if(!ventas.get(ventaSeleccionada).isPedido()) {
			errors.add("El vehículo no fue ingresado a la concesionaria. ");
		}else if(!view.papelesEnRegla(ventaSeleccionada)){
			errors.add("Entregue los papeles. ");
		}
		
		if(errors.isEmpty()) { 
			ventasVehiculosController.registrarEntrega(ventas.get(ventaSeleccionada).getIdVentaVehiculo());
			onRefrescar(a);
			new MessageDialog().showMessages("Entrega de vehiculo Registrada");
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
	
	private void mostrarVentas() { //TODO traer ventas con fechaEntrega real en null, en caso  de que fechaEntrega real sea el atributo que estoy buscando
		this.ventas = ventasVehiculosController.readVentasVehiculosParaEntregar();
		this.view.setData(ventas);
	}
	

}
