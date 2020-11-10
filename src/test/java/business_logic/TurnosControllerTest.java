package business_logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dto.TurnoDTO;
import repositories.TurnosDao;

class TurnosControllerTest {

	private TurnosDao dao = Mockito.mock(TurnosDao.class);
	private TurnosController controller = new TurnosController(dao);

	@Test
	void testTurnosController() {
		assertNotNull(controller);
	}

	@Test
	void testReadAll() {
		LinkedList<TurnoDTO> turnos = new LinkedList<>();
		for (int i = 0; i < 10; i++)
			turnos.add(new TurnoDTO().makeTestDTO());
		Mockito.when(dao.readAll()).thenReturn(turnos);
		Assertions.assertEquals(controller.readAll().size(), turnos.size());
	}

	@Test
	void testReadAllByDNI() {
		LinkedList<TurnoDTO> turnos = new LinkedList<>();
		for (int i = 0; i < 10; i++)
			turnos.add(new TurnoDTO().makeTestDTO());
		Mockito.when(dao.readAll()).thenReturn(turnos);
		Assertions.assertEquals(controller.readAll().size(), turnos.size());
	}

	@Test
	void testReadByDniCliente() {
		TurnoDTO target = new TurnoDTO().makeTestDTO();
		Mockito.when(dao.readByDni(Mockito.any(Integer.class))).thenReturn(target);
		Assertions.assertNotNull(controller.readByDniCliente(target.getDniCliente()));
	}
}
