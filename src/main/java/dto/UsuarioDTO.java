package dto;

import java.util.Date;
import java.util.List;

public class UsuarioDTO {

	private Integer id;

	private CuentaDTO cuenta;

	private DatosPersonalesDTO datos;

	private SucursalDTO sucursal;

	public UsuarioDTO() {

	}

	public UsuarioDTO(Integer id, CuentaDTO cuenta, DatosPersonalesDTO datos) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.datos = datos;
	}

	public UsuarioDTO makeTestDTO(CuentaDTO cuenta, DatosPersonalesDTO datos) {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setCuenta(cuenta);
		usuario.setDatosPersonales(datos);
		return usuario;
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

	public void setDatosPersonales(DatosPersonalesDTO datos) {
		this.datos = datos;
	}

	public void setDatos(DatosPersonalesDTO datos) {
		this.datos = datos;
	}

	public SucursalDTO getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}

	public List<String> validate() {
		List<String> lst = datos.validate();
		lst.addAll(cuenta.validate());
		return lst;
	}

	public static UsuarioDTO makeTestDTO() {
		DatosPersonalesDTO datos = new DatosPersonalesDTO();
		datos.setId(2);
		datos.setNombreCompleto("u066 user");
		datos.setDni(2233);
		datos.setTelefono("1111");
		datos.setEmail("u006@mail.com");
		datos.setCalle("calle");
		datos.setAltura("2");
		datos.setPiso("1");
		datos.setDpto("A");
		datos.setLocalidad("loc");
		CuentaDTO cuenta = new CuentaDTO();
		cuenta.setIdCuenta(1);
		cuenta.setFechaDeAlta(new Date());
		cuenta.setFechaDeBaja(new Date());
		cuenta.setNombreUsuario("u006");
		cuenta.setPassword("pu006");
		cuenta.setRole("admin");
		UsuarioDTO usuario = new UsuarioDTO(null, cuenta, datos);
		return usuario;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", cuenta=" + cuenta + ", datos=" + datos + "]";
	}

}
