
package presentacion;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import business_logic.ClientesController;
import business_logic.OrdenesTrabajoController;
import business_logic.PresupuestosController;
import business_logic.VehiculosConOrdenDeTrabajoController;
import dto.ClienteDTO;
import dto.taller.EstadoPresupuesto;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.taller.OrdenDeTrabajoDTO;
import dto.taller.PresupuestoDTO;
import dto.taller.IngresoOrdenDeTrabajoDTO;
import dto.validators.StringValidator;
import presentacion.views.tecnico.PanelGestionTrabajoView;

public class GestionTrabajosPresenter {

	private PanelGestionTrabajoView view;
	private PresupuestosController presupuestosController;
	private ClientesController clienteController;
	private VehiculosConOrdenDeTrabajoController vehiculosController;
	private OrdenesTrabajoController ordenDeTrabajoController;
	private PresupuestoDTO nuevoPresupuesto;

	public GestionTrabajosPresenter(PresupuestosController presupuestoController, ClientesController clienteController,
			VehiculosConOrdenDeTrabajoController vehiculosController, OrdenesTrabajoController ordenDeTrabajoController,
			PresupuestosController presupuestosController) {

		this.presupuestosController = presupuestoController;
		this.clienteController = clienteController;
		this.view = PanelGestionTrabajoView.getInstance();
		this.vehiculosController = vehiculosController;
		this.ordenDeTrabajoController = ordenDeTrabajoController;
		this.presupuestosController = presupuestosController;

		this.view.setActionOnBuscar(a -> onBuscar(a));
		this.view.setActionSelectVehiculoCliente(a -> onSelectVehiculoDeCliente(a));
		this.view.setActionOnSeleccionarPresupuesto(a -> onSelecionarPresupuesto(a));
		this.view.setActionOnRegistrar(a -> onRegistrar(a));
	}

	private void onBuscar(ActionEvent a) {

		this.view.clearAll();
		List<IngresoOrdenDeTrabajoDTO> vehiculosEnTabla = new LinkedList<IngresoOrdenDeTrabajoDTO>();
		String inputDni = this.view.getTxtDni();
		boolean flag = false;

		if (new StringValidator(inputDni).number("").validate().isEmpty()) {
			ClienteDTO cliente = clienteController.readByDni(Integer.parseInt(inputDni));
			if (cliente != null) {
				List<IngresoOrdenDeTrabajoDTO> vehiculos = vehiculosController
						.readVehicleWithClientIdWhereOtIsOpen(cliente.getIdCliente());
				for (IngresoOrdenDeTrabajoDTO vehiculoConOrdenDeTrabajo : vehiculos) {
					OrdenDeTrabajoDTO ordenDeTrabajo = this.ordenDeTrabajoController
							.readByIdVehiculo(vehiculoConOrdenDeTrabajo.getId());
					List<PresupuestoDTO> presupuestos = this.presupuestosController
							.readByIdOt(ordenDeTrabajo.getIdOrdenTrabajo());
					for (PresupuestoDTO presupuesto : presupuestos) {
						if (presupuesto.getEstado() == EstadoPresupuesto.APROBADO
								|| presupuesto.getEstado() == EstadoPresupuesto.PAGADO) {// pagado o aprobado
							flag = true;
						}
					}
					if (flag == true) {
						vehiculosEnTabla.add(vehiculoConOrdenDeTrabajo);
					}
					flag = false;
				}
				if (vehiculosEnTabla.size() > 0) {
					this.view.setData(vehiculosEnTabla);
				}
			}
		}
	}

	private void onSelectVehiculoDeCliente(ListSelectionEvent a) {
		IngresoOrdenDeTrabajoDTO idVehiculo = this.view.getidVehiculoSeleccionado();
		if (idVehiculo != null) {
			FichaTecnicaVehiculoDTO fichaVehiculo = vehiculosController
					.readFichaTecnicaById(idVehiculo.getIdFichaTecnica());
			if (fichaVehiculo != null) {
				this.view.clearDataFichaTecnicaVehiculo();
				this.view.clearDataOrdenDeTrabajo();
				this.view.setData(fichaVehiculo);
				OrdenDeTrabajoDTO ordenDeTrabajo = this.ordenDeTrabajoController.readByIdVehiculo(idVehiculo.getId());
				if (ordenDeTrabajo != null) {
					this.view.setData(ordenDeTrabajo);
					List<PresupuestoDTO> presupuestos = this.presupuestosController
							.readByIdOt(ordenDeTrabajo.getIdOrdenTrabajo());
					this.view.setDataPresupuestos(presupuestos);
				} else {
					this.view.clearDataOrdenDeTrabajo();
					this.view.clearDataPresupuestos();
				}
			}
		}
	}

	private void onSelecionarPresupuesto(ListSelectionEvent a) {
		if (this.view.getIdPresupuesto() >= 0) {
			this.nuevoPresupuesto = presupuestosController.readById(this.view.getIdPresupuesto());
			this.view.setDataPresupuesto(nuevoPresupuesto);
		}
	}

	private void onRegistrar(ActionEvent a) {

		int idPresupuesto = this.view.getIdPresupuestoSeleccionada();

		if (idPresupuesto != -1) {
			int resp = JOptionPane.showOptionDialog(null,
					"¿Estás seguro que quieres registrar\n el presupuesto realizado?", "Confirmación",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (resp == 0) {
				presupuestosController.updateEstadoPresupuesto(idPresupuesto);
				this.onBuscar(a);
			}
		}
	}
}
