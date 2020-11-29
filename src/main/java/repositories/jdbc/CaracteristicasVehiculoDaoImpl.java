package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.CaracteristicasVehiculoDTO;
import repositories.CaracteristicasVehiculoDao;
import repositories.jdbc.utils.Mapper;

public class CaracteristicasVehiculoDaoImpl extends GenericJdbcDao<CaracteristicasVehiculoDTO> implements CaracteristicasVehiculoDao {
	
	private static final String readAll= "SELECT * FROM CaracteristicasVehiculo";
	
	private static final String readById= readAll + " WHERE idCaracteristicas = ?";

	public CaracteristicasVehiculoDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(CaracteristicasVehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(CaracteristicasVehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CaracteristicasVehiculoDTO readByID(Integer id) {
		List<CaracteristicasVehiculoDTO> ret = getTemplate().query(readById).param(id).excecute(getMapper());
		return ret.isEmpty()? null: ret.get(0);
	}

	@Override
	public List<CaracteristicasVehiculoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<CaracteristicasVehiculoDTO> getMapper() {
		
		return new Mapper<CaracteristicasVehiculoDTO>() {
			@Override
			public CaracteristicasVehiculoDTO map(Object[] obj) {
				CaracteristicasVehiculoDTO c = new CaracteristicasVehiculoDTO();
				c.setIdCaracteristicas((Integer) obj[0]);
				c.setFamilia((String) obj[1]);
				c.setLinea((String) obj[2]);
				c.setCilindrada((Integer) obj[3]);
				c.setColor((String) obj[4]);
				c.setPrecio((Double) obj[5]);
				c.setMotor((String) obj[6]);
				c.setDireccion((String) obj[7]);
				c.setPotencia((Integer) obj[8]);
				c.setFrenosDelanteros((String) obj[9]);
				c.setFrenosTraseros((String) obj[10]);
				c.setTorqueMaximo((Double) obj[11]);
				c.setVolumenVaul((Double) obj[12]);
				c.setNumeroPuertas((Integer) obj[13]);
				return c;
			}
		};
	}

	
}
