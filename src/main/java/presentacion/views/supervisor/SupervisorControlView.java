package presentacion.views.supervisor;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
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

import dto.OrdenDeTrabajoDTO;

import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SupervisorControlView extends JInternalFrame {

	private static final long serialVersionUID = 4306672868994985561L;

	private static final String[] COLUMNAS_ENTREGAS = {};

	private static final String [] COLUMNAS_ORDENES = new String [] {""};

	private static SupervisorControlView instance;

	private TurnosPanelView turnosPanelView;
	
	private DefaultTableModel modelOrdenesDeTrabajo;

	private PanelClientesView panelClientesView;

	private ConsultaDePresupuestosSupervisorView panelConsultaOTPresupuestadasView;

	private PanelEntregaVehiculosView panelEntregaVehiculosView;

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

	public static SupervisorControlView getInstance() {
		if (instance == null)
			instance = new SupervisorControlView();
		return instance;
	}

	private SupervisorControlView() {

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

		turnosPanelView = new TurnosPanelView();
		turnosPanel.add(turnosPanelView);

		JPanel clientesPanel = new PanelClientesView();
		tabbedPane.addTab("Gestión de Clientes", null, clientesPanel, null);
		clientesPanel.setLayout(new BorderLayout(0, 0));

		panelClientesView = new PanelClientesView();
		clientesPanel.add(panelClientesView);

		panelConsultaOTPresupuestadasView = ConsultaDePresupuestosSupervisorView.getInstance();
		tabbedPane.addTab("Gestión de presupuestos", panelConsultaOTPresupuestadasView);

		panelEntregaVehiculosView = new PanelEntregaVehiculosView();
		tabbedPane.addTab("Gestion entrega de vehículo", panelEntregaVehiculosView);

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
			e.printStackTrace();
		}
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}

	public void setOrdenesDeTrabajo(List<OrdenDeTrabajoDTO> ordenes) {
		for (OrdenDeTrabajoDTO orden : ordenes) {
			Object[] row = { orden.getTipoOrdeTrabajo(), orden.getIdUsuarioAlta(), orden.getIdVehiculoOt(),
					orden.getFechaDeAlta(), orden.getTrabajoSolicitado(), orden.getTrabajoSujerido(),
					orden.getFechaEntregado() };
			modelOrdenesDeTrabajo.addRow(row);
		}
	}

	public void clearOrdenesDeTrabajo() {
		modelOrdenesDeTrabajo.setRowCount(0);
		modelOrdenesDeTrabajo.setColumnCount(0);
		modelOrdenesDeTrabajo.setColumnIdentifiers(COLUMNAS_ORDENES);
	}

	public TurnosPanelView getTurnosView() {
		return this.turnosPanelView;
	}
	
	public PanelClientesView getClientesView() {
		return this.panelClientesView;
	}

	public void setActionBuscarOT(ActionListener listener) {
		// TODO Auto-generated method stub

	}

	public String getDniClienteBusquedaOT() {
		// TODO Auto-generated method stub
		return null;
	}

	public void clearTurnos() {
		turnosPanelView.clear();
	}
}