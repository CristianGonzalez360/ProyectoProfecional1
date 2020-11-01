package repositories.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dto.UsuarioDTO;
import repositories.jdbc.utils.H2DataSource;

class UsuariosDaoImplTest {

	private H2DataSource ds = new H2DataSource();
	private	CuentasDaoImpl cuentasDao = new CuentasDaoImpl(ds.getConnection());
	private DatosPersonalesDaoImpl datosPersonalesDao = new DatosPersonalesDaoImpl(ds.getConnection());
	private UsuariosDaoImpl dao = new UsuariosDaoImpl(ds.getConnection());
			
	@Test
	void testNotNull() {
		Assertions.assertNotNull(ds);
		Assertions.assertNotNull(cuentasDao);
		Assertions.assertNotNull(datosPersonalesDao);
		Assertions.assertNotNull(dao);
	}	
	
	@Test
	void testInsert() {
		UsuarioDTO target = new UsuarioDTO().makeTestDTO();
		cuentasDao.insert(target.getCuenta());
		datosPersonalesDao.insert(target.getDatos());
		target.getCuenta().setIdCuenta(cuentasDao.readByCredentials(target.getCuenta().getNombreUsuario(), target.getCuenta().getPassword()).getIdCuenta());
		target.getDatos().setId(datosPersonalesDao.readByDni(target.getDatos().getDni()).getId());
	}
}
