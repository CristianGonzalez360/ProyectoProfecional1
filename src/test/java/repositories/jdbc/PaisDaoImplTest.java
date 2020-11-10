package repositories.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dto.PaisDTO;
import repositories.jdbc.utils.DataSource;
import repositories.jdbc.utils.H2DataSource;

class PaisDaoImplTest {

	private PaisDaoImpl dao = setup();

	public PaisDaoImpl setup() {
		DataSource ds = new H2DataSource();
		return new PaisDaoImpl(ds.getConnection());
	}

	PaisDTO makeTestDTO() {
		PaisDTO target = new PaisDTO(null, "Nicaragua");
		return target;
	}

	@BeforeEach
	void cleanDb() {
		for (PaisDTO target : dao.readAll())
			dao.deleteById(target.getId());
	}

	@Test
	void testPaisDaoImpl() {
		assertNotNull(dao);
	}

	@Test
	void testInsert() {
		PaisDTO target = makeTestDTO();
		assertTrue(dao.insert(target));
	}

	@Test
	void testUpdate() {
		PaisDTO saved = makeTestDTO();
		dao.insert(saved);
		PaisDTO updated = dao.readByName(saved.getNombre());
		updated.setNombre("Colombia");
		assertTrue(dao.update(updated));
		assertNotEquals(saved.getNombre(), updated.getNombre());
	}

	@Test
	void testDeleteById() {
		PaisDTO saved = makeTestDTO();
		dao.insert(saved);
		PaisDTO target = dao.readAll().get(0);
		assertTrue(dao.deleteById(target.getId()));
	}

	@Test
	void testReadByID() {
		PaisDTO saved = makeTestDTO();
		dao.insert(saved);
		PaisDTO target = dao.readAll().get(0);
		assertNotNull(dao.readByID(target.getId()));
	}

	@Test
	void testReadAll() {
		PaisDTO saved = makeTestDTO();
		dao.insert(saved);
		assertEquals(dao.readAll().size(), 1);
	}

	@Test
	void testReadByName() {
		PaisDTO saved = makeTestDTO();
		dao.insert(saved);
		assertNotNull(dao.readByName(saved.getNombre()));
	}
}
