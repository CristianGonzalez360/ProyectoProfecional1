package repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import repositories.jdbc.FichaTecnicaVehiculoDaoImpl;
import repositories.jdbc.IngresoOrdenDeTrabajoDaoImpl;
import repositories.jdbc.utils.H2DataSource;

class VehiculosConOrdenDeTrabajoDaoTest {

	H2DataSource ds = new H2DataSource();
	FichaTecnicaVehiculoDaoImpl fichasDao = new FichaTecnicaVehiculoDaoImpl(ds.getConnection());
	IngresoOrdenDeTrabajoDaoImpl dao = new IngresoOrdenDeTrabajoDaoImpl(ds.getConnection());

	@Test
	void testNotNull() {
		Assertions.assertNotNull(dao);
	}

	@Test
	void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	void testReadAll() {
		fail("Not yet implemented");
	}
}
