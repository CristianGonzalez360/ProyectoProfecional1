package dto;

public class DatosPersonalesDTO {
	
	private Integer id;
	
	private String nombreCompleto;
	
	private Integer dni;
	
	private String telefono;
	
	private String email;
	
	private String calle;
	
	private Integer altura;
	
	private Integer piso;
	
	private String dpto;
	
	private String localidad;

	public DatosPersonalesDTO() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public Integer getDni() {
		return this.dni;
	}
	
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}	

	public Integer getPiso() {
		return piso;
	}

	public void setPiso(Integer piso) {
		this.piso = piso;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Override
	public String toString() {
		return "DatosPersonalesDTO [id=" + id + ", nombreCompleto=" + nombreCompleto + ", dni=" + dni + ", telefono="
				+ telefono + ", email=" + email + ", calle=" + calle + ", altura=" + altura + ", piso=" + piso
				+ ", dpto=" + dpto + ", localidad=" + localidad + "]";
	}

	public DatosPersonalesDTO makeTestDTO() {
		DatosPersonalesDTO datos = new DatosPersonalesDTO();
		datos.setId(2);
		datos.setNombreCompleto("u001");
		datos.setDni(2233);
		datos.setTelefono("1111");
		datos.setEmail("u001@mail.com");
		datos.setCalle("calle");
		datos.setAltura(2);
		datos.setPiso(1);
		datos.setDpto("A");
		datos.setLocalidad("loc");
		return datos;
	}	
	
}
