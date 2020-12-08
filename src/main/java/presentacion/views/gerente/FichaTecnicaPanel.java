package presentacion.views.gerente;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.temporal.CompraVehiculoUsadoDTO;

public class FichaTecnicaPanel extends JPanel {
	
	private JTextField textNroChasis;
	private JLabel lblNewLabel_1 = new JLabel("Nro. de motor");
	private JTextField textNroDeMotor = new JTextField();
	private JLabel lblNewLabel_2 = new JLabel("Patente");
	private JTextField textPatente = new JTextField();
	private JLabel lblNewLabel_3 = new JLabel("Kilometraje");
	private JTextField textKilometraje = new JTextField();
	private JLabel lblNewLabel_4 = new JLabel("Marca");
	private JTextField textMarca = new JTextField();
	private JLabel lblNewLabel_5 = new JLabel("Modelo");
	private JTextField textModelo = new JTextField();
	private JLabel lblNewLabel_6 = new JLabel("Color");
	private JTextField textColor = new JTextField();
	private JLabel lblNewLabel_7 = new JLabel("Combustion");
	private JTextField textCombustion = new JTextField();
	private JLabel lblNewLabel_8 = new JLabel("Asegurador");
	private JTextField textAsegurador = new JTextField();
	private JLabel lblNewLabel_9 = new JLabel("Nro. poliza");
	private JTextField textNroPoliza = new JTextField();
	private JLabel lblNewLabel_10 = new JLabel("Garantia");
	private JTextField textGarantia = new JTextField();
	private JTextField txtFamilia;
	private JTextField txtLinea;
	
	public FichaTecnicaPanel() {
		setLayout(new BorderLayout());
		
		JPanel contentPanel = new JPanel(); 
		//contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
				lblNewLabel_4 = new JLabel("Marca");
				contentPanel.add(lblNewLabel_4, "2, 2");
				textMarca = new JTextField();
				contentPanel.add(textMarca, "4, 2, fill, default");
				lblNewLabel_5 = new JLabel("Modelo");
				contentPanel.add(lblNewLabel_5, "6, 2");
				textModelo = new JTextField();
				contentPanel.add(textModelo, "8, 2, fill, default");
		
		JLabel lblFamilia = new JLabel("Familia");
		contentPanel.add(lblFamilia, "2, 4, left, default");
		
		txtFamilia = new JTextField();
		contentPanel.add(txtFamilia, "4, 4, fill, default");
		txtFamilia.setColumns(10);
		
		JLabel lblLinea = new JLabel("Linea");
		contentPanel.add(lblLinea, "6, 4, left, default");
		
		txtLinea = new JTextField();
		contentPanel.add(txtLinea, "8, 4, fill, default");
		txtLinea.setColumns(10);
		lblNewLabel_6 = new JLabel("Color");
		contentPanel.add(lblNewLabel_6, "2, 6");
		textColor = new JTextField();
		contentPanel.add(textColor, "4, 6, fill, default");
		
		lblNewLabel_1 = new JLabel("Nro. de motor");
		contentPanel.add(lblNewLabel_1, "6, 6, left, default");
		textNroDeMotor = new JTextField();
		contentPanel.add(textNroDeMotor, "8, 6, fill, default");
		lblNewLabel_3 = new JLabel("Kilometraje");
		contentPanel.add(lblNewLabel_3, "2, 8");
		textKilometraje = new JTextField();
		contentPanel.add(textKilometraje, "4, 8, fill, default");
		
				JLabel lblNewLabel = new JLabel("Nro. de chasis");
				contentPanel.add(lblNewLabel, "6, 8");
		textNroChasis = new JTextField();
		
				
				contentPanel.add(textNroChasis, "8, 8");
		lblNewLabel_7 = new JLabel("Combustion");
		contentPanel.add(lblNewLabel_7, "2, 10");
		textCombustion = new JTextField();
		contentPanel.add(textCombustion, "4, 10, fill, default");
		lblNewLabel_8 = new JLabel("Asegurador");
		contentPanel.add(lblNewLabel_8, "6, 10");
		textAsegurador = new JTextField();
		contentPanel.add(textAsegurador, "8, 10, fill, default");
		lblNewLabel_9 = new JLabel("Nro. poliza");
		contentPanel.add(lblNewLabel_9, "2, 12");
		textNroPoliza = new JTextField();
		contentPanel.add(textNroPoliza, "4, 12, fill, default");
		lblNewLabel_10 = new JLabel("Garantia");
		contentPanel.add(lblNewLabel_10, "6, 12");
		textGarantia = new JTextField();
		contentPanel.add(textGarantia, "8, 12, fill, default");
		lblNewLabel_2 = new JLabel("Patente");
		contentPanel.add(lblNewLabel_2, "2, 14, left, default");
		textPatente = new JTextField();
		contentPanel.add(textPatente, "4, 14, fill, default");
	}

	
	public CompraVehiculoUsadoDTO getData() {
		CompraVehiculoUsadoDTO compra = new CompraVehiculoUsadoDTO();
		compra.setNroMotor(this.textNroDeMotor.getText());
		compra.setNroMotor(this.textNroDeMotor.getText());
		compra.setPatente(this.textPatente.getText());
		compra.setKilometraje(this.textKilometraje.getText());
		compra.setMarca(this.textMarca.getText());
		compra.setModelo(this.textModelo.getText());
		compra.setColor(this.textColor.getText());
		compra.setCombustion(this.textCombustion.getText());
		compra.setAsegurador(this.textAsegurador.getText());
		compra.setNroPolizaSeguro(this.textNroPoliza.getText());
		compra.setKilometrajeGarantia(this.textGarantia.getText());
		compra.setFamilia(this.txtFamilia.getText());
		compra.setLinea(this.txtLinea.getText());
		return compra;
		
	}
}
