package business_logic;

import java.util.Calendar;
import java.util.Date;

import dto.GarantiaVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.temporal.ModalidadVentaVehiculoDTO;
import repositories.DaosFactory;

public class ActualizadorDeGarantiaService {

	/**
	 * @apiNote IDEALMENTE ESTE BLOQUE DE VARIABLES DEBERIAN LEERSE DESDE UN ARCHIVO
	 *          DE CONFIGURACION
	 * @TODO QUE LO REFACTORIZE D10S
	 */
	private static final Integer KILOMETRAJE_MAX_GARANTIZADO_PARA_USADO = 90000;

	private static final int UN_ANIO_GARANTIA = 360;

	private static final int ANIO_GARANTIA_USADO = 1;

	private static final Integer ANIOS_MAXIMO_GARANTIA_NUEVO = 5;

	private static final Integer ANIOS_MINIMO_GARANTIA_NUEVO = 3;

	private static final Integer KILOMETRAJE_GARANTIZADO_PARA_NUEVO_SIN_EXTENSION = 100000;

	private static final Integer KILOMETRAJE_MAX_GARANTIZADO_PARA_NUEVO_CON_EXTENSION = 100000;

	private static final double COSTO_INICIAL_GARANTIA = 32000.0;

	private DaosFactory daos;

	public ActualizadorDeGarantiaService(DaosFactory daos) {
		this.daos = daos;
	}

	public void updateGarantiaVehiculo(Integer idVehiculo, ModalidadVentaVehiculoDTO modalidadVenta) {
		assert idVehiculo != null;
		assert modalidadVenta != null;
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(idVehiculo);
		GarantiaVehiculoDTO garantia = daos.makeGarantiasVehiculosDao().readByIdVehiculo(vehiculo.getIdVehiculo());
		if (garantia == null) {
			garantia = registrarGarantia(vehiculo);
		}
		if (vehiculo.isUsado()) {
			registrarGarantiaParaVehiculoUsado(garantia);
		} else {
			if (!modalidadVenta.isExtiendeGarantia()) {
				registrarGarantiaParaVehiculoNuevoSinExtension(garantia);
			} else {
				registrarGarantiaParaVehiculoConExtension(modalidadVenta, garantia);
			}
		}
		daos.makeGarantiasVehiculosDao().update(garantia);
	}

	private void registrarGarantiaParaVehiculoConExtension(ModalidadVentaVehiculoDTO modalidadVenta,
			GarantiaVehiculoDTO garantia) {
		garantia.setAniosDeGarantia(ANIOS_MAXIMO_GARANTIA_NUEVO);
		garantia.setFechaInicioDeLaGarantia(new Date());
		garantia.setFechaDeCaducidadDeLaGarantia(sumarRestarDiasFecha(new Date(), UN_ANIO_GARANTIA * 5));
		garantia.setKilometrajeGarantizado(
				garantia.getKilometrajeInicialDelVehiculo() + KILOMETRAJE_MAX_GARANTIZADO_PARA_NUEVO_CON_EXTENSION);
		garantia.setCostoFinalConIVA(Double.parseDouble(modalidadVenta.getCostoGarantia()));
	}

	private void registrarGarantiaParaVehiculoNuevoSinExtension(GarantiaVehiculoDTO garantia) {
		garantia.setAniosDeGarantia(ANIOS_MINIMO_GARANTIA_NUEVO);
		garantia.setFechaInicioDeLaGarantia(new Date());
		garantia.setFechaDeCaducidadDeLaGarantia(sumarRestarDiasFecha(new Date(), UN_ANIO_GARANTIA * 3));
		garantia.setKilometrajeGarantizado(
				garantia.getKilometrajeInicialDelVehiculo() + KILOMETRAJE_GARANTIZADO_PARA_NUEVO_SIN_EXTENSION);
	}

	private void registrarGarantiaParaVehiculoUsado(GarantiaVehiculoDTO garantia) {
		garantia.setAniosDeGarantia(ANIO_GARANTIA_USADO);
		garantia.setFechaInicioDeLaGarantia(new Date());
		garantia.setFechaDeCaducidadDeLaGarantia(sumarRestarDiasFecha(new Date(), UN_ANIO_GARANTIA));
		garantia.setKilometrajeGarantizado(
				garantia.getKilometrajeInicialDelVehiculo() + KILOMETRAJE_MAX_GARANTIZADO_PARA_USADO);
	}

	private GarantiaVehiculoDTO registrarGarantia(VehiculoDTO vehiculo) {
		GarantiaVehiculoDTO garantia;
		garantia = new GarantiaVehiculoDTO();
		garantia.setIdVehiculo(vehiculo.getIdVehiculo());
		FichaTecnicaVehiculoDTO ftecnica = daos.makeFichaTecnicaVehiculoDao().readByID(vehiculo.getIdFichaTecnica());
		if(ftecnica != null) {
			garantia.setKilometrajeInicialDelVehiculo(ftecnica.getKilometraje());	
		} else {
			garantia.setKilometrajeInicialDelVehiculo(0);
		}
		garantia.setCostoFinalConIVA(COSTO_INICIAL_GARANTIA);
		daos.makeGarantiasVehiculosDao().insert(garantia);
		return garantia;
	}

	private Date sumarRestarDiasFecha(Date fecha, int dias) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.DAY_OF_YEAR, dias);
		return calendar.getTime();
	}
}
