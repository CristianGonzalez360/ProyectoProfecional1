package dto;

public class GarantiaVehiculoDTO {
	
	private Integer id;
	
	private String aniosDeGarantia;
	
	private String kilometrajeInicialDelVehiculo;
	
	private String kilometrajeGarantizado;
	
	private String fechaInicioDeLaGarantia;
	
	private String fechaDeCaducidadDeLaGarantia;
	
	private String costoFinalConIVA;

	public GarantiaVehiculoDTO() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAniosDeGarantia() {
		return aniosDeGarantia;
	}

	public void setAniosDeGarantia(String aniosDeGarantia) {
		this.aniosDeGarantia = aniosDeGarantia;
	}

	public String getKilometrajeInicialDelVehiculo() {
		return kilometrajeInicialDelVehiculo;
	}

	public void setKilometrajeInicialDelVehiculo(String kilometrajeInicialDelVehiculo) {
		this.kilometrajeInicialDelVehiculo = kilometrajeInicialDelVehiculo;
	}

	public String getKilometrajeGarantizado() {
		return kilometrajeGarantizado;
	}

	public void setKilometrajeGarantizado(String kilometrajeGarantizado) {
		this.kilometrajeGarantizado = kilometrajeGarantizado;
	}

	public String getFechaInicioDeLaGarantia() {
		return fechaInicioDeLaGarantia;
	}

	public void setFechaInicioDeLaGarantia(String fechaInicioDeLaGarantia) {
		this.fechaInicioDeLaGarantia = fechaInicioDeLaGarantia;
	}

	public String getFechaDeCaducidadDeLaGarantia() {
		return fechaDeCaducidadDeLaGarantia;
	}

	public void setFechaDeCaducidadDeLaGarantia(String fechaDeCaducidadDeLaGarantia) {
		this.fechaDeCaducidadDeLaGarantia = fechaDeCaducidadDeLaGarantia;
	}

	public String getCostoFinalConIVA() {
		return costoFinalConIVA;
	}

	public void setCostoFinalConIVA(String costoFinalConIVA) {
		this.costoFinalConIVA = costoFinalConIVA;
	}
}
