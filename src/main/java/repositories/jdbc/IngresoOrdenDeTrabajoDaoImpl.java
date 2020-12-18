package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.taller.IngresoOrdenDeTrabajoDTO;
import dto.taller.TrabajoPresupuestadoDTO;
import repositories.IngresoOrdenDeTrabajoDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class IngresoOrdenDeTrabajoDaoImpl extends GenericJdbcDao<IngresoOrdenDeTrabajoDTO>
		implements IngresoOrdenDeTrabajoDao {

	private static final String insert = "INSERT INTO VehiculoConOrdenesDeTrabajo (idFichaTecnicaVehiculo,idCliente,kilometrajeGarantia,aseguradora,nroPolizaSeguro,patenteVehiculo,idVehiculo) VALUES (?,?,?,?,?,?,?)";

	private static final String readAll = "SELECT * FROM VehiculoConOrdenesDeTrabajo";

	private static final String readByPatente = readAll + " " + "WHERE patenteVehiculo = ?";

	private static final String readByClienteId = readAll + " " + "WHERE idCliente = ?";

	private static final String readAllId = "SELECT * FROM VehiculoConOrdenesDeTrabajo WHERE idVehiculoConOT = ?";
	
	private static final String updateById = "UPDATE VehiculoConOrdenesDeTrabajo SET aseguradora = ? , nroPolizaSeguro = ? , kilometrajeGarantia = ? WHERE idCliente = ? AND idVehiculo = ?";//"UPDATE TrabajosPresupuestados SET descripcionTrabajo = ?, precioTrabajo = ?, tiempoEstTrabajo = ? WHERE idTrabajoPresu = ?";

	public IngresoOrdenDeTrabajoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(IngresoOrdenDeTrabajoDTO entity) {
		return false;
	}

	@Override
	public boolean insert(IngresoOrdenDeTrabajoDTO entity) {
		return getTemplate().query(insert)
				.param(entity.getIdFichaTecnica() == null ? new NullObject() : entity.getIdFichaTecnica())
				.param(entity.getIdCliente())
				.param(entity.getKilometrajeGarantia() == null ? new NullObject() : entity.getKilometrajeGarantia())
				.param(entity.getAseguradora() == null ? new NullObject() : entity.getAseguradora())
				.param(entity.getNroPolizaSeguro() == null ? new NullObject() : entity.getNroPolizaSeguro())
				.param(entity.getPatente() == null ? new NullObject() : entity.getPatente())
				.param(entity.getIdVehiculo() == null ? new NullObject() : entity.getIdVehiculo()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public IngresoOrdenDeTrabajoDTO readByID(Integer id) {
		List<IngresoOrdenDeTrabajoDTO> dtos = getTemplate().query(readAllId).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<IngresoOrdenDeTrabajoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public IngresoOrdenDeTrabajoDTO readByPatente(String patente) {
		List<IngresoOrdenDeTrabajoDTO> dtos = getTemplate().query(readByPatente).param(patente)
				.excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<IngresoOrdenDeTrabajoDTO> readByClienteId(Integer idCliente) {
		return getTemplate().query(readByClienteId).param(idCliente).excecute(getMapper());
	}

	@Override
	protected Mapper<IngresoOrdenDeTrabajoDTO> getMapper() {

		return new Mapper<IngresoOrdenDeTrabajoDTO>() {
			@Override
			public IngresoOrdenDeTrabajoDTO map(Object[] obj) {
				IngresoOrdenDeTrabajoDTO dto = new IngresoOrdenDeTrabajoDTO();
				dto.setId((Integer) obj[0]);
				dto.setIdFichaTecnica((Integer) obj[1]);
				dto.setIdCliente((Integer) obj[2]);
				dto.setKilometrajeGarantia((Integer) obj[3]);
				dto.setAseguradora((String) obj[4]);
				dto.setNroPolizaSeguro((Integer) obj[5]);
				dto.setPatente((String) obj[6]);
				dto.setIdVehiculo((Integer) obj[7]);
				return dto;
			}
		};
	}
	
	@Override
	public boolean updateById(IngresoOrdenDeTrabajoDTO entity) {
		return getTemplate().query(updateById).param(entity.getAseguradora()).param(entity.getNroPolizaSeguro())
				.param(entity.getKilometrajeGarantia()).param(entity.getIdCliente()).param(entity.getIdVehiculo()).excecute();
	}
}
