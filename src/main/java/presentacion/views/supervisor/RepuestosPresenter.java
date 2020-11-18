package presentacion.views.supervisor;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import business_logic.RepuestosController;
import dto.RepuestoDTO;
import services.DatabaseGraph;

public class RepuestosPresenter {
	
	private PanelGestionRepuestos gestionRepuestos;
	private RepuestosController repuestosController;
	private NuevosRepuestosFormView nuevosRepuestosView;
	private DatabaseGraph repuestosGraph;
	
	public RepuestosPresenter(RepuestosController controller) {
		this.repuestosController = controller;
		this.nuevosRepuestosView = NuevosRepuestosFormView.getInstance();
		this.gestionRepuestos = PanelGestionRepuestos.getInstance();
		this.gestionRepuestos.setActionOnBuscar(a -> onBuscarRepuesto(a));
		this.gestionRepuestos.setActionOnIngresarStock(a -> onIngresarStock(a));
		this.gestionRepuestos.setActionOnCargarArchivo(a -> onCargarArchivo(a));
		
		this.nuevosRepuestosView.setActionOnValidadCarga(a -> onValidarCarga(a));
		this.nuevosRepuestosView.setActionOnCancelarCarga(a -> onCancelarCarga(a));
		
		cargarMarcas();
	}
	
	
	private void onCancelarCarga(ActionEvent a) {
		this.nuevosRepuestosView.cerrar();//cerrar
		this.nuevosRepuestosView.clear();//limpiar la vista tambn
	}


	private void onCargarArchivo(ActionEvent a) {
		
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.YML", "yml");//filtrar archivos
		chooser.setFileFilter(filtro);
		chooser.setAcceptAllFileFilterUsed(false);//quita opcion todos los archivos
		int seleccion = chooser.showOpenDialog(null);
		
		if(seleccion==JFileChooser.APPROVE_OPTION) {
			try {
				LogManager.getLogger(this.getClass()).log(Level.INFO,
						"Seed database operation status: [INITIALIZED - LOADING .YML]");
				Yaml yaml = new Yaml(new Constructor(DatabaseGraph.class));
				InputStream inputStream = new FileInputStream(chooser.getSelectedFile().getAbsolutePath());//FileInputStream cambio
				repuestosGraph = yaml.load(inputStream);
				this.nuevosRepuestosView.cargarTabla(repuestosGraph.getRepuestos());
				this.nuevosRepuestosView.mostrar();
				
			} catch (Exception e) {
				LogManager.getLogger(this.getClass()).log(Level.ERROR,
						"Seed database operation status: [ABORT - ERROR LOADING .YML, " + e.getMessage() + "]");
			}
		}
	}

	private void onValidarCarga(ActionEvent a) {
		List<Integer> idRepuestosNoAceptados = this.nuevosRepuestosView.getIdRepuestosNoAceptados();//obtengo id de repuestos que estoy leyendo que no fueron aceptados para cargar
		int codigo;
		List<RepuestoDTO> repuestosCargados = this.repuestosGraph.getRepuestos();//lista con todos los repuestos del archivo
		ListIterator<RepuestoDTO> it = repuestosCargados.listIterator();
		RepuestoDTO repuestoAuxiliar;
		while(it.hasNext()) {//elimino los no seleccionados
			
			codigo=it.next().getCodigoRepuesto();
			for(int i=0;i<idRepuestosNoAceptados.size();i++) {
				if(codigo==idRepuestosNoAceptados.get(i)) {
					it.remove();
				}
			}
		}
		
		it = repuestosCargados.listIterator();
		while(it.hasNext()) {//busco los que ya existen y actualizo la cantidad de repuestos
			
			repuestoAuxiliar=it.next();			
			if(repuestosController.readByCodigo(repuestoAuxiliar.getCodigoRepuesto()) != null) {
				repuestoAuxiliar.setStockRepuesto(repuestoAuxiliar.getStockRepuesto()+repuestosController.readByCodigo(repuestoAuxiliar.getCodigoRepuesto()).getStockRepuesto());
			}
		}

		it = repuestosCargados.listIterator();
		while(it.hasNext()) {//inserto en la base de datos.
			repuestoAuxiliar=it.next();
			repuestoAuxiliar.setDescripcionRepuesto(repuestoAuxiliar.getDescripcionRepuesto().toLowerCase());
			repuestosController.saveByCodigo(repuestoAuxiliar);
		}
		
		this.nuevosRepuestosView.cerrar();//cerrar
		this.nuevosRepuestosView.clear();//limpiar la vista tambn
		cargarMarcas();//actualizo combobox
		
		List<RepuestoDTO> repuestos = repuestosController.readAll();
		this.gestionRepuestos.setData(repuestos);//actualizo tabla repuestos
		
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
