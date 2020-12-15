package dto.temporal;

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
		errors.addAll(
				new StringValidator(this.combustion).notBlank("El tipo de combustión es obligatoria.").validate());
		errors.addAll(new StringValidator(this.cilindrada).notBlank("Debe ingresar cilindrada.")
				.max(20, "Cilindrada no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.motor).notBlank("Debe ingresar motor.")
				.max(20, "Motor no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.transmision).notBlank("Debe ingresar transmision.")
				.max(30, "Transmision no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.direccion).notBlank("Debe ingresar direccion.")
				.max(26, "Direccion no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.potencia).notBlank("Debe ingresar potencia.")
				.max(20, "Potencia no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.frenosDelanteros).notBlank("Debe ingresar frenosDelanteros.")
				.max(20, "Frenos Delanteros no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.frenosTraseros).notBlank("Debe ingresar frenosTraseros.")
				.max(20, "Frenos Traseros no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.torqueMaximo).notBlank("Debe ingresar torqueMaximo.")
				.max(20, "Torque maximo no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.volumenBaul).notBlank("Debe ingresar volumenBaul.")
				.max(20, "Volumen de baúl no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.cantidadPuertas).number("Cantidad de Puertas debe ser un número.")
				.notBlank("Debe ingresar cantidadPuertas.")
				.max(20, "Cantidad de puertas no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.familia).notBlank("Debe ingresar familia.")
				.max(20, "Familia no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.linea).notBlank("Debe ingresar linea.")
				.max(20, "Linea no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.precio).notBlank("Debe ingresar el Precio de Compra.")
				.positiveDouble("Precio de Compra debe ser un número")
				.max(20, "Precio de Compra no puede tener mas de 20 caracteres").validate());
		errors.addAll(new StringValidator(this.precioVenta).notBlank("Debe ingresar Precio de Venta.")
				.positiveDouble("Precio de Venta debe ser un número")
				.max(20, "Precio de Venta no puede tener mas de 20 caracteres").validate());
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
