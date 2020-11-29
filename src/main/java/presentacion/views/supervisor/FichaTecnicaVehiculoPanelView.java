package presentacion.views.supervisor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import dto.taller.FichaTecnicaVehiculoDTO;

public class FichaTecnicaVehiculoPanelView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2115071730650608664L;
	private JTextField textNroMotor;
	private JTextField textNroDeChasis;
	private JTextField textMarca;
	private JTextField textModelo;
	private JTextField textColor;
	private JTextField textCombustion;
	/**
	 * Create the panel.
	 */
	public FichaTecnicaVehiculoPanelView() {
		setBorder(new TitledBorder(null, "Ficha tecnica del vehiculo", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel_8 = new JLabel("Nro de motor");
		add(lblNewLabel_8, "2, 2");

		textNroMotor = new JTextField();
		add(textNroMotor, "4, 2, fill, default");
		textNroMotor.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Nro de chasis");
		add(lblNewLabel_9, "2, 4");

		textNroDeChasis = new JTextField();
		add(textNroDeChasis, "4, 4, fill, default");
		textNroDeChasis.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Marca");
		add(lblNewLabel_10, "2, 6");

		textMarca = new JTextField();
		add(textMarca, "4, 6, fill, default");
		textMarca.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Modelo");
		add(lblNewLabel_11, "2, 8");

		textModelo = new JTextField();
		add(textModelo, "4, 8, fill, default");
		textModelo.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Color");
		add(lblNewLabel_12, "2, 10");

		textColor = new JTextField();
		add(textColor, "4, 10, fill, default");
		textColor.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Combustion");
		add(lblNewLabel_13, "2, 12");

		textCombustion = new JTextField();
		add(textCombustion, "4, 12, fill, default");
		textCombustion.setColumns(10);

	}
	
	public void disableAllFichaTecnicaInputs() {
		this.textNroDeChasis.setEditable(false);
		this.textNroMotor.setEditable(false);
		this.textMarca.setEditable(false);
		this.textColor.setEditable(false);
		this.textCombustion.setEditable(false);
		this.textModelo.setEditable(false);
	}

	public void clearData() {
		this.textNroDeChasis.setText("");
		this.textNroMotor.setText("");
		this.textMarca.setText("");
		this.textColor.setText("");
		this.textCombustion.setText("");
		this.textModelo.setText("");
	}
	
	public void setData(FichaTecnicaVehiculoDTO fichaVehiculo) {
		fichaVehiculo.getId();
		this.textNroDeChasis.setText(fichaVehiculo.getNroChasis().toString());
		this.textNroMotor.setText(fichaVehiculo.getNroMotor().toString());
		this.textMarca.setText(fichaVehiculo.getMarca());
		this.textColor.setText(fichaVehiculo.getColor());
		this.textCombustion.setText(fichaVehiculo.getCombustion());
		this.textModelo.setText(fichaVehiculo.getModelo().toString());
	}
}
