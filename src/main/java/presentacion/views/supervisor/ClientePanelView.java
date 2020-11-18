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
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Dni");
		lblNewLabel.setBounds(20, 50, 42, 14);
		add(lblNewLabel);

		textDni = new JTextField();
		textDni.setBounds(68, 47, 122, 20);
		add(textDni);
		textDni.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(196, 50, 76, 14);
		add(lblNewLabel_1);

		textNombre = new JTextField();
		textNombre.setBounds(278, 47, 141, 20);
		add(textNombre);
		textNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Telefono");
		lblNewLabel_2.setBounds(20, 79, 42, 14);
		add(lblNewLabel_2);

		textTelefono = new JTextField();
		textTelefono.setBounds(68, 76, 122, 20);
		add(textTelefono);
		textTelefono.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(196, 79, 76, 14);
		add(lblNewLabel_3);

		textEmail = new JTextField();
		textEmail.setBounds(278, 76, 141, 20);
		add(textEmail);
		textEmail.setColumns(10);

		JLabel Calle = new JLabel("calle");
		Calle.setBounds(20, 105, 42, 14);
		add(Calle);

		textCalle = new JTextField();
		textCalle.setBounds(68, 102, 122, 20);
		add(textCalle);
		textCalle.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Altura");
		lblNewLabel_4.setBounds(196, 105, 76, 14);
		add(lblNewLabel_4);

		textAltura = new JTextField();
		textAltura.setBounds(278, 102, 141, 20);
		add(textAltura);
		textAltura.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Piso");
		lblNewLabel_5.setBounds(20, 131, 42, 14);
		add(lblNewLabel_5);

		textPiso = new JTextField();
		textPiso.setBounds(68, 128, 122, 20);
		add(textPiso);
		textPiso.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Dpto");
		lblNewLabel_6.setBounds(196, 131, 76, 14);
		add(lblNewLabel_6);

		textDpto = new JTextField();
		textDpto.setBounds(278, 128, 141, 20);
		add(textDpto);
		textDpto.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("localidad");
		lblNewLabel_7.setBounds(21, 157, 41, 14);
		add(lblNewLabel_7);

		textLocalidad = new JTextField();
		textLocalidad.setBounds(68, 154, 122, 20);
		add(textLocalidad);
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
