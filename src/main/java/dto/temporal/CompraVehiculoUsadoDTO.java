package dto.temporal;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class CompraVehiculoUsadoDTO {

	private String nroChasis;
	private String nroMotor;
	private String kilometraje;
	private String marca;
	private String modelo;
	private String patente;
	private String color;
	private String combustion;
	private String asegurador;
	private String nroPolizaSeguro;
	private String kilometrajeGarantia;
	private String cilindrada;
	private String motor;
	private String transmision;
	private String direccion;
	private String potencia;
	private String frenosDelanteros;
	private String frenosTraseros;
	private String torqueMaximo;
	private String volumenBaul;
	private String cantidadPuertas;
	private String precio;
	private String precioVenta;
	private String familia;
	private String linea;
	
	
	public List<String> validate() {
		List<String> errors = new LinkedList<>();
		errors.addAll(new StringValidator(this.nroChasis).number("El número de chasis debe ser un número").validate());
		errors.addAll(new StringValidator(this.nroMotor).number("El número de motor debe ser un número").validate());
		errors.addAll(new StringValidator(this.kilometraje).number("El kilometraje debe ser un número").validate());
		errors.addAll(new StringValidator(this.marca).notBlank("La marca es obligatoria.").validate());
		errors.addAll(new StringValidator(this.modelo).number("El modelo debe ser un número").validate());
		errors.addAll(new StringValidator(this.patente).notBlank("La patente es obligatoria").validate());
		errors.addAll(new StringValidator(this.color).notBlank("El color es obligatorio.").validate());
		errors.addAll(new StringValidator(this.combustion).notBlank("El tipo de combustión es obligatoria.").validate());
		errors.addAll(new StringValidator(this.asegurador).notBlank("El asegurador es obligatorio").validate());
		errors.addAll(new StringValidator(this.nroPolizaSeguro).number("El nro. de la poliza debe ser un número").validate());
		errors.addAll(new StringValidator(this.kilometrajeGarantia).number("El kilometraje en garantía debe ser un número").validate());
		//TODO
		return errors;
	}
	
	public String getNroChasis() {
		return nroChasis;
	}
	public void setNroChasis(String nroChasis) {
		this.nroChasis = nroChasis;
	}
	public String getNroMotor() {
		return nroMotor;
	}
	public void setNroMotor(String nroMotor) {
		this.nroMotor = nroMotor;
	}
	public String getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(String kilometraje) {
		this.kilometraje = kilometraje;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCombustion() {
		return combustion;
	}
	public void setCombustion(String combustion) {
		this.combustion = combustion;
	}
	public String getAsegurador() {
		return asegurador;
	}
	public void setAsegurador(String asegurador) {
		this.asegurador = asegurador;
	}
	public String getNroPolizaSeguro() {
		return nroPolizaSeguro;
	}
	public void setNroPolizaSeguro(String nroPolizaSeguro) {
		this.nroPolizaSeguro = nroPolizaSeguro;
	}
	public String getKilometrajeGarantia() {
		return kilometrajeGarantia;
	}
	public void setKilometrajeGarantia(String kilometrajeGarantia) {
		this.kilometrajeGarantia = kilometrajeGarantia;
	}
	public String getCilindrada() {
		return cilindrada;
	}
	public void setCilindrada(String cilindrada) {
		this.cilindrada = cilindrada;
	}
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public String getTransmision() {
		return transmision;
	}
	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPotencia() {
		return potencia;
	}
	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}
	public String getFrenosDelanteros() {
		return frenosDelanteros;
	}
	public void setFrenosDelanteros(String frenosDelanteros) {
		this.frenosDelanteros = frenosDelanteros;
	}
	public String getFrenosTraseros() {
		return frenosTraseros;
	}
	public void setFrenosTraseros(String frenosTraseros) {
		this.frenosTraseros = frenosTraseros;
	}
	public String getTorqueMaximo() {
		return torqueMaximo;
	}
	public void setTorqueMaximo(String torqueMaximo) {
		this.torqueMaximo = torqueMaximo;
	}
	public String getVolumenBaul() {
		return volumenBaul;
	}
	public void setVolumenBaul(String volumenBaul) {
		this.volumenBaul = volumenBaul;
	}
	public String getCantidadPuertas() {
		return cantidadPuertas;
	}
	public void setCantidadPuertas(String cantidadPuertas) {
		this.cantidadPuertas = cantidadPuertas;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
}
