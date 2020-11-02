package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.FichaTecnicaVehiculoDTO;
import repositories.FichaTecnicaVehiculoDao;
import repositories.jdbc.utils.Mapper;

public class FichaTecnicaVehiculoDaoImpl extends GenericJdbcDao<FichaTecnicaVehiculoDTO> implements FichaTecnicaVehiculoDao {

	private static final String insert = "INSERT INTO FichaTecnicaVehiculo "
			+ "(nroChasis, nroMotor, kilometraje, marca, modelo, color, combustion, descripcion) VALUES (?,?,?,?,?,?,?,?)";
	
	private static final String readAll = "SELECT * FROM FichaTecnicaVehiculo";

	private static final String readByNroMotor = readAll + " " + "WHERE nroMotor = ?";
	
	public FichaTecnicaVehiculoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(FichaTecnicaVehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(FichaTecnicaVehiculoDTO entity) {
		return getTemplate()
				.query(insert)
				.param(entity.getNroChasis())
				.param(entity.getNroMotor())
				.param(entity.getKilometraje())
				.param(entity.getMarca())
				.param(entity.getModelo())
				.param(entity.getColor())
				.param(entity.getCombustion())
				.param(entity.getDescripcion())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FichaTecnicaVehiculoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FichaTecnicaVehiculoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}
	
	@Override
	public FichaTecnicaVehiculoDTO readByNroMotor(Integer nroMotor) {
		List<FichaTecnicaVehiculoDTO> dtos = getTemplate().query(readByNroMotor).param(nroMotor).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	protected Mapper<FichaTecnicaVehiculoDTO> getMapper() {
		// TODO Auto-generated method stub
		return new Mapper<FichaTecnicaVehiculoDTO>() {

			@Override
			public FichaTecnicaVehiculoDTO map(Object[] obj) {
				FichaTecnicaVehiculoDTO dto = new FichaTecnicaVehiculoDTO();
				dto.setId((Integer) obj[0]);
				dto.setNroChasis((Integer) obj[1]);
				dto.setNroMotor((Integer) obj[2]);
				dto.setKilometraje((Integer) obj[3]);
				dto.setMarca((String) obj[4]);
				dto.setModelo((Integer) obj[5]);
				dto.setColor((String) obj[6]);
				dto.setDescripcion((String) obj[7]);
				return dto;
			}
		};
	}
}
