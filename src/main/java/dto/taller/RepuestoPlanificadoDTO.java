
package dto.taller;

public class RepuestoPlanificadoDTO {

	private Integer idRepuestoPlanificado;

	private Integer idRepuesto;

	private Integer idPresu;

	private Integer cantRequerida;

	private RepuestoDTO repuesto;
	
	private Double precio;

	public RepuestoPlanificadoDTO() {

	}

	public RepuestoPlanificadoDTO(RepuestoMantenimientoDTO repuesto2) {
		this.idRepuesto = repuesto2.getIdRepuesto();
		this.cantRequerida = repuesto2.getCantRequerida();
		this.repuesto = repuesto2.getRepuesto();
		this.precio = repuesto2.getRepuesto().getPrecioRepuesto();
	}

	public Integer getIdPresu() {
		return idPresu;
	}

	public void setIdPresu(Integer idPresu) {
		this.idPresu = idPresu;
	}

	public Integer getIdRepuestoPlanificado() {
		return idRepuestoPlanificado;
	}

	public Integer getCantRequerida() {
		return cantRequerida;
	}

	public void setCantRequerida(Integer cantRequerida) {
		this.cantRequerida = cantRequerida;
	}

	public void setIdRepuestoPlanificado(Integer idRepuesto) {
		this.idRepuestoPlanificado = idRepuesto;
	}

	@Override
	public String toString() {
		return "RepuestoPlanificadoDTO [idPresu=" + idPresu + ", idRepuesto=" + idRepuesto + ", cantRequerida="
				+ cantRequerida + "]";
	}

	public RepuestoDTO getRepuesto() {
		return repuesto;
	}

	public void setRepuesto(RepuestoDTO repuesto) {
		this.repuesto = repuesto;
		this.idRepuesto = repuesto.getIdRepuesto();
		this.precio = repuesto.getPrecioRepuesto();
	}

	public Integer getIdRepuesto() {
		return idRepuesto;
	}

	public void setIdRepuesto(Integer idRepuesto) {
		this.idRepuesto = idRepuesto;
	}

	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if (obj instanceof RepuestoPlanificadoDTO) {
			RepuestoPlanificadoDTO rp = (RepuestoPlanificadoDTO) obj;
			ret = this.getIdRepuestoPlanificado().equals(rp.getIdRepuestoPlanificado());
		}
		return ret;
	}

	public Double getPrecioTotal() {
		return repuesto.getPrecioRepuesto() * cantRequerida;
	}

	public String getDescripcion() {
		return repuesto.getDescripcionRepuesto();
	}

	public Double getPrecio() {
		return this.precio;
	}

	public String getMarca() {
		return repuesto.getMarcaRepuesto();
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
}
