package presentacion.views.supervisor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.ClienteDTO;
import dto.GarantiaVehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.VehiculoConOrdenDeTrabajoDTO;
import presentacion.views.vendedor.PanelCaracteristicasDeLaGarantia;
import presentacion.views.vendedor.TablePanel;

import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class PanelClientesView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623725856065117794L;
	
	private final String[] columnasTablaVehiculos = new String[] { "NRO. VEHICULO", "KM GARANTIA", "ASEGURADORA",
			"NRO POLIZA SEGURO", "PATENTE" };
	
	private DefaultTableModel tableModelVehiculos;

	private static PanelClientesView instance;
	
	private JButton btnBuscar;
	
	private JSplitPane splitPane;
	
	private JPanel panel;
	
	private JPanel panelDerecho;
	
	private JPanel panel_3;
	
	private TablePanel<VehiculoConOrdenDeTrabajoDTO> table;

	private PanelCliente panelCliente;

	private PanelFichaTecnicaVehiculo panelFichaTecnica;
	
	private JPanel panel_7;
	
	private ToolbarPanelGestionClientes toolBar;
	
	private PanelOrdenDeTrabajo panelOrdenTrabajo;
	
	private PanelCaracteristicasDeLaGarantia garantia;
	
	private JTextField textDniBusqueda;

	public PanelClientesView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.getLayout();
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(panel_4, BorderLayout.NORTH);

		JLabel lblClienteDNI = new JLabel("Cliente DNI");
		panel_4.add(lblClienteDNI);
		
		textDniBusqueda = new JTextField();
		panel_4.add(textDniBusqueda);
		textDniBusqueda.setColumns(10);

		btnBuscar = new JButton("Buscar");
		panel_4.add(btnBuscar);

		splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);

		panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		this.panelCliente = new PanelCliente();
		panel.add(panelCliente);

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Listado de vehiculos del cliente", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		table = new TablePanel<VehiculoConOrdenDeTrabajoDTO>(columnasTablaVehiculos) {

			private static final long serialVersionUID = -2201535451043046243L;

			@Override
			public void setData(List<VehiculoConOrdenDeTrabajoDTO> data) {
				for (VehiculoConOrdenDeTrabajoDTO dto : data) {
					Object[] row = { dto.getId(), dto.getKilometrajeGarantia(), dto.getAseguradora(), dto.getNroPolizaSeguro(),
							dto.getPatente() };
					this.model.addRow(row);
				}
			}

			@Override
			public VehiculoConOrdenDeTrabajoDTO getData() {
				int rows = this.table.getSelectedRowCount();
				if (rows == 1) {
					int row = this.table.getSelectedRow();
					VehiculoConOrdenDeTrabajoDTO dto = new VehiculoConOrdenDeTrabajoDTO();
					dto.setId(Integer.parseInt(model.getValueAt(row, 0).toString()));
					return dto;
				}
				return null;
			}
			
		};
		
		panel_7 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_7.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3.add(panel_7, BorderLayout.SOUTH);

		toolBar = new ToolbarPanelGestionClientes();
		panel_7.add(toolBar);

		panelDerecho = new JPanel();
		splitPane.setRightComponent(panelDerecho);
		
		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Ficha tecnica del vehiculo", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		
		this.panelFichaTecnica = new PanelFichaTecnicaVehiculo();
		panelDerecho.add(panelFichaTecnica);
		
		garantia = new PanelCaracteristicasDeLaGarantia();
		panelDerecho.add(garantia);

		panelOrdenTrabajo = new PanelOrdenDeTrabajo();
		panelDerecho.add(panelOrdenTrabajo);
		
		disableAllClienteInputs();
		disableAllFichaTecnicaInputs();
	}

	private void disableAllClienteInputs() {
		this.panelCliente.disableAllClienteInputs();
	}

	private void disableAllFichaTecnicaInputs() {
		this.panelFichaTecnica.disableAllFichaTecnicaInputs();
	}

	public static PanelClientesView getInstance() {
		if (instance == null) {
			instance = new PanelClientesView();
		}
		return instance;
	}

	public void clearDataCliente() {
		this.panelCliente.clearData();
	}

	public void clearDataFichaTecnicaVehiculo() {
		this.panelFichaTecnica.clearDataFichaTecnicaVehiculo();
	}

	public void clearDataOrdenDeTrabajo() {
		this.panelOrdenTrabajo.clearDataOrdenDeTrabajo();
	}

	public void setData(ClienteDTO cliente) {
		this.panelCliente.setData(cliente);
	}

	public void setData(FichaTecnicaVehiculoDTO fichaVehiculo) {
		fichaVehiculo.getId();
		this.panelFichaTecnica.setData(fichaVehiculo);
	}

	public void setData(OrdenDeTrabajoDTO ordenDeTrabajo) {
		this.panelOrdenTrabajo.setData(ordenDeTrabajo);
	}

	public void setData(List<VehiculoConOrdenDeTrabajoDTO> vehiculos) {

	}

	public VehiculoConOrdenDeTrabajoDTO getidVehiculoSeleccionado() {
		return this.table.getData();
	}

	public String dniClienteSeleccionado() {
		return this.panelCliente.getData().getDatosPersonalesDTO().getDni().toString();
	}

	public void setActionSelectVehiculoCliente(ListSelectionListener listener) {
		this.table.setActionSelect(listener);
	}

	public void setActionBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}
	
	public void setActionRegistrarCliente(ActionListener listener) {
		this.toolBar.setActionRegistrarNuevoCliente(listener);
	}

	public void setActionRegistrarVehiculo(ActionListener listener) {
		this.toolBar.setActionRegistrarNuevoVehiculo(listener);
	}

	public void setActionRegistrarOrdenDeTrabajo(ActionListener listener) {
		this.toolBar.setActionRegistrarOrdenDeTrabajo(listener);
	}

	public void setActionOnEditarCliente(ActionListener listener) {
		this.toolBar.setActionEditarCliente(listener);
	}

	public void clearAll() {
		this.clearDataCliente();
		this.clearDataFichaTecnicaVehiculo();
		this.table.clearData();
		this.clearDataOrdenDeTrabajo();
		this.garantia.clearData();
	}

	public void lockButtonRegistrarOrdenDeTrabajo() {
		this.toolBar.lockButtonRegistrarOrdenDeTrabajo();
	}

	public void unlockButtonRegistrarOrdenDeTrabajo() {
		this.toolBar.unlockButtonRegistrarOrdenDeTrabajo();
	}

	public void setDataGarantia(GarantiaVehiculoDTO garantia) {
		this.garantia.setData(garantia);
	}

	public void clearDataGarantia() {
		this.garantia.clearData();
	}

	public String getDniCliente() {
		return this.textDniBusqueda.getText() != null ? this.textDniBusqueda.getText() : "";
	}

	public void clearDataListadoVehiculosCliente() {
		this.table.clearData();
	}
}