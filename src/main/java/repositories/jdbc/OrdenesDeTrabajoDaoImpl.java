package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.OrdenDeTrabajoDTO;
import repositories.OrdenesDeTrabajoDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class OrdenesDeTrabajoDaoImpl extends GenericJdbcDao<OrdenDeTrabajoDTO> implements OrdenesDeTrabajoDao {

	private static final String readAll = 
			"SELECT idOT, tipoTrabajo, idUsuAlta, idVehiculoOt," + 
			"fechaAltaOt, trabajoSolicitado, trabajoSujerido, fechaEntregadoVehiculo " +
			"FROM OrdenesDeTrabajo";
	
	private static final String insert = 
			"INSERT INTO OrdenesDeTrabajo (tipoTrabajo, idUsuAlta, idVehiculoOt, fechaAltaOt, trabajoSolicitado, trabajoSujerido, fechaEntregadoVehiculo) VALUES (?,?,?,?,?,?,?)";
	
	public OrdenesDeTrabajoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(OrdenDeTrabajoDTO entity) {
		return false;
	}

	@Override
	public boolean insert(OrdenDeTrabajoDTO entity) {
		return getTemplate()
				.query(insert)
				.param(entity.getTipoOrdeTrabajo())
				.param(entity.getIdUsuarioAlta())
				.param(entity.getIdVehiculoOt())
				.param(entity.getFechaDeAlta())
				.param(entity.getTrabajoSolicitado())
				.param(entity.getTrabajoSujerido())
				.param(entity.getFechaEntregado() == null ? new NullObject() : entity.getFechaEntregado())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OrdenDeTrabajoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdenDeTrabajoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	protected Mapper<OrdenDeTrabajoDTO> getMapper() {
		return new Mapper<OrdenDeTrabajoDTO>() {

			@Override
			public OrdenDeTrabajoDTO map(Object[] obj) {
				OrdenDeTrabajoDTO ot = new OrdenDeTrabajoDTO();
				ot.setIdOrdenTrabajo((Integer)obj[0]);
				ot.setTipoOrdeTrabajo((String)obj[1]);
				ot.setIdUsuarioAlta((Integer)obj[2]);
				ot.setIdVehiculoOt((Integer)obj[3]);
				ot.setFechaDeAlta((Date)obj[4]);
				ot.setTrabajoSolicitado((String)obj[5]);
				ot.setTrabajoSujerido((String)obj[6]);
				ot.setFechaEntregado(obj[7] == null ? null : (Date)obj[7]);
				return ot;
			}
		};
	}
}
