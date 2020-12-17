package presentacion.views.supervisor;

import javax.swing.JDialog;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.taller.RepuestoDTO;
import dto.temporal.AltaRepuestoDTO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class DialogEditorRepuestos extends JDialog {

	private static final long serialVersionUID = 4827100761114913193L;

	private JTextField tfCodigo;
	private JTextField tfMarca;
	private JTextField tfDescripcion;
	private JTextField tfStock;
	private JTextField txtTfstockminimo;
	private JTextField tfFabricante;
	private JTextField tfPrecioCompra;
	private JTextField tfPrecioVenta;

	private static DialogEditorRepuestos instance;
	private JCheckBox checkBox;
	private JPanel panel;
	private JButton btnAceptar;
	private JButton btnCancelar;

	private DialogEditorRepuestos() {
		setBounds(100, 100, 350, 400);
		setTitle("Editar Repuesto");
		setResizable(false);
		getContentPane().setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("107px"),
						FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("97px:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, },
				new RowSpec[] { FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.LINE_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.LINE_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.LINE_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.LINE_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.LINE_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.LINE_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.LABEL_COMPONENT_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), }));

		JLabel lblCdigo = new JLabel("Código:");
		getContentPane().add(lblCdigo, "2, 2, fill, center");

		JLabel lblMarca = new JLabel("Marca:");
		getContentPane().add(lblMarca, "2, 4, fill, center");

		JLabel lblDescripcion = new JLabel("Descripción:");
		getContentPane().add(lblDescripcion, "2, 6, fill, fill");

		JLabel lblStock = new JLabel("Stock:");
		getContentPane().add(lblStock, "2, 8, fill, center");

		JLabel lblStockMinimo = new JLabel("Stock mínimo:");
		getContentPane().add(lblStockMinimo, "2, 10, fill, center");

		JLabel lblFabricante = new JLabel("Fabricante:");
		getContentPane().add(lblFabricante, "2, 12, fill, center");

		JLabel lblPrecioDeCompra = new JLabel("Precio de Compra:");
		getContentPane().add(lblPrecioDeCompra, "2, 14, fill, center");

		JLabel lblPrecioDeVenta = new JLabel("Precio de Venta:");
		getContentPane().add(lblPrecioDeVenta, "2, 16, fill, center");

		JLabel lblGaranta = new JLabel("Garantía:");
		getContentPane().add(lblGaranta, "2, 18, fill, center");

		tfCodigo = new JTextField();
		getContentPane().add(tfCodigo, "4, 2, fill, default");
		tfCodigo.setColumns(10);

		tfMarca = new JTextField();
		getContentPane().add(tfMarca, "4, 4, fill, default");
		tfMarca.setColumns(10);

		tfDescripcion = new JTextField();
		getContentPane().add(tfDescripcion, "4, 6, fill, default");
		tfDescripcion.setColumns(10);

		tfStock = new JTextField();
		getContentPane().add(tfStock, "4, 8, fill, default");
		tfStock.setColumns(10);

		txtTfstockminimo = new JTextField();
		getContentPane().add(txtTfstockminimo, "4, 10, fill, default");
		txtTfstockminimo.setColumns(10);

		tfFabricante = new JTextField();
		getContentPane().add(tfFabricante, "4, 12, fill, default");
		tfFabricante.setColumns(10);

		tfPrecioCompra = new JTextField();
		getContentPane().add(tfPrecioCompra, "4, 14, fill, default");
		tfPrecioCompra.setColumns(10);

		tfPrecioVenta = new JTextField();
		getContentPane().add(tfPrecioVenta, "4, 16, fill, default");
		tfPrecioVenta.setColumns(10);

		checkBox = new JCheckBox("");
		getContentPane().add(checkBox, "4, 18, left, center");

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, "2, 20, 3, 1, fill, top");

		btnAceptar = new JButton("Aceptar");
		panel.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);

		setVisible(false);
	}

	public static DialogEditorRepuestos getInstance() {
		if (instance == null)
			instance = new DialogEditorRepuestos();
		return instance;
	}

	public void setData(RepuestoDTO repuesto) {
		this.tfCodigo.setText(repuesto.getCodigoRepuesto() + "");
		this.tfDescripcion.setText(repuesto.getDescripcionRepuesto());
		this.tfFabricante.setText(repuesto.getFabricante());
		this.tfMarca.setText(repuesto.getMarcaRepuesto());
		this.tfPrecioCompra.setText(repuesto.getPrecioCompra() + "");
		this.tfPrecioVenta.setText(repuesto.getPrecioRepuesto() + "");
		this.tfStock.setText(repuesto.getStockRepuesto() + "");
		this.txtTfstockminimo.setText(repuesto.getStockMinimo() + "");
		this.checkBox.setSelected(repuesto.isGarantia());
	}

	public AltaRepuestoDTO getData() {
		AltaRepuestoDTO ret = new AltaRepuestoDTO();
		ret.setCodigoRepuesto(tfCodigo.getText());
		ret.setDescripcionRepuesto(this.tfDescripcion.getText());
		ret.setFabricante(this.tfFabricante.getText());
		ret.setMarcaRepuesto(this.tfMarca.getText());
		ret.setPrecioCompra(this.tfPrecioCompra.getText());
		ret.setPrecioRepuesto(this.tfPrecioVenta.getText());
		ret.setStockRepuesto(this.tfStock.getText());
		ret.setStockMinimo(this.txtTfstockminimo.getText());
		ret.setGarantia(this.checkBox.isSelected());
		return ret;
	}

	public void clearData() {
		this.tfCodigo.setText("");
		this.tfDescripcion.setText("");
		this.tfFabricante.setText("");
		this.tfMarca.setText("");
		this.tfPrecioCompra.setText("");
		this.tfPrecioVenta.setText("");
		this.tfStock.setText("");
		this.txtTfstockminimo.setText("");
		this.checkBox.setSelected(false);
	}

	public void display() {
		setVisible(true);
	}

	public void close() {
		setVisible(false);
		clearData();
	}

	public void setActionOnAceptar(ActionListener listener) {
		this.btnAceptar.addActionListener(listener);
	}

	public void setActionOnCancelar(ActionListener listener) {
		this.btnCancelar.addActionListener(listener);
	}
}
