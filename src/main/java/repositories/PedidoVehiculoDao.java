package repositories;

import java.util.List;

import dto.PedidoVehiculoDTO;

public interface PedidoVehiculoDao extends GenericDao<PedidoVehiculoDTO, Integer> {

	public List<PedidoVehiculoDTO> readByCliente(int idCliente);

	List<PedidoVehiculoDTO> readAllPedidosDeVenta(Integer idSucursal);

	public boolean estaPedido(Integer idVentaVehiculo);

	public boolean updateIngreso(PedidoVehiculoDTO pedido);

	public boolean estaIngresado(Integer idVentaVehiculo);

	public PedidoVehiculoDTO readByIdVenta(Integer idVentaVehiculo);

}
