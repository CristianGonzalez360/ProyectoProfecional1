package presentacion;

import java.awt.event.ActionEvent;
import business_logic.LoginController;
import business_logic.exceptions.ForbiddenException;
import presentacion.views.admin.AdminControlView;
import presentacion.views.cajero.CajeroControlView;
import presentacion.views.gerente.GerenteControlView;
import presentacion.views.supervisor.SupervisorControlView;
import presentacion.views.tecnico.TecnicoControlView;
import presentacion.views.utils.ConfirmationDialog;
import presentacion.views.utils.MessageDialog;
import presentacion.views.vendedor.VendedorControlView;
import dto.UserCrendentialsDTO;
import dto.temporal.SessionDTO;

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
				if (session.getRole().equals("gerente")) {
					GerenteControlView.getInstance().clearData();
					GerenteControlView.getInstance().display();
				}
				if (session.getRole().equals("vendedor")) {
					VendedorControlView.getInstance().clearData();
					VendedorControlView.getInstance().display();
				}
				if (session.getRole().equals("admin")) {
					AdminControlView.getInstance().clearData();
					AdminControlView.getInstance().display();
				}
				
				workbenchView.disableLoginButton();
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
		TecnicoControlView.getInstance().clearData();
		TecnicoControlView.getInstance().close();
		SupervisorControlView.getInstance().clearTurnos();
		SupervisorControlView.getInstance().close();
		CajeroControlView.getInstance().clearData();
		CajeroControlView.getInstance().close();
		GerenteControlView.getInstance().clearData();
		GerenteControlView.getInstance().close();
		VendedorControlView.getInstance().clearData();
		VendedorControlView.getInstance().close();
		AdminControlView.getInstance().clearData();
		AdminControlView.getInstance().close();
		workbenchView.enableLoginButton();
		workbenchView.clearData();
	}
}