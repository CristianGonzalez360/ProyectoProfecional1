package business_logic;

import java.util.Date;

import dto.GarantiaVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import repositories.DaosFactory;

public class GarantiasController {

	private DaosFactory daos;

	public GarantiasController(DaosFactory daos) {
		super();
		this.daos = daos;
	}
	
	public GarantiaVehiculoDTO readByIdVehiculo(Integer id) {
		return daos.makeGarantiasVehiculosDao().readByIdVehiculo(id);
	}
	
	public boolean estaEnGarantia(Integer idVehiculo) {
		boolean ret = true;
		GarantiaVehiculoDTO garantia = readByIdVehiculo(idVehiculo);
		Date fechaCaducidad = garantia.getFechaDeCaducidadDeLaGarantia();
		if(fechaCaducidad.after(new Date())) {
			ret = false;
		} 
		VehiculoDTO vehiculo = daos.makeVehiculoDao().readByID(idVehiculo);
		FichaTecnicaVehiculoDTO ficha = daos.makeFichaTecnicaVehiculoDao().readByID(vehiculo.getIdFichaTecnica());
		if(ficha.getKilometraje() > garantia.getKilometrajeGarantizado()-garantia.getKilometrajeInicialDelVehiculo()){
			ret = false;
		}
		return ret;
	}
}
