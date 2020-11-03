package presentacion.views;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.AltaDeVehiculoDTO;

public class VehiculoFormView extends JDialog {

	private static final long serialVersionUID = -2246189166156653659L;
	private final JPanel contentPanel = new JPanel();

	private JTextField textNroChasis;
	private final JLabel lblNewLabel_1 = new JLabel("Nro. de motor");
	private final JTextField textNroDeMotor = new JTextField();
	private final JLabel lblNewLabel_2 = new JLabel("Patente");
	private final JTextField textPatente = new JTextField();
	private final JLabel lblNewLabel_3 = new JLabel("Kilometraje");
	private final JTextField textKilometraje = new JTextField();
	private final JLabel lblNewLabel_4 = new JLabel("Marca");
	private final JTextField textMarca = new JTextField();
	private final JLabel lblNewLabel_5 = new JLabel("Modelo");
	private final JTextField textModelo = new JTextField();
	private final JLabel lblNewLabel_6 = new JLabel("Color");
	private final JTextField textColor = new JTextField();
	private final JLabel lblNewLabel_7 = new JLabel("Combustion");
	private final JTextField textCombustion = new JTextField();
	private final JLabel lblNewLabel_8 = new JLabel("Asegurador");
	private final JTextField textAsegurador = new JTextField();
	private final JLabel lblNewLabel_9 = new JLabel("Nro. poliza");
	private final JTextField textNroPoliza = new JTextField();
	private final JLabel lblNewLabel_10 = new JLabel("Garantia");
	private final JTextField textGarantia = new JTextField();
	private final JPanel panel = new JPanel();
	private final JButton btnSalvar = new JButton("Salvar");
	private final JButton btnCancelar = new JButton("Cancelar");

	private static VehiculoFormView instance;

	public static VehiculoFormView getInstance() {
		if (instance == null)
			instance = new VehiculoFormView();
		return instance;
	}

	private VehiculoFormView() {
		setTitle("Form alta de vehículo");
		setResizable(false);
		setModal(true);
		textGarantia.setColumns(10);
		textNroPoliza.setColumns(10);
		textAsegurador.setColumns(10);
		textCombustion.setColumns(10);
		textColor.setColumns(10);
		textModelo.setColumns(10);
		textMarca.setColumns(10);
		textKilometraje.setColumns(10);
		textPatente.setColumns(10);
		textNroDeMotor.setColumns(10);
		setBounds(100, 100, 607, 355);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

		JLabel lblNewLabel = new JLabel("Nro. de chasis");
		contentPanel.add(lblNewLabel, "2, 2");

		textNroChasis = new JTextField();
		contentPanel.add(textNroChasis, "4, 2");
		textNroChasis.setColumns(10);

		contentPanel.add(lblNewLabel_1, "6, 2, right, default");

		contentPanel.add(textNroDeMotor, "8, 2, fill, default");

		contentPanel.add(lblNewLabel_2, "2, 4, left, default");

		contentPanel.add(textPatente, "4, 4, fill, default");

		contentPanel.add(lblNewLabel_3, "6, 4");

		contentPanel.add(textKilometraje, "8, 4, fill, default");

		contentPanel.add(lblNewLabel_4, "2, 6");

		contentPanel.add(textMarca, "4, 6, fill, default");

		contentPanel.add(lblNewLabel_5, "6, 6");

		contentPanel.add(textModelo, "8, 6, fill, default");

		contentPanel.add(lblNewLabel_6, "2, 8");

		contentPanel.add(textColor, "4, 8, fill, default");

		contentPanel.add(lblNewLabel_7, "6, 8");

		contentPanel.add(textCombustion, "8, 8, fill, default");

		contentPanel.add(lblNewLabel_8, "2, 10");

		contentPanel.add(textAsegurador, "4, 10, fill, default");

		contentPanel.add(lblNewLabel_9, "6, 10");

		contentPanel.add(textNroPoliza, "8, 10, fill, default");

		contentPanel.add(lblNewLabel_10, "2, 12");

		contentPanel.add(textGarantia, "4, 12, fill, default");

		contentPanel.add(panel, "2, 14, 7, 1, fill, bottom");

		panel.add(btnSalvar);

		panel.add(btnCancelar);
	}

	public void clearData() {
		this.textNroChasis.setText("");
		this.textNroDeMotor.setText("");
		this.textPatente.setText("");
		this.textKilometraje.setText("");
		this.textMarca.setText("");
		this.textModelo.setText("");
		this.textColor.setText("");
		this.textCombustion.setText("");
		this.textAsegurador.setText("");
		this.textNroPoliza.setText("");
		this.textGarantia.setText("");
	}

	public AltaDeVehiculoDTO getData() {
		AltaDeVehiculoDTO dto = new AltaDeVehiculoDTO();
		dto.setNroChasis(this.textNroChasis.getText());
		dto.setNroMotor(this.textNroDeMotor.getText());
		dto.setPatente(this.textPatente.getText());
		dto.setKilometraje(this.textKilometraje.getText());
		dto.setMarca(this.textMarca.getText());
		dto.setModelo(this.textModelo.getText());
		dto.setColor(this.textColor.getText());
		dto.setCombustion(this.textCombustion.getText());
		dto.setAsegurador(this.textAsegurador.getText());
		dto.setNroPolizaSeguro(this.textNroPoliza.getText());
		dto.setKilometrajeGarantia(this.textGarantia.getText());
		return dto;
	}

	public void display() {
		this.setVisible(true);
	}

	public void close() {
		this.setVisible(false);
	}

	public void setActionSave(ActionListener listener) {
		this.btnSalvar.addActionListener(listener);
	}

	public void setActionCancel(ActionListener listener) {
		this.btnCancelar.addActionListener(listener);
	}
}
