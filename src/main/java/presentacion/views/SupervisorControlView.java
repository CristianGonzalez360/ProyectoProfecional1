package presentacion.views;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import dto.TurnoDTO;

import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class SupervisorControlView extends JInternalFrame {

	private static final long serialVersionUID = 4306672868994985561L;

	private static final String[] COLUMNAS_TURNOS = new String[] { "NRO. TURNO", "NOMBRE CLIENTE", "DNI CLIENTE",
			"FECHA DE ALTA", "FECHA PROGRAMADA" };

	private static SupervisorControlView instance;

	private JTextField textFieldTurno;

	private JTable table;

	private DefaultTableModel tableModelTurnos;

	private JButton btnBuscar;

	private JButton btnRegistrarTurno;

	private JButton btnCancelarTurno;
	private JTextField textDni;
	private JTextField textPatente;
	private JTextField textTrabajoSugerido;
	private JTextField textTrabajoSolicitado;
	private JTextField textTrabajosPorPresupuestar;
	private JTextField textTrabajosEnCurso;
	private JTextField textTrabajosTerminados;
	private JTextField textTotalPresupuestado;
	private JTable tablaOrdenesDeTrabajo;
	private JTable tablaPresupuesto;
	private String[] columnasOrdenTrabajo = { "Nro OT", "Fecha alta", "Usuario de alta", "Dni cliente", "patente" };// aca
																													// se
																													// modifican
																													// las
																													// columnas
																													// de
																													// la
																													// tabla
																													// orden
																													// de
																													// trabajo
	private String[] columnasPresupuesto = { "Fecha alta" };// aca se modifican las columnas de la tabla presupuesto

	private PanelClientesView panelClientesView;

	public static SupervisorControlView getInstance() {
		if (instance == null)
			instance = new SupervisorControlView();
		return instance;
	}

	private SupervisorControlView() {

		setMaximizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Supervisor control view");
		setBounds(100, 100, 859, 647);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel clientesPanel = new PanelClientesView();
		tabbedPane.addTab("Clientes", null, clientesPanel, null);
		clientesPanel.setLayout(new BorderLayout(0, 0));

		panelClientesView = new PanelClientesView();
		clientesPanel.add(panelClientesView);

		JPanel turnosPanel = new JPanel();
		tabbedPane.addTab("Turnos", null, turnosPanel, null);
		turnosPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(20);
		turnosPanel.add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("DNI CLIENTE");
		panel_1.add(lblNewLabel);

		textFieldTurno = new JTextField("");
		textFieldTurno.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textFieldTurno);
		textFieldTurno.setColumns(20);

		btnBuscar = new JButton("buscar turnos");
		panel_1.add(btnBuscar);

		JPanel panel_2 = new JPanel();
		turnosPanel.add(panel_2, BorderLayout.SOUTH);

		btnRegistrarTurno = new JButton("Registrar turno");
		panel_2.add(btnRegistrarTurno);

		btnCancelarTurno = new JButton("Cancelar turno");
		panel_2.add(btnCancelarTurno);

		JScrollPane scrollPane = new JScrollPane();
		turnosPanel.add(scrollPane, BorderLayout.CENTER);

		tableModelTurnos = new DefaultTableModel(null, COLUMNAS_TURNOS);
		table = new JTable(tableModelTurnos);
		scrollPane.setViewportView(table);

		JPanel otPresupuestadasPanel = new JPanel();
		tabbedPane.addTab("OT presupuestadas", null, otPresupuestadasPanel, null);
		otPresupuestadasPanel.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 11, 818, 66);
		otPresupuestadasPanel.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(41, 26, 46, 14);
		panel_3.add(lblDni);

		JLabel lblPatenteVehculo = new JLabel("Patente vehículo");
		lblPatenteVehculo.setBounds(249, 26, 103, 14);
		panel_3.add(lblPatenteVehculo);

		JButton btnBuscar_1 = new JButton("Buscar");
		btnBuscar_1.setBounds(603, 22, 89, 23);
		panel_3.add(btnBuscar_1);

		textDni = new JTextField();
		textDni.setBounds(66, 23, 139, 20);
		panel_3.add(textDni);
		textDni.setColumns(10);

		textPatente = new JTextField();
		textPatente.setBounds(341, 23, 113, 20);
		panel_3.add(textPatente);
		textPatente.setColumns(10);

		JCheckBox chckbxTodos = new JCheckBox("Todos");
		chckbxTodos.setBounds(478, 22, 97, 23);
		panel_3.add(chckbxTodos);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(435, 107, 393, 100);
		otPresupuestadasPanel.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblTrabajoSugerido = new JLabel("Trabajo sugerido");
		lblTrabajoSugerido.setBounds(27, 25, 102, 14);
		panel_4.add(lblTrabajoSugerido);

		JLabel lblTrabajoSolicitado = new JLabel("Trabajo solicitado");
		lblTrabajoSolicitado.setBounds(27, 64, 102, 14);
		panel_4.add(lblTrabajoSolicitado);

		textTrabajoSugerido = new JTextField();
		textTrabajoSugerido.setBounds(139, 22, 207, 20);
		panel_4.add(textTrabajoSugerido);
		textTrabajoSugerido.setColumns(10);

		textTrabajoSolicitado = new JTextField();
		textTrabajoSolicitado.setBounds(139, 61, 207, 20);
		panel_4.add(textTrabajoSolicitado);
		textTrabajoSolicitado.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 88, 402, 468);
		otPresupuestadasPanel.add(panel_5);
		panel_5.setLayout(null);

		JButton buttonRegistrarEntrega = new JButton("Registrar entrega de vehículo");
		buttonRegistrarEntrega.setBounds(108, 434, 187, 23);
		panel_5.add(buttonRegistrarEntrega);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(21, 268, 371, 145);
		panel_5.add(panel_6);
		panel_6.setLayout(null);

		JLabel lblTrabajosPorPresupuestar = new JLabel("Trabajos por presupuestar");
		lblTrabajosPorPresupuestar.setBounds(10, 26, 151, 14);
		panel_6.add(lblTrabajosPorPresupuestar);

		JLabel lblTrabajosEnCurso = new JLabel("Trabajos en curso");
		lblTrabajosEnCurso.setBounds(10, 72, 151, 14);
		panel_6.add(lblTrabajosEnCurso);

		JLabel lblTrabajosTerminados = new JLabel("Trabajos terminados");
		lblTrabajosTerminados.setBounds(10, 107, 151, 14);
		panel_6.add(lblTrabajosTerminados);

		textTrabajosPorPresupuestar = new JTextField();
		textTrabajosPorPresupuestar.setBounds(171, 23, 86, 20);
		panel_6.add(textTrabajosPorPresupuestar);
		textTrabajosPorPresupuestar.setColumns(10);

		textTrabajosEnCurso = new JTextField();
		textTrabajosEnCurso.setBounds(171, 69, 86, 20);
		panel_6.add(textTrabajosEnCurso);
		textTrabajosEnCurso.setColumns(10);

		textTrabajosTerminados = new JTextField();
		textTrabajosTerminados.setBounds(171, 104, 86, 20);
		panel_6.add(textTrabajosTerminados);
		textTrabajosTerminados.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 16, 382, 241);
		panel_5.add(scrollPane_1);

		DefaultTableModel modelOrdenesDeTrabajo = (new DefaultTableModel(null, columnasOrdenTrabajo));
		tablaOrdenesDeTrabajo = new JTable(modelOrdenesDeTrabajo);
		scrollPane_1.setViewportView(tablaOrdenesDeTrabajo);

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(435, 245, 393, 310);
		otPresupuestadasPanel.add(panel_7);
		panel_7.setLayout(null);

		JLabel lblPresupuestos = new JLabel("Presupuestos");
		lblPresupuestos.setBounds(10, 11, 102, 14);
		panel_7.add(lblPresupuestos);

		JButton btnEnviarFactura = new JButton("Enviar factura al cliente");
		btnEnviarFactura.setBounds(10, 262, 147, 23);
		panel_7.add(btnEnviarFactura);

		JButton btnRegistrarPago = new JButton("Registrar pago");
		btnRegistrarPago.setBounds(167, 262, 106, 23);
		panel_7.add(btnRegistrarPago);

		JButton btnConsultarDetalle = new JButton("Consultar detalle");
		btnConsultarDetalle.setBounds(283, 262, 100, 23);
		panel_7.add(btnConsultarDetalle);

		JLabel lblTotalPresupuestado = new JLabel("Total presupuestado");
		lblTotalPresupuestado.setBounds(39, 227, 118, 14);
		panel_7.add(lblTotalPresupuestado);

		textTotalPresupuestado = new JTextField();
		textTotalPresupuestado.setBounds(187, 224, 118, 20);
		panel_7.add(textTotalPresupuestado);
		textTotalPresupuestado.setColumns(10);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 36, 373, 170);
		panel_7.add(scrollPane_2);

		DefaultTableModel modelPresupuestos = (new DefaultTableModel(null, columnasPresupuesto));
		tablaPresupuesto = new JTable(modelPresupuestos);
		scrollPane_2.setViewportView(tablaPresupuesto);

		JPanel panel = new JPanel();
		tabbedPane.addTab("OT para cerrar", null, panel, null);

	}

	public void display() {
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}

	public void setTurnos(List<TurnoDTO> turnos) {
		for (TurnoDTO turno : turnos) {
			Object[] row = { turno.getIdTurno().toString(), turno.getNombreCliente(), turno.getDniCliente(),
					turno.getFechaAlta().toString(), turno.getFechaProgramada().toString() };
			tableModelTurnos.addRow(row);
		}
	}

	public TurnoDTO getSelectedTurno() {
		int row = table.getSelectedRow();
		if (table.getSelectedRowCount() == 1) {
			TurnoDTO ret = new TurnoDTO();
			ret.setIdTurno(Integer.parseInt(tableModelTurnos.getValueAt(row, 0).toString()));
			ret.setNombreCliente(tableModelTurnos.getValueAt(row, 1).toString());
			ret.setDniCliente(Integer.parseInt(tableModelTurnos.getValueAt(row, 2).toString()));
			ret.setFechaAlta(parse(tableModelTurnos.getValueAt(row, 3).toString()));
			ret.setFechaProgramada(parse(tableModelTurnos.getValueAt(row, 4).toString()));
			return ret;
		}
		return null;
	}

	private Date parse(String str) {
		try {
			return new SimpleDateFormat("yyyy-mm-dd").parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void clearTurnos() {
		tableModelTurnos.setRowCount(0);
		tableModelTurnos.setColumnCount(0);
		tableModelTurnos.setColumnIdentifiers(COLUMNAS_TURNOS);
	}

	public void setActionBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}

	public void setActionRegistrarTurno(ActionListener listener) {
		this.btnRegistrarTurno.addActionListener(listener);
	}

	public void setActionCancelarTurno(ActionListener listener) {
		this.btnCancelarTurno.addActionListener(listener);
	}

	public String getDniClienteBusquedaTurno() {
		return textFieldTurno.getText();
	}

	public PanelClientesView getPanelClientesView() {
		return this.panelClientesView;
	}
}