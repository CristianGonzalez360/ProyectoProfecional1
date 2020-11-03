package presentacion.views;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.ClienteDTO;
import dto.DatosPersonalesDTO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class ClienteFormView extends JDialog {

	private static final long serialVersionUID = -6293870932970697649L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textTelefono;
	private JTextField textCalle;
	private JTextField textPiso;
	private JTextField textDepto;
	private JTextField textDni;
	private JTextField textEmail;
	private JTextField textAltura;
	private JTextField textLocalidad;
	
	private static ClienteFormView instance;
	private JButton btnSalvar;
	private JButton btnUpdate;

	private ClienteFormView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(20, 21, 46, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblTelfono = new JLabel("Teléfono");
		lblTelfono.setBounds(20, 57, 46, 14);
		contentPanel.add(lblTelfono);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(20, 121, 46, 14);
		contentPanel.add(lblCalle);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(20, 149, 46, 14);
		contentPanel.add(lblPiso);
		
		JLabel lblDepto = new JLabel("Depto.");
		lblDepto.setBounds(128, 149, 46, 14);
		contentPanel.add(lblDepto);
		
		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(241, 24, 46, 14);
		contentPanel.add(lblDni);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(241, 60, 46, 14);
		contentPanel.add(lblEmail);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(241, 114, 46, 14);
		contentPanel.add(lblAltura);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(241, 149, 46, 14);
		contentPanel.add(lblLocalidad);
		
		textNombre = new JTextField();
		textNombre.setBounds(88, 18, 120, 20);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(88, 54, 120, 20);
		contentPanel.add(textTelefono);
		textTelefono.setColumns(10);
		
		textCalle = new JTextField();
		textCalle.setBounds(88, 118, 120, 20);
		contentPanel.add(textCalle);
		textCalle.setColumns(10);
		
		textPiso = new JTextField();
		textPiso.setBounds(54, 146, 46, 20);
		contentPanel.add(textPiso);
		textPiso.setColumns(10);
		
		textDepto = new JTextField();
		textDepto.setBounds(166, 146, 46, 20);
		contentPanel.add(textDepto);
		textDepto.setColumns(10);
		
		textDni = new JTextField();
		textDni.setBounds(284, 21, 120, 20);
		contentPanel.add(textDni);
		textDni.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(284, 57, 120, 20);
		contentPanel.add(textEmail);
		textEmail.setColumns(10);
		
		textAltura = new JTextField();
		textAltura.setBounds(307, 111, 97, 20);
		contentPanel.add(textAltura);
		textAltura.setColumns(10);
		
		textLocalidad = new JTextField();
		textLocalidad.setBounds(307, 146, 97, 20);
		contentPanel.add(textLocalidad);
		textLocalidad.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar vehículo");
		btnRegistrar.setBounds(41, 194, 89, 23);
		contentPanel.add(btnRegistrar);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(171, 194, 89, 23);
		contentPanel.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(301, 194, 89, 23);
		contentPanel.add(btnCancelar);
		
		btnUpdate = new JButton("Salvar");
		btnUpdate.setVisible(false);
		btnUpdate.setBounds(171, 194, 89, 23);
		contentPanel.add(btnUpdate);
		
		setVisible(false);
//		{//duda si va o no
//			JPanel buttonPane = new JPanel();
//			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				JButton cancelButton = new JButton("Cancel");
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
//		}
	}
	
	public static ClienteFormView getInstance() {
		if(instance == null) {
			instance = new ClienteFormView();
		}
		return instance;
	}
	
	public ClienteDTO getData() {
		ClienteDTO ret = new ClienteDTO();
		ret.setFechaAltaCliente(new Date());
		DatosPersonalesDTO datosPersonales = new DatosPersonalesDTO();
		datosPersonales.setNombreCompleto(textNombre.getText());
		datosPersonales.setDni(Integer.parseInt(textDni.getText()));
		datosPersonales.setEmail(textEmail.getText());
		datosPersonales.setTelefono(textTelefono.getText());
		datosPersonales.setCalle(textCalle.getText());
		datosPersonales.setAltura(Integer.parseInt(textAltura.getText()));
		datosPersonales.setPiso(Integer.parseInt(textPiso.getText()));
		datosPersonales.setDpto(textDepto.getText());
		datosPersonales.setLocalidad(textLocalidad.getText());
		ret.setDatosPersonalesDTO(datosPersonales);
		return ret;
	}
	
	public void setData(ClienteDTO cliente) {
		DatosPersonalesDTO datos = cliente.getDatosPersonalesDTO();
		textNombre.setText(datos.getNombreCompleto());
		textDni.setText(datos.getDni() + "");
		textEmail.setText(datos.getEmail());
		textTelefono.setText(datos.getTelefono());
		textCalle.setText(datos.getCalle());
		textAltura.setText(datos.getAltura() + "");
		textPiso.setText(datos.getPiso() + "");
		textDepto.setText(datos.getDpto());
		textLocalidad.setText(datos.getLocalidad());
		
		this.btnSalvar.setVisible(false);
		this.btnUpdate.setVisible(true);
	}
	
	public void setActionOnSave(ActionListener listener) {
		this.btnSalvar.addActionListener(listener);
	}

	public void setActionOnUpdate(ActionListener listener) {
		this.btnUpdate.addActionListener(listener);
	}
	
	public void clearData() {
		textNombre.setText(null);
		textDni.setText(null);
		textEmail.setText(null);
		textTelefono.setText(null);
		textCalle.setText(null);
		textAltura.setText(null);
		textPiso.setText(null);
		textDepto.setText(null);
		textLocalidad.setText(null);
		
		this.btnSalvar.setVisible(true);
		this.btnUpdate.setVisible(false);
	}

	public void display() {
		setVisible(true);
	}
	
	public void close() {
		setVisible(false);
	}
}
