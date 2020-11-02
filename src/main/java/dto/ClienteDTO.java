package dto;

import java.util.Date;

public class ClienteDTO {

	private int idCliente;
	private DatosPersonalesDTO datosPersonalesDTO;
	private Date fechaAltaCliente;
	
	public ClienteDTO() {

	}
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public DatosPersonalesDTO getDatosPersonalesDTO() {
		return datosPersonalesDTO;
	}
	public void setDatosPersonalesDTO(DatosPersonalesDTO datosPersonalesDTO) {
		this.datosPersonalesDTO = datosPersonalesDTO;
	}
	public Date getFechaAltaCliente() {
		return fechaAltaCliente;
	}
	public void setFechaAltaCliente(Date fechaAltaCliente) {
		this.fechaAltaCliente = fechaAltaCliente;
	}
	
}

