package services;

import java.util.LinkedList;
import java.util.List;

import dto.CaracteristicaVehiculoDTO;
import dto.ClienteDTO;
import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.GarantiaVehiculoDTO;
import dto.MonedaDTO;
import dto.SucursalDTO;
import dto.UsuarioDTO;
import dto.VehiculoDTO;
import dto.VentaVehiculoDTO;
import dto.taller.FacturaDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.PresupuestoDTO;
import dto.taller.RepuestoDTO;
import dto.taller.RepuestoPlanificadoDTO;
import dto.taller.TrabajoPresupuestadoDTO;
import dto.taller.TurnoDTO;
import dto.taller.VehiculoConOrdenDeTrabajoDTO;

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

	private List<VentaVehiculoDTO> ventaVehiculo;

	private List<CaracteristicaVehiculoDTO> caracteristicaVehiculo;

	private List<VehiculoDTO> vehiculos;

	private List<MonedaDTO> monedas;

	private List<SucursalDTO> sucursales;

	private List<GarantiaVehiculoDTO> garantias;

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
		ventaVehiculo = new LinkedList<>();
		caracteristicaVehiculo = new LinkedList<>();
		vehiculos = new LinkedList<>();
		monedas = new LinkedList<>();
		sucursales = new LinkedList<>();
		garantias = new LinkedList<>();
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

	public List<VentaVehiculoDTO> getVentaVehiculo() {
		return ventaVehiculo;
	}

	public void setVentaVehiculo(List<VentaVehiculoDTO> ventaVehiculo) {
		this.ventaVehiculo = ventaVehiculo;
	}

	public List<CaracteristicaVehiculoDTO> getCaracteristicaVehiculo() {
		return caracteristicaVehiculo;
	}

	public void setCaracteristicaVehiculo(List<CaracteristicaVehiculoDTO> caracteristicaVehiculo) {
		this.caracteristicaVehiculo = caracteristicaVehiculo;
	}

	public List<VehiculoDTO> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<VehiculoDTO> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<MonedaDTO> getMonedas() {
		return monedas;
	}

	public void setMonedas(List<MonedaDTO> monedas) {
		this.monedas = monedas;
	}

	public List<SucursalDTO> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<SucursalDTO> sucursales) {
		this.sucursales = sucursales;
	}

	public List<GarantiaVehiculoDTO> getGarantias() {
		return garantias;
	}

	public void setGarantias(List<GarantiaVehiculoDTO> garantias) {
		this.garantias = garantias;
	}
}
