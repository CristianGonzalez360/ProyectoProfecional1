package presentacion.views.admin;

import javax.swing.JPanel;

import dto.temporal.ConfigDatabaseDTO;
import dto.temporal.ConfigSmtpServerDTO;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class PanelConfiguracionGeneral extends JPanel {

	private static final long serialVersionUID = 7616885639212161352L;

	private PanelConfiguracionDB panelConfiguracion;

	private PanelConfiguracionSMTP panelSmtp;

	private JButton btnConfigurarDb;

	private JButton btnNewButton_1;

	public PanelConfiguracionGeneral() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		panelConfiguracion = new PanelConfiguracionDB();
		add(panelConfiguracion);

		panelSmtp = new PanelConfiguracionSMTP();
		add(panelSmtp);

		JPanel panelOpciones = new JPanel();
		add(panelOpciones);

		btnConfigurarDb = new JButton("Configurar DB");
		panelOpciones.add(btnConfigurarDb);

		btnNewButton_1 = new JButton("Configurar SMTP");
		panelOpciones.add(btnNewButton_1);

		this.panelConfiguracion.disableAllInputs();
		this.panelSmtp.disableAllInputs();
	}

	public void setActionConfigurarDb(ActionListener listener) {
		this.btnConfigurarDb.addActionListener(listener);
	}

	public void setActionConfigurarSmtp(ActionListener listener) {
		this.btnNewButton_1.addActionListener(listener);
	}

	public void setData(ConfigDatabaseDTO dto) {
		this.panelConfiguracion.setData(dto);
	}

	public void setData(ConfigSmtpServerDTO dto) {
		this.panelSmtp.setData(dto);
	}
}
