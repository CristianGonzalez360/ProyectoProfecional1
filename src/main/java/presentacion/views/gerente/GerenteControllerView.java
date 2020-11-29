package presentacion.views.gerente;

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

import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.TurnoDTO;
import presentacion.views.supervisor.ConsultaDePresupuestosSupervisorView;
import presentacion.views.supervisor.EntregaVehiculosPanelView;
import presentacion.views.supervisor.PanelClientesView;

import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GerenteControllerView extends JInternalFrame {


	private static final String[] COLUMNAS_TURNOS = new String[] { "NRO. TURNO", "NOMBRE DE CLIENTE", "DNI",
			"FECHA DE ALTA", "FECHA PROGRAMADA", "TELEFONO", "EMAIL" };

	private static final String[] COLUMNAS_ORDENES = { "TIPO DE TRABAJO", "ID. USUARIO DE ALTA", "ID. VEHICULO OT",
			"FECHA ALTA", "TRABAJO SOLICITADO", "TRABAJO SUGERIDO", "FEHCA ENTRGA VEHICULO" };

	private static final String[] COLUMNAS_ENTREGAS = {};

	private static GerenteControllerView instance;

	private JTextField textFieldTurno;

	private JTable table;

	private DefaultTableModel tableModelTurnos;

	private JButton btnBuscar;

	private JButton btnRegistrarTurno;

	private JButton btnCancelarTurno;

	private DefaultTableModel modelOrdenesDeTrabajo;

	private PanelClientesView panelClientesView;

	private ConsultaDePresupuestosSupervisorView panelConsultaOTPresupuestadasView;

	private EntregaVehiculosPanelView panelEntregaVehiculosView;

	/*
	 * --- Vehicle Delivery
	 */
	private JPanel panelSuperiorEntrega;
	private FlowLayout flowLayoutSuperiorEntrega;
	private JTextField textDniDeEntrega;
	private JLabel lblDniDeEntrega;
	private JButton btnBuscarEntregas;

	private JScrollPane scrollPanelTablaEntregas;
	private DefaultTableModel modelEntregas;
	private JTable tablaEntregas;

	private JPanel panelInferiorEntrega;
	private JButton btnRegistrarEntrega;

	public static GerenteControllerView getInstance() {
		if (instance == null)
			instance = new GerenteControllerView();
		return instance;
	}

	private GerenteControllerView() {

		setMaximizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Supervisor Control View");
		setBounds(100, 100, 1280, 739);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel turnosPanel = new JPanel();
		tabbedPane.addTab("Gestión de Turnos", null, turnosPanel, null);
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
		table = new JTable(tableModelTurnos);
		scrollPane.setViewportView(table);

		JPanel clientesPanel = new PanelClientesView();
		tabbedPane.addTab("Gestión de Clientes", null, clientesPanel, null);
		clientesPanel.setLayout(new BorderLayout(0, 0));

		panelClientesView = new PanelClientesView();
		clientesPanel.add(panelClientesView);

		panelConsultaOTPresupuestadasView = ConsultaDePresupuestosSupervisorView.getInstance();
		tabbedPane.addTab("Venta Repuesto", panelConsultaOTPresupuestadasView);

		panelEntregaVehiculosView = new EntregaVehiculosPanelView();
		tabbedPane.addTab("Gestion entrega de vehículo", panelEntregaVehiculosView);

		tableModelTurnos = new DefaultTableModel(null, COLUMNAS_TURNOS);

		modelOrdenesDeTrabajo = (new DefaultTableModel(null, COLUMNAS_ORDENES));

		// vehicle delivery
		panelSuperiorEntrega = new JPanel();
		panelSuperiorEntrega.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		flowLayoutSuperiorEntrega = (FlowLayout) panelSuperiorEntrega.getLayout();
		flowLayoutSuperiorEntrega.setHgap(20);

		panelEntregaVehiculosView.add(panelSuperiorEntrega, BorderLayout.NORTH);

		lblDniDeEntrega = new JLabel("Cliente DNI");
		panelSuperiorEntrega.add(lblDniDeEntrega);

		textDniDeEntrega = new JTextField("");
		textDniDeEntrega.setHorizontalAlignment(SwingConstants.CENTER);
		panelSuperiorEntrega.add(textDniDeEntrega);
		textDniDeEntrega.setColumns(20);

		btnBuscarEntregas = new JButton("Buscar");
		panelSuperiorEntrega.add(btnBuscarEntregas);

		scrollPanelTablaEntregas = new JScrollPane();

		panelEntregaVehiculosView.add(scrollPanelTablaEntregas, BorderLayout.CENTER);

		modelEntregas = new DefaultTableModel(null, COLUMNAS_ENTREGAS);
		tablaEntregas = new JTable(modelEntregas);
		scrollPanelTablaEntregas.setViewportView(tablaEntregas);

		panelInferiorEntrega = new JPanel();

		panelEntregaVehiculosView.add(panelInferiorEntrega, BorderLayout.SOUTH);

		btnRegistrarEntrega = new JButton("Registrar Entrega");
		panelInferiorEntrega.add(btnRegistrarEntrega);

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
					turno.getFechaAlta().toString(), turno.getFechaProgramada().toString(), turno.getTelefonoCliente(),
					turno.getEmailCliente() };
			tableModelTurnos.addRow(row);
		}
	}

	public void setOrdenesDeTrabajo(List<OrdenDeTrabajoDTO> ordenes) {
		for (OrdenDeTrabajoDTO orden : ordenes) {
			Object[] row = { orden.getTipoOrdeTrabajo(), orden.getIdUsuarioAlta(), orden.getIdVehiculoOt(),
					orden.getFechaDeAlta(), orden.getTrabajoSolicitado(), orden.getTrabajoSujerido(),
					orden.getFechaEntregado() };
			modelOrdenesDeTrabajo.addRow(row);
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

	public Integer getIdSelectedTurno() {
		int row = table.getSelectedRow();
		int id;
		if (table.getSelectedRowCount() == 1) {
			id = Integer.parseInt(tableModelTurnos.getValueAt(row, 0).toString());
			return id;
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

	public void clearOrdenesDeTrabajo() {
		modelOrdenesDeTrabajo.setRowCount(0);
		modelOrdenesDeTrabajo.setColumnCount(0);
		modelOrdenesDeTrabajo.setColumnIdentifiers(COLUMNAS_ORDENES);
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

	public void setActionBuscarOT(ActionListener listener) {
		// TODO Auto-generated method stub

	}

	public String getDniClienteBusquedaOT() {
		// TODO Auto-generated method stub
		return null;
	}
}