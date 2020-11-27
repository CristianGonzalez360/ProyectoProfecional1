package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.SucursalDTO;
import repositories.SucursalDao;
import repositories.jdbc.utils.Mapper;

public class SucursalDaoImpl extends GenericJdbcDao<SucursalDTO> implements SucursalDao{

	public SucursalDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(SucursalDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(SucursalDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SucursalDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SucursalDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<SucursalDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
