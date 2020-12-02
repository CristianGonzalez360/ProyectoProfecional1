package repositories;

import java.util.Date;
import java.util.List;

import dto.VentaVehiculoDTO;

public interface VentaVehiculoDao extends GenericDao<VentaVehiculoDTO, Integer> {
	
	public List<VentaVehiculoDTO> readByVendedor(int idUsuario);

	public List<VentaVehiculoDTO> readVentasVehiculosNoDisponibles();
	
	public List<VentaVehiculoDTO> readAll();

	public List<VentaVehiculoDTO> readFechas(Date desde, Date hasta);

	public boolean noEstaEntregado(Integer idVentaVehiculo);

	public List<VentaVehiculoDTO> readVentasVehiculosParaEntregar();
}
