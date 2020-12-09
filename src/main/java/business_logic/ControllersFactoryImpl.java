package business_logic;

import repositories.DaosFactory;
import services.SessionServiceImpl;

public class ControllersFactoryImpl extends ControllersFactory {

	protected DaosFactory daos;

	private LoginController loginController;

	private TurnosController turnosController;

	private ClientesController clientesController;

	private VehiculosConOrdenDeTrabajoController vehiculosController;

	private OrdenesTrabajoController ordenesDeTrabajoController;

	private PresupuestosController presupuestosController;

	private RepuestosController repuestosController;

	private FacturasController facturasController;
	
	private EntregaDeVehiculoController entregasController;
	
	private SucursalesController sucursalesController;
	
	private PedidosController pedidosController;
	
	private VentasVehiculosController ventasController;
	
	private ConfiguradorTerminalController configurador;
	
	private VehiculosController vehiculosController2;

	private UsuariosController usuariosController;

	
	public ControllersFactoryImpl(DaosFactory daos) {
		this.daos = daos;
	}

	@Override
	public TurnosController makeTurnosController() {
		if (turnosController == null)
			turnosController = new TurnosController(daos.makeTurnosDao());
		return turnosController;
	}

	@Override
	public ClientesController makeClientesController() {
		if (clientesController == null)
			clientesController = new ClientesController(daos.makeClienteDao(), daos.makeDatosPersonalesDao());
		return clientesController;
	}

	@Override
	public VehiculosConOrdenDeTrabajoController makeVehiculosConOrdenDeTrabajoController() {
		if (vehiculosController == null) {
			this.vehiculosController = new VehiculosConOrdenDeTrabajoController(daos.makeVehiculoConOrdeDeTrabajoDao(),
					daos.makeOrdenDeTrabajoDao(), daos.makeFichaTecnicaVehiculoDao());
		}
		return this.vehiculosController;
	}

	@Override
	public OrdenesTrabajoController makeOrdenesDeTrabajoController() {
		if (ordenesDeTrabajoController == null)
			ordenesDeTrabajoController = new OrdenesTrabajoController(daos.makeOrdenDeTrabajoDao(),
					SessionServiceImpl.getInstance(), daos.makeFacturasDao(), daos.makePresupuestoDao());
		return ordenesDeTrabajoController;
	}

	@Override
	public PresupuestosController makePresupuestosController() {
		if (presupuestosController == null) {
			presupuestosController = new PresupuestosController(daos.makePresupuestoDao(),
					daos.makeTrabajosPlanificadosDao(), daos.makeRepuestosPlanificadosDao(), daos.makeRepuestoDao());	
		}
		return presupuestosController;
	}

	@Override
	public RepuestosController makeRepuestosController() {
		if (repuestosController == null)
			repuestosController = new RepuestosController(daos.makeRepuestoDao());
		return repuestosController;
	}

	@Override
	public FacturasController makeFacturasController() {
		if(facturasController == null) 
			facturasController = new FacturasController(daos);
		return facturasController;
	}
	
	@Override
	public EntregaDeVehiculoController makeEntregasController() {
		if(entregasController == null)
			entregasController = new EntregaDeVehiculoController(daos.makeClienteDao(), daos.makeDatosPersonalesDao(), daos.makeOrdenDeTrabajoDao(), daos.makePresupuestoDao(), daos.makeVehiculoConOrdeDeTrabajoDao(), daos.makeFichaTecnicaVehiculoDao(), daos.makeFacturasDao());
		return entregasController;
	}

	@Override
	public SucursalesController makeSucursalesController() {
		if(sucursalesController == null) {
			sucursalesController = new SucursalesController(daos);
		}
		return sucursalesController;
	}
	
	@Override
	public PedidosController makePedidosController() {
		if(pedidosController == null) {
			pedidosController = new PedidosController(daos.makeDatosPersonalesDao(), daos.makeUsuariosDao(), daos.makeVentaVehiculoDao(), daos.makeVehiculoDao(), daos.makePedidoVehiculoDao());
		}
		return pedidosController;
	}

	@Override
	public VentasVehiculosController makeVentasVehiculosController() {
		if(ventasController == null) ventasController = new VentasVehiculosController(daos);
		return ventasController;
	}
	
	@Override
	public ConfiguradorTerminalController makeConfiguradorTerminalController() {
		if(configurador == null) configurador = new ConfiguradorTerminalController();
		return configurador;
	}
	
	@Override
	public LoginController makeLoginController() {
		if (loginController == null)
			loginController = new LoginController(daos.makeUsuariosDao(), SessionServiceImpl.getInstance(), configurador);
		return loginController;
	}

	@Override
	public VehiculosController makeVehiculosController() {
		if(vehiculosController2 == null) vehiculosController2 = new VehiculosController(daos);
		return vehiculosController2;
	}
	
	@Override
	public UsuariosController makeUsuariosController() {
		if(usuariosController == null) usuariosController = new UsuariosController(daos);
		return usuariosController;
	}
}
