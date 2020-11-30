package business_logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.DatosPersonalesDTO;
import dto.PedidoVehiculoDTO;
import dto.UsuarioDTO;
import dto.VehiculoParaVentaDTO;
import dto.temporal.PedidoDTO;
import repositories.ClientesDao;
import repositories.DatosPersonalesDao;
import repositories.PedidoVehiculoDao;
import repositories.UsuariosDao;
import repositories.VehiculosEnVentaDao;

public class PedidosController {

	private ClientesDao clientesDao;
	private DatosPersonalesDao datosPersonalesDao;
	private UsuariosDao usuariosDao;
	private VehiculosEnVentaDao vehiculosEnVentaDao;
	private PedidoVehiculoDao pedidosDao;

	public PedidosController(ClientesDao clientesDao, DatosPersonalesDao datosPersonalesDao, UsuariosDao usuariosDao,
			VehiculosEnVentaDao vehiculosEnVentaDao, PedidoVehiculoDao pedidosDao) {
		this.clientesDao = clientesDao;
		this.datosPersonalesDao = datosPersonalesDao;
		this.usuariosDao = usuariosDao;
		this.vehiculosEnVentaDao = vehiculosEnVentaDao;
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

		return pedidos;
	}

	private PedidoDTO armarPedidoCompleto(PedidoVehiculoDTO pedido) {
		PedidoDTO nuevoPedido = new PedidoDTO();

		Integer idPedido = nuevoPedido.getIdPedido();
		String nombreCliente = "";
		String apellidoCliente = "";
		String dniCliente = "";
		String marcaAuto = datosDeVehiculo(pedido.getIdVentaVehiculo()).getMarca();
		String modeloAuto = datosDeVehiculo(pedido.getIdVentaVehiculo()).getFamilia();
		String colorAuto = datosDeVehiculo(pedido.getIdVentaVehiculo()).getColorVehiculo();
		String conbustionAuto = datosDeVehiculo(pedido.getIdVentaVehiculo()).getFichaTecnica().getCombustion();
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

	private VehiculoParaVentaDTO datosDeVehiculo(Integer id) {
		return vehiculosEnVentaDao.readByID(id);
	}

	private UsuarioDTO datosDeUsuario(Integer id) {
		return usuariosDao.readByID(id);
	}

}
