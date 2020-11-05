package dto;

public class RepuestoPlanificadoDTO {
	
	private Integer idRepuestoPlanificado;
	private Integer idPresu;
	private Integer idRepuesto;
	private Integer cantRequerida;
	
	public RepuestoPlanificadoDTO () {
		
	}
	
	public Integer getIdPresu() {
		return idPresu;
	}
	public void setIdPresu(Integer idPresu) {
		this.idPresu = idPresu;
	}
	public Integer getIdRepuesto() {
		return idRepuesto;
	}
	public void setIdRepuesto(Integer idRepuesto) {
		this.idRepuesto = idRepuesto;
	}
	public Integer getCantRequerida() {
		return cantRequerida;
	}
	public void setCantRequerida(Integer cantRequerida) {
		this.cantRequerida = cantRequerida;
	}
	@Override
	public String toString() {
		return "RepuestoPlanificadoDTO [idPresu=" + idPresu + ", idRepuesto=" + idRepuesto + ", cantRequerida="
				+ cantRequerida + "]";
	}

	public Integer getIdRepuestoPlanificado() {
		return idRepuestoPlanificado;
	}

	public void setIdRepuestoPlanificado(Integer idRepuestoPlanificado) {
		this.idRepuestoPlanificado = idRepuestoPlanificado;
	}
	
}
