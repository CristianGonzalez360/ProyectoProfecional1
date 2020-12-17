package repositories.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dto.CompraRepuestoDTO;
import dto.taller.RepuestoDTO;
import repositories.RepuestosDao;
import repositories.jdbc.utils.Mapper;

public class RepuestosDaoImpl extends GenericJdbcDao<RepuestoDTO> implements RepuestosDao {

	private static final String readAll = "SELECT * FROM Repuestos";

	private static final String readById = "SELECT * FROM Repuestos WHERE Repuestos.idRepuesto = ?";

	private static final String insert = "INSERT INTO Repuestos (codigoRepuesto, precioRepuesto, marcaRepuesto, descripcionRepuesto, stockRepuesto, fabricante, stockMinimo, precioCompra, garantia)"
			+ " " + "VALUES (?,?,?,?,?,?,?,?,?)";

	private static final String readByDescripcion = readAll + " " + "WHERE descripcionRepuesto = ?";

	private static final String readByMarca = readAll + " " + "WHERE marcaRepuesto = ?";

	private static final String readByMarcaYDescripcion = readAll + " "
			+ "WHERE marcaRepuesto = ? AND descripcionRepuesto = ?";

	private static final String readMarcas = "SELECT DISTINCT marcaRepuesto FROM Repuestos";

	private static final String readByCodigo = "SELECT * FROM Repuestos WHERE Repuestos.codigoRepuesto = ?";

	private static final String updateByCodigo = "UPDATE Repuestos SET Repuestos.stockRepuesto = ? WHERE Repuestos.codigoRepuesto = ?";

	private static final String update = "UPDATE repuestos SET stockRepuesto = ?, stockMinimo = ? , codigoRepuesto = ?, precioRepuesto = ?,"
			+ " marcaRepuesto = ?, descripcionRepuesto = ?, fabricante = ?, precioCompra = ?, garantia = ? WHERE idRepuesto = ?";

	private static final String readSinStock = readAll + " " + "WHERE stockRepuesto<stockMinimo";
	
	private static final String registroCompra = "INSERT INTO CompraRepuesto(codigoRepuesto,precioCompra,fechaCompra, cantidad) VALUES (?,?,?,?)";
	
	private static final String readCompras = "SELECT * FROM CompraRepuesto WHERE fechaCompra BETWEEN ? AND ? ORDER BY fechaCompra ASC";

	public RepuestosDaoImpl(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean update(RepuestoDTO entity) {
		return getTemplate().query(update).param(entity.getStockRepuesto()).param(entity.getStockMinimo())
				.param(entity.getCodigoRepuesto()).param(entity.getPrecioRepuesto()).param(entity.getMarcaRepuesto())
				.param(entity.getDescripcionRepuesto()).param(entity.getFabricante()).param(entity.getPrecioCompra())
				.param(entity.isGarantia()).param(entity.getIdRepuesto()).excecute();
	}

	@Override
	public boolean insert(RepuestoDTO entity) {

		return getTemplate().query(insert).param(entity.getCodigoRepuesto()).param(entity.getPrecioRepuesto())
				.param(entity.getMarcaRepuesto()).param(entity.getDescripcionRepuesto())
				.param(entity.getStockRepuesto()).param(entity.getFabricante()).param(entity.getStockMinimo())
				.param(entity.getPrecioCompra()).param(entity.isGarantia()).excecute();
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
	public List<RepuestoDTO> readByMarca(String marca) {
		return getTemplate().query(readByMarca).param(marca).excecute(getMapper());
	}

	@Override
	public List<RepuestoDTO> readByDescripcion(String descripcion) {
		return getTemplate().query(readByDescripcion).param(descripcion).excecute(getMapper());
	}

	@Override
	protected Mapper<RepuestoDTO> getMapper() {

		return new Mapper<RepuestoDTO>() {

			@Override
			public RepuestoDTO map(Object[] obj) {
				RepuestoDTO dto = new RepuestoDTO();
				dto.setIdRepuesto((Integer) obj[0]);
				dto.setCodigoRepuesto((Integer) obj[1]);
				dto.setPrecioRepuesto((Double) obj[2]);
				dto.setMarcaRepuesto((String) obj[3]);
				dto.setDescripcionRepuesto((String) obj[4]);
				dto.setStockRepuesto((Integer) obj[5]);
				dto.setFabricante((String) obj[6]);
				dto.setStockMinimo((Integer) obj[7]);
				dto.setGarantia((Boolean) obj[8]);
				dto.setPrecioCompra((Double) obj[9]);

				return dto;
			}
		};
	}

	@Override
	public List<RepuestoDTO> readByMarcaYDescripcion(String marca, String descripcion) {
		return getTemplate().query(readByMarcaYDescripcion).param(marca).param(descripcion).excecute(getMapper());
	}

	@Override
	public List<String> readMarcas() {
		Mapper<String> mapper = new Mapper<String>() {

			@Override
			public String map(Object[] obj) {
				return (String) obj[0];
			}
		};
		return getTemplate().query(readMarcas).excecute(mapper);
	}

	@Override
	public RepuestoDTO readByCodigo(Integer codigo) {
		List<RepuestoDTO> dtos = getTemplate().query(readByCodigo).param(codigo).excecute(getMapper());
		return dtos.isEmpty() ? null : dtos.get(0);
	}

	@Override
	public void updateByCodigo(RepuestoDTO repuesto) {
		getTemplate().query(updateByCodigo).param(repuesto.getStockRepuesto()).param(repuesto.getCodigoRepuesto())
				.excecute();
	}

	@Override
	public List<RepuestoDTO> readRepuestosSinStock() {
		return getTemplate().query(readSinStock).excecute(getMapper());
	}

	@Override
	public void registrarCompra(CompraRepuestoDTO compra) {
		getTemplate().query(registroCompra).param(compra.getCodigoRepuesto()).param(compra.getPrecioCompra())
		.param(compra.getFechaCompra()).param(compra.getCantidad()).excecute();
	}

	@Override
	public List<CompraRepuestoDTO> readCompras(Date desde, Date hasta){
		return getTemplate().query(readCompras).param(desde).param(hasta).excecute(new Mapper<CompraRepuestoDTO>() {

			@Override
			public CompraRepuestoDTO map(Object[] obj) {
				CompraRepuestoDTO ret = new CompraRepuestoDTO();
				ret.setIdCompraRepuesto((Integer)obj[0]);
				ret.setCodigoRepuesto((Integer) obj[1]);
				ret.setPrecioCompra((Double)obj[2]);
				ret.setFechaCompra((Date)obj[3]);
				ret.setCantidad((Integer) obj[4]);
				return ret;
			}
		});
	}
	
}
