package dto;

import dto.taller.FichaTecnicaVehiculoDTO;

public class VehiculoParaVentaDTO {
	
	private Integer id;
	
	private String marca;
	
	private String familia;
	
	private String linea;
	
	private String tipo;
	
	private String colorVehiculo;

	private String nombreSucursal;
	
	private CaracteristicaVehiculoDTO caracteristicas;
	
	private FichaTecnicaVehiculoDTO fichaTecnica;
	
	public VehiculoParaVentaDTO() {
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getColorVehiculo() {
		return colorVehiculo;
	}

	public void setColorVehiculo(String colorVehiculo) {
		this.colorVehiculo = colorVehiculo;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public CaracteristicaVehiculoDTO getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(CaracteristicaVehiculoDTO caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public FichaTecnicaVehiculoDTO getFichaTecnica() {
		return fichaTecnica;
	}

	public void setFichaTecnica(FichaTecnicaVehiculoDTO fichaTecnica) {
		this.fichaTecnica = fichaTecnica;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VehiculoParaVentaDTO makeTestDTO() {
		VehiculoParaVentaDTO dto = new VehiculoParaVentaDTO();
		dto.setId(11111);
		dto.setMarca("Wolkswagen");
		dto.setFamilia("Polo");
		dto.setLinea("Trend");
		dto.setTipo("Nuevo");
		dto.setColorVehiculo("Verde París");
		dto.setNombreSucursal("Martinez nro. 1");
		dto.setCaracteristicas(new CaracteristicaVehiculoDTO().makeTestDTO());
		dto.setFichaTecnica(new FichaTecnicaVehiculoDTO().makeTestDTO());
		return dto;
	}
}