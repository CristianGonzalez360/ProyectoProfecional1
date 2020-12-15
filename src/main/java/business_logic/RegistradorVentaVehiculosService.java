package business_logic;

import java.util.Calendar;
import java.util.Date;
import business_logic.exceptions.ForbiddenException;
import business_logic.exceptions.NotFoundException;
import dto.GarantiaVehiculoDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.VehiculoConOrdenDeTrabajoDTO;
import dto.temporal.ModalidadVentaVehiculoDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import repositories.DaosFactory;
import services.SessionServiceImpl;

public class RegistradorVentaVehiculosService {
	
	private static final String FORBIDDEN_VEHICULO = "Para cerrar una venta es necesario indicar un vehículo.";

	private static final String FORBIDDEN_CLIENTE = "Para cerrar una venta es necesario indicar el cliente.";
	
	private static final String FABRICANTE = "Fabricante";

	private static final Integer KILOMETRAJE_MAX_GARANTIZADO_PARA_USADO = 90000;
	
	private static final int UN_ANIO_GARANTIA = 360;
	
	private static final int ANIO_GARANTIA_USADO = 1;

	private static final Integer ANIOS_MAXIMO_GARANTIA_NUEVO = 5;

	private static final Integer ANIOS_MINIMO_GARANTIA_NUEVO = 3;

	private static final Integer KILOMETRAJE_GARANTIZADO_PARA_NUEVO_SIN_EXTENSION = 100000;

	private static final Integer KILOMETRAJE_MAX_GARANTIZADO_PARA_NUEVO_CON_EXTENSION = 100000;
	
	private DaosFactory daos;
	
	public RegistradorVentaVehiculosService(DaosFactory daos) {
		assert daos != null;
		this.daos = daos;
	}
	
	@SuppressWarnings("deprecation")
	public void registrarVenta(Integer idCliente, OutputConsultaVehiculoEnVentaDTO vehiculo, ModalidadVentaVehiculoDTO modalidadVenta) {
		if(idCliente == null) throw new ForbiddenException(FORBIDDEN_CLIENTE);
		if(vehiculo == null) throw new ForbiddenException(FORBIDDEN_VEHICULO);
		VentaVehiculoDTO venta = makeVentaDTO(idCliente, vehiculo, modalidadVenta);
		if(requiereRegistrarPrimero(vehiculo)) {
			VehiculoDTO target = makeVehiculoNuevoParaRegistrar(vehiculo);
			vehiculo.setCodigo(target.getIdVehiculo()+"");
			venta.setIdVehiculo(target.getIdVehiculo());
		}
		daos.makeVentaVehiculoDao().insert(venta);
		daos.makeVehiculoDao().updateDisponibilidadVehiculo(venta.getIdVehiculo(), new Boolean(false));
		updateGarantiaVehiculo(Integer.parseInt(vehiculo.getCodigo()), modalidadVenta);
		makeVehiculoConOrdenDeTrabajo(venta.getIdVehiculo(), idCliente);
	}
		
	private void updateGarantiaVehiculo(Integer idVehiculo, ModalidadVentaVehiculoDTO modalidadVenta) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(idVehiculo);
		GarantiaVehiculoDTO garantia = daos.makeGarantiasVehiculosDao().readByIdVehiculo(idVehiculo);
		if(garantia == null) throw new NotFoundException("El vehiculo no tiene garantia");
		if(vehiculo.isUsado()) {
			garantia.setAniosDeGarantia(ANIO_GARANTIA_USADO);
			garantia.setFechaInicioDeLaGarantia(new Date());
			garantia.setFechaDeCaducidadDeLaGarantia(sumarRestarDiasFecha(new Date(), UN_ANIO_GARANTIA));
			garantia.setKilometrajeGarantizado(garantia.getKilometrajeInicialDelVehiculo() + KILOMETRAJE_MAX_GARANTIZADO_PARA_USADO);
		} else {
			if(!modalidadVenta.isExtiendeGarantia()) {
				garantia.setAniosDeGarantia(ANIOS_MINIMO_GARANTIA_NUEVO);
				garantia.setFechaInicioDeLaGarantia(new Date());
				garantia.setFechaDeCaducidadDeLaGarantia(sumarRestarDiasFecha(new Date(), UN_ANIO_GARANTIA * 3));
				garantia.setKilometrajeGarantizado(garantia.getKilometrajeInicialDelVehiculo() + KILOMETRAJE_GARANTIZADO_PARA_NUEVO_SIN_EXTENSION);
			} else {
				garantia.setAniosDeGarantia(ANIOS_MAXIMO_GARANTIA_NUEVO);
				garantia.setFechaInicioDeLaGarantia(new Date());
				garantia.setFechaDeCaducidadDeLaGarantia(sumarRestarDiasFecha(new Date(), UN_ANIO_GARANTIA * 5));
				garantia.setKilometrajeGarantizado(garantia.getKilometrajeInicialDelVehiculo() + KILOMETRAJE_MAX_GARANTIZADO_PARA_NUEVO_CON_EXTENSION);
				garantia.setCostoFinalConIVA(Double.parseDouble(modalidadVenta.getCostoGarantia()));
			}
		}
		daos.makeGarantiasVehiculosDao().update(garantia);
	}
	
	private Date sumarRestarDiasFecha(Date fecha, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		return calendar.getTime();
	}
	
	private VehiculoDTO makeVehiculoNuevoParaRegistrar(OutputConsultaVehiculoEnVentaDTO vehiculo) {
		VehiculoDTO nuevo = daos.makeVehiculoDao().readByID(Integer.parseInt(vehiculo.getCodigo()));
		nuevo.setIdVehiculo(null);
		nuevo.setDisponible(true);
		nuevo.setFechaIngreso(null);			
		daos.makeVehiculoDao().insert(nuevo);
		int last = daos.makeVehiculoDao().readAll().size() -1;
		VehiculoDTO target = daos.makeVehiculoDao().readAll().get(last);
		return target;
	}
	
	private boolean requiereRegistrarPrimero(OutputConsultaVehiculoEnVentaDTO vehiculo) {
		return vehiculo.getSucursal().equals(FABRICANTE);
	}

	private VentaVehiculoDTO makeVentaDTO(Integer idCliente, OutputConsultaVehiculoEnVentaDTO vehiculo, ModalidadVentaVehiculoDTO modalidadVenta) {
		VentaVehiculoDTO venta = new VentaVehiculoDTO();
		venta.setFechaVentaVN(new Date());
		venta.setIdCliente(idCliente);
		venta.setIdVehiculo(Integer.parseInt(vehiculo.getCodigo()));
		venta.setIdUsuVentaVN(SessionServiceImpl.getInstance().getActiveSession().getIdUsuario());
		venta.setIdSucursalVenta(SessionServiceImpl.getInstance().getActiveSession().getIdSucursal());
		if(!modalidadVenta.isEfectivo()) {
			venta.setFinanciera(modalidadVenta.getFinanciera());
			venta.setNroCuotas(Integer.parseInt(modalidadVenta.getNroCuotas()));
			venta.setMontoCuota(Double.parseDouble(modalidadVenta.getMontoCuota()));
		}
		venta.setFabricante(vehiculo.getMarca());
		venta.setComisionCobrada(Double.parseDouble(modalidadVenta.getComision()));
		venta.setPrecioVenta(new CalculadoraMontoFinalVentaService(modalidadVenta).getPrecioFinalVenta());
		return venta;
	}
	
	private void makeVehiculoConOrdenDeTrabajo(Integer idVehiculo, Integer idCliente) {
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(idVehiculo);
		VehiculoConOrdenDeTrabajoDTO v = new VehiculoConOrdenDeTrabajoDTO();
		v.setIdFichaTecnica(vehiculo.getIdFichaTecnica());
		v.setIdCliente(idCliente);
		if(vehiculo.isUsado()) {
			FichaTecnicaVehiculoDTO ficha = daos.makeFichaTecnicaVehiculoDao().readByID(vehiculo.getIdFichaTecnica());
			v.setPatente(ficha.getPatente());
		}
		//GarantiaVehiculoDTO garantia = daos.makeGarantiasVehiculosDao().readByIdVehiculo(vehiculo.getIdVehiculo());
		//v.setKilometrajeGarantia(garantia.getKilometrajeGarantizado());
		v.setIdVehiculo(idVehiculo);
		daos.makeVehiculoConOrdeDeTrabajoDao().insert(v);
	}
}
