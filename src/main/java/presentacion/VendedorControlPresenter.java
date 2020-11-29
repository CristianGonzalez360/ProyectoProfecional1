package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import business_logic.ClientesController;
import business_logic.SucursalesController;
import business_logic.VehiculosController;
import dto.ClienteDTO;
import dto.VehiculoParaVentaDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import dto.validators.StringValidator;
import presentacion.views.supervisor.ClienteFormView;
import presentacion.views.vendedor.VendedorControlView;

public class VendedorControlPresenter {
	
	private final VendedorControlView view = VendedorControlView.getInstance();
	
	private ClientesController clientesController;
	
	private VehiculosController vehiculosController;
	
	private SucursalesController sucursalesController;
	
	public VendedorControlPresenter(ClientesController clientesController, VehiculosController vehiculosController, SucursalesController sucController) {
		assert clientesController != null;
		assert vehiculosController != null;
		this.clientesController = clientesController;
		this.vehiculosController = vehiculosController;
		this.sucursalesController = sucController;
		this.view.setActionConsultarCliente((a) -> onConsultarCliente(a));
		this.view.setActionConsultarVehiculo((a) -> onConsultarVehiculo(a));
		this.view.setActionRegistrarCliente((a)->onDisplayClienteFormView(a));
		this.view.setActionSelectVehiculo((a)->onSelectVehiculo(a));
		this.view.setActionSelectVentaEnEfectivo((a)->onSelectVentaEnEfectivo(a));
		this.view.addTiposBusqueda(new String [] {"NUEVO", "USADO"});
		this.view.addSucursalesBusqueda(sucursalesController.readAll());
		this.view.addFinancieras(sucursalesController.readFinancierasByPais("ARG"));
		this.view.setDataIVA("21");
	}
	
	private void onDisplayClienteFormView(ActionEvent e) {
		view.clearDataCliente();
		ClienteFormView.getInstance().clearData();
		ClienteFormView.getInstance().display();
	}

	private void onSelectVentaEnEfectivo(ActionEvent a) {
		if(view.isVentaEnEfectivo()) {
			view.disableVentaFinanciada();
		} else {
			view.enableVentafinanciada();
		}
	}
	
	private void onSelectVehiculo(ListSelectionEvent a) {
		if(view.getDataCodigoDeVehiculo() != null) {
			Integer codigoVehiculo = Integer.parseInt(view.getDataCodigoDeVehiculo().getCodigo());
			VehiculoParaVentaDTO dto = vehiculosController.readByCodigo(codigoVehiculo);
			view.setData(dto);	
		}
	}

	private void onConsultarCliente(ActionEvent e) {
		String dniBuscado = view.getData();
		if(!dniBuscado.trim().isEmpty() && dniBuscado != null ) {
			boolean esDniConFormatoCorrecto = new StringValidator(dniBuscado).number("").validate().isEmpty();
			if (esDniConFormatoCorrecto) {
				Integer dniCliente = Integer.parseInt(dniBuscado);
				ClienteDTO cliente = clientesController.readByDni(dniCliente);
				view.clearDataCliente();
				if(cliente != null) {
					view.setData(cliente);
				}
			}	
		}
	}
	
	private void onConsultarVehiculo(ActionEvent a) {
		ConsultaVehiculoParaVentaDTO consulta = view.getDataConsultaVehiculo();
		view.clearDataVehiculos();
		if(consulta.validate().isEmpty()) {
			List<OutputConsultaVehiculoEnVentaDTO> vehiculos = vehiculosController.readByCriteria(consulta);
			view.setData(vehiculos);
		}
	}
}