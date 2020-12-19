package presentacion.views.admin;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.DatosPersonalesDTO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class DatosPersonalesPanel extends JPanel {

	private static final long serialVersionUID = 1506820789976519507L;

	private JTextField textNombre;

	private JTextField textApellido;

	private JTextField textDni;

	private JTextField textTelefono;

	private JTextField textEmail;

	private JTextField textCalle;

	private JTextField textAltura;

	private JTextField textPiso;

	private JTextField textDpto;

	private JTextField textLocalidad;

	public DatosPersonalesPanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		JLabel lblNewLabel = new JLabel("Nombre");
		add(lblNewLabel, "2, 2, left, default");

		textNombre = new JTextField();
		add(textNombre, "4, 2, fill, default");
		textNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Apellido");
		add(lblNewLabel_1, "2, 4, left, default");

		textApellido = new JTextField();
		add(textApellido, "4, 4, fill, default");
		textApellido.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("DNI");
		add(lblNewLabel_2, "2, 6, left, default");

		textDni = new JTextField();
		add(textDni, "4, 6, fill, default");
		textDni.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Teléfono");
		add(lblNewLabel_3, "2, 8, left, default");

		textTelefono = new JTextField();
		add(textTelefono, "4, 8, fill, default");
		textTelefono.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Email");
		add(lblNewLabel_4, "2, 10, left, default");

		textEmail = new JTextField();
		add(textEmail, "4, 10, fill, default");
		textEmail.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Calle");
		add(lblNewLabel_5, "2, 12, left, default");

		textCalle = new JTextField();
		add(textCalle, "4, 12, fill, default");
		textCalle.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Altura");
		add(lblNewLabel_6, "2, 14, left, default");

		textAltura = new JTextField();
		add(textAltura, "4, 14, fill, default");
		textAltura.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Piso");
		add(lblNewLabel_7, "2, 16, left, default");

		textPiso = new JTextField();
		add(textPiso, "4, 16, fill, default");
		textPiso.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Departamento");
		add(lblNewLabel_8, "2, 18, left, default");

		textDpto = new JTextField();
		add(textDpto, "4, 18, fill, default");
		textDpto.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Localidad");
		add(lblNewLabel_9, "2, 20, left, default");

		textLocalidad = new JTextField();
		add(textLocalidad, "4, 20, fill, default");
		textLocalidad.setColumns(10);
		clearData();
	}

	public void setBorderTitle(String string) {
		setBorder(new TitledBorder(null, string, TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public void setData(DatosPersonalesDTO datos) {
		textNombre.setText(datos.getNombreCompleto());
		textApellido.setText(datos.getApellido());
		textDni.setText(datos.getDni().toString());
		textTelefono.setText(datos.getTelefono());
		textEmail.setText(datos.getEmail());
		textCalle.setText(datos.getCalle());
		textAltura.setText(datos.getAltura());
		textPiso.setText(datos.getPiso());
		textDpto.setText(datos.getDpto());
		textLocalidad.setText(datos.getLocalidad());
	}

	public void clearData() {
		textNombre.setText("");
		textApellido.setText("");
		textDni.setText("");
		textTelefono.setText("");
		textEmail.setText("");
		textCalle.setText("");
		textAltura.setText("");
		textPiso.setText("");
		textDpto.setText("");
		textLocalidad.setText("");
	}

	public DatosPersonalesDTO getData() {
		DatosPersonalesDTO dto = new DatosPersonalesDTO();
		dto.setNombreCompleto(textNombre.getText());
		dto.setApellido(textApellido.getText());
		try {
			Integer.parseInt(textDni.getText());
			dto.setDni(Integer.parseInt(textDni.getText()));
		} catch (NumberFormatException e) {
			dto.setDni(null);
		}
		dto.setTelefono(textTelefono.getText());
		dto.setEmail(textEmail.getText());
		dto.setCalle(textCalle.getText());
		dto.setAltura(textAltura.getText());
		dto.setPiso(textPiso.getText());
		dto.setDpto(textDpto.getText());
		dto.setLocalidad(textLocalidad.getText());
		return dto;
	}
}
