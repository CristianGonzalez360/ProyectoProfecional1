package presentacion;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business_logic.ClientesController;
import business_logic.FacturasController;
import business_logic.OrdenesTrabajoController;
import business_logic.PresupuestosController;
import business_logic.VehiculosConOrdenDeTrabajoController;
import dto.ClienteDTO;
import dto.taller.EstadoPresupuesto;
import dto.taller.FacturaDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.PresupuestoDTO;
import dto.taller.RepuestoPlanificadoDTO;
import dto.taller.TrabajoPresupuestadoDTO;
import dto.taller.VehiculoConOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.supervisor.ConsultaDePresupuestosSupervisorView;
import presentacion.views.supervisor.InputComentarioDialog;
import presentacion.views.utils.ReporteViewImpl;

public class ConsultaDePresupuestoPresenter {

	private ConsultaDePresupuestosSupervisorView view = ConsultaDePresupuestosSupervisorView.getInstance();

	private VehiculosConOrdenDeTrabajoController vehiculoController;

	private ClientesController clientesController;

	private OrdenesTrabajoController otController;

	private PresupuestosController presController;
	
	private FacturasController facController;

	private ClienteDTO cliente;
	
	public ConsultaDePresupuestoPresenter(VehiculosConOrdenDeTrabajoController controller
			, ClientesController clienteController,
			OrdenesTrabajoController otController,
			PresupuestosController presController,
			FacturasController facController) {
		this.vehiculoController = controller;
		this.clientesController = clienteController;
		this.otController = otController;
		this.presController = presController;
		this.facController = facController;
		
		view.setActionOnBuscar((a) -> onBuscar(a));
		view.setActionSelectVehiculoCliente(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				onSelectVehiculoDeCliente();
			}
		});
		view.setActionSelectPresupuestoCliente(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				onSelectPresupuesto();
			}
		});
		view.setActionGenerarFactura((a)->onGenerarFactura(a));
	}

	private void onSelectVehiculoDeCliente() {
		VehiculoConOrdenDeTrabajoDTO vehiculo = view.getVehiculoSeleccionado();
		if (vehiculo != null) {
			OrdenDeTrabajoDTO ordenDeTrabajo = otController.readByIdVehiculo(vehiculo.getId());
			view.setData(ordenDeTrabajo);
			List<PresupuestoDTO> presupuestos = presController.readByIdOt(ordenDeTrabajo.getIdOrdenTrabajo());
			view.clearDataPresupuestos();
			if(presupuestos != null) this.setDataPresupuestos(presupuestos);
		}
	}
	
	private void setDataPresupuestos(List<PresupuestoDTO> presupuestos) {
		view.setDataPresupuestos(presupuestos);
		for (PresupuestoDTO presupuestoDTO : presupuestos) {
			if(presupuestoDTO.getEstado().name() == EstadoPresupuesto.PENDIENTE.name()) {
				view.unLockButtonGenerarFactura();
			}
		}
	}
	
	private void onSelectPresupuesto() {
		PresupuestoDTO presupuesto = view.getPresupuestoSeleccionado();
		if(presupuesto != null) {
			List<TrabajoPresupuestadoDTO> trabajos = presController.readTrabajosByIdPresupuesto(presupuesto.getIdPresupuesto());
			view.clearDataTrabajos();
			view.setDataTrabajos(trabajos);
			List<RepuestoPlanificadoDTO> repuestos = presController.readRepuestoByIdPresupuesto(presupuesto.getIdPresupuesto());
			view.clearDataRepuestos();
			view.setDataRepuestos(repuestos);
		}
	}
	
	private void onBuscar(ActionEvent a) {
		view.clearDataListadoVehiculosCliente();
		view.clearDataPresupuestos();
		view.clearDataRepuestos();
		view.clearDataTrabajos();
		view.clearDataOrdeDeTrabajo();
		view.lockButtonGenerarFactura();
		String inputDni = view.getTextDni();
		if (new StringValidator(inputDni).number("").validate().isEmpty()) {
			cliente = clientesController.readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				List<VehiculoConOrdenDeTrabajoDTO> vehiculos = vehiculoController
						.readVehicleWithClientIdWhereOtIsOpen(cliente.getIdCliente());
				view.clearDataListadoVehiculosCliente();
				view.setData(vehiculos);
			}
		}
	}
	
	private void actualizarEstadoPresupuestos() {
		Map<Integer, Boolean> presupuestosSeleccionados = view.getPresupuestosPresentados();
		presupuestosSeleccionados.forEach((k,v) -> {
			PresupuestoDTO presupuesto = presController.readById(k);
			if(presupuesto.getEstado().equals(EstadoPresupuesto.PENDIENTE)) {
				presupuesto.setFechaAprobacion(new Date());
				if(v.booleanValue() == false) {
					String comentario = new InputComentarioDialog(presupuesto).open();
					presupuesto.setComentarioRechazo(comentario);
					presupuesto.setEstado(EstadoPresupuesto.RECHAZADO);
				} else {
					presupuesto.setEstado(EstadoPresupuesto.APROBADO);
				}
				presController.registrarAprobacion(presupuesto);
			} 
		});
		updatePresupuestosView();
	}
	
	private void onGenerarFactura(ActionEvent a) {
		List<PresupuestoDTO> ps = getPresupuestosFacturados();
		actualizarEstadoPresupuestos();
		FacturaDTO factura = null;
		Integer ordenDeTrabajoId = null;
		if(!ps.isEmpty()) {
			ordenDeTrabajoId = ps.get(0).getIdOT();
			if (!otController.esRechazada(ordenDeTrabajoId)) {
				factura = new FacturaDTO();
				factura.setIdOrdenDeTrabajo(ordenDeTrabajoId);
				double total = 0;
				for (PresupuestoDTO p : ps) {
					total += p.getPrecio();
				}
				factura.setTotal(total);
				factura.setCliente(cliente);
				facController.save(factura);
	
				for (PresupuestoDTO p : ps) {
					p.setIdFactura(factura.getIdFactura());
					presController.update(p);
				}
				factura.setPresupuestosFacturados(ps);
				
				ReporteViewImpl ventanaReporte = new ReporteViewImpl();
				ventanaReporte.setData(facController.makeFacturaTaller(factura));
				ventanaReporte.open();
			}
		}
	}
	
	private List<PresupuestoDTO> getPresupuestosFacturados(){
		Map<Integer, Boolean> presupuestos = view.getPresupuestosPresentados();
		Object[] keys = presupuestos.keySet().toArray();
		List<PresupuestoDTO> ps = new ArrayList<PresupuestoDTO>();
		for (int i = 0; i < keys.length; i++) {
			if(presupuestos.get(keys[i]) == true) {
				int idPresupuesto = (Integer) keys[i];
				PresupuestoDTO p = presController.readById(idPresupuesto);
				ps.add(p);
			}
		}
		return ps;
	}
	
	private void updatePresupuestosView () {
		view.clearDataPresupuestos();
		Integer idOrdenDeTrabajo = view.getIdOrdenDeTrabajoPresentada();
		List<PresupuestoDTO> presupuestos = presController.readByIdOt(idOrdenDeTrabajo);
		this.setDataPresupuestos(presupuestos);
	}
}