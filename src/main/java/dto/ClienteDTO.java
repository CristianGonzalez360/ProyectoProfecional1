package dto;

import java.util.Date;

public class ClienteDTO {
	
	private Integer idCliente;
	
	private Date fechaDeAlta;
	
	private DatosPersonalesDTO datosPersonales;

	public ClienteDTO() {
		
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFechaDeAlta() {
		return fechaDeAlta;
	}

	public void setFechaDeAlta(Date fechaDeAlta) {
		this.fechaDeAlta = fechaDeAlta;
	}

	public DatosPersonalesDTO getDatosPersonales() {
		return datosPersonales;
	}

	public void setDatosPersonales(DatosPersonalesDTO datosPersonales) {
		this.datosPersonales = datosPersonales;
	}
}
