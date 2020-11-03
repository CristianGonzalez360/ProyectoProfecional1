package dto;

public class VehiculoConOrdenDeTrabajoDTO {

	private Integer id;
	
	private Integer idFichaTecnica;
	
	private Integer idCliente;
	
	private Integer kilometrajeGarantia;
	
	private String aseguradora;
	
	private Integer nroPolizaSeguro;
	
	private String patente;

	public VehiculoConOrdenDeTrabajoDTO() {}
	
	public VehiculoConOrdenDeTrabajoDTO(AltaDeVehiculoDTO vehiculoDeAlta) {
		this.kilometrajeGarantia = Integer.parseInt(vehiculoDeAlta.getKilometrajeGarantia());
		this.aseguradora = vehiculoDeAlta.getAsegurador();
		this.nroPolizaSeguro = Integer.parseInt(vehiculoDeAlta.getNroPolizaSeguro());
		this.patente = vehiculoDeAlta.getPatente();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdFichaTecnica() {
		return idFichaTecnica;
	}

	public void setIdFichaTecnica(Integer idFichaTecnica) {
		this.idFichaTecnica = idFichaTecnica;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getKilometrajeGarantia() {
		return kilometrajeGarantia;
	}

	public void setKilometrajeGarantia(Integer kilometrajeGarantia) {
		this.kilometrajeGarantia = kilometrajeGarantia;
	}

	public String getAseguradora() {
		return aseguradora;
	}

	public void setAseguradora(String aseguradora) {
		this.aseguradora = aseguradora;
	}

	public Integer getNroPolizaSeguro() {
		return nroPolizaSeguro;
	}

	public void setNroPolizaSeguro(Integer nroPolizaSeguro) {
		this.nroPolizaSeguro = nroPolizaSeguro;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

}