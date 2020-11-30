package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.PedidoVehiculoDTO;
import repositories.PedidoVehiculoDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class PedidoVehiculoDaoImpl extends GenericJdbcDao<PedidoVehiculoDTO> implements PedidoVehiculoDao {

	private static final String readAll = "SELECT * FROM PedidoVehiculo";

	private static final String insert = "INSERT INTO PedidoVehiculo (fechaPedido, fechaIngreso, idUsuPedido, idUsuIngreso, idVentaVehiculo) VALUES (?,?,?,?,?)";

	public PedidoVehiculoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(PedidoVehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(PedidoVehiculoDTO entity) {
		return getTemplate().query(insert)
				.param(entity.getFechaPedido() == null ? new NullObject() : entity.getFechaPedido())
				.param(entity.getFechaIngreso() == null ? new NullObject() : entity.getFechaIngreso())
				.param(entity.getIdUsuPedido() == null ? new NullObject() : entity.getIdUsuPedido())
				.param(entity.getIdUsuIngreso() == null ? new NullObject() : entity.getIdUsuIngreso())
				.param(entity.getIdVentaVehiculo() == null? new NullObject() : entity.getIdVentaVehiculo()).excecute();
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
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public List<PedidoVehiculoDTO> readByCliente(int idCliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<PedidoVehiculoDTO> getMapper() {
		return new Mapper<PedidoVehiculoDTO>() {

			@Override
			public PedidoVehiculoDTO map(Object[] obj) {
				PedidoVehiculoDTO pedido = new PedidoVehiculoDTO();
				pedido.setIdPedidoVehiculo((Integer) obj[0]);
				pedido.setFechaPedido((Date) obj[1] == null ? null : (Date) obj[1]);
				pedido.setFechaIngreso((Date) obj[2]);
				pedido.setIdUsuPedido((Integer) obj[3]);
				pedido.setIdUsuIngreso((Integer) obj[4]);
				pedido.setIdVentaVehiculo((Integer) obj[5]);
				return pedido;
			}
		};
	}

}
