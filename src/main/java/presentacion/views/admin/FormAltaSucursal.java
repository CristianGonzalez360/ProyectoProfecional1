package presentacion.views.admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.temporal.AltaSucursalDTO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class FormAltaSucursal extends JDialog {

	private static final long serialVersionUID = -3546851129503005540L;

	private final JPanel contentPanel = new JPanel();
	
	private JTextField textCalle;
	
	private JTextField textAltura;
	
	private JTextField textLocalidad;

	private JComboBox<String> comboBoxPais;

	private JButton okButton;

	private JButton cancelButton;

	private static FormAltaSucursal instance;
	
	private FormAltaSucursal() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setTitle("Form alta de sucursal");
		setBounds(100, 100, 294, 222);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			JLabel lblNewLabel = new JLabel("Pais");
			contentPanel.add(lblNewLabel, "2, 2, left, default");
		}
		{
			comboBoxPais = new JComboBox<String>();
			contentPanel.add(comboBoxPais, "4, 2, fill, default");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Calle");
			contentPanel.add(lblNewLabel_1, "2, 4, left, default");
		}
		{
			textCalle = new JTextField();
			contentPanel.add(textCalle, "4, 4, fill, default");
			textCalle.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Altura");
			contentPanel.add(lblNewLabel_2, "2, 6, left, default");
		}
		{
			textAltura = new JTextField();
			contentPanel.add(textAltura, "4, 6, fill, default");
			textAltura.setColumns(10);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Localidad");
			contentPanel.add(lblNewLabel_3, "2, 8, left, default");
		}
		{
			textLocalidad = new JTextField();
			contentPanel.add(textLocalidad, "4, 8, fill, default");
			textLocalidad.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static FormAltaSucursal getInstance() {
		if(instance == null) instance = new FormAltaSucursal();
		return instance;
	}
	
	public void clearData() {
		comboBoxPais.removeAllItems();
		textAltura.setText("");
		textCalle.setText("");
		textLocalidad.setText("");
	}
	
	public AltaSucursalDTO getData() {
		AltaSucursalDTO dto = new AltaSucursalDTO();
		dto.setAltura(textAltura.getText());
		dto.setCalle(textCalle.getText());
		dto.setLocalidad(textLocalidad.getText());
		dto.setPais(comboBoxPais.getSelectedItem() == null ? "" : comboBoxPais.getSelectedItem().toString());
		return dto;
	}
	
	public void setDataPaises(List<String> paises) {
		for(String pais: paises) this.comboBoxPais.addItem(pais);
	}
	
	public void setActionOk(ActionListener listener) {
		this.okButton.addActionListener(listener);
	}
	
	public void setActionCancel(ActionListener listener) {
		this.cancelButton.addActionListener(listener);
	}

	public void onDisplay() {
		this.setVisible(true);
	}
	
	public void close() {
		this.dispose();
	}

	public void setactionOnSelectPais(ActionListener listener) {
		this.comboBoxPais.addActionListener(listener);
	}
}
