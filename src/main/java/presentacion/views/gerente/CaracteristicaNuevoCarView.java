package presentacion.views.gerente;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.CaracteristicaVehiculoDTO;

@SuppressWarnings("serial")
public class CaracteristicaNuevoCarView extends JPanel {

	private static CaracteristicaNuevoCarView instance;
	private JTextField textTransmision;
	private JTextField textCilindrada;
	private JTextField textDireccion;
	private JTextField textPotencia;
	private JTextField textFrenosDelanteros;
	private JTextField textFrenosTraseros;
	private JTextField textTorqueMaximo;
	private JTextField textVolumenBaul;
	private JTextField textCantidadPuertas;
	private JLabel lblMotor;
	private JTextField textMotor;
	private JLabel lblPrecio;
	private JTextField textPrecio;

	public CaracteristicaNuevoCarView() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Caracteristicas del vehiculo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("right:123px"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("122px:grow"), },
				new RowSpec[] { FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel_1 = new JLabel("Cilindrada");
		add(lblNewLabel_1, "2, 4, right, center");

		textCilindrada = new JTextField();
		textCilindrada.setFocusable(false);
		add(textCilindrada, "4, 4, fill, top");
		textCilindrada.setColumns(10);

		lblMotor = new JLabel("Motor");
		add(lblMotor, "2, 6, right, default");

		textMotor = new JTextField();
		textMotor.setFocusable(false);
		add(textMotor, "4, 6, fill, top");
		textMotor.setColumns(10);

		JLabel lblNewLabel = new JLabel("Transmision");
		add(lblNewLabel, "2, 8, right, center");

		textTransmision = new JTextField();
		textTransmision.setFocusable(false);
		add(textTransmision, "4, 8, fill, top");
		textTransmision.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Direccion");
		add(lblNewLabel_2, "2, 10, right, center");

		textDireccion = new JTextField();
		textDireccion.setFocusable(false);
		add(textDireccion, "4, 10, fill, top");
		textDireccion.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Potencia");
		add(lblNewLabel_3, "2, 12, right, center");

		textPotencia = new JTextField();
		textPotencia.setFocusable(false);
		add(textPotencia, "4, 12, fill, top");
		textPotencia.setColumns(10);

		JLabel Calle = new JLabel("Frenos delanteros");
		add(Calle, "2, 14, right, center");

		textFrenosDelanteros = new JTextField();
		textFrenosDelanteros.setFocusable(false);
		add(textFrenosDelanteros, "4, 14, fill, top");
		textFrenosDelanteros.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Frenos traseros");
		add(lblNewLabel_4, "2, 16, right, center");

		textFrenosTraseros = new JTextField();
		textFrenosTraseros.setFocusable(false);
		add(textFrenosTraseros, "4, 16, fill, top");
		textFrenosTraseros.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Torque maximo");
		add(lblNewLabel_5, "2, 18, right, center");

		textTorqueMaximo = new JTextField();
		textTorqueMaximo.setFocusable(false);
		add(textTorqueMaximo, "4, 18, fill, top");
		textTorqueMaximo.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Volumen baul");
		add(lblNewLabel_6, "2, 20, right, center");

		textVolumenBaul = new JTextField();
		textVolumenBaul.setFocusable(false);
		add(textVolumenBaul, "4, 20, fill, top");
		textVolumenBaul.setColumns(10);

		lblPrecio = new JLabel("Precio");
		add(lblPrecio, "2, 24, right, default");

		textPrecio = new JTextField();
		textPrecio.setFocusable(false);
		add(textPrecio, "4, 24, fill, top");
		textPrecio.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Cantidad puertas");
		add(lblNewLabel_7, "2, 22, right, center");

		textCantidadPuertas = new JTextField();
		textCantidadPuertas.setFocusable(false);
		add(textCantidadPuertas, "4, 22, fill, top");
		textCantidadPuertas.setColumns(10);

		this.disableAllCaracteristicasInputs();
	}

	public static CaracteristicaNuevoCarView getInstance() {
		if (instance == null) {
			instance = new CaracteristicaNuevoCarView();
		}
		return instance;
	}

	private void disableAllCaracteristicasInputs() {
		this.textCilindrada.setEditable(false);
		this.textTransmision.setEditable(false);
		this.textDireccion.setEditable(false);
		this.textPotencia.setEditable(false);
		this.textFrenosDelanteros.setEditable(false);
		this.textFrenosTraseros.setEditable(false);
		this.textTorqueMaximo.setEditable(false);
		this.textVolumenBaul.setEditable(false);
		this.textCantidadPuertas.setEditable(false);
		this.textMotor.setEditable(false);
		this.textPrecio.setEditable(false);
	}

	public void clearData() {
		this.textCilindrada.setText("");
		this.textTransmision.setText("");
		this.textDireccion.setText("");
		this.textPotencia.setText("");
		this.textFrenosDelanteros.setText("");
		this.textFrenosTraseros.setText("");
		this.textTorqueMaximo.setText("");
		this.textVolumenBaul.setText("");
		this.textCantidadPuertas.setText("");
		this.textMotor.setText("");
		this.textPrecio.setText("");
	}

	public void setData(CaracteristicaVehiculoDTO caracteristica) {
		this.textCilindrada.setText(caracteristica.getCilindrada());
		this.textMotor.setText(caracteristica.getMotor());
		this.textTransmision.setText(caracteristica.getTransmision());
		this.textDireccion.setText(caracteristica.getDireccion());
		this.textPotencia.setText(caracteristica.getPotencia());
		this.textFrenosDelanteros.setText(caracteristica.getFrenosDelanteros());
		this.textFrenosTraseros.setText(caracteristica.getFrenosTraseros());
		this.textTorqueMaximo.setText(caracteristica.getTorqueMaximo());
		this.textVolumenBaul.setText(caracteristica.getVolumenBaul());
		this.textCantidadPuertas.setText(caracteristica.getCantidadPuertas());
		this.textPrecio.setText(caracteristica.getPrecio());
	}

}
