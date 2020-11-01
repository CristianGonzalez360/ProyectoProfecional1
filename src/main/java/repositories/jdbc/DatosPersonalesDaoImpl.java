package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.DatosPersonalesDTO;
import repositories.DatosPersonalesDao;
import repositories.jdbc.utils.Mapper;

public class DatosPersonalesDaoImpl extends GenericJdbcDao<DatosPersonalesDTO> implements DatosPersonalesDao {

	private static final String insertDatosPersonales = "INSERT INTO DatosPersonales (nombreCompleto, dni, telefono, email, calle, altura,piso, dpto, localidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String readAll = "SELECT * FROM DatosPersonales";
	
	private static final String readByDni = readAll + " wHERE DatosPersonales.dni = ?";
		
	public DatosPersonalesDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean insert(DatosPersonalesDTO entity) {
		return getTemplate()
			.query(insertDatosPersonales)
			.param(entity.getNombreCompleto())
			.param(entity.getDni())
			.param(entity.getTelefono())
			.param(entity.getEmail())
			.param(entity.getCalle())
			.param(entity.getAltura())
			.param(entity.getPiso())
			.param(entity.getDpto())
			.param(entity.getLocalidad())
			.excecute();
	}

	@Override
	public boolean update(DatosPersonalesDTO entity) {
		return false;
	}
	
	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public DatosPersonalesDTO readByID(Integer id) {
		return null;
	}

	@Override
	public List<DatosPersonalesDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public DatosPersonalesDTO readByDni(Integer dni) {
		List<DatosPersonalesDTO> dtos = getTemplate().query(readByDni).param(dni).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}
	
	@Override
	protected Mapper<DatosPersonalesDTO> getMapper() {
		return new Mapper<DatosPersonalesDTO>() {
			
			@Override
			public DatosPersonalesDTO map(Object[] obj) {
				DatosPersonalesDTO dper = new DatosPersonalesDTO();
						dper.setId((Integer) obj[0]);
						dper.setNombreCompleto((String) obj[1]);
						dper.setDni((Integer) obj[2]);
						dper.setTelefono((String) obj[3]);
						dper.setEmail((String) obj[4]);
						dper.setCalle((String) obj[5]);
						dper.setAltura((Integer) obj[6]);
						dper.setPiso((Integer) obj[7]);
						dper.setDpto((String) obj[8]);
						dper.setLocalidad((String) obj[9]);
				return dper;
			}			
		};
	}
}
