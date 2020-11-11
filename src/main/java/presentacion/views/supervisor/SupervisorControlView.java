package presentacion.views.supervisor;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import dto.OrdenDeTrabajoDTO;

public class SupervisorControlView extends JInternalFrame {

	private static final long serialVersionUID = 4306672868994985561L;

	private static final String [] COLUMNAS_ORDENES = new String [] {""};

	private static SupervisorControlView instance;

	private TurnosPanelView turnosPanelView;
	
	private DefaultTableModel modelOrdenesDeTrabajo;

	private PanelClientesView panelClientesView;

	private ConsultaDePresupuestosSupervisorView panelConsultaOTPresupuestadasView;

	private EntregaVehiculosPanelView panelEntregaVehiculosView;

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

		/*
		 * Entregas
		 */
		JPanel entregasPanel = new JPanel();
		tabbedPane.addTab("Gestión entrega de vehículo", null, entregasPanel, null);
		entregasPanel.setLayout(new BorderLayout(0, 0));
		
		panelEntregaVehiculosView = new EntregaVehiculosPanelView();
		entregasPanel.add(panelEntregaVehiculosView);
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