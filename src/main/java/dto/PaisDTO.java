package dto;

import java.util.List;

import dto.validators.StringValidator;

public class PaisDTO {

	private Integer id;

	private String nombre;

	public PaisDTO() {
		setId(null);
		setNombre(null);
	}
	
	public PaisDTO(Integer id, String nombre) {
		setId(id);
		setNombre(nombre);
	}

	public List<String> validate() {
		return new StringValidator(nombre)
				.max(20, "Max 20 chars")
				.notBlank("El nombre del pais no puede estar en blanco")
				.validate();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
