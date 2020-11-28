package presentacion.views.vendedor;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JTabbedPane;

import dto.ClienteDTO;
import presentacion.views.supervisor.ClientePanelView;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;

public class VendedorControlView extends JInternalFrame {
	  	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1308877516578945407L;

	private static VendedorControlView instance;
	
	private PanelBusquedaPorDniView busquedaPanel;
	
	private ClientePanelView clientePanel;
	
	private BusquedaVehiculoPanel busquedaVehiculoPanel;
	
	private DatosVentaVehiculo datosVentaVehiculoPanel;

	private TablePanel tableView;
	
	private CaracteristicaDeVehiculoPanel caracteristicaVehiculoPanel;
	
	private JButton btnRegistrarVenta;
	
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
		setBounds(100, 100, 1055, 739);
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
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		btnRegistrarVenta = new JButton("Registrar venta");
		panel_3.add(btnRegistrarVenta);
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		busquedaVehiculoPanel = new BusquedaVehiculoPanel();
		panel_2.add(busquedaVehiculoPanel, BorderLayout.NORTH);
		
		datosVentaVehiculoPanel = new DatosVentaVehiculo();
		panel_2.add(datosVentaVehiculoPanel, BorderLayout.SOUTH);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(tabbedPane_1, BorderLayout.CENTER);
		tableView = new TablePanel(new String [] {"Código", "Familia","Linea","Cilindrada", "Color", "Precio"});
		tabbedPane_1.addTab("Listado de vehículos", null, tableView, null);
		
		caracteristicaVehiculoPanel = new CaracteristicaDeVehiculoPanel();
		tabbedPane_1.addTab("Caracteristica del Vehículo", null, caracteristicaVehiculoPanel, null);
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

	public void setActionConsultarCliente(ActionListener listener) {
		this.busquedaPanel.setActionBuscar(listener);
	}
	
	public String getData() {
		return this.busquedaPanel.getData();
	}

	public void clearDataCliente() {
		this.clientePanel.clearData();
	}

	public void setData(ClienteDTO cliente) {
		this.clientePanel.setData(cliente);
	}

	public void setActionConsultarVehiculo(ActionListener listener) {
		this.busquedaVehiculoPanel.setActionBuscar(listener);
	}
}
