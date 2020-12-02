package business_logic;

import dto.temporal.ModalidadVentaVehiculoDTO;

public class CalculadoraMontoFinalVentaService {
	
	private static final int PORCENTAJE_COMISION = 3;
	
	private static final int IVA = 21;
	
	private final ModalidadVentaVehiculoDTO mod;
	
	public CalculadoraMontoFinalVentaService(ModalidadVentaVehiculoDTO modalidad) {
		this.mod = modalidad;
	}
	
	public Double calcularComision() {
		return Double.parseDouble(mod.getMontoFinanciado())*PORCENTAJE_COMISION / 100;
	}

	public Double getPrecioFinalVenta() {
		Double precioFinal = Double.parseDouble(mod.getMontoFinanciado());
		Double impuesto = (precioFinal * IVA)/100;
		return precioFinal + impuesto;
	}

	public String calcularMontoCuota() {
		Double montoCuota = Double.parseDouble(mod.getMontoFinanciado()) / Integer.parseInt(mod.getNroCuotas());
		return montoCuota.toString();
	}
}
