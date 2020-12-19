package presentacion.views.supervisor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JSplitPane;
import dto.ClienteDTO;
import dto.GarantiaVehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.IngresoOrdenDeTrabajoDTO;
import presentacion.views.vendedor.PanelCaracteristicasDeLaGarantia;
import presentacion.views.vendedor.TablePanel;

import javax.swing.BoxLayout;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

public class PanelClientesView extends JPanel {

	private static final long serialVersionUID = -5623725856065117794L;

	private final String[] columnasTablaVehiculos = new String[] { "NRO. VEHICULO", "KM GARANTIA", "ASEGURADORA",
			"NRO. POLIZA SEGURO", "PATENTE" };

	private static PanelClientesView instance;

	private JButton btnBuscar;

	private JSplitPane splitPane;

	private JPanel panel;

	private JPanel panelDerecho;

	private TablePanel<IngresoOrdenDeTrabajoDTO> table;

	private PanelCliente panelCliente;

	private JTextField textDniBusqueda;

	private JTabbedPane tabbedPane;

	private PanelFichaTecnicaVehiculo panelFichaTecnica;

	private PanelCaracteristicasDeLaGarantia garantia;

	private PanelOrdenDeTrabajo panelOrdenTrabajo;

	private JPanel panel_1;

	private ToolbarPanelGestionClientes toolBar;
	private JButton btnReporteVehiculo;

	public PanelClientesView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.getLayout();
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(panel_4, BorderLayout.NORTH);

		JLabel lblClienteDNI = new JLabel("DNI");
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

		panel_1 = new JPanel();
		panel.add(panel_1);

		toolBar = new ToolbarPanelGestionClientes();
		panel_1.add(toolBar);

		table = new TablePanel<IngresoOrdenDeTrabajoDTO>(columnasTablaVehiculos) {

			private static final long serialVersionUID = -2201535451043046243L;

			@Override
			public void setData(List<IngresoOrdenDeTrabajoDTO> data) {
				for (IngresoOrdenDeTrabajoDTO dto : data) {
					Object[] row = { dto.getId(), dto.getKilometrajeGarantia(), dto.getAseguradora(),
							dto.getNroPolizaSeguro(), dto.getPatente() };
					this.model.addRow(row);
				}
			}

			@Override
			public IngresoOrdenDeTrabajoDTO getData() {
				int rows = this.table.getSelectedRowCount();
				if (rows == 1) {
					int row = this.table.getSelectedRow();
					IngresoOrdenDeTrabajoDTO dto = new IngresoOrdenDeTrabajoDTO();
					dto.setId(Integer.parseInt(model.getValueAt(row, 0).toString()));
					return dto;
				}
				return null;
			}

		};

		panelDerecho = new JPanel();
		splitPane.setRightComponent(panelDerecho);

		panelDerecho.add(table);

		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));

		btnReporteVehiculo = new JButton("Historial del Vehiculo");
		panelDerecho.add(btnReporteVehiculo);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelDerecho.add(tabbedPane);

		panelFichaTecnica = new PanelFichaTecnicaVehiculo();
		tabbedPane.addTab("Ficha técnica", null, panelFichaTecnica, null);

		garantia = new PanelCaracteristicasDeLaGarantia();
		tabbedPane.addTab("Garantía", null, garantia, null);

		panelOrdenTrabajo = new PanelOrdenDeTrabajo();
		tabbedPane.addTab("Orden de trabajo", null, panelOrdenTrabajo, null);

		this.panelCliente.disableInputs();
	}

	public static PanelClientesView getInstance() {
		if (instance == null) {
			instance = new PanelClientesView();
		}
		return instance;
	}

	public void clearAll() {
		this.clearDataCliente();
		this.clearDataFichaTecnicaVehiculo();
		this.table.clearData();
		this.clearDataOrdenDeTrabajo();
		this.garantia.clearData();
	}

	public void clearDataCliente() {
		this.panelCliente.clearData();
	}

	public void clearDataFichaTecnicaVehiculo() {
		this.panelFichaTecnica.clearData();
	}

	public void clearDataOrdenDeTrabajo() {
		this.panelOrdenTrabajo.clearData();
	}

	public void clearDataListadoVehiculosCliente() {
		this.table.clearData();
	}

	public void clearDataGarantia() {
		this.garantia.clearData();
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

	public void setData(List<IngresoOrdenDeTrabajoDTO> vehiculos) {
		this.table.setData(vehiculos);
	}

	public Integer getidVehiculoSeleccionado() {
		return table.getData() != null ? table.getData().getId() : null;
	}

	public String dniClienteSeleccionado() {
		return this.panelCliente.getData().getDatosPersonalesDTO().getDni().toString();
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

	public String getDniCliente() {
		return this.textDniBusqueda.getText() != null ? this.textDniBusqueda.getText() : "";
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
	
	public void setActionReporteDeVehiculo(ActionListener listener) {
		this.btnReporteVehiculo.addActionListener(listener);
	}
	
	public void setUpdateKilometrajeOnFichaTecnica(String kilometrajeActual) {
		this.panelFichaTecnica.updateKilometraje(kilometrajeActual);
	}

	public FichaTecnicaVehiculoDTO getDataFichaTechica() {
		return this.panelFichaTecnica.getData();
	}

	public GarantiaVehiculoDTO getDataGarantia() {
		return this.garantia.getData();
	}
}