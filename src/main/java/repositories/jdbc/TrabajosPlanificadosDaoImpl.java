package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.TrabajoPlanificadoDTO;
import repositories.TrabajosPlanificadosDao;
import repositories.jdbc.utils.Mapper;

public class TrabajosPlanificadosDaoImpl extends GenericJdbcDao<TrabajoPlanificadoDTO> implements TrabajosPlanificadosDao {

	private static final String readAll = "SELECT * FROM TrabajosPlanificados";
	
	private static final String readById = readAll + " " + "WHERE TrabajosPlanficados.idTrabajoPresu = ?";
	
	private static final String readbByPresupuestoId = readAll + " " + "WHERE TrabajosPlanificados.idPresupuesto = ?";
	
	private static final String insert = "INSERT INTO TrabajosPlanificados (idPresupuesto, descripcionTrabajo, precioTrabajo, tiempoEstTrabajo) VALUES (?,?,?,?)";
	
	public TrabajosPlanificadosDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(TrabajoPlanificadoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(TrabajoPlanificadoDTO entity) {
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
	public TrabajoPlanificadoDTO readByID(Integer id) {
		List<TrabajoPlanificadoDTO> dtos = getTemplate().query(readById).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<TrabajoPlanificadoDTO> readByPresupuestoId(Integer id){
		return getTemplate().query(readbByPresupuestoId).param(id).excecute(getMapper());
	}
	
	@Override
	public List<TrabajoPlanificadoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	protected Mapper<TrabajoPlanificadoDTO> getMapper() {
		return new Mapper<TrabajoPlanificadoDTO>() {

			@Override
			public TrabajoPlanificadoDTO map(Object[] obj) {
				TrabajoPlanificadoDTO dto = new TrabajoPlanificadoDTO();
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
