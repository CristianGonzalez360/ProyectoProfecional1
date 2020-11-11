package presentacion.views.supervisor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

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

public class EntregaVehiculosPanelView extends JPanel {

	private static final long serialVersionUID = -5623725856065117794L;

	private static final String[] COLUMNAS_ENTREGAS = { "DNI CLIENTE", "MARCA DE AUTO", "MODELO DE AUTO", "COLOR",
			"PATENTE" };

	private JLabel lblDniCliente;
	private JTextField textDniCliente;
	private JButton btnBuscar;

	private DefaultTableModel tableModelEntregas;
	private JTable tableEntregas;

	private JButton btnRegistrarEntrega;

	public EntregaVehiculosPanelView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) panelSuperior.getLayout();
		flowLayout.setHgap(20);
		add(panelSuperior, BorderLayout.NORTH);

		lblDniCliente = new JLabel("Cliente DNI");
		panelSuperior.add(lblDniCliente);

		textDniCliente = new JTextField();
		textDniCliente.setHorizontalAlignment(SwingConstants.CENTER);
		panelSuperior.add(textDniCliente);
		textDniCliente.setColumns(10);

		btnBuscar = new JButton("Buscar");
		panelSuperior.add(btnBuscar);

		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);

		JScrollPane scrollPaneEntregas = new JScrollPane();
		add(scrollPaneEntregas, BorderLayout.CENTER);
		tableModelEntregas = new DefaultTableModel(null, COLUMNAS_ENTREGAS);
		tableEntregas = new JTable(tableModelEntregas);
		scrollPaneEntregas.setViewportView(tableEntregas);

		JPanel panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);

		btnRegistrarEntrega = new JButton("Registrar turno");
		panelInferior.add(btnRegistrarEntrega);
	}

	public void setActionBuscarEntregas(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}

	public void setRegistrarEntrega(ActionListener listener) {
		this.btnRegistrarEntrega.addActionListener(listener);
	}

}