package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.TurnoDTO;
import repositories.TurnosDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class TurnosDaoImpl extends GenericJdbcDao<TurnoDTO> implements TurnosDao {
	
	private static final String update = "UPDATE Turnos SET fechaCanceladoTurno = ? WHERE idTurno = ?";

	private static final String readAll = "SELECT * FROM Turnos";
	
	private static final String readByID = "SELECT * FROM Turnos WHERE Turnos.idTurno = ?";

	private static final String readByDni = "SELECT * FROM Turnos WHERE Turnos.dniCliente = ?";

	private static final String insert = "INSERT INTO Turnos (idCliente, fechaCanceladoTurno, fechaAltaTurno, fechProgramadaTurno, nombreCliente, dniCliente, telefonoCliente, emailCliente) VALUES (?,?,?,?,?,?,?,?)";

	public TurnosDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(TurnoDTO entity) {
		/*
		 * Update Cancellation Date
		 */
		return getTemplate().query(update)
				.param(entity.getFechaCancelado())
				.param(entity.getIdTurno())
				.excecute();
	}

	@Override
	public boolean insert(TurnoDTO entity) {
		return getTemplate().query(insert)
				.param(entity.getIdCliente())
				.param(entity.getFechaCancelado() == null ? new NullObject() : entity.getFechaCancelado())
				.param(entity.getFechaAlta())
				.param(entity.getFechaProgramada())
				.param(entity.getNombreCliente())
				.param(entity.getDniCliente())
				.param(entity.getTelefonoCliente())
				.param(entity.getEmailCliente())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TurnoDTO readByID(Integer dni) {
		List<TurnoDTO> turnos = getTemplate().query(readByDni).param(dni).excecute(getMapper());
		return turnos.isEmpty() ? null : turnos.get(0);
	}

	@Override
	public List<TurnoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public TurnoDTO readByDni(Integer dni) {
		List<TurnoDTO> dtos = getTemplate().query(readByDni).param(dni).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	public TurnoDTO readByIdTurno(Integer id) {
		List<TurnoDTO> dtos = getTemplate().query(readByID).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}
	
	@Override
	protected Mapper<TurnoDTO> getMapper() {
		return new Mapper<TurnoDTO>() {

			@Override
			public TurnoDTO map(Object[] obj) {
				TurnoDTO turno = new TurnoDTO();
				turno.setIdTurno((Integer) obj[0]);
				turno.setIdCliente((obj[1] != null ? (Integer) obj[1] : null));
				turno.setFechaCancelado(obj[2] == null ? null : (Date) obj[2]);
				turno.setFechaAlta((Date) obj[3]);
				turno.setFechaProgramada((Date) obj[4]);
				turno.setNombreCliente((String) obj[5]);
				turno.setDniCliente((Integer) obj[6]);
				turno.setTelefonoCliente((String) obj[7]);
				turno.setEmailCliente((String) obj[8]);
				return turno;
			}
		};
	}
}
