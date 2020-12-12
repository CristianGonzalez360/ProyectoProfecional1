package presentacion.views.vendedor;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

import dto.CaracteristicaVehiculoDTO;
import dto.ClienteDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.ModalidadVentaVehiculoDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import presentacion.views.supervisor.ClientePanelView;

public class VendedorControlView extends JInternalFrame {
	  	
	private static final long serialVersionUID = 1308877516578945407L;

	private static VendedorControlView instance;
	
	private PanelBusquedaPorDniView busquedaPanel;
	
	private ClientePanelView clientePanel;
	
	private BusquedaVehiculoPanel busquedaVehiculoPanel;
	
	private DatosVentaVehiculo datosVentaVehiculoPanel;

	private TablePanel<OutputConsultaVehiculoEnVentaDTO> tableView;
	
	private CaracteristicaDeVehiculoPanel caracteristicaVehiculoPanel;
	
	private PanelCaracteristicasDeLaGarantia garantiaPanel;
	
	private HistorialVentasView panelHistorial;
	
	private JButton btnRegistrarCliente;
	
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
		
		btnRegistrarCliente = new JButton("Registrar Cliente");
		panel_3.add(btnRegistrarCliente);
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		busquedaVehiculoPanel = new BusquedaVehiculoPanel();
		panel_2.add(busquedaVehiculoPanel, BorderLayout.NORTH);
		
		datosVentaVehiculoPanel = new DatosVentaVehiculo();
		panel_2.add(datosVentaVehiculoPanel, BorderLayout.SOUTH);
		datosVentaVehiculoPanel.setNoEditable();
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel_2.add(tabbedPane_1, BorderLayout.CENTER);
		
		this.panelHistorial = HistorialVentasView.getInstance();
		tabbedPane.add("Historial Ventas", this.panelHistorial);	
		
		tableView = new TablePanel<OutputConsultaVehiculoEnVentaDTO>(new String [] {"Código", "Marca", "Familia", "Linea", "Color","Sucursal", "Precio"}) {
			private static final long serialVersionUID = -6912872259496249346L;

			@Override
			public void setData(List<OutputConsultaVehiculoEnVentaDTO> data) {
				for(OutputConsultaVehiculoEnVentaDTO dto : data) {
					Object [] row = { dto.getCodigo(), dto.getMarca(), dto.getFamilia(), dto.getLinea(),dto.getColor(), dto.getSucursal(), dto.getPrecio() };
					model.addRow(row);	
				}
			}

			@Override
			public OutputConsultaVehiculoEnVentaDTO getData() {
				OutputConsultaVehiculoEnVentaDTO ret = null;
				if(table.getSelectedRowCount() == 1) {
					int row = table.getSelectedRow();
					ret = new OutputConsultaVehiculoEnVentaDTO();
					ret.setCodigo(model.getValueAt(row, 0).toString());
					ret.setMarca(model.getValueAt(row, 1).toString());
					ret.setFamilia(model.getValueAt(row, 2).toString());
					ret.setLinea(model.getValueAt(row, 3).toString());
					ret.setColor(model.getValueAt(row, 4).toString());
					ret.setSucursal(model.getValueAt(row, 5).toString());
					ret.setPrecio(model.getValueAt(row, 6).toString());
				}
				return ret;
			}
		};
		
		tabbedPane_1.addTab("Listado de vehículos", null, tableView, null);
		caracteristicaVehiculoPanel = new CaracteristicaDeVehiculoPanel();
		tabbedPane_1.addTab("Caracteristica del Vehículo", null, caracteristicaVehiculoPanel, null);
		
		garantiaPanel = new PanelCaracteristicasDeLaGarantia();
		tabbedPane_1.addTab("Caracteristicas de la garantia", null, garantiaPanel, null);
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
		busquedaPanel.clearData();
		clientePanel.clearData();
		busquedaVehiculoPanel.clearData();
		caracteristicaVehiculoPanel.clearData();
		datosVentaVehiculoPanel.clearData();
		tableView.clearData();
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

	public ConsultaVehiculoParaVentaDTO getDataConsultaVehiculo() {
		return this.busquedaVehiculoPanel.getData();
	}

	public void clearDataVehiculos() {
		this.tableView.clearData();
	}

	public void setData(List<OutputConsultaVehiculoEnVentaDTO> vehiculos) {
		this.tableView.setData(vehiculos);
	}

	public void addTiposBusqueda(String[] tipos) {
		this.busquedaVehiculoPanel.addTipos(tipos);
	}

	public void setActionSelectVehiculo(ListSelectionListener listener) {
		this.tableView.setActionSelect(listener);
	}

	public OutputConsultaVehiculoEnVentaDTO getDataVehiculoEnVenta() {
		return this.tableView.getData();
	}

	public void setData(CaracteristicaVehiculoDTO dto) {
		this.caracteristicaVehiculoPanel.setData(dto);
	}

	public void addFinancieras(List<String> list) {
		this.datosVentaVehiculoPanel.setData(list);
	}

	public void setActionSelectVentaEnEfectivo(ActionListener listener) {
		this.datosVentaVehiculoPanel.setActionSelectVentaEnEfectivo(listener);
	}

	public boolean isVentaEnEfectivo() {
		return this.datosVentaVehiculoPanel.isVentaEnEfectivo();
	}
	
	public void disableVentaFinanciada() {
		this.datosVentaVehiculoPanel.disableFinanciamiento();
	}

	public void enableVentafinanciada() {
		this.datosVentaVehiculoPanel.enableFinanciamiento();
	}
	
	public void setActionRegistrarCliente(ActionListener listener) {
		this.btnRegistrarCliente.addActionListener(listener);
	}

	public void addMarcasBusqueda(List<String> readNombreMarcasVehiculos) {
		this.busquedaVehiculoPanel.addMarcas(readNombreMarcasVehiculos);
	}

	public void setActionRegistrarVenta(ActionListener listener) {
		this.datosVentaVehiculoPanel.setActionRegistrarVenta(listener);
	}

	public Integer getDataCliente() {
		return this.clientePanel.getIdCliente();
	}

	public ModalidadVentaVehiculoDTO getDataModalidadVenta() {
		return this.datosVentaVehiculoPanel.getData();
	}

	public void setDataVentaPrecioVehiculoSeleccionado(String precio) {
		datosVentaVehiculoPanel.setMontoFinanciado(precio);
	}

	public void setDataComisionVendedor(Double comision) {
		datosVentaVehiculoPanel.setComisionVendedor(comision.toString());
	}

	public void setDataPrecioFinal(Double precioFinalVenta) {
		datosVentaVehiculoPanel.setPrecioFinalVenta(precioFinalVenta.toString());
	}
	
	public void setActionUpdateNroCuotas(ChangeListener listener) {
		datosVentaVehiculoPanel.setActionUpdtNroDeCuotas(listener);
	}

	public void setMontoCuota(String montoCuota) {
		datosVentaVehiculoPanel.setMontoCuota(montoCuota);
	}

	public void clearDataModalidadVenta() {
		datosVentaVehiculoPanel.clearData();
	}
	
	public void setMarcas(List<String> marcas) {
		this.busquedaVehiculoPanel.setDataMarcas(marcas);
	}
	
	public PanelCaracteristicasDeLaGarantia getCaracteristicasDeLaGarantiaView() {
		return this.garantiaPanel;
	}
}
