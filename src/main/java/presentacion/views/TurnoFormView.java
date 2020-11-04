package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

import dto.TurnoDTO;

@SuppressWarnings("serial")
public class TurnoFormView extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private static TurnoFormView instance;
	private JTextField textNombre;
	private JTextField textDNI;
	private JTextField textTelefono;
	private JTextField textEmail;
	private JDateChooser fechaTurno;

	private JButton btnConfirmar;
	private JButton btnCancelar;

	public static TurnoFormView getInstance() {
		if (instance == null)
			instance = new TurnoFormView();
		return instance;
	}

	private TurnoFormView() {
		setBounds(100, 100, 350, 320);
		setTitle("Formulario de alta de turno");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panelForm = new JPanel();
		panelForm.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelForm.setBounds(10, 11, 314, 226);
		contentPanel.add(panelForm);
		panelForm.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNombreApellido = new JLabel("Nombre y Apellido");
		panelForm.add(lblNombreApellido, "4, 4");

		textNombre = new JTextField();
		panelForm.add(textNombre, "10, 4, fill, default");
		textNombre.setColumns(10);

		JLabel lblDNI = new JLabel("DNI");
		panelForm.add(lblDNI, "4, 6");

		textDNI = new JTextField();
		panelForm.add(textDNI, "10, 6, fill, default");
		textDNI.setColumns(10);

		JLabel lblTelefono = new JLabel("Teléfono");
		panelForm.add(lblTelefono, "4, 8");

		textTelefono = new JTextField();
		panelForm.add(textTelefono, "10, 8, fill, default");
		textTelefono.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		panelForm.add(lblEmail, "4, 10");

		textEmail = new JTextField();
		panelForm.add(textEmail, "10, 10, fill, default");
		textEmail.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha del turno");
		panelForm.add(lblFecha, "4, 12");

		fechaTurno = new JDateChooser();
		panelForm.add(fechaTurno, "10, 12, fill, default");

		{
			JPanel panelButton = new JPanel();
			panelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(panelButton, BorderLayout.SOUTH);
			{
				btnConfirmar = new JButton("OK");
				btnConfirmar.setActionCommand("OK");
				panelButton.add(btnConfirmar);
				getRootPane().setDefaultButton(btnConfirmar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancelar");
				panelButton.add(btnCancelar);
			}
		}
		setModal(true);
		setVisible(false);
	}

	public void display() {
		this.setVisible(true);
	}

	public TurnoDTO getData() {
		TurnoDTO nuevoTurno = new TurnoDTO();
		
		nuevoTurno.setNombreCliente(getTextNombre());
		nuevoTurno.setDniCliente(getDNI());
		nuevoTurno.setFechaProgramada(getFechaTurno());
		nuevoTurno.setFechaAlta(fechaDeHoy());

		return nuevoTurno;
	}

	public Date fechaDeHoy() {
		return new Date();
	}

	public void clearData() {
		textNombre.setText(null);
		textDNI.setText(null);
		textTelefono.setText(null);
		textEmail.setText(null);

		fechaTurno.setDate(null);

		this.btnConfirmar.setVisible(true);
		this.btnCancelar.setVisible(true);
	}

	public void setActionSave(ActionListener listener) {
		this.btnConfirmar.addActionListener(listener);
	}

	public void setActionCancel(ActionListener listener) {
		this.btnCancelar.addActionListener(listener);
	}

	public void cerrar() {
		setVisible(false);
	}

	public String getTextNombre() {
		return textNombre.getText();
	}
	
	public Integer getDNI() {
		Integer dniCliente;
		try {
			dniCliente = Integer.parseInt(textDNI.getText());
		} catch (NumberFormatException e) {
			dniCliente = -2;
		}

		return dniCliente;
	}

	public String getTextTelefono() {
		return textTelefono.getText();
	}

	public String getTextEmail() {
		return textEmail.getText();
	}

	public Date getFechaTurno() {
		return fechaTurno.getDate();
	}

}
