package presentacion;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import presentacion.views.gerente.GerenteNuevosCar;
import presentacion.views.gerente.NuevosVehiculosFormView;
import presentacion.views.utils.MessageDialog;
import services.DatabaseGraphVehiculoNuevo;

public class NuevosCarPresenter {
	
	private GerenteNuevosCar view;
	private NuevosVehiculosFormView nuevosVehiculos;
	private DatabaseGraphVehiculoNuevo vehiculosGraph;
	
	public NuevosCarPresenter(GerenteNuevosCar view) {
		this.view = view;
		this.nuevosVehiculos = NuevosVehiculosFormView.getInstance();
		
		this.view.setActionOnCargarArchivo((a) -> onCargarArchivo(a));
		this.nuevosVehiculos.setActionOnCancelarCarga(a -> onCancelarCarga(a));
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
//				System.out.println(vehiculosGraph.getVehiculos());
//				System.out.println(vehiculosGraph.getCaracteristicas());
//				this.nuevosVehiculos.cargarTabla(vehiculosGraph.getVehiculos());
//				this.nuevosVehiculos.mostrar();
				
			} catch (Exception e) {
//				new MessageDialog().showMessages("El archivo esta vacío, por favor revisar el contenido del archivo");
				System.out.println(e);
			}
		}
		System.out.println(vehiculosGraph.getCaracteristicas());

		
	}

	private void onCancelarCarga(ActionEvent a) {
		this.nuevosVehiculos.cerrar();
		this.nuevosVehiculos.clear();
	}
	
}
