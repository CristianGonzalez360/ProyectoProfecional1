package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.VehiculoConOrdenDeTrabajoDTO;
import repositories.VehiculosConOrdenDeTrabajoDao;
import repositories.jdbc.utils.Mapper;

public class VehiculosConOrdenDeTrabajoDaoImpl extends GenericJdbcDao<VehiculoConOrdenDeTrabajoDTO>
		implements VehiculosConOrdenDeTrabajoDao {

	private static final String insert = "INSERT INTO VehiculoConOrdenesDeTrabajo (idFichaTecnicaVehiculo,idCliente,kilometrajeGarantia,aseguradora,nroPolizaSeguro,patenteVehiculo) VALUES (?,?,?,?,?,?)";

	private static final String readAll = "SELECT * FROM VehiculoConOrdenesDeTrabajo";

	private static final String readByPatente = readAll + " " + "WHERE patenteVehiculo = ?";

	private static final String readByClienteId = readAll + " " + "WHERE idCliente = ?";
	
	private static final String readAllId = "SELECT * FROM VehiculoConOrdenesDeTrabajo WHERE idVehiculoConOT = ?";

	public VehiculosConOrdenDeTrabajoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(VehiculoConOrdenDeTrabajoDTO entity) {
		return false;
	}

	@Override
	public boolean insert(VehiculoConOrdenDeTrabajoDTO entity) {
		return getTemplate().query(insert).param(entity.getIdFichaTecnica()).param(entity.getIdCliente())
				.param(entity.getKilometrajeGarantia()).param(entity.getAseguradora())
				.param(entity.getNroPolizaSeguro()).param(entity.getPatente()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public VehiculoConOrdenDeTrabajoDTO readByID(Integer id) {
		List<VehiculoConOrdenDeTrabajoDTO> dtos = getTemplate().query(readAllId).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<VehiculoConOrdenDeTrabajoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public VehiculoConOrdenDeTrabajoDTO readByPatente(String patente) {
		List<VehiculoConOrdenDeTrabajoDTO> dtos = getTemplate().query(readByPatente).param(patente)
				.excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<VehiculoConOrdenDeTrabajoDTO> readByClienteId(Integer idCliente) {
		return getTemplate().query(readByClienteId).param(idCliente).excecute(getMapper());
	}

	@Override
	protected Mapper<VehiculoConOrdenDeTrabajoDTO> getMapper() {

		return new Mapper<VehiculoConOrdenDeTrabajoDTO>() {
			@Override
			public VehiculoConOrdenDeTrabajoDTO map(Object[] obj) {
				VehiculoConOrdenDeTrabajoDTO dto = new VehiculoConOrdenDeTrabajoDTO();
				dto.setId((Integer) obj[0]);
				dto.setIdFichaTecnica((Integer) obj[1]);
				dto.setIdCliente((Integer) obj[2]);
				dto.setKilometrajeGarantia((Integer) obj[3]);
				dto.setAseguradora((String) obj[4]);
				dto.setNroPolizaSeguro((Integer) obj[5]);
				dto.setPatente((String) obj[6]);
				return dto;
			}
		};
	}
}
