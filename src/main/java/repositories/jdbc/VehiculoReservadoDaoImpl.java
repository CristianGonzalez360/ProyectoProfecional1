package repositories.jdbc;

import java.sql.Connection;
import java.util.List;

import dto.VehiculoReservadoDTO;
import repositories.VehiculoReservadoDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class VehiculoReservadoDaoImpl extends GenericJdbcDao<VehiculoReservadoDTO> implements VehiculoReservadoDao {

	private static final String insert = "INSERT INTO VehiculoReservado (idFichaTecnica, idPedido, precioVenta) VALUES (?,?,?)";

	private static final String readAll = "SELECT * FROM VehiculoReservado";

	public VehiculoReservadoDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(VehiculoReservadoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(VehiculoReservadoDTO entity) {
		return getTemplate().query(insert)
				.param(entity.getIdFichaTecnica())
				.param(entity.getIdPedido() == null ? new NullObject() : entity.getIdPedido())
				.param(entity.getPrecioVenta()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VehiculoReservadoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehiculoReservadoDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	protected Mapper<VehiculoReservadoDTO> getMapper() {
		return new Mapper<VehiculoReservadoDTO>() {
			
			@Override
			public VehiculoReservadoDTO map(Object[] obj) {
				VehiculoReservadoDTO reserva = new VehiculoReservadoDTO();
				reserva.setIdVehiculoReservado((Integer) obj[0]);
				reserva.setIdFichaTecnica((Integer) obj[1]);
				reserva.setIdPedido((Integer) obj[2]);
				reserva.setPrecioVenta((Double) obj[3]);
				return reserva;
			}
		};
	}

}
