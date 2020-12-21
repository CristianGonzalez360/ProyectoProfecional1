package services;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.MonedaDTO;
import dto.SucursalDTO;
import dto.UsuarioDTO;
import repositories.DaosFactory;
import javax.inject.Inject;
import java.io.InputStream;

public class DatabaseSeederServiceImpl {

	private final String ymlFileName = "db.yml";
	private DaosFactory daos;

	@Inject
	public DatabaseSeederServiceImpl(DaosFactory daosFactory) {
		this.daos = daosFactory;
	}

	public void seedDatabase() {
		DatabaseGraph graph = loadDatabaseGraph();
		for (CuentaDTO target : graph.getCuentas()) {
			daos.makeCuentasDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (DatosPersonalesDTO target : graph.getDatosPersonales()) {
			daos.makeDatosPersonalesDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (UsuarioDTO target : graph.getUsuarios()) {
			daos.makeUsuariosDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (MonedaDTO target : graph.getMonedas()) {
			daos.makeMonedasDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (SucursalDTO target : graph.getSucursales()) {
			daos.makeSucursalesDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
	}

	private DatabaseGraph loadDatabaseGraph() {
		DatabaseGraph graph = null;
		try {
			LogManager.getLogger(this.getClass()).log(Level.INFO,
					"Seed database operation status: [INITIALIZED - LOADING .YML]");
			Yaml yaml = new Yaml(new Constructor(DatabaseGraph.class));
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(ymlFileName);
			graph = yaml.load(inputStream);
		} catch (Exception e) {
			LogManager.getLogger(this.getClass()).log(Level.ERROR,
					"Seed database operation status: [ABORT - ERROR LOADING .YML, " + e.getMessage() + "]");
		}
		return graph;
	}
}