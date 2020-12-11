package presentacion.views.admin;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.temporal.ConfigDatabaseDTO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelConfiguracionDB extends JPanel {

	private static final long serialVersionUID = 8894140251628157320L;
	
	private JTextField textUser;
	
	private JTextField textPassword;
	
	private JTextField textIP;
	
	private JTextField textPort;

	public PanelConfiguracionDB() {
		setBorder(new TitledBorder(null, "Parametros base de datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		JLabel lblNewLabel = new JLabel("User");
		add(lblNewLabel, "2, 2, left, default");
		
		textUser = new JTextField();
		add(textUser, "4, 2, fill, default");
		textUser.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		add(lblNewLabel_1, "2, 4, left, default");
		
		textPassword = new JTextField();
		add(textPassword, "4, 4, fill, default");
		textPassword.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("IP");
		add(lblNewLabel_2, "2, 6, left, default");
		
		textIP = new JTextField();
		add(textIP, "4, 6, fill, default");
		textIP.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Port");
		add(lblNewLabel_3, "2, 8, left, default");
		
		textPort = new JTextField();
		add(textPort, "4, 8, fill, default");
		textPort.setColumns(10);
	}

	public void clearData() {
		textUser.setText("");
		textPassword.setText("");
		textPort.setText("");
		textIP.setText("");
	}
	
	public void setData(ConfigDatabaseDTO dto) {
		textUser.setText(dto.getUser());
		textPassword.setText(dto.getPassword());
		textPort.setText(dto.getPort());
		textIP.setText(dto.getIp());
	}
	
	public ConfigDatabaseDTO getData() {
		ConfigDatabaseDTO dto = new ConfigDatabaseDTO();
		dto.setUser(textUser.getText());
		dto.setPassword(textPassword.getText());
		dto.setIp(textIP.getText());
		dto.setPort(textPort.getText());
		return dto;
	}
}
