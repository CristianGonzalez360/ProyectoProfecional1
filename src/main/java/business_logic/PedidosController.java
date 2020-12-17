package business_logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import dto.DatosPersonalesDTO;
import dto.PedidoVehiculoDTO;
import dto.UsuarioDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.temporal.PedidoDTO;
import repositories.DatosPersonalesDao;
import repositories.PedidoVehiculoDao;
import repositories.UsuariosDao;
import repositories.VehiculosDao;
import repositories.VentaVehiculoDao;
import services.SessionServiceImpl;

public class PedidosController {

	private DatosPersonalesDao datosPersonalesDao;
	private UsuariosDao usuariosDao;
	private VentaVehiculoDao ventaVehiculoDao;
	private VehiculosDao vehiculosDao;
	private PedidoVehiculoDao pedidosDao;

	public PedidosController(DatosPersonalesDao datosPersonalesDao, UsuariosDao usuariosDao,
			VentaVehiculoDao ventaVehiculoDao, VehiculosDao vehiculosDao, PedidoVehiculoDao pedidosDao) {
		this.datosPersonalesDao = datosPersonalesDao;
		this.usuariosDao = usuariosDao;
		this.ventaVehiculoDao = ventaVehiculoDao;
		this.vehiculosDao = vehiculosDao;
		this.pedidosDao = pedidosDao;
	}

	public List<PedidoDTO> readAllByDniCliente(Integer dniCliente, Integer idSucursal) {
		List<PedidoDTO> pedidos = new ArrayList<>();

		for (PedidoDTO pedido : readAllPedidos(idSucursal)) {
			if (pedido != null && pedido.getDniCliente().equals(dniCliente.toString()))
				pedidos.add(pedido);
		}
		return pedidos;
	}

	public List<PedidoDTO> readAllPedidos(Integer idSucursal) {
		List<PedidoDTO> pedidos = new ArrayList<>();

		for (PedidoVehiculoDTO pedido : pedidosDao.readAllPedidosDeVenta(idSucursal)) {
			if (pedido != null)
				pedidos.add(armarPedidoCompleto(pedido));
		}
		return pedidos;
	}

	private PedidoDTO armarPedidoCompleto(PedidoVehiculoDTO pedido) {
		VentaVehiculoDTO datosDeVenta = getDatosDeVenta(pedido.getIdVentaVehiculo());
		DatosPersonalesDTO datosCliente = getDatosDeCliente(datosDeVenta.getIdCliente());
		VehiculoDTO datosVehiculo = getDatosVehiculo(datosDeVenta.getIdVehiculo());
		UsuarioDTO datosUsuario = getDatosDeUsuario(pedido.getIdUsuPedido());

		String dniCliente = String.valueOf(datosCliente.getDni());
		String modeloAuto = datosVehiculo.getFamilia() + " - " + datosVehiculo.getLinea();
		String conbustionAuto = "";
		String nombreUsuario = datosUsuario.getDatos().getNombreCompleto();

		return new PedidoDTO(pedido.getIdPedidoVehiculo(), datosCliente.getNombreCompleto(), datosCliente.getApellido(),
				dniCliente, datosVehiculo.getMarca(), modeloAuto, datosVehiculo.getColor(), conbustionAuto,
				nombreUsuario, pedido.getFechaPedido());
	}

	private VentaVehiculoDTO getDatosDeVenta(Integer idVentaVehiculo) {
		return ventaVehiculoDao.readByID(idVentaVehiculo);
	}

	private DatosPersonalesDTO getDatosDeCliente(Integer id) {
		return datosPersonalesDao.readByID(id);
	}

	private VehiculoDTO getDatosVehiculo(Integer idVehiculo) {
		return vehiculosDao.readByID(idVehiculo);
	}

	private UsuarioDTO getDatosDeUsuario(Integer id) {
		return usuariosDao.readByID(id);
	}

	public void save(Integer idVenta) {
		PedidoVehiculoDTO pedido = new PedidoVehiculoDTO();
		pedido.setIdVentaVehiculo(idVenta);
		pedido.setIdUsuPedido(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		pedidosDao.insert(pedido);
	}

	public boolean registrarIngresoPedidoById(Integer idPedido, Integer idUsuario) {
		if (idPedido == null)
			return false;

		PedidoVehiculoDTO pedido = pedidosDao.readByID(idPedido);
		pedido.setFechaIngreso(new Date());
		pedido.setIdUsuIngreso(idUsuario);

		return pedidosDao.updateIngreso(pedido);
	}
	
	public PedidoVehiculoDTO readPedidoById (Integer idPedido) {
		return pedidosDao.readByID(idPedido);
	}
	
	public VentaVehiculoDTO readByVentaId(Integer idVentaVehiculo) {
		return ventaVehiculoDao.readByID(idVentaVehiculo);
	}
	
	public VehiculoDTO readByVehiculoId(Integer idVehiculo) {
		return vehiculosDao.readByID(idVehiculo);
	}
	
	public boolean updateIdFichaTecnicaDeVehiculo (VehiculoDTO vehiculo) {
		return vehiculosDao.updateIdFichaTecnica(vehiculo);
	}
}
