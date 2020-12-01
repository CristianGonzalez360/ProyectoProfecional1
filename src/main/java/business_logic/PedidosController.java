package business_logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.DatosPersonalesDTO;
import dto.PedidoVehiculoDTO;
import dto.UsuarioDTO;
import dto.VentaVehiculoDTO;
import dto.temporal.PedidoDTO;
import repositories.ClientesDao;
import repositories.DatosPersonalesDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.PedidoVehiculoDao;

import repositories.UsuariosDao;
import repositories.VentaVehiculoDao;
import services.SessionServiceImpl;

public class PedidosController {

	private ClientesDao clientesDao;
	private DatosPersonalesDao datosPersonalesDao;
	private UsuariosDao usuariosDao;
	private VentaVehiculoDao ventaVehiculoDao;
	private FichaTecnicaVehiculoDao fichaTecnicaDao;
	private PedidoVehiculoDao pedidosDao;

	public PedidosController(ClientesDao clientesDao, DatosPersonalesDao datosPersonalesDao, UsuariosDao usuariosDao,
			VentaVehiculoDao ventaVehiculoDao, PedidoVehiculoDao pedidosDao) {
		this.clientesDao = clientesDao;
		this.datosPersonalesDao = datosPersonalesDao;
		this.usuariosDao = usuariosDao;
		this.ventaVehiculoDao = ventaVehiculoDao;
		this.pedidosDao = pedidosDao;
	}

	public List<PedidoDTO> readAllByDniCliente(Integer dniCliente) {
		return null;
	}

	public List<PedidoDTO> readAllPedidos() {
		List<PedidoDTO> pedidos = new ArrayList<>();

		for (PedidoVehiculoDTO pedido : pedidosDao.readAll()) {
			pedidos.add(armarPedidoCompleto(pedido));
		}

		System.out.println("buscargg");
		return pedidos;
	}

	private PedidoDTO armarPedidoCompleto(PedidoVehiculoDTO pedido) {
		PedidoDTO nuevoPedido = new PedidoDTO();

		Integer idPedido = nuevoPedido.getIdPedido();
		String nombreCliente = "";
		String apellidoCliente = "";
		String dniCliente = "";
		String marcaAuto = datosDeVehiculo(pedido.getIdVentaVehiculo()).getFabricante();
		String modeloAuto = "";
		String colorAuto = "";
		String conbustionAuto = "";
		String nombreUsuario = datosDeUsuario(pedido.getIdUsuPedido()).getDatos().getNombreCompleto();
		Date fechaPedido = pedido.getFechaPedido();

		nuevoPedido.setIdPedido(idPedido);
		nuevoPedido.setNombreCliente(nombreCliente);
		nuevoPedido.setApellidoCliente(apellidoCliente);
		nuevoPedido.setDniCliente(dniCliente);
		nuevoPedido.setMarcaAuto(marcaAuto);
		nuevoPedido.setModeloAuto(modeloAuto);
		nuevoPedido.setColorAuto(colorAuto);
		nuevoPedido.setConbustionAuto(conbustionAuto);
		nuevoPedido.setNombreUsuario(nombreUsuario);
		nuevoPedido.setFechaPedido(fechaPedido);

		return nuevoPedido;
	}

	private DatosPersonalesDTO datosDeCliente(Integer id) {
		return datosPersonalesDao.readByID(id);
	}

	private VentaVehiculoDTO datosDeVehiculo(Integer id) {
		return ventaVehiculoDao.readByID(id);
	}

	private UsuarioDTO datosDeUsuario(Integer id) {
		return usuariosDao.readByID(id);
	}

	public void save(Integer idVenta) {
		PedidoVehiculoDTO pedido = new PedidoVehiculoDTO();
		pedido.setIdVentaVehiculo(idVenta);
		pedido.setIdUsuPedido(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		pedidosDao.insert(pedido);
	}

}
