package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.MonedaDTO;
import repositories.MonedaDao;
import repositories.jdbc.utils.Mapper;

public class MonedaDaoImpl extends GenericJdbcDao<MonedaDTO> implements MonedaDao {

	public MonedaDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(MonedaDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(MonedaDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MonedaDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MonedaDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<MonedaDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
