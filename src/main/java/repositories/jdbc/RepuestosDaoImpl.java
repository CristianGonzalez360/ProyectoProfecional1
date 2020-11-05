package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.RepuestoDTO;
import repositories.RepuestosDao;
import repositories.jdbc.utils.Mapper;

public class RepuestosDaoImpl extends GenericJdbcDao<RepuestoDTO> implements RepuestosDao {

	public RepuestosDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(RepuestoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(RepuestoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RepuestoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepuestoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<RepuestoDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}
}
