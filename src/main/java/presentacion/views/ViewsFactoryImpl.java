package presentacion.views;

import presentacion.WorkbenchPresenter;
import business_logic.ControllersFactory;
import presentacion.ClientePresenter;
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
		new TurnosPresenter(controllers.makeTurnosController());
		new ClientePresenter(SupervisorControlView.getInstance().getPanelClientesView(), controllers.makeClientesController(), controllers.makeVehiculosController(), controllers.makeOrdenesDeTrabajoController());
		return new WorkbenchPresenter(controllers.makeLoginController());
	}
}
