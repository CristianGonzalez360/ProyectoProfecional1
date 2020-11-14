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

public class PanelCobroCajeroView extends JPanel {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3152338359660079392L;
	
	private static PanelCobroCajeroView instance;
	
	private final String[] columnasListadoDeFacturas = new String[] { "Nro. Factura", "ID orden de trabajo","Fecha alta", "Fecha Cierre de pago","Total"};
	
	private DefaultTableModel listadoDeFacturasModel;
	
	private JPanel panel;
	private JScrollPane scrollPaneFacturas;
	private JTable tableFacturas;
	private JPanel panel_1;
	private JButton btnRegistrarPago;
	private JPanel panel_3;
	private JButton btnCargarFacturas;

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
		panel.setLayout(new BorderLayout(0, 0));
		
		this.listadoDeFacturasModel = new DefaultTableModel(null, this.columnasListadoDeFacturas); 
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
		};;
		scrollPaneFacturas.setViewportView(tableFacturas);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(15);
		panel.add(panel_1, BorderLayout.SOUTH);
		
		btnRegistrarPago = new JButton("Registrar Pago");
		btnRegistrarPago.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(btnRegistrarPago);
		btnRegistrarPago.setEnabled(false);
		
		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(15);
		add(panel_3, BorderLayout.NORTH);
		
		btnCargarFacturas = new JButton("Cargar Factura");
		panel_3.add(btnCargarFacturas);
		
	}

	public void cargarTabla(List<FacturaDTO> presupuestos) {
		for (FacturaDTO presupuesto : presupuestos) {
			
				Object[] row = { presupuesto.getIdFactura().toString(), presupuesto.getIdOrdenDeTrabajo(), presupuesto.getFechaDeAlta(), presupuesto.getFechaDeCierrePorPago(),presupuesto.getTotal() };
				listadoDeFacturasModel.addRow(row);
			
		}
		if(presupuestos.size()>=1) {
			botonvisible();
		}
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
