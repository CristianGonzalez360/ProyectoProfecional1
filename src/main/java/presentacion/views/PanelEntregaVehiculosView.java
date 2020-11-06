package presentacion.views;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PanelEntregaVehiculosView extends JPanel {

	private static final long serialVersionUID = -5623725856065117794L;
	private JTextField textField;

	public PanelEntregaVehiculosView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("DNI");
		panel.add(lblNewLabel);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Buscar");
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		panel_4.getLayout();
		panel_4.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));

	}
}