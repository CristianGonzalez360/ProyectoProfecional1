package presentacion;

import business_logic.AdminLoggerController;
import presentacion.views.admin.AdminControlView;

public class AdminViewFactory extends ViewsFactory {

	@Override
	public Presenter makePresenter() {
		WorkbenchView.getInstance().addFrames(AdminControlView.getInstance());
		new ConfiguracionGeneralPresenter();
		return new ConfigurationWorkbenchPresenter(new AdminLoggerController());
	}

}
