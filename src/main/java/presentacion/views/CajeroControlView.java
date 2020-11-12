package presentacion.views;

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

	private PanelCajeroView cajeroPanel;

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

		this.cajeroPanel = PanelCajeroView.getInstance();
		tabbedPane.add("Gestión de presupuestos", this.cajeroPanel);
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
