/**
 * 
 */
package repositories.jdbc;

import repositories.CuentasDao;
import repositories.DaosFactory;
import repositories.DatosPersonalesDao;
import repositories.PaisDao;
import repositories.UsuariosDao;
import repositories.jdbc.utils.DataSource;

public class DaosFactoryImpl extends DaosFactory {

	private DataSource ds;
	
	private PaisDao paisDao;

	private UsuariosDao usuariosDao;
	
	private CuentasDao cuentasDao;
	
	private DatosPersonalesDao datosPersonalesDao;
	
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
}
