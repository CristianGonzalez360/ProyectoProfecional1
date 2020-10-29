/**
 * 
 */
package repositories.jdbc;

import repositories.DaosFactory;
import repositories.PaisDao;

public class DaosFactoryImpl extends DaosFactory {

	private DataSource ds;
	private PaisDao paisDao;
	
	public DaosFactoryImpl(DataSource dataSource) {
		ds = dataSource;
	}

	@Override
	public PaisDao makePaisDao() {
		if(paisDao == null) paisDao = new PaisDaoImpl(ds.getConnection());
		return paisDao;
	}
}
