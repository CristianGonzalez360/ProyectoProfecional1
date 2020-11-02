package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.ClienteDTO;
import repositories.ClientesDao;
import repositories.GenericDao;
import repositories.jdbc.utils.Mapper;

public class ClientesDaoImpl extends GenericJdbcDao<ClienteDTO> implements ClientesDao {

	public ClientesDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(ClienteDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(ClienteDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ClienteDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClienteDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<ClienteDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}
}
