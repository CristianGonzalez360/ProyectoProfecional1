package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.PedidoVehiculoDTO;
import repositories.PedidoVehiculoDao;
import repositories.jdbc.utils.Mapper;

public class PedidoVehiculoDaoImpl extends GenericJdbcDao<PedidoVehiculoDTO> implements PedidoVehiculoDao {

	public PedidoVehiculoDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(PedidoVehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(PedidoVehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PedidoVehiculoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PedidoVehiculoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PedidoVehiculoDTO> readByCliente(int idCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<PedidoVehiculoDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
