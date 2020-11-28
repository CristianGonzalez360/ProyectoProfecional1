package presentacion;

import java.awt.event.ActionEvent;

import business_logic.ClientesController;
import dto.ClienteDTO;
import dto.validators.StringValidator;
import presentacion.views.vendedor.VendedorControlView;

public class VentaVehiculosPresenter {
	
	private final VendedorControlView view = VendedorControlView.getInstance();
	
	private ClientesController clientesController;
	
	public VentaVehiculosPresenter(ClientesController clientesController) {
		assert clientesController != null;
		this.clientesController = clientesController;
		this.view.setActionConsultarCliente((a) -> onConsultarCliente(a));
		this.view.setActionConsultarVehiculo((a) -> onConsultarVehiculo(a));
	}
	
	private void onConsultarVehiculo(ActionEvent a) {
		// TODO Auto-generated method stub
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
}