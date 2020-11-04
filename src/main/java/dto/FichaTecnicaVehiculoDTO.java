package dto;

public class FichaTecnicaVehiculoDTO {

	private Integer id;

	private Integer nroChasis;

	private Integer nroMotor;

	private Integer kilometraje;

	private String marca;

	private Integer modelo;

	private String color;

	private String combustion;

	private String descripcion;

	private String patente;
	
	public FichaTecnicaVehiculoDTO() {
	}

	public FichaTecnicaVehiculoDTO(AltaDeVehiculoDTO vehiculoDeAlta) {
		this.nroChasis = Integer.parseInt(vehiculoDeAlta.getNroChasis());
		this.nroMotor = Integer.parseInt(vehiculoDeAlta.getNroMotor());
		this.kilometraje = Integer.parseInt(vehiculoDeAlta.getKilometraje());
		this.marca = vehiculoDeAlta.getMarca();
		this.modelo = Integer.parseInt(vehiculoDeAlta.getModelo());
		this.color = vehiculoDeAlta.getColor();
		this.combustion = vehiculoDeAlta.getCombustion();
		this.descripcion = vehiculoDeAlta.getDescripcion();
	}

	public FichaTecnicaVehiculoDTO makeTestDTO() {
		FichaTecnicaVehiculoDTO ret = new FichaTecnicaVehiculoDTO();
		ret.setNroChasis(4576);
		ret.setNroMotor(3456);
		ret.setKilometraje(7567);
		ret.setMarca("df");
		ret.setModelo(1977);
		ret.setColor("rojo");
		ret.setCombustion("nafta");
		ret.setDescripcion("Funciona correctamente");
		return ret;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNroChasis() {
		return nroChasis;
	}

	public void setNroChasis(Integer nroChasis) {
		this.nroChasis = nroChasis;
	}

	public Integer getNroMotor() {
		return nroMotor;
	}

	public void setNroMotor(Integer nroMotor) {
		this.nroMotor = nroMotor;
	}

	public Integer getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(Integer kilometraje) {
		this.kilometraje = kilometraje;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCombustion() {
		return combustion;
	}

	public void setCombustion(String combustion) {
		this.combustion = combustion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	@Override
	public String toString() {
		return "FichaTecnicaVehiculoDTO [id=" + id + ", nroChasis=" + nroChasis + ", nroMotor=" + nroMotor
				+ ", kilometraje=" + kilometraje + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color
				+ ", combustion=" + combustion + ", descripcion=" + descripcion + ", patente=" + patente + "]";
	}

}
