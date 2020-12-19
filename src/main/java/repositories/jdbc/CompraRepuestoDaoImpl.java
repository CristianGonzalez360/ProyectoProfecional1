package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.CompraRepuestoDTO;
import repositories.CompraRepuestoDao;
import repositories.jdbc.utils.Mapper;

public class CompraRepuestoDaoImpl extends GenericJdbcDao<CompraRepuestoDTO> implements CompraRepuestoDao {


	private static final String readFechas = "SELECT * FROM CompraRepuesto where fechaCompra BETWEEN ? and ?";

	
	public CompraRepuestoDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	public List<CompraRepuestoDTO> readFechas(Date desde, Date hasta) {
		return getTemplate().query(readFechas).param(desde).param(hasta).excecute(getMapper());
	}

	@Override
	public boolean update(CompraRepuestoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(CompraRepuestoDTO entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CompraRepuestoDTO readByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompraRepuestoDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Mapper<CompraRepuestoDTO> getMapper() {
		return new Mapper<CompraRepuestoDTO>() {

			@Override
			public CompraRepuestoDTO map(Object[] obj) {
				CompraRepuestoDTO compraVehiculo = new CompraRepuestoDTO();
				compraVehiculo.setIdCompraRepuesto((Integer) obj[0]);
				compraVehiculo.setCodigoRepuesto((Integer) obj[1]);
				compraVehiculo.setPrecioCompra((Double) obj[2]);
				compraVehiculo.setFechaCompra((Date) obj[3]);
				compraVehiculo.setCantidad((Integer) obj[4]);
				return compraVehiculo;
			}
		};
	}

}
