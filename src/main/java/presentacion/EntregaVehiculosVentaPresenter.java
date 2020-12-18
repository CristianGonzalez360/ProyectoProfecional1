package presentacion;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import business_logic.ClientesController;
import business_logic.ControllersFactory;
import business_logic.VehiculosController;
import business_logic.VentasVehiculosController;
import business_logic.exceptions.ForbiddenException;
import dto.CaracteristicaVehiculoDTO;
import dto.ClienteDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.temporal.AltaDeVehiculoDTO;
import dto.temporal.VehiculoParaEntregar;
import presentacion.views.gerente.FormRegistroAseguradoraView;
import presentacion.views.gerente.PanelEntregaDeVehiculos;
import presentacion.views.utils.MessageDialog;
import services.SessionServiceImpl;

public class EntregaVehiculosVentaPresenter {

	private PanelEntregaDeVehiculos view;

	private List<VehiculoParaEntregar> ventas;
	private int ventaSeleccionada;

	private VentasVehiculosController ventasVehiculosController;
	private ClientesController clientesController;
	private VehiculosController vehiculoController;
	private FormRegistroAseguradoraView formNuevaAseguradora;
	private ControllersFactory controllers;

	public EntregaVehiculosVentaPresenter(VentasVehiculosController ventasVehiculosController,
			ClientesController clientesController, VehiculosController vehiculoController, ControllersFactory controllers) {
		
		this.view = PanelEntregaDeVehiculos.getInstance();
		this.vehiculoController = vehiculoController;
		this.view.setActionOnSeleccionarVenta(a -> onSeleccionarVenta(a));
		this.view.setActionOnRefrescar(a -> onRefrescar(a));
		this.view.setActionOnRegistrar(a -> onRegistrar(a));
		this.formNuevaAseguradora = FormRegistroAseguradoraView.getInstance();
		this.formNuevaAseguradora.setActionSave((a) -> onRegistrarNuevaAseguradora(a));
		
		this.ventasVehiculosController = ventasVehiculosController;
		this.clientesController = clientesController;
		this.controllers = controllers;
		
		this.ventaSeleccionada = -1;
		this.ventas = new ArrayList<>();
	}

	private void onRegistrar(ActionEvent a) {
		List<String> errors = new LinkedList<>();
		if (ventaSeleccionada == -1) {
			errors.add("Debe seleccionar una venta.");
		}
		if (!view.papelesEnRegla(ventaSeleccionada)) {
			errors.add("Para registrar la entrega, primero debe confirmar la recepcion de los papeles");
		}

		if (errors.isEmpty()) {
//			System.out.println(ventas.get(ventaSeleccionada).getVenta().getIdSucursalVenta());
			if(SessionServiceImpl.getInstance().getActiveSession().getSucursal().getIdSucursal().intValue()==ventas.get(ventaSeleccionada).getVenta().getIdSucursalVenta().intValue()) {
				this.formNuevaAseguradora.display();
			} else {
				new MessageDialog().showMessages("El vehículo no esta en esta sucursal");
			}
				
		} else {
			new MessageDialog().showMessages(errors);
		}
	}
	
	private void onRegistrarNuevaAseguradora(ActionEvent a) {
		
//		System.out.println(view.getIdPedido());
		List<String> errors = this.formNuevaAseguradora.aseguradoraValida();
		if(errors.isEmpty()) {

			ClienteDTO cliente = clientesController.readById(ventas.get(ventaSeleccionada).getVenta().getIdCliente());
			VehiculoDTO vehiculo = ventasVehiculosController.datosVehiculo(ventas.get(ventaSeleccionada).getVenta().getIdVehiculo());
			FichaTecnicaVehiculoDTO ficha = vehiculoController.readByIdFichaTecnica(vehiculo.getIdFichaTecnica());
			AltaDeVehiculoDTO vehiculoDeAlta = crearAltaDeVehiculo(ficha);
			
			try {
				ventasVehiculosController.registrarEntrega(ventas.get(ventaSeleccionada));
				controllers.makeVehiculosConOrdenDeTrabajoController().save2(cliente.getIdCliente(), vehiculoDeAlta);
				
				onRefrescar(a);
				formNuevaAseguradora.close();
				
				new MessageDialog().showMessages("Entrega de vehiculo Registrada");
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

	private AltaDeVehiculoDTO crearAltaDeVehiculo(FichaTecnicaVehiculoDTO ficha) {
		AltaDeVehiculoDTO ret = new AltaDeVehiculoDTO();
		ret.setAsegurador(formNuevaAseguradora.getAseguradora());
		ret.setNroPolizaSeguro(formNuevaAseguradora.getNroPoliza());
		ret.setNroChasis(ficha.getNroChasis().toString());
		ret.setNroMotor(ficha.getNroMotor().toString());
		ret.setKilometraje(ficha.getKilometraje().toString());
		ret.setMarca(ficha.getMarca());
		ret.setModelo(ficha.getModelo().toString());
		ret.setPatente(ficha.getPatente().toString());
		ret.setColor(ficha.getColor());
		ret.setCombustion(ficha.getCombustion());
		ret.setDescripcion("");
		ret.setKilometrajeGarantia("100000");
		
		return ret;
	}
}
