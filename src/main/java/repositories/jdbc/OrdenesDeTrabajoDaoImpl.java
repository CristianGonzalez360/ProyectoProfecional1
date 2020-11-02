package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.OrdenDeTrabajoDTO;
import dto.UsuarioDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import repositories.OrdenesDeTrabajoDao;
import repositories.jdbc.utils.Mapper;

public class OrdenesDeTrabajoDaoImpl extends GenericJdbcDao<OrdenDeTrabajoDTO> implements OrdenesDeTrabajoDao {

	private static final String readAll = 
			"SELECT idOT, TipoTrabajo.descripcionTrabajo, idUsuAlta, idVehiculoOt," + 
			"fechaAltaOt, trabajoSolicitado, trabajoSujerido, fechaEntregadoVehiculo, idFichaTecnicaVehiculo, idCliente, kilometrajeGarantia, aseguradora, nroPolizaSeguro, patenteVehiculo," +
			"FROM OrdenesDeTrabajo INNER JOIN TipoTrabajo ON OrdenesDeTrabajo.idTipoOt = TipoTrabajo.idTipoTrabajo\n" + 
			"INNER JOIN VehiculoConOrdenesDeTrabajo ON OrdenesDeTrabajo.idVehiculoOt = VehiculoConOrdenesDeTrabajo.idVehiculoConOT";
	
	private static final String insert = 
			"INSERT INTO OrdenesDeTrabajo (idUsuAlta, idVehiculoOt, fechaAltaOt, trabajoSolicitado, trabajoSujerido, fechaEntregadoVehiculo) VALUES (?,?,?,?,?,?)";
	
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
				.param(entity.getIdUsuarioAlta().getId())
				.param(entity.getIdVehiculoConOrdenTrabajo().getId())
				.param(entity.getFechaDeAlta())
				.param(entity.getTrabajoSolicitado())
				.param(entity.getTrabajoSujerido())
				.param(entity.getFechaEntregado())
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
				UsuarioDTO usuarioAlta = new UsuarioDTO();
				usuarioAlta.setId((Integer)obj[2]);
				VehiculoConOrdenDeTrabajoDTO vehiculo = new VehiculoConOrdenDeTrabajoDTO();
				vehiculo.setId((Integer)obj[3]);
				ot.setFechaDeAlta((Date)obj[4]);
				ot.setTrabajoSolicitado((String)obj[5]);
				ot.setTrabajoSujerido((String)obj[6]);
				ot.setFechaEntregado(obj[7] == null ? null : (Date)obj[7]);
				vehiculo.setIdFichaTecnica((Integer)obj[8]);
				vehiculo.setIdCliente((Integer)obj[9]);
				vehiculo.setKilometrajeGarantia((Integer)obj[10]);
				vehiculo.setAseguradora((String)obj[11]);
				vehiculo.setNroPolizaSeguro((Integer)obj[12]);
				vehiculo.setPatente((String)obj[13]);
				ot.setVehiculo(vehiculo);
				return ot;
			}
		};
	}
}
