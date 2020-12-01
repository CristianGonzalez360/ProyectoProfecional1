package business_logic;

import java.util.LinkedList;
import java.util.List;

import dto.CaracteristicaVehiculoDTO;
import dto.PedidoVehiculoDTO;
import dto.SucursalDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import repositories.DaosFactory;

public class VentasVehiculosController {
		
	private DaosFactory daos;
	
	private static final String pais = "Argentina";
	
	public VentasVehiculosController(DaosFactory daos) {
		assert daos != null;
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
	

	public List<VentaVehiculoDTO> readVentasVehiculosNoDisponibles(){
		List<VentaVehiculoDTO> ret = daos.makeVentaVehiculoDao().readVentasVehiculosNoDisponibles();
		return ret;
	}


	public List<String> readNombreMarcasVehiculos() {
		return daos.makeVehiculoDao().readAllMarcasVehiculos();
	}
	
	public List<OutputConsultaVehiculoEnVentaDTO> readByCriteria(ConsultaVehiculoParaVentaDTO consulta) {
		List<VehiculoDTO> temp = daos.makeVehiculoDao().readByCriteria(consulta.getMarca(), new Boolean(consulta.getTipo().equals("Usado")));	
		List<OutputConsultaVehiculoEnVentaDTO> ret = new LinkedList<>();
		for(VehiculoDTO dto : temp) {
			OutputConsultaVehiculoEnVentaDTO aux = new OutputConsultaVehiculoEnVentaDTO();
			aux.setMarca(dto.getMarca());
			aux.setFamilia(dto.getFamilia());
			aux.setLinea(dto.getLinea());
			aux.setPrecio(dto.getPrecioVenta().toString());
			aux.setCodigo(dto.getIdVehiculo().toString());
			aux.setColor(dto.getColor());
			aux.setSucursal(dto.getIdSucursal() == null ? "NONE" : dto.getIdSucursal().toString());
			ret.add(aux);
		}
		return ret;
	}

	public CaracteristicaVehiculoDTO readCaracteristicaVehiculoByIdVehiculo(Integer codigoVehiculo) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(codigoVehiculo);
		return daos.makeCaracteristicasVehiculoDao().readByID(vehiculo.getIdCaracteristicas());
	}
	
	public List<VehiculoDTO> readAllVehiculoNuevos() {
		List<VehiculoDTO> vehiculos = daos.makeVehiculoDao().readAll();
		List<VehiculoDTO> vehiculosNuevos = new LinkedList<VehiculoDTO>();
		for (VehiculoDTO vehiculo : vehiculos) {
			if(vehiculo.isUsado()==false) {
				vehiculosNuevos.add(vehiculo);
			}
		}
		return vehiculosNuevos;
	}
	
	public void saveVehiculosNuevos(List<VehiculoDTO> vehiculosNuevos) {
		for (VehiculoDTO vehiculo : vehiculosNuevos) {
			daos.makeVehiculoDao().insert(vehiculo);
		}
	}
}

