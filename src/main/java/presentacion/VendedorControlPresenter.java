package presentacion;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;

import business_logic.CalculadoraMontoFinalVentaService;
import business_logic.ControllersFactory;
import business_logic.exceptions.ForbiddenException;
import dto.ClienteDTO;
import dto.GarantiaVehiculoDTO;
import dto.VehiculoDTO;
import dto.taller.FichaTecnicaVehiculoDTO;
import dto.temporal.ConsultaVehiculoParaVentaDTO;
import dto.temporal.ModalidadVentaVehiculoDTO;
import dto.temporal.OutputConsultaVehiculoEnVentaDTO;
import dto.validators.StringValidator;
import presentacion.views.supervisor.FormCliente;
import presentacion.views.utils.MessageDialog;
import presentacion.views.utils.ReporteViewImpl;
import presentacion.views.vendedor.VendedorControlView;

public class VendedorControlPresenter {

	private static final String VENTA_MSG = "Se efectuó la venta del vehículo.";

	private final VendedorControlView view = VendedorControlView.getInstance();

	private ControllersFactory controllers;
	
	public VendedorControlPresenter(ControllersFactory controllers) {
		this.controllers = controllers;
		setActions();
		setOpcionesBusqueda();
	}

	private void setOpcionesBusqueda() {
		this.view.addTiposBusqueda(new String[] { "Nuevo", "Usado" });
		/**
		 * @ApiIssue NO DEBERÍA ESTAR HARDCODEADO LAS FINANCIERAS
		 */
		this.view.addFinancieras(Arrays.asList(new String[] { "Santander", "ICBC", "HSBC", "City Bank" }));
		this.view.addMarcasBusqueda(controllers.makeVentasVehiculosController().readNombreMarcasVehiculos());
	}

	private void setActions() {
		this.view.setActionConsultarCliente((a) -> onConsultarCliente(a));
		this.view.setActionConsultarVehiculo((a) -> onConsultarVehiculo(a));
		this.view.setActionRegistrarCliente((a) -> onDisplayClienteFormView(a));
		this.view.setActionSelectVehiculo((a) -> onSelectVehiculo(a));
		this.view.setActionSelectVentaEnEfectivo((a) -> onSelectVentaEnEfectivo(a));
		this.view.setActionRegistrarVenta((a) -> onRegistrarVenta(a));
		this.view.setActionUpdateNroCuotas((a) -> onUpdateNroCuotas(a));
		this.view.setActionSelectExtenderGarantia((a) -> onSelectExtenderGarantia(a));
	}

	private void onUpdateNroCuotas(ChangeEvent a) {
		updateMontoCuota();
	}

	private void updateMontoCuota() {
		ModalidadVentaVehiculoDTO modalidad = view.getDataModalidadVenta();
		String montoCuota = new CalculadoraMontoFinalVentaService(modalidad).calcularMontoCuota();
		view.setMontoCuota(montoCuota);
	}

	private void onRegistrarVenta(ActionEvent a) {
		Integer idCliente = view.getDataCliente();
		OutputConsultaVehiculoEnVentaDTO vehiculo = view.getDataVehiculoEnVenta();
		ModalidadVentaVehiculoDTO modalidadVenta = view.getDataModalidadVenta();
		try {
			controllers.makeVentasVehiculosController().registrarVenta(idCliente, vehiculo, modalidadVenta);
			ReporteViewImpl ventanaReporte = new ReporteViewImpl();
			ventanaReporte.setData(controllers.makeVentasVehiculosController().makeFacturaVentaVehiculo(
					controllers.makeVentasVehiculosController().readByIdVehiculo(Integer.parseInt(vehiculo.getCodigo()))));
			ventanaReporte.open();
			new MessageDialog().showMessages(VENTA_MSG);
			view.clearData();
		} catch (ForbiddenException e) {
			new MessageDialog().showMessages(e.getMessage());
		}
	}

	private void onDisplayClienteFormView(ActionEvent e) {
		view.clearDataCliente();
		FormCliente.getInstance().clearData();
		FormCliente.getInstance().display();
	}

	private void onSelectVentaEnEfectivo(ActionEvent a) {
		if (view.isVentaEnEfectivo()) {
			view.disableVentaFinanciada();
		} else {
			view.enableVentafinanciada();
		}
	}

	private void onSelectExtenderGarantia(ActionEvent a) {
		if (view.getDataVehiculoEnVenta() != null) {
			ModalidadVentaVehiculoDTO mod = view.getDataModalidadVenta();
			if (view.isExtenderGarantia()) {
				Double precioGarantia = new CalculadoraMontoFinalVentaService(mod).calcularCostoGarantiaExtendida();
				view.setDataCostoBaseGarantia(precioGarantia.toString());
			} else {
				view.setDataCostoBaseGarantia(view.getDataGarantia().getCostoFinalConIVA().toString());
			}
			view.setDataPrecioFinal(
					new CalculadoraMontoFinalVentaService(view.getDataModalidadVenta()).getPrecioFinalVenta());
			view.setDataComisionVendedor(
					new CalculadoraMontoFinalVentaService(view.getDataModalidadVenta()).calcularComision());
			this.updateMontoCuota();
		}
	}

	private void onSelectVehiculo(ListSelectionEvent a) {
		if (view.getDataVehiculoEnVenta() != null) {
			OutputConsultaVehiculoEnVentaDTO out = view.getDataVehiculoEnVenta();
			Integer codigoVehiculo = Integer.parseInt(out.getCodigo());
			view.setData(controllers.makeVentasVehiculosController().readCaracteristicaVehiculoByIdVehiculo(codigoVehiculo));
			GarantiaVehiculoDTO garantia = controllers.makeGarantiasController().readByIdVehiculo(codigoVehiculo);
			if(garantia != null)view.setData(garantia);
			view.clearDataModalidadVenta();
			VehiculoDTO vehiculo = controllers.makeVehiculosController().readVehiculoById(codigoVehiculo);
			if(vehiculo.getIdFichaTecnica() != null) {
				FichaTecnicaVehiculoDTO ficha = controllers.makeVehiculosController().readByIdFichaTecnica(vehiculo.getIdFichaTecnica());
				view.clearDataFichaTecnica();
				if(ficha != null) {
					view.setData(ficha);
				}	
			}
			updatePanelVenta(out.getPrecio(), garantia.getCostoFinalConIVA().toString());
			updateMontoCuota();
		}
	}

	private void updatePanelVenta(String precioUnidad, String precioGarantia) {
		view.setMontoFinanciado(precioUnidad);
		view.setDataCostoBaseGarantia(precioGarantia);
		CalculadoraMontoFinalVentaService calc = new CalculadoraMontoFinalVentaService(view.getDataModalidadVenta());
		view.setDataComisionVendedor(calc.calcularComision());
		view.setDataPrecioFinal(calc.getPrecioFinalVenta());
	}

	private void onConsultarCliente(ActionEvent e) {
		String dniBuscado = view.getData();
		
		if(dniBuscado == null || dniBuscado.trim().isEmpty())
			view.clearDataCliente();
		else {
			boolean esDniConFormatoCorrecto = new StringValidator(dniBuscado).number("").validate().isEmpty();
			if (esDniConFormatoCorrecto) {
				Integer dniCliente = Integer.parseInt(dniBuscado);
				ClienteDTO cliente = controllers.makeClientesController().readByDni(dniCliente);
				view.clearDataCliente();
				if (cliente != null) {
					view.setData(cliente);
				}
			}else {
				view.clearDataCliente();
			}
		}
	}

	private void onConsultarVehiculo(ActionEvent a) {
		ConsultaVehiculoParaVentaDTO consulta = view.getDataConsultaVehiculo();
		view.clearDataCliente();
		view.clearDataFichaTecnica();
		view.clearDataModalidadVenta();
		view.clearDataVehiculos();
		view.clearDataGarantia();
		if (consulta.validate().isEmpty()) {
			List<OutputConsultaVehiculoEnVentaDTO> vehiculos = controllers.makeVentasVehiculosController().readDisponiblesByCriteria(consulta);
			view.setData(vehiculos);
		}
	}
}