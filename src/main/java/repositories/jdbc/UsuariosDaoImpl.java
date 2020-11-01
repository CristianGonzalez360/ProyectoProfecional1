package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;

import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.UsuarioDTO;
import repositories.UsuariosDao;
import repositories.jdbc.utils.Mapper;

public class UsuariosDaoImpl extends GenericJdbcDao<UsuarioDTO> implements UsuariosDao {

	private static final String readAll = "SELECT idUsuario, Usuarios.idCuenta, usuarios.idDatosPersonales, fechaAltaCuenta, fechaBajaCuenta, nombreUsuCuenta, passUsuCuenta, rol, nombreCompleto, dni, telefono, email, calle, altura, piso, dpto, localidad FROM usuarios INNER JOIN cuentas ON usuarios.idCuenta = cuentas.idCuenta INNER JOIN datospersonales on usuarios.idDatosPersonales = datosPersonales.idDatosPersonales";
	
	private static final String readByCredentials = readAll + " WHERE Cuentas.nombreUsuCuenta = ? AND Cuentas.passUsuCuenta = ?";
	
	private static final String readById = readAll + " " + "WHERE Usuarios.idUsuario = ?"; 
	
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
		throw new NotImplementedException();
	}
	
	@Override
	public boolean deleteById(Integer id) {
		throw new NotImplementedException();
	}

	@Override
	public UsuarioDTO readByID(Integer id) {
		List<UsuarioDTO> target = getTemplate().query(readById).param(id).excecute(getMapper());
		return target.isEmpty() ? null : target.get(0);
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
				CuentaDTO cuenta = new CuentaDTO();
				cuenta.setIdCuenta((Integer) obj[1]);
				cuenta.setFechaDeAlta((Date) obj[3]);
				cuenta.setFechaDeBaja((Date) obj[4]);
				cuenta.setNombreUsuario((String)obj[5]);
				cuenta.setPassword((String)obj[6]);
				cuenta.setRole((String)obj[7]);

				DatosPersonalesDTO dper = new DatosPersonalesDTO();
				dper.setId((Integer) obj[2]);
				dper.setNombreCompleto((String)obj[8]);
				dper.setDni((Integer)obj[9]);
				dper.setTelefono((String)obj[10]);
				dper.setEmail((String)obj[11]);
				dper.setCalle((String)obj[12]);
				dper.setAltura((Integer)obj[13]);
				dper.setPiso((Integer)obj[14]);
				dper.setDpto((String)obj[15]);
				dper.setLocalidad((String)obj[16]);
				return new UsuarioDTO((Integer) obj[0], 
						cuenta,dper);
			}			
		};
	}
}