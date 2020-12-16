package presentacion.views.supervisor;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.taller.RepuestoDTO;
import dto.temporal.IngresoStockDTO;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogIngresoStock extends JDialog {
	private JTextField tfCodigo;
	private JTextField tfDescripcion;
	private JTextField tfMarca;
	private JTextField tfStock;
	private JTextField tfPrecio;

	private static DialogIngresoStock instance;
	private JButton btnAceptar;
	private JLabel lblPrecioDeVenta;
	private JTextField tfPrecioVenta;

	private DialogIngresoStock() {
		setTitle("Ingreso de Stock");
		setBounds(100, 100, 400, 300);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblCdigo = new JLabel("Código:");
		panel.add(lblCdigo, "2, 2, right, center");

		tfCodigo = new JTextField();
		tfCodigo.setFocusable(false);
		tfCodigo.setEditable(false);
		panel.add(tfCodigo, "4, 2, fill, default");
		tfCodigo.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripción:");
		panel.add(lblDescripcion, "2, 4, right, center");

		tfDescripcion = new JTextField();
		tfDescripcion.setFocusable(false);
		tfDescripcion.setEditable(false);
		panel.add(tfDescripcion, "4, 4, fill, default");
		tfDescripcion.setColumns(10);

		JLabel lblMarca = new JLabel("Marca:");
		panel.add(lblMarca, "2, 6, right, center");

		tfMarca = new JTextField();
		tfMarca.setFocusable(false);
		tfMarca.setEditable(false);
		panel.add(tfMarca, "4, 6, fill, default");
		tfMarca.setColumns(10);

		JLabel lblStockIngresado = new JLabel("Cantidad Ingresada:");
		panel.add(lblStockIngresado, "2, 8, right, default");

		tfStock = new JTextField();
		panel.add(tfStock, "4, 8, fill, default");
		tfStock.setColumns(10);

		JLabel lblPrecioDeCompra = new JLabel("Precio de Compra:");
		panel.add(lblPrecioDeCompra, "2, 10, right, default");

		tfPrecio = new JTextField();
		panel.add(tfPrecio, "4, 10, fill, default");
		tfPrecio.setColumns(10);

		lblPrecioDeVenta = new JLabel("Precio de Venta:");
		panel.add(lblPrecioDeVenta, "2, 12, right, default");

		tfPrecioVenta = new JTextField();
		panel.add(tfPrecioVenta, "4, 12, fill, default");
		tfPrecioVenta.setColumns(10);

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_1, BorderLayout.SOUTH);

		btnAceptar = new JButton("Aceptar");
		panel_1.add(btnAceptar);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				clearData();
			}
		});
		setVisible(false);
	}

	public void clearData() {
		this.tfCodigo.setText("");
		this.tfDescripcion.setText("");
		this.tfMarca.setText("");
		this.tfStock.setText("");
		this.tfPrecio.setText("");
		this.tfPrecioVenta.setText("");
	}

	public static DialogIngresoStock getInstance() {
		if (instance == null)
			instance = new DialogIngresoStock();
		return instance;
	}

	public void setData(RepuestoDTO repuesto) {
		this.tfCodigo.setText(repuesto.getCodigoRepuesto() + "");
		this.tfDescripcion.setText(repuesto.getDescripcionRepuesto());
		this.tfMarca.setText(repuesto.getMarcaRepuesto());
		this.tfPrecio.setText(repuesto.getPrecioCompra() + "");
		this.tfPrecioVenta.setText(repuesto.getPrecioRepuesto() + "");
	}

	public IngresoStockDTO getData() {
		IngresoStockDTO ret = new IngresoStockDTO();
		ret.setPrecioCompra(tfPrecio.getText());
		ret.setCantidad(tfStock.getText());
		ret.setPrecioVenta(tfPrecioVenta.getText());
		return ret;
	}

	public void setActionOnAceptar(ActionListener listener) {
		this.btnAceptar.addActionListener(listener);
	}

	public void display() {
		setVisible(true);
	}

	public void close() {
		setVisible(false);
		clearData();
	}

}
