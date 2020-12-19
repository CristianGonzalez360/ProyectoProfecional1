package presentacion;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import business_logic.ClientesController;
import business_logic.ControllersFactory;
import business_logic.VentasVehiculosController;
import business_logic.exceptions.ForbiddenException;
import dto.CaracteristicaVehiculoDTO;
import dto.ClienteDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.IngresoOrdenDeTrabajoDTO;
import dto.temporal.VehiculoParaEntregar;
import presentacion.views.gerente.FormRegistroAseguradoraView;
import presentacion.views.gerente.PanelEntregaDeVehiculos;
import presentacion.views.utils.MessageDialog;

public class EntregaVehiculosVentaPresenter {

	private PanelEntregaDeVehiculos view;
	private FormRegistroAseguradoraView formRegistroAseguradora;
	private List<VehiculoParaEntregar> ventas;
	private int ventaSeleccionada;

	private VentasVehiculosController ventasVehiculosController;
	private ClientesController clientesController;
	private ControllersFactory controllers;

	public EntregaVehiculosVentaPresenter(VentasVehiculosController ventasVehiculosController,
			ClientesController clientesController, ControllersFactory controllers) {
		this.view = PanelEntregaDeVehiculos.getInstance();
		this.controllers = controllers;
		this.view.setActionOnSeleccionarVenta(a -> onSeleccionarVenta(a));
		this.view.setActionOnRefrescar(a -> onRefrescar(a));
		this.view.setActionOnRegistrar(a -> onRegistrar(a));
		
		this.formRegistroAseguradora = FormRegistroAseguradoraView.getInstance();
		this.formRegistroAseguradora.setActionSave(a -> onRegistrarAseguradora(a));
		
		this.ventasVehiculosController = ventasVehiculosController;
		this.clientesController = clientesController;

		this.ventaSeleccionada = -1;
		this.ventas = new ArrayList<>();
	}

	private void onRegistrar(ActionEvent a) {
		List<String> errors = new LinkedList<>();
		if (ventaSeleccionada == -1) {
			errors.add("¡Por favor, seleccione una venta!");
		}
		if (!view.papelesEnRegla(ventaSeleccionada)) {
			errors.add("Para registrar la entrega, primero debe confirmar la recepción de papeles requeridos.");
		}

		if (errors.isEmpty()) {
			try {
				
				formRegistroAseguradora.display();
			} catch (ForbiddenException e) {
				new MessageDialog().showMessages(e.getMessage());
			}
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
			CaracteristicaVehiculoDTO vehiculo = ventasVehiculosController
					.readCaracteristicaVehiculoByIdVehiculo(ventas.get(ventaSeleccionada).getVenta().getIdVehiculo());
			this.view.setdata(vehiculo);
			ClienteDTO cliente = clientesController.readById(ventas.get(ventaSeleccionada).getVenta().getIdCliente());
			this.view.setData(cliente);
		}
	}

	private void mostrarVentas() { // TODO traer ventas con fechaEntrega real en null, en caso de que fechaEntrega
									// real sea el atributo que estoy buscando
		this.ventas = ventasVehiculosController.readVentasVehiculosParaEntregar();
		this.view.setData(ventas);
	}
	

	private void onRegistrarAseguradora(ActionEvent a) {
		
		List<String> errors = this.formRegistroAseguradora.aseguradoraValida();
		if(errors.isEmpty()) {
			Integer idVehiculo = ventas.get(ventaSeleccionada).getVenta().getIdVehiculo();
			Integer idCliente = ventas.get(ventaSeleccionada).getVenta().getIdCliente();
			
			List<IngresoOrdenDeTrabajoDTO> ingresosRegistrados = controllers.makeVehiculosConOrdenDeTrabajoController().readAllIngresoOrdenTrabajo();
			int i=0;
			boolean flag = true;
			while(i<ingresosRegistrados.size()&&flag){
				
				if(ingresosRegistrados.get(i).getIdCliente().intValue()==idCliente.intValue() && ingresosRegistrados.get(i).getIdVehiculo().intValue()==idVehiculo.intValue()) {			
					ventasVehiculosController.registrarEntrega(ventas.get(ventaSeleccionada));
					
					
					VehiculoDTO vehiculo = controllers.makePedidosController().readByVehiculoId(ventas.get(ventaSeleccionada).getVenta().getIdVentaVehiculo());
//					System.out.println(vehiculo);
					FichaTecnicaVehiculoDTO ficha = controllers.makeVehiculosController().readByIdFichaTecnica(vehiculo.getIdFichaTecnica());
					
//					System.out.println(ficha);		
					ingresosRegistrados.get(i).setKilometrajeGarantia(ficha.getKilometraje()+100000);//seteado CAMBIAR
					ingresosRegistrados.get(i).setAseguradora(formRegistroAseguradora.getAseguradora());
					ingresosRegistrados.get(i).setNroPolizaSeguro(Integer.parseInt(formRegistroAseguradora.getNroPoliza()));
					
					controllers.makeVehiculosConOrdenDeTrabajoController().updateVehiculoIngresado(ingresosRegistrados.get(i));
					onRefrescar(a);
					this.formRegistroAseguradora.close();
					new MessageDialog().showMessages("Se registró la entrega de vehiculo.");
					flag=false;
				}
				i++;
			}
			
		} else {
			new MessageDialog().showMessages(errors);
		}
		
	}


}
