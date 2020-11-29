package presentacion.views.vendedor;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;

public class DatosVentaVehiculo extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3567541717160280774L;
	
	private JTextField textFieldMontoFinanciado;
	
	private JTextField textFieldMontoCuota;
	
	private JTextField textFieldIva;
	
	private JTextField textFieldPrecioFinal;
	
	private JTextField textFieldComisionVenta;

	private JComboBox<String> comboBoxFinanciera;

	private JCheckBox chckbxNewCheckBox;

	private JSpinner spinnerInteres;

	private JSpinner spinner;
	
	public DatosVentaVehiculo() {
		setBorder(new TitledBorder(null, "Datos de la venta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_1 = new JLabel("Financiera");
		add(lblNewLabel_1, "2, 2, left, default");
		
		comboBoxFinanciera = new JComboBox<String>();
		add(comboBoxFinanciera, "4, 2, 3, 1, fill, default");
		
		chckbxNewCheckBox = new JCheckBox("Es venta en efectivo");
		add(chckbxNewCheckBox, "8, 2, 5, 1");
		
		JLabel lblNewLabel = new JLabel("Monto financiado");
		add(lblNewLabel, "2, 4");
		
		textFieldMontoFinanciado = new JTextField();
		add(textFieldMontoFinanciado, "4, 4, fill, default");
		textFieldMontoFinanciado.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Interés");
		add(lblNewLabel_2, "6, 4, left, default");
		
		spinnerInteres = new JSpinner();
		add(spinnerInteres, "8, 4");
		
		JLabel lblNewLabel_3 = new JLabel("Nro. de cuotas");
		add(lblNewLabel_3, "2, 6, left, default");
		
		spinner = new JSpinner();
		add(spinner, "4, 6");
		
		JLabel lblNewLabel_4 = new JLabel("Monto cuota");
		add(lblNewLabel_4, "6, 6, left, default");
		
		textFieldMontoCuota = new JTextField();
		add(textFieldMontoCuota, "8, 6, 5, 1, fill, default");
		textFieldMontoCuota.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Precio final");
		add(lblNewLabel_6, "2, 8, left, default");
		
		textFieldPrecioFinal = new JTextField();
		add(textFieldPrecioFinal, "4, 8, fill, default");
		textFieldPrecioFinal.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Comisión por venta");
		add(lblNewLabel_7, "6, 8, right, default");
		
		textFieldComisionVenta = new JTextField();
		add(textFieldComisionVenta, "8, 8, fill, default");
		textFieldComisionVenta.setColumns(10);		
		
		JLabel lblNewLabel_5 = new JLabel("IVA");
		add(lblNewLabel_5, "10, 8, left, default");
		
		textFieldIva = new JTextField();
		add(textFieldIva, "12, 8, fill, default");
		textFieldIva.setColumns(10);
		disableTextIva();
	}

	public void disableTextIva() {
		this.textFieldIva.setEditable(false);
	}
	
	public void setData(List<String> list) {
		for(String item : list) this.comboBoxFinanciera.addItem(item);
	}

	public void setActionSelectVentaEnEfectivo(ActionListener listener) {
		this.chckbxNewCheckBox.addActionListener(listener);
	}
	
	public boolean isVentaEnEfectivo() {
		return this.chckbxNewCheckBox.isSelected();
	}

	public void disableFinanciamiento() {
		this.textFieldMontoCuota.setEnabled(false);
		this.textFieldMontoFinanciado.setEnabled(false);
		this.comboBoxFinanciera.setEnabled(false);
		this.spinner.setEnabled(false);
		this.spinnerInteres.setEnabled(false);
	}
	
	public void enableFinanciamiento() {
		this.textFieldMontoCuota.setEnabled(true);
		this.textFieldMontoFinanciado.setEnabled(true);
		this.comboBoxFinanciera.setEnabled(true);		
		this.spinner.setEnabled(true);
		this.spinnerInteres.setEnabled(true);
	}

	public void setIVA(String iva) {
		this.textFieldIva.setText(iva);
	}
}
