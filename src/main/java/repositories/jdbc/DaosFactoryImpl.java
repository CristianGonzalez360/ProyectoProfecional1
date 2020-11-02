/**
 * 
 */
package repositories.jdbc;


import dto.CuentaDTO;
import repositories.ClientesDao;
import repositories.CuentasDao;
import repositories.DaosFactory;
import repositories.DatosPersonalesDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.GenericDao;
import repositories.PaisDao;
import repositories.TurnosDao;
import repositories.UsuariosDao;
import repositories.jdbc.utils.DataSource;

public class DaosFactoryImpl extends DaosFactory {

	private DataSource ds;
	
	private PaisDao paisDao;

	private UsuariosDao usuariosDao;
	
	private CuentasDao cuentasDao;
	
	private DatosPersonalesDao datosPersonalesDao;
	

	private TurnosDao turnosDao;
	
	private FichaTecnicaVehiculoDao fichaTecnicaVehiculo;

	private ClientesDao clientesDao;

	
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
		if(usuariosDao == null)
			usuariosDao = new UsuariosDaoImpl(ds.getConnection());
		return usuariosDao;
	}

	@Override
	public CuentasDao makeCuentasDao() {
		if(cuentasDao == null)
			cuentasDao = new CuentasDaoImpl(ds.getConnection());
		return cuentasDao;
	}

	@Override
	public DatosPersonalesDao makeDatosPersonalesDao() {
		if(datosPersonalesDao == null)
			datosPersonalesDao = new DatosPersonalesDaoImpl(ds.getConnection());
		return datosPersonalesDao;
	}
	
	@Override
	public TurnosDao makeTurnosDao() {
		if(turnosDao == null) {
			turnosDao = new TurnosDaoImpl(ds.getConnection());
		}
		return turnosDao;
	}

	@Override
	public FichaTecnicaVehiculoDao makeFichaTecnicaVehiculoDao() {
		if(this.fichaTecnicaVehiculo == null) {
			this.fichaTecnicaVehiculo = new FichaTecnicaVehiculoDaoImpl(ds.getConnection());
		}
		return fichaTecnicaVehiculo;
	}

	@Override
	public ClientesDao makeClienteDao() {
		if(clientesDao == null)
			clientesDao = new ClientesDaoImpl(ds.getConnection());
		return clientesDao;
	}
}
