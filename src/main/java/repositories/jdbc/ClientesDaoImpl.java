package repositories.jdbc;

import java.sql.Connection;
import java.util.List;
import dto.ClienteDTO;
import dto.DatosPersonalesDTO;
import repositories.ClientesDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class ClientesDaoImpl extends GenericJdbcDao<ClienteDTO> implements ClientesDao{

	static final String readall = "SELECT idCliente, Clientes.idDatosPersonales, nombreCompleto, dni, telefono, email, calle, altura, piso, dpto, localidad FROM Clientes INNER JOIN DatosPersonales on Clientes.idDatosPersonales = DatosPersonales.idDatosPersonales";
	
	static final String readbyid = "SELECT * FROM Clientes WHERE idCliente = ?";
	
	static final String readByDni = readall + " " + "WHERE DatosPersonales.dni = ?";
	
	static final String insert = "INSERT INTO Clientes (fechaAltaCliente, idDatosPersonales ) VALUES (?,?)";

	static final String delete = "DELETE FROM Clientes WHERE idCliente = ?";
	
	public ClientesDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean insert(ClienteDTO entity) {
		return getTemplate()
				.query(insert)
				.param(entity.getFechaAltaCliente())
				.param(entity.getIdDatosPersonales())
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
		return getTemplate()
				.query(readall)
				.excecute(getMapper());
	}

	@Override
	public ClienteDTO readByDNI(Integer dni) {
		List<ClienteDTO> dtos = getTemplate()
				.query(readByDni)
				.param(dni)
				.excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	protected Mapper<ClienteDTO> getMapper() {
		return new Mapper<ClienteDTO>() {
			@Override
			public ClienteDTO map(Object[] obj) {
				ClienteDTO ret = new ClienteDTO();
				ret.setIdCliente((Integer)obj[0]);
				ret.setIdDatosPersonales((Integer)obj[1]);
				DatosPersonalesDTO datos = new DatosPersonalesDTO();
				datos.setId((Integer)obj[1]);
				datos.setNombreCompleto((String)obj[2]);
				datos.setDni((Integer)obj[3]);
				datos.setTelefono((String)obj[4]);
				datos.setEmail(((String)obj[5]));
				datos.setCalle(((String)obj[6]));
				datos.setAltura((Integer)obj[7]);
				datos.setPiso((Integer)obj[8]);
				datos.setDpto((String)obj[9]);
				datos.setLocalidad((String)obj[10]);
				ret.setDatosPersonalesDTO(datos);
				return ret;
			}
		};
	}
}
