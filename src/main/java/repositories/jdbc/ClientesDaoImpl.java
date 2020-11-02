package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import dto.ClienteDTO;
import repositories.ClientesDao;
import repositories.jdbc.utils.Mapper;

public class ClientesDaoImpl extends GenericJdbcDao<ClienteDTO> implements ClientesDao{
	
	static final int FIRST = 0;
	static final String insert = "INSERT INTO clientes (fechaAltaCliente,datosPersonalesId) VALUES (?,?)";
	static final String delete = "DELETE FROM clientes WHERE idCliente = ?";
	static final String readall = "SELECT * FROM clientes";
	static final String readbyid = "SELECT * FROM clientes WHERE idCliente = ?";
	static final String readByDni = "SELECT * FROM clientes  INNER JOIN datosPersonales ON clientes.datosPersonalesId = datosPersonales.idDatosPerso WHERE dniPer = ?";
	
	private DatosPersonalesDaoImpl datosPersonalesDaoImpl;

	public ClientesDaoImpl(Connection connection) {
		super(connection);
		this.datosPersonalesDaoImpl = new DatosPersonalesDaoImpl(connection);
	}

	@Override
	public boolean update(ClienteDTO entity) {
		return datosPersonalesDaoImpl.update(entity.getDatosPersonalesDTO());
	}

	@Override
	public boolean insert(ClienteDTO entity) {
		boolean insetDatosPersonales = datosPersonalesDaoImpl.insert(entity.getDatosPersonalesDTO());
		boolean insertCliente = getTemplate().query(insert)
				.param(entity.getFechaAltaCliente())
				.param(datosPersonalesDaoImpl.readByDni(entity.getDatosPersonalesDTO().getDni()))
				.excecute();
		return (insertCliente && insetDatosPersonales);
	}

	@Override
	public boolean deleteById(Integer id) {
		return getTemplate().query(delete).param(id).excecute();
	}

	@Override
	public ClienteDTO readByID(Integer id) {
		return getTemplate().query(readbyid).param(id).excecute(getMapper()).get(FIRST);
	}

	@Override
	public List<ClienteDTO> readAll() {
		return getTemplate().query(readall).excecute(getMapper());
	}

	@Override
	public ClienteDTO readByDNI(int dni) {
		return getTemplate().query(readByDni).excecute(getMapper()).get(FIRST);
	}

	@Override
	protected Mapper<ClienteDTO> getMapper() {
		return new Mapper<ClienteDTO>() {
			@Override
			public ClienteDTO map(Object[] obj) {
				ClienteDTO ret = new ClienteDTO();
				ret.setIdCliente((Integer) obj[0]);
				ret.setFechaAltaCliente((Date) obj[1]);
				ret.setDatosPersonalesDTO(datosPersonalesDaoImpl.readByID((Integer) obj[2]));
				return ret;
			}
		};
	}

}
