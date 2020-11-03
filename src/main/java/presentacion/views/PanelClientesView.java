package presentacion.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import dto.ClienteDTO;
import dto.DatosPersonalesDTO;

public class PanelClientesView extends JPanel {

	private JTextField txtDNI;
	private JTable tablaVehiculos;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepto;
	private JTextField txtLocalidad;
	private JTextField txtAlta;

	private static PanelClientesView instance;
	private JButton btnBuscar;
	private JButton btnEditar;
	private JButton btnRegistrar;
	
	public PanelClientesView() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		splitPane.setLeftComponent(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 46, 14);
		panel_3.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(76, 8, 124, 20);
		panel_3.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(10, 36, 46, 14);
		panel_3.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setEditable(false);
		txtDni.setBounds(76, 33, 124, 20);
		panel_3.add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblTelfono = new JLabel("Teléfono:");
		lblTelfono.setBounds(10, 61, 46, 14);
		panel_3.add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setBounds(76, 58, 124, 20);
		panel_3.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 86, 46, 14);
		panel_3.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setBounds(76, 83, 124, 20);
		panel_3.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setBounds(10, 112, 46, 14);
		panel_3.add(lblCalle);
		
		txtCalle = new JTextField();
		txtCalle.setEditable(false);
		txtCalle.setBounds(76, 109, 124, 20);
		panel_3.add(txtCalle);
		txtCalle.setColumns(10);
		
		JLabel lblAltura = new JLabel("Altura:");
		lblAltura.setBounds(10, 137, 46, 14);
		panel_3.add(lblAltura);
		
		txtAltura = new JTextField();
		txtAltura.setEditable(false);
		txtAltura.setBounds(76, 134, 124, 20);
		panel_3.add(txtAltura);
		txtAltura.setColumns(10);
		
		JLabel lblPiso = new JLabel("Piso:");
		lblPiso.setBounds(10, 162, 46, 14);
		panel_3.add(lblPiso);
		
		txtPiso = new JTextField();
		txtPiso.setEditable(false);
		txtPiso.setBounds(76, 159, 124, 20);
		panel_3.add(txtPiso);
		txtPiso.setColumns(10);
		
		JLabel lblDepto = new JLabel("Depto:");
		lblDepto.setBounds(10, 187, 46, 14);
		panel_3.add(lblDepto);
		
		txtDepto = new JTextField();
		txtDepto.setEditable(false);
		txtDepto.setBounds(76, 184, 124, 20);
		panel_3.add(txtDepto);
		txtDepto.setColumns(10);
		
		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(10, 212, 56, 14);
		panel_3.add(lblLocalidad);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setEditable(false);
		txtLocalidad.setBounds(76, 209, 124, 20);
		panel_3.add(txtLocalidad);
		txtLocalidad.setColumns(10);
		
		JLabel lblAlta = new JLabel("Alta:");
		lblAlta.setBounds(10, 237, 56, 14);
		panel_3.add(lblAlta);
		
		txtAlta = new JTextField();
		txtAlta.setEditable(false);
		txtAlta.setBounds(76, 234, 124, 20);
		panel_3.add(txtAlta);
		txtAlta.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.NORTH);
		
		JLabel lblClienteDNI = new JLabel("Cliente DNI");
		panel_4.add(lblClienteDNI);
		
		txtDNI = new JTextField();
		panel_4.add(txtDNI);
		txtDNI.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		panel_4.add(btnBuscar);
		
		btnEditar = new JButton("Editar");
		panel_4.add(btnEditar);
		
		btnRegistrar = new JButton("Registrar");
		panel_4.add(btnRegistrar);
		
		JPanel panel_5 = new JPanel();
		splitPane.setRightComponent(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, BorderLayout.NORTH);
		
		tablaVehiculos = new JTable();
		JScrollPane scroll = new JScrollPane(tablaVehiculos);
		panel_5.add(scroll, BorderLayout.CENTER);
		
		JButton btnRegistrarVehiculo = new JButton("Registrar Vehículo");
		panel_6.add(btnRegistrarVehiculo);
		
		JButton btnEditarVehiculo = new JButton("Editar Vehículo");
		panel_6.add(btnEditarVehiculo);
		
		JButton btnRegistrarOrdenDe = new JButton("Registrar Orden de Trabajo");
		panel_6.add(btnRegistrarOrdenDe);
	}
	
	public static PanelClientesView getInstance() {
		if(instance == null) {
			instance = new PanelClientesView();
		}
		return instance;
	}
	
	public void setData(ClienteDTO cliente) {
		DatosPersonalesDTO datos = cliente.getDatosPersonalesDTO();
		txtNombre.setText(datos.getNombreCompleto());
		txtDni.setText(datos.getDni() + "");
		txtEmail.setText(datos.getEmail());
		txtTelefono.setText(datos.getTelefono());
		txtCalle.setText(datos.getCalle());
		txtAltura.setText(datos.getCalle());
		txtPiso.setText(datos.getPiso() + "");
		txtDepto.setText(datos.getDpto());
		txtLocalidad.setText(datos.getLocalidad());
		txtAlta.setText(cliente.getFechaAltaCliente().toString());
	}
	
	public void setOnSearchAction(ActionListener a) {
		this.btnBuscar.addActionListener(a);
	}

	public int getDniCliente() {
		return Integer.parseInt(txtDNI.getText());
	}
	
	public void setOnUpdateAction(ActionListener a) {
		this.btnEditar.addActionListener(a);
	}
	
	public void setOnCreateAction(ActionListener a) {
		this.btnRegistrar.addActionListener(a);
	}
}
