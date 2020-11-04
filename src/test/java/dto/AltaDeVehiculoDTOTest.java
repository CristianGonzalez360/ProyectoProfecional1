package dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AltaDeVehiculoDTOTest {

	@Test
	void testValidate() {
		AltaDeVehiculoDTO target = new AltaDeVehiculoDTO();
		Assertions.assertTrue(target.validate().isEmpty());
	}

}
