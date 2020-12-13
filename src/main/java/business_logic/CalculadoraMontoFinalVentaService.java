package business_logic;

import dto.temporal.ModalidadVentaVehiculoDTO;

public class CalculadoraMontoFinalVentaService {
	
	private static final int PORCENTAJE_COMISION = 3;
		
	private final ModalidadVentaVehiculoDTO mod;
	
	public CalculadoraMontoFinalVentaService(ModalidadVentaVehiculoDTO modalidad) {
		this.mod = modalidad;
	}
	
	public Double calcularComision() {
		return Double.parseDouble(mod.getMontoFinanciado())*PORCENTAJE_COMISION / 100;
	}

	public Double getPrecioFinalVenta() {
		Double precioFinal = Double.parseDouble(mod.getMontoFinanciado());
		Double costoGarantia = Double.parseDouble(mod.getCostoGarantia());
		return precioFinal + costoGarantia;
	}
	
	public String calcularMontoCuota() {
		Double montoCuota = Double.parseDouble(mod.getMontoFinanciado()) + Double.parseDouble(mod.getCostoGarantia()) / Integer.parseInt(mod.getNroCuotas());
		return montoCuota.toString();
	}
	
	public Double calcularCostoGarantiaExtendida() {
		Double costo = Double.parseDouble(mod.getCostoGarantia());
		return (costo*2)/3 + costo;
	}
}
