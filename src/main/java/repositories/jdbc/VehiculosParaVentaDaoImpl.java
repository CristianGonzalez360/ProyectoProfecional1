package repositories.jdbc;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import dto.VehiculoParaVentaDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import repositories.VehiculosEnVentaDao;
import repositories.jdbc.utils.Mapper;

public class VehiculosParaVentaDaoImpl extends GenericJdbcDao<VehiculoParaVentaDTO> implements VehiculosEnVentaDao {

	public VehiculosParaVentaDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(VehiculoParaVentaDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(VehiculoParaVentaDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VehiculoParaVentaDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoParaVentaDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<VehiculoParaVentaDTO> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoParaVentaDTO> readByCriteria(ConsultaVehiculoParaVentaDTO consulta) {
		List<VehiculoParaVentaDTO> ret = new LinkedList<>();
		VehiculoParaVentaDTO temp = new VehiculoParaVentaDTO().makeTestDTO();
		ret.add(temp);
		return ret;
	}
}
