package business_logic;

import java.util.LinkedList;
import java.util.List;

import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import repositories.DaosFactory;

public class ConsultadorVehiculosEnVentaService {

	private static final String FABRICANTE = "Fabricante";
		
	public ConsultadorVehiculosEnVentaService() {
	}
	
	public List<OutputConsultaVehiculoEnVentaDTO> read(DaosFactory daos, ConsultaVehiculoParaVentaDTO consulta) {		
		List<VehiculoDTO> temp = daos.makeVehiculoDao().readDisponiblesByCriteria(consulta.getMarca(), new Boolean(consulta.getTipo().equals("Usado")));	
		List<OutputConsultaVehiculoEnVentaDTO> ret = new LinkedList<>();		
		for(VehiculoDTO dto : temp) {
			VentaVehiculoDTO ventasRegistradas = daos.makeVentaVehiculoDao().readByIdVehiculoVendido(dto.getIdVehiculo());			
			final boolean VEHICULO_NO_VENDIDO = ventasRegistradas == null;
			if(VEHICULO_NO_VENDIDO) {
				ret.add(makeFromVehiculo(dto));	
			}
		}
		return ret;
	}
	
	private OutputConsultaVehiculoEnVentaDTO makeFromVehiculo(VehiculoDTO dto) {
		OutputConsultaVehiculoEnVentaDTO aux = new OutputConsultaVehiculoEnVentaDTO();
		aux.setMarca(dto.getMarca());
		aux.setFamilia(dto.getFamilia());
		aux.setLinea(dto.getLinea());
		aux.setPrecio(dto.getPrecioVenta().toString());
		aux.setCodigo(dto.getIdVehiculo().toString());
		aux.setColor(dto.getColor());
		aux.setSucursal(dto.getIdSucursal() == null ? FABRICANTE: dto.getIdSucursal().toString());
		return aux;
	}
}
