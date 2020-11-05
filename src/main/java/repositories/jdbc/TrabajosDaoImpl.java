package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.TrabajoDTO;
import repositories.TrabajosDao;
import repositories.jdbc.utils.Mapper;

public class TrabajosDaoImpl extends GenericJdbcDao<TrabajoDTO> implements TrabajosDao {

	public TrabajosDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(TrabajoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(TrabajoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TrabajoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrabajoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<TrabajoDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
