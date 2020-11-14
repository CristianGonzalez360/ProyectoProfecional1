package dto;

public class EntregaDeVehiculoDTO {

	private Integer dniCliente;
	private String nombreCompleto;

	private String marcaAuto;
	private String modeloAuto;
	private String colorAuto;
	private String patenteAuto;

	private Integer idOrdenDeTrabajo;

	public EntregaDeVehiculoDTO(Integer dniCliente, String nombreCompleto, String marcaAuto, String modeloAuto,
			String colorAuto, String patenteAuto, Integer idOrdenDeTrabajo) {
		super();
		this.dniCliente = dniCliente;
		this.nombreCompleto = nombreCompleto;
		this.marcaAuto = marcaAuto;
		this.modeloAuto = modeloAuto;
		this.colorAuto = colorAuto;
		this.patenteAuto = patenteAuto;
		this.idOrdenDeTrabajo = idOrdenDeTrabajo;
	}

	public Integer getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(Integer dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getMarcaAuto() {
		return marcaAuto;
	}

	public void setMarcaAuto(String marcaAuto) {
		this.marcaAuto = marcaAuto;
	}

	public String getModeloAuto() {
		return modeloAuto;
	}

	public void setModeloAuto(String modeloAuto) {
		this.modeloAuto = modeloAuto;
	}

	public String getColorAuto() {
		return colorAuto;
	}

	public void setColorAuto(String colorAuto) {
		this.colorAuto = colorAuto;
	}

	public String getPatenteAuto() {
		return patenteAuto;
	}

	public void setPatenteAuto(String patenteAuto) {
		this.patenteAuto = patenteAuto;
	}

	public Integer getIdOrdenDeTrabajo() {
		return idOrdenDeTrabajo;
	}

	public void setIdOrdenDeTrabajo(Integer idOrdenDeTrabajo) {
		this.idOrdenDeTrabajo = idOrdenDeTrabajo;
	}

}
