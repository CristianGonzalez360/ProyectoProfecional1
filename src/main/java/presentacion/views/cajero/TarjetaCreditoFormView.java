package presentacion.views.cajero;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class TarjetaCreditoFormView extends JDialog {

	private static final long serialVersionUID = -6293870932970697649L;
	private final JPanel contentPanel = new JPanel();

	private static TarjetaCreditoFormView instance;
	private JButton btnSalvar;
	private JTextField textNombre;
	private JTextField textDni;
	private JTextField textEmail;
	private JTextField textCalle;
	private JTextField textAltura;
	private JTextField textDepto;

	private TarjetaCreditoFormView() {
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
				ColumnSpec.decode("right:89px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(47dlu;default):grow"),},
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

		JLabel lblNumeroTarjeta = new JLabel("Numero tarjeta");
		panel.add(lblNumeroTarjeta, "2, 2, right, default");

		textNombre = new JTextField();
		textNombre.setColumns(10);
		panel.add(textNombre, "4, 2, fill, default");

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		panel.add(lblNombreYApellido, "2, 4, right, default");

		textDni = new JTextField();
		textDni.setColumns(10);
		panel.add(textDni, "4, 4, fill, default");

		JLabel lblFechaExpiracion = new JLabel("Fecha expiracion");
		panel.add(lblFechaExpiracion, "2, 6, right, default");

		textEmail = new JTextField();
		textEmail.setColumns(10);
		panel.add(textEmail, "4, 6, fill, default");

		JLabel lblCalle = new JLabel("Cod Seguridad");
		panel.add(lblCalle, "6, 6, right, default");

		textCalle = new JTextField();
		textCalle.setColumns(10);
		panel.add(textCalle, "8, 6, fill, default");

		JLabel lblDni = new JLabel("DNI");
		panel.add(lblDni, "2, 8, right, default");

		textAltura = new JTextField();
		textAltura.setColumns(10);
		panel.add(textAltura, "4, 8, fill, default");

		JLabel lblCuotas = new JLabel("Cuotas");
		panel.add(lblCuotas, "2, 10, right, default");

		textDepto = new JTextField();
		textDepto.setColumns(10);
		panel.add(textDepto, "4, 10, fill, default");

		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1, BorderLayout.SOUTH);

		btnSalvar = new JButton("Pagar");
		panel_1.add(btnSalvar);

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

	public static TarjetaCreditoFormView getInstance() {
		if (instance == null) {
			instance = new TarjetaCreditoFormView();
		}
		return instance;
	}

	public AltaClienteDTO getData() {
		AltaClienteDTO datosPersonales = new AltaClienteDTO();
		datosPersonales.setNombreCompleto(textNombre.getText());
		
		datosPersonales.setDni(this.textDni.getText());
		datosPersonales.setEmail(textEmail.getText());
		
		datosPersonales.setCalle(textCalle.getText());
		datosPersonales.setAltura(textAltura.getText());
		
		datosPersonales.setDpto(textDepto.getText());
		
		return datosPersonales;

	}

	public void setData(ClienteDTO cliente) {
		DatosPersonalesDTO datos = cliente.getDatosPersonalesDTO();
		textNombre.setText(datos.getNombreCompleto());
		
		textDni.setText(datos.getDni() + "");
		textEmail.setText(datos.getEmail());
		
		textCalle.setText(datos.getCalle());
		textAltura.setText(datos.getAltura() + "");
		
		textDepto.setText(datos.getDpto());
		

	//	this.btnSalvar.setVisible(false);
		
	}

	//public void setActionOnSave(ActionListener listener) {
	//	this.btnSalvar.addActionListener(listener);
//	}

	public void setActionOnRegistrar(ActionListener listener) {
		this.btnSalvar.addActionListener(listener);
		
	}

	public void clearData() {
		textNombre.setText("");
		textDni.setText("");
		textEmail.setText("");
		textCalle.setText("");
		textAltura.setText("");
		textDepto.setText("");

		this.btnSalvar.setVisible(true);
	}

	public void display() {
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}
}