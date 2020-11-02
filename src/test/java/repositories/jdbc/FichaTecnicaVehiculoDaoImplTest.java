package repositories.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.FichaTecnicaVehiculoDTO;
import repositories.jdbc.utils.H2DataSource;

class FichaTecnicaVehiculoDaoImplTest {

	private FichaTecnicaVehiculoDaoImpl dao = new FichaTecnicaVehiculoDaoImpl(new H2DataSource().getConnection());
	
	@Test
	void testFichaTecnicaVehiculoDaoImpl() {
		Assertions.assertNotNull(dao);
	}

	@Test
	void testInsert() {
		FichaTecnicaVehiculoDTO target = new FichaTecnicaVehiculoDTO().makeTestDTO();
		Assertions.assertTrue(dao.insert(target));
	}
	
	@Test
	void testReadAll() {
		FichaTecnicaVehiculoDTO target = new FichaTecnicaVehiculoDTO().makeTestDTO();
		target.setNroChasis(31233333);
		target.setNroMotor(454564);
		dao.insert(target);
		Assertions.assertTrue(!dao.readAll().isEmpty());
		Assertions.assertNotNull(dao.readByNroMotor(454564));
	}
}
