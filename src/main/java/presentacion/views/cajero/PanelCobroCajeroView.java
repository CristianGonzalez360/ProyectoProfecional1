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
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado de facturas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.CENTER);
		
		this.listadoDeFacturasModel = new DefaultTableModel(null, this.columnasListadoDeFacturas); 
		panel.setLayout(null);
		scrollPaneFacturas = new JScrollPane();
		scrollPaneFacturas.setBounds(10, 16, 740, 149);
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
		};;
		scrollPaneFacturas.setViewportView(tableFacturas);
		
		panel_1 = new JPanel();
		panel_1.setBounds(6, 385, 744, 43);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(15);
		panel.add(panel_1);
		
		btnRegistrarPago = new JButton("Registrar Pago");
		btnRegistrarPago.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(btnRegistrarPago);
		btnRegistrarPago.setEnabled(false);
		
		panel_2 = new JPanel();
		panel_2.setBounds(10, 166, 740, 208);
		panel.add(panel_2);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
		);
		
		panel_4 = new JPanel();
		tabbedPane.addTab("Tarjeta Credito", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Numero");
		lblNewLabel.setBounds(10, 11, 90, 14);
		panel_4.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad Cuotas");
		lblNewLabel_1.setBounds(10, 36, 90, 14);
		panel_4.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre y Apellido");
		lblNewLabel_2.setBounds(10, 61, 90, 14);
		panel_4.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha Vencimiento");
		lblNewLabel_3.setBounds(10, 86, 90, 14);
		panel_4.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Codigo Seguridad");
		lblNewLabel_4.setBounds(10, 111, 90, 14);
		panel_4.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("DNI");
		lblNewLabel_5.setBounds(10, 136, 90, 14);
		panel_4.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(110, 8, 86, 20);
		panel_4.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(110, 33, 86, 20);
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(110, 58, 86, 20);
		panel_4.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(110, 83, 38, 20);
		panel_4.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(110, 108, 86, 20);
		panel_4.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(110, 133, 86, 20);
		panel_4.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(158, 83, 38, 20);
		panel_4.add(textField_6);
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
		panel_2.setLayout(gl_panel_2);
		
		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(15);
		add(panel_3, BorderLayout.NORTH);
		
		Label label = new Label("Id Factura");
		panel_3.add(label);
		
		textFactura = new JTextField();
		panel_3.add(textFactura);
		textFactura.setColumns(10);
		
		btnCargarFacturas = new JButton("Cargar Factura");
		panel_3.add(btnCargarFacturas);
		
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
