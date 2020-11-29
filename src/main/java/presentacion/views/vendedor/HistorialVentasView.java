
package presentacion.views.vendedor;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextField;
import java.awt.Label;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.taller.FacturaDTO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;

public class HistorialVentasView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3152338359660079392L;
	
	private static HistorialVentasView instance;
	
	//private final String[] columnasListadoDeFacturas = new String[] { "Nro. Factura" ,"DNI" ,"Fecha de pago","Total", "Estado" };
	private final String[] columnasListadoDeFacturas = new String[] { "Id venta" ,"DNI" ,"Fecha de venta","Vehiculo", "Estado" };
	
	private DefaultTableModel listadoDeFacturasModel;
	
	private JPanel panel;
	private JScrollPane scrollPaneFacturas;
	private JTable tableFacturas;
	private JPanel panel_1;
	private JPanel panel_3;
	private JButton btnCargarFacturas;
	private JTextField textFactura;

	public static HistorialVentasView getInstance() {
		if (instance == null) {
			instance = new HistorialVentasView();
		}
		return instance;
	}

	public HistorialVentasView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Label label = new Label("ID");
		panel_3.add(label);
		
		textFactura = new JTextField();
		panel_3.add(textFactura);
		textFactura.setColumns(10);
		
		btnCargarFacturas = new JButton("Cargar");
		panel_3.add(btnCargarFacturas);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado Ventas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(15);
		panel.add(panel_1);
	
	}

	public void cargarTabla(List<FacturaDTO> presupuestos) {
		for (FacturaDTO presupuesto : presupuestos) {
			
			
			
				Object[] row = { presupuesto.getIdFactura().toString(),  presupuesto.getDni(), presupuesto.getFechaDeCierrePorPago(),
						presupuesto.getTotal(), presupuesto.getEstado() };
				listadoDeFacturasModel.addRow(row);
			
			
		}
		
	}
	
	public void cargarTabla(FacturaDTO presupuesto) {
		if(presupuesto != null) {
			Object[] row = { presupuesto.getIdFactura().toString(),  presupuesto.getDni(), presupuesto.getFechaDeCierrePorPago(),
					presupuesto.getTotal(), presupuesto.getEstado() };
			listadoDeFacturasModel.addRow(row);
			
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
			idSeleccionada= (String) tableFacturas.getValueAt(row, 4);
		}
		return idSeleccionada;
	}
	
	
	public Double getTotalSeleccionada(){
		Double idSeleccionada = null;
		int row = tableFacturas.getSelectedRow();
		if(row!=-1) {
			idSeleccionada= (Double) tableFacturas.getValueAt(row, 3);
		}
		return idSeleccionada;
	}
	
	
}