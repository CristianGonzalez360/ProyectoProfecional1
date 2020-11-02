package repositories;

import dto.CuentaDTO;

public abstract class DaosFactory {

	private static DaosFactory concreteFactory;

	public static void setFactory(DaosFactory factory) {
		if (factory == null)
			throw new IllegalArgumentException("Concrete DaosFactory is a null pointer");
		concreteFactory = factory;
	}

	public static DaosFactory getFactory() {
		if (concreteFactory == null)
			throw new RuntimeException("Inject concrete factory first.");
		return concreteFactory;
	}

	public abstract PaisDao makePaisDao();

	public abstract UsuariosDao makeUsuariosDao();
	
	public abstract CuentasDao makeCuentasDao();
	
	public abstract DatosPersonalesDao makeDatosPersonalesDao();

	public abstract TurnosDao makeTurnosDao();
}
