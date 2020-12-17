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

	@SuppressWarnings("deprecation")
	public List<OutputConsultaVehiculoEnVentaDTO> read(DaosFactory daos, ConsultaVehiculoParaVentaDTO consulta) {
		Boolean ES_USADO =  new Boolean(consulta.getTipo().equals("Usado"));
		List<VehiculoDTO> temp = daos.makeVehiculoDao().readByCriteria(consulta.getMarca(), ES_USADO);
		List<OutputConsultaVehiculoEnVentaDTO> ret = new LinkedList<>();
		for (VehiculoDTO dto : temp) {
			ret.add(makeFromVehiculo(dto));
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
		aux.setSucursal(dto.getIdSucursal() == null ? FABRICANTE : dto.getIdSucursal().toString());
		return aux;
	}
}
