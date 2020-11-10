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

public class ClientePanelView extends JPanel {

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
	
	public ClientePanelView() {
		setBorder(
				new TitledBorder(null, "Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("14px"), ColumnSpec.decode("max(0dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(80dlu;default):grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("73px:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(93dlu;default):grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("26px"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel = new JLabel("Dni");
		add(lblNewLabel, "2, 4");

		textDni = new JTextField();
		add(textDni, "4, 4, fill, default");
		textDni.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		add(lblNewLabel_1, "6, 4");

		textNombre = new JTextField();
		add(textNombre, "8, 4, fill, default");
		textNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Telefono");
		add(lblNewLabel_2, "2, 6");

		textTelefono = new JTextField();
		add(textTelefono, "4, 6, fill, default");
		textTelefono.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Email");
		add(lblNewLabel_3, "6, 6");

		textEmail = new JTextField();
		add(textEmail, "8, 6, fill, default");
		textEmail.setColumns(10);

		JLabel Calle = new JLabel("calle");
		add(Calle, "2, 8");

		textCalle = new JTextField();
		add(textCalle, "4, 8, fill, default");
		textCalle.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Altura");
		add(lblNewLabel_4, "6, 8");

		textAltura = new JTextField();
		add(textAltura, "8, 8, fill, default");
		textAltura.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Piso");
		add(lblNewLabel_5, "2, 10");

		textPiso = new JTextField();
		add(textPiso, "4, 10, fill, default");
		textPiso.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Dpto");
		add(lblNewLabel_6, "6, 10");

		textDpto = new JTextField();
		add(textDpto, "8, 10, fill, default");
		textDpto.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("localidad");
		add(lblNewLabel_7, "2, 12, right, default");

		textLocalidad = new JTextField();
		add(textLocalidad, "4, 12, fill, default");
		textLocalidad.setColumns(10);
		
		this.disableAllClienteInputs();
	}

	private void disableAllClienteInputs() {
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
	}
	
	public void setData(ClienteDTO cliente) {
		this.idDatosPersonalesDelCliente = cliente.getDatosPersonalesDTO().getId();
		this.idCliente = cliente.getIdCliente();
		this.textNombre.setText(cliente.getDatosPersonalesDTO().getNombreCompleto());
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
		cliente.getDatosPersonalesDTO().setDni(textDni.getText().trim().isEmpty() ? null : Integer.parseInt(textDni.getText()));
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
