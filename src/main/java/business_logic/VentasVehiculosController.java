package business_logic;

import java.util.List;

import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import repositories.VentaVehiculoDao;

public class VentasVehiculosController {
	
	private VentaVehiculoDao ventasDao;
		
	public VentasVehiculosController(VentaVehiculoDao ventasDao) {
		this.ventasDao = ventasDao;
	}

	public VehiculoDTO readByCodigo(Integer codigoVehiculo) {
		return null;
	}

	public List<OutputConsultaVehiculoEnVentaDTO> readByCriteria(ConsultaVehiculoParaVentaDTO consulta) {
		return null;
	}
	
	public List<VentaVehiculoDTO> readVentasVehiculosNoDisponibles(){
		List<VentaVehiculoDTO> ret = ventasDao.readVentasVehiculosNoDisponibles();
		return ret;
	}
}
