package repositories;

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

	public abstract UsuariosDao makeUsuariosDao();

	public abstract CuentasDao makeCuentasDao();

	public abstract DatosPersonalesDao makeDatosPersonalesDao();

	public abstract TurnosDao makeTurnosDao();

	public abstract FichaTecnicaVehiculoDao makeFichaTecnicaVehiculoDao();

	public abstract OrdenesDeTrabajoDao makeOrdenDeTrabajoDao();

	public abstract ClientesDao makeClienteDao();

	public abstract VehiculosConOrdenDeTrabajoDao makeVehiculoConOrdeDeTrabajoDao();

	public abstract RepuestosDao makeRepuestoDao();

	public abstract PresupuestosDao makePresupuestoDao();

	public abstract TrabajosPresupuestadosDao makeTrabajosPlanificadosDao();

	public abstract RepuestosPlanificadosDao makeRepuestosPlanificadosDao();

	public abstract FacturasDao makeFacturasDao();

	public abstract VehiculoDao makeVehiculoDao();

	public abstract SucursalDao makeSucursalesDao();

	public abstract VehiculosEnVentaDao makeVehiculosParaVentaDao();

	public abstract PedidoVehiculoDao makePedidoVehiculoDao();
}
