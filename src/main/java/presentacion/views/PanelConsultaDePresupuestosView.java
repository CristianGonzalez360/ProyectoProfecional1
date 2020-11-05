package presentacion.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTabbedPane;

public class PanelConsultaDePresupuestosView extends JPanel {

	private static final long serialVersionUID = -5623725856065117794L;
	private JTextField txtDNI;

	private static PanelConsultaDePresupuestosView instance;
	private JButton btnBuscar;
	private JSplitPane splitPane;
	private JPanel panelEste;
	private JPanel panelOeste;
	private JPanel panelEsteNorte;

	private final String[] columnasTablaVehiculos = new String[] { "NRO. VEHICULO", "KM GARANTIA", "ASEGURADORA", "NRO POLIZA SEGURO", "PATENTE" };
	private DefaultTableModel tableModelVehiculos;
	private JTable tableVehiculos;
	
	private final String [] columnasListadoDePresupuestos  = new String [] {"NRO. Presupuesto", "FECHA ALTA", "COMENTARIO ALTA", "PRECIO", "APROBAR"};
	private DefaultTableModel listadoDePresupuestosModel;
	
	private final String [] columnasListadoDeRepuestos  = new String [] {"CODIGO", "MARCA", "DESCRIPCIÓN", "PRECIO", "CANTIDAD"};
	private DefaultTableModel listadoDeRepuestosModel;
	
	private final String [] columnasListadoDeTrabajos  = new String [] {"NRO", "FECHA ALTA", "DESCRIPCIÓN", "PRECIO", "ESTIMADO", "FECHA CIERRE"};
	private DefaultTableModel listadoDeTrabajosModel;
	
	private JTextField textField;
	private JLabel lblNewLabel;
	private JPanel panelEsteSur;

	private JLabel lblTipo;
	private JTextField textField_1;
	private JLabel lblNewLabel_1;
	private JTextField textField_2;
	private JTextField textTrabajoSolicitado;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textField_3;
	private JLabel lblNewLabel_4;
	private JTextField textField_4;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JTabbedPane tabbedPane;
	private JPanel panel_3;
	private JPanel panel_5;
	private JScrollPane scrollPanePresupuestos;
	private JTable tablePresupuestos;
	private JScrollPane scrollPaneRepuestos;
	private JTable tableRepuestos;
	private JTable tableTrabajos;

	public static PanelConsultaDePresupuestosView getInstance() {
		if (instance == null) {
			instance = new PanelConsultaDePresupuestosView();
		}
		return instance;
	}
	
	public PanelConsultaDePresupuestosView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setHgap(20);
		panel_4.getLayout();
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(panel_4, BorderLayout.NORTH);

		JLabel lblClienteDNI = new JLabel("Cliente DNI");
		panel_4.add(lblClienteDNI);

		txtDNI = new JTextField("");
		panel_4.add(txtDNI);
		txtDNI.setColumns(10);
		
		lblNewLabel = new JLabel("PATENTE");
		panel_4.add(lblNewLabel);
		
		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);

		btnBuscar = new JButton("Buscar");
		panel_4.add(btnBuscar);

		splitPane = new JSplitPane();
		add(splitPane, BorderLayout.CENTER);

		panelEste = new JPanel();
		splitPane.setLeftComponent(panelEste);
		panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));

		panelEsteNorte = new JPanel();
		panelEsteNorte.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ordenes de trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEste.add(panelEsteNorte);
		panelEsteNorte.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneVehiculos = new JScrollPane();
		panelEsteNorte.add(scrollPaneVehiculos);

		tableModelVehiculos = new DefaultTableModel(null, this.columnasTablaVehiculos);
		tableVehiculos = new JTable(tableModelVehiculos);
		scrollPaneVehiculos.setViewportView(tableVehiculos);
		
		panelEsteSur = new JPanel();
		panelEsteSur.setBorder(new TitledBorder(null, "Descripci\u00F3n de la orden de trabajo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEste.add(panelEsteSur);
		
		panelEsteSur.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblTipo = new JLabel("Tipo de trabajo");
		panelEsteSur.add(lblTipo, "2, 2");
		
		textField_1 = new JTextField();
		panelEsteSur.add(textField_1, "4, 2, fill, default");
		textField_1.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Fecha de alta");
		panelEsteSur.add(lblNewLabel_1, "2, 4");
		
		textField_2 = new JTextField();
		panelEsteSur.add(textField_2, "4, 4, fill, default");
		textField_2.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Trabajo soclicitad");
		panelEsteSur.add(lblNewLabel_2, "2, 6");
		
		textTrabajoSolicitado = new JTextField();
		panelEsteSur.add(textTrabajoSolicitado, "4, 6, fill, default");
		textTrabajoSolicitado.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Trabajo sugerido");
		panelEsteSur.add(lblNewLabel_3, "2, 8");
		
		textField_3 = new JTextField();
		panelEsteSur.add(textField_3, "4, 8, fill, default");
		textField_3.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Fecha de cierre");
		panelEsteSur.add(lblNewLabel_4, "2, 10");
		
		textField_4 = new JTextField();
		panelEsteSur.add(textField_4, "4, 10, fill, default");
		textField_4.setColumns(10);
	
		panelOeste = new JPanel();
		splitPane.setRightComponent(panelOeste);
		panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de presupuestos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOeste.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrollPanePresupuestos = new JScrollPane();
		panel.add(scrollPanePresupuestos, BorderLayout.CENTER);
		
		this.listadoDePresupuestosModel =  new DefaultTableModel(null, this.columnasListadoDePresupuestos);
		
		/**
		 * 
		 */
		Object [] data = new Object[] {"1","2020-10-04", "Tren delantero", "1500"};
		this.listadoDePresupuestosModel.addRow(data);
		tablePresupuestos = new JTable(listadoDePresupuestosModel);
		scrollPanePresupuestos.setViewportView(tablePresupuestos);
		this.addCheckBox(4, tablePresupuestos);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Detalles del presupuesto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOeste.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane, BorderLayout.CENTER);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Repuestos planificados", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		scrollPaneRepuestos = new JScrollPane();
		panel_3.add(scrollPaneRepuestos, BorderLayout.CENTER);
		
		this.listadoDeRepuestosModel =  new DefaultTableModel(null, this.columnasListadoDeRepuestos);
		tableRepuestos = new JTable(listadoDeRepuestosModel);
		scrollPaneRepuestos.setViewportView(tableRepuestos);
		
		panel_5 = new JPanel();
		tabbedPane.addTab("Trabajos planificados", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTrabajos = new JScrollPane();
		panel_5.add(scrollPaneTrabajos, BorderLayout.CENTER);
		
		this.listadoDeTrabajosModel =  new DefaultTableModel(null, this.columnasListadoDeTrabajos);
		tableTrabajos = new JTable(listadoDeTrabajosModel);
		scrollPaneTrabajos.setViewportView(tableTrabajos);
		
		panel_2 = new JPanel();
		panelOeste.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_2.add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Generar factura");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrar pago");
		toolBar.add(btnNewButton_1);
	}
	
	void addCheckBox(int column, JTable table) {
		TableColumn tc = table.getColumnModel().getColumn(column);
		tc.setCellEditor(table.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
	}
	
	public boolean iPersupuestoAprobado(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}
}