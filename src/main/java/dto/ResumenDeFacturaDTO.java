package dto;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ResumenDeFacturaDTO {

	private OrdenDeTrabajoDTO ordenDeTrabajo;
	
	private FacturaDTO factura;
	
	private List<RepuestoPlanificadoDTO> repuestos;
	
	private List<TrabajoPresupuestadoDTO> trabajos;

	public ResumenDeFacturaDTO() {
	}
	
	public List<String> generarResumen() {
		List<String> renglones = new LinkedList<>();
		if(trabajos != null && repuestos != null) {
			renglones.add(" ------ CONCESIONARIOS LARUSSO 'Pateamos la competencia'------");
			renglones.add(" ------ FECHA DE EMISION DEL RESUMEN ----" + new Date() + "Suecursal: ARG BS.AS");
			double costofinaltrabajos = 0;
			for(TrabajoPresupuestadoDTO trabajo : trabajos) {
				costofinaltrabajos += trabajo.getPrecioTrabajo();
				renglones.add("COD. " + trabajo.getIdTrabajoPresu() + "PRECIO U. " + trabajo.getPrecioTrabajo());
			}
			renglones.add("Cant. trabajos " + trabajos.size());
			renglones.add(" ----- MONTO TOTAL A PAGAR: " + costofinaltrabajos + "------");	
		}
		return renglones;
	}
	
	public OrdenDeTrabajoDTO getOrdenDeTrabajo() {
		return ordenDeTrabajo;
	}

	public void setOrdenDeTrabajo(OrdenDeTrabajoDTO ordenDeTrabajo) {
		this.ordenDeTrabajo = ordenDeTrabajo;
	}

	public FacturaDTO getFactura() {
		return factura;
	}

	public void setFactura(FacturaDTO factura) {
		this.factura = factura;
	}

	public List<RepuestoPlanificadoDTO> getRepuestos() {
		return repuestos;
	}

	public void setRepuestos(List<RepuestoPlanificadoDTO> repuestos) {
		this.repuestos = repuestos;
	}

	public List<TrabajoPresupuestadoDTO> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(List<TrabajoPresupuestadoDTO> trabajos) {
		this.trabajos = trabajos;
	}
}
