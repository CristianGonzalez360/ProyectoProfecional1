package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.VehiculoDTO;
import repositories.VehiculoDao;
import repositories.jdbc.utils.Mapper;

public class VehiculoDaoImpl extends GenericJdbcDao<VehiculoDTO> implements VehiculoDao {

	public VehiculoDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(VehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(VehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VehiculoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoDTO> readNuevosDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<VehiculoDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoDTO> readUsados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoDTO> readUsadosYNuevosDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}

}
