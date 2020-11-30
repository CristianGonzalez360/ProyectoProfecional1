package presentacion.views.vendedor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import dto.temporal.ConsultaVehiculoParaVentaDTO;

public class BusquedaVehiculoPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4304226028387787383L;
	
	private JTextField textFamilia;

	private JComboBox<String> comboBoxTipo;

	private JButton btnConsultar;
	private JComboBox<String> comboBoxMarca;
	private JLabel lblNewLabel_4;
	private JTextField textFieldLinea;
	
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
		
		comboBoxMarca = new JComboBox<String>();
		add(comboBoxMarca);
		
		JLabel lblNewLabel_2 = new JLabel("Familia");
		add(lblNewLabel_2);
		
		textFamilia = new JTextField();
		add(textFamilia);
		textFamilia.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Linea");
		add(lblNewLabel_4);
		
		textFieldLinea = new JTextField();
		add(textFieldLinea);
		textFieldLinea.setColumns(10);
		
		btnConsultar = new JButton("Buscar");
		add(btnConsultar);
		
		clearData();
	}
	
	public void clearData() {
		this.textFamilia.setText("");
		this.textFieldLinea.setText("");
	}
	
	public ConsultaVehiculoParaVentaDTO getData() {
		ConsultaVehiculoParaVentaDTO dto = new ConsultaVehiculoParaVentaDTO();
		dto.setMarca(this.comboBoxMarca.getSelectedItem().toString());
		dto.setFamilia(this.textFamilia.getText());
		dto.setLinea(this.textFamilia.getText());
		dto.setTipo(this.comboBoxTipo.getSelectedItem().toString());
		return dto;
	}
	
	public void setActionBuscar(ActionListener listener) {
		this.btnConsultar.addActionListener(listener);
	}
	
	public void addMarcas(List<String> marcas) {
		for(String str: marcas) this.comboBoxMarca.addItem(str);
	}

	public void addTipos(String[] tipos) {
		assert tipos != null;
		for(String str : tipos) this.comboBoxTipo.addItem(str);
	}
}
