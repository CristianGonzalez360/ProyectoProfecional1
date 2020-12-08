package presentacion.views.admin;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class PanelBusquedaSucursal extends JPanel {

	private static final long serialVersionUID = 633811077360140055L;

	private JComboBox<String> comboBoxPais;
	
	private JButton btnBuscar;

	private JLabel searchLabel;

	public PanelBusquedaSucursal() {
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		searchLabel = new JLabel("Pais");
		add(searchLabel);
		
		comboBoxPais = new JComboBox<String>();
		add(comboBoxPais);
		
		btnBuscar = new JButton("Buscar");
		add(btnBuscar);
	}

	public void setSearchLabelName(String title) {
		this.searchLabel.setText(title);
	}
	
	public String getData() {
		if(comboBoxPais.getSelectedItem() != null) {
			return this.comboBoxPais.getSelectedItem().toString();
		}
		return "";
	}

	public void setActionBuscar(ActionListener listener) {
		this.btnBuscar.addActionListener(listener);
	}

	public void addPaises(String[] strings) {
		for(String str: strings) {
			this.comboBoxPais.addItem(str);
		}
	}
}
