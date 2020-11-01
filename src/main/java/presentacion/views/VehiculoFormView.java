package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VehiculoFormView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2246189166156653659L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textNroChasis;
	private JTextField textPatente;
	private JTextField textMarca;
	private JTextField textColor;
	private JTextField textAseguradora;
	private JTextField Garantia;
	private JTextField textNroMotor;
	private JTextField textKilometraje;
	private JTextField textModelo;
	private JTextField textCombustion;
	private JTextField textNroPoliza;

	public VehiculoFormView() {
		setBounds(100, 100, 750, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblChasis = new JLabel("Numero de chasis");
			lblChasis.setBounds(41, 37, 189, 14);
			contentPanel.add(lblChasis);
		}
		
		textNroChasis = new JTextField();
		textNroChasis.setBounds(153, 34, 142, 20);
		contentPanel.add(textNroChasis);
		textNroChasis.setColumns(10);
		
		JLabel lblPatente = new JLabel("Patente");
		lblPatente.setBounds(41, 85, 189, 14);
		contentPanel.add(lblPatente);
		
		textPatente = new JTextField();
		textPatente.setBounds(153, 82, 142, 20);
		contentPanel.add(textPatente);
		textPatente.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(41, 133, 184, 14);
		contentPanel.add(lblMarca);
		
		textMarca = new JTextField();
		textMarca.setBounds(153, 130, 142, 20);
		contentPanel.add(textMarca);
		textMarca.setColumns(10);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(41, 181, 189, 14);
		contentPanel.add(lblColor);
		
		textColor = new JTextField();
		textColor.setBounds(153, 178, 142, 20);
		contentPanel.add(textColor);
		textColor.setColumns(10);
		
		textAseguradora = new JTextField();
		textAseguradora.setBounds(153, 226, 142, 20);
		contentPanel.add(textAseguradora);
		textAseguradora.setColumns(10);
		
		JLabel lblAseguradora = new JLabel("Aseguradora");
		lblAseguradora.setBounds(41, 229, 189, 14);
		contentPanel.add(lblAseguradora);
		
		JLabel lblGarantia = new JLabel("Garantía");
		lblGarantia.setBounds(41, 277, 184, 14);
		contentPanel.add(lblGarantia);
		
		Garantia = new JTextField();
		Garantia.setBounds(153, 274, 142, 20);
		contentPanel.add(Garantia);
		Garantia.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Numero de motor");
		lblNewLabel_1.setBounds(414, 37, 121, 14);
		contentPanel.add(lblNewLabel_1);
		
		textNroMotor = new JTextField();
		textNroMotor.setBounds(546, 34, 142, 20);
		contentPanel.add(textNroMotor);
		textNroMotor.setColumns(10);
		
		JLabel lblKilometraje = new JLabel("Kilometraje");
		lblKilometraje.setBounds(414, 85, 121, 14);
		contentPanel.add(lblKilometraje);
		
		textKilometraje = new JTextField();
		textKilometraje.setBounds(546, 82, 142, 20);
		contentPanel.add(textKilometraje);
		textKilometraje.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(414, 133, 121, 14);
		contentPanel.add(lblModelo);
		
		textModelo = new JTextField();
		textModelo.setBounds(546, 130, 142, 20);
		contentPanel.add(textModelo);
		textModelo.setColumns(10);
		
		JLabel lblCombustin = new JLabel("Combustión");
		lblCombustin.setBounds(414, 181, 121, 14);
		contentPanel.add(lblCombustin);
		
		textCombustion = new JTextField();
		textCombustion.setBounds(546, 178, 142, 20);
		contentPanel.add(textCombustion);
		textCombustion.setColumns(10);
		
		JLabel lblNmeroDePolisa = new JLabel("Número de poliza");
		lblNmeroDePolisa.setBounds(414, 229, 121, 14);
		contentPanel.add(lblNmeroDePolisa);
		
		textNroPoliza = new JTextField();
		textNroPoliza.setBounds(546, 226, 142, 20);
		contentPanel.add(textNroPoliza);
		textNroPoliza.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(206, 302, 89, 23);
		contentPanel.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(396, 302, 89, 23);
		contentPanel.add(btnCancelar);
//		{//duda si va o no
//			JPanel buttonPane = new JPanel();
//			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton okButton = new JButton("Guardar");
//				okButton.setActionCommand("OK");
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				JButton cancelButton = new JButton("Cancelar");
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
//		}
	}
}
