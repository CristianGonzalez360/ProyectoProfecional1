package business_logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dto.ClienteDTO;
import repositories.ClientesDao;
import repositories.DatosPersonalesDao;

class ClientesControllerTest {

	ClientesDao dao = Mockito.mock(ClientesDao.class);
	DatosPersonalesDao datosdao = Mockito.mock(DatosPersonalesDao.class);
	ClientesController controller = new ClientesController(dao, datosdao);
	
	@Test
	void testClientesController() {
		Assertions.assertNotNull(dao);
	}

	@Test
	void testReadByDni() {
		Mockito.when(dao.readByDNI(111)).thenReturn(new ClienteDTO().makeTestDTO());
		Assertions.assertNotNull(controller.readByDni(111));
	}
}
