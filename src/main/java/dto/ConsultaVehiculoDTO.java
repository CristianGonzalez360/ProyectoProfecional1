package dto;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class ConsultaVehiculoDTO {
	
	private String tipo;
	
	private String marca;
	
	private String linea;
	
	private Integer Sucursal;
	
	public ConsultaVehiculoDTO() {
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public Integer getSucursal() {
		return Sucursal;
	}

	public void setSucursal(Integer sucursal) {
		Sucursal = sucursal;
	}

	public List<String> validate() {
		LinkedList<String> errors = new LinkedList<>();
		errors.addAll(new StringValidator(this.marca).notBlank("").validate());
		errors.addAll(new StringValidator(this.linea).notBlank("").validate());
		errors.addAll(new StringValidator(this.tipo).notBlank("").validate());
		return errors;
	}
}
