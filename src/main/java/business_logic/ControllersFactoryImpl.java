package business_logic;

import repositories.DaosFactory;
import services.SessionServiceImpl;

public class ControllersFactoryImpl extends ControllersFactory {

	protected DaosFactory daos;

	private LoginController loginController;

	private TurnosController turnosController;

	private ClientesController clientesController;

	private VehiculosController vehiculosController;

	private OrdenesTrabajoController ordenesDeTrabajoController;
	
	private PresupuestosController presupuestosController;
	
	private RepuestosController repuestosController;

	public ControllersFactoryImpl(DaosFactory daos) {
		this.daos = daos;
	}

	@Override
	public LoginController makeLoginController() {
		if (loginController == null)
			loginController = new LoginController(daos.makeUsuariosDao(), SessionServiceImpl.getInstance());
		return loginController;
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
	public VehiculosController makeVehiculosController() {
		if (vehiculosController == null) {
			this.vehiculosController = new VehiculosController(daos.makeVehiculoConOrdeDeTrabajoDao(),
					daos.makeFichaTecnicaVehiculoDao());
		}
		return this.vehiculosController;
	}

	@Override
	public OrdenesTrabajoController makeOrdenesDeTrabajoController() {
		if (ordenesDeTrabajoController == null)
			ordenesDeTrabajoController = new OrdenesTrabajoController(daos.makeOrdenDeTrabajoDao(),
					SessionServiceImpl.getInstance());
		return ordenesDeTrabajoController;
	}

	@Override
	public PresupuestosController makePresupuestosController() {
		if (presupuestosController == null)
			presupuestosController = new PresupuestosController();//TODO faltan los daos
		return presupuestosController;
	}

	@Override
	public RepuestosController makeRepuestosController() {
		if (repuestosController == null)
			repuestosController = new RepuestosController(daos.makeRepuestoDao());
		return repuestosController;
	}
}
