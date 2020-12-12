package presentacion.views.vendedor;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.temporal.ModalidadVentaVehiculoDTO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JButton;

public class DatosVentaVehiculo extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3567541717160280774L;
	
	private JTextField textFieldMontoFinanciado;
	
	private JTextField textFieldMontoCuota;
	
	private JTextField textFieldPrecioFinal;
	
	private JTextField textFieldComisionVenta;

	private JComboBox<String> comboBoxFinanciera;

	private JCheckBox chckbxNewCheckBox;

	private JSpinner spinner;
	private JButton btnRegistrarVenta;
	private JCheckBox chckbxExtenderGarantia;
	
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
				FormSpecs.DEFAULT_COLSPEC,},
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_1 = new JLabel("Financiera");
		add(lblNewLabel_1, "2, 2, left, default");
		
		comboBoxFinanciera = new JComboBox<String>();
		add(comboBoxFinanciera, "4, 2, fill, default");
		
		chckbxExtenderGarantia = new JCheckBox("Extender garantía");
		add(chckbxExtenderGarantia, "6, 2");
		
		chckbxNewCheckBox = new JCheckBox("Es venta en efectivo");
		add(chckbxNewCheckBox, "8, 2, 3, 1");
		
		JLabel lblNewLabel = new JLabel("Monto financiado");
		add(lblNewLabel, "2, 4");
		
		textFieldMontoFinanciado = new JTextField();
		add(textFieldMontoFinanciado, "4, 4, fill, default");
		textFieldMontoFinanciado.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Nro. de cuotas");
		add(lblNewLabel_3, "6, 4, left, default");
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, 500, 1);
		spinner = new JSpinner(sm);
		add(spinner, "8, 4");
		
		JLabel lblNewLabel_4 = new JLabel("Monto cuota");
		add(lblNewLabel_4, "2, 6, left, default");
		
		textFieldMontoCuota = new JTextField();
		add(textFieldMontoCuota, "4, 6, fill, default");
		textFieldMontoCuota.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Precio final");
		add(lblNewLabel_6, "6, 6, left, default");
		
		textFieldPrecioFinal = new JTextField();
		add(textFieldPrecioFinal, "8, 6, fill, default");
		textFieldPrecioFinal.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Comisión por venta");
		add(lblNewLabel_7, "2, 8, right, default");
		
		textFieldComisionVenta = new JTextField();
		add(textFieldComisionVenta, "4, 8, fill, default");
		textFieldComisionVenta.setColumns(10);
		
		btnRegistrarVenta = new JButton("Registrar venta");
		add(btnRegistrarVenta, "8, 10, right, default");
		clearData();
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

	public void setNoEditable() {
		this.textFieldMontoCuota.setEditable(false);
		this.textFieldMontoFinanciado.setEditable(false);
		this.comboBoxFinanciera.setEditable(false);
		this.textFieldComisionVenta.setEditable(false);
		this.textFieldMontoCuota.setEditable(false);
		this.textFieldPrecioFinal.setEditable(false);
	}
	
	public void disableFinanciamiento() {
		this.textFieldMontoCuota.setEnabled(false);
		this.textFieldMontoFinanciado.setEnabled(false);
		this.comboBoxFinanciera.setEnabled(false);
		this.spinner.setEnabled(false);
		this.textFieldMontoFinanciado.setEnabled(false);
	}
	
	public void enableFinanciamiento() {
		this.textFieldMontoCuota.setEnabled(true);
		this.textFieldMontoFinanciado.setEnabled(true);
		this.comboBoxFinanciera.setEnabled(true);		
		this.spinner.setEnabled(true);
	}

	public ModalidadVentaVehiculoDTO getData() {
		ModalidadVentaVehiculoDTO dto = new ModalidadVentaVehiculoDTO();
		dto.setEfectivo(this.chckbxNewCheckBox.isSelected());
		dto.setFinanciera(this.comboBoxFinanciera.getSelectedItem().toString());
		dto.setNroCuotas(this.spinner.getValue().toString());
		dto.setMontoCuota(this.textFieldMontoCuota.getText());
		dto.setComision(this.textFieldComisionVenta.getText());
		dto.setMontoFinanciado(this.textFieldMontoFinanciado.getText());
		return dto;
	}

	public void clearData() {
		this.chckbxNewCheckBox.setSelected(false);
		this.spinner.setValue(1);
		this.textFieldComisionVenta.setText("");
		this.textFieldMontoCuota.setText("");
		this.textFieldPrecioFinal.setText("");
		this.textFieldComisionVenta.setText("");
		this.textFieldMontoFinanciado.setText("");
	}

	public void setMontoFinanciado(String precio) {
		this.textFieldMontoFinanciado.setText(precio);
	}

	public void setComisionVendedor(String string) {
		this.textFieldComisionVenta.setText(string);
	}

	public void setPrecioFinalVenta(String string) {
		this.textFieldPrecioFinal.setText(string);
	}
	
	public void setActionRegistrarVenta(ActionListener listener) {
		this.btnRegistrarVenta.addActionListener(listener);
	}
	
	public void setActionUpdtNroDeCuotas(ChangeListener listener) {
		this.spinner.addChangeListener(listener);
	}

	public void setMontoCuota(String montoCuota) {
		this.textFieldMontoCuota.setText(montoCuota);
	}
	
	public boolean isExtenderGarantia() {
		return this.chckbxExtenderGarantia.isSelected();
	}

	public void setActionExtenderGarantia(ActionListener listener) {
		this.chckbxExtenderGarantia.addActionListener(listener);
	}
}
