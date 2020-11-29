package repositories.jdbc;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import dto.SucursalDTO;
import repositories.SucursalDao;
import repositories.jdbc.utils.Mapper;

public class SucursalesDaoImpl extends GenericJdbcDao<SucursalDTO> implements SucursalDao{

	public SucursalesDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(SucursalDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(SucursalDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SucursalDTO readByID(Integer id) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SucursalDTO readByName(String name) {
		// TODO Auto-generated method stub
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
