package dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.temporal.AltaDeVehiculoDTO;

class AltaDeVehiculoDTOTest {

	@Test
	void testValidate() {
		AltaDeVehiculoDTO target = new AltaDeVehiculoDTO();
		Assertions.assertTrue(target.validate().isEmpty());
	}

}
