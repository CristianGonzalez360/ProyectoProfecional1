package dto.temporal;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class AltaSucursalDTO {
	
	private String pais;
	
	private String moneda;
	
	private String calle;
	
	private String altura;
	
	private String localidad;

	public AltaSucursalDTO() {}	
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	public List<String> validate() {
		List<String> ret = new LinkedList<>();
		ret.addAll(new StringValidator(this.pais).notBlank("El país es obligatorio").validate());
		ret.addAll(new StringValidator(this.moneda).notBlank("La moneda es obligatoria").validate());
		ret.addAll(new StringValidator(this.localidad).notBlank("El localidad es obligatoria").validate());
		ret.addAll(new StringValidator(this.calle).notBlank("La calle es obligatorio").validate());
		ret.addAll(new StringValidator(this.altura).number("La altura debe ser un número").validate());
		return ret;
	}
}
