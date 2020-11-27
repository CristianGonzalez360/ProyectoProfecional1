package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.VehiculoReservadoDTO;
import repositories.VehiculoReservadoDao;
import repositories.jdbc.utils.Mapper;

public class VehiculoReservadoDaoImpl extends GenericJdbcDao<VehiculoReservadoDTO> implements VehiculoReservadoDao {

	public VehiculoReservadoDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(VehiculoReservadoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(VehiculoReservadoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VehiculoReservadoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoReservadoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<VehiculoReservadoDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
