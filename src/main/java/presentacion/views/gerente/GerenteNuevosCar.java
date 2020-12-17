package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import dto.CaracteristicaVehiculoDTO;
import dto.VehiculoDTO;

@SuppressWarnings("serial")
public class GerenteNuevosCar extends JPanel {

	private static final String[] nombreColumnasAuto = { "Código", "Marca", "Familia", "Linea", "Color", "Precio", "Deposito" };

	private JPanel panelSuperior = new JPanel();
	private JPanel panelInterior = new JPanel();
	private JComboBox<String> comboMarcas;
	private JButton btnBuscar;
	private JScrollPane scrollPaneRepuestos;
	private DefaultTableModel modelAutosNuevos;
	private JTable tablaAutosNuevos;
	private static GerenteNuevosCar vista;
	private JPanel panel_1;
	private CaracteristicaNuevoCarView panelCaracteristicas;
	private JSplitPane splitPane;
	private JPanel panelBotonesVehiculos;
	private JButton btnCargarArchivo;
	private JLabel lblMarca;

	private GerenteNuevosCar() {
		setBounds(100, 100, 812, 616);
		setLayout(new BorderLayout());

		modelAutosNuevos = new DefaultTableModel(null, nombreColumnasAuto) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JPanel panelIzquierdo = new JPanel();
		splitPane = new JSplitPane();
		splitPane.setEnabled(true);
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);
		splitPane.setLeftComponent(panelIzquierdo);

		JPanel panelDerecho = new JPanel(new BorderLayout());

		panelCaracteristicas = CaracteristicaNuevoCarView.getInstance();
		panelDerecho.add(panelCaracteristicas, BorderLayout.CENTER);

		splitPane.setRightComponent(panelDerecho);

		panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));

		panelSuperior = new JPanel();
		panelSuperior.setForeground(SystemColor.menu);
		panelSuperior.setBorder(
				new TitledBorder(null, "Vehiculos para la venta", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelSuperior.setBackground(SystemColor.menu);
		panelIzquierdo.add(panelSuperior);
		panelSuperior.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		panelSuperior.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblMarca = new JLabel("Marca");
		panel_1.add(lblMarca);

		comboMarcas = new JComboBox<>();
		panel_1.add(comboMarcas);

		btnBuscar = new JButton("Buscar");
		panel_1.add(btnBuscar);

		panelInterior = new JPanel();
		panelInterior.setBackground(SystemColor.menu);
		panelSuperior.add(panelInterior, BorderLayout.CENTER);
		panelInterior.setLayout(new BorderLayout(0, 0));
		tablaAutosNuevos = new JTable(modelAutosNuevos) {
			@Override
			public Class<?> getColumnClass(int column) {
				return column == 6? Boolean.class : super.getColumnClass(column);
			}
		};
		scrollPaneRepuestos = new JScrollPane(tablaAutosNuevos);
		panelInterior.add(scrollPaneRepuestos, BorderLayout.CENTER);

		panelBotonesVehiculos = new JPanel();
		panelSuperior.add(panelBotonesVehiculos, BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) panelBotonesVehiculos.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		btnCargarArchivo = new JButton("Cargar desde archivo");
		panelBotonesVehiculos.add(btnCargarArchivo);

	}

	public static GerenteNuevosCar getInstance() {
		if (vista == null)
			vista = new GerenteNuevosCar();
		return vista;
	}

	public void setActionSelectVehiculo(ListSelectionListener listSelectionListener) {
		this.tablaAutosNuevos.getSelectionModel().addListSelectionListener(listSelectionListener);
	}

	public void clearDataVehiculos() {
		modelAutosNuevos.setRowCount(0);
	}

	public void display() {
		setVisible(true);
	}

	public void cargarTabla(List<VehiculoDTO> vehiculos) {
		for (VehiculoDTO vehiculo : vehiculos) {
			Object[] row = { vehiculo.getIdVehiculo(), vehiculo.getMarca(), vehiculo.getFamilia(), vehiculo.getLinea(),
					vehiculo.getColor(), vehiculo.getPrecioVenta(), vehiculo.isDisponible() };
			modelAutosNuevos.addRow(row);
		}
	}

	public Integer getIdVehiculo() {
		Integer ret = -1;
		int rows = this.tablaAutosNuevos.getSelectedRowCount();
		if (rows == 1) {
			int row = tablaAutosNuevos.getSelectedRow();
			ret = (Integer) tablaAutosNuevos.getValueAt(row, 0);
		}
		return ret;
	}

	public void close() {
		setVisible(false);
	}

	public void setActionOnBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}

	public void setActionOnCargarArchivo(ActionListener listener) {
		this.btnCargarArchivo.addActionListener(listener);
	}

	public String getMarca() {
		return (String) comboMarcas.getSelectedItem();
	}

//	public String getDescripcion() {
//		return textDescipcion.getText();
//	}

	public void setDataMarcas(List<String> marcas) {
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
		for (String marca : marcas) {
			modelo.addElement(marca);
		}
		comboMarcas.setModel(modelo);
	}

	public void cargarCaracteristica(CaracteristicaVehiculoDTO caracteristicas) {
		panelCaracteristicas.setData(caracteristicas);
	}

	public void clearCaracteristicaData() {
		panelCaracteristicas.clearData();
	}

	public void clear() {
		this.panelCaracteristicas.clearData();
		this.clearDataVehiculos();
	}

}
