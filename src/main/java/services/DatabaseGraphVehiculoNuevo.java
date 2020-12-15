package services;

import java.util.LinkedList;
import java.util.List;

import dto.CaracteristicaVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;

public class DatabaseGraphVehiculoNuevo {
	private List<VehiculoDTO> vehiculos;
	private List<CaracteristicaVehiculoDTO> caracteristicaVehiculo;
	private List<FichaTecnicaVehiculoDTO> fichaTecnica;

	public DatabaseGraphVehiculoNuevo() {
		vehiculos = new LinkedList<>();
		caracteristicaVehiculo = new LinkedList<>();
		setFichaTecnica(new LinkedList<>());
	}

	public List<VehiculoDTO> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<VehiculoDTO> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<CaracteristicaVehiculoDTO> getCaracteristicas() {
		return caracteristicaVehiculo;
	}

	public void setCaracteristicas(List<CaracteristicaVehiculoDTO> caracteristicas) {
		this.caracteristicaVehiculo = caracteristicas;
	}

	public List<FichaTecnicaVehiculoDTO> getFichaTecnica() {
		return fichaTecnica;
	}

	public void setFichaTecnica(List<FichaTecnicaVehiculoDTO> fichaTecnica) {
		this.fichaTecnica = fichaTecnica;
	}
}
