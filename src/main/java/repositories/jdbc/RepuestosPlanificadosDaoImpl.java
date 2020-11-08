package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import repositories.RepuestosPlanificadosDao;
import repositories.jdbc.utils.Mapper;

public class RepuestosPlanificadosDaoImpl extends GenericJdbcDao<RepuestoPlanificadoDTO>
		implements RepuestosPlanificadosDao {

	public static final String readAll = "SELECT * FROM RepuestosPlanificados INNER JOIN repuestos ON RepuestosPlanificados.idRepuesto = repuestos.idRepuesto";

	public static final String readByPresupuestoId = readAll + " " + "WHERE idPresu = ?";

	public static final String insert = "INSERT INTO RepuestosPlanificados (idPresu, idRepuesto, cantRequerida) VALUES (?,?,?)";
	
	private static final String update = "UPDATE RepuestosPlanificados SET cantRequerida = ? WHERE idRepuestoPlanificado = ?";
	
	private static final String delete = "DELETE FROM RepuestosPlanificados WHERE idRepuestoPlanificado = ?";

	public RepuestosPlanificadosDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(RepuestoPlanificadoDTO entity) {
		return getTemplate().query(update).param(entity.getCantRequerida())
				.param(entity.getIdRepuestoPlanificado()).excecute();
	}

	@Override
	public boolean insert(RepuestoPlanificadoDTO entity) {
		return getTemplate().query(insert).param(entity.getIdPresu())
				.param(entity.getIdRepuesto())
				.param(entity.getCantRequerida()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return getTemplate().query(delete).param(id).excecute();
	}

	@Override
	public RepuestoPlanificadoDTO readByID(Integer id) {
		return null;
	}

	@Override
	public List<RepuestoPlanificadoDTO> readAll() {
		return null;
	}

	@Override
	public List<RepuestoPlanificadoDTO> readByIdPresupuesto(Integer id) {
		assert id != null;
		return getTemplate().query(readByPresupuestoId).param(id).excecute(getMapper());
	}

	@Override
	protected Mapper<RepuestoPlanificadoDTO> getMapper() {
		return new Mapper<RepuestoPlanificadoDTO>() {

			@Override
			public RepuestoPlanificadoDTO map(Object[] obj) {
				RepuestoPlanificadoDTO dto = new RepuestoPlanificadoDTO();
				dto.setIdRepuestoPlanificado((Integer) obj[0]);
				
				dto.setIdPresu((Integer) obj[1]);
				dto.setIdRepuesto((Integer) obj[2]);
				dto.setCantRequerida((Integer) obj[3]);
				
				RepuestoDTO repuesto = new RepuestoDTO();
				repuesto.setIdRepuesto((Integer) obj[4]);
				repuesto.setCodigoRepuesto((Integer) obj[5]);
				repuesto.setPrecioRepuesto((Double) obj[6]);
				repuesto.setMarcaRepuesto((String) obj[7]);
				repuesto.setDescripcionRepuesto((String) obj[8]);
				repuesto.setStockRepuesto((Integer) obj[9]);
				repuesto.setFabricante((String) obj[10]);
				repuesto.setStockMinimo((Integer) obj[11]);
				dto.setRepuesto(repuesto);
				return dto;
			}
		};
	}
}
