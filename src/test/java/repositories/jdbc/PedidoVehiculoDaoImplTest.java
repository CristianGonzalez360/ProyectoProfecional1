package repositories.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.PedidoVehiculoDTO;
import dto.UsuarioDTO;
import repositories.CuentasDao;
import repositories.DatosPersonalesDao;
import repositories.PedidoVehiculoDao;
import repositories.UsuariosDao;
import repositories.jdbc.utils.H2DataSource;

class PedidoVehiculoDaoImplTest {

	private static CuentasDao cuentaDao = new CuentasDaoImpl(new H2DataSource().getConnection());
	private static DatosPersonalesDao datosDao = new DatosPersonalesDaoImpl(new H2DataSource().getConnection());
	private static UsuariosDao usuarioDao = new UsuariosDaoImpl(new H2DataSource().getConnection());

	private PedidoVehiculoDao pedidoDao = new PedidoVehiculoDaoImpl(new H2DataSource().getConnection());

	@BeforeAll
	private static void llenarDatosParaTest() {
		CuentaDTO cuenta = new CuentaDTO().makeTestDTO();
		cuentaDao.insert(cuenta);

		DatosPersonalesDTO datos = new DatosPersonalesDTO().makeTestDTO();
		datosDao.insert(datos);

		UsuarioDTO usuario = new UsuarioDTO(null, cuenta, datos);
		usuario.getCuenta().setIdCuenta(1);
		usuario.getDatos().setId(1);
		usuarioDao.insert(usuario);

	}

	@Test
	void testPedidoVehiculoDaoImpl() {
		Assertions.assertNotNull(cuentaDao);
		Assertions.assertNotNull(datosDao);
		Assertions.assertNotNull(usuarioDao);
		Assertions.assertNotNull(pedidoDao);
	}

	@Test
	void testReadAllNotNull() {
		Assertions.assertNotNull(pedidoDao.readAll());
	}

	@Test
	void testReadAllIsEmpty() {
		pedidoDao = new PedidoVehiculoDaoImpl(new H2DataSource().getConnection());

		Assertions.assertTrue(pedidoDao.readAll().isEmpty());
	}

	@Test
	void testInsert() {
		PedidoVehiculoDTO pedido = new PedidoVehiculoDTO().makeTestDTO();
		pedido.setIdUsuPedido(1);
		pedido.setIdUsuIngreso(1);

		Assertions.assertTrue(pedidoDao.insert(pedido));
	}

	@Test
	void testReadAll() {
		PedidoVehiculoDTO pedido = new PedidoVehiculoDTO().makeTestDTO();
		pedido.setIdUsuPedido(1);
		pedido.setIdUsuIngreso(1);

		Assertions.assertTrue(pedidoDao.insert(pedido));
		Assertions.assertEquals(pedidoDao.readAll().size(), 1);
	}
}
