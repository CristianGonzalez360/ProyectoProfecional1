package repositories.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import repositories.DaosFactory;
import repositories.jdbc.utils.H2DataSource;

class DaosFactoryImplTest {

	DaosFactory factory = setUp();
	
	private DaosFactory setUp() {
		DaosFactory.setFactory(new DaosFactoryImpl(new H2DataSource()));
		return DaosFactory.getFactory();
	}
	
	@Test
	void test() {
		assertNotNull(factory);
	}
	
	@Test
	void testMakePaisDao() {
		assertNotNull(factory.makePaisDao());
	}
}
