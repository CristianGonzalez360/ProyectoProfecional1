package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.UserCrendentialsDTO;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginView extends JDialog {

	private static final long serialVersionUID = 8786589718227732342L;

	private final JPanel contentPanel = new JPanel();

	private static LoginView instance;
	
	private JTextField textFieldUsuario;
	
	private JTextField textFieldPassword;

	private JButton okButton;

	private JButton cancelButton;

	private LoginView() {
		setTitle("Login form");
		setBounds(100, 100, 450, 206);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));
		JLabel lblNewLabel = new JLabel("Usuario");
		contentPanel.add(lblNewLabel, "2, 2, right, default");

		textFieldUsuario = new JTextField("");
		contentPanel.add(textFieldUsuario, "4, 2, fill, default");
		textFieldUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		contentPanel.add(lblNewLabel_1, "2, 4, right, default");

		textFieldPassword = new JTextField("");
		contentPanel.add(textFieldPassword, "4, 4, fill, default");
		textFieldPassword.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	public static LoginView getInstance() {
		if (instance == null)
			instance = new LoginView();
		return instance;
	}

	public void display() {
		setVisible(true);
	}

	public void clearData() {
		this.textFieldUsuario.setText("");
		this.textFieldPassword.setText("");
	}

	public UserCrendentialsDTO getData() {
		return new UserCrendentialsDTO(this.textFieldUsuario.getText(), this.textFieldPassword.getText());
	}

	public void setActionAceptar(ActionListener listener) {
		this.okButton.addActionListener(listener);
	}

	public void close() {
		this.setVisible(false);
	}
}
