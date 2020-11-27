
package presentacion;

import business_logic.ControllersFactory;
import presentacion.views.cajero.CajeroControlView;
import presentacion.views.gerente.GerenteControlView;
import presentacion.views.supervisor.ClientePresenter;
import presentacion.views.supervisor.RepuestosPresenter;
import presentacion.views.supervisor.SupervisorControlView;
import presentacion.views.tecnico.TecnicoControlView;


public class ViewsFactoryImpl extends ViewsFactory {

	private ControllersFactory controllers;

	public ViewsFactoryImpl(ControllersFactory factory) {
		controllers = factory;
		WorkbenchView.getInstance().addFrames(TecnicoControlView.getInstance());
		WorkbenchView.getInstance().addFrames(SupervisorControlView.getInstance());
		WorkbenchView.getInstance().addFrames(CajeroControlView.getInstance());
		WorkbenchView.getInstance().addFrames(GerenteControlView.getInstance());

	}

	@Override
	public Presenter makePresenter() {
		new TurnosPresenter(SupervisorControlView.getInstance().getTurnosView(),controllers.makeTurnosController());
		new ClientePresenter(SupervisorControlView.getInstance().getClientesView(),
				controllers.makeClientesController(), controllers.makeVehiculosController(),
				controllers.makeOrdenesDeTrabajoController());
		new OrdenDeTrabajoPresenter(controllers.makeOrdenesDeTrabajoController());
		new PresupuestosPresenter(controllers.makePresupuestosController(), controllers.makeRepuestosController(),
				controllers.makeOrdenesDeTrabajoController(), controllers.makeVehiculosController(),
				controllers.makeClientesController());
		new ConsultaDePresupuestoPresenter(controllers.makeVehiculosController(), controllers.makeClientesController(), controllers.makeOrdenesDeTrabajoController()
				,controllers.makePresupuestosController(), controllers.makeFacturasController());
		new RepuestosPresenter(controllers.makeRepuestosController());
		new GestionTrabajosPresenter(controllers.makePresupuestosController(), controllers.makeClientesController(), controllers.makeVehiculosController(), controllers.makeOrdenesDeTrabajoController(), controllers.makePresupuestosController());
//		new EntregaDeVehiculoPresenter(SupervisorControlView.getInstance().getEntregasView());
		new CarritoPresenter (controllers.makeRepuestosController(), controllers.makeClientesController(), controllers.makeFacturasController());
		new EntregaDeVehiculoPresenter(SupervisorControlView.getInstance().getEntregasView(), controllers.makeEntregasController());
		new CobroCajeroPresenter(controllers.makeFacturasController());
		return new WorkbenchPresenter(controllers.makeLoginController());
	}
}
