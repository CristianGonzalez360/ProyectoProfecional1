package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.CompraVehiculoDTO;
import dto.VentaVehiculoDTO;
import repositories.CompraVehiculoDao;
import repositories.jdbc.utils.Mapper;

public class CompraVehiculoDaoImpl extends GenericJdbcDao<CompraVehiculoDTO> implements CompraVehiculoDao {

	private static final String INSERT = "INSERT INTO CompraVehiculo(idVehiculo, precioCompra, fechaCompra, idUsuCompra) VALUES (?,?,?,?)";

	private static final String readFechas = "SELECT * FROM CompraVehiculo where fechaCompra BETWEEN ? and ?";

	
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
		return getTemplate().query(INSERT).param(entity.getIdVehiculo()).param(entity.getPrecioCompra())
				.param(entity.getFechaCompra()).param(entity.getIdUsuCompra()).excecute();
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

	public List<CompraVehiculoDTO> readFechas(Date desde, Date hasta) {
		return getTemplate().query(readFechas).param(desde).param(hasta).excecute(getMapper());
	}
	
}
