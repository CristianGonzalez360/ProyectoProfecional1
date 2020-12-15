package presentacion.views.gerente;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
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
	private JTextField txtFamilia;
	private JTextField txtLinea;

	public FichaTecnicaPanel() {
		setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		// contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));
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
		lblNewLabel_2 = new JLabel("Patente");
		contentPanel.add(lblNewLabel_2, "6, 10, left, default");
		textPatente = new JTextField();
		contentPanel.add(textPatente, "8, 10, fill, default");
	}

	public CompraVehiculoUsadoDTO getData() {
		CompraVehiculoUsadoDTO compra = new CompraVehiculoUsadoDTO();
		compra.setNroMotor(this.textNroDeMotor.getText());
		compra.setNroChasis(this.textNroChasis.getText());
		compra.setPatente(this.textPatente.getText());
		compra.setKilometraje(this.textKilometraje.getText());
		compra.setMarca(this.textMarca.getText());
		compra.setModelo(this.textModelo.getText());
		compra.setColor(this.textColor.getText());
		compra.setCombustion(this.textCombustion.getText());
		compra.setFamilia(this.txtFamilia.getText());
		compra.setLinea(this.txtLinea.getText());
		return compra;

	}

	public void setdata(FichaTecnicaVehiculoDTO ficha) {
		this.textNroDeMotor.setText(ficha.getNroMotor() + "");
		this.textNroChasis.setText(ficha.getNroChasis() + "");
		this.textPatente.setText(ficha.getPatente());
		this.textKilometraje.setText(ficha.getKilometraje() + "");
		;
		this.textMarca.setText(ficha.getMarca());
		this.textModelo.setText(ficha.getModelo() + "");
		this.textColor.setText(ficha.getColor());
		this.textCombustion.setText(ficha.getCombustion());
	}

	public void setdata(VehiculoDTO vehiculo) {
		this.txtFamilia.setText(vehiculo.getFamilia());
		this.txtLinea.setText(vehiculo.getLinea());
	}

	public void cleardata() {
		this.textNroDeMotor.setText("");
		this.textNroChasis.setText("");
		this.textPatente.setText("");
		this.textKilometraje.setText("");
		;
		this.textMarca.setText("");
		this.textModelo.setText("");
		this.textColor.setText("");
		this.textCombustion.setText("");
	}
}
