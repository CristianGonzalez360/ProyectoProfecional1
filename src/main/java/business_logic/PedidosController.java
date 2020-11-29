package business_logic;

import java.util.ArrayList;
import java.util.List;

import dto.PedidoVehiculoDTO;
import repositories.ClientesDao;
import repositories.DatosPersonalesDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.PedidoVehiculoDao;
import repositories.VehiculoReservadoDao;

public class PedidosController {

	private PedidoVehiculoDao pedidosDao;

	public PedidosController(ClientesDao clientesDao, DatosPersonalesDao datosPersonalesDao,
			FichaTecnicaVehiculoDao fichaTecnicaDao, VehiculoReservadoDao vehiculoReservadoDao,
			PedidoVehiculoDao pedidosDao) {
		this.pedidosDao = pedidosDao;
	}

	public List<PedidoVehiculoDTO> readByDniCliente(Integer dniCliente) {
		return null;
	}

	public List<PedidoVehiculoDTO> readAllPedidos() {
		List<PedidoVehiculoDTO> pedidos = new ArrayList<>();

		for (PedidoVehiculoDTO pedido : pedidosDao.readAll())
			pedidos.add(pedido);

		return pedidos;
	}
	
	public void registrarPedido(PedidoVehiculoDTO pedido) {
		pedidosDao.insert(pedido);
	}
}
