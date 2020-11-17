package services;

import java.util.LinkedList;
import java.util.List;

import dto.ClienteDTO;
import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.FacturaDTO;
import dto.FichaTecnicaVehiculoDTO;
import dto.OrdenDeTrabajoDTO;
import dto.PresupuestoDTO;
import dto.RepuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;
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

	private List<RepuestoDTO> repuestos;

	private List<PresupuestoDTO> presupuestos;

	private List<TrabajoPresupuestadoDTO> trabajos;

	private List<RepuestoPlanificadoDTO> repuestosPlanificados;
	
	private List<FacturaDTO> facturas;

	public DatabaseGraph() {
		cuentas = new LinkedList<>();
		datosPersonales = new LinkedList<>();
		usuarios = new LinkedList<>();
		turnos = new LinkedList<>();
		ordenesDeTrabajo = new LinkedList<>();
		vehiculosConOrdenDeTrabajo = new LinkedList<>();
		clientes = new LinkedList<>();
		repuestos = new LinkedList<>();
		presupuestos = new LinkedList<>();
		trabajos = new LinkedList<>();
		repuestosPlanificados = new LinkedList<>();
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

	public List<RepuestoDTO> getRepuestos() {
		return repuestos;
	}

	public void setRepuestos(List<RepuestoDTO> repuestos) {
		this.repuestos = repuestos;
	}

	public List<PresupuestoDTO> getPresupuestos() {
		return presupuestos;
	}

	public void setPresupuestos(List<PresupuestoDTO> presupuestos) {
		this.presupuestos = presupuestos;
	}

	public List<TrabajoPresupuestadoDTO> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(List<TrabajoPresupuestadoDTO> trabajos) {
		this.trabajos = trabajos;
	}

	public List<RepuestoPlanificadoDTO> getRepuestosPlanificados() {
		return repuestosPlanificados;
	}

	public void setRepuestosPlanificados(List<RepuestoPlanificadoDTO> repuestosPlanificados) {
		this.repuestosPlanificados = repuestosPlanificados;
	}

	public List<FacturaDTO> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<FacturaDTO> facturas) {
		this.facturas = facturas;
	}
}
