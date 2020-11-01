package services;

import java.util.LinkedList;
import java.util.List;

import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.PaisDTO;
import dto.UsuarioDTO;

public class DatabaseGraph {

	private List<PaisDTO> paisesList;
	
	private List<CuentaDTO> cuentasList;
	
	private List<DatosPersonalesDTO> datosPersonalesList;
	
	private List<UsuarioDTO> usuariosList;
	
	public DatabaseGraph() {
		setPaisesList(new LinkedList<>());
		setCuentasList(new LinkedList<>());
		setDatosPersonalesList(new LinkedList<>());
		setUsuariosList(new LinkedList<>());
	}

	public List<PaisDTO> getPaisesList() {
		return paisesList;
	}

	public void setPaisesList(List<PaisDTO> paisesList) {
		this.paisesList = paisesList;
	}

	public List<CuentaDTO> getCuentasList() {
		return cuentasList;
	}

	public void setCuentasList(List<CuentaDTO> cuentasList) {
		this.cuentasList = cuentasList;
	}

	public List<DatosPersonalesDTO> getDatosPersonalesList() {
		return datosPersonalesList;
	}

	public void setDatosPersonalesList(List<DatosPersonalesDTO> datosPersonalesList) {
		this.datosPersonalesList = datosPersonalesList;
	}

	public List<UsuarioDTO> getUsuariosList() {
		return usuariosList;
	}

	public void setUsuariosList(List<UsuarioDTO> usuariosList) {
		this.usuariosList = usuariosList;
	}
}
