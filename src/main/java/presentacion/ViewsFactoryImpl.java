package presentacion;

import business_logic.ControllersFactory;
import presentacion.views.admin.AdminControlView;
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
		WorkbenchView.getInstance().addFrames(AdminControlView.getInstance());
	}

	@Override
	public Presenter makePresenter() {
		new TurnosPresenter(SupervisorControlView.getInstance().getTurnosView(), controllers.makeTurnosController(),
				controllers.makeConfiguradorCapacidadTurnosController());
		new ClientePresenter(SupervisorControlView.getInstance().getClientesView(),
				controllers.makeClientesController(), controllers.makeVehiculosConOrdenDeTrabajoController(),
				controllers.makeOrdenesDeTrabajoController());
		new OrdenDeTrabajoPresenter(controllers.makeOrdenesDeTrabajoController());
		new PresupuestosPresenter(controllers.makePresupuestosController(), controllers.makeRepuestosController(),
				controllers.makeOrdenesDeTrabajoController(), controllers.makeVehiculosConOrdenDeTrabajoController(),
				controllers.makeClientesController());
		new ConsultaDePresupuestoPresenter(controllers.makeVehiculosConOrdenDeTrabajoController(),
				controllers.makeClientesController(), controllers.makeOrdenesDeTrabajoController(),
				controllers.makePresupuestosController(), controllers.makeFacturasController());
		new CobroCajeroPresenter(controllers.makeFacturasController());
		new HistorialVentasPresenter(controllers.makeVentasVehiculosController());
		new HistorialVentasGerentePresenter(controllers.makeVentasVehiculosController());
		new VendedorControlPresenter(controllers.makeClientesController(), controllers.makeVentasVehiculosController());
		new PedidosPresenter(GerenteControlView.getInstance().getPedidosPanelView(),
				controllers.makePedidosController());
		new NuevosCarPresenter(GerenteControlView.getInstance().getAutosNuevosPanelView(),
				controllers.makeVentasVehiculosController());
		new CarritoPresenter(controllers.makeRepuestosController(), controllers.makeClientesController(),
				controllers.makeFacturasController());
		new RegistroPedidoPresenter(controllers.makeVentasVehiculosController(), controllers.makeClientesController(),
				controllers.makePedidosController());
		new EntregaVehiculosVentaPresenter(controllers.makeVentasVehiculosController(),
				controllers.makeClientesController());

		new RepuestosPresenter(controllers.makeRepuestosController());
		new EntregaDeVehiculoPresenter(SupervisorControlView.getInstance().getEntregasView(),
				controllers.makeEntregasController());
		new GestionTrabajosPresenter(controllers.makePresupuestosController(), controllers.makeClientesController(),
				controllers.makeVehiculosConOrdenDeTrabajoController(), controllers.makeOrdenesDeTrabajoController(),
				controllers.makePresupuestosController());
		new VehiculosUsadosPresenter(controllers.makeVehiculosController());
		makeAdminControllers();
		return new WorkbenchPresenter(controllers.makeLoginController());
	}

	private void makeAdminControllers() {
		new GestionUsuariosPresenter(controllers.makeUsuariosController());
		new SucursalesControlPresenter(controllers.makeSucursalesController(),
				controllers.makeConfiguradorTerminalController());
		new ConfiguracionGeneralPresenter();
	}
}
