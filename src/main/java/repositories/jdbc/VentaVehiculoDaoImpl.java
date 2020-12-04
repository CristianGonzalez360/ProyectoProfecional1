package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.VentaVehiculoDTO;
import repositories.VentaVehiculoDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;
import services.SessionServiceImpl;

public class VentaVehiculoDaoImpl extends GenericJdbcDao<VentaVehiculoDTO> implements VentaVehiculoDao {
	
	public static final String readFechas = "SELECT * FROM VentasVehiculos where fechaVentaVN BETWEEN ? and ?";
	
	public static final String readAll = "SELECT * FROM VentasVehiculos";

	public static final String readByIdVehiculoVendido = "SELECT * FROM VentasVehiculos WHERE idVehiculo = ?";
	
	public static final String insert = 
			"INSERT INTO VentasVehiculos(idUsuVentaVN,idUsuPedido,idUsuLlegada,idPagoVentaVN,fechaVentaVN"
			+ ",fechaEntregaReal,fabricante,comisionCobrada,precioVenta,financiera,nroCuotas,montoCuota"
			+ ",idVehiculo,idCliente,idUsuEntrega,idSucursal) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String readVentasVehiculosNoDisponibles = readAll + " INNER JOIN Vehiculos "
			+ "WHERE VentasVehiculos.idVehiculo = Vehiculos.idVehiculo "
			+ "AND Vehiculos.idSucursal IS null AND VentasVehiculos.idSucursal = ?"; 

	private static final String readById = "SELECT * FROM VentasVehiculos WHERE idVentaVehiculo = ?";
	
	private static final String readByIdVenta = readAll + " WHERE idVentaVehiculo = ? AND fechaEntregaReal is null";
	
	private static final String updateEntregaVehiculo = "UPDATE VentasVehiculos SET fechaEntregaReal = ? WHERE idVentaVehiculo = ?";

	public static final String readVentaParaEntregar = "SELECT * FROM VentasVehiculos where fechaEntregaReal is null AND idSucursal = ?";

	private static final String readByIdVendedor = "SELECT * FROM VentasVehiculos WHERE idUsuVentaVN = ? and fechaVentaVN BETWEEN ? and ?";
	
	private static final String readByIdVehículo = readAll + " WHERE idVehiculo = ?";
	
	public VentaVehiculoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(VentaVehiculoDTO entity) {
		return getTemplate().query(updateEntregaVehiculo)
				.param(entity.getFechaEntregaReal())
				.param(entity.getIdVentaVehiculo()).excecute();
	}

	@Override
	public boolean insert(VentaVehiculoDTO entity) {
		return getTemplate()
				.query(insert)
				.param(entity.getIdUsuVentaVN())
				.param(entity.getIdUsuPedido() == null ? new NullObject() : entity.getIdUsuPedido())
				.param(entity.getIdUsuLlegada() == null ? new NullObject() : entity.getIdUsuLlegada())
				.param(entity.getIdPagoVentaVN() == null ? new NullObject() : entity.getIdPagoVentaVN())
				.param(entity.getFechaVentaVN() == null ? new NullObject() : entity.getFechaVentaVN())
				.param(entity.getFechaEntregaReal() == null ? new NullObject() : entity.getFechaEntregaReal())
				.param(entity.getFabricante())
				.param(entity.getComisionCobrada())
				.param(entity.getPrecioVenta())
				.param(entity.getFinanciera() == null ? new NullObject() : entity.getFinanciera())
				.param(entity.getNroCuotas() == null ? new NullObject() : entity.getNroCuotas())
				.param(entity.getMontoCuota() == null ? new NullObject() : entity.getMontoCuota())
				.param(entity.getIdVehiculo())
				.param(entity.getIdCliente())
				.param(entity.getIdUsuEntrega() == null ? new NullObject() : entity.getIdUsuEntrega())
				.param(entity.getIdSucursalVenta() == null ? new NullObject() : entity.getIdSucursalVenta())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VentaVehiculoDTO readByID(Integer id) {
		List<VentaVehiculoDTO> dtos = getTemplate().query(readById).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<VentaVehiculoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public List<VentaVehiculoDTO> readFechas(Date desde, Date hasta) {
		return getTemplate().query(readFechas)
				.param(desde)
				.param(hasta)
				.excecute(getMapper());
	}

	
	@Override
	public List<VentaVehiculoDTO> readByIdVendedor(Integer id,Date desde, Date hasta) {
		return getTemplate().query(readByIdVendedor)
				.param(id)
				.param(desde)
				.param(hasta)
				.excecute(getMapper());
	}
	
	public List<VentaVehiculoDTO> readByVendedor(int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<VentaVehiculoDTO> readVentasVehiculosNoDisponibles() {
		return getTemplate().query(readVentasVehiculosNoDisponibles)
				.param(SessionServiceImpl.getInstance().getActiveSession().getIdSucursal()).excecute(getMapper());
	}

	@Override
	public VentaVehiculoDTO readByIdVehiculoVendido(Integer idVehiculo) {
		List<VentaVehiculoDTO> ventas = getTemplate().query(readByIdVehiculoVendido).param(idVehiculo).excecute(getMapper());
		return ventas.isEmpty() ? null : ventas.get(0);
	}
	
	@Override
	protected Mapper<VentaVehiculoDTO> getMapper() {
		return new Mapper<VentaVehiculoDTO>() {

			@Override
			public VentaVehiculoDTO map(Object[] obj) {
				VentaVehiculoDTO ret = new VentaVehiculoDTO();
				ret.setIdVentaVehiculo((Integer) obj[0]);
				ret.setIdUsuVentaVN((Integer) obj[1]);
				ret.setIdUsuPedido((Integer) obj[2]);
				ret.setIdUsuLlegada((Integer) obj[3]);
				ret.setIdPagoVentaVN((Integer) obj[4]);
				ret.setFechaVentaVN((Date) obj[5]);
				ret.setFechaEntregaReal((Date) obj[6]);
				ret.setFabricante((String) obj[7]);
				ret.setComisionCobrada((Double) obj[8]);
				ret.setPrecioVenta((Double) obj[9]);
				ret.setFinanciera((String) obj[10]);
				ret.setNroCuotas((Integer) obj[11]);
				ret.setMontoCuota((Double) obj[12]);
				ret.setIdVehiculo((Integer) obj[13]);
				ret.setIdCliente((Integer) obj[14]);
				ret.setIdUsuEntrega((Integer) obj[15]);
				ret.setIdSucursalVenta((Integer) obj[16]);
				return ret;
			}
		};
	}


	@Override
	public boolean noEstaEntregado(Integer idVentaVehiculo) {
		return !getTemplate().query(readByIdVenta).param(idVentaVehiculo).excecute(getMapper()).isEmpty();
	}

	@Override
	public List<VentaVehiculoDTO> readVentasVehiculosParaEntregar() {
		return getTemplate().query(readVentaParaEntregar).param(SessionServiceImpl.getInstance().getActiveSession().getIdSucursal()).excecute(getMapper());
	}

	@Override
	public VentaVehiculoDTO readByIdVehiculo(Integer idVehiculo) {
		List<VentaVehiculoDTO> dtos = getTemplate().query(readByIdVehículo).param(idVehiculo).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}
}
