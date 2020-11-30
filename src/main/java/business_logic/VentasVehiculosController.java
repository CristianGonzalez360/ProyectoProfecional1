package business_logic;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import dto.CaracteristicaVehiculoDTO;
import dto.ClienteDTO;
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
	
	public List<OutputConsultaVehiculoEnVentaDTO> readDisponiblesByCriteria(ConsultaVehiculoParaVentaDTO consulta) {
		List<VehiculoDTO> temp = daos.makeVehiculoDao().readDisponiblesByCriteria(consulta.getMarca(), new Boolean(consulta.getTipo().equals("Usado")));	
		List<OutputConsultaVehiculoEnVentaDTO> ret = new LinkedList<>();
		for(VehiculoDTO dto : temp) {
			if(dto.isDisponible()) {
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
		}
		return ret;
	}

	public CaracteristicaVehiculoDTO readCaracteristicaVehiculoByIdVehiculo(Integer codigoVehiculo) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(codigoVehiculo);
		return daos.makeCaracteristicasVehiculoDao().readByID(vehiculo.getIdCaracteristicas());
	}

	public void registrarVenta(ClienteDTO cliente, OutputConsultaVehiculoEnVentaDTO vehiculo,
			ModalidadVentaVehiculoDTO modalidadVenta) {
		VentaVehiculoDTO venta = new VentaVehiculoDTO();
		venta.setFechaVentaVN(new Date());
		venta.setIdCliente(cliente.getIdCliente());
		venta.setIdVehiculo(Integer.parseInt(vehiculo.getCodigo()));
		venta.setIdUsuVentaVN(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		venta.setIdSucursalVenta(SessionServiceImpl.getInstance().getActiveSession().getIdSucursal());
		if(!modalidadVenta.isEfectivo()) {
			venta.setFinanciera(modalidadVenta.getFinanciera());
			venta.setNroCuotas(Integer.parseInt(modalidadVenta.getNroCuotas()));
			venta.setMontoCuota(Double.parseDouble(modalidadVenta.getMontoCuota()));
		}
		venta.setComisionCobrada(Double.parseDouble(modalidadVenta.getComision()));
		venta.setPrecioVenta(Double.parseDouble(modalidadVenta.getPrecioFinal()));
		daos.makeVentaVehiculoDao().insert(venta);
	}
}
