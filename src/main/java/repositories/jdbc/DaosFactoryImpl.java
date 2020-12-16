/**
 * 
 */
package repositories.jdbc;

import repositories.CaracteristicasVehiculoDao;
import repositories.ClientesDao;
import repositories.CuentasDao;
import repositories.DaosFactory;
import repositories.DatosPersonalesDao;
import repositories.FacturasDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.MantenimientoDao;
import repositories.GarantiasDao;
import repositories.MonedaDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.PedidoVehiculoDao;
import repositories.PresupuestosDao;
import repositories.RepuestoMantenimientoDao;
import repositories.RepuestosDao;
import repositories.RepuestosPlanificadosDao;
import repositories.SucursalesDao;
import repositories.TrabajoMantenimientoDao;
import repositories.TrabajosPresupuestadosDao;
import repositories.TurnosDao;
import repositories.UsuariosDao;
import repositories.VehiculosDao;
import repositories.IngresoOrdenDeTrabajoDao;
import repositories.VentaVehiculoDao;
import repositories.jdbc.utils.DataSource;

public class DaosFactoryImpl extends DaosFactory {

	private DataSource ds;

	private UsuariosDao usuariosDao;

	private CuentasDao cuentasDao;

	private DatosPersonalesDao datosPersonalesDao;

	private TurnosDao turnosDao;

	private FichaTecnicaVehiculoDao fichaTecnicaVehiculo;

	private OrdenesDeTrabajoDao ordenesDeTrabajoDao;

	private ClientesDao clientesDao;

	private IngresoOrdenDeTrabajoDao vehiculosConOtDao;

	private RepuestosDao repuestosDao;

	private PresupuestosDao presupuestosDao;

	private TrabajosPresupuestadosDao trabajosPlanificadosDao;

	private RepuestosPlanificadosDao repuestosPlanificadosDao;

	private FacturasDao facturasDao;

	private VehiculosDao vehiculoDao;

	private MonedaDao monedasDao;

	private SucursalesDao sucursalesDao;

	private PedidoVehiculoDao pedidoVehiculoDao;

	private VentaVehiculoDao ventaVehiculoDao;

	private CaracteristicasVehiculoDao caracteristicasDao;

	private GarantiasDaoImpl garantiasDao;

	private MantenimientoDao mantenimientoDao;

	private RepuestoMantenimientoDao repuestoMantenimientoDao;

	private TrabajoMantenimientoDao trabajoMantenimientoDao;

	public DaosFactoryImpl(DataSource dataSource) {
		ds = dataSource;
	}

	@Override
	public UsuariosDao makeUsuariosDao() {
		if (usuariosDao == null)
			usuariosDao = new UsuariosDaoImpl(ds.getConnection());
		return usuariosDao;
	}

	@Override
	public CuentasDao makeCuentasDao() {
		if (cuentasDao == null)
			cuentasDao = new CuentasDaoImpl(ds.getConnection());
		return cuentasDao;
	}

	@Override
	public DatosPersonalesDao makeDatosPersonalesDao() {
		if (datosPersonalesDao == null)
			datosPersonalesDao = new DatosPersonalesDaoImpl(ds.getConnection());
		return datosPersonalesDao;
	}

	@Override
	public TurnosDao makeTurnosDao() {
		if (turnosDao == null) {
			turnosDao = new TurnosDaoImpl(ds.getConnection());
		}
		return turnosDao;
	}

	@Override
	public FichaTecnicaVehiculoDao makeFichaTecnicaVehiculoDao() {
		if (this.fichaTecnicaVehiculo == null) {
			this.fichaTecnicaVehiculo = new FichaTecnicaVehiculoDaoImpl(ds.getConnection());
		}
		return fichaTecnicaVehiculo;
	}

	@Override
	public OrdenesDeTrabajoDao makeOrdenDeTrabajoDao() {
		if (ordenesDeTrabajoDao == null)
			ordenesDeTrabajoDao = new OrdenesDeTrabajoDaoImpl(ds.getConnection());
		return ordenesDeTrabajoDao;
	}

	public ClientesDao makeClienteDao() {
		if (clientesDao == null)
			clientesDao = new ClientesDaoImpl(ds.getConnection());
		return clientesDao;
	}

	@Override
	public IngresoOrdenDeTrabajoDao makeVehiculoConOrdeDeTrabajoDao() {
		if (this.vehiculosConOtDao == null)
			this.vehiculosConOtDao = new IngresoOrdenDeTrabajoDaoImpl(ds.getConnection());
		return vehiculosConOtDao;
	}

	@Override
	public RepuestosDao makeRepuestoDao() {
		if (this.repuestosDao == null) {
			this.repuestosDao = new RepuestosDaoImpl(ds.getConnection());
		}
		return repuestosDao;
	}

	@Override
	public PresupuestosDao makePresupuestoDao() {
		if (presupuestosDao == null)
			presupuestosDao = new PresupuestosDaoImpl(ds.getConnection());
		return presupuestosDao;
	}

	@Override
	public TrabajosPresupuestadosDao makeTrabajosPlanificadosDao() {
		if (trabajosPlanificadosDao == null) {
			trabajosPlanificadosDao = new TrabajosPresupuestadosDaoImpl(ds.getConnection());
		}
		return trabajosPlanificadosDao;
	}

	@Override
	public RepuestosPlanificadosDao makeRepuestosPlanificadosDao() {
		if (repuestosPlanificadosDao == null) {
			repuestosPlanificadosDao = new RepuestosPlanificadosDaoImpl(ds.getConnection());
		}
		return repuestosPlanificadosDao;
	}

	@Override
	public FacturasDao makeFacturasDao() {
		if (facturasDao == null)
			facturasDao = new FacturasDaoImpl(ds.getConnection());
		return facturasDao;
	}

	@Override
	public VehiculosDao makeVehiculoDao() {
		if (vehiculoDao == null)
			vehiculoDao = new VehiculoDaoImpl(ds.getConnection());
		return vehiculoDao;
	}

	@Override
	public SucursalesDao makeSucursalesDao() {
		if (sucursalesDao == null)
			sucursalesDao = new SucursalesDaoImpl(ds.getConnection());
		return sucursalesDao;
	}

	@Override
	public PedidoVehiculoDao makePedidoVehiculoDao() {
		if (this.pedidoVehiculoDao == null) {
			this.pedidoVehiculoDao = new PedidoVehiculoDaoImpl(ds.getConnection());
		}
		return pedidoVehiculoDao;
	}

	@Override
	public VentaVehiculoDao makeVentaVehiculoDao() {
		if (ventaVehiculoDao == null)
			ventaVehiculoDao = new VentaVehiculoDaoImpl(ds.getConnection());
		return ventaVehiculoDao;
	}

	@Override
	public CaracteristicasVehiculoDao makeCaracteristicasVehiculoDao() {
		if (caracteristicasDao == null)
			caracteristicasDao = new CaracteristicasVehiculoDaoImpl(ds.getConnection());
		return caracteristicasDao;
	}

	@Override
	public MonedaDao makeMonedasDao() {
		if (monedasDao == null) {
			monedasDao = new MonedaDaoImpl(ds.getConnection());
		}
		return monedasDao;
	}

	@Override
	public MantenimientoDao makeMantenimientoDao() {
		if (mantenimientoDao == null)
			mantenimientoDao = new MantenimientoDaoImpl(ds.getConnection());
		return mantenimientoDao;
	}

	@Override
	public RepuestoMantenimientoDao makeRepuestoMantenimientoDao() {
		if (repuestoMantenimientoDao == null)
			repuestoMantenimientoDao = new RepuestoMantenimientoDaoImpl(ds.getConnection());
		return repuestoMantenimientoDao;
	}

	@Override
	public TrabajoMantenimientoDao makeTrabajoMantenimientoDao() {
		if (trabajoMantenimientoDao == null)
			trabajoMantenimientoDao = new TrabajoMantenimientoDaoImpl(ds.getConnection());
		return trabajoMantenimientoDao;
	}

	public GarantiasDao makeGarantiasVehiculosDao() {
		if (garantiasDao == null)
			garantiasDao = new GarantiasDaoImpl(ds.getConnection());
		return garantiasDao;
	}
}
