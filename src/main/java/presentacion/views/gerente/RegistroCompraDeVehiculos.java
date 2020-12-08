package presentacion.views.gerente;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import presentacion.views.vendedor.CaracteristicaDeVehiculoPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.CaracteristicaVehiculoDTO;
import dto.temporal.CompraVehiculoUsadoDTO;

public class RegistroCompraDeVehiculos extends JPanel {
	
	private  CaracteristicaDeVehiculoPanel caracteristicas;
	private FichaTecnicaPanel fichaTecnica;
	private JTextField txtPrecioCompra;
	private JTextField txtPrecioVenta;
	
	public RegistroCompraDeVehiculos() {
		setLayout(new BorderLayout());
		
		JPanel panelCentral = new JPanel();
		add(panelCentral,BorderLayout.CENTER);
		this.caracteristicas = new CaracteristicaDeVehiculoPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		this.fichaTecnica = new FichaTecnicaPanel();
		
		JPanel precios = new JPanel();
		
		panelCentral.add(precios);
		precios.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),}));
		
		JLabel lblPrecioDeCompra = new JLabel("Precio de Compra");
		precios.add(lblPrecioDeCompra, "1, 2, left, center");
		
		txtPrecioCompra = new JTextField();
		precios.add(txtPrecioCompra, "3, 2, fill, top");
		txtPrecioCompra.setColumns(10);
		
		JLabel lblPrecioDeVenta = new JLabel("Precio de venta");
		precios.add(lblPrecioDeVenta, "5, 2, right, default");
		
		txtPrecioVenta = new JTextField();
		precios.add(txtPrecioVenta, "7, 2, fill, default");
		txtPrecioVenta.setColumns(10);
		panelCentral.add(fichaTecnica);
		panelCentral.add(caracteristicas);
	}

	public CompraVehiculoUsadoDTO getData() {
		CompraVehiculoUsadoDTO ret = this.fichaTecnica.getData();
		CaracteristicaVehiculoDTO carac = this.caracteristicas.getData();
		ret.setCilindrada(carac.getCilindrada());
		ret.setMotor(carac.getMotor());
		ret.setTransmision(carac.getTransmision());
		ret.setDireccion(carac.getDireccion());
		ret.setPotencia(carac.getPotencia());
		ret.setFrenosDelanteros(carac.getFrenosDelanteros());
		ret.setFrenosTraseros(carac.getFrenosTraseros());
		ret.setTorqueMaximo(carac.getTorqueMaximo());
		ret.setVolumenBaul(carac.getVolumenBaul());
		ret.setCantidadPuertas(carac.getCantidadPuertas());
		ret.setPrecio(this.txtPrecioCompra.getText());
		ret.setPrecioVenta(this.txtPrecioVenta.getText());
		return ret;
	}
}
