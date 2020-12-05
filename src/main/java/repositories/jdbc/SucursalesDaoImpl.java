package repositories.jdbc;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import dto.SucursalDTO;
import repositories.SucursalesDao;
import repositories.jdbc.utils.Mapper;

public class SucursalesDaoImpl extends GenericJdbcDao<SucursalDTO> implements SucursalesDao {

	private static final String insert = "INSERT INTO sucursal(pais, calle, altura, localidad, idMoneda) VALUES(?,?,?,?,?)";

	private static final String readByPais = "SELECT * FROM sucursal WHERE pais = ?";

	private static final String readByID = "SELECT * FROM sucursal WHERE idSucursal = ?";

	public SucursalesDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(SucursalDTO entity) {
		return false;
	}

	@Override
	public boolean insert(SucursalDTO entity) {
		return getTemplate().query(insert).param(entity.getPais()).param(entity.getCalle()).param(entity.getAltura())
				.param(entity.getLocalidad()).param(entity.getIdMoneda()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public SucursalDTO readByID(Integer id) {
		List<SucursalDTO> sucursales = getTemplate().query(readByID).param(id).excecute(getMapper());
		return sucursales.isEmpty() ? null : sucursales.get(0);
	}

	@Override
	public List<SucursalDTO> readAll() {
		return new LinkedList<>();
	}

	@Override
	public List<SucursalDTO> readByPais(String pais) {
		return getTemplate().query(readByPais).param(pais).excecute(getMapper());
	}

	@Override
	public List<String> readFinancierasByPais(String pais) {
		LinkedList<String> mock = new LinkedList<>();
		mock.add("ICBC");
		mock.add("Santander");
		mock.add("CityGroup");
		return mock;
	}

	@Override
	public SucursalDTO readByName(String name) {
		return null;
	}

	@Override
	protected Mapper<SucursalDTO> getMapper() {
		return new Mapper<SucursalDTO>() {

			@Override
			public SucursalDTO map(Object[] obj) {
				SucursalDTO ret = new SucursalDTO();
				ret.setIdSucursal((Integer) obj[0]);
				ret.setPais((String) obj[1]);
				ret.setCalle((String) obj[2]);
				ret.setAltura((Integer) obj[3]);
				ret.setLocalidad((String) obj[4]);
				ret.setIdMoneda((Integer) obj[5]);
				return ret;
			}
		};
	}
}
