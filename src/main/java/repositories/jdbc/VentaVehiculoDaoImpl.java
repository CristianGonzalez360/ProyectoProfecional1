package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.VentaVehiculoDTO;
import repositories.VentaVehiculoDao;
import repositories.jdbc.utils.Mapper;

public class VentaVehiculoDaoImpl extends GenericJdbcDao<VentaVehiculoDTO> implements VentaVehiculoDao {
	
	public static final String readAll = "SELECT * FROM VentasVehiculos";
	
	public static final String readVentasVehiculosNoDisponibles = readAll + " INNER JOIN Vehiculos WHERE VentasVehiculos.idVehiculo = Vehiculos.idVehiculo AND disponible = false"; //AND idSucursal = ?"; 

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
		return new Mapper<VentaVehiculoDTO>() {

			@Override
			public VentaVehiculoDTO map(Object[] obj) {
				VentaVehiculoDTO ret = new VentaVehiculoDTO();
				ret.setIdVentaVehiculo((Integer) obj[0]);
				ret.setIdUsuPedido((Integer) obj[1]);
				ret.setIdUsuLlegada((Integer) obj[2]);
				ret.setIdPagoVentaVN((Integer) obj[4]);
				ret.setFechaVentaVN((Date) obj[5]);
				ret.setFechaEntregaReal((Date) obj[6]);
				ret.setFabricante((String) obj[7]);
				ret.setComisionCobrada((Double) obj[8]);
				ret.setPrecioVenta((Double) obj[9]);
				ret.setIdVehiculo((Integer) obj[10]);
				ret.setIdCliente((Integer) obj[11]);
				ret.setIdUsuEntrega((Integer) obj[12]);
				return ret;
			}
		};
	}

	@Override
	public List<VentaVehiculoDTO> readVentasVehiculosNoDisponibles() {
		return getTemplate().query(readVentasVehiculosNoDisponibles).excecute(getMapper());
	}

}
