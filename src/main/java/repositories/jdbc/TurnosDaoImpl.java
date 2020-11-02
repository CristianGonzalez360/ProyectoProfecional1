package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.TurnoDTO;
import repositories.TurnosDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class TurnosDaoImpl extends GenericJdbcDao<TurnoDTO> implements TurnosDao {

	private static final String readAll = "SELECT * FROM Turnos";
	
	private static final String readByDni = "SELECT * FROM Turnos WHERE Turnos.dniCliente = ?";

	private static final String insert = "INSERT INTO Turnos (idCliente, fechaCanceladoTurno,fechaAltaTurno,fechaProgramadaTurno,nombreCliente,dniCliente) VALUES (?,?,?,?,?,?)";
	
	public TurnosDaoImpl(Connection connection) {
		super(connection);
	}	

	@Override
	public boolean update(TurnoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(TurnoDTO entity) {
		return getTemplate()
				.query(insert)
				.param(entity.getIdCliente())
				.param(entity.getFechaCancelado() == null ? new NullObject() : entity.getFechaCancelado())
				.param(entity.getFechaAlta())
				.param(entity.getFechaProgramada())
				.param(entity.getNombreCliente())
				.param(entity.getDniCliente())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TurnoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	protected Mapper<TurnoDTO> getMapper() {
		return new Mapper<TurnoDTO>() {
			
			@Override
			public TurnoDTO map(Object[] obj) {
				TurnoDTO turno = new TurnoDTO();
				turno.setIdTurno((Integer)obj[0]);
				turno.setFechaCancelado((Date)obj[1]);
				turno.setFechaAlta((Date)obj[2]);
				turno.setFechaProgramada((Date)obj[3]);
				turno.setNombreCliente((String)obj[4]);
				turno.setDniCliente((Integer)obj[5]);
				return turno;
			}			
		};
	}
}
