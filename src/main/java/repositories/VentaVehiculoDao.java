package repositories;

import java.util.List;

import dto.VentaVehiculoDTO;

public interface VentaVehiculoDao extends GenericDao<VentaVehiculoDTO, Integer> {
	
	public List<VentaVehiculoDTO> readByVendedor(int idUsuario);

	public List<VentaVehiculoDTO> readVentasVehiculosNoDisponibles();
	
	public List<VentaVehiculoDTO> readAll();
}
