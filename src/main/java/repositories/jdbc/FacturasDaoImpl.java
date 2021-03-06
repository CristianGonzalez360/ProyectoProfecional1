
package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.taller.FacturaDTO;
import repositories.FacturasDao;
import repositories.jdbc.utils.Mapper;
import repositories.jdbc.utils.NullObject;

public class FacturasDaoImpl extends GenericJdbcDao<FacturaDTO> implements FacturasDao {

	private static final String insert = "INSERT INTO Facturas (idOT, fechaDeAlta, fechaDeCierrePorPago, total, estado , dni,idCliente,descripcion) VALUES (?,?,?,?,?,?,?,?)";

	private static final String updateFechaPago = "UPDATE Facturas SET fechaDeCierrePorPago = ? WHERE idFactura = ?";

	private static final String readByOrdenDeTrabajoId = "SELECT * FROM Facturas WHERE idOT = ?";

	private static final String readAll = "SELECT * FROM Facturas";

	private static final String readByFactura = "SELECT * FROM Facturas where idFactura = ?";

	private static final String updatePago = "UPDATE Facturas SET estado = 'PAGA' , fechaDeCierrePorPago = SYSDATE WHERE idFactura = ?";

	private static final String insertFacturaCarrito = "INSERT INTO Facturas (estado, fechaDeAlta, total, dni, idCliente, descripcion) VALUES (?,?,?,?,?,?)";

	private static final String getFacturasPagasDate = "SELECT * FROM Facturas WHERE estado = 'PAGA' AND fechaDeCierrePorPago BETWEEN ? AND ?";

	public FacturasDaoImpl(Connection connection) {
		super(connection);
	}

	@Override
	public boolean update(FacturaDTO entity) {
		return false;
	}

	@Override
	public boolean updateFechaCierrePorPago(Integer id, Date fecha) {
		return getTemplate().query(updateFechaPago).param(fecha).param(id).excecute();
	}

	@Override
	public boolean updatePorPago(Integer id) {
		return getTemplate().query(updatePago).param(id).excecute();
	}

	@Override
	public boolean insert(FacturaDTO entity) {
		return getTemplate().query(insert).param(entity.getIdOrdenDeTrabajo()).param(entity.getFechaDeAlta())
				.param(entity.getFechaDeCierrePorPago() == null ? new NullObject() : entity.getFechaDeCierrePorPago())
				.param(entity.getTotal()).param(entity.getEstado()).param(entity.getDni()).param(entity.getIdCliente()).param(entity.getDescripcion()).excecute();
	}

	@Override
	public boolean deleteById(Integer id) {
		return false;
	}

	@Override
	public List<FacturaDTO> readByOrdenDeTrabajoId(Integer id) {
		return getTemplate().query(readByOrdenDeTrabajoId).param(id).excecute(getMapper());
	}

	@Override
	public FacturaDTO readByID(Integer id) {
		return null;
	}

	@Override
	public List<FacturaDTO> readAll() {
		return getTemplate().query(readAll).excecute(getMapper());
	}

	@Override
	public List<FacturaDTO> readByFactura(Integer id) {
		return getTemplate().query(readByFactura).param(id).excecute(getMapper());
	}

	@Override
	protected Mapper<FacturaDTO> getMapper() {
		return new Mapper<FacturaDTO>() {

			@Override
			public FacturaDTO map(Object[] obj) {
				FacturaDTO factura = new FacturaDTO();
				factura.setIdFactura((Integer) obj[0]);
				factura.setIdOrdenDeTrabajo((Integer) obj[1]);
				factura.setFechaDeAlta((Date) obj[2]);
				factura.setFechaDeCierrePorPago(obj[3] == null ? null : (Date) obj[3]);
				factura.setTotal((Double) obj[4]);
				factura.setEstado((String) obj[5]);
				factura.setDni((Integer) obj[6]);
				factura.setIdCliente((Integer)obj[7]);
				factura.setDescripcion((String) obj[8]);
				return factura;
			}
		};
	}

	@Override
	public FacturaDTO readById(Integer id) {
		List<FacturaDTO> dtos = getTemplate().query(readByFactura).param(id).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	public void insertFacturaCarrito(FacturaDTO facturaCarrito) {
		// TODO Auto-generated method stub
		getTemplate().query(insertFacturaCarrito).param(facturaCarrito.getEstado())
				.param(facturaCarrito.getFechaDeAlta()).param(facturaCarrito.getTotal())
				.param(facturaCarrito.getCliente().getDatosPersonalesDTO().getDni())
				.param(facturaCarrito.getCliente().getIdCliente()).param(facturaCarrito.getDescripcion()).excecute();
	}

	public List<FacturaDTO> readByDates(Date desde, Date hasta) {
		return getTemplate().query(getFacturasPagasDate).param(desde).param(hasta).excecute(getMapper());
	}

}