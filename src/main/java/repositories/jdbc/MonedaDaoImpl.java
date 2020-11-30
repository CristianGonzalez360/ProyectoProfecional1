package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.MonedaDTO;
import repositories.MonedaDao;
import repositories.jdbc.utils.Mapper;

public class MonedaDaoImpl extends GenericJdbcDao<MonedaDTO> implements MonedaDao {

	private static final String insert = "INSERT INTO moneda(nombre, simbolo, cotizacionDolar) VALUES(?,?,?)";
	
	public MonedaDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(MonedaDTO entity) {
		return false;
	}

	@Override
	public boolean insert(MonedaDTO entity) {
		return getTemplate()
				.query(insert)
				.param(entity.getNombre())
				.param(entity.getSimbolo())
				.param(entity.getCotizacionDolar())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MonedaDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MonedaDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<MonedaDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
