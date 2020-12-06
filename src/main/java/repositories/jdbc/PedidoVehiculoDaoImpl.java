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

	private static final String readAllPedidosDeVenta = "SELECT pv.idPedidoVehiculo, pv.fechaPedido, pv.fechaIngreso, pv.idUsuPedido, pv.idUsuIngreso, pv.idVentaVehiculo, vv.idSucursal FROM pedidovehiculo pv INNER JOIN ventasvehiculos vv ON pv.idVentaVehiculo = vv.idVentaVehiculo WHERE vv.idSucursal = ? AND pv.fechaIngreso IS NULL";

	private static final String readById = readAll + " WHERE idPedidoVehiculo = ?";

	private static final String readByIdVenta = readAll + " WHERE idVentaVehiculo = ?";

	private static final String insert = "INSERT INTO PedidoVehiculo (fechaPedido, fechaIngreso, idUsuPedido, idUsuIngreso, idVentaVehiculo) VALUES (?,?,?,?,?)";

	private static final String updateIngreso = "UPDATE PedidoVehiculo SET fechaIngreso = ?, idUsuIngreso = ? WHERE idPedidoVehiculo = ?";
	
	private static final String readByIdVentaIngreso = readAll + " WHERE idVentaVehiculo = ? and fechaIngreso is not null";

	public PedidoVehiculoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(PedidoVehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean updateIngreso(PedidoVehiculoDTO pedido) {
		return getTemplate().query(updateIngreso)
				.param(pedido.getFechaIngreso())
				.param(pedido.getIdUsuIngreso())
				.param(pedido.getIdPedidoVehiculo()).excecute();
	}

	@Override
	public boolean insert(PedidoVehiculoDTO entity) {
		return getTemplate().query(insert)
				.param(entity.getFechaPedido() == null ? new NullObject() : entity.getFechaPedido())
				.param(entity.getFechaIngreso() == null ? new NullObject() : entity.getFechaIngreso())
				.param(entity.getIdUsuPedido() == null ? new NullObject() : entity.getIdUsuPedido())
				.param(entity.getIdUsuIngreso() == null ? new NullObject() : entity.getIdUsuIngreso())
				.param(entity.getIdVentaVehiculo() == null ? new NullObject() : entity.getIdVentaVehiculo()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PedidoVehiculoDTO readByID(Integer id) {
		List<PedidoVehiculoDTO> ret = getTemplate().query(readById).param(id).excecute(getMapper());
		return ret.isEmpty() ? null : ret.get(0);
	}

	@Override
	public List<PedidoVehiculoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public List<PedidoVehiculoDTO> readAllPedidosDeVenta(Integer idSucursal) {
		return getTemplate().query(readAllPedidosDeVenta).param(idSucursal).excecute(getMapper());
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

	@Override
	public boolean estaPedido(Integer idVentaVehiculo) {
		return !getTemplate().query(readByIdVenta).param(idVentaVehiculo).excecute(getMapper()).isEmpty();
	}

	@Override
	public boolean estaIngresado(Integer idVentaVehiculo) {
		return !getTemplate().query(readByIdVentaIngreso).param(idVentaVehiculo).excecute(getMapper()).isEmpty();
	}
	
}
