package presentacion.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class PanelClientesView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5623725856065117794L;
	private JTextField txtDNI;
	
	private final String[] columnasTablaVehiculos = new String [] {"NRO. VEHICULO", "KM GARANTIA", "ASEGURADORA", "NRO POLIZA SEGURO", "PATENTE"};
	private DefaultTableModel tableModelVehiculos;
	private JTable tablaVehiculos;

	private static PanelClientesView instance;
	private JButton btnBuscar;
	private JButton btnEditar;
	private JButton btnRegistrar;
	private JTextField textNombre;
	private JTextField textDni;
	private JTextField textTelefono;
	private JTextField textEmail;
	private JTextField textCalle;
	private JTextField textAltura;
	private JTextField textPiso;
	private JTextField textDpto;
	private JTextField textLocalidad;
	private JTextField textFechaAlta;
	
	public PanelClientesView() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		splitPane.setLeftComponent(panel_3);
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
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
		panel_3.add(lblNewLabel, "2, 2");
		
		textNombre = new JTextField();
		panel_3.add(textNombre, "4, 2, fill, default");
		textNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Dni");
		panel_3.add(lblNewLabel_1, "2, 4");
		
		textDni = new JTextField();
		panel_3.add(textDni, "4, 4, fill, default");
		textDni.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Telefono");
		panel_3.add(lblNewLabel_2, "2, 6");
		
		textTelefono = new JTextField();
		panel_3.add(textTelefono, "4, 6, fill, default");
		textTelefono.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		panel_3.add(lblNewLabel_3, "2, 8");
		
		textEmail = new JTextField();
		panel_3.add(textEmail, "4, 8, fill, default");
		textEmail.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Calle");
		panel_3.add(lblNewLabel_4, "2, 10");
		
		textCalle = new JTextField();
		panel_3.add(textCalle, "4, 10, fill, default");
		textCalle.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Altura");
		panel_3.add(lblNewLabel_5, "2, 12");
		
		textAltura = new JTextField();
		panel_3.add(textAltura, "4, 12, fill, default");
		textAltura.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Piso");
		panel_3.add(lblNewLabel_6, "2, 14");
		
		textPiso = new JTextField();
		panel_3.add(textPiso, "4, 14, fill, default");
		textPiso.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Dpto");
		panel_3.add(lblNewLabel_7, "2, 16");
		
		textDpto = new JTextField();
		panel_3.add(textDpto, "4, 16, fill, default");
		textDpto.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Localidad");
		panel_3.add(lblNewLabel_8, "2, 18");
		
		textLocalidad = new JTextField();
		panel_3.add(textLocalidad, "4, 18, fill, default");
		textLocalidad.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Fecha de Alta");
		panel_3.add(lblNewLabel_9, "2, 20");
		
		textFechaAlta = new JTextField();
		panel_3.add(textFechaAlta, "4, 20, fill, default");
		textFechaAlta.setColumns(10);
		
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
		
		tableModelVehiculos = new DefaultTableModel(null, this.columnasTablaVehiculos);
		tablaVehiculos = new JTable(tableModelVehiculos);
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
		textNombre.setText(datos.getNombreCompleto());
		textDni.setText(datos.getDni() + "");
		textEmail.setText(datos.getEmail());
		textTelefono.setText(datos.getTelefono());
		textCalle.setText(datos.getCalle());
		textAltura.setText(datos.getCalle());
		textPiso.setText(datos.getPiso() + "");
		textDpto.setText(datos.getDpto());
		textLocalidad.setText(datos.getLocalidad());
		textFechaAlta.setText(cliente.getFechaAltaCliente().toString());
	}
	
	public void clearData() {
		textNombre.setText("");
		textDni.setText("");
		textEmail.setText("");
		textTelefono.setText("");
		textCalle.setText("");
		textAltura.setText("");
		textPiso.setText("");
		textDpto.setText("");
		textLocalidad.setText("");
		textFechaAlta.setText("");
	}
	
	public void setOnSearchAction(ActionListener a) {
		this.btnBuscar.addActionListener(a);
	}

	public Integer getDniCliente() {
		if(txtDNI.getText() != null)return Integer.parseInt(txtDNI.getText());
		return null;
	}
	
	public void setOnUpdateAction(ActionListener a) {
		this.btnEditar.addActionListener(a);
	}
	
	public void setOnCreateAction(ActionListener a) {
		this.btnRegistrar.addActionListener(a);
	}
}
