package presentacion.views.gerente;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;
import javax.swing.table.DefaultTableModel;

import dto.VentaVehiculoDTO;
import dto.temporal.VehiculoParaEntregar;

public class PanelVentas extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTable tablaVentas;
	private DefaultTableModel modelo;
	private static final String[] columnas = { "Nro.", "Vehículo", "Fecha", "Precio", "Estado del pedido",
			"Fecha del pedido" };

	public PanelVentas() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tablaVentas = new JTable() {
			private static final long serialVersionUID = 6338578460850975220L;

			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		tablaVentas.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tablaVentas);

		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(columnas);
		tablaVentas.setModel(modelo);
	}

	public void setData(List<VehiculoParaEntregar> ventas) {
		modelo.setRowCount(0);
		for (VehiculoParaEntregar venta : ventas) {
			Object[] row = { venta.getVenta().getIdVentaVehiculo(),
					venta.getVehiculo().getMarca() + " " + venta.getVehiculo().getFamilia() + " "
							+ venta.getVehiculo().getLinea(),
					venta.getVenta().getFechaVentaVN(), venta.getVenta().getPrecioVenta(),
					venta.isPedido() ? "PEDIDO" : "PENDIENTE",
					venta.isPedido() ? venta.getPedido().getFechaPedido() : "-" };
			modelo.addRow(row);
		}
	}

	public void setActionOnSeleccionarVenta(ListSelectionListener listener) {
		tablaVentas.getSelectionModel().addListSelectionListener(listener);
	}

	public int getFIlaSaleccionada() {
		return tablaVentas.getSelectedRow();
	}

	public void clearData() {
		modelo.setRowCount(0);
	}

}
