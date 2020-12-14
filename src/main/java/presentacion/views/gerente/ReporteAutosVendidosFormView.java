package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

public class ReporteAutosVendidosFormView extends JDialog {

	private static final long serialVersionUID = 1L;
	private static ReporteAutosVendidosFormView instance;

	public static ReporteAutosVendidosFormView getInstance() {
		if (instance == null) {
			instance = new ReporteAutosVendidosFormView();
		}
		return instance;
	}

	private final JPanel contentPanel = new JPanel();

	private JButton btnGenerarReporte;

	private JDateChooser fechaDesde;
	private JDateChooser fechaHasta;
	private JComboBox<String> comboMarca;
	private JComboBox<String> comboModelo;
	private JComboBox<String> comboAnio;

	public ReporteAutosVendidosFormView() {
		setTitle("Reporte Autos Vendidos");
		setBounds(100, 100, 400, 280);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panelCentral = new JPanel();
		panelCentral.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblDesde = new JLabel("Desde");
		panelCentral.add(lblDesde, "4, 4");

		fechaDesde = new JDateChooser();
		panelCentral.add(fechaDesde, "6, 4");

		JLabel lblHasta = new JLabel("Hasta");
		panelCentral.add(lblHasta, "8, 4");

		fechaHasta = new JDateChooser();
		panelCentral.add(fechaHasta, "10, 4");

		JLabel lblMarca = new JLabel("Marca");
		panelCentral.add(lblMarca, "4, 8");

		comboMarca = new JComboBox<String>();
		panelCentral.add(comboMarca, "10, 8, fill, default");

		JLabel lblModelo = new JLabel("Modelo");
		panelCentral.add(lblModelo, "4, 10");

		comboModelo = new JComboBox<String>();
		panelCentral.add(comboModelo, "10, 10, fill, default");

		JLabel lblAnio = new JLabel("Año");
		panelCentral.add(lblAnio, "4, 12");

		comboAnio = new JComboBox<String>();
		panelCentral.add(comboAnio, "10, 12, fill, default");

		JPanel panelInferior = new JPanel();
		contentPanel.add(panelInferior, BorderLayout.SOUTH);

		btnGenerarReporte = new JButton("GenerarReporte");
		panelInferior.add(btnGenerarReporte);

	}

	public void clearData() {
		this.btnGenerarReporte.setVisible(true);
	}

	public void display() {
		setVisible(true);
	}

	public void setActionGenerarReporte(ActionListener listener) {
		this.btnGenerarReporte.addActionListener(listener);
	}

	public void setData(String [] marcas) {
		comboMarca.removeAllItems();
		comboMarca.addItem("Todos");
		for(String marca : marcas) {
			comboMarca.addItem(marca);
		}
	}
	
	
	public JButton getBtnGenerarReporte() {
		return btnGenerarReporte;
	}

	public void setBtnGenerarReporte(JButton btnGenerarReporte) {
		this.btnGenerarReporte = btnGenerarReporte;
	}

	public JDateChooser getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(JDateChooser fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public JDateChooser getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(JDateChooser fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public JComboBox<String> getComboMarca() {
		return comboMarca;
	}

	public void setComboMarca(JComboBox<String> comboMarca) {
		this.comboMarca = comboMarca;
	}

	public JComboBox<String> getComboModelo() {
		return comboModelo;
	}

	public void setComboModelo(JComboBox<String> comboModelo) {
		this.comboModelo = comboModelo;
	}

	public JComboBox<String> getComboAnio() {
		return comboAnio;
	}

	public void setComboAnio(JComboBox<String> comboAnio) {
		this.comboAnio = comboAnio;
	}
}
