package repositories.jdbc;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import dto.SucursalDTO;
import repositories.SucursalesDao;
import repositories.jdbc.utils.Mapper;

public class SucursalesDaoImpl extends GenericJdbcDao<SucursalDTO> implements SucursalesDao{

	private static final String insert = "INSERT INTO sucursal(pais, calle, altura, localidad, idMoneda) VALUES(?,?,?,?,?)";
	
	public SucursalesDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(SucursalDTO entity) {
		return false;
	}

	@Override
	public boolean insert(SucursalDTO entity) {
		return getTemplate().query(insert)
				.param(entity.getPais())
				.param(entity.getCalle())
				.param(entity.getAltura())
				.param(entity.getLocalidad())
				.param(entity.getIdMoneda())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public SucursalDTO readByID(Integer id) {
		return null;
	}

	@Override
	public List<SucursalDTO> readAll() {
		LinkedList<SucursalDTO> mock = new LinkedList<>();
		SucursalDTO ret = new SucursalDTO();
		ret.setLocalidad("Avellaneda");
		mock.add(ret);
		return mock;
	}

	@Override
	protected Mapper<SucursalDTO> getMapper() {
		return null;
	}

	@Override
	public SucursalDTO readByName(String name) {
		return null;
	}

	@Override
	public List<String> readFinancierasByPais(String pais) {
		LinkedList<String> mock = new LinkedList<>();
		mock.add("ICBC");
		mock.add("Santander");
		mock.add("CityGroup");
		return mock;
	}
}
