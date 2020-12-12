package presentacion;

import java.awt.event.ActionEvent;

import business_logic.AdminLoggerController;
import business_logic.exceptions.ForbiddenException;
import dto.UserCrendentialsDTO;
import dto.temporal.SessionDTO;
import presentacion.views.admin.AdminControlView;
import presentacion.views.utils.ConfirmationDialog;
import presentacion.views.utils.MessageDialog;

public class ConfigurationWorkbenchPresenter implements Presenter {

	private AdminLoggerController loginController;
	
	public ConfigurationWorkbenchPresenter(AdminLoggerController controller) {
		WorkbenchView.getInstance().setActionOnLogin((a) -> onDisplayLoginView(a));
		WorkbenchView.getInstance().setActionOnLogout((a) -> onLogout(a));
		LoginView.getInstance().setActionAceptar((a) -> onLogin(a));
		WorkbenchView.getInstance().setActionOnSalir((a) -> onSalir(a));
		this.loginController = controller;
	}

	@Override
	public void onInit() {
		WorkbenchView.getInstance().open();
	}

	private void onDisplayLoginView(ActionEvent e) {
		LoginView.getInstance().display();
	}

	private void onLogin(ActionEvent e) {
		UserCrendentialsDTO credentials = LoginView.getInstance().getData();
		if (credentials != null) {
			try {
				SessionDTO session = loginController.logUser(credentials);
				if(session != null) {
					String datosDeSession = String.format("%s - Usuario: %s", session.getFecha() ,session.getNombreUsuario());
					WorkbenchView.getInstance().setData(datosDeSession);
					if (session.getRole().equals("admin")) {
						AdminControlView.getInstance().clearData();
						AdminControlView.getInstance().display();
					}
				}
				WorkbenchView.getInstance().disableLoginButton();
				LoginView.getInstance().clearData();
				LoginView.getInstance().close();
			} catch (ForbiddenException e1) {
				new MessageDialog().showMessages(e1.getMessage());
			}
		}
	}

	private void onSalir(ActionEvent e) {
		int confirm = new ConfirmationDialog("¿Estás seguro que quieres salir?").open();
		if (confirm == 0) {
			System.exit(0);
		}
	}

	private void onLogout(ActionEvent e) {
		loginController.logout();
		AdminControlView.getInstance().clearData();
		AdminControlView.getInstance().close();
		WorkbenchView.getInstance().enableLoginButton();
		WorkbenchView.getInstance().clearData();
	}

}
