package presentacion.views.vendedor;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.VehiculoDTO;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CaracteristicaDeVehiculoPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6366092727436773232L;
	
	private JTextField textCilindrada;
	
	private JTextField textMotor;
	
	private JTextField textFrenosDelanteros;
	
	private JTextField textPotencia;
	
	private JTextField textDireccion;
	
	private JTextField textTransmision;
	
	private JTextField textFrenosTraseros;
	
	private JTextField textTorqueMaximo;
	
	private JTextField textBaul;
	
	private JTextField textNroPuertas;
	
	public CaracteristicaDeVehiculoPanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(91dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(83dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(175dlu;default):grow"),},
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
		
		textCilindrada = new JTextField();
		add(textCilindrada, "4, 2, fill, default");
		textCilindrada.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Transmisión");
		add(lblNewLabel_2, "6, 2, left, default");
		
		textTransmision = new JTextField();
		add(textTransmision, "8, 2, fill, default");
		textTransmision.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Motor");
		add(lblNewLabel_1, "2, 4");
		
		textMotor = new JTextField();
		add(textMotor, "4, 4, fill, default");
		textMotor.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Frenos traseros");
		add(lblNewLabel_6, "6, 4, left, default");
		
		textFrenosTraseros = new JTextField();
		add(textFrenosTraseros, "8, 4, fill, default");
		textFrenosTraseros.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Dirección");
		add(lblNewLabel_3, "2, 6, left, default");
		
		textDireccion = new JTextField();
		add(textDireccion, "4, 6, fill, default");
		textDireccion.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Torque máximo (Nm/r)");
		add(lblNewLabel_7, "6, 6, left, default");
		
		textTorqueMaximo = new JTextField();
		add(textTorqueMaximo, "8, 6, fill, default");
		textTorqueMaximo.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Potencia (CV)");
		add(lblNewLabel_4, "2, 8, left, default");
		
		textPotencia = new JTextField();
		add(textPotencia, "4, 8, fill, default");
		textPotencia.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Volumen baúl (cc)");
		add(lblNewLabel_8, "6, 8");
		
		textBaul = new JTextField();
		add(textBaul, "8, 8, fill, default");
		textBaul.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Frenos delanteros");
		add(lblNewLabel_5, "2, 10, left, default");
		
		textFrenosDelanteros = new JTextField();
		add(textFrenosDelanteros, "4, 10, fill, default");
		textFrenosDelanteros.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Nro. puertas");
		add(lblNewLabel_9, "6, 10, left, default");
		
		textNroPuertas = new JTextField();
		add(textNroPuertas, "8, 10, fill, default");
		textNroPuertas.setColumns(10);
	}

	public void setData(VehiculoDTO dto) {
//		textCilindrada.setText(dto.getCaracteristicas().getCilindrada());
//		textMotor.setText(dto.getCaracteristicas().getMotor());
//		textFrenosDelanteros.setText(dto.getCaracteristicas().getFrenosDelanteros());
//		textPotencia.setText(dto.getCaracteristicas().getPotencia());
//		textDireccion.setText(dto.getCaracteristicas().getDireccion());
//		textTransmision.setText(dto.getCaracteristicas().getTransmision());
//		textFrenosTraseros.setText(dto.getCaracteristicas().getFrenosTraseros());
//		textTorqueMaximo.setText(dto.getCaracteristicas().getTorqueMaximo());
//		textBaul.setText(dto.getCaracteristicas().getVolumenBaul());
//		textNroPuertas.setText(dto.getCaracteristicas().getCantidadPuertas());
	}

	public void clearData() {
		textCilindrada.setText("");
		textMotor.setText("");
		textFrenosDelanteros.setText("");
		textPotencia.setText("");
		textDireccion.setText("");
		textTransmision.setText("");
		textFrenosTraseros.setText("");
		textTorqueMaximo.setText("");
		textBaul.setText("");
		textNroPuertas.setText("");
	}
}
