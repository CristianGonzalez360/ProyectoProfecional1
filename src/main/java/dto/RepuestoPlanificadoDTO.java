package dto;

public class RepuestoPlanificadoDTO {

	private Integer idRepuesto;
	
	private Integer idPresu;

	private Integer cantRequerida;

	public RepuestoPlanificadoDTO() {

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

	public Integer getCantRequerida() {
		return cantRequerida;
	}

	public void setCantRequerida(Integer cantRequerida) {
		this.cantRequerida = cantRequerida;
	}
	
	public void setIdRepuesto(Integer idRepuesto) {
		this.idRepuesto = idRepuesto;
	}

	@Override
	public String toString() {
		return "RepuestoPlanificadoDTO [idPresu=" + idPresu + ", idRepuesto=" + idRepuesto + ", cantRequerida="
				+ cantRequerida + "]";
	}
}
