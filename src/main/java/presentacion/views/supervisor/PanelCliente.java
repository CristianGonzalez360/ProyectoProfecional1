package presentacion.views.supervisor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.ClienteDTO;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class PanelCliente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8951435029326569045L;

	private Integer idCliente;

	private Integer idDatosPersonalesDelCliente;

	private JTextField textDni;

	private JTextField textNombre;

	private JTextField textTelefono;

	private JTextField textEmail;

	private JTextField textCalle;

	private JTextField textAltura;

	private JTextField textPiso;

	private JTextField textDpto;

	private JTextField textLocalidad;

	private JLabel lblApellido;

	private JTextField tfApellido;

	public PanelCliente() {
		setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Datos del cliente",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("right:72px"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("122px:grow"), },
				new RowSpec[] { FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		add(lblNewLabel_1, "2, 2, right, center");

		textNombre = new JTextField();
		textNombre.setFocusable(false);
		add(textNombre, "4, 2, fill, top");
		textNombre.setColumns(10);

		lblApellido = new JLabel("Apellido");
		add(lblApellido, "2, 4, right, default");

		tfApellido = new JTextField();
		tfApellido.setFocusable(false);
		tfApellido.setEditable(false);
		add(tfApellido, "4, 4, fill, top");
		tfApellido.setColumns(10);

		JLabel lblNewLabel = new JLabel("Dni");
		add(lblNewLabel, "2, 6, right, center");

		textDni = new JTextField();
		textDni.setFocusable(false);
		add(textDni, "4, 6, fill, top");
		textDni.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Telefono");
		add(lblNewLabel_2, "2, 8, right, center");

		textTelefono = new JTextField();
		textTelefono.setFocusable(false);
		add(textTelefono, "4, 8, fill, top");
		textTelefono.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Email");
		add(lblNewLabel_3, "2, 10, right, center");

		textEmail = new JTextField();
		textEmail.setFocusable(false);
		add(textEmail, "4, 10, fill, top");
		textEmail.setColumns(10);

		JLabel Calle = new JLabel("calle");
		add(Calle, "2, 12, right, center");

		textCalle = new JTextField();
		textCalle.setFocusable(false);
		add(textCalle, "4, 12, fill, top");
		textCalle.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Altura");
		add(lblNewLabel_4, "2, 14, right, center");

		textAltura = new JTextField();
		textAltura.setFocusable(false);
		add(textAltura, "4, 14, fill, top");
		textAltura.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Piso");
		add(lblNewLabel_5, "2, 16, right, center");

		textPiso = new JTextField();
		textPiso.setFocusable(false);
		add(textPiso, "4, 16, fill, top");
		textPiso.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Dpto");
		add(lblNewLabel_6, "2, 18, right, center");

		textDpto = new JTextField();
		textDpto.setFocusable(false);
		add(textDpto, "4, 18, fill, top");
		textDpto.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Localidad");
		add(lblNewLabel_7, "2, 20, right, center");

		textLocalidad = new JTextField();
		textLocalidad.setFocusable(false);
		add(textLocalidad, "4, 20, fill, top");
		textLocalidad.setColumns(10);
	}

	public void disableAllClienteInputs() {
		this.textNombre.setEditable(false);
		this.textDni.setEditable(false);
		this.textTelefono.setEditable(false);
		this.textEmail.setEditable(false);
		this.textCalle.setEditable(false);
		this.textAltura.setEditable(false);
		this.textPiso.setEditable(false);
		this.textDpto.setEditable(false);
		this.textLocalidad.setEditable(false);
	}

	public void clearData() {
		this.idCliente = null;
		this.idDatosPersonalesDelCliente = null;
		this.textNombre.setText("");
		this.textDni.setText("");
		this.textTelefono.setText("");
		this.textEmail.setText("");
		this.textCalle.setText("");
		this.textAltura.setText("");
		this.textPiso.setText("");
		this.textDpto.setText("");
		this.textLocalidad.setText("");
		this.tfApellido.setText("");
	}

	public void setData(ClienteDTO cliente) {
		this.idDatosPersonalesDelCliente = cliente.getDatosPersonalesDTO().getId();
		this.idCliente = cliente.getIdCliente();
		this.textNombre.setText(cliente.getDatosPersonalesDTO().getNombreCompleto());
		this.tfApellido.setText(cliente.getDatosPersonalesDTO().getApellido());
		this.textDni.setText(cliente.getDatosPersonalesDTO().getDni().toString());
		this.textTelefono.setText(cliente.getDatosPersonalesDTO().getTelefono());
		this.textEmail.setText(cliente.getDatosPersonalesDTO().getEmail());
		this.textCalle.setText(cliente.getDatosPersonalesDTO().getCalle());
		this.textAltura.setText(cliente.getDatosPersonalesDTO().getAltura().toString());
		this.textPiso.setText(cliente.getDatosPersonalesDTO().getPiso().toString());
		this.textDpto.setText(cliente.getDatosPersonalesDTO().getDpto());
		this.textLocalidad.setText(cliente.getDatosPersonalesDTO().getLocalidad());
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public Integer getIdDatosPersonalesCliente() {
		return this.idDatosPersonalesDelCliente;
	}

	public ClienteDTO getData() {
		ClienteDTO cliente = new ClienteDTO();
		cliente.setIdCliente(idCliente);
		cliente.setIdDatosPersonales(idDatosPersonalesDelCliente);
		cliente.getDatosPersonalesDTO().setNombreCompleto(this.textNombre.getText());
		cliente.getDatosPersonalesDTO()
				.setDni(textDni.getText().trim().isEmpty() ? null : Integer.parseInt(textDni.getText()));
		cliente.getDatosPersonalesDTO().setTelefono(textTelefono.getText());
		cliente.getDatosPersonalesDTO().setTelefono(textTelefono.getText());
		cliente.getDatosPersonalesDTO().setEmail(textEmail.getText());
		cliente.getDatosPersonalesDTO().setCalle(textCalle.getText());
		cliente.getDatosPersonalesDTO().setAltura(textAltura.getSelectedText());
		cliente.getDatosPersonalesDTO().setPiso(textPiso.getText());
		cliente.getDatosPersonalesDTO().setDpto(textDpto.getText());
		cliente.getDatosPersonalesDTO().setLocalidad(textLocalidad.getText());
		return cliente;
	}
}