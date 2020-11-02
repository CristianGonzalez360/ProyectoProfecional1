package business_logic;

import repositories.DaosFactory;
import services.SessionServiceImpl;

public class ControllersFactoryImpl extends ControllersFactory {

	protected DaosFactory daos;

	private PaisController paisController;

	private LoginController loginController;
	
	private ClientesController clientesController;
	
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
	public ClientesController makeClientesController() {
		if(clientesController == null)
			clientesController = new ClientesControllerImp(daos.makeClienteDao());
		return clientesController;
	}
}
