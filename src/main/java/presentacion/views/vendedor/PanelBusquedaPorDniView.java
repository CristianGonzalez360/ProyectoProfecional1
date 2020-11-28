package presentacion.views.vendedor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

public class PanelBusquedaPorDniView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4625097762285053450L;
	
	private JButton btnBuscar;
	
	private JTextField textField;
	
	public PanelBusquedaPorDniView() {
		setBorder(new TitledBorder(null, "B\u00FAsqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));		
		JLabel lblNewLabel = new JLabel("DNI");
		add(lblNewLabel);
				
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		clearData();
		
		btnBuscar = new JButton("Buscar");
		add(btnBuscar);
	}

	public void setActionBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}
	
	public String getData() {
		return textField.getText();
	}
	
	public void clearData() {
		textField.setText("");
	}
}
