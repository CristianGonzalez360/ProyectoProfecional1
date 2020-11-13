package presentacion.views.supervisor;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import business_logic.RepuestosController;
import dto.RepuestoDTO;
import dto.validators.StringValidator;
import presentacion.views.utils.InputDialog;
import presentacion.views.utils.MessageDialog;

public class RepuestosPresenter {

	private PanelGestionRepuestos gestionRepuestos;
	private RepuestosController repuestosController;
	private String marca;
	private String descripcion;

	public RepuestosPresenter(RepuestosController controller) {
		this.repuestosController = controller;
		this.gestionRepuestos = PanelGestionRepuestos.getInstance();
		this.gestionRepuestos.setActionOnBuscar(a -> onBuscarRepuesto(a));
		this.gestionRepuestos.setActionOnIngresarStock(a -> onIngresarStock(a));
		this.gestionRepuestos.setActionOnCargarArchivo(a -> onCargarArchivo(a));

		cargarMarcas();
	}

	private void onCargarArchivo(ActionEvent a) {
		// TODO Auto-generated method stub
	}

	private void onIngresarStock(ActionEvent a) {
		int id = this.gestionRepuestos.getIdRepuesto();
		if (id >= 0) {
			String stock = new InputDialog().title("Ingresar Stock").setLabel("Cantidad").open();
			if (stock != null) {
				List<String> error = new StringValidator(stock).notBlank("Debe ingresar un valor")
						.number("El valor debe ser numérico").validate();
				if (error.isEmpty()) {
					int cantidad = Integer.parseInt(stock);
					RepuestoDTO repuesto = repuestosController.readById(id);
					repuesto.setStockRepuesto(repuesto.getStockRepuesto() + cantidad);
					repuestosController.update(repuesto);
					refrescar();
				} else {
					new MessageDialog().showMessages(error);
				}
			}
		} else {
			new MessageDialog().showMessages("Seleccione un repuesto");
		}
	}

	private void refrescar() {
		List<RepuestoDTO> repuestos;
		if (descripcion.isEmpty()) {
			if (marca == "todas") {
				repuestos = repuestosController.readAll();
			} else {
				repuestos = repuestosController.readByMarca(marca);
			}
		} else {
			if (marca == "todas") {
				repuestos = repuestosController.readByDescripcion(descripcion);
			} else {
				repuestos = repuestosController.readbyMarcaYDescripcion(marca, descripcion);
			}
		}
		gestionRepuestos.setData(repuestos);
	}

	public void cargarMarcas() {
		List<String> marcas = new ArrayList<String>();
		marcas.add("todas");
		marcas.addAll(repuestosController.readMarcas());
		this.gestionRepuestos.setDataMarcas(marcas);
	}

	// Busca repuestos segun criterio seleccionado
	private void onBuscarRepuesto(ActionEvent a) {
		marca = gestionRepuestos.getMarca();
		descripcion = gestionRepuestos.getDescripcion();
		refrescar();
	}
}
