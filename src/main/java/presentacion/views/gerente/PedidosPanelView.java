package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import dto.temporal.PedidoDTO;

public class PedidosPanelView extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final String[] COLUMNAS_PEDIDOS = new String[] { "NRO. PEDIDO", "NOMBRE DE CLIENTE", "DNI",
			"MARCA DE AUTO", "MODELO", "COLOR", "COMBUSTION", "USUARIO DE PEDIDO", "FECHA DE PEDIDO" };

	private JTextField textDNI;
	private JLabel lblDNI;
	private JButton btnBuscar;

	private DefaultTableModel tableModelPedidos;
	private JTable tablePedidos;

	private JButton btnRegistrarPedido;

	public PedidosPanelView() {
		setLayout(new BorderLayout(0, 0));
		JPanel panelNorte = new JPanel();
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) panelNorte.getLayout();
		flowLayout.setHgap(20);
		add(panelNorte, BorderLayout.NORTH);

		lblDNI = new JLabel("DNI");
		panelNorte.add(lblDNI);

		textDNI = new JTextField("");
		textDNI.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(textDNI);
		textDNI.setColumns(20);

		btnBuscar = new JButton("Buscar");
		panelNorte.add(btnBuscar);

		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);

		btnRegistrarPedido = new JButton("Registrar Ingreso");
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

	public String getDniBusqueda() {
		return this.textDNI.getText();
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
