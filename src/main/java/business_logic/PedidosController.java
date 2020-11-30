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

	public List<PedidoDTO> readAllByDniCliente(Integer dniCliente) {
		return null;
	}

	public List<PedidoDTO> readAllPedidos() {
		List<PedidoDTO> pedidos = new ArrayList<>();

		for (PedidoVehiculoDTO pedido : pedidosDao.readAll()) {
			System.out.println(pedido.toString());
			pedidos.add(armarPedidoCompleto(pedido));
		}
		System.out.println("--");
		return pedidos;
	}

	private PedidoDTO armarPedidoCompleto(PedidoVehiculoDTO pedido) {
		VentaVehiculoDTO datosDeVenta = datosDeVenta(pedido.getIdVentaVehiculo());
		DatosPersonalesDTO datosCliente = datosDeCliente(datosDeVenta.getIdCliente());
		VehiculoDTO datosVehiculo = datosVehiculo(datosDeVenta.getIdVehiculo());
		UsuarioDTO datosUsuario = datosDeUsuario(pedido.getIdUsuPedido());

		String nombreCliente = datosCliente.getNombreCompleto();
		String apellidoCliente = datosCliente.getApellido();
		String dniCliente = String.valueOf(datosCliente.getDni());

		String marcaAuto = datosVehiculo.getMarca();
		String modeloAuto = datosVehiculo.getFamilia() + " - " + datosVehiculo.getLinea();
		String colorAuto = datosVehiculo.getColor();
		String conbustionAuto = "";

		String nombreUsuario = datosUsuario.getDatos().getNombreCompleto();
		Date fechaPedido = pedido.getFechaPedido();

		PedidoDTO nuevoPedido = new PedidoDTO();

		nuevoPedido.setNombreCliente(nombreCliente);
		nuevoPedido.setApellidoCliente(apellidoCliente);
		nuevoPedido.setDniCliente(dniCliente);
		nuevoPedido.setMarcaAuto(marcaAuto);
		nuevoPedido.setModeloAuto(modeloAuto);
		nuevoPedido.setColorAuto(colorAuto);
		nuevoPedido.setConbustionAuto(conbustionAuto);
		nuevoPedido.setNombreUsuario(nombreUsuario);
		nuevoPedido.setFechaPedido(fechaPedido);

		System.out.println("Nuevo - > " + nuevoPedido);
		return nuevoPedido;
	}

	private VentaVehiculoDTO datosDeVenta(Integer idVentaVehiculo) {
		return ventaVehiculoDao.readByID(idVentaVehiculo);
	}

	private DatosPersonalesDTO datosDeCliente(Integer id) {
		return datosPersonalesDao.readByID(id);
	}

	private VehiculoDTO datosVehiculo(Integer idVehiculo) {
		return vehiculosDao.readByID(idVehiculo);
	}

	private UsuarioDTO datosDeUsuario(Integer id) {
		return usuariosDao.readByID(id);
	}

	public void save(PedidoVehiculoDTO pedido) {
		pedido.setIdUsuPedido(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		pedidosDao.insert(pedido);
	}

}
