package services;

import java.util.LinkedList;
import java.util.List;

import dto.ClienteDTO;
import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.TurnoDTO;
import dto.UsuarioDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;

public class DatabaseGraph {

	private List<CuentaDTO> cuentas;

	private List<DatosPersonalesDTO> datosPersonales;

	private List<UsuarioDTO> usuarios;

	private List<TurnoDTO> turnos;

	private List<FichaTecnicaVehiculoDTO> fichaTecnicaVehiculos;

	private List<OrdenDeTrabajoDTO> ordenesDeTrabajo;

	private List<VehiculoConOrdenDeTrabajoDTO> vehiculosConOrdenDeTrabajo;

	private List<ClienteDTO> clientes;

	public DatabaseGraph() {
		cuentas = new LinkedList<>();
		datosPersonales = new LinkedList<>();
		usuarios = new LinkedList<>();
		turnos = new LinkedList<>();
		ordenesDeTrabajo = new LinkedList<>();
		vehiculosConOrdenDeTrabajo = new LinkedList<>();
		clientes = new LinkedList<>();
	}

	public List<CuentaDTO> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<CuentaDTO> cuentas) {
		this.cuentas = cuentas;
	}

	public List<DatosPersonalesDTO> getDatosPersonales() {
		return datosPersonales;
	}

	public void setDatosPersonales(List<DatosPersonalesDTO> datosPersonales) {
		this.datosPersonales = datosPersonales;
	}

	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}

	public List<TurnoDTO> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<TurnoDTO> turnos) {
		this.turnos = turnos;
	}

	public List<FichaTecnicaVehiculoDTO> getFichaTecnicaVehiculos() {
		return fichaTecnicaVehiculos;
	}

	public void setFichaTecnicaVehiculos(List<FichaTecnicaVehiculoDTO> fichaTecnicaVehiculos) {
		this.fichaTecnicaVehiculos = fichaTecnicaVehiculos;
	}

	public List<OrdenDeTrabajoDTO> getOrdenesDeTrabajo() {
		return ordenesDeTrabajo;
	}

	public void setOrdenesDeTrabajo(List<OrdenDeTrabajoDTO> ordenesDeTrabajo) {
		this.ordenesDeTrabajo = ordenesDeTrabajo;
	}

	public List<VehiculoConOrdenDeTrabajoDTO> getVehiculosConOrdenDeTrabajo() {
		return vehiculosConOrdenDeTrabajo;
	}

	public void setVehiculosConOrdenDeTrabajo(List<VehiculoConOrdenDeTrabajoDTO> vehiculosConOrdenDeTrabajo) {
		this.vehiculosConOrdenDeTrabajo = vehiculosConOrdenDeTrabajo;
	}

	public List<ClienteDTO> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteDTO> clientes) {
		this.clientes = clientes;

	}

}
