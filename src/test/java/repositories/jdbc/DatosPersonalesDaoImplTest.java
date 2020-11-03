package repositories.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.DatosPersonalesDTO;
import repositories.jdbc.utils.H2DataSource;

class DatosPersonalesDaoImplTest {

	DatosPersonalesDaoImpl dao = new DatosPersonalesDaoImpl(new H2DataSource().getConnection());
	
	private DatosPersonalesDTO makeTestDto() {
		DatosPersonalesDTO datos = new DatosPersonalesDTO();
				datos.setNombreCompleto("John Doe");
				datos.setDni(333);
				datos.setTelefono("1111");
				datos.setEmail("johndoe@mail.com");
				datos.setCalle("calle");
				datos.setAltura("2");
				datos.setPiso("2");
				datos.setDpto("A");
				datos.setLocalidad("loc");
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
