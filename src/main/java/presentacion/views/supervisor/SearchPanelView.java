package presentacion.views.supervisor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class SearchPanelView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3615072143371235780L;
	private JTextField txtDNI;
	private JButton btnBuscar;

	public SearchPanelView() {
		getLayout();
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JLabel lblClienteDNI = new JLabel("Cliente DNI");
		add(lblClienteDNI);

		txtDNI = new JTextField("");
		add(txtDNI);
		txtDNI.setColumns(10);

		btnBuscar = new JButton("Buscar");
		add(btnBuscar);
	}

	public JTextField getTxtDNI() {
		return txtDNI;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}
}
