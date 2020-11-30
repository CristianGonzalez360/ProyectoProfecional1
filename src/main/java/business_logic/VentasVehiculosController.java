package business_logic;

import java.util.LinkedList;
import java.util.List;

import dto.CaracteristicaVehiculoDTO;
import dto.SucursalDTO;
import dto.VehiculoDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import repositories.DaosFactory;

public class VentasVehiculosController {
		
	private DaosFactory daos;
	
	private static final String pais = "Argentina";
	
	public VentasVehiculosController(DaosFactory daos) {
		this.daos = daos;
	}

	public VehiculoDTO readByCodigo(Integer codigoVehiculo) {
		return daos.makeVehiculoDao().readByID(codigoVehiculo);
	}

	public List<String> readNombreSucursales() {
		List<SucursalDTO> sucursales = daos.makeSucursalesDao().readByPais(pais);
		List<String> nombresSucursales = new LinkedList<>();
		for(SucursalDTO suc: sucursales) nombresSucursales.add(suc.getLocalidad());
		return nombresSucursales;
	}
	
	public List<String> readNombreMarcasVehiculos() {
		return daos.makeVehiculoDao().readAllMarcasVehiculos();
	}
	
	public List<OutputConsultaVehiculoEnVentaDTO> readByCriteria(ConsultaVehiculoParaVentaDTO consulta) {
		Integer idSucursal = daos.makeSucursalesDao().readByName(consulta.getSucursal()).getIdSucursal();
		List<VehiculoDTO> temp = daos
				.makeVehiculoDao()
				.readByCriteria(consulta.getTipo().equals("Nuevo") ? false : true, consulta.getMarca(), consulta.getFamilia() ,consulta.getLinea(), idSucursal);
		List<OutputConsultaVehiculoEnVentaDTO> ret = new LinkedList<>();
		for(VehiculoDTO dto : temp) {
			OutputConsultaVehiculoEnVentaDTO aux = new OutputConsultaVehiculoEnVentaDTO();
			aux.setMarca(dto.getMarca());
			aux.setFamilia(dto.getFamilia());
			aux.setLinea(dto.getLinea());
			aux.setPrecio(dto.getPrecioVenta().toString());
			aux.setCodigo(dto.getIdVehiculo().toString());
			aux.setColor(dto.getColor());
			CaracteristicaVehiculoDTO car = daos.makeCaracteristicasVehiculoDao().readByID(dto.getIdCaracteristicas());	
			aux.setCilindrada(car.getCilindrada());
			ret.add(aux);
		}
		return ret;
	}
}