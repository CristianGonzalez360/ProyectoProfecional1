package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.VehiculoDTO;
import repositories.VehiculosDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class VehiculoDaoImpl extends GenericJdbcDao<VehiculoDTO> implements VehiculosDao {

	private static final String insert = "INSERT INTO Vehiculos(precioVenta,idFichaTecnica,marca,familia,linea,color,idCaracteristica,fechaIngreso,disponible,usado,idSucursal) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

	private static final String readDisponiblesByCriteria = "SELECT * FROM Vehiculos WHERE marca = ? AND usado = ?";
	
	private static final String readById = "SELECT * FROM Vehiculos WHERE idVehiculo = ?";

	private static final String readAllMarcas = "SELECT DISTINCT marca FROM Vehiculos";
	
	private static final String readAll = "SELECT * FROM Vehiculos";

	private static final String updateDisponibilidad = "UPDATE Vehiculos SET disponible = ? WHERE idVehiculo = ?";
	
	private static final String readVehiculosUsados = readAll + " WHERE usado = true AND idVehiculo NOT IN (SELECT idVehiculo FROM VentasVehiculos)";

	private static final String maximoId = "SELECT MAX(idVehiculo) FROM Vehiculos";
	
	public VehiculoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(VehiculoDTO entity) {
		return false;
	}
	
	@Override
	public boolean updateDisponibilidadVehiculo(Integer id, Boolean boolean1) {
		return getTemplate().query(updateDisponibilidad).param(boolean1).param(id).excecute();
	}

	@Override
	public boolean insert(VehiculoDTO entity) {
		return getTemplate().query(insert)
				.param(entity.getPrecioVenta())
				.param(entity.getIdFichaTecnica() == null ? new NullObject() : entity.getIdFichaTecnica())
				.param(entity.getMarca())
				.param(entity.getFamilia())
				.param(entity.getLinea())
				.param(entity.getColor())
				.param(entity.getIdCaracteristicas())
				.param(entity.getFechaIngreso() == null ? new NullObject() : entity.getFechaIngreso())
				.param(new Boolean(entity.isDisponible()))
				.param(new Boolean(entity.isUsado()))
				.param(entity.getIdSucursal() == null ? new NullObject() : entity.getIdSucursal())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public VehiculoDTO readByID(Integer id) {
		List<VehiculoDTO> dtos = getTemplate().query(readById).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<VehiculoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public List<String> readAllMarcasVehiculos() {
		return getTemplate().query(readAllMarcas).excecute(new Mapper<String>() {
			@Override
			public String map(Object[] obj) {
				return (String) obj[0];
			}
		});
	}

	@Override
	public List<VehiculoDTO> readDisponiblesByCriteria(String marca, Boolean usado) {
		return getTemplate()
				.query(readDisponiblesByCriteria)
				.param(marca)
				.param(usado)
				.excecute(getMapper());
	}

	@Override
	protected Mapper<VehiculoDTO> getMapper() {
		return new Mapper<VehiculoDTO>() {
			@Override
			public VehiculoDTO map(Object[] obj) {
				VehiculoDTO ret = new VehiculoDTO();
				ret.setIdVehiculo((Integer) obj[0]);
				ret.setPrecioVenta((Double) obj[1]);
				ret.setIdFichaTecnica(obj[2] == null ? null : (Integer) obj[2]);
				ret.setMarca((String) obj[3]);
				ret.setFamilia((String) obj[4]);
				ret.setLinea((String) obj[5]);
				ret.setColor((String) obj[6]);
				ret.setIdCaracteristicas((Integer) obj[7]);
				ret.setFechaIngreso(obj[8] == null ? null : (Date) obj[8]);
				ret.setDisponible(((Boolean) obj[9]).booleanValue());
				ret.setUsado(((Boolean) obj[10]).booleanValue());
				ret.setIdSucursal(obj[12] == null ? null : (Integer) obj[12]);
				return ret;
			}
		};
	}

	@Override
	public List<VehiculoDTO> readVehiculosUsados() {
		return getTemplate().query(readVehiculosUsados).excecute(getMapper());
	}

	@Override
	public Integer getIdMaximo() {
		return getTemplate().query(maximoId).excecute(new Mapper<Integer>() {

			@Override
			public Integer map(Object[] obj) {
				return (Integer) obj[0];
			}
		}).get(0);
	}
}
