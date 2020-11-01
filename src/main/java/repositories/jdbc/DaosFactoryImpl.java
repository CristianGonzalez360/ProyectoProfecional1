/**
 * 
 */
package repositories.jdbc;

import repositories.DaosFactory;
import repositories.PaisDao;
import repositories.UsuariosDao;
import repositories.jdbc.utils.DataSource;

public class DaosFactoryImpl extends DaosFactory {

	private DataSource ds;
	
	private PaisDao paisDao;

	private UsuariosDao usuariosDao;
	
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
		return null;
	}
}
