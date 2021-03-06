
package presentacion.views.vendedor;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dto.temporal.VentaDTO;

public class HistorialVentasView extends JPanel {
	private static final long serialVersionUID = -3152338359660079392L;

	private static HistorialVentasView instance;

	private final String[] columnasListadoDeVentas = new String[] { "ID. VENTA", "FECHA DE VENTA", "FECHA DE ENTREGA",
			"MARCA", "MODELO", "NOMBRE DE CLIENTE", "PRECIO DE VENTA", "COMISION", "SUCURSAL" };

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

		this.listadoDeVentasModel = new DefaultTableModel(null, this.columnasListadoDeVentas);
		;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scrollPaneVentas = new JScrollPane();
		panel.add(scrollPaneVentas);
		tableVentas = new JTable(listadoDeVentasModel) {// tabla no editable
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

	public void cargarTabla(List<VentaDTO> ventas) {
		for (VentaDTO venta : ventas) {
			Object[] row = { venta.getIdVenta(), venta.getFechaDeVenta(), venta.getFechaDeEntrega(),
					venta.getMarcaVehiculo(), venta.getModeloVehiculo(), venta.getNombreCliente(),
					venta.getPrecioVenta().toString(), venta.getComisionVenta(), venta.getSucursal() };
			listadoDeVentasModel.addRow(row);
		}
	}

	public Date getVentaDesde() {
		return textVentasDesde.getDate();
	}

	public Date getVentaHasta() {
		return textVentasHasta.getDate();
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