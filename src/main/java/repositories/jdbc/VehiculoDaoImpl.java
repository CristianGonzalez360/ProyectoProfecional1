package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.VehiculoDTO;
import repositories.VehiculosDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class VehiculoDaoImpl extends GenericJdbcDao<VehiculoDTO> implements VehiculosDao {

	private static final String insert = 
	"INSERT INTO Vehiculos(precioVenta,idFichaTecnica,marca,familia,linea,idCaracteristica,fechaIngreso,disponible,usado,idCompra,idSucursal) "
	+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	public VehiculoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(VehiculoDTO entity) {
		return false;
	}

	@Override
	public boolean insert(VehiculoDTO entity) {
		return getTemplate()
				.query(insert)
				.param(entity.getPrecioVenta())
				.param(entity.getIdFichaTecnica() == null ? new NullObject() : entity.getIdFichaTecnica())
				.param(entity.getMarca())
				.param(entity.getFamilia())
				.param(entity.getLinea())
				.param(entity.getIdCaracteristicas())
				.param(entity.getFechaIngreso() == null ? new NullObject(): entity.getFechaIngreso())
				.param(new Boolean(entity.isDisponible()))
				.param(new Boolean(entity.isUsado()))
				.param(entity.getIdCompra() == null ? new NullObject() : entity.getIdCompra())
				.param(entity.getIdSucursal() == null ? new NullObject() : entity.getIdSucursal())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public VehiculoDTO readByID(Integer id) {
		return null;
	}

	@Override
	public List<VehiculoDTO> readAll() {
		return null;
	}

	@Override
	protected Mapper<VehiculoDTO> getMapper() {
		return new Mapper<VehiculoDTO>() {
			@Override
			public VehiculoDTO map(Object[] obj) {
				VehiculoDTO ret = new VehiculoDTO();
				ret.setIdVehiculo((Integer) obj[0]);
				ret.setPrecioVenta((Double) obj[1]);
				ret.setFechaIngreso((Date) obj[2]);
				ret.setDisponible((boolean) obj[3]);
				ret.setUsado((Boolean) obj[4]);
				ret.setIdCompra((Integer) obj[5]);
				return ret;
			}
		};
	}
}
