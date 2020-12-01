package repositories;

import java.util.List;

import dto.PedidoVehiculoDTO;

public interface PedidoVehiculoDao extends GenericDao<PedidoVehiculoDTO, Integer> {
	
	public List<PedidoVehiculoDTO> readByCliente(int idCliente);

	public boolean estaPedido(Integer idVentaVehiculo);
}
