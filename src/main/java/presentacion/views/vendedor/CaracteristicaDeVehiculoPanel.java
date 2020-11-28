package presentacion.views.vendedor;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CaracteristicaDeVehiculoPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6366092727436773232L;
	
	private JTextField textField;
	
	private JTextField textField_1;
	
	private JTextField textField_2;
	
	private JTextField textField_3;
	
	private JTextField textField_4;
	
	private JTextField textField_5;
	
	private JTextField textField_6;
	
	private JTextField textField_7;
	
	private JTextField textField_8;
	
	private JTextField textField_9;
	
	public CaracteristicaDeVehiculoPanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(91dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(83dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(175dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Cilindrada");
		add(lblNewLabel, "2, 2, left, default");
		
		textField = new JTextField();
		add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Transmisión");
		add(lblNewLabel_2, "6, 2, left, default");
		
		textField_5 = new JTextField();
		add(textField_5, "8, 2, fill, default");
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Motor");
		add(lblNewLabel_1, "2, 4");
		
		textField_1 = new JTextField();
		add(textField_1, "4, 4, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Frenos traseros");
		add(lblNewLabel_6, "6, 4, left, default");
		
		textField_6 = new JTextField();
		add(textField_6, "8, 4, fill, default");
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Dirección");
		add(lblNewLabel_3, "2, 6, left, default");
		
		textField_4 = new JTextField();
		add(textField_4, "4, 6, fill, default");
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Torque máximo (Nm/r)");
		add(lblNewLabel_7, "6, 6, left, default");
		
		textField_7 = new JTextField();
		add(textField_7, "8, 6, fill, default");
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Potencia (CV)");
		add(lblNewLabel_4, "2, 8, left, default");
		
		textField_3 = new JTextField();
		add(textField_3, "4, 8, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Volumen baúl (cc)");
		add(lblNewLabel_8, "6, 8");
		
		textField_8 = new JTextField();
		add(textField_8, "8, 8, fill, default");
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Frenos delanteros");
		add(lblNewLabel_5, "2, 10, left, default");
		
		textField_2 = new JTextField();
		add(textField_2, "4, 10, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Nro. puertas");
		add(lblNewLabel_9, "6, 10, left, default");
		
		textField_9 = new JTextField();
		add(textField_9, "8, 10, fill, default");
		textField_9.setColumns(10);
	}

}
