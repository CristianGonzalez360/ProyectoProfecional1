package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dto.temporal.VehiculoParaEntregar;

public class PanelVentaVehiculosDisponibles extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int PAPELES = 5;

	private JTable tablaVentas;
	private DefaultTableModel modelo;
	private static final String[] columnas = { "NRO", "FECHA DE VENTA", "ESTADO DEL PEDIDO", "ESTADO DEL VEHICULO",
			"SUCURSAL", "DOCUMENTACION" };

	public PanelVentaVehiculosDisponibles() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tablaVentas = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				if (column == PAPELES) {
					return Boolean.class;
				}
				return super.getColumnClass(column);
			}
		};
		scrollPane.setViewportView(tablaVentas);

		modelo = new DefaultTableModel();
		modelo.setColumnIdentifiers(columnas);
		tablaVentas.setModel(modelo);
	}

	public boolean papelesEnRegla(int idFila) {
		return (boolean) tablaVentas.getValueAt(idFila, PAPELES);
	}

	public void setData(List<VehiculoParaEntregar> ventas) {
		modelo.setRowCount(0);
		for (VehiculoParaEntregar venta : ventas) {
			Object[] row = { venta.getVenta().getIdVentaVehiculo(), venta.getVenta().getFechaVentaVN(),
					venta.getSucursal() == null ? (venta.isPedido() ? "PEDIDO" : "PENDIENTE") : "-",
					venta.getSucursal() == null ? (venta.isIngresado() ? "DISPONIBLE" : "NO INGRESADO") : "DISPONIBLE",
					venta.getSucursal() != null
							? venta.getSucursal().getIdSucursal() + " " + venta.getSucursal().getLocalidad()
							: "",
					false };
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
