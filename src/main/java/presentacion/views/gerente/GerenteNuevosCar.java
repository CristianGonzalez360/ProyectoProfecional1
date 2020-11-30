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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class GerenteNuevosCar extends JPanel {


	private static final String[] nombreColumnasAuto = { "Codigo", "Marca", "Familia", "Linea", "Color",
			"Precio" };

	private JPanel panelSuperior = new JPanel();
	private JPanel panelInterior = new JPanel();
	private JComboBox<String> comboMarcas;
	private JLabel lblDescripcion;
	private JTextField textDescipcion;
	private JButton btnBuscar;
	private JScrollPane scrollPaneRepuestos;
	private DefaultTableModel modelAutosNuevos;
	private JTable tablaAutosNuevos;
	private static GerenteNuevosCar vista;
	private JPanel panel_1;
	private List<Integer> idRepuestos;
	private CaracteristicaNuevoCarView panelCaracteristicas;
	private JSplitPane splitPane;
	private JPanel panelBotonesVehiculos;
	private JButton btnCargarArchivo;
	private JLabel lblMarca;

	private GerenteNuevosCar() {
		setBounds(100, 100, 712, 600);
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
		splitPane.setResizeWeight(0.0);
		add(splitPane, BorderLayout.CENTER);
		splitPane.setLeftComponent(panelIzquierdo);
		
		JPanel panelDerecho = new JPanel(new BorderLayout());
		
		panelCaracteristicas = CaracteristicaNuevoCarView.getInstance();//CaracteristicaNuevoCarView.getInstance();
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
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10px"),
				ColumnSpec.decode("max(34dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("132px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("right:75px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("78px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("85px"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),}));
		
		lblMarca = new JLabel("Marca");
		panel_1.add(lblMarca, "2, 2, right, default");

		comboMarcas = new JComboBox<>();
		panel_1.add(comboMarcas, "4, 2, default, center");

		lblDescripcion = new JLabel();
		panel_1.add(lblDescripcion, "6, 2, right, center");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setText("Descripción");

		textDescipcion = new JTextField();
		panel_1.add(textDescipcion, "8, 2, default, center");
		textDescipcion.setColumns(10);

		btnBuscar = new JButton("Buscar");
		panel_1.add(btnBuscar, "10, 2, fill, top");

		panelInterior = new JPanel();
		panelInterior.setBackground(SystemColor.menu);
		panelSuperior.add(panelInterior, BorderLayout.CENTER);
		panelInterior.setLayout(new BorderLayout(0, 0));
		tablaAutosNuevos = new JTable(modelAutosNuevos);
		scrollPaneRepuestos = new JScrollPane(tablaAutosNuevos);
		panelInterior.add(scrollPaneRepuestos, BorderLayout.CENTER);
		
		panelBotonesVehiculos = new JPanel();
		panelSuperior.add(panelBotonesVehiculos, BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) panelBotonesVehiculos.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		btnCargarArchivo = new JButton("Cargar archivo");
		panelBotonesVehiculos.add(btnCargarArchivo);

	}
	
	public static GerenteNuevosCar getInstance() {
		if (vista == null)
			vista = new GerenteNuevosCar();
		return vista;
	}

	public void clearDataVehiculos() {
		modelAutosNuevos.setRowCount(0);
	}

	public void display() {
		setVisible(true);
	}

//	public void setDataVehiculos(List<RepuestoDTO> repuestos) {
//		modelAutosNuevos.setRowCount(0);
//		idRepuestos.clear();
//		for (RepuestoDTO r : repuestos) {
//			idRepuestos.add(r.getIdRepuesto());
//			Object[] row = { r.getCodigoRepuesto(), r.getDescripcionRepuesto(), r.getMarcaRepuesto(), r.getFabricante(),
//					r.getStockRepuesto(), r.getPrecioRepuesto() };
//			modelAutosNuevos.addRow(row);
//		}
//	}

	public String getIdRepuesto() {
		String ret = -1 + "";
		if (tablaAutosNuevos.getSelectedRow() >= 0) {
			ret = "" + idRepuestos.get(tablaAutosNuevos.getSelectedRow());
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

	public String getDescripcion() {
		return textDescipcion.getText();
	}

	public void setDataMarcas(List<String> marcas) {
		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
		for (String marca : marcas) {
			modelo.addElement(marca);
		}
		comboMarcas.setModel(modelo);
	}


	public void clearCaracteristicaData() {
		panelCaracteristicas.clearData();
	}
	
	public void clear() {
//	this.modelRepuestosInferior.setRowCount(0);
	this.panelCaracteristicas.clearData();
	}
	
}
	
	
