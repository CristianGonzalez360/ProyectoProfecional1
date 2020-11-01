package presentacion.views;

import presentacion.WorkbenchPresenter;
import business_logic.ControllersFactory;
import presentacion.PaisPresenter;

public class ViewsFactoryImpl extends ViewsFactory {

	private ControllersFactory controllers;

	public ViewsFactoryImpl(ControllersFactory factory) {
		controllers = factory;
		WorkbenchView.getInstance().addFrames(TecnicoControlView.getInstance());
		WorkbenchView.getInstance().addFrames(SupervisorControlView.getInstance());
	}

	@Override
	public Presenter makePresenter() {
		new PaisPresenter(controllers.makePaisController());
		return new WorkbenchPresenter(controllers.makeLoginController());
	}
}
