package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.taller.FichaTecnicaVehiculoDTO;
import repositories.FichaTecnicaVehiculoDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class FichaTecnicaVehiculoDaoImpl extends GenericJdbcDao<FichaTecnicaVehiculoDTO>
		implements FichaTecnicaVehiculoDao {

	private static final String insert = "INSERT INTO FichaTecnicaVehiculo (nroChasis, nroMotor, kilometraje, marca, modelo, color, combustion, descripcion, patente) "
			+ "VALUES (?,?,?,?,?,?,?,?,?)";

	private static final String readAll = "SELECT * FROM FichaTecnicaVehiculo";

	private static final String readByNroMotor = readAll + " " + "WHERE nroMotor = ?";

	private static final String readByNroChasis = readAll + " " + "WHERE nroChasis = ?";

	private static final String readById = readAll + " " + "WHERE idFichaTecnicaVehiculo = ?";

	private static final String maximoId = "SELECT MAX(idFichaTecnicaVehiculo) FROM FichaTecnicaVehiculo";

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
		return getTemplate().query(insert).param(entity.getNroChasis()).param(entity.getNroMotor())
				.param(entity.getKilometraje()).param(entity.getMarca()).param(entity.getModelo())
				.param(entity.getColor()).param(entity.getCombustion())
				.param(entity.getDescripcion() == null ? new NullObject() : entity.getDescripcion())
				.param(entity.getPatente() == null ? new NullObject() : entity.getPatente()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FichaTecnicaVehiculoDTO readByID(Integer id) {
		if(id == null) return null;
		List<FichaTecnicaVehiculoDTO> dtos = getTemplate().query(readById).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
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
	public Integer getIdMaximo() {
		return getTemplate().query(maximoId).excecute(new Mapper<Integer>() {

			@Override
			public Integer map(Object[] obj) {
				return (Integer) obj[0];
			}
		}).get(0);
	}

	@Override
	public FichaTecnicaVehiculoDTO readByNroChasis(Integer nroChasis) {
		List<FichaTecnicaVehiculoDTO> dtos = getTemplate().query(readByNroChasis).param(nroChasis)
				.excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public void updateKilometraje(Integer idVehiculo, Integer kilometrajeActual) {
		final String query = "UPDATE FichaTecnicaVehiculo SET kilometraje = ? WHERE idFichaTecnicaVehiculo = ?";
		getTemplate().query(query).param(kilometrajeActual).param(idVehiculo).excecute();
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
				dto.setCombustion((String) obj[7]);
				dto.setDescripcion((String) obj[8]);
				dto.setPatente((String) obj[9]);
				return dto;
			}
		};
	}
}
