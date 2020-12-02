package presentacion.views.gerente;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JTabbedPane;

public class GerenteControlView extends JInternalFrame {

	private static final long serialVersionUID = 1308877516578945407L;

	private static GerenteControlView instance;

	private PedidosPanelView pedidosPanelView;
	
	private GerenteNuevosCar autosNuevosView;
	
	private HistorialVentasGerenteView historialVentasView;

	public static GerenteControlView getInstance() {
		if (instance == null)
			instance = new GerenteControlView();
		return instance;
	}

	private GerenteControlView() {
		setClosable(false);
		setTitle("Gerente Control View");
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 800, 436);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel pedidosPanel = new JPanel();
		tabbedPane.addTab("Gestión de Pedidos", null, pedidosPanel, null);
		pedidosPanel.setLayout(new BorderLayout(0, 0));

		pedidosPanelView = new PedidosPanelView();
		pedidosPanel.add(pedidosPanelView);
		

		autosNuevosView = GerenteNuevosCar.getInstance();
		tabbedPane.addTab("Autos nuevos", autosNuevosView);


		PanelRegistroPedido panelPedidos = PanelRegistroPedido.getInstance();
		tabbedPane.addTab("Registro de Pedidos",panelPedidos );
		
		
		historialVentasView =  HistorialVentasGerenteView.getInstance();
		tabbedPane.addTab("Historial Ventas", historialVentasView);
		

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

	public void clearData() {
		pedidosPanelView.clear();
	}

	public PedidosPanelView getPedidosPanelView() {
		return pedidosPanelView;
	}

	public void setPedidosPanelView(PedidosPanelView pedidosPanelView) {
		this.pedidosPanelView = pedidosPanelView;
	}
	
	public GerenteNuevosCar getAutosNuevosPanelView () {
		return autosNuevosView;
	}

}
