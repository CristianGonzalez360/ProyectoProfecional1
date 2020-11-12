package presentacion;

import java.awt.event.ActionEvent;
import business_logic.LoginController;
import business_logic.exceptions.ForbiddenException;
import presentacion.views.CajeroControlView;
import presentacion.views.LoginView;
import presentacion.views.Presenter;
import presentacion.views.SupervisorControlView;
import presentacion.views.TecnicoControlView;
import presentacion.views.WorkbenchView;
import presentacion.views.utils.ConfirmationDialog;
import presentacion.views.utils.ErrorDialog;
import dto.SessionDTO;
import dto.UserCrendentialsDTO;

public class WorkbenchPresenter implements Presenter {

	private WorkbenchView workbenchView = WorkbenchView.getInstance();

	private LoginController loginController;

	public WorkbenchPresenter(LoginController loginController) {
		WorkbenchView.getInstance().setActionOnLogin((a) -> onDisplayLoginView(a));
		WorkbenchView.getInstance().setActionOnLogout((a) -> onLogout(a));
		LoginView.getInstance().setActionAceptar((a) -> onLogin(a));
		workbenchView.setActionOnSalir((a) -> onSalir(a));
		this.loginController = loginController;
	}

	@Override
	public void onInit() {
		workbenchView.open();
	}

	private void onDisplayLoginView(ActionEvent e) {
		LoginView.getInstance().display();
	}

	private void onLogin(ActionEvent e) {
		UserCrendentialsDTO credentials = LoginView.getInstance().getData();
		if (credentials != null) {
			try {
				SessionDTO session = loginController.logUser(credentials);
				workbenchView.setData(session.getInitSession().toString() + " - " + session.getNombreUsuario());
				if (session.getRole().equals("tecnico")) {
					TecnicoControlView.getInstance().clearData();
					TecnicoControlView.getInstance().display();
				}
				if (session.getRole().equals("supervisor")) {
					SupervisorControlView.getInstance().clearTurnos();
					SupervisorControlView.getInstance().display();
				}
				if (session.getRole().equals("cajero")) {
					CajeroControlView.getInstance().clearData();
					CajeroControlView.getInstance().display();
				}
				workbenchView.disableLoginButton();
				LoginView.getInstance().clearData();
				LoginView.getInstance().close();
			} catch (ForbiddenException e1) {
				new ErrorDialog().showMessages(e1.getMessage());
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
		TecnicoControlView.getInstance().clearData();
		TecnicoControlView.getInstance().close();
		SupervisorControlView.getInstance().clearTurnos();
		SupervisorControlView.getInstance().close();
		CajeroControlView.getInstance().clearData();
		CajeroControlView.getInstance().close();
		workbenchView.enableLoginButton();
		workbenchView.clearData();
	}
}