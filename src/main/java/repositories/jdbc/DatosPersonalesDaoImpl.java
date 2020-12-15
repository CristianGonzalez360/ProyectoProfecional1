package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.DatosPersonalesDTO;
import repositories.DatosPersonalesDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class DatosPersonalesDaoImpl extends GenericJdbcDao<DatosPersonalesDTO> implements DatosPersonalesDao {

	private static final String insertDatosPersonales = "INSERT INTO DatosPersonales (nombreCompleto, apellido, dni, telefono, email, calle, altura,piso, dpto, localidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String update = "UPDATE DatosPersonales SET nombreCompleto = ?, apellido = ?, dni = ?, telefono = ?, email = ?, calle = ?, altura = ?,piso = ?, dpto = ?, localidad = ? WHERE idDatosPersonales = ?";

	private static final String readAll = "SELECT * FROM DatosPersonales";

	private static final String readByTelefono = "SELECT * FROM DatosPersonales WHERE DatosPersonales.telefono = ?";

	private static final String readByDni = readAll + " wHERE DatosPersonales.dni = ?";

	private static final String readAllId = readAll + " WHERE DatosPersonales.idDatosPersonales = ?";

	public DatosPersonalesDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean insert(DatosPersonalesDTO entity) {
		return getTemplate().query(insertDatosPersonales).param(entity.getNombreCompleto()).param(entity.getApellido())
				.param(entity.getDni()).param(entity.getTelefono()).param(entity.getEmail()).param(entity.getCalle())
				.param(entity.getAltura()).param(entity.getPiso()).param(entity.getDpto()).param(entity.getLocalidad())
				.excecute();
	}

	@Override
	public boolean update(DatosPersonalesDTO entity) {
		return getTemplate().query(update).param(entity.getNombreCompleto()).param(entity.getApellido())
				.param(entity.getDni() == null ? new NullObject() : entity.getDni()).param(entity.getTelefono())
				.param(entity.getEmail()).param(entity.getCalle())
				.param(entity.getAltura() == null ? new NullObject() : entity.getAltura())
				.param(entity.getPiso() == null ? new NullObject() : entity.getPiso()).param(entity.getDpto())
				.param(entity.getLocalidad()).param(entity.getId()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public DatosPersonalesDTO readByTelefono(String telefono) {
		List<DatosPersonalesDTO> dtos = getTemplate().query(readByTelefono).param(telefono).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public DatosPersonalesDTO readByEmail(String email) {
		List<DatosPersonalesDTO> dtos = getTemplate().query(readAll + " WHERE email = ?").param(email)
				.excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public DatosPersonalesDTO readByID(Integer id) {
		List<DatosPersonalesDTO> dtos = getTemplate().query(readAllId).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
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
				dper.setApellido((String) obj[2]);
				dper.setDni((Integer) obj[3]);
				dper.setTelefono((String) obj[4]);
				dper.setEmail((String) obj[5]);
				dper.setCalle((String) obj[6]);
				dper.setAltura((String) obj[7]);
				dper.setPiso((String) obj[8]);
				dper.setDpto((String) obj[9]);
				dper.setLocalidad((String) obj[10]);
				return dper;
			}
		};
	}
}
