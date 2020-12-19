package presentacion.views.admin;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.temporal.ConfigSmtpServerDTO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelConfiguracionSMTP extends JPanel {

	private static final long serialVersionUID = -3308981510301633634L;

	private JTextField textEmail;

	private JTextField textPassword;

	public PanelConfiguracionSMTP() {
		setBorder(new TitledBorder(null, "Configuración de servirdor SMTP", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		JLabel lblNewLabel_2 = new JLabel("Email Remitente");
		add(lblNewLabel_2, "2, 2, left, default");

		textEmail = new JTextField();
		add(textEmail, "4, 2, fill, default");
		textEmail.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Password Remitente");
		add(lblNewLabel_3, "2, 4, left, default");

		textPassword = new JTextField();
		add(textPassword, "4, 4, fill, default");
		textPassword.setColumns(10);

	}

	public void disableAllInputs() {
		this.textEmail.setEditable(false);
		this.textPassword.setEditable(false);
	}

	public void clearData() {
		this.textEmail.setText("");
		this.textPassword.setText("");
	}

	public void setData(ConfigSmtpServerDTO dto) {
		this.textEmail.setText(dto.getCorreoRemitente());
		this.textPassword.setText(dto.getPasswordRemitente());
	}

	public ConfigSmtpServerDTO getData() {
		ConfigSmtpServerDTO dto = new ConfigSmtpServerDTO();
		dto.setCorreoRemitente(this.textEmail.getText());
		dto.setPasswordRemitente(this.textPassword.getText());
		return dto;
	}
}
