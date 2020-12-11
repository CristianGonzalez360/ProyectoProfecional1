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

	public abstract LoginController makeLoginController();

	public abstract TurnosController makeTurnosController();

	public abstract ClientesController makeClientesController();

	public abstract VehiculosConOrdenDeTrabajoController makeVehiculosConOrdenDeTrabajoController();

	public abstract OrdenesTrabajoController makeOrdenesDeTrabajoController();

	public abstract PresupuestosController makePresupuestosController();

	public abstract RepuestosController makeRepuestosController();
	
	public abstract FacturasController makeFacturasController();
	
	public abstract EntregaDeVehiculoController makeEntregasController();

	public abstract SucursalesController makeSucursalesController();

	public abstract PedidosController makePedidosController();

	public abstract VentasVehiculosController makeVentasVehiculosController();

	public abstract ConfiguradorTerminalController makeConfiguradorTerminalController();
	
	public abstract VehiculosController makeVehiculosController();

	public abstract UsuariosController makeUsuariosController();

	public abstract ConfiguradorBaseDeDatosController makeConfiguradorBaseDeDatosController();

	public abstract ConfiguradorSmtpController makeConfiguradorSmtpController();

}
