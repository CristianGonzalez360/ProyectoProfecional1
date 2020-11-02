package business_logic;

import repositories.DaosFactory;
import services.SessionServiceImpl;

public class ControllersFactoryImpl extends ControllersFactory {

	protected DaosFactory daos;

	private PaisController paisController;

	private LoginController loginController;
	
	private TurnosController turnosController;
	
	public ControllersFactoryImpl(DaosFactory daos) {
		this.daos = daos;
	}

	@Override
	public PaisController makePaisController() {
		if(paisController == null)
			paisController = new PaisControllerImpl(daos.makePaisDao());
		return paisController;
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
}
