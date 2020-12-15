package repositories.jdbc.utils;

import business_logic.ConfiguradorBaseDeDatosController;
import dto.temporal.ConfigDatabaseDTO;

public class DataSourceFactoryImpl extends DataSourceFactory {

	@Override
	public DataSource makeDataSource(DataSourceType type) {
		DataSource ret = null;
		if (type == DataSourceType.IN_MEMORY)
			ret = new H2DataSource();
		else if (type == DataSourceType.PERSISTENT)
			ret = makeMySQLDataSource();
		return ret;
	}

	private SQLDataSource makeMySQLDataSource() {
		ConfigDatabaseDTO dto = new ConfiguradorBaseDeDatosController().read();
		SQLDataSource ret = new SQLDataSource().dbName("concesionaria").user(dto.getUser()).pass(dto.getPassword())
				.host(dto.getIp()).port(dto.getPort()).driver("com.mysql.jdbc.Driver");
		return ret;
	}
}
