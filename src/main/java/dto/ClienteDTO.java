package dto;

import java.util.Date;

import java.util.LinkedList;
import java.util.List;

import dto.validators.CompositeValidator;

import java.util.List;


public class ClienteDTO {

	private Integer idCliente;

	private Integer idDatosPersonales;

	private DatosPersonalesDTO datosPersonalesDTO;

	private Date fechaAltaCliente;

	public ClienteDTO() {

	}

	public ClienteDTO(AltaClienteDTO altaCliente) {
		this.datosPersonalesDTO = new DatosPersonalesDTO();
		datosPersonalesDTO.setNombreCompleto(altaCliente.getNombreCompleto());
		datosPersonalesDTO.setDni(Integer.parseInt(altaCliente.getDni()));
		datosPersonalesDTO.setEmail(altaCliente.getEmail());
		datosPersonalesDTO.setTelefono(altaCliente.getTelefono());
		datosPersonalesDTO.setCalle(altaCliente.getCalle());
		datosPersonalesDTO.setAltura(altaCliente.getAltura());
		datosPersonalesDTO.setPiso(altaCliente.getPiso());
		datosPersonalesDTO.setDpto(altaCliente.getDpto());
		datosPersonalesDTO.setLocalidad(altaCliente.getLocalidad());
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdDatosPersonales() {
		return idDatosPersonales;
	}

	public void setIdDatosPersonales(Integer idDatosPersonales) {
		this.idDatosPersonales = idDatosPersonales;
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

	public ClienteDTO makeTestDTO() {
		// TODO Auto-generated method stub
		return new ClienteDTO();
	}

	@Override
	public String toString() {
		return "ClienteDTO [idCliente=" + idCliente + ", idDatosPersonales=" + idDatosPersonales
				+ ", datosPersonalesDTO=" + datosPersonalesDTO + ", fechaAltaCliente=" + fechaAltaCliente + "]";
	}

	public List<String> validate() {
		return this.datosPersonalesDTO.validate();
	}
}
