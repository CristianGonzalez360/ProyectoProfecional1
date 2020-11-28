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

	private static final String insert = "INSERT INTO PedidoVehiculo (idVehiculoReservado, fechaPedido, fechaIngreso, idUsuPedido, idUsuIngreso, idCliente) VALUES (?,?,?,?,?,?)";

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
				.param(entity.getIdVehiculoReservado() == null ? new NullObject() : entity.getIdVehiculoReservado())
				.param(entity.getFechaPedido() == null ? new NullObject() : entity.getFechaPedido())
				.param(entity.getFechaIngreso() == null ? new NullObject() : entity.getFechaIngreso())
				.param(entity.getIdUsuPedido() == null ? new NullObject() : entity.getIdUsuPedido())
				.param(entity.getIdUsuIngreso() == null ? new NullObject() : entity.getIdUsuIngreso())
				.param(entity.getIdCliente() == null ? new NullObject() : entity.getIdCliente()).excecute();
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
				pedido.setIdVehiculoReservado((Integer) obj[1] != null ? (Integer) obj[1] : null);
				pedido.setFechaPedido((Date) obj[2] == null ? null : (Date) obj[2]);
				pedido.setFechaIngreso((Date) obj[3]);
				pedido.setIdUsuPedido((Integer) obj[4]);
				pedido.setIdUsuIngreso((Integer) obj[5]);
				pedido.setIdCliente((Integer) obj[6]);
				return pedido;
			}
		};
	}

}
