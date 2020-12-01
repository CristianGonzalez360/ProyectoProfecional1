package presentacion.views.admin;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.MonedaDTO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelDatosMoneda extends JPanel {
	
	private static final long serialVersionUID = 1981816220276090041L;

	private JTextField textCotizacion;
	
	private final JLabel lblNewLabel_1 = new JLabel("Simbolo");
	
	private JTextField textNombre;
	
	private JTextField textSimbolo;

	public PanelDatosMoneda() {
		setBorder(new TitledBorder(null, "Datos de la moneda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Nombre");
		add(lblNewLabel, "2, 2, right, default");
		
		textNombre = new JTextField();
		add(textNombre, "4, 2, fill, default");
		textNombre.setColumns(10);
		add(lblNewLabel_1, "6, 2, right, default");
		
		textSimbolo = new JTextField();
		add(textSimbolo, "8, 2, fill, default");
		textSimbolo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cotización con el dolar");
		add(lblNewLabel_2, "10, 2, right, default");
		
		textCotizacion = new JTextField();
		add(textCotizacion, "12, 2, fill, default");
		textCotizacion.setColumns(10);
	}
	
	public void setNonEditable() {
		this.textCotizacion.setEditable(false);
		this.textNombre.setEditable(false);
		this.textSimbolo.setEditable(false);
	}

	public void clearData() {
		this.textCotizacion.setText("");
		this.textNombre.setText("");
		this.textSimbolo.setText("");
	}
	
	public void setData(MonedaDTO moneda) {
		this.textCotizacion.setText(moneda.getCotizacionDolar().toString());
		this.textNombre.setText(moneda.getNombre());
		this.textSimbolo.setText(moneda.getSimbolo());
	}
}
