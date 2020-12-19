package presentacion.views.admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import dto.temporal.ConfigSmtpServerDTO;

public class FormConfiguracionSmtpView extends JDialog {

	private static final long serialVersionUID = -8728212924086785516L;

	private PanelConfiguracionSMTP panelConfiguracion;

	private JButton okButton;

	private JButton cancelButton;

	private static FormConfiguracionSmtpView instance;

	public static FormConfiguracionSmtpView getInstance() {
		if (instance == null)
			instance = new FormConfiguracionSmtpView();
		return instance;
	}

	private FormConfiguracionSmtpView() {
		setTitle("Parámetros de configuración SMTP");
		setResizable(false);
		setBounds(100, 100, 450, 248);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			panelConfiguracion = new PanelConfiguracionSMTP();
			getContentPane().add(panelConfiguracion, BorderLayout.CENTER);
		}
	}

	public void clearData() {
		this.panelConfiguracion.clearData();
	}

	public void setData(ConfigSmtpServerDTO dto) {
		this.panelConfiguracion.setData(dto);
	}

	public ConfigSmtpServerDTO getData() {
		return this.panelConfiguracion.getData();
	}

	public void setActionOk(ActionListener listener) {
		this.okButton.addActionListener(listener);
	}

	public void setActionCancel(ActionListener listener) {
		this.cancelButton.addActionListener(listener);
	}

	public void open() {
		setVisible(true);
	}

	public void close() {
		dispose();
	}
}
