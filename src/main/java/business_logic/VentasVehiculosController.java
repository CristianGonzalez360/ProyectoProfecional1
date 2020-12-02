package business_logic;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import dto.CaracteristicaVehiculoDTO;
import dto.SucursalDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.ModalidadVentaVehiculoDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import repositories.DaosFactory;
import services.SessionServiceImpl;

public class VentasVehiculosController {
	
	private DaosFactory daos;
	
	public VentasVehiculosController(DaosFactory daos) {
		assert daos != null;
		this.daos = daos;
	}
	
	public VehiculoDTO readByCodigo(Integer codigoVehiculo) {
		return daos.makeVehiculoDao().readByID(codigoVehiculo);
	}

	public List<VentaVehiculoDTO> readVentasVehiculosNoDisponibles(){
		List<VentaVehiculoDTO> ret = daos.makeVentaVehiculoDao().readVentasVehiculosNoDisponibles();
		for (VentaVehiculoDTO venta : ret) {
			venta.setPedido(daos.makePedidoVehiculoDao().estaPedido(venta.getIdVentaVehiculo()));
		}
		return ret;
	}

	public List<String> readNombreMarcasVehiculos() {
		return daos.makeVehiculoDao().readAllMarcasVehiculos();
	}
	
	public List<VentaVehiculoDTO> readAll() {
		return daos.makeVentaVehiculoDao().readAll();
	}
	
	public List<VentaVehiculoDTO> readFechas(Date desde,Date hasta) {
		return daos.makeVentaVehiculoDao().readFechas(desde, hasta);
	}
	
	public List<VentaVehiculoDTO> readByIdVendedor(Integer id,Date desde, Date hasta) {
		return daos.makeVentaVehiculoDao().readByIdVendedor(id,desde,hasta);
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

	public List<OutputConsultaVehiculoEnVentaDTO> readDisponiblesByCriteria(ConsultaVehiculoParaVentaDTO consulta) {		
		return new ConsultadorVehiculosEnVentaService().read(daos, consulta);
	}
	
	public void registrarVenta(Integer idCliente, OutputConsultaVehiculoEnVentaDTO vehiculo,ModalidadVentaVehiculoDTO modalidadVenta) {
		new RegistradorVentaVehiculosService(daos).registrarVenta(idCliente, vehiculo, modalidadVenta);
	}
	
	public CaracteristicaVehiculoDTO readCaracteristicaVehiculoByIdVehiculo(Integer codigoVehiculo) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(codigoVehiculo);
		return daos.makeCaracteristicasVehiculoDao().readByID(vehiculo.getIdCaracteristicas());
	}
	
	public List<String> readNombreSucursales() {
		List<SucursalDTO> sucursales = daos.makeSucursalesDao().readByPais(readPaisSucursal());
		List<String> nombresSucursales = new LinkedList<>();
		for(SucursalDTO suc: sucursales) nombresSucursales.add(suc.getLocalidad());
		return nombresSucursales;
	}
	
	private String readPaisSucursal() {
		Integer idSucursal = SessionServiceImpl.getInstance().getActiveSession().getIdSucursal();
		SucursalDTO sucursal = daos.makeSucursalesDao().readByID(idSucursal);
		return sucursal.getPais();
	}
}
