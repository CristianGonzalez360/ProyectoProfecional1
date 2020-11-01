package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.UserDTO;
import repositories.UsuariosDao;

public class UsuariosDaoImpl extends GenericJdbcDao<UserDTO> implements UsuariosDao {

	private static final String readByCredentials = 
			"SELECT idUsuario, nombrePer, dniPer, telefonoPer, emailPer, callePer, alturaPer, dptoPer, locPer, fechaAltaCuenta, fechaBajaCuenta, esActiva, nombreUsuCuenta, passUsuCuenta, rol "
			+"FROM Usuarios INNER JOIN Cuentas ON Usuarios.idCuenta = Cuentas.idCuenta"
			+"INNER JOIN Usuarios.datosPersonalesId = idDatosPerso";
	
	public UsuariosDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(UserDTO entity) {
		return false;
	}

	@Override
	public boolean insert(UserDTO entity) {
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public UserDTO readByID(Integer id) {
		return null;
	}

	@Override
	public List<UserDTO> readAll() {
		return null;
	}

	@Override
	public UserDTO readByCredentials(String email, String password) {
		return null;
	}

	@Override
	protected Mapper<UserDTO> getMapper() {
		return null;
	}
}
