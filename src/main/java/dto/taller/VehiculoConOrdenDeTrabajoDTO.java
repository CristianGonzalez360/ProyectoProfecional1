package dto.taller;

import dto.temporal.AltaDeVehiculoDTO;

public class VehiculoConOrdenDeTrabajoDTO {

	private Integer id;

	private Integer idFichaTecnica;

	private Integer idCliente;

	private Integer kilometrajeGarantia;

	private String aseguradora;

	private Integer nroPolizaSeguro;

	private String patente;
	
	private Integer idVehiculo;

	public VehiculoConOrdenDeTrabajoDTO() {
	}

	public VehiculoConOrdenDeTrabajoDTO makeTestDTO() {
		VehiculoConOrdenDeTrabajoDTO ret = new VehiculoConOrdenDeTrabajoDTO();
		ret.setIdCliente(10);
		ret.setIdFichaTecnica(4);
		ret.setKilometrajeGarantia(10000);
		ret.setAseguradora("La Nueva");
		ret.setNroPolizaSeguro(0012);
		ret.setPatente("SOA152");
		return ret;
	}
	
	public VehiculoConOrdenDeTrabajoDTO(AltaDeVehiculoDTO vehiculoDeAlta) {
		if (vehiculoDeAlta.getKilometrajeGarantia() != null)
			this.kilometrajeGarantia = Integer.parseInt(vehiculoDeAlta.getKilometrajeGarantia());
		else
			this.kilometrajeGarantia = null;
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

	public Integer getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	@Override
	public String toString() {
		return "VehiculoConOrdenDeTrabajoDTO [id=" + id + ", idFichaTecnica=" + idFichaTecnica + ", idCliente="
				+ idCliente + ", kilometrajeGarantia=" + kilometrajeGarantia + ", aseguradora=" + aseguradora
				+ ", nroPolizaSeguro=" + nroPolizaSeguro + ", patente=" + patente + "]";
	}

}