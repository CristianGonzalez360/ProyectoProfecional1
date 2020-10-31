package business_logic;

import repositories.DaosFactory;

public class ControllersFactoryImpl extends ControllersFactory {

	protected DaosFactory daos;

	private PaisController paisController;

	public ControllersFactoryImpl(DaosFactory daos) {
		this.daos = daos;
	}

	@Override
	public PaisController makePaisController() {
		if (paisController == null)
			paisController = new PaisControllerImpl(daos.makePaisDao());
		return paisController;
	}
}
