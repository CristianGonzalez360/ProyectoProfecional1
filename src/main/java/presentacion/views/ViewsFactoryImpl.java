package presentacion.views;

import presentacion.WorkbenchPresenter;
import business_logic.ControllersFactory;
import business_logic.PresupuestosController;
import presentacion.ClientePresenter;
import presentacion.PresupuestosPresenter;
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
		new ClientePresenter(SupervisorControlView.getInstance().getPanelClientesView(),
				controllers.makeClientesController(), controllers.makeVehiculosController(),
				controllers.makeOrdenesDeTrabajoController());
		new PresupuestosPresenter(controllers.makePresupuestosController());
		return new WorkbenchPresenter(controllers.makeLoginController());
	}
}
