package repositories.jdbc;

import java.sql.Connection;
import java.util.List;
import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import repositories.ClientesDao;
import repositories.jdbc.utils.Mapper;

public class ClientesDaoImpl extends GenericJdbcDao<ClienteDTO> implements ClientesDao {

	private static final String readall = "SELECT idCliente, Clientes.idDatosPersonales, nombreCompleto, dni, telefono, email, calle, altura, piso, dpto, localidad FROM Clientes INNER JOIN DatosPersonales on Clientes.idDatosPersonales = DatosPersonales.idDatosPersonales";

	private static final String readByDni = readall + " " + "WHERE DatosPersonales.dni = ?";

	private static final String insert = "INSERT INTO Clientes (fechaAltaCliente, idDatosPersonales ) VALUES (?,?)";

	private static final String readByEmail = readall + " " + "WHERE DatosPersonales.email = ?";

	private static final String readByTelefono = readall + " " + "WHERE DatosPersonales.telefono = ?";

	public ClientesDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean insert(ClienteDTO entity) {
		return getTemplate().query(insert).param(entity.getFechaAltaCliente()).param(entity.getIdDatosPersonales())
				.excecute();
	}

	@Override
	public boolean update(ClienteDTO entity) {
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public ClienteDTO readByID(Integer id) {
		return null;
	}

	@Override
	public List<ClienteDTO> readAll() {
		return getTemplate().query(readall).excecute(getMapper());
	}

	@Override
	public ClienteDTO readByDNI(Integer dni) {
		List<ClienteDTO> dtos = getTemplate().query(readByDni).param(dni).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public ClienteDTO readByTelefono(String telefono) {
		List<ClienteDTO> dtos = getTemplate().query(readByTelefono).param(telefono).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public ClienteDTO readByEmail(String email) {
		List<ClienteDTO> dtos = getTemplate().query(readByEmail).param(email).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	protected Mapper<ClienteDTO> getMapper() {
		return new Mapper<ClienteDTO>() {
			@Override
			public ClienteDTO map(Object[] obj) {
				ClienteDTO ret = new ClienteDTO();
				ret.setIdCliente((Integer) obj[0]);
				ret.setIdDatosPersonales((Integer) obj[1]);
				DatosPersonalesDTO datos = new DatosPersonalesDTO();
				datos.setId((Integer) obj[1]);
				datos.setNombreCompleto((String) obj[2]);
				datos.setDni((Integer) obj[3]);
				datos.setTelefono((String) obj[4]);
				datos.setEmail(((String) obj[5]));
				datos.setCalle(((String) obj[6]));
				datos.setAltura((String) obj[7]);
				datos.setPiso((String) obj[8]);
				datos.setDpto((String) obj[9]);
				datos.setLocalidad((String) obj[10]);
				ret.setDatosPersonalesDTO(datos);
				return ret;
			}
		};
	}
}
