package presentacion;

import java.awt.event.ActionEvent;
import java.io.IOException;
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
import repositories.DaosFactory;
import services.DatabaseSeederServiceImpl;

public class ConfiguracionGeneralPresenter {

	private PanelConfiguracionGeneral view = AdminControlView.getInstance().getPanelConfiguracion();

	private ConfiguradorBaseDeDatosController configDbController = new ConfiguradorBaseDeDatosController();

	private ConfiguradorSmtpController configSmtpController = new ConfiguradorSmtpController();

	public ConfiguracionGeneralPresenter() {
		view.setActionConfigurarDb((a) -> onDisplayFormConfiguracionDB(a));
		view.setActionConfigurarSmtp((a) -> onDisplayFormConfiguracionSmtp(a));
		try {
			view.setData(configDbController.read());
		} catch (IOException e1) {
			new MessageDialog().showMessages("Configure la conexion con el motor de la base de datos");
		}
		try {
			view.setData(configSmtpController.read());
		} catch (IOException e) {
			new MessageDialog().showMessages("Configure el servidor SMTP");
		}
		view.setActionSeedDB((a)->onSeedDB(a));
		FormConfigurationDBView.getInstance().setActionSave((a) -> onRegistrarConfiguracionDB(a));
		FormConfigurationDBView.getInstance().setActionCancel((a) -> {
			FormConfigurationDBView.getInstance().close();
		});
		FormConfigurationDBView.getInstance().setActionLocalhost((a) -> onSelectCheckboxLocalhost(a));
		FormConfiguracionSmtpView.getInstance().setActionOk((a) -> onRegistrarConfiguracioSmtp(a));
		FormConfiguracionSmtpView.getInstance().setActionCancel((a) -> {
			FormConfiguracionSmtpView.getInstance().close();
		});
	}

	private void onSeedDB(ActionEvent a) {
		new DatabaseSeederServiceImpl(DaosFactory.getFactory()).seedDatabase();
	}

	private void onSelectCheckboxLocalhost(ActionEvent a) {
		boolean isLocalhost = FormConfigurationDBView.getInstance().isLocalhost();
		if (isLocalhost) {
			FormConfigurationDBView.getInstance().disableInputIP("localhost");
		} else {
			FormConfigurationDBView.getInstance().enableInputIP("");
		}
	}

	private void onRegistrarConfiguracioSmtp(ActionEvent a) {
		ConfigSmtpServerDTO dto = FormConfiguracionSmtpView.getInstance().getData();
		List<String> errors = dto.validate();
		if (errors.isEmpty()) {
			try {
				configSmtpController.save(dto);
			} catch (IOException e) {
				new MessageDialog().showMessages("Configure el servidor SMTP");
			}
			FormConfiguracionSmtpView.getInstance().close();
			view.setData(dto);
		} else {
			new MessageDialog().showMessages(errors);
		}
	}

	private void onRegistrarConfiguracionDB(ActionEvent a) {
		ConfigDatabaseDTO dto = FormConfigurationDBView.getInstance().getData();
		List<String> errors = dto.validate();
		if (errors.isEmpty()) {
			try {
				configDbController.save(dto);
			} catch (IOException e) {
				new MessageDialog().showMessages("Configure la conexion con el motor de la base de datos");
			}
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
