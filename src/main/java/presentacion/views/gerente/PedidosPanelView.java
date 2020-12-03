package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dto.temporal.PedidoDTO;

public class PedidosPanelView extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final String[] COLUMNAS_PEDIDOS = new String[] { "NRO. PEDIDO", "NOMBRE DE CLIENTE", "DNI",
			"MARCA DE AUTO", "MODELO", "COLOR", "COMBUSTION", "USUARIO DE PEDIDO", "FECHA DE PEDIDO" };

	private JButton btnBuscar;

	private DefaultTableModel tableModelPedidos;
	private JTable tablePedidos;

	private JButton btnRegistrarPedido;

	public PedidosPanelView() {
		setLayout(new BorderLayout(0, 0));
		setForeground(SystemColor.menu);
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEFT, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		setBackground(SystemColor.menu);

		JPanel panelNorte = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelNorte.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		add(panelNorte, BorderLayout.NORTH);

		btnBuscar = new JButton("Refrescar");
		panelNorte.add(btnBuscar);

		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);

		btnRegistrarPedido = new JButton("Registrar Ingreso de Unidad");
		panelSur.add(btnRegistrarPedido);

		JScrollPane scrollPanelPedidos = new JScrollPane();
		add(scrollPanelPedidos, BorderLayout.CENTER);
		tableModelPedidos = new DefaultTableModel(null, COLUMNAS_PEDIDOS);
		tablePedidos = new JTable(tableModelPedidos);
		scrollPanelPedidos.setViewportView(tablePedidos);
	}

	public void clear() {
		tableModelPedidos.setRowCount(0);
		tableModelPedidos.setColumnCount(0);
		tableModelPedidos.setColumnIdentifiers(COLUMNAS_PEDIDOS);
	}

	public void setActionBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}

	public void setActionRegistrarIngreso(ActionListener listener) {
		this.btnRegistrarPedido.addActionListener(listener);
	}

	public void setData(List<PedidoDTO> pedidos) {
		for (PedidoDTO pedido : pedidos) {
			String nombreCompleto = String.format("%s %s", pedido.getNombreCliente(), pedido.getApellidoCliente());
			Object[] row = { pedido.getIdPedido().toString(), nombreCompleto, pedido.getDniCliente(),
					pedido.getMarcaAuto(), pedido.getModeloAuto(), pedido.getColorAuto(), pedido.getConbustionAuto(),
					pedido.getNombreUsuario(), pedido.getFechaPedido().toString() };
			tableModelPedidos.addRow(row);
		}
	}

	public Integer getIdSelectedRow() {
		int row = tablePedidos.getSelectedRow();
		return row;
	}

	public Integer getIdSelectedPedido() {
		int row = tablePedidos.getSelectedRow();
		int id;
		if (tablePedidos.getSelectedRow() != -1) {
			id = Integer.parseInt(tablePedidos.getValueAt(row, 0).toString());
			return id;
		}
		return null;
	}

}
