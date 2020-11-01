package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.UserCrendentialsDTO;

public class LoginView extends JDialog {

	private static final long serialVersionUID = 8786589718227732342L;
	
	private final JPanel contentPanel = new JPanel();

	private static LoginView instance;
	
	private LoginView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static LoginView getInstance() {
		if(instance == null) instance = new LoginView();
		return instance;
	}
	
	public void onDisplay() {
		setVisible(true);
	}

	public void clearData() {
		
	}

	public UserCrendentialsDTO getData() {
		// TODO Auto-generated method stub
		return null;
	}
}
