package presentacion.views.admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import dto.temporal.ConfigDatabaseDTO;

import javax.swing.JCheckBox;

public class FormConfigurationDBView extends JDialog {

	private static final long serialVersionUID = -4088765082266597046L;
	
	private PanelConfiguracionDB panelConfiguracion;
	
	private JButton okButton;
	
	private JCheckBox chckbxIsLocalhost;
	
	private static FormConfigurationDBView instance;
	
	private JButton btnCancel;
	
	public static FormConfigurationDBView getInstance() {
		if(instance == null) instance = new FormConfigurationDBView();
		return instance;
	}	
	
	private FormConfigurationDBView() {
		setTitle("Parametros de configuración db");
		setResizable(false);
		setBounds(100, 100, 362, 264);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			chckbxIsLocalhost = new JCheckBox("Is localhost");
			buttonPane.add(chckbxIsLocalhost);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				btnCancel = new JButton("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		{
			panelConfiguracion = new PanelConfiguracionDB();
			getContentPane().add(panelConfiguracion, BorderLayout.CENTER);
		}
		setModal(true);
	}
	
	public void open() {
		setVisible(true);
	}
	
	public void close() {
		this.dispose();
	}			
		
	public void setActionSave(ActionListener listener) {
		assert listener != null;
		okButton.addActionListener(listener);
	}
		
	public void setActionLocalhost(ActionListener listener) {
		assert listener != null;
		this.chckbxIsLocalhost.addActionListener(listener);
	}

	public void clearData() {
		this.panelConfiguracion.clearData();
	}

	public ConfigDatabaseDTO getData() {
		return panelConfiguracion.getData();
	}

	public void setActionCancel(ActionListener listener) {
		this.btnCancel.addActionListener(listener);
	}

	public boolean isLocalhost() {
		return this.chckbxIsLocalhost.isSelected();
	}

	public void disableInputIP(String txt) {
		this.panelConfiguracion.disableInputIP(txt);
	}

	public void enableInputIP(String txt) {
		this.panelConfiguracion.enableInputIP(txt);
	}
}
