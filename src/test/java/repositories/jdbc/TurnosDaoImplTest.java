package repositories.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.TurnoDTO;
import repositories.jdbc.utils.H2DataSource;

class TurnosDaoImplTest {

	private TurnosDaoImpl dao = new TurnosDaoImpl(new H2DataSource().getConnection());

	@Test
	void testTurnosDaoImpl() {
		Assertions.assertNotNull(dao);
	}
	
	@Test
	void testUpdate() {
		TurnoDTO target = new TurnoDTO().makeTestDTOUpdate();
		Assertions.assertTrue(dao.update(target));
	}

	@Test
	void testInsert() {
		TurnoDTO target = new TurnoDTO().makeTestDTO();
		Assertions.assertTrue(dao.insert(target));
	}

	@Test
	void testReadByDni() {
		TurnoDTO target = new TurnoDTO().makeTestDTO();
		dao.insert(target);
		Assertions.assertNotNull(dao.readByDni(target.getDniCliente()));
	}

	@Test
	void testReadAll() {
		for (int i = 0; i < 10; i++) {
			dao.insert(new TurnoDTO().makeTestDTO());
		}
		Assertions.assertEquals(dao.readAll().size(), 11);
	}
}
