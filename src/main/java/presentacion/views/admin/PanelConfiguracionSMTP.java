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

	private JTextField textHost;
	
	private JTextField textPort;
	
	private JTextField textEmail;
	
	private JTextField textPassword;

	public PanelConfiguracionSMTP() {
		setBorder(new TitledBorder(null, "Configuracion servirdor SMTP", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Host");
		add(lblNewLabel, "2, 2, left, default");
		
		textHost = new JTextField();
		add(textHost, "4, 2, fill, default");
		textHost.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Port");
		add(lblNewLabel_1, "2, 4, left, default");
		
		textPort = new JTextField();
		add(textPort, "4, 4, fill, default");
		textPort.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email remitente");
		add(lblNewLabel_2, "2, 6, left, default");
		
		textEmail = new JTextField();
		add(textEmail, "4, 6, fill, default");
		textEmail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Password remitente");
		add(lblNewLabel_3, "2, 8, left, default");
		
		textPassword = new JTextField();
		add(textPassword, "4, 8, fill, default");
		textPassword.setColumns(10);

	}

	public void disableAllInputs() {
		this.textEmail.setEditable(false);
		this.textHost.setEditable(false);
		this.textPassword.setEditable(false);
		this.textPort.setEditable(false);
	}
	
	public void clearData() {
		this.textEmail.setText("");
		this.textHost.setText("");
		this.textPassword.setText("");
		this.textPort.setText("");
	}
	
	public void setData(ConfigSmtpServerDTO dto) {
		this.textEmail.setText(dto.getCorreoRemitente());
		this.textHost.setText(dto.getHost());
		this.textPassword.setText(dto.getPasswordRemitente());
		this.textPort.setText(dto.getPort());
	}
	
	public ConfigSmtpServerDTO getData() {
		ConfigSmtpServerDTO dto = new ConfigSmtpServerDTO();
		dto.setCorreoRemitente(this.textEmail.getText());
		dto.setHost(this.textHost.getText());
		dto.setPort(this.textPort.getText());
		dto.setPasswordRemitente(this.textPassword.getText());
		return dto;
	}
}
