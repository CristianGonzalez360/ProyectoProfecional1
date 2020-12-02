
package presentacion.views.gerente;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;
import java.awt.Label;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

import dto.VentaVehiculoDTO;
import dto.taller.FacturaDTO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;

public class HistorialVentasGerenteView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3152338359660079392L;
	
	private static HistorialVentasGerenteView instance;
	

	private final String[] columnasListadoDeVentas = new String[] { "Id venta" ,"Fecha Venta" ,"Fecha entrega","Id Vehiculo" ,"Id Cliente"};
	
	private DefaultTableModel listadoDeVentasModel;
	
	private JPanel panel;
	private JScrollPane scrollPaneVentas;
	private JTable tableVentas;
	private JPanel panel_1;
	private JPanel panel_3;
	private JButton btnCargarVentas;
	
	private JLabel lblNewLabel;
	
	private JDateChooser textVentasDesde;
	private JDateChooser textVentasHasta;

	public static HistorialVentasGerenteView getInstance() {
		if (instance == null) {
			instance = new HistorialVentasGerenteView();
		}
		return instance;
	}

	public HistorialVentasGerenteView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Label label = new Label("Ventas desde:");
		panel_3.add(label);
		
		
		
		textVentasDesde = new JDateChooser();
		panel_3.add(textVentasDesde);
		
		
		lblNewLabel = new JLabel("Hasta:");
		panel_3.add(lblNewLabel);
		
		textVentasHasta = new JDateChooser();
		panel_3.add(textVentasHasta);
		
		
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
								 venta.getFechaVentaVN(),
								 venta.getFechaEntregaReal(),
								 venta.getIdVehiculo(),
								 venta.getIdCliente()
 };
				listadoDeVentasModel.addRow(row);
			
			
		}
		
	}
	
	

	public  Date getVentaDesde() {
		return  textVentasDesde.getDate();
	}
		
	public Date getVentaHasta() {
		return  textVentasHasta.getDate();
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