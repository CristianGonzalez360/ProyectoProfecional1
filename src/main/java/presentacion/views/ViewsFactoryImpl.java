package presentacion.views;

import presentacion.WorkbenchPresenter;
import business_logic.ControllersFactory;
import presentacion.PaisPresenter;
import presentacion.TurnosPresenter;

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
		new TurnosPresenter(controllers.makeTurnosController());
		return new WorkbenchPresenter(controllers.makeLoginController());
	}
}
