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

	private JComboBox<String> comboBoxNroCuotas;
	
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
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Es venta en efectivo");
		add(chckbxNewCheckBox, "2, 2");
		
		JLabel lblNewLabel_1 = new JLabel("Financiera");
		add(lblNewLabel_1, "2, 4, left, default");
		
		comboBoxFinanciera = new JComboBox<String>();
		add(comboBoxFinanciera, "4, 4, 5, 1, fill, default");
		
		JLabel lblNewLabel = new JLabel("Monto financiado");
		add(lblNewLabel, "2, 6");
		
		textFieldMontoFinanciado = new JTextField();
		add(textFieldMontoFinanciado, "4, 6, fill, default");
		textFieldMontoFinanciado.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Interés");
		add(lblNewLabel_2, "6, 6, left, default");
		
		JSpinner spinnerInteres = new JSpinner();
		add(spinnerInteres, "8, 6");
		
		JLabel lblNewLabel_3 = new JLabel("Nro. de cuotas");
		add(lblNewLabel_3, "2, 8, left, default");
		
		comboBoxNroCuotas = new JComboBox<String>();
		add(comboBoxNroCuotas, "4, 8, fill, default");
		
		JLabel lblNewLabel_4 = new JLabel("Monto cuota");
		add(lblNewLabel_4, "6, 8, left, default");
		
		textFieldMontoCuota = new JTextField();
		add(textFieldMontoCuota, "8, 8, fill, default");
		textFieldMontoCuota.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("IVA");
		add(lblNewLabel_5, "2, 10, left, default");
		
		textFieldIva = new JTextField();
		add(textFieldIva, "4, 10, fill, default");
		textFieldIva.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Precio final");
		add(lblNewLabel_6, "2, 12, left, default");
		
		textFieldPrecioFinal = new JTextField();
		add(textFieldPrecioFinal, "4, 12, fill, default");
		textFieldPrecioFinal.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Comisión por venta");
		add(lblNewLabel_7, "6, 12, right, default");
		
		textFieldComisionVenta = new JTextField();
		add(textFieldComisionVenta, "8, 12, fill, default");
		textFieldComisionVenta.setColumns(10);		
	}
}
