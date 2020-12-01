package presentacion;

import business_logic.ControllersFactory;
import presentacion.views.cajero.CajeroControlView;
import presentacion.views.gerente.GerenteControlView;
import presentacion.views.supervisor.SupervisorControlView;
import presentacion.views.tecnico.TecnicoControlView;
import presentacion.views.vendedor.VendedorControlView;

public class ViewsFactoryImpl extends ViewsFactory {

	private ControllersFactory controllers;

	public ViewsFactoryImpl(ControllersFactory factory) {
		controllers = factory;
		WorkbenchView.getInstance().addFrames(TecnicoControlView.getInstance());
		WorkbenchView.getInstance().addFrames(SupervisorControlView.getInstance());
		WorkbenchView.getInstance().addFrames(CajeroControlView.getInstance());
		WorkbenchView.getInstance().addFrames(GerenteControlView.getInstance());
		WorkbenchView.getInstance().addFrames(VendedorControlView.getInstance());

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
		new CobroCajeroPresenter(controllers.makeFacturasController());
		new VendedorControlPresenter(controllers.makeClientesController(), controllers.makeSucursalesController(), controllers.makeVentasVehiculosController());
		new PedidosPresenter(GerenteControlView.getInstance().getPedidosPanelView(), controllers.makePedidosController());
		new NuevosCarPresenter(GerenteControlView.getInstance().getAutosNuevosPanelView());
		new CarritoPresenter(controllers.makeRepuestosController(), controllers.makeClientesController(), controllers.makeFacturasController());
		new RegistroPedidoPresenter(controllers.makeVentasVehiculosController(),controllers.makeClientesController() , controllers.makePedidosController());

		return new WorkbenchPresenter(controllers.makeLoginController());
	}
}
