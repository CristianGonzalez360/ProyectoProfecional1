package dto.taller;

public class RepuestoMantenimientoDTO {
	
	private Integer idRepuestoMantenimiento;
	private Integer idRepuesto;
	private Integer idMantenimiento;
	private Integer cantRequerida;
	private RepuestoDTO repuesto;

	public RepuestoMantenimientoDTO() {

	}

	public Integer getidMantenimiento() {
		return idMantenimiento;
	}

	public void setidMantenimiento(Integer idPresu) {
		this.idMantenimiento = idPresu;
	}

	public Integer getIdRepuestoMantenimiento() {
		return idRepuestoMantenimiento;
	}

	public Integer getCantRequerida() {
		return cantRequerida;
	}

	public void setCantRequerida(Integer cantRequerida) {
		this.cantRequerida = cantRequerida;
	}

	public void setIdRepuestoMantenimiento(Integer idRepuesto) {
		this.idRepuestoMantenimiento = idRepuesto;
	}

	@Override
	public String toString() {
		return "RepuestoPlanificadoDTO [idPresu=" + idMantenimiento + ", idRepuesto=" + idRepuesto + ", cantRequerida="
				+ cantRequerida + "]";
	}

	public RepuestoDTO getRepuesto() {
		return repuesto;
	}

	public void setRepuesto(RepuestoDTO repuesto) {
		this.repuesto = repuesto;
		this.idRepuesto = repuesto.getIdRepuesto();
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
		if(obj instanceof RepuestoMantenimientoDTO) {
			RepuestoMantenimientoDTO rp = (RepuestoMantenimientoDTO) obj;
			ret = this.getIdRepuestoMantenimiento().equals(rp.getIdRepuestoMantenimiento());
		}
		return ret;
	}
	
	public Double getPrecioTotal() {
		return repuesto.getPrecioRepuesto()*cantRequerida;
	}
	
	public String getDescripcion() {
		return repuesto.getDescripcionRepuesto();
	}
	
	public Double getPrecio() {
		return repuesto.getPrecioRepuesto();
	}
	
	public String getMarca() {
		return repuesto.getMarcaRepuesto();
	}
}
