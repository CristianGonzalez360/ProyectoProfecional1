package dto;

public class CaracteristicaVehiculoDTO {

	private Integer idCaracteristica;

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

	public CaracteristicaVehiculoDTO() {
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

	public Integer getIdCaracteristica() {
		return idCaracteristica;
	}

	public void setIdCaracteristica(Integer idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}

	public CaracteristicaVehiculoDTO makeTestDTO() {
		CaracteristicaVehiculoDTO dto = new CaracteristicaVehiculoDTO();
		dto.setCilindrada("1.58");
		dto.setMotor("1.6 MSI nafta");
		dto.setTransmision("Manual 5 velocidades");
		dto.setDireccion("eléctrica asistida");
		dto.setPotencia("100 CV");
		dto.setFrenosDelanteros("Discos ventilados");
		dto.setFrenosTraseros("a tambor");
		dto.setTorqueMaximo("155/4000");
		dto.setVolumenBaul("300 cc");
		dto.setCantidadPuertas("3");
		dto.setPrecio("1000000");
		return dto;
	}

	@Override
	public String toString() {
		return "CaracteristicaVehiculoDTO [idCaracteristica=" + idCaracteristica + ", cilindrada=" + cilindrada
				+ ", motor=" + motor + ", transmision=" + transmision + ", direccion=" + direccion + ", potencia="
				+ potencia + ", frenosDelanteros=" + frenosDelanteros + ", frenosTraseros=" + frenosTraseros
				+ ", torqueMaximo=" + torqueMaximo + ", volumenBaul=" + volumenBaul + ", cantidadPuertas="
				+ cantidadPuertas + ", precio=" + precio + "]";
	}
}
