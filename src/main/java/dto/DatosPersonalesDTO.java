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

	public DatosPersonalesDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public DatosPersonalesDTO setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
		return this;
	}

	public Integer getDni() {
		return this.dni;
	}
	
	public DatosPersonalesDTO setDni(Integer dni) {
		this.dni = dni;
		return this;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public DatosPersonalesDTO setTelefono(String telefono) {
		this.telefono = telefono;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public DatosPersonalesDTO setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getCalle() {
		return calle;
	}

	public DatosPersonalesDTO setCalle(String calle) {
		this.calle = calle;
		return this;
	}

	public Integer getAltura() {
		return altura;
	}

	public DatosPersonalesDTO setAltura(Integer altura) {
		this.altura = altura;
		return this;
	}	

	public Integer getPiso() {
		return piso;
	}

	public DatosPersonalesDTO setPiso(Integer piso) {
		this.piso = piso;
		return this;
	}

	public String getDpto() {
		return dpto;
	}

	public DatosPersonalesDTO setDpto(String dpto) {
		this.dpto = dpto;
		return this;
	}

	public String getLocalidad() {
		return localidad;
	}

	public DatosPersonalesDTO setLocalidad(String localidad) {
		this.localidad = localidad;
		return this;
	}

	@Override
	public String toString() {
		return "DatosPersonalesDTO [id=" + id + ", nombreCompleto=" + nombreCompleto + ", dni=" + dni + ", telefono="
				+ telefono + ", email=" + email + ", calle=" + calle + ", altura=" + altura + ", piso=" + piso
				+ ", dpto=" + dpto + ", localidad=" + localidad + "]";
	}	
	
}
