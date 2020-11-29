package business_logic;

import java.util.ArrayList;
import java.util.List;

import dto.EntregaDeVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.PedidoVehiculoDTO;
import repositories.ClientesDao;
import repositories.DatosPersonalesDao;
import repositories.FichaTecnicaVehiculoDao;
import repositories.PedidoVehiculoDao;
import repositories.VehiculoReservadoDao;

public class PedidosController {

	private ClientesDao clientesDao;
	private DatosPersonalesDao datosPersonalesDao;
	private FichaTecnicaVehiculoDao fichaTecnicaDao;
	private VehiculoReservadoDao vehiculoReservadoDao;
	private PedidoVehiculoDao pedidosDao;

	public PedidosController(ClientesDao clientesDao, DatosPersonalesDao datosPersonalesDao,
			FichaTecnicaVehiculoDao fichaTecnicaDao, VehiculoReservadoDao vehiculoReservadoDao,
			PedidoVehiculoDao pedidosDao) {
		this.clientesDao = clientesDao;
		this.datosPersonalesDao = datosPersonalesDao;
		this.fichaTecnicaDao = fichaTecnicaDao;
		this.vehiculoReservadoDao = vehiculoReservadoDao;
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
}
