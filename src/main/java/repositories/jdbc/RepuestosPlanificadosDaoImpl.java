package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.taller.RepuestoDTO;
import dto.taller.RepuestoPlanificadoDTO;
import repositories.RepuestosPlanificadosDao;
import repositories.jdbc.utils.Mapper;

public class RepuestosPlanificadosDaoImpl extends GenericJdbcDao<RepuestoPlanificadoDTO>
		implements RepuestosPlanificadosDao {

	public static final String readAll = "SELECT * FROM RepuestosPlanificados INNER JOIN repuestos ON RepuestosPlanificados.idRepuesto = repuestos.idRepuesto";

	public static final String readByPresupuestoId = readAll + " " + "WHERE idPresu = ?";

	public static final String insert = "INSERT INTO RepuestosPlanificados (idPresu, idRepuesto, cantRequerida, precio) VALUES (?,?,?,?)";

	private static final String update = "UPDATE RepuestosPlanificados SET cantRequerida = ? WHERE idRepuestoPlanificado = ?";

	private static final String delete = "DELETE FROM RepuestosPlanificados WHERE idRepuestoPlanificado = ?";

	public RepuestosPlanificadosDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(RepuestoPlanificadoDTO entity) {
		return getTemplate().query(update).param(entity.getCantRequerida()).param(entity.getIdRepuestoPlanificado())
				.excecute();
	}

	@Override
	public boolean insert(RepuestoPlanificadoDTO entity) {
		return getTemplate().query(insert).param(entity.getIdPresu()).param(entity.getIdRepuesto())
				.param(entity.getPrecio())
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
				dto.setPrecio((Double)obj[4]);

				RepuestoDTO repuesto = new RepuestoDTO();
				repuesto.setIdRepuesto((Integer) obj[5]);
				repuesto.setCodigoRepuesto((Integer) obj[6]);
				repuesto.setPrecioRepuesto((Double) obj[7]);
				repuesto.setMarcaRepuesto((String) obj[8]);
				repuesto.setDescripcionRepuesto((String) obj[9]);
				repuesto.setStockRepuesto((Integer) obj[10]);
				repuesto.setFabricante((String) obj[11]);
				repuesto.setStockMinimo((Integer) obj[12]);
				repuesto.setGarantia((Boolean) obj[13]);
				repuesto.setPrecioCompra((Double) obj[14]);
				dto.setRepuesto(repuesto);
				return dto;
			}
		};
	}
}
