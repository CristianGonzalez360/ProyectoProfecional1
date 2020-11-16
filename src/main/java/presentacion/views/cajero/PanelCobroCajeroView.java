package presentacion.views.cajero;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.FacturaDTO;

import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.BoxLayout;

public class PanelCobroCajeroView extends JPanel {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3152338359660079392L;
	
	private static PanelCobroCajeroView instance;
	
	private final String[] columnasListadoDeFacturas = new String[] { "Nro. Factura", "ID orden de trabajo","Fecha alta", "Fecha Cierre de pago","Total", "Estado"};
	
	private DefaultTableModel listadoDeFacturasModel;
	
	private JPanel panel;
	private JScrollPane scrollPaneFacturas;
	private JTable tableFacturas;
	private JPanel panel_1;
	private JButton btnRegistrarPago;
	private JPanel panel_3;
	private JButton btnCargarFacturas;
	private JTextField textFactura;
	private JPanel panel_2;
	private JTabbedPane tabbedPane;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;

	public static PanelCobroCajeroView getInstance() {
		if (instance == null) {
			instance = new PanelCobroCajeroView();
		}
		return instance;
	}

	public PanelCobroCajeroView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Label label = new Label("Id Factura");
		panel_3.add(label);
		
		textFactura = new JTextField();
		panel_3.add(textFactura);
		textFactura.setColumns(10);
		
		btnCargarFacturas = new JButton("Cargar Factura");
		panel_3.add(btnCargarFacturas);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de facturas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		
		this.listadoDeFacturasModel = new DefaultTableModel(null, this.columnasListadoDeFacturas);;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPaneFacturas = new JScrollPane();
		panel.add(scrollPaneFacturas);
		tableFacturas = new JTable(listadoDeFacturasModel){//tabla no editable
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -7694783590163789939L;
			

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPaneFacturas.setViewportView(tableFacturas);
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Tarjeta Credito", null, panel_4, null);
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("90px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("38px"),
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("38px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),}));
		
		JLabel lblNewLabel = new JLabel("Numero");
		panel_4.add(lblNewLabel, "2, 2, fill, center");
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad Cuotas");
		panel_4.add(lblNewLabel_1, "2, 4, fill, center");
		
		JLabel lblNewLabel_2 = new JLabel("Nombre y Apellido");
		panel_4.add(lblNewLabel_2, "2, 6, fill, center");
		
		JLabel lblNewLabel_3 = new JLabel("Fecha Vencimiento");
		panel_4.add(lblNewLabel_3, "2, 8, fill, center");
		
		JLabel lblNewLabel_4 = new JLabel("Codigo Seguridad");
		panel_4.add(lblNewLabel_4, "2, 10, fill, center");
		
		JLabel lblNewLabel_5 = new JLabel("DNI");
		panel_4.add(lblNewLabel_5, "2, 12, fill, center");
		
		textField = new JTextField();
		panel_4.add(textField, "4, 2, 3, 1, left, top");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		panel_4.add(textField_1, "4, 4, 3, 1, left, top");
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		panel_4.add(textField_2, "4, 6, 3, 1, left, top");
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		panel_4.add(textField_3, "4, 8, fill, top");
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		panel_4.add(textField_4, "4, 10, 3, 1, left, top");
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		panel_4.add(textField_5, "4, 12, 3, 1, left, top");
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		panel_4.add(textField_6, "6, 8, fill, top");
		textField_6.setColumns(10);
		
		panel_5 = new JPanel();
		tabbedPane.addTab("Tarjeta Debito", null, panel_5, null);
		panel_5.setLayout(null);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBounds(362, 5, 1, 1);
		panel_4_1.setLayout(null);
		panel_5.add(panel_4_1);
		
		JLabel lblNewLabel_6 = new JLabel("Numero");
		lblNewLabel_6.setBounds(10, 11, 90, 14);
		panel_4_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cantidad Cuotas");
		lblNewLabel_1_1.setBounds(10, 36, 90, 14);
		panel_4_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Nombre y Apellido");
		lblNewLabel_2_1.setBounds(10, 61, 90, 14);
		panel_4_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Fecha Vencimiento");
		lblNewLabel_3_1.setBounds(10, 86, 90, 14);
		panel_4_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Codigo Seguridad");
		lblNewLabel_4_1.setBounds(10, 111, 90, 14);
		panel_4_1.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("DNI");
		lblNewLabel_5_1.setBounds(10, 136, 90, 14);
		panel_4_1.add(lblNewLabel_5_1);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(110, 8, 86, 20);
		panel_4_1.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(110, 33, 86, 20);
		panel_4_1.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(110, 58, 86, 20);
		panel_4_1.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(110, 83, 38, 20);
		panel_4_1.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(110, 108, 86, 20);
		panel_4_1.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(110, 133, 86, 20);
		panel_4_1.add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(158, 83, 38, 20);
		panel_4_1.add(textField_13);
		
		JPanel panel_4_2 = new JPanel();
		panel_4_2.setLayout(null);
		panel_4_2.setBounds(0, 0, 725, 169);
		panel_5.add(panel_4_2);
		
		JLabel lblNewLabel_7 = new JLabel("Numero");
		lblNewLabel_7.setBounds(10, 11, 90, 14);
		panel_4_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_2_2 = new JLabel("Nombre y Apellido");
		lblNewLabel_2_2.setBounds(10, 34, 90, 14);
		panel_4_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("Fecha Vencimiento");
		lblNewLabel_3_2.setBounds(10, 59, 90, 14);
		panel_4_2.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_4_2 = new JLabel("Codigo Seguridad");
		lblNewLabel_4_2.setBounds(10, 84, 90, 14);
		panel_4_2.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_5_2 = new JLabel("DNI");
		lblNewLabel_5_2.setBounds(10, 109, 90, 14);
		panel_4_2.add(lblNewLabel_5_2);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(110, 8, 86, 20);
		panel_4_2.add(textField_14);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(110, 31, 86, 20);
		panel_4_2.add(textField_16);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(110, 56, 38, 20);
		panel_4_2.add(textField_17);
		
		textField_18 = new JTextField();
		textField_18.setColumns(10);
		textField_18.setBounds(110, 81, 86, 20);
		panel_4_2.add(textField_18);
		
		textField_19 = new JTextField();
		textField_19.setColumns(10);
		textField_19.setBounds(110, 106, 86, 20);
		panel_4_2.add(textField_19);
		
		textField_20 = new JTextField();
		textField_20.setColumns(10);
		textField_20.setBounds(158, 56, 38, 20);
		panel_4_2.add(textField_20);
		
		panel_6 = new JPanel();
		tabbedPane.addTab("Mercado Pago", null, panel_6, null);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Bitcoins", null, panel_7, null);
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Efectivo", null, panel_8, null);
		panel_2.add(tabbedPane);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(15);
		panel.add(panel_1);
		
		btnRegistrarPago = new JButton("Registrar Pago");
		btnRegistrarPago.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(btnRegistrarPago);
		btnRegistrarPago.setEnabled(false);
		
	}

	public void cargarTabla(List<FacturaDTO> presupuestos) {
		for (FacturaDTO presupuesto : presupuestos) {
			
			
			
				Object[] row = { presupuesto.getIdFactura().toString(), presupuesto.getIdOrdenDeTrabajo(), 
						presupuesto.getFechaDeAlta(), presupuesto.getFechaDeCierrePorPago(),presupuesto.getTotal(), presupuesto.getEstado() };
				listadoDeFacturasModel.addRow(row);
			
			
		}
		if(presupuestos.size()>=1) {
			botonvisible();
		}
	}

	public String getFactura() {
		return (String) textFactura.getText();
	}
	
	public boolean iPersupuestoAprobado(int row, int column, JTable table) {
		return table.getValueAt(row, column) != null;
	}

	public void setActionOnBuscar(ActionListener listener) {
		this.btnCargarFacturas.addActionListener(listener);
	}
	
	public void setActionOnRegistrar(ActionListener listener) {
		this.btnRegistrarPago.addActionListener(listener);
	}
	
	public void clear() {
		listadoDeFacturasModel.setRowCount(0);
		listadoDeFacturasModel.setColumnCount(0);
		listadoDeFacturasModel.setColumnIdentifiers(columnasListadoDeFacturas);
	}
	
	public int getIdPresupuestoSeleccionada(){
		int idSeleccionada=-1;
		int row = tableFacturas.getSelectedRow();
		if(row!=-1) {
			idSeleccionada= Integer.valueOf((String) tableFacturas.getValueAt(row, 0));
		}
		return idSeleccionada;
	}
	
	public void botonvisible() {
		this.btnRegistrarPago.setEnabled(true);
	}
}
