package presentacion.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.AltaClienteDTO;
import dto.ClienteDTO;
import dto.DatosPersonalesDTO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionEvent;

public class ClienteFormView extends JDialog {

	private static final long serialVersionUID = -6293870932970697649L;
	private final JPanel contentPanel = new JPanel();

	private static ClienteFormView instance;
	private JButton btnSalvar;
	private JButton btnUpdate;
	private JTextField textNombre;
	private JTextField textTelefono;
	private JTextField textDni;
	private JTextField textEmail;
	private JTextField textCalle;
	private JTextField textAltura;
	private JTextField textPiso;
	private JTextField textDepto;
	private JTextField textLocalidad;

	private ClienteFormView() {
		setBounds(100, 100, 506, 253);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("14px"),
				ColumnSpec.decode("right:max(37dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(80dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("right:51px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(93dlu;default):grow"),},
			new RowSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel label = new JLabel("Nombre");
		panel.add(label, "2, 2, right, default");
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		panel.add(textNombre, "4, 2, fill, default");
		
		JLabel label_2 = new JLabel("Dni");
		panel.add(label_2, "6, 2, right, default");
		
		textDni = new JTextField();
		textDni.setColumns(10);
		panel.add(textDni, "8, 2, fill, default");
		
		JLabel label_1 = new JLabel("Teléfono");
		panel.add(label_1, "2, 4, right, default");
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		panel.add(textTelefono, "4, 4, fill, default");
		
		JLabel label_3 = new JLabel("Email");
		panel.add(label_3, "6, 4, right, default");
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		panel.add(textEmail, "8, 4, fill, default");
		
		JLabel lblCalle = new JLabel("Calle");
		panel.add(lblCalle, "2, 6, right, default");
		
		textCalle = new JTextField();
		textCalle.setColumns(10);
		panel.add(textCalle, "4, 6, fill, default");
		
		JLabel label_4 = new JLabel("Altura");
		panel.add(label_4, "6, 6, right, default");
		
		textAltura = new JTextField();
		textAltura.setColumns(10);
		panel.add(textAltura, "8, 6, fill, default");
		
		JLabel label_5 = new JLabel("Piso");
		panel.add(label_5, "2, 8, right, default");
		
		textPiso = new JTextField();
		textPiso.setColumns(10);
		panel.add(textPiso, "4, 8, fill, default");
		
		JLabel label_6 = new JLabel("Depto.");
		panel.add(label_6, "6, 8, right, default");
		
		textDepto = new JTextField();
		textDepto.setColumns(10);
		panel.add(textDepto, "8, 8, fill, default");
		
		JLabel label_7 = new JLabel("Localidad");
		panel.add(label_7, "2, 10, right, default");
		
		textLocalidad = new JTextField();
		textLocalidad.setColumns(10);
		panel.add(textLocalidad, "4, 10, fill, default");
		
		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1, BorderLayout.SOUTH);
				
		btnSalvar = new JButton("Salvar");
		panel_1.add(btnSalvar);
								
		btnUpdate = new JButton("Salvar");
		panel_1.add(btnUpdate);
		btnUpdate.setVisible(false);
						
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		panel_1.add(btnCancelar);

		setVisible(false);
		clearData();
	}

	public static ClienteFormView getInstance() {
		if (instance == null) {
			instance = new ClienteFormView();
		}
		return instance;
	}


	public AltaClienteDTO getData() {
		AltaClienteDTO datosPersonales = new AltaClienteDTO();
		datosPersonales.setNombreCompleto(textNombre.getText());
		datosPersonales.setDni(this.textDni.getText());
		datosPersonales.setEmail(textEmail.getText());
		datosPersonales.setTelefono(textTelefono.getText());
		datosPersonales.setCalle(textCalle.getText());
		datosPersonales.setAltura(textAltura.getText());
		datosPersonales.setPiso(textPiso.getText());
		datosPersonales.setDpto(textDepto.getText());
		datosPersonales.setLocalidad(textLocalidad.getText());
		return datosPersonales;

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
		textNombre.setText("");
		textDni.setText("");
		textEmail.setText("");
		textTelefono.setText("");
		textCalle.setText("");
		textAltura.setText("");
		textPiso.setText("");
		textDepto.setText("");
		textLocalidad.setText("");

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