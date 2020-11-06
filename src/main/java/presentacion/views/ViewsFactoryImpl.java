package presentacion.views;

import business_logic.ControllersFactory;
import presentacion.ClientePresenter;
import presentacion.ConsultaDePresupuestoPresenter;
import presentacion.OrdenDeTrabajoPresenter;
import presentacion.PresupuestosPresenter;
import presentacion.TurnosPresenter;
import presentacion.WorkbenchPresenter;

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
		new OrdenDeTrabajoPresenter(controllers.makeOrdenesDeTrabajoController());
		new PresupuestosPresenter(controllers.makePresupuestosController(), controllers.makeRepuestosController(),
				controllers.makeOrdenesDeTrabajoController(), controllers.makeVehiculosController(),
				controllers.makeClientesController());
		new ConsultaDePresupuestoPresenter(controllers.makeVehiculosController(), controllers.makeClientesController(), controllers.makeOrdenesDeTrabajoController()
				,controllers.makePresupuestosController());
		return new WorkbenchPresenter(controllers.makeLoginController());
	}
}
