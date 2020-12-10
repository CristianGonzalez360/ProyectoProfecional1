package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.CaracteristicaVehiculoDTO;
import repositories.CaracteristicasVehiculoDao;
import repositories.jdbc.utils.Mapper;

public class CaracteristicasVehiculoDaoImpl extends GenericJdbcDao<CaracteristicaVehiculoDTO> implements CaracteristicasVehiculoDao {

	private static final String insert = 
			"INSERT INTO CaracteristicasVehiculo(cilindrada, motor,direccion,potencia,frenosDelanteros,transmision,frenosTraseros,torqueMaximo,volumenDeBaul,nroDePuertas, precio) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String readById = "SELECT * FROM CaracteristicasVehiculo WHERE idCaracteristica = ?";

	private static final String maximoId = "SELECT MAX(idCaracteristica) FROM CaracteristicasVehiculo";
	
	public CaracteristicasVehiculoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(CaracteristicaVehiculoDTO entity) {
		return false;
	}

	@Override
	public boolean insert(CaracteristicaVehiculoDTO entity) {
		return getTemplate().query(insert)
				.param(entity.getCilindrada())
				.param(entity.getMotor())
				.param(entity.getDireccion())
				.param(entity.getPotencia())
				.param(entity.getFrenosDelanteros())
				.param(entity.getTransmision())
				.param(entity.getFrenosTraseros())
				.param(entity.getTorqueMaximo())
				.param(entity.getVolumenBaul())
				.param(entity.getCantidadPuertas())
				.param(entity.getPrecio())
				.excecute();
	}
	
	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public CaracteristicaVehiculoDTO readByID(Integer id) {
		List<CaracteristicaVehiculoDTO> dtos = getTemplate().query(readById).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<CaracteristicaVehiculoDTO> readAll() {
		return null;
	}

	@Override
	protected Mapper<CaracteristicaVehiculoDTO> getMapper() {
		return new Mapper<CaracteristicaVehiculoDTO>() {
		
			@Override
			public CaracteristicaVehiculoDTO map(Object[] obj) {
				CaracteristicaVehiculoDTO ret = new CaracteristicaVehiculoDTO();
				
				ret.setIdCaracteristica((Integer)obj[0]);
				ret.setCilindrada((String)obj[1]);
				ret.setMotor((String)obj[2]);
				ret.setDireccion((String)obj[3]);
				ret.setPotencia((String)obj[4]);
				ret.setFrenosDelanteros((String)obj[5]);
				ret.setTransmision((String)obj[6]);
				ret.setFrenosTraseros((String)obj[7]);
				ret.setTorqueMaximo((String)obj[8]);
				ret.setVolumenBaul((String)obj[9]);
				ret.setCantidadPuertas((String)obj[10]);
				ret.setPrecio((String)obj[11]);
				return ret;
			}			
		};
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