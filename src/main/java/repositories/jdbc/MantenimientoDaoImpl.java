package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.taller.MantenimientoDTO;
import repositories.MantenimientoDao;
import repositories.jdbc.utils.Mapper;

public class MantenimientoDaoImpl extends GenericJdbcDao<MantenimientoDTO> implements MantenimientoDao {

	private static final String insert = "INSERT INTO Mantenimientos(nombre, comentario) VALUES (?,?)";
	private static final String idMaximo = "SELECT MAX(idMantenimiento) FROM Mantenimientos";
	private static final String readAll = "SELECT * FROM Mantenimientos";
	private static final String readById = readAll + " WHERE idMantenimiento = ?";

	public MantenimientoDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(MantenimientoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(MantenimientoDTO entity) {
		return getTemplate().query(insert).param(entity.getNombre()).param(entity.getComentario()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public MantenimientoDTO readByID(Integer id) {
		List<MantenimientoDTO> m = getTemplate().query(readById).param(id).excecute(getMapper());
		return m.isEmpty() ? null : m.get(0);
	}

	@Override
	public List<MantenimientoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	protected Mapper<MantenimientoDTO> getMapper() {
		return new Mapper<MantenimientoDTO>() {

			@Override
			public MantenimientoDTO map(Object[] obj) {
				MantenimientoDTO ret = new MantenimientoDTO();
				ret.setId((Integer) obj[0]);
				ret.setNombre((String) obj[1]);
				ret.setComentario((String) obj[2]);
				return ret;
			}
		};
	}

	@Override
	public int getIdMaximo() {
		return getTemplate().query(idMaximo).excecute(new Mapper<Integer>() {
			@Override
			public Integer map(Object[] obj) {
				return (Integer) obj[0];
			}
		}).get(0);
	}

}
