package presentacion;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import business_logic.ClientesController;
import business_logic.FacturasController;
import business_logic.RepuestosController;
import dto.ClienteDTO;
import dto.FacturaDTO;
import dto.RepuestoCompradoDTO;
import dto.RepuestoDTO;
import dto.validators.StringValidator;
import presentacion.views.cajero.PanelCarritoRepuestoView;
import presentacion.views.supervisor.ClienteFormView;
import presentacion.views.tecnico.AltaPresupuestoFormView;
import presentacion.views.utils.MessageDialog;
import presentacion.views.utils.ReporteViewImpl;

public class CarritoPresenter {

	private PanelCarritoRepuestoView view;
	private AltaPresupuestoFormView altaPresupuesto;
//	private PresupuestoDTO nuevoPresupuesto;
	private RepuestosController repuestosController;
	private ClientesController clienteController;
	private FacturasController facturasController;

	private String marca;
	private String descripcion;
	private Double precioTotal = 0.00;
	RepuestoCompradoDTO repuestoComprado = new RepuestoCompradoDTO();
	ClienteDTO clienteFactura;
	
	private List<RepuestoCompradoDTO> repuestos;

	public CarritoPresenter(RepuestosController repuestosController, ClientesController clientesController,
			FacturasController facturasController) {
		this.repuestosController = repuestosController;
		this.clienteController = clientesController;
		this.facturasController = facturasController;

		this.view = PanelCarritoRepuestoView.getInstance();
		this.altaPresupuesto = AltaPresupuestoFormView.getInstance();

		this.altaPresupuesto.setActionOnCancelar(a -> onCancelar(a));
		this.view.setActionOnAgregar(a -> onAgregarRepuesto(a));
		this.view.setActionOnQuitar(a -> onQuitarRepuesto(a));

		this.view.setActionOnAgregarCliente(a -> onDisplayClienteForm(a));

		
		this.view.setActionOnBuscarCliente(a -> onBuscarCliente(a));
		this.view.setActionOnBuscar(a -> onBuscarRepuesto(a));
		
		//issue#32
		this.view.setActionOnCrearFactura(a -> onCrearFactura(a));
		
		repuestos = new ArrayList<>();
		
		this.cargarBuscadorRepuestos();
		
		
	}

	private void onDisplayClienteForm(ActionEvent a) {
		// TODO Auto-generated method stub
		ClienteFormView.getInstance().display();
		;
	}

	// Cuando se cancela, borra el presupuesto para que no quede vacio.
	private void onCancelar(ActionEvent a) {
//		nuevoPresupuesto = null;
//		altaPresupuesto.clearData();
//		altaPresupuestso.close();
	}

	// Quita un repuesto del presupuesto
	private void onQuitarRepuesto(ActionEvent a) {
		Integer fila = this.view.getSeleccionado();
		if (fila >= 0) {
			RepuestoCompradoDTO repuestocomprado = repuestos.get(fila);
			RepuestoDTO repuesto = repuestosController.readById(repuestocomprado.getRepuesto().getIdRepuesto());
			repuesto.setStockRepuesto(repuestocomprado.getCantRequerida() + repuesto.getStockRepuesto());
			repuestosController.update(repuesto);
			onBuscarRepuesto(a);
			this.repuestos.remove(fila.intValue());
			this.view.setDataRepuestosComprados(repuestos);
			//issue32
			precioTotal = precioTotal - repuestocomprado.getRepuesto().getPrecioRepuesto() * (repuestocomprado.getCantRequerida());
			this.view.getTfTotalFactura().setText(precioTotal.toString());
		}
	}
	
	// Agrega repuesto a el carrito
	private void onAgregarRepuesto(ActionEvent a) {
		String cantidad = view.getCantidad();
		String idRepuesto = view.getIdRepuesto();
		List<String> errors = new StringValidator(idRepuesto).number("Debe seleccionar un repuesto.").validate();
		errors.addAll(new StringValidator(cantidad).notBlank("Debe ingresar una cantidad.")
				.positiveInteger("La cantidad debe ser un número mayor a 0.").validate());
		if (errors.isEmpty()) {
			RepuestoDTO repuesto = repuestosController.readById(Integer.parseInt(idRepuesto));
			int cant = Integer.parseInt(cantidad);
			int stock = repuesto.getStockRepuesto();
			if (cant <= stock) {
				RepuestoCompradoDTO repuestoComprado = new RepuestoCompradoDTO();
				repuestoComprado.setCantRequerida(cant);
				repuestoComprado.setRepuesto(repuesto);
				repuesto.setStockRepuesto(stock - cant);
				repuestosController.update(repuesto);
				repuestos.add(repuestoComprado);
//				issue#32
				precioTotal = precioTotal + (repuestoComprado.getRepuesto().getPrecioRepuesto()) * (Integer.parseInt(cantidad) )  ;
				onBuscarRepuesto(a);
				view.setDataRepuestosComprados(repuestos);
				view.getTfTotalFactura().setText(precioTotal.toString());
			} else {
				new MessageDialog().showMessages("No hay stock suficiente.");
			}
		} else {
			new MessageDialog().showMessages(errors);
			;
		}
	}

	// Carga los repuestos y las marcas
	private void cargarBuscadorRepuestos() {
		List<String> marcas = new ArrayList<>();
		marcas.add("Todas");
		marcas.addAll(repuestosController.readMarcas());
		this.view.setDataMarcas(marcas);
		this.view.setDataRepuestos(repuestosController.readAll());
	}

	// Busca vehiculos con orden de trabajo abiertas
	private void onBuscarCliente(ActionEvent a) {
		view.clearClienteData();
		String inputDni = view.getDniCliente();
		if (new StringValidator(inputDni).number("").validate().isEmpty()) {
			ClienteDTO cliente = clienteController.readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				view.setDataCliente(cliente);
				clienteFactura = cliente;
			}
		}
	}

	private void refrescar() {
		List<RepuestoDTO> repuestos;
		if (descripcion.isEmpty()) {
			if (marca == "Todas") {
				repuestos = repuestosController.readAll();
			} else {
				repuestos = repuestosController.readByMarca(marca);
			}
		} else {
			if (marca == "Todas") {
				repuestos = repuestosController.readByDescripcion(descripcion);
			} else {
				repuestos = repuestosController.readbyMarcaYDescripcion(marca, descripcion);
			}
		}
		view.setDataRepuestos(repuestos);
	}

	public void cargarMarcas() {
		List<String> marcas = new ArrayList<String>();
		marcas.add("todas");
		marcas.addAll(repuestosController.readMarcas());
		this.view.setDataMarcas(marcas);
	}

	// Busca repuestos segun criterio seleccionado
	private void onBuscarRepuesto(ActionEvent a) {
		marca = view.getMarca();
		descripcion = view.getDescripcion();
		refrescar();
	}

	private void onCrearFactura(ActionEvent a) {
		//TODO agregar validaciones
		FacturaDTO facturaCarrito = new FacturaDTO();
		facturaCarrito.setCliente(clienteFactura);
		facturaCarrito.setRepuestosComprados(repuestos);
		facturaCarrito.setTotal(precioTotal);
		facturaCarrito.setFechaDeAlta(new Date());
		facturasController.generarFacturaCarrito(facturaCarrito);
		mostrarFactura(facturaCarrito);
		view.clear();
		this.precioTotal = 0.0;
		this.clienteFactura = null;
		this.repuestos.clear();
	}
	
	private void mostrarFactura(FacturaDTO factura) {
		ReporteViewImpl ventanaReporte = new ReporteViewImpl();
		ventanaReporte.setData(facturasController.makeFacturaRepuestos(factura));
		ventanaReporte.open();
	}
	
}
