package services;

import java.util.LinkedList;
import java.util.List;

import dto.CuentaDTO;
import dto.DatosPersonalesDTO;
import dto.UsuarioDTO;

public class DatabaseGraph {

	private List<CuentaDTO> cuentas;
	
	private List<DatosPersonalesDTO> datosPersonales;
	
	private List<UsuarioDTO> usuarios;
	
	public DatabaseGraph() {
		cuentas = new LinkedList<>();
		datosPersonales = new LinkedList<>();
		usuarios = new LinkedList<>();
	}

	public List<CuentaDTO> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<CuentaDTO> cuentas) {
		this.cuentas = cuentas;
	}

	public List<DatosPersonalesDTO> getDatosPersonales() {
		return datosPersonales;
	}

	public void setDatosPersonales(List<DatosPersonalesDTO> datosPersonales) {
		this.datosPersonales = datosPersonales;
	}

	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}
	
}
