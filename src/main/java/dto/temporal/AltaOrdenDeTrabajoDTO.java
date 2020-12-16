package dto.temporal;

import java.util.LinkedList;
import java.util.List;

import dto.validators.StringValidator;

public class AltaOrdenDeTrabajoDTO {

	private String trabajoSugerido;

	private String trabajoSolicitado;

	private String tipoDeTrabajo;

	private String kilometrajeActual;
	
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

	public String getKilometrajeActual() {
		return kilometrajeActual;
	}

	public void setKilometrajeActual(String kilometrajeActual) {
		this.kilometrajeActual = kilometrajeActual;
	}

	public List<String> validate() {
		List<String> errors = new LinkedList<>();
		errors.addAll(new StringValidator(trabajoSolicitado).notBlank("El trabajo solicitado es requerido.")
				.max(60, "Como máximo 60 caracteres para el trabajo solicitado").validate());
		if (!this.trabajoSugerido.trim().isEmpty()) {
			errors.addAll(new StringValidator(this.trabajoSugerido)
					.max(60, "Como máximo 60 caracteres para el trabajo sugerido").validate());
		}
		errors.addAll(new StringValidator(this.kilometrajeActual).number("El kilometraje debe ser un numero").validate());
		return errors;
	}
}
