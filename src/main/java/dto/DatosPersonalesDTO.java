package dto;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class DatosPersonalesDTO {

	private Integer id;

	private String nombre;

	private String apellido;

	private Integer dni;

	private String telefono;

	private String email;

	private String calle;

	private String altura;

	private String piso;

	private String dpto;

	private String localidad;

	public DatosPersonalesDTO() {

	}

	public DatosPersonalesDTO makeTestDTO() {
		DatosPersonalesDTO datos = new DatosPersonalesDTO();
		datos.setNombreCompleto("u001");
		datos.setApellido("ap001");
		datos.setDni(33390111);
		datos.setTelefono("111134");
		datos.setEmail("u001@mail.com");
		datos.setCalle("calle");
		datos.setAltura("2");
		datos.setPiso("1");
		datos.setDpto("A");
		datos.setLocalidad("loc");
		return datos;
	}

	public List<String> validate() {
		List<String> errors = new LinkedList<>();
		errors.addAll(new StringValidator(nombre).notBlank("El nombre es obligatorio")
				.max(20, "Maximo una longitud de 20 caracteres para el nombre").validate());

		errors.addAll(new StringValidator(apellido).notBlank("El apellido es obligatorio")
				.max(20, "Maximo una longitud de 20 caracteres para el apellido").validate());
		if (this.dni == null) {
			errors.add("El dni es obligatorio");
		}
		errors.addAll(new StringValidator(telefono).number("El telefono debe ser un número").validate());
		if (!altura.trim().isEmpty())
			errors.addAll(new StringValidator(altura).max(2, "Maximo una longitud de 20 caracteres para la altura")
					.number("La altura debe ser un número").validate());
		if (!piso.trim().isEmpty())
			errors.addAll(new StringValidator(piso).number("El piso debe ser un número").validate());
		return errors;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombre;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombre = nombreCompleto;
	}

	public Integer getDni() {
		return dni;
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

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
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
		return "DatosPersonalesDTO [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
				+ ", telefono=" + telefono + ", email=" + email + ", calle=" + calle + ", altura=" + altura + ", piso="
				+ piso + ", dpto=" + dpto + ", localidad=" + localidad + "]";
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
