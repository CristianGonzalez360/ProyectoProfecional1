package presentacion;

import java.awt.event.ActionEvent;
import presentacion.views.LoginView;
import presentacion.views.Presenter;
import presentacion.views.WorkbenchView;
import dto.UserCrendentialsDTO;

public class WorkbenchPresenter implements Presenter {

	private WorkbenchView workbenchView = WorkbenchView.getInstance();
		
	private LoginView loginView = LoginView.getInstance();
	
	public WorkbenchPresenter() {
		WorkbenchView.getInstance().setActionOnLogin((e)->onDisplayLoginView(e));
	}

	@Override
	public void onInit() {
		workbenchView.open();
	}
	
	private void onDisplayLoginView(ActionEvent e) {
		loginView.clearData();
		loginView.onDisplay();
	}
	
	private void onLogin(ActionEvent e) {
		UserCrendentialsDTO dto = loginView.getData();
		if(dto.validate().isEmpty()) {
			
		} else {
			
		}
	}
}
