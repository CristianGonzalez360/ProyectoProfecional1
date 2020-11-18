package presentacion.views.cajero;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JTabbedPane;

public class CajeroControlView extends JInternalFrame {
	  
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1308877516578945407L;

	private static CajeroControlView instance;

	private PanelCarritoRepuestoView cajeroPanel;
	private PanelCobroCajeroView cajeroPanel2;


	public static CajeroControlView getInstance() {
		if (instance == null)
			instance = new CajeroControlView();
		return instance;
	}

	private CajeroControlView() {
		setClosable(false);
		setTitle("Cajero control view");
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 800, 436);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		this.cajeroPanel = PanelCarritoRepuestoView.getInstance();
		tabbedPane.add("Venta repuesto", this.cajeroPanel);
		
		this.cajeroPanel2 = PanelCobroCajeroView.getInstance();
		tabbedPane.add("Pago Facturas", this.cajeroPanel2);
		
		
		
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
