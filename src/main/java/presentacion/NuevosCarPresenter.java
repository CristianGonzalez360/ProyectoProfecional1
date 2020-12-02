package presentacion;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import business_logic.VentasVehiculosController;
import dto.CaracteristicaVehiculoDTO;
import dto.VehiculoDTO;
import presentacion.views.gerente.GerenteNuevosCar;
import presentacion.views.gerente.NuevosVehiculosFormView;
import presentacion.views.utils.MessageDialog;
import presentacion.views.vendedor.VendedorControlView;
import services.DatabaseGraphVehiculoNuevo;

public class NuevosCarPresenter {
	
	private GerenteNuevosCar view;
	private NuevosVehiculosFormView nuevosVehiculos;
	private DatabaseGraphVehiculoNuevo vehiculosGraph;
	private VentasVehiculosController ventasVehiculosController;
	private static final String All= "Todas";
	
	public NuevosCarPresenter(GerenteNuevosCar view, VentasVehiculosController ventasVehiculosController) {
		this.ventasVehiculosController = ventasVehiculosController;
		this.view = view;
		this.nuevosVehiculos = NuevosVehiculosFormView.getInstance();
		
		this.view.setActionOnCargarArchivo((a) -> onCargarArchivo(a));
		this.nuevosVehiculos.setActionOnCancelarCarga((a)->onCancelarCarga(a));
		this.nuevosVehiculos.setActionOnValidadCarga((a)->onValidarCarga(a));
		
		this.view.setActionSelectVehiculo((a)->onSelectVehiculo(a));
		this.view.setActionOnBuscar((a)->onBuscar(a));
		this.cargarMarcas();
		this.cargarTodosVehiculos();
	}

	private void onCargarArchivo(ActionEvent a) {
		vehiculosGraph = null;

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.YML", "yml");
		chooser.setFileFilter(filtro);
		chooser.setAcceptAllFileFilterUsed(false);
		int seleccion = chooser.showOpenDialog(null);
		
		if(seleccion==JFileChooser.APPROVE_OPTION) {
			try {
				Yaml yaml = new Yaml(new Constructor(DatabaseGraphVehiculoNuevo.class));
				InputStream inputStream = new FileInputStream(chooser.getSelectedFile().getAbsolutePath());//FileInputStream cambio
				vehiculosGraph = yaml.load(inputStream);
				this.nuevosVehiculos.cargarTabla(vehiculosGraph.getVehiculos());
				this.nuevosVehiculos.mostrar();
				
			} catch (Exception e) {
				new MessageDialog().showMessages("El archivo esta vacío, por favor revisar el contenido del archivo");
			}
		}
		
	}
	
	private void onValidarCarga(ActionEvent a) {
		List<Integer> idRepuestosNoAceptados = this.nuevosVehiculos.getIdVehiculosNoAceptados();
		int j=0;
		int k=0;
		List<VehiculoDTO> vehiculosNuevos = vehiculosGraph.getVehiculos();
		ListIterator<VehiculoDTO> it = vehiculosNuevos.listIterator();
		
		while(it.hasNext()&&j<idRepuestosNoAceptados.size() ) {//quito los que selecciono
			it.next();
			if(idRepuestosNoAceptados.get(j)==k) {		
				it.remove();
				j++;
			}
			k++;
		}
		
		this.ventasVehiculosController.saveVehiculosNuevos(vehiculosNuevos);
		this.view.clear();
		this.cargarTodosVehiculos();
		this.cargarMarcas();
		this.nuevosVehiculos.cerrar();
		this.nuevosVehiculos.clear();
		List<String> marcas = new ArrayList<String>();
		marcas.addAll(ventasVehiculosController.readNombreMarcasVehiculos());
		VendedorControlView.getInstance().clearData();
		VendedorControlView.getInstance().setMarcas(marcas);
	}

	private void onCancelarCarga(ActionEvent a) {
		this.nuevosVehiculos.cerrar();
		this.nuevosVehiculos.clear();
	}
	
	public void cargarMarcas() {
		List<String> marcas = new ArrayList<String>();
		marcas.add(All);
		marcas.addAll(ventasVehiculosController.readNombreMarcasVehiculos());
		this.view.setDataMarcas(marcas);
	}
	
	public void cargarTodosVehiculos() {
		this.view.cargarTabla(ventasVehiculosController.readAllVehiculoNuevos());
	}
	
	private void onSelectVehiculo(ListSelectionEvent a) {
		Integer idVehiculoSeleccionado = view.getIdVehiculo();
		if(idVehiculoSeleccionado!=-1) {
			CaracteristicaVehiculoDTO caracteristicas = ventasVehiculosController.readCaracteristicaVehiculoByIdVehiculo(idVehiculoSeleccionado);
			this.view.cargarCaracteristica(caracteristicas);	
		}
	}
	
	private void onBuscar(ActionEvent a) {
		String marcaSeleccionada = this.view.getMarca();
		if(marcaSeleccionada.equals(new String("Todas"))) {
			this.view.clear();
			this.cargarTodosVehiculos();
		}else {
			List<VehiculoDTO> vehiculosNuevos = ventasVehiculosController.readAllVehiculoNuevos();
			List<VehiculoDTO> vehiculosPorMarca = new ArrayList<VehiculoDTO>();
			for (VehiculoDTO vehiculo : vehiculosNuevos) {
				if(vehiculo.getMarca().equals(marcaSeleccionada)) {
					vehiculosPorMarca.add(vehiculo);
				}
			}
			this.cargarVehiculosPorMarca(vehiculosPorMarca);
		}
	}
	
	private void cargarVehiculosPorMarca(List<VehiculoDTO> vehiculosPorMarca) {
		this.view.clear();
		this.view.cargarTabla(vehiculosPorMarca);
	}
}
