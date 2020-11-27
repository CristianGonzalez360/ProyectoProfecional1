package presentacion.views.gerente;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JTabbedPane;

public class GerenteControlView extends JInternalFrame {
	  
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1308877516578945407L;

	private static GerenteControlView instance;

	private PanelCarritoRepuestoView cajeroPanel;
//	private PanelCobroCajeroView cajeroPanel2;


	public static GerenteControlView getInstance() {
		if (instance == null)
			instance = new GerenteControlView();
		return instance;
	}

	private GerenteControlView() {
		setClosable(false);
		setTitle("Cajero Control View");
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 800, 436);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		this.cajeroPanel = PanelCarritoRepuestoView.getInstance();
		tabbedPane.add("Venta de Repuesto", this.cajeroPanel);
		
//		this.cajeroPanel2 = PanelCobroCajeroView.getInstance();
//		tabbedPane.add("Pago de Facturas", this.cajeroPanel2);
		
		
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

	}
}
