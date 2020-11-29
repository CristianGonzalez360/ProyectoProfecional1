package presentacion.views.vendedor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import dto.temporal.ConsultaVehiculoParaVentaDTO;

public class BusquedaVehiculoPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4304226028387787383L;
	
	private JTextField textMarca;
	
	private JTextField textLinea;

	private JComboBox<String> comboBoxTipo;

	private JComboBox<String> comboBoxSucursal;

	private JButton btnConsultar;
	
	public BusquedaVehiculoPanel() {
		setBorder(new TitledBorder(null, "Consulta de veh\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setHgap(10);
		
		JLabel lblNewLabel = new JLabel("Tipo");
		add(lblNewLabel);
		
		comboBoxTipo = new JComboBox<String>();
		add(comboBoxTipo);
		
		JLabel lblNewLabel_1 = new JLabel("Marca");
		add(lblNewLabel_1);
		
		textMarca = new JTextField();
		add(textMarca);
		textMarca.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Familia");
		add(lblNewLabel_2);
		
		textLinea = new JTextField();
		add(textLinea);
		textLinea.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Sucursal");
		add(lblNewLabel_3);
		
		comboBoxSucursal = new JComboBox<String>();
		add(comboBoxSucursal);
		
		btnConsultar = new JButton("Buscar");
		add(btnConsultar);
		
		clearData();
	}
	
	public void clearData() {
		this.textMarca.setText("");
		this.textLinea.setText("");
	}
	
	public ConsultaVehiculoParaVentaDTO getData() {
		ConsultaVehiculoParaVentaDTO dto = new ConsultaVehiculoParaVentaDTO();
		dto.setMarca(this.textMarca.getText());
		dto.setLinea(this.textLinea.getText());
		dto.setTipo(this.comboBoxTipo.getSelectedItem().toString());
		dto.setSucursal(this.comboBoxSucursal.getSelectedItem().toString());
		return dto;
	}
	
	public void setActionBuscar(ActionListener listener) {
		this.btnConsultar.addActionListener(listener);
	}
}
