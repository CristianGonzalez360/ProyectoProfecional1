package repositories.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.DatosPersonalesDTO;
import repositories.jdbc.utils.H2DataSource;

class DatosPersonalesDaoImplTest {

	DatosPersonalesDaoImpl dao = new DatosPersonalesDaoImpl(new H2DataSource().getConnection());
	
	private DatosPersonalesDTO makeTestDto() {
		DatosPersonalesDTO datos = new DatosPersonalesDTO()
				.setNombreCompleto("John Doe")
				.setDni(333)
				.setTelefono("1111")
				.setEmail("johndoe@mail.com")
				.setCalle("calle")
				.setAltura(2)
				.setPiso(1)
				.setDpto("A")
				.setLocalidad("loc");
		return datos;
	}
	
	@Test
	void testDatosPersonalesDaoImpl() {
		Assertions.assertNotNull(dao);
	}

	@Test
	void testInsert() {
		DatosPersonalesDTO dto =  makeTestDto();
		Assertions.assertTrue(dao.insert(dto));
	}
	
	@Test
	void testReadByDni() {
		DatosPersonalesDTO dto =  makeTestDto();
		dao.insert(dto);
		DatosPersonalesDTO loaded = dao.readByDni(dto.getDni());
		Assertions.assertNotNull(loaded);
		System.out.println(loaded.toString());
	}
}
