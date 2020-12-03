package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dto.VentaVehiculoDTO;

public class PanelVentaVehiculosDisponibles extends JPanel{
	
	private JTable tablaVentas;
	private DefaultTableModel modelo;
	private static final String[] columnas= {"Nro.", "Fecha de venta", "Estado del pedido", "Estado ","Documentacion"};
	
	public PanelVentaVehiculosDisponibles() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tablaVentas = new JTable() {
			@Override
			public Class<?> getColumnClass(int column) {
			if (column == 4 ) {
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
		return (boolean) tablaVentas.getValueAt(idFila, 4);
	}
	
	public void setData(List<VentaVehiculoDTO> ventas) {
		modelo.setRowCount(0);
		for (VentaVehiculoDTO venta : ventas) {
			Object[] row = {venta.getIdVentaVehiculo(), venta.getFechaVentaVN(), venta.isPedido()? "PEDIDO" : "PENDIENTE", 
				venta.isIngresado() ? "INGRESADO" : "NO INGRESADO", false };
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
