package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.TurnoDTO;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class TurnoFormView extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private static TurnoFormView instance;
	private JTextField textNombre;
	private JTextField textDNI;
	private JTextField textTelefono;
	private JTextField textEmail;
	private JDateChooser fechaTurno;

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
				JButton btnConfirm = new JButton("OK");
				btnConfirm.setActionCommand("OK");
				panelButton.add(btnConfirm);
				getRootPane().setDefaultButton(btnConfirm);
			}
			{
				JButton btnCancel = new JButton("Cancelar");
				btnCancel.setActionCommand("Cancelar");
				panelButton.add(btnCancel);
			}
		}
		setModal(true);
	}

	public void display() {
		this.setVisible(true);
	}

	public TurnoDTO getData() {
		return null;
	}

	public void clearData() {

	}

	public void setActionSave(ActionListener listener) {

	}

	public void setActionCancel(ActionListener listener) {

	}
}
