package dto.temporal;

public class ModalidadVentaVehiculoDTO {
	
	private boolean efectivo;
	
	private String financiera;
	
	private String nroCuotas;
	
	private String montoCuota;
	
	private String precioFinal;
	
	private String comision;
	
	public ModalidadVentaVehiculoDTO() {
	}

	public boolean isEfectivo() {
		return efectivo;
	}

	public void setEfectivo(boolean efectivo) {
		this.efectivo = efectivo;
	}

	public String getFinanciera() {
		return financiera;
	}

	public void setFinanciera(String financiera) {
		this.financiera = financiera;
	}

	public String getNroCuotas() {
		return nroCuotas;
	}

	public void setNroCuotas(String nroCuotas) {
		this.nroCuotas = nroCuotas;
	}

	public String getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(String montoCuota) {
		this.montoCuota = montoCuota;
	}

	public String getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(String precioFinal) {
		this.precioFinal = precioFinal;
	}
	
	public String getComision() {
		return comision;
	}

	public void setComision(String comision) {
		this.comision = comision;
	}

	@Override
	public String toString() {
		return "ModalidadVentaVehiculoDTO [efectivo=" + efectivo + ", financiera=" + financiera + ", nroCuotas="
				+ nroCuotas + ", montoCuota=" + montoCuota + ", precioFinal=" + precioFinal + "]";
	}
}
