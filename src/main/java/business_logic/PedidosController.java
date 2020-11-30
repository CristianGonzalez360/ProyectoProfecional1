package business_logic;

import java.util.ArrayList;
import java.util.List;

import dto.PedidoVehiculoDTO;
import dto.temporal.PedidoDTO;
import repositories.ClientesDao;
import repositories.DatosPersonalesDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.PedidoVehiculoDao;
import services.SessionServiceImpl;

public class PedidosController {

	private ClientesDao clienteDao;
	private DatosPersonalesDao datosPersonalesDao;
	private FichaTecnicaVehiculoDao fichaTecnicaDao;
	private PedidoVehiculoDao pedidosDao;

	public PedidosController(ClientesDao clientesDao, DatosPersonalesDao datosPersonalesDao,
			FichaTecnicaVehiculoDao fichaTecnicaDao, PedidoVehiculoDao pedidosDao) {
		this.clienteDao = clientesDao;
		this.datosPersonalesDao = datosPersonalesDao;
		this.fichaTecnicaDao = fichaTecnicaDao;
		this.pedidosDao = pedidosDao;
	}

	public List<PedidoDTO> readAllByDniCliente(Integer dniCliente) {
		return null;
	}

//	public List<PedidoVehiculoDTO> readAllPedidos() {
//		List<PedidoVehiculoDTO> pedidos = new ArrayList<>();
//
//		for (PedidoVehiculoDTO pedido : pedidosDao.readAll())
//			pedidos.add(pedido);
//
//		return pedidos;
//	}

	public List<PedidoDTO> readAllPedidos() {
		List<PedidoDTO> pedidos = new ArrayList<>();

		for (PedidoVehiculoDTO pedido : pedidosDao.readAll()) {

			pedidos.add(new PedidoDTO());
		}

		return pedidos;
	}

	public void save(PedidoVehiculoDTO pedido) {
		pedido.setIdUsuPedido(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		pedidosDao.insert(pedido);
	}
	
	
}
