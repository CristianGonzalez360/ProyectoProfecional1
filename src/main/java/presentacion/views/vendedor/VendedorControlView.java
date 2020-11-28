package presentacion.views.vendedor;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JTabbedPane;

import presentacion.views.supervisor.ClientePanelView;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

public class VendedorControlView extends JInternalFrame {
	  	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1308877516578945407L;

	private static VendedorControlView instance;
	
	private PanelBusquedaPorDniView busquedaPanel;
	
	private ClientePanelView clientePanel;
	
	private BusquedaVehiculoPanel busquedaVehiculoPanel;
	
	private TableView tablaVehiculos;
	
	private CaracteristicaDeVehiculoPanel caracteristicaVehiculoPanel;
	
	private DatosVentaVehiculo datosVentaVehiculoPanel;
	
	public static VendedorControlView getInstance() {
		if (instance == null)
			instance = new VendedorControlView();
		return instance;
	}

	private VendedorControlView() {
		setClosable(false);
		setTitle("Vendedor Control View");
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 1055, 516);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Venta de vehículos", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		busquedaPanel = new PanelBusquedaPorDniView();
		panel_1.add(busquedaPanel);
		clientePanel = new ClientePanelView();
		panel_1.add(clientePanel);
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		busquedaVehiculoPanel = new BusquedaVehiculoPanel();
		panel_2.add(busquedaVehiculoPanel, BorderLayout.NORTH);
		
		JPanel panelListadoVehiculos = new JPanel();
		panel_2.add(panelListadoVehiculos, BorderLayout.CENTER);
		panelListadoVehiculos.setLayout(new BoxLayout(panelListadoVehiculos, BoxLayout.X_AXIS));
		
		tablaVehiculos = new TableView(new String [] {"Código", "Familia", "Cilindrada", "Precio", "Sucursal"});
		tablaVehiculos.setBorder(new TitledBorder(null, "Listado de vehículos"));
		panelListadoVehiculos.add(tablaVehiculos);
		
		caracteristicaVehiculoPanel = new CaracteristicaDeVehiculoPanel();
		caracteristicaVehiculoPanel.setBorder(new TitledBorder(null, "Caracter\u00EDsticas del veh\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelListadoVehiculos.add(caracteristicaVehiculoPanel);
		
		datosVentaVehiculoPanel = new DatosVentaVehiculo();
		panel_2.add(datosVentaVehiculoPanel, BorderLayout.SOUTH);
	}

	public void display() {
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}

	public void clearData() {

	}
}
