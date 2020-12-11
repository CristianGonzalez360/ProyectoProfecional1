package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ConfiguradorBaseDeDatosController;
import business_logic.ConfiguradorSmtpController;
import dto.temporal.ConfigDatabaseDTO;
import dto.temporal.ConfigSmtpServerDTO;
import presentacion.views.admin.AdminControlView;
import presentacion.views.admin.FormConfiguracionSmtpView;
import presentacion.views.admin.FormConfigurationDBView;
import presentacion.views.admin.PanelConfiguracionGeneral;
import presentacion.views.utils.MessageDialog;

public class ConfiguracionGeneralPresenter {
	
	private PanelConfiguracionGeneral view = AdminControlView.getInstance().getPanelConfiguracion();
	
	private ConfiguradorBaseDeDatosController configDbController;
	
	private ConfiguradorSmtpController configSmtpController;
	
	public ConfiguracionGeneralPresenter(ConfiguradorBaseDeDatosController configDbController, ConfiguradorSmtpController configSmtpController) {
		this.configDbController = configDbController;
		this.configSmtpController = configSmtpController;
		view.setActionConfigurarDb((a)->onDisplayFormConfiguracionDB(a));
		view.setActionConfigurarSmtp((a)->onDisplayFormConfiguracionSmtp(a));
		view.setData(configDbController.read());
		view.setData(configSmtpController.read());		
		FormConfigurationDBView.getInstance().setActionSave((a)->onRegistrarConfiguracionDB(a));
		FormConfiguracionSmtpView.getInstance().setActionOk((a)->onRegistrarConfiguracioSmtp(a));
	}
	
	private void onRegistrarConfiguracioSmtp(ActionEvent a) {
		ConfigSmtpServerDTO dto = FormConfiguracionSmtpView.getInstance().getData();
		List<String> errors = dto.validate();
		if(errors.isEmpty()) {
			configSmtpController.save(dto);
			FormConfiguracionSmtpView.getInstance().close();
			view.setData(dto);
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	private void onRegistrarConfiguracionDB(ActionEvent a) {
		ConfigDatabaseDTO dto = FormConfigurationDBView.getInstance().getData();
		List<String> errors = dto.validate();
		if(!errors.isEmpty()) {
			configDbController.save(dto);
			FormConfigurationDBView.getInstance().close();
			view.setData(dto);
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	private void onDisplayFormConfiguracionDB(ActionEvent e) {
		FormConfigurationDBView.getInstance().clearData();
		FormConfigurationDBView.getInstance().open();
	}
	
	private void onDisplayFormConfiguracionSmtp(ActionEvent e) {
		FormConfiguracionSmtpView.getInstance().clearData();
		FormConfiguracionSmtpView.getInstance().open();
	}
}
