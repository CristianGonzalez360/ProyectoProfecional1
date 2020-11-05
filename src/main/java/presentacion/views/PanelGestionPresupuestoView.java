package presentacion.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.ActionEvent;

public class PanelGestionPresupuestoView extends JPanel {

	private static final long serialVersionUID = -5623725856065117794L;
	private JTextField txtDNI;

	private static PanelGestionPresupuestoView instance;
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
	private JPanel panelEsteSur;

	private JLabel lblTipo;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textTrabajoSolicitado;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textField_3;
	private JLabel lblNewLabel_4;
	private JTextField textField_4;
	private JPanel panel_1;
	private JTabbedPane tabbedPane;
	private JPanel panel_3;
	private JPanel panel_5;
	private JScrollPane scrollPaneRepuestos;
	private JTable tableRepuestos;
	private JTable tableTrabajos;
	private JPanel panel_2;
	private JButton btnPlanificarRepuestos;
	private JPanel panel_6;
	private JButton btnPlanificarTrabajos;
	private JPanel panel_7;
	private JButton btnRegistrarPresupuesto;
	private JToolBar toolBar;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JLabel lblNewLabel_5;
	private JTextField textField_5;
	private JLabel lblNewLabel_6;
	private JTextField textField_6;
	private JLabel lblNewLabel_7;
	private JTextField textField_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JTextField textField_8;
	private JTextField textField_9;

	public static PanelGestionPresupuestoView getInstance() {
		if (instance == null) {
			instance = new PanelGestionPresupuestoView();
		}
		return instance;
	}
	
	private PanelGestionPresupuestoView() {
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
				FormSpecs.DEFAULT_COLSPEC,
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
				RowSpec.decode("default:grow"),}));
		
		lblTipo = new JLabel("Tipo de trabajo");
		panelEsteSur.add(lblTipo, "2, 2");
		
		textField_1 = new JTextField();
		panelEsteSur.add(textField_1, "4, 2, fill, default");
		textField_1.setColumns(10);
		
		lblNewLabel = new JLabel("Alta");
		panelEsteSur.add(lblNewLabel, "6, 2");
		
		textField_4 = new JTextField();
		panelEsteSur.add(textField_4, "8, 2, fill, default");
		textField_4.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Cierre");
		panelEsteSur.add(lblNewLabel_4, "2, 4");
		
		textField_2 = new JTextField();
		panelEsteSur.add(textField_2, "4, 4, fill, default");
		textField_2.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Trabajo sugerido");
		panelEsteSur.add(lblNewLabel_3, "2, 6");
		
		textField_3 = new JTextField();
		panelEsteSur.add(textField_3, "4, 6, 5, 1, fill, default");
		textField_3.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Trabajo soclicitad");
		panelEsteSur.add(lblNewLabel_2, "2, 8");
		
		textTrabajoSolicitado = new JTextField();
		panelEsteSur.add(textTrabajoSolicitado, "4, 8, 5, 1, fill, default");
		textTrabajoSolicitado.setColumns(10);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ficha t\u00E9cnica del veh\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEste.add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(33dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(65dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(48dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(89dlu;default):grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblNewLabel_1 = new JLabel("Marca");
		panel.add(lblNewLabel_1, "2, 2");
		
		textField = new JTextField();
		panel.add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Modelo");
		panel.add(lblNewLabel_5, "6, 2");
		
		textField_5 = new JTextField();
		panel.add(textField_5, "8, 2, fill, default");
		textField_5.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Color");
		panel.add(lblNewLabel_6, "2, 4");
		
		textField_6 = new JTextField();
		panel.add(textField_6, "4, 4, fill, default");
		textField_6.setColumns(10);
		
		lblNewLabel_7 = new JLabel("Combustion");
		panel.add(lblNewLabel_7, "6, 4");
		
		textField_7 = new JTextField();
		panel.add(textField_7, "8, 4, fill, default");
		textField_7.setColumns(10);
		
		lblNewLabel_8 = new JLabel("Nro. Chasis");
		panel.add(lblNewLabel_8, "2, 6");
		
		textField_9 = new JTextField();
		panel.add(textField_9, "4, 6, fill, default");
		textField_9.setColumns(10);
		
		lblNewLabel_9 = new JLabel("Motor");
		panel.add(lblNewLabel_9, "6, 6");
		
		textField_8 = new JTextField();
		panel.add(textField_8, "8, 6, fill, default");
		textField_8.setColumns(10);
	
		panelOeste = new JPanel();
		splitPane.setRightComponent(panelOeste);
		panelOeste.setLayout(new BoxLayout(panelOeste, BoxLayout.Y_AXIS));
		
		this.listadoDePresupuestosModel =  new DefaultTableModel(null, this.columnasListadoDePresupuestos);
		
		/**
		 * 
		 */
		Object [] data = new Object[] {"1","2020-10-04", "Tren delantero", "1500"};
		this.listadoDePresupuestosModel.addRow(data);
		
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
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_2.getLayout();
		flowLayout_3.setAlignment(FlowLayout.TRAILING);
		panel_3.add(panel_2, BorderLayout.SOUTH);
		
		btnPlanificarRepuestos = new JButton("Planificar repuesto");
		panel_2.add(btnPlanificarRepuestos);
		
		panel_5 = new JPanel();
		tabbedPane.addTab("Trabajos planificados", null, panel_5, null);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTrabajos = new JScrollPane();
		panel_5.add(scrollPaneTrabajos, BorderLayout.CENTER);
		
		this.listadoDeTrabajosModel =  new DefaultTableModel(null, this.columnasListadoDeTrabajos);
		tableTrabajos = new JTable(listadoDeTrabajosModel);
		scrollPaneTrabajos.setViewportView(tableTrabajos);
		
		panel_6 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_6.getLayout();
		flowLayout_2.setAlignment(FlowLayout.TRAILING);
		panel_5.add(panel_6, BorderLayout.SOUTH);
		
		btnPlanificarTrabajos = new JButton("Planificar trabajo");
		panel_6.add(btnPlanificarTrabajos);
		
		panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelOeste.add(panel_7);
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel_7.add(toolBar);
		
		btnRegistrarPresupuesto = new JButton("Registrar presupuesto");
		toolBar.add(btnRegistrarPresupuesto);
	}
	
	void addCheckBox(int column, JTable table) {
	}
	
	public boolean iPersupuestoAprobado(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}
	
	public void setActionOnPlanificarTrabajos(ActionListener listener) {
		this.btnPlanificarTrabajos.addActionListener(listener);
	}

	public void setActionOnPlanificarRepuestos(ActionListener listener) {
		btnPlanificarRepuestos.addActionListener(listener);
	}

	public void setActionOnRegistrarPresupuesto(ActionListener listener) {
		this.btnRegistrarPresupuesto.addActionListener(listener);
	}
	
	
}
