package services;

import java.util.LinkedList;
import java.util.List;

import dto.taller.RepuestoDTO;

public class DatabaseGraphRepuesto {
	private List<RepuestoDTO> repuestos;
	
	public DatabaseGraphRepuesto() {
		repuestos = new LinkedList<>();
	}
	public List<RepuestoDTO> getRepuestos() {
		return repuestos;
	}

	public void setRepuestos(List<RepuestoDTO> repuestos) {
		this.repuestos = repuestos;
	}
}
