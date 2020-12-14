package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.GarantiaVehiculoDTO;
import repositories.GarantiasDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class GarantiasDaoImpl extends GenericJdbcDao<GarantiaVehiculoDTO> implements GarantiasDao {

	private static final String insert = "INSERT INTO  GarantiasVehiculos(idVehiculo, aniosDeGarantia,kilometrajeInicialDelVehiculo,kilometrajeGarantizado"
			+ ",fechaInicioDeLaGarantia, fechaDeCaducidadDeLaGarantia,costoFinalConIVA) VALUES (?,?,?,?,?,?,?)";

	private static final String readByIdVehiculo = "SELECT * FROM GarantiasVehiculos WHERE GarantiasVehiculos.idVehiculo = ?";

	public GarantiasDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(GarantiaVehiculoDTO entity) {
		return false;
	}

	@Override
	public boolean insert(GarantiaVehiculoDTO entity) {
		return getTemplate().query(insert).param(entity.getIdVehiculo())
				.param(entity.getAniosDeGarantia())
				.param(entity.getKilometrajeInicialDelVehiculo())
				.param(entity.getKilometrajeGarantizado() == null? new NullObject() : entity.getKilometrajeGarantizado())
				.param(entity.getFechaInicioDeLaGarantia() == null? new NullObject() : entity.getFechaInicioDeLaGarantia())
				.param(entity.getFechaDeCaducidadDeLaGarantia() == null? new NullObject() : entity.getFechaDeCaducidadDeLaGarantia())
				.param(entity.getCostoFinalConIVA() == null? new NullObject() : entity.getCostoFinalConIVA())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public GarantiaVehiculoDTO readByID(Integer id) {
		return null;
	}

	@Override
	public GarantiaVehiculoDTO readByIdVehiculo(Integer id) {
		List<GarantiaVehiculoDTO> target = getTemplate().query(readByIdVehiculo).param(id).excecute(getMapper());
		return target.isEmpty() ? null : target.get(0);
	}

	@Override
	public List<GarantiaVehiculoDTO> readAll() {

		return null;
	}

	@Override
	protected Mapper<GarantiaVehiculoDTO> getMapper() {

		return new Mapper<GarantiaVehiculoDTO>() {

			@Override
			public GarantiaVehiculoDTO map(Object[] obj) {
				GarantiaVehiculoDTO ret = new GarantiaVehiculoDTO();
				ret.setId((Integer) obj[0]);
				ret.setIdVehiculo((Integer) obj[1]);
				ret.setAniosDeGarantia((Integer) obj[2]);
				ret.setKilometrajeInicialDelVehiculo((Integer) obj[3]);
				ret.setKilometrajeGarantizado((Integer) obj[4]);
				ret.setFechaInicioDeLaGarantia((Date) obj[5]);
				ret.setFechaDeCaducidadDeLaGarantia((Date) obj[6]);
				ret.setCostoFinalConIVA((Double) obj[7]);
				return ret;
			}
		};
	}
}
