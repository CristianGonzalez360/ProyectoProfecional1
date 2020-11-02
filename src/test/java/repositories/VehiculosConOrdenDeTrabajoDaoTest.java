package repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import repositories.jdbc.FichaTecnicaVehiculoDaoImpl;
import repositories.jdbc.VehiculosConOrdenDeTrabajoDaoImpl;
import repositories.jdbc.utils.H2DataSource;

class VehiculosConOrdenDeTrabajoDaoTest {

	
	H2DataSource ds = new H2DataSource();
	FichaTecnicaVehiculoDaoImpl fichasDao = new FichaTecnicaVehiculoDaoImpl(ds.getConnection());
	VehiculosConOrdenDeTrabajoDaoImpl dao = new VehiculosConOrdenDeTrabajoDaoImpl(ds.getConnection());
	
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
