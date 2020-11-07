package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import business_logic.ClientesController;
import business_logic.OrdenesTrabajoController;
import business_logic.PresupuestosController;
import business_logic.VehiculosController;
import business_logic.exceptions.ForbiddenException;
import dto.ClienteDTO;
import dto.OrdenDeTrabajoDTO;
import dto.PresupuestoDTO;
import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;
import dto.VehiculoConOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.PanelConsultaDePresupuestosView;
import presentacion.views.utils.ConfirmationDialog;
import presentacion.views.utils.ErrorDialog;

public class ConsultaDePresupuestoPresenter {

	private PanelConsultaDePresupuestosView view = PanelConsultaDePresupuestosView.getInstance();

	private VehiculosController vehiculoController;

	private ClientesController clientesController;

	private OrdenesTrabajoController otController;

	private PresupuestosController presController;
	
	public ConsultaDePresupuestoPresenter(VehiculosController controller
			, ClientesController clienteController,
			OrdenesTrabajoController otController,
			PresupuestosController presController) {
		this.vehiculoController = controller;
		this.clientesController = clienteController;
		this.otController = otController;
		this.presController = presController;
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
			this.setDataPresupuestos(presupuestos);
		}
	}
	
	private void setDataPresupuestos(List<PresupuestoDTO> presupuestos) {
		view.setDataPresupuestos(presupuestos);
		if(otController.getFactura(view.getIdOrdenDeTrabajoPresentada()) != null) {
			view.lockButtonGenerarFactura();
		} else {
			view.unLockButtonGenerarFactura();
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
		String inputDni = view.getTextDni();
		if (new StringValidator(inputDni).number("").validate().isEmpty()) {
			ClienteDTO cliente = clientesController.readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				List<VehiculoConOrdenDeTrabajoDTO> vehiculos = vehiculoController
						.readVehicleWithClientIdWhereOtIsOpen(cliente.getIdCliente());
				view.clearDataListadoVehiculosCliente();
				view.setData(vehiculos);
			}
		}
	}
	
	private void onGenerarFactura(ActionEvent a) {
		Map<Integer, Boolean> presupuestosSeleccionados = view.getPresupuestosPresentados();
		try {
			int confirmation = new ConfirmationDialog("¿Está seguro de generar una factura para los presupuestos seleccionados?").open();
			if(confirmation == 0) {
				presController.generarFactura(presupuestosSeleccionados);
				view.clearDataPresupuestos();
				Integer idOrdenDeTrabajo = view.getIdOrdenDeTrabajoPresentada();
				List<PresupuestoDTO> presupuestos = presController.readByIdOt(idOrdenDeTrabajo);
				this.setDataPresupuestos(presupuestos);
			}
		} catch(ForbiddenException e) {
			new ErrorDialog().showMessages(e.getMessage());
		}
	}
}