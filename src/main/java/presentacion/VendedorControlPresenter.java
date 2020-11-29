package presentacion;

import java.awt.event.ActionEvent;
import java.util.List;

import business_logic.ClientesController;
import business_logic.VehiculosController;
import dto.ClienteDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import dto.validators.StringValidator;
import presentacion.views.vendedor.VendedorControlView;

public class VendedorControlPresenter {
	
	private final VendedorControlView view = VendedorControlView.getInstance();
	
	private ClientesController clientesController;
	
	private VehiculosController vehiculosController;
	
	public VendedorControlPresenter(ClientesController clientesController, VehiculosController vehiculosController) {
		assert clientesController != null;
		assert vehiculosController != null;
		this.clientesController = clientesController;
		this.vehiculosController = vehiculosController;
		this.view.setActionConsultarCliente((a) -> onConsultarCliente(a));
		this.view.setActionConsultarVehiculo((a) -> onConsultarVehiculo(a));
		this.view.addTiposBusqueda(new String [] {"NUEVO", "USADO"});
		this.view.addSucursalesBusqueda(new String [] {"Martinez", "Avellaneda", "Quilmes"});
		
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