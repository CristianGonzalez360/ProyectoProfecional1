package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.CompraVehiculoDTO;
import repositories.CompraVehiculoDao;
import repositories.jdbc.utils.Mapper;

public class CompraVehiculoDaoImpl extends GenericJdbcDao<CompraVehiculoDTO> implements CompraVehiculoDao {

	public CompraVehiculoDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(CompraVehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(CompraVehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CompraVehiculoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompraVehiculoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<CompraVehiculoDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
