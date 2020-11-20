package presentacion.views.supervisor;

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

import dto.EntregaDeVehiculoDTO;

public class EntregaVehiculosPanelView extends JPanel {

	private static final long serialVersionUID = -5623725856065117794L;

	private static final String[] COLUMNAS_ENTREGAS = {"ID", "DNI CLIENTE", "NOMBRE Y APELLIDO" , "MARCA DE AUTO", "MODELO DE AUTO", "COLOR",
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

		btnRegistrarEntrega = new JButton("Registrar Entrega");
		panelInferior.add(btnRegistrarEntrega);
	}

	public void setActionBuscarEntregas(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}

	public void setRegistrarEntrega(ActionListener listener) {
		this.btnRegistrarEntrega.addActionListener(listener);
	}

	public String getDniBusqueda() {
		return this.textDniCliente.getText();
	}

	public void clear() {
		tableModelEntregas.setRowCount(0);
		tableModelEntregas.setColumnCount(0);
		tableModelEntregas.setColumnIdentifiers(COLUMNAS_ENTREGAS);
	}

	public void setData(List<EntregaDeVehiculoDTO> entregas) {
		for (EntregaDeVehiculoDTO entrega : entregas) {
			Object[] row = { entrega.getIdOrdenDeTrabajo(), entrega.getDniCliente(), entrega.getNombreCompleto(), entrega.getMarcaAuto(),
					entrega.getModeloAuto(), entrega.getColorAuto(), entrega.getPatenteAuto() };
			tableModelEntregas.addRow(row);
		}
	}

	public Integer getIdSelectedEntrega() {
		int row = tableEntregas.getSelectedRow(); //id de fila seleccionada
		int id; //para id de la OT
		if (tableEntregas.getSelectedRow() == 0) {
			id = Integer.parseInt(tableEntregas.getValueAt(row, 0).toString());
			return id;
		}
		return null;
	}
	
	public Integer getDNISelectedEntrega() {
		int row = tableEntregas.getSelectedRow(); //id de fila seleccionada
		int dni; //para dni
		if (tableEntregas.getSelectedRow() == 0) {
			dni = Integer.parseInt(tableEntregas.getValueAt(row, 1).toString());
			return dni;
		}
		return null;
	}
	
	public Integer getIdSelectedRow() {
		int row = tableEntregas.getSelectedRow();
		return row; //id de fila seleccionada
	}

}