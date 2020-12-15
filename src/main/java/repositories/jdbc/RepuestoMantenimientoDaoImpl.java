package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.taller.MantenimientoDTO;
import dto.taller.RepuestoMantenimientoDTO;
import repositories.RepuestoMantenimientoDao;
import repositories.jdbc.utils.Mapper;

public class RepuestoMantenimientoDaoImpl extends GenericJdbcDao<RepuestoMantenimientoDTO>
		implements RepuestoMantenimientoDao {

	private static final String insert = "INSERT INTO RepuestosMantenimiento(idRepuesto, idMantenimiento, cantidad) VALUES (?,?,?)";
	private static final String readByIdMantenimiento = "SELECT * FROM RepuestosMantenimiento WHERE idMantenimiento = ?";
	private static final String delete = "DELETE FROM RepuestosMantenimiento WHERE idRepuestoMantenimiento = ?";

	public RepuestoMantenimientoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(RepuestoMantenimientoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(RepuestoMantenimientoDTO entity) {
		return getTemplate().query(insert).param(entity.getIdRepuesto()).param(entity.getidMantenimiento())
				.param(entity.getCantRequerida()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return getTemplate().query(delete).param(id).excecute();
	}

	@Override
	public RepuestoMantenimientoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepuestoMantenimientoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<RepuestoMantenimientoDTO> getMapper() {
		return new Mapper<RepuestoMantenimientoDTO>() {

			@Override
			public RepuestoMantenimientoDTO map(Object[] obj) {
				RepuestoMantenimientoDTO ret = new RepuestoMantenimientoDTO();
				ret.setIdRepuestoMantenimiento((int) obj[0]);
				ret.setIdRepuesto((int) obj[1]);
				ret.setidMantenimiento((int) obj[2]);
				ret.setCantRequerida((int) obj[3]);
				return ret;
			}
		};
	}

	@Override
	public List<RepuestoMantenimientoDTO> readByIdMantenimiento(Integer id) {
		return getTemplate().query(readByIdMantenimiento).param(id).excecute(getMapper());
	}

}
