package business_logic;

import repositories.DaosFactory;
import services.SessionServiceImpl;

public class ControllersFactoryImpl extends ControllersFactory {

	protected DaosFactory daos;

	private LoginController loginController;
	
	private TurnosController turnosController;

	private ClientesController clientesController;

	private VehiculosController vehiculosController;
	
	public ControllersFactoryImpl(DaosFactory daos) {
		this.daos = daos;
	}

	@Override 
	public LoginController makeLoginController() {
		if(loginController == null)
			loginController = new LoginController(daos.makeUsuariosDao(), SessionServiceImpl.getInstance());
		return loginController;
	}

	@Override
	public TurnosController makeTurnosController() {
		if(turnosController == null)
			turnosController = new TurnosController(daos.makeTurnosDao());
		return turnosController;
	}
	
	@Override
	public ClientesController makeClientesController() {
		if(clientesController == null)
			clientesController = new ClientesController(daos.makeClienteDao());
		return clientesController;
	}

	@Override
	public VehiculosController makeVehiculosController() {
		if(vehiculosController == null) {
			this.vehiculosController = new VehiculosController(daos.makeVehiculoConOrdeDeTrabajoDao(), daos.makeFichaTecnicaVehiculoDao());
		}
		return this.vehiculosController;
	}
}
