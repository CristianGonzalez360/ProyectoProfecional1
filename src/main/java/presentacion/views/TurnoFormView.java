package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.TurnoDTO;

@SuppressWarnings("serial")
public class TurnoFormView extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private static TurnoFormView instance;

	public static TurnoFormView getInstance() {
		if (instance == null)
			instance = new TurnoFormView();
		return instance;
	}

	private TurnoFormView() {
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
