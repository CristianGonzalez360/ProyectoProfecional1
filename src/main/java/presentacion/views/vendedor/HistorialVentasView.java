
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

import dto.VentaVehiculoDTO;
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
	

	private final String[] columnasListadoDeVentas = new String[] { "Id venta" ,"Id usario" ,"Id usuario pedido","Id usuario llegada" };
	
	private DefaultTableModel listadoDeVentasModel;
	
	private JPanel panel;
	private JScrollPane scrollPaneVentas;
	private JTable tableVentas;
	private JPanel panel_1;
	private JPanel panel_3;
	private JButton btnCargarVentas;
	private JTextField textVentas;

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
		
		textVentas = new JTextField();
		panel_3.add(textVentas);
		textVentas.setColumns(10);
		
		btnCargarVentas = new JButton("Cargar");
		panel_3.add(btnCargarVentas);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Listado Ventas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		
		this.listadoDeVentasModel = new DefaultTableModel(null, this.columnasListadoDeVentas);;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPaneVentas = new JScrollPane();
		panel.add(scrollPaneVentas);
		tableVentas = new JTable(listadoDeVentasModel){//tabla no editable
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -7694783590163789939L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPaneVentas.setViewportView(tableVentas);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(15);
		panel.add(panel_1);
	
	}


	

	
	public void cargarTabla(List<VentaVehiculoDTO> ventas) {
		for (VentaVehiculoDTO venta : ventas) {
			
			
			
				Object[] row = { venta.getIdVentaVehiculo()  , 
								 venta.getIdUsuVentaVN(),
								 venta.getIdUsuPedido(),
								 venta.getIdUsuLlegada()
 };
				listadoDeVentasModel.addRow(row);
			
			
		}
		
	}
	
	

	public String getVenta() {
		return (String) textVentas.getText();
	}
		
	

	public void setActionOnBuscar(ActionListener listener) {
		this.btnCargarVentas.addActionListener(listener);
		
	}
		
	public void clear() {
		listadoDeVentasModel.setRowCount(0);
		listadoDeVentasModel.setColumnCount(0);
		listadoDeVentasModel.setColumnIdentifiers(columnasListadoDeVentas);
	}
	

	
	
	
	
}