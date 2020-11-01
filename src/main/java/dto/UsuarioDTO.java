package dto;

import java.util.Date;

public class UsuarioDTO {

	private Integer id;
	
	private CuentaDTO cuenta;
	
	private DatosPersonalesDTO datos;
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Integer id, CuentaDTO cuenta, DatosPersonalesDTO datos) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.datos = datos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CuentaDTO getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaDTO cuenta) {
		this.cuenta = cuenta;
	}

	public DatosPersonalesDTO getDatos() {
		return datos;
	}

	public void setDatos(DatosPersonalesDTO datos) {
		this.datos = datos;
	}

	public UsuarioDTO makeTestDTO() {
		DatosPersonalesDTO datos = new DatosPersonalesDTO()
				.setNombreCompleto("u001")
				.setDni(2233)
				.setTelefono("1111")
				.setEmail("u001@mail.com")
				.setCalle("calle")
				.setAltura(2)
				.setPiso(1)
				.setDpto("A")
				.setLocalidad("loc");
		CuentaDTO cuenta = new CuentaDTO()
				.setFechaDeAlta(new Date())
				.setFechaDeBaja(new Date())
				.setNombreUsuario("u001")
				.setPassword("p001")
				.setRole("admin");
		UsuarioDTO usuario = new UsuarioDTO(null, cuenta, datos);
		return usuario;
	}
	
	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", cuenta=" + cuenta + ", datos=" + datos + "]";
	}
	
}
