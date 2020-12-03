package dto.temporal;

import java.text.SimpleDateFormat;
import java.util.Date;

import dto.SucursalDTO;
import dto.UsuarioDTO;

public class SessionDTO {

	private Date initSession;

	private UsuarioDTO user;

	private SucursalDTO sucursal;
	
	public SessionDTO() {
	}

	public String getFecha() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(initSession);
		return formattedDate;
	}
	
	public Date getInitSession() {
		return initSession;
	}

	public UsuarioDTO getUser() {
		return user;
	}

	public void setInitSession(Date initSession) {
		this.initSession = initSession;
	}

	public String getRole() {
		return user.getCuenta().getRole();
	}

	public String getNombreUsuario() {
		return user.getDatos().getNombreCompleto();
	}

	public Integer getIdUsuario() {
		return this.user.getId();
	}

	public void setUser(UsuarioDTO user) {
		this.user = user;
	}

	public Integer getIdSucursal() {
		return sucursal.getIdSucursal();
	}

	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}
	
	public SucursalDTO getSucursal() {
		return this.sucursal;
	}
}
