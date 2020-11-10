package repositories.jdbc;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import repositories.ClientesDao;
import repositories.DatosPersonalesDao;
import repositories.jdbc.utils.H2DataSource;

class ClientesDaoImplTest {

	H2DataSource ds = new H2DataSource();
	private DatosPersonalesDao datosDao = new DatosPersonalesDaoImpl(ds.getConnection());
	private ClientesDao dao = new ClientesDaoImpl(ds.getConnection());

	@Test
	void testInsertCliente() {
		DatosPersonalesDTO datos = new DatosPersonalesDTO().makeTestDTO();
		Assertions.assertTrue(datosDao.insert(datos));
		ClienteDTO cliente = new ClienteDTO();
		cliente.setFechaAltaCliente(new Date());
		cliente.setIdDatosPersonales(datosDao.readByDni(datos.getDni()).getId());
		Assertions.assertTrue(dao.insert(cliente));
	}

	@Test
	void testClientesDaoImpl() {
		Assertions.assertFalse(dao.readAll().isEmpty());
		Assertions.assertEquals(dao.readAll().size(), 1);
		System.out.println(dao.readAll().toString());
	}

	@Test
	void testReadByDni() {
		Assertions.assertNotNull(dao.readByDNI(new DatosPersonalesDTO().makeTestDTO().getDni()));
	}
}