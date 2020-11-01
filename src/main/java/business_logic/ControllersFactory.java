package business_logic;

public abstract class ControllersFactory {

	private static ControllersFactory concreteFactory;

	public static void setFactory(ControllersFactory factory) {
		if (factory == null)
			throw new IllegalArgumentException("Factory is null");
		concreteFactory = factory;
	}

	public static ControllersFactory getFactory() {
		assert concreteFactory != null;
		return concreteFactory;
	}

	public abstract PaisController makePaisController();

	public abstract LoginController makeLoginController();
}
