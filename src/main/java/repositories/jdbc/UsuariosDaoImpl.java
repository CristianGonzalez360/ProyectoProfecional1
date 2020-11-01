package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.UsuarioDTO;
import repositories.UsuariosDao;
import repositories.jdbc.utils.Mapper;

public class UsuariosDaoImpl extends GenericJdbcDao<UsuarioDTO> implements UsuariosDao {

	private static final String readAll = "SELECT idUsuario, Usuarios.idCuenta, usuarios.idDatosPersonales, fechaAltaCuenta, fechaBajaCuenta, nombreUsuCuenta, passUsuCuenta, rol, nombreCompleto, dni, telefono, email, calle, altura, piso, dpto, localidad FROM usuarios INNER JOIN cuentas ON usuarios.idCuenta = cuentas.idCuenta INNER JOIN datospersonales on usuarios.idDatosPersonales = datosPersonales.idDatosPersonales";
	
	private static final String readByCredentials = readAll + " WHERE Cuentas.nombreUsuCuenta = ? AND Cuentas.passUsuCuenta = ?";
	
	private static final String insert = "INSERT INTO Usuarios (idCuenta, idDatosPersonales) VALUES (?, ?)";
	
	public UsuariosDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean insert(UsuarioDTO entity) {
		return getTemplate()
				.query(insert)
				.param(entity.getCuenta().getIdCuenta())
				.param(entity.getDatos().getId())
				.excecute();
	}

	@Override
	public boolean update(UsuarioDTO entity) {
		return false;
	}
	
	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public UsuarioDTO readByID(Integer id) {
		return null;
	}

	@Override
	public List<UsuarioDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public UsuarioDTO readByCredentials(String email, String password) {
		List<UsuarioDTO> dtos = getTemplate().query(readByCredentials).param(email).param(password).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}
	
	@Override
	protected Mapper<UsuarioDTO> getMapper() {
		return new Mapper<UsuarioDTO>() {

			@Override
			public UsuarioDTO map(Object[] obj) {
				return new UsuarioDTO((Integer) obj[0], new CuentaDTO().setIdCuenta((Integer) obj[1]), new DatosPersonalesDTO().setId((Integer) obj[2]));
			}			
		};
	}
}