package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.VentaVehiculoDTO;
import repositories.VentaVehiculoDao;
import repositories.jdbc.utils.Mapper;

public class VentaVehiculoDaoImpl extends GenericJdbcDao<VentaVehiculoDTO> implements VentaVehiculoDao {

	public VentaVehiculoDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(VentaVehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(VentaVehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VentaVehiculoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VentaVehiculoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VentaVehiculoDTO> readByVendedor(int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<VentaVehiculoDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
