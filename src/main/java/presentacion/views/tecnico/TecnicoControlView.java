
package presentacion.views.tecnico;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JTabbedPane;

public class TecnicoControlView extends JInternalFrame {

	private static final long serialVersionUID = 4704503289092275653L;

	private static TecnicoControlView instance;

	private PanelGestionPresupuestoView gestionPresupuestoPanel;
	private PanelGestionTrabajoView registrarTrabajoPanel;

	public static TecnicoControlView getInstance() {
		if (instance == null)
			instance = new TecnicoControlView();
		return instance;
	}

	private TecnicoControlView() {
		setClosable(false);
		setTitle("Tecnico Control View");
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 800, 436);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		this.gestionPresupuestoPanel = PanelGestionPresupuestoView.getInstance();
		tabbedPane.add("Gestión de Presupuestos", this.gestionPresupuestoPanel);

		this.registrarTrabajoPanel = PanelGestionTrabajoView.getInstance();
		tabbedPane.add("Gestión de Trabajos", this.registrarTrabajoPanel);
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
