package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.RepuestoPlanificadoDTO;
import repositories.RepuestosPlanificadosDao;
import repositories.jdbc.utils.Mapper;

public class RepuestosPlanificadosDaoImpl extends GenericJdbcDao<RepuestoPlanificadoDTO> implements RepuestosPlanificadosDao {

	public RepuestosPlanificadosDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(RepuestoPlanificadoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(RepuestoPlanificadoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RepuestoPlanificadoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepuestoPlanificadoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<RepuestoPlanificadoDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

}
