package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import repositories.RepuestosPlanificadosDao;
import repositories.jdbc.utils.Mapper;

public class RepuestosPlanificadosDaoImpl extends GenericJdbcDao<RepuestoPlanificadoDTO>
		implements RepuestosPlanificadosDao {

	public static final String readAll = "SELECT * FROM RepuestosPlanificados";

	public static final String readByPresupuestoId = "SELECT * FROM RepuestosPlanificados INNER JOIN repuestos ON RepuestosPlanificados.idRepuesto = repuestos.idRepuesto WHERE idPresu = ?";

	public static final String insert = "INSERT INTO RepuestosPlanificados (idPresu, idRepuesto, cantRequerida) VALUES (?,?,?)";

	public RepuestosPlanificadosDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(RepuestoPlanificadoDTO entity) {
		return false;
	}

	@Override
	public boolean insert(RepuestoPlanificadoDTO entity) {
		return getTemplate().query(insert).param(entity.getIdPresu()).param(entity.getIdRepuesto())
				.param(entity.getCantRequerida()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
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
				dto.setIdPresu((Integer) obj[0]);
				dto.setIdRepuesto((Integer) obj[1]);
				dto.setCantRequerida((Integer) obj[2]);
				
				RepuestoDTO repuesto = new RepuestoDTO();
				repuesto.setIdRepuesto((Integer) obj[3]);
				repuesto.setCodigoRepuesto((Integer) obj[4]);
				repuesto.setPrecioRepuesto((Double) obj[5]);
				repuesto.setMarcaRepuesto((String) obj[6]);
				repuesto.setDescripcionRepuesto((String) obj[7]);
				repuesto.setStockRepuesto((Integer) obj[8]);
				repuesto.setFabricante((String) obj[9]);
				repuesto.setStockMinimo((Integer) obj[10]);
				dto.setRepuesto(repuesto);
				return dto;
			}
		};
	}
}
