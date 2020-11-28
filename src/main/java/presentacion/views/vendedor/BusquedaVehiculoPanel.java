package presentacion.views.vendedor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

public class BusquedaVehiculoPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4304226028387787383L;
	
	private JTextField textField;
	
	private JTextField textField_1;

	private JComboBox<String> comboBox;

	private JComboBox<String> comboBox_1;
	
	public BusquedaVehiculoPanel() {
		setBorder(new TitledBorder(null, "Consulta de veh\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setHgap(10);
		
		JLabel lblNewLabel = new JLabel("Tipo");
		add(lblNewLabel);
		
		comboBox = new JComboBox<String>();
		add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Marca");
		add(lblNewLabel_1);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Familia");
		add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Sucursal");
		add(lblNewLabel_3);
		
		comboBox_1 = new JComboBox<String>();
		add(comboBox_1);
		
		JButton btnNewButton = new JButton("Buscar");
		add(btnNewButton);
	}

}
