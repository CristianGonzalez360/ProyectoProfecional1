package presentacion.views;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class TecnicoControlView extends JInternalFrame {

	private static final long serialVersionUID = 4704503289092275653L;

	private static TecnicoControlView instance;
	
	public static TecnicoControlView getInstance() {
		if(instance == null) instance = new TecnicoControlView();
		return instance;
	}
	
	private TecnicoControlView() {
		setTitle("Tecnico control view");
		setResizable(true);
		try {
			setIcon(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 634, 519);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel otConPresupuestosPagos = new JPanel();
		tabbedPane.addTab("OT con presupuestos pagos", null, otConPresupuestosPagos, null);

		JPanel otSinPresupuestar = new JPanel();
		tabbedPane.addTab("OT sin presupuestar", null, otSinPresupuestar, null);

	}

	public void display() {
		setVisible(true);		
	}

}
