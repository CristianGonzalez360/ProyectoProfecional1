/**
 * 
 */
package repositories.jdbc;

import repositories.ClientesDao;
import repositories.CuentasDao;
import repositories.DaosFactory;
import repositories.DatosPersonalesDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.OrdenesDeTrabajoDao;
import repositories.PaisDao;
import repositories.PresupuestosDao;
import repositories.RepuestosDao;
import repositories.RepuestosPlanificadosDao;
import repositories.TrabajosPresupuestadosDao;
import repositories.TurnosDao;
import repositories.UsuariosDao;
import repositories.VehiculosConOrdenDeTrabajoDao;
import repositories.jdbc.utils.DataSource;

public class DaosFactoryImpl extends DaosFactory {

	private DataSource ds;

	private PaisDao paisDao;

	private UsuariosDao usuariosDao;

	private CuentasDao cuentasDao;

	private DatosPersonalesDao datosPersonalesDao;

	private TurnosDao turnosDao;

	private FichaTecnicaVehiculoDao fichaTecnicaVehiculo;

	private OrdenesDeTrabajoDao ordenesDeTrabajoDao;

	private ClientesDao clientesDao;

	private VehiculosConOrdenDeTrabajoDao vehiculosConOtDao;
	
	private RepuestosDao repuestosDao;

	private PresupuestosDao presupuestosDao;
	
	private TrabajosPresupuestadosDao trabajosPlanificadosDao;

	private RepuestosPlanificadosDao repuestosPlanificadosDao;
	
	public DaosFactoryImpl(DataSource dataSource) {
		ds = dataSource;
	}

	@Override
	public PaisDao makePaisDao() {
		if (paisDao == null)
			paisDao = new PaisDaoImpl(ds.getConnection());
		return paisDao;
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
	public VehiculosConOrdenDeTrabajoDao makeVehiculoConOrdeDeTrabajoDao() {
		if (this.vehiculosConOtDao == null)
			this.vehiculosConOtDao = new VehiculosConOrdenDeTrabajoDaoImpl(ds.getConnection());
		return vehiculosConOtDao;
	}

	@Override
	public RepuestosDao makeRepuestoDao() {
		if(this.repuestosDao == null) {
			this.repuestosDao = new RepuestosDaoImpl(ds.getConnection());
		}
		return repuestosDao;
	}
	
	@Override
	public PresupuestosDao makePresupuestoDao() {
		if(presupuestosDao == null)
			presupuestosDao = new PresupuestosDaoImpl(ds.getConnection());
		return presupuestosDao;
	}

	@Override
	public TrabajosPresupuestadosDao makeTrabajosPlanificadosDao() {
		if(trabajosPlanificadosDao == null) {
			trabajosPlanificadosDao = new TrabajosPresupuestadosDaoImpl(ds.getConnection());
		}
		return trabajosPlanificadosDao;
	}

	@Override
	public RepuestosPlanificadosDao makeRepuestosPlanificadosDao() {
		if(repuestosPlanificadosDao == null) {
			repuestosPlanificadosDao = new RepuestosPlanificadosDaoImpl(ds.getConnection());
		}
		return repuestosPlanificadosDao;
	}
}