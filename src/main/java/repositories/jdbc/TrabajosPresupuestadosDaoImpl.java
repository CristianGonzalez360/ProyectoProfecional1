package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.TrabajoPresupuestadoDTO;
import repositories.TrabajosPresupuestadosDao;
import repositories.jdbc.utils.Mapper;

public class TrabajosPresupuestadosDaoImpl extends GenericJdbcDao<TrabajoPresupuestadoDTO> implements TrabajosPresupuestadosDao {

	private static final String readAll = "SELECT * FROM TrabajosPresupuestados";
	
	private static final String readById = readAll + " " + "WHERE TrabajosPresupuestados.idTrabajoPresu = ?";
	
	private static final String readbByPresupuestoId = readAll + " " + "WHERE TrabajosPresupuestados.idPresupuesto = ?";
	
	private static final String insert = "INSERT INTO TrabajosPresupuestados (idPresupuesto, descripcionTrabajo, precioTrabajo, tiempoEstTrabajo) VALUES (?,?,?,?)";
	
	public TrabajosPresupuestadosDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(TrabajoPresupuestadoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(TrabajoPresupuestadoDTO entity) {
		return getTemplate().query(insert)
				.param(entity.getIdPresupuesto())
				.param(entity.getDescripcionTrabajo())
				.param(entity.getPrecioTrabajo())
				.param(entity.getTiempoEstTrabajo())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TrabajoPresupuestadoDTO readByID(Integer id) {
		List<TrabajoPresupuestadoDTO> dtos = getTemplate().query(readById).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<TrabajoPresupuestadoDTO> readByPresupuestoId(Integer id){
		return getTemplate().query(readbByPresupuestoId).param(id).excecute(getMapper());
	}
	
	@Override
	public List<TrabajoPresupuestadoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	protected Mapper<TrabajoPresupuestadoDTO> getMapper() {
		return new Mapper<TrabajoPresupuestadoDTO>() {

			@Override
			public TrabajoPresupuestadoDTO map(Object[] obj) {
				TrabajoPresupuestadoDTO dto = new TrabajoPresupuestadoDTO();
				dto.setIdTrabajoPresu((Integer)obj[0]);
				dto.setIdPresupuesto((Integer)obj[1]);
				dto.setDescripcionTrabajo((String)obj[2]);
				dto.setPrecioTrabajo((Double)obj[3]);
				dto.setTiempoEstTrabajo((Integer)obj[4]);
				return dto;
			}
		};
	}
}
