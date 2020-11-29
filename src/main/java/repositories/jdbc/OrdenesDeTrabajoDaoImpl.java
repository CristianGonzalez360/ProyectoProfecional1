package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.taller.OrdenDeTrabajoDTO;
import repositories.OrdenesDeTrabajoDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class OrdenesDeTrabajoDaoImpl extends GenericJdbcDao<OrdenDeTrabajoDTO> implements OrdenesDeTrabajoDao {

	private static final String readAll = "SELECT idOT, tipoTrabajo, idUsuAlta, idVehiculoOt,"
			+ "fechaAltaOt, trabajoSolicitado, trabajoSujerido, fechaEntregadoVehiculo " + "FROM OrdenesDeTrabajo";

	private static final String readByVehiculoIdNoEntregado = readAll + " "
			+ "WHERE idVehiculoOt = ? AND fechaEntregadoVehiculo IS NULL";

	private static final String insert = "INSERT INTO OrdenesDeTrabajo (tipoTrabajo, idUsuAlta, idVehiculoOt, fechaAltaOt, trabajoSolicitado, trabajoSujerido, fechaEntregadoVehiculo) "
			+ "VALUES (?,?,?,?,?,?,?)";

	private static final String readByVehiculoId = readAll + " " + "WHERE idVehiculoOt = ?";
	
	private static final String readAllOrdenesParaEntregar = "SELECT o.idOT, o.tipoTrabajo, o.idUsuAlta, o.idVehiculoOt, o.fechaAltaOt, o.trabajoSolicitado, o.trabajoSujerido, o.fechaEntregadoVehiculo "
			+ "FROM OrdenesDeTrabajo o "
			+ "WHERE o.fechaEntregadoVehiculo IS NULL ";

	private static final String readByID = "SELECT * FROM OrdenesDeTrabajo WHERE idOT = ?";
			
	private static final String update = "UPDATE OrdenesDeTrabajo SET fechaEntregadoVehiculo = ? WHERE idOT = ?";
	
	public OrdenesDeTrabajoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(OrdenDeTrabajoDTO entity) {
		return getTemplate().query(update).param(entity.getFechaEntregado() == null ? new NullObject() : entity.getFechaEntregado()).param(entity.getIdOrdenTrabajo()).excecute();
	}

	@Override
	public boolean insert(OrdenDeTrabajoDTO entity) {
		return getTemplate().query(insert).param(entity.getTipoOrdeTrabajo()).param(entity.getIdUsuarioAlta())
				.param(entity.getIdVehiculoOt())
				.param(entity.getFechaDeAlta() == null ? new NullObject() : entity.getFechaDeAlta())
				.param(entity.getTrabajoSolicitado())
				.param(entity.getTrabajoSujerido())
				.param(entity.getFechaEntregado() == null ? new NullObject() : entity.getFechaEntregado()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public OrdenDeTrabajoDTO readByID(Integer id) {
		List<OrdenDeTrabajoDTO> ordenes = getTemplate().query(readByID).param(id).excecute(getMapper());
		return ordenes.isEmpty() ? null : ordenes.get(0);
	}

	@Override
	public List<OrdenDeTrabajoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public List<OrdenDeTrabajoDTO> readByVehiculoId(Integer id) {
		return getTemplate().query(readByVehiculoId).param(id).excecute(getMapper());
	}

	@Override
	public OrdenDeTrabajoDTO readByIdVehiculoConOtNoCerrada(Integer id) {
		List<OrdenDeTrabajoDTO> dtos = getTemplate().query(readByVehiculoIdNoEntregado).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	protected Mapper<OrdenDeTrabajoDTO> getMapper() {
		return new Mapper<OrdenDeTrabajoDTO>() {

			@Override
			public OrdenDeTrabajoDTO map(Object[] obj) {
				OrdenDeTrabajoDTO ot = new OrdenDeTrabajoDTO();
				ot.setIdOrdenTrabajo((Integer) obj[0]);
				ot.setTipoOrdeTrabajo((String) obj[1]);
				ot.setIdUsuarioAlta(obj[2] == null ? null : (Integer) obj[2]);
				ot.setIdVehiculoOt((Integer) obj[3]);
				ot.setFechaDeAlta((Date) obj[4]);
				ot.setTrabajoSolicitado((String) obj[5]);
				ot.setTrabajoSujerido((String) obj[6]);
				ot.setFechaEntregado(obj[7] == null ? null : (Date) obj[7]);
				return ot;
			}
		};
	}

	@Override
	public List<OrdenDeTrabajoDTO> readAllOrdenesSinEntregar() {
		return getTemplate().query(readAllOrdenesParaEntregar).excecute(getMapper());
	}
}
