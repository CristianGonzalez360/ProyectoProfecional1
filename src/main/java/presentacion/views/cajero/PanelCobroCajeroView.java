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
	
	private final String[] columnasListadoDeFacturas = new String[] { "Nro. Factura", "Fecha de pago","Total", "Estado"};
	
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
	private JPanel panel_6;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JPanel panel_9;

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
				ColumnSpec.decode("38px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
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
		
		textField = new JTextField();
		panel_4.add(textField, "4, 2, left, top");
		textField.setColumns(10);
		
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
		
		panel_9 = new JPanel();
		panel_9.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Tarjeta Debito", null, panel_9, null);
		
		panel_6 = new JPanel();
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Mercado Pago", null, panel_6, null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Bitcoins", null, panel_7, null);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
			
			
			
				Object[] row = { presupuesto.getIdFactura().toString(),  presupuesto.getFechaDeCierrePorPago(),presupuesto.getTotal(), presupuesto.getEstado() };
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
	
	
	public String getEstadoSeleccionada(){
		String idSeleccionada = null;
		int row = tableFacturas.getSelectedRow();
		if(row!=-1) {
			idSeleccionada= (String) tableFacturas.getValueAt(row, 3);
		}
		return idSeleccionada;
	}
	
	public void botonvisible() {
		this.btnRegistrarPago.setEnabled(true);
	}
}
