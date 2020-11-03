package dto;

import java.util.List;

public class AltaOrdenDeTrabajoDTO {

	private String trabajoSugerido;
	
	private String trabajoSolicitado;
	
	private String tipoDeTrabajo;
	
	public AltaOrdenDeTrabajoDTO() {
		super();
	}

	public String getTrabajoSugerido() {
		return trabajoSugerido;
	}

	public void setTrabajoSugerido(String trabajoSugerido) {
		this.trabajoSugerido = trabajoSugerido;
	}

	public String getTrabajoSolicitado() {
		return trabajoSolicitado;
	}

	public void setTrabajoSolicitado(String trabajoSolicitado) {
		this.trabajoSolicitado = trabajoSolicitado;
	}

	public String getTipoDeTrabajo() {
		return tipoDeTrabajo;
	}

	public void setTipoDeTrabajo(String tipoDeTrabajo) {
		this.tipoDeTrabajo = tipoDeTrabajo;
	}

	public List<String> validate() {
		// TODO Auto-generated method stub
		return null;
	}
}
