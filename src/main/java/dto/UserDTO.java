package dto;

public class UserDTO {

	private Integer id;
	
	private CuentaDTO cuenta;
	
	private DatosPersonalesDTO datos;
	
	public UserDTO(Integer id, CuentaDTO cuenta, DatosPersonalesDTO datos) {
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
}
