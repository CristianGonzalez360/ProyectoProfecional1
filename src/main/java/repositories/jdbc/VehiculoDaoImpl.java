package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import dto.ConsultaVehiculoDTO;
import dto.VehiculoDTO;
import repositories.VehiculoDao;
import repositories.jdbc.utils.Mapper;

public class VehiculoDaoImpl extends GenericJdbcDao<VehiculoDTO> implements VehiculoDao {

	private static final String readAll = "SELECT * FROM vehiculos";
	
	private static final String readNuevosDisponibles = readAll + " " + "WHERE usado = false AND disponible = true";

	public VehiculoDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(VehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(VehiculoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VehiculoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoDTO> readNuevosDisponibles() {
		// TODO Auto-generated method stub
		return getTemplate().query(readNuevosDisponibles).excecute(getMapper());
	}

	@Override
	protected Mapper<VehiculoDTO> getMapper() {
		return new Mapper<VehiculoDTO>() {
			@Override
			public VehiculoDTO map(Object[] obj) {
				VehiculoDTO ret = new VehiculoDTO();
				ret.setIdVehiculo((Integer) obj[0]);
				ret.setPrecioVenta((Double) obj[1]);
				ret.setFechaIngreso((Date) obj[2]);
				ret.setDisponible((boolean) obj[3]);
				ret.setUsado((Boolean) obj[4]);
				ret.setIdCompra((Integer) obj[5]);
				ret.setIdSucursal((Integer) obj[6]);
				return ret;
			}
		};
	}

	@Override
	public List<VehiculoDTO> readUsados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoDTO> readUsadosYNuevosDisponibles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoDTO> readByCriteria(ConsultaVehiculoDTO consulta) {
		String query = readAll;
		boolean anterior = false;
		boolean where = false;
		if(consulta.getTipo() != null) {
			query += " WHERE tipo = " + consulta.getTipo();
			anterior = true;
			where = true;
		}
		
		if(consulta.getMarca() != null) {
			if (anterior) 
				query += " AND";
			else if(!where) {
				query += " WHERE";
				where = true;
			}
			query += "  marca = " + consulta.getMarca();
		}
		else {
			anterior = false;
		}
		
		if(consulta.getLinea() != null) {
			if (anterior) 
				query += " AND";
			else if(!where) {
				query += " WHERE";
				where = true;
			}
			query += " linea = " + consulta.getLinea();
		}
		else {
			anterior = false;
		}
		
		if(consulta.getSucursal() != null) {
			if (anterior) 
				query += " AND";
			else if(!where) 
				query += " WHERE";
			query += " idSucursal = " + consulta.getLinea();
		}
		
		return getTemplate().query(query).excecute(getMapper());
	}
}
