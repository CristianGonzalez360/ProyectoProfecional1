package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.EstadoPresupuesto;
import dto.PresupuestoDTO;
import repositories.PresupuestosDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class PresupuestosDaoImpl extends GenericJdbcDao<PresupuestoDTO> implements PresupuestosDao {
	
	private static final String readAll = "SELECT * FROM Presupuestos";

	private static final String readById = readAll + " " + "WHERE Presupuestos.idPresupuesto = ?";

	private static final String readByOrdenDeTrabajoId = readAll + " " + "WHERE Presupuestos.idOT = ?";

	private static final String insert = "INSERT INTO Presupuestos (idOT, idUsuAltaPresu, idUsuCierrePresu, idUsuRegPago"
			+ ",idPago,fechaAltaPresu,comentarioAltaPresu,fechaCierrePresu,comentarioRechazo,fechaAprobacion,estado) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

	private static final String updateState = "UPDATE Presupuestos SET estado = ?, fechaAprobacion = ? WHERE idPresupuesto = ?";
	
	private static final String updateStateForPayment = "UPDATE Presupuestos SET estado = ? WHERE idPresupuesto = ?";
	
	private static final String delete = "DELETE FROM Presupuestos WHERE idPresupuesto = ?";
	
	private static final String update = "UPDATE Presupuestos SET comentarioAltaPresu = ?, idFactura = ? WHERE idPresupuesto = ?";
	
	private static final String readByIdFactura = readAll + " " + "WHERE Presupuestos.idFactura = ?";
	
	private static final String rechazo = "UPDATE Presupuestos SET estado = ?, fechaAprobacion = ?, comentarioRechazo = ? WHERE idPresupuesto = ?";
		
	public PresupuestosDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean updateStateById(Integer id, Date aprovedDate, EstadoPresupuesto estado) {
			return getTemplate().query(updateState).param(estado.name()).param(aprovedDate).param(id).excecute();
	}
	
	@Override
	public boolean updateState(Integer id, EstadoPresupuesto estado) {
		return getTemplate().query(updateStateForPayment).param(estado.name()).param(id).excecute();
	}
	
	
	@Override
	public boolean update(PresupuestoDTO entity) {
		return getTemplate().query(update).param(entity.getComentarioAltaPresu())
				.param(entity.getIdFactura() == null? new NullObject() : entity.getIdFactura())
				.param(entity.getIdPresupuesto()).excecute();
	}

	@Override
	public boolean insert(PresupuestoDTO entity) {
		return getTemplate().query(insert).param(entity.getIdOT())
				.param(entity.getIdUsuAltaPresu() == null ? new NullObject() : entity.getIdUsuAltaPresu())
				.param(entity.getIdUsuCierrePresu() == null ? new NullObject() : entity.getIdUsuCierrePresu())
				.param(entity.getIdUsuRegPago() == null ? new NullObject() : entity.getIdUsuRegPago())
				.param(entity.getIdPago() == null ? new NullObject() : entity.getIdPago())
				.param(entity.getFechaAltaPresu() == null ? new NullObject() : entity.getFechaAltaPresu())
				.param(entity.getComentarioAltaPresu() == null ? new NullObject() : entity.getComentarioAltaPresu())
				.param(entity.getFechaCierrePresu() == null ? new NullObject() : entity.getFechaCierrePresu())
				.param(entity.getComentarioRechazo() == null ? new NullObject() : entity.getComentarioRechazo())
				.param(entity.getFechaAprobacion() == null ? new NullObject() : entity.getFechaAprobacion())
				.param(entity.getEstado().name())				
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public PresupuestoDTO readByID(Integer id) {
		List<PresupuestoDTO> dtos = getTemplate().query(readById).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<PresupuestoDTO> readByOrdenDeTrabajoId(Integer id) {
		return getTemplate().query(readByOrdenDeTrabajoId).param(id).excecute(getMapper());
	}

	@Override
	public List<PresupuestoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	protected Mapper<PresupuestoDTO> getMapper() {
		return new Mapper<PresupuestoDTO>() {
		
			@Override
			public PresupuestoDTO map(Object[] obj) {
				PresupuestoDTO dto = new PresupuestoDTO();
				dto.setIdPresupuesto((Integer)obj[0]);
				dto.setIdOT((Integer) obj[1]);
				dto.setIdFactura(obj[2] == null ? null :(Integer) obj[2]);
				dto.setIdUsuAltaPresu(obj[3] == null ? null : (Integer) obj[3]);
				dto.setIdUsuCierrePresu(obj[4] == null ? null : (Integer) obj[4]);
				dto.setIdUsuRegPago(obj[5] == null ? null : (Integer) obj[5]);
				dto.setIdPago(obj[6] == null ? null : (Integer) obj[6]);
				dto.setFechaAltaPresu(obj[7] == null ? null : (Date) obj[7]);
				dto.setComentarioAltaPresu(obj[8] == null ? null : (String) obj[8]);
				dto.setFechaCierrePresu(obj[9] == null ? null : (Date) obj[9]);
				dto.setComentarioRechazo(obj[10] == null ? "" : (String) obj[10]);
				dto.setFechaAprobacion(obj[11] == null ? null : (Date) obj[11]);
				dto.setEstado((Enum.valueOf(EstadoPresupuesto.class, (String) obj[12])));
				return dto;
			}
		};
	}

	@Override
	public boolean delete(Integer id) {
		return getTemplate().query(delete).param(id).excecute();
	}

	@Override
	public List<PresupuestoDTO> readByFacturaId(Integer idFactura) {
		return getTemplate().query(readByIdFactura).param(idFactura).excecute(getMapper());
	}

	@Override
	public boolean registrarAprobacion(PresupuestoDTO presupuesto) {
		return getTemplate().query(rechazo).param(presupuesto.getEstado().name())
				.param(presupuesto.getFechaAprobacion())
				.param(presupuesto.getComentarioRechazo() == null? new NullObject() : presupuesto.getComentarioRechazo())
				.param(presupuesto.getIdPresupuesto())
				.excecute();
	}
}
