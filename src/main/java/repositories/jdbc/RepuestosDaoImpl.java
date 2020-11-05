package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.RepuestoDTO;
import repositories.RepuestosDao;
import repositories.jdbc.utils.Mapper;

public class RepuestosDaoImpl extends GenericJdbcDao<RepuestoDTO> implements RepuestosDao {

	private static final String readAll = "SELECT * FROM Repuestos";
	
	private static final String readById = "SELECT * FROM Repuestos WHERE Repuestos.idRepuesto = ?";
	
	private static final String insert = 
			"INSERT INTO Repuestos (codigoRepuesto, precioRepuesto, marcaRepuesto, descripcionRepuesto, stockRepuesto, fabricante, stockMinimo)"
			+ " " + "VALUES (?,?,?,?,?,?,?)";
	
	public RepuestosDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(RepuestoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(RepuestoDTO entity) {
		return getTemplate().query(insert)
				.param(entity.getCodigoRepuesto())
				.param(entity.getPrecioRepuesto())
				.param(entity.getMarcaRepuesto())
				.param(entity.getDescripcionRepuesto())
				.param(entity.getStockRepuesto())
				.param(entity.getFabricante())
				.param(entity.getStockMinimo())
				.excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RepuestoDTO readByID(Integer id) {	
		List<RepuestoDTO> dtos = getTemplate().query(readById).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public List<RepuestoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}
	
	@Override
	protected Mapper<RepuestoDTO> getMapper() {
		
		return new Mapper<RepuestoDTO>() {

			@Override
			public RepuestoDTO map(Object[] obj) {
				RepuestoDTO dto = new RepuestoDTO();
				dto.setIdRepuesto((Integer)obj[0]);
				dto.setCodigoRepuesto((Integer)obj[1]);
				dto.setPrecioRepuesto((Double)obj[2]);
				dto.setMarcaRepuesto((String)obj[3]);
				dto.setDescripcionRepuesto((String)obj[4]);
				dto.setStockRepuesto((Integer)obj[5]);
				dto.setFabricante((String)obj[6]);
				dto.setStockMinimo((Integer)obj[7]);
				return dto;
			}	
		};
	}
}
