package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.PresupuestoDTO;
import repositories.PresupuestosDao;
import repositories.jdbc.utils.Mapper;

public class PresupuestosDaoImpl extends GenericJdbcDao<PresupuestoDTO> implements PresupuestosDao {

	public PresupuestosDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(PresupuestoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(PresupuestoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PresupuestoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PresupuestoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<PresupuestoDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}
}
