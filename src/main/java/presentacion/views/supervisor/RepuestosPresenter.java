package presentacion.views.supervisor;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import business_logic.RepuestosController;
import dto.RepuestoDTO;

public class RepuestosPresenter {
	
	private PanelGestionRepuestos gestionRepuestos;
	private RepuestosController repuestosController;
	
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
		// TODO Auto-generated method stub
	}
	
	public void cargarMarcas() {
		List<String> marcas = new ArrayList<String>();
		marcas.add("todas");
		marcas.addAll(repuestosController.readMarcas());
		this.gestionRepuestos.setDataMarcas(marcas);
	}

	//Busca repuestos segun criterio seleccionado
	private void onBuscarRepuesto(ActionEvent a) {
		String marca = gestionRepuestos.getMarca();
		String descripcion = gestionRepuestos.getDescripcion();
		List<RepuestoDTO> repuestos;
		if(descripcion.isEmpty()) {
			if(marca == "todas") {
				repuestos = repuestosController.readAll();
			} else {
				repuestos = repuestosController.readByMarca(marca);
			}
		} else {
			if(marca == "todas") {
				repuestos = repuestosController.readByDescripcion(descripcion);
			} else {
				repuestos = repuestosController.readbyMarcaYDescripcion(marca, descripcion);
			}
		}
		gestionRepuestos.setData(repuestos);
	}
}
