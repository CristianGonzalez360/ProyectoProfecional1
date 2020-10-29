package business_logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import business_logic.exceptions.ConflictException;
import business_logic.exceptions.ConstraintViolationException;
import business_logic.exceptions.NotFoundException;
import dto.PaisDTO;
import repositories.PaisDao;

class PaisControllerImplTest {
	
	PaisDao paisDao = Mockito.mock(PaisDao.class);
	PaisControllerImpl controller = new PaisControllerImpl(paisDao);

	@Test
	void testPaisControllerImpl() {
		assertNotNull(paisDao);
		assertNotNull(controller);
	}

	@Test
	void testSaveThrowsException() {
		PaisDTO target = new PaisDTO(null, "Bolivia");
		Mockito.when(paisDao.readByName(target.getNombre())).thenReturn(target);
		Assertions.assertThrows(ConflictException.class, ()-> {
			controller.save(target);
		});
	}

	@Test
	void testSaveDoesntThrowsException() {
		PaisDTO target = new PaisDTO(null, "Colombia");
		Mockito.when(paisDao.readByName(target.getNombre())).thenReturn(null);
		try {
			controller.save(target);
		} catch (RuntimeException e) {
			fail("Controller trhows exception");
		}
	}
	
	@Test
	void testUpdateThrowsConflictException() {
		PaisDTO target = new PaisDTO(null, "Bolivia");
		Mockito.when(paisDao.readByName(target.getNombre())).thenReturn(target);
		Assertions.assertThrows(ConflictException.class, ()-> {
			controller.update(target);
		});
	}
	
	@Test
	void testUpdateThrowsNotFoundException() {
		PaisDTO target = new PaisDTO(1, "Bolivia");
		Mockito.when(paisDao.readByName(target.getNombre())).thenReturn(null);
		Mockito.when(paisDao.readByID(target.getId())).thenReturn(null);
		Assertions.assertThrows(NotFoundException.class, ()-> {
			controller.update(target);
		});
	}
	
	@Test
	void testUpdateDoesntThrowsAnyException() {
		PaisDTO target = new PaisDTO(1, "Bolivia");
		Mockito.when(paisDao.readByName(target.getNombre())).thenReturn(null);
		Mockito.when(paisDao.readByID(target.getId())).thenReturn(target);
		try {
			controller.save(target);
		} catch (RuntimeException e) {
			fail("Controller trhows exception");
		}
	}

	@Test
	void testDeleteByIdThrowsException() {
		PaisDTO target = new PaisDTO(1, "Nicaragua");
		Mockito.when(paisDao.deleteById(target.getId())).thenThrow(new ConstraintViolationException(""));
		Assertions.assertThrows(ConstraintViolationException.class, ()-> {
			paisDao.deleteById(target.getId());
		});
	}

	@Test
	void testDeleteByIdDoesntThrowsException() {
		PaisDTO target = new PaisDTO(1, "Bolivia");
		Mockito.when(paisDao.deleteById(target.getId())).thenReturn(true);
		try {
			controller.deleteById(target.getId());
		} catch (ConstraintViolationException e) {
			fail("Controller trhows exception");
		}
	}
	
	@Test
	void testReadAll() {
		PaisDTO target = new PaisDTO(1, "Bolivia");
		Mockito.when(paisDao.readAll()).thenReturn(Arrays.asList(new PaisDTO [] { target }));
		assertNotNull(controller.readAll());
		assertEquals(controller.readAll().size(), 1);
	}

}
