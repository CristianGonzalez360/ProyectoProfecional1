package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.taller.TrabajoMantenimientoDTO;
import repositories.TrabajoMantenimientoDao;
import repositories.jdbc.utils.Mapper;

public class TrabajoMantenimientoDaoImpl extends GenericJdbcDao<TrabajoMantenimientoDTO>
		implements TrabajoMantenimientoDao {

	private static final String insert = "INSERT INTO TrabajosMantenimiento(descripcionTrabajo, idMantenimiento, precio, tiempoEstTrabajo) VALUES (?,?,?,?)";
	private static final String readByIdMantenimiento = "SELECT * FROM TrabajosMantenimiento WHERE idMantenimiento = ?";
	private static final String delete = "DELETE FROM TrabajosMantenimiento WHERE idTrabajoMantenimiento = ?";

	public TrabajoMantenimientoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(TrabajoMantenimientoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(TrabajoMantenimientoDTO entity) {
		return getTemplate().query(insert).param(entity.getDescripcionTrabajo()).param(entity.getidMantenimiento())
				.param(entity.getPrecioTrabajo()).param(entity.getTiempoEstTrabajo()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return getTemplate().query(delete).param(id).excecute();
	}

	@Override
	public TrabajoMantenimientoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrabajoMantenimientoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<TrabajoMantenimientoDTO> getMapper() {
		return new Mapper<TrabajoMantenimientoDTO>() {

			@Override
			public TrabajoMantenimientoDTO map(Object[] obj) {
				TrabajoMantenimientoDTO ret = new TrabajoMantenimientoDTO();
				ret.setIdTrabajoMantenimiento((Integer) obj[0]);
				ret.setDescripcionTrabajo((String) obj[1]);
				ret.setidMantenimiento((Integer) obj[2]);
				ret.setPrecioTrabajo((Double) obj[3]);
				ret.setTiempoEstTrabajo((Integer) obj[4]);
				return ret;
			}
		};
	}

	@Override
	public List<TrabajoMantenimientoDTO> readByIdMantennimiento(int id) {
		return getTemplate().query(readByIdMantenimiento).param(id).excecute(getMapper());
	}

}
