package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.CuentaDTO;
import repositories.CuentasDao;
import repositories.jdbc.utils.Mapper;

public class CuentasDaoImpl extends GenericJdbcDao<CuentaDTO> implements CuentasDao {

	private static final String insert = "INSERT INTO Cuentas (fechaAltaCuenta, fechaBajaCuenta, nombreUsuCuenta, passUsuCuenta, rol) VALUES (?, ?, ?, ?, ?)";

	private static final String readAll = "SELECT * FROM Cuentas";
	
	private static final String readByCredentials = readAll + " WHERE Cuentas.nombreUsuCuenta = ? AND Cuentas.passUsuCuenta = ?";
	
	public CuentasDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean insert(CuentaDTO entity) {
		return getTemplate()
				.query(insert)
				.param(entity.getFechaDeAlta())
				.param(entity.getFechaDeBaja())
				.param(entity.getNombreUsuario())
				.param(entity.getPassword())
				.param(entity.getRole())
				.excecute();
	}

	@Override
	public boolean update(CuentaDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CuentaDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CuentaDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public CuentaDTO readByCredentials(String email, String pass) {
		List<CuentaDTO> dto = getTemplate().query(readByCredentials).param(email).param(pass).excecute(getMapper());
		return dto.isEmpty() ? null : dto.get(0);
	}
	
	@Override
	protected Mapper<CuentaDTO> getMapper() {
		return new Mapper<CuentaDTO>() {

			@Override
			public CuentaDTO map(Object[] obj) {
				return new CuentaDTO()
				.setIdCuenta((Integer) obj[0])
				.setFechaDeAlta((Date) obj[1])
				.setFechaDeBaja((Date) obj[2])
				.setNombreUsuario((String) obj[3])
				.setPassword((String) obj[4])
				.setRole((String) obj[5]);
			}
		};
	}
}
