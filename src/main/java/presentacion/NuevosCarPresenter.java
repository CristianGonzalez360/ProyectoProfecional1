package presentacion;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import business_logic.VehiculosController;
import business_logic.VentasVehiculosController;
import dto.CaracteristicaVehiculoDTO;
import dto.GarantiaVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
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
	private VehiculosController	vehiculosController;
	private GarantiaVehiculoDTO garantiaDefault;
	private static final String All= "Todas";
	
	public NuevosCarPresenter(GerenteNuevosCar view, VentasVehiculosController ventasVehiculosController, VehiculosController vehiculosController) {
		this.ventasVehiculosController = ventasVehiculosController;
		this.vehiculosController = vehiculosController;
		this.view = view;
		this.generarGarantiaDefault();
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
				
				if(validarArchivo()) {
					if(validarFichasTecnicasMotor()) {
						if(validarFichasTecnicasChasis()) {
							this.nuevosVehiculos.cargarTabla(vehiculosGraph.getVehiculos());
							this.nuevosVehiculos.mostrar();
						}else {
							new MessageDialog().showMessages("El archivo contiene:\n-Nro de chasis\nexistentes.\nPor favor revisar el contenido del archivo.");						
						}
					} else {
						new MessageDialog().showMessages("El archivo contiene:\n-Nro de motor\nexistentes.\nPor favor revisar el contenido del archivo.");
					}
				} else {
					new MessageDialog().showMessages("El archivo debe tener por cada vehiculo nuevo: \n-una caracteristica  \n-una ficha tecnica.\nPor favor revisar el contenido del archivo.");
				}
			} catch (Exception e) {
				new MessageDialog().showMessages("El archivo esta vacío, por favor revisar el contenido del archivo.");
			}
		}
	}
	
	private void onValidarCarga(ActionEvent a) {
		List<Integer> idAutosNoAceptados = this.nuevosVehiculos.getIdVehiculosNoAceptados();
		List<VehiculoDTO> vehiculosNuevos = vehiculosGraph.getVehiculos();
		List<CaracteristicaVehiculoDTO> caracteristicas = vehiculosGraph.getCaracteristicas();
		List<FichaTecnicaVehiculoDTO> fichasTecnicas = vehiculosGraph.getFichaTecnica();
		
		//cada vehiculo tiene 1 caracteristica y 1 ficha tecnica
		quitarVehiculosNoAceptados(idAutosNoAceptados, vehiculosNuevos);//quito los no aceptados
		quitarCaracteristicasNoAceptados(idAutosNoAceptados, caracteristicas);
		quitarFichastecnicasNoAceptadas(idAutosNoAceptados,fichasTecnicas);

		if(vehiculosNuevos.size()>0) {
			for(int i=0;i<vehiculosNuevos.size();i++) {
				vehiculosNuevos.get(i).setIdCaracteristicas(vehiculosController.guardarCaracteristicaNueva(caracteristicas.get(i)));//me devuelve el id de la caracteristica que guarde
				vehiculosNuevos.get(i).setIdFichaTecnica(vehiculosController.guardarFichaTecnicaNueva(fichasTecnicas.get(i)));//devuelve el id de la ficha que guarde
				vehiculosNuevos.get(i).setFechaIngreso(new Date());
				garantiaDefault.setIdVehiculo((vehiculosController.guardarVehiculoNuevo(vehiculosNuevos.get(i))));//devuelve el id del vehiculo que guarde
				vehiculosController.guardarGarantiaNuevo(garantiaDefault);
			}
		}
				
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

	private void quitarVehiculosNoAceptados(List<Integer> idAutosNoAceptados, List<VehiculoDTO> vehiculosNuevos) {
		int k=0,j=0;
		ListIterator<VehiculoDTO> it = vehiculosNuevos.listIterator();
		
		while(it.hasNext()&&j<idAutosNoAceptados.size() ) {//quito los que selecciono
			it.next();
			if(idAutosNoAceptados.get(j)==k) {		
				it.remove();
				j++;
			}
			k++;
		}
	}

	private void quitarCaracteristicasNoAceptados(List<Integer> idAutosNoAceptados, List<CaracteristicaVehiculoDTO> caracteristicasNuevas) {
		int k=0,j=0;
		ListIterator<CaracteristicaVehiculoDTO> it = caracteristicasNuevas.listIterator();
		
		while(it.hasNext()&&j<idAutosNoAceptados.size() ) {//quito los que selecciono
			it.next();
			if(idAutosNoAceptados.get(j)==k) {		
				it.remove();
				j++;
			}
			k++;
		}
	}
	
	private void quitarFichastecnicasNoAceptadas(List<Integer> idAutosNoAceptados, List<FichaTecnicaVehiculoDTO> fichasTecnicas) {
		int k=0,j=0;
		ListIterator<FichaTecnicaVehiculoDTO> it = fichasTecnicas.listIterator();
		
		while(it.hasNext()&&j<idAutosNoAceptados.size() ) {//quito los que selecciono
			it.next();
			if(idAutosNoAceptados.get(j)==k) {		
				it.remove();
				j++;
			}
			k++;
		}
		
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
	
	public void cargarTodosVehiculos() {//modificado
		List <VehiculoDTO> vehiculos = ventasVehiculosController.readAllVehiculoNuevos();
		List <VehiculoDTO> vehiculosNuevos = new ArrayList<VehiculoDTO>();
		for (VehiculoDTO vehiculo : vehiculos) {
			if(vehiculo.isDisponible()&&!vehiculo.isUsado()) {
				vehiculosNuevos.add(vehiculo);
			}
		}
		this.view.cargarTabla(vehiculosNuevos);
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
					if(vehiculo.isDisponible()&&!vehiculo.isUsado()) {
						vehiculosPorMarca.add(vehiculo);
					}
				}
			}
			this.cargarVehiculosPorMarca(vehiculosPorMarca);
		}
	}
	
	private void cargarVehiculosPorMarca(List<VehiculoDTO> vehiculosPorMarca) {
		this.view.clear();
		this.view.cargarTabla(vehiculosPorMarca);
	}
	

	private void generarGarantiaDefault() {
		garantiaDefault = new GarantiaVehiculoDTO();
		garantiaDefault.setAniosDeGarantia(3);
		garantiaDefault.setKilometrajeInicialDelVehiculo(0);
		garantiaDefault.setKilometrajeGarantizado(150000);
		garantiaDefault.setFechaInicioDeLaGarantia(new Date());
		Calendar hoy = Calendar.getInstance();
		hoy.add(Calendar.YEAR, 3);
		Date fechaVencimiento = hoy.getTime();
		garantiaDefault.setFechaDeCaducidadDeLaGarantia(fechaVencimiento);
		garantiaDefault.setCostoFinalConIVA(15000.0);//cuanto?
	}
	
	private boolean validarArchivo() {
		
		if(vehiculosGraph.getVehiculos().size()==vehiculosGraph.getCaracteristicas().size()&&vehiculosGraph.getCaracteristicas().size()==vehiculosGraph.getFichaTecnica().size()) {
			return true;
		}
		return false;
	}
	
	private boolean validarFichasTecnicasMotor() {
		List<FichaTecnicaVehiculoDTO> fichasTecnicas = vehiculosGraph.getFichaTecnica();
		for (FichaTecnicaVehiculoDTO fichaTecnica : fichasTecnicas) {
//			fichaTecnica.getNroChasis();
			if(vehiculosController.isNroMotorExistente(fichaTecnica.getNroMotor())==true) {//hay un nro motor existente
				return false;
			}
		}
		return true;
	}
	
	private boolean validarFichasTecnicasChasis() {
		List<FichaTecnicaVehiculoDTO> fichasTecnicas = vehiculosGraph.getFichaTecnica();
		for (FichaTecnicaVehiculoDTO fichaTecnica : fichasTecnicas) {
			if(vehiculosController.isNroChasisExistente(fichaTecnica.getNroChasis())==true) {//hay un nro chasis existente
				return false;
			}
		}
		return true;
	}
}
