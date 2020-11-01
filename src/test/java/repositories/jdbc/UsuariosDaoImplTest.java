package repositories.jdbc;

import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.UsuarioDTO;
import repositories.jdbc.utils.H2DataSource;

class UsuariosDaoImplTest {

	private H2DataSource ds = new H2DataSource();
	private	CuentasDaoImpl cuentasDao = new CuentasDaoImpl(ds.getConnection());
	private DatosPersonalesDaoImpl datosPersonalesDao = new DatosPersonalesDaoImpl(ds.getConnection());
	private UsuariosDaoImpl dao = new UsuariosDaoImpl(ds.getConnection());
	
	private final String user = "john";
	private final String password = "doe";
	private final Integer dni = 31233;
	
	private UsuarioDTO makeTestUsuario() {
		DatosPersonalesDTO datos = new DatosPersonalesDTO()
				.setNombreCompleto("John Doe")
				.setDni(dni)
				.setTelefono("1111")
				.setEmail("johndoe@mail.com")
				.setCalle("calle")
				.setAltura(2)
				.setPiso(1)
				.setDpto("A")
				.setLocalidad("loc");
		CuentaDTO cuenta = new CuentaDTO()
				.setFechaDeAlta(new Date())
				.setFechaDeBaja(new Date())
				.setNombreUsuario(user)
				.setPassword(password)
				.setRole("admin");
		UsuarioDTO usuario = new UsuarioDTO(null, cuenta, datos);
		return usuario;
	}
	
	@Test
	void testNotNull() {
		Assertions.assertNotNull(ds);
		Assertions.assertNotNull(cuentasDao);
		Assertions.assertNotNull(datosPersonalesDao);
		Assertions.assertNotNull(dao);
	}	
	
	@Test
	void testInsert() {
		UsuarioDTO target = makeTestUsuario();
		cuentasDao.insert(target.getCuenta());
		datosPersonalesDao.insert(target.getDatos());
		target.getCuenta().setIdCuenta(cuentasDao.readByCredentials(user, password).getIdCuenta());
		target.getDatos().setId(datosPersonalesDao.readByDni(dni).getId());
		dao.insert(target);
		System.out.println(dao.readAll().toString());
	}
}
