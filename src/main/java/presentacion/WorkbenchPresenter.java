package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import business_logic.LoginController;
import business_logic.exceptions.ForbiddenException;
import presentacion.views.LoginView;
import presentacion.views.Presenter;
import presentacion.views.SupervisorControlView;
import presentacion.views.TecnicoControlView;
import presentacion.views.WorkbenchView;
import presentacion.views.utils.ErrorDialog;
import dto.SessionDTO;
import dto.UserCrendentialsDTO;

public class WorkbenchPresenter implements Presenter {

	private WorkbenchView workbenchView = WorkbenchView.getInstance();
			
	private LoginController loginController;
	
	public WorkbenchPresenter(LoginController loginController) {
		WorkbenchView.getInstance().setActionOnLogin(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginView.getInstance().onDisplay();
			}
		});
		LoginView.getInstance().setActionAceptar((a)->onLogin(a));
		this.loginController = loginController;
	}

	@Override
	public void onInit() {
		workbenchView.open();
	}
	
	private void onLogin(ActionEvent e) {
		UserCrendentialsDTO credentials = LoginView.getInstance().getData();
		if(credentials != null) {
			try {
				//SessionDTO session = loginController.logUser(credentials);
				SessionDTO session = new SessionDTO().setInitSession(new Date()).setIdUsuario(1).setNombreUsuario("johndoe").setRole("tecnico");
				workbenchView.setData(session.getInitSession().toString() + " " + session.getNombreUsuario());
				
				if(session.getRole().equals("tecnico")) TecnicoControlView.getInstance().display();
				if(session.getRole().equals("supervisor")) SupervisorControlView.getInstance().display();
				
				workbenchView.disableLoginButton();
				LoginView.getInstance().clearData();
				LoginView.getInstance().close();
			} catch (ForbiddenException e1) {
				new ErrorDialog().showMessages(e1.getMessage());
			}	
		}
	}
}
