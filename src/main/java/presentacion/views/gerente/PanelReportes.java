package presentacion.views.gerente;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelReportes extends JPanel {
	private static final long serialVersionUID = 1L;
	private static PanelReportes instance;

	public static PanelReportes getInstance() {
		if (instance == null)
			instance = new PanelReportes();
		return instance;
	}

	public PanelReportes() {
		setLayout(new BorderLayout(0, 0));
	}
}
