package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.CaracteristicaVehiculoDTO;
import repositories.CaracteristicasVehiculoDao;
import repositories.jdbc.utils.Mapper;

public class CaracteristicasVehiculoDaoImpl extends GenericJdbcDao<CaracteristicaVehiculoDTO> implements CaracteristicasVehiculoDao {

	private static final String insert = "INSERT INTO CaracteristicasVehiculo(cilindrada, motor,direccion,potencia,frenosDelanteros"
			+ ",transmision,frenosTraseros,torqueMaximo,volumenDeBaul,nroDePuertas, precio) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	
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
		return null;
	}

	@Override
	public List<CaracteristicaVehiculoDTO> readAll() {
		return null;
	}

	@Override
	protected Mapper<CaracteristicaVehiculoDTO> getMapper() {
		return null;
	}
}
