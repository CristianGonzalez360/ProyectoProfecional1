package repositories.jdbc;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.CuentaDTO;
import repositories.jdbc.utils.H2DataSource;

class CuentasDaoImplTest {

	CuentasDaoImpl dao = new CuentasDaoImpl(new H2DataSource().getConnection());
	
	private CuentaDTO makeCuentaDto() {
		CuentaDTO dto = new CuentaDTO();
			dto.setFechaDeAlta(new Date());
			dto.setFechaDeBaja(new Date());
			dto.setRole("admin");
			dto.setNombreUsuario("john");
			dto.setPassword("doe");
		return dto;
	}
	
	@Test
	void testCuentasDaoImpl() {
		Assertions.assertNotNull(dao);
	}

	@Test
	void testInsert() {
		CuentaDTO target = makeCuentaDto();
		Assertions.assertTrue(dao.insert(target));
	}
	
	@Test
	void testReadByCredentials() {
		CuentaDTO target = makeCuentaDto();
		dao.insert(target);
		CuentaDTO loaded = dao.readByCredentials(target.getNombreUsuario(), target.getPassword());
		Assertions.assertNotNull(loaded);
	}
}
