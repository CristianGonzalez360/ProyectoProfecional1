package services;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import dto.ClienteDTO;
import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.PresupuestoDTO;
import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;
import dto.TurnoDTO;
import dto.UsuarioDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
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
		for (TurnoDTO target : graph.getTurnos()) {
			daos.makeTurnosDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (FichaTecnicaVehiculoDTO target : graph.getFichaTecnicaVehiculos()) {
			daos.makeFichaTecnicaVehiculoDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (ClienteDTO target : graph.getClientes()) {
			daos.makeClienteDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (VehiculoConOrdenDeTrabajoDTO target : graph.getVehiculosConOrdenDeTrabajo()) {
			daos.makeVehiculoConOrdeDeTrabajoDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (OrdenDeTrabajoDTO target : graph.getOrdenesDeTrabajo()) {
			daos.makeOrdenDeTrabajoDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (RepuestoDTO target : graph.getRepuestos()) {
			daos.makeRepuestoDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (PresupuestoDTO target : graph.getPresupuestos()) {
			daos.makePresupuestoDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (TrabajoPresupuestadoDTO target : graph.getTrabajos()) {
			daos.makeTrabajosPlanificadosDao().insert(target);
			LogManager.getLogger(this.getClass()).log(Level.INFO, "Seed database >>>>>> " + target.toString());
		}
		for (RepuestoPlanificadoDTO target : graph.getRepuestosPlanificados()) {
			daos.makeRepuestosPlanificadosDao().insert(target);
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