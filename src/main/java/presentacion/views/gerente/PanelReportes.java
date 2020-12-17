package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

public class PanelReportes extends JPanel {
	private static final long serialVersionUID = 1L;
	private static PanelReportes instance;

	public static PanelReportes getInstance() {
		if (instance == null)
			instance = new PanelReportes();
		return instance;
	}

	private JDateChooser fechaIngresoDesde;
	private JDateChooser fechaIngresoHasta;
	private JButton btnReporteIngresos;

	private JDateChooser fechaEgresoDesde;
	private JDateChooser fechaEgresoHasta;
	private JButton btnReporteEgresos;

	private JDateChooser fechaVentasDesde;
	private JDateChooser fechaVentasHasta;
	private JButton btnReporteVentas;

	public PanelReportes() {
		setLayout(new BorderLayout(0, 0));
		setLayout(new BorderLayout(5, 5));

		JPanel panelSuperior = new JPanel();
		panelSuperior.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout fl_panelSuperior = (FlowLayout) panelSuperior.getLayout();
		fl_panelSuperior.setVgap(40);
		fl_panelSuperior.setHgap(20);
		add(panelSuperior, BorderLayout.NORTH);

		JPanel panelCentral = new JPanel();
		panelCentral.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Reportes",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.X_AXIS));

		JPanel panelReporteIngresos = new JPanel();
		panelReporteIngresos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Reporte de Ingresos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCentral.add(panelReporteIngresos);
		panelReporteIngresos.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("46px:grow"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.PREF_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.MIN_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblIngresosDesde = new JLabel("Ingresos Desde");
		panelReporteIngresos.add(lblIngresosDesde, "2, 4, 3, 1");

		fechaIngresoDesde = new JDateChooser();
		panelReporteIngresos.add(fechaIngresoDesde, "6, 4, fill, default");

		JLabel lblIngresosHasta = new JLabel("Ingresos Hasta");
		panelReporteIngresos.add(lblIngresosHasta, "2, 6, 3, 1, fill, fill");

		fechaIngresoHasta = new JDateChooser();
		panelReporteIngresos.add(fechaIngresoHasta, "6, 6, fill, default");

		btnReporteIngresos = new JButton("Ver Reporte de Ingresos");
		panelReporteIngresos.add(btnReporteIngresos, "6, 12");

		JPanel panelReporteEgresos = new JPanel();
		panelReporteEgresos.setBorder(
				new TitledBorder(null, "Reporte de Egresos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentral.add(panelReporteEgresos);
		panelReporteEgresos.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, ColumnSpec.decode("46px:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.DEFAULT_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblEgresosDesde = new JLabel("Egresos Desde");
		panelReporteEgresos.add(lblEgresosDesde, "2, 4, left, top");

		fechaEgresoDesde = new JDateChooser();
		panelReporteEgresos.add(fechaEgresoDesde, "6, 4, fill, default");

		JLabel lblEgresosHasta = new JLabel("Egresos Hasta");
		panelReporteEgresos.add(lblEgresosHasta, "2, 6");

		fechaEgresoHasta = new JDateChooser();
		panelReporteEgresos.add(fechaEgresoHasta, "6, 6, fill, default");

		btnReporteEgresos = new JButton("Ver Reporte de Egresos");
		panelReporteEgresos.add(btnReporteEgresos, "6, 12");

		JPanel panelReporteVentas = new JPanel();
		panelReporteVentas.setBorder(new TitledBorder(null, "Reporte de Venta de Unidad", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelCentral.add(panelReporteVentas);
		panelReporteVentas.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.LABEL_COMPONENT_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(45dlu;default):grow"), FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.DEFAULT_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblVentasDesde = new JLabel("Ventas Desde");
		panelReporteVentas.add(lblVentasDesde, "2, 4, left, top");

		fechaVentasDesde = new JDateChooser();
		panelReporteVentas.add(fechaVentasDesde, "6, 4, fill, default");

		JLabel lblVentasHasta = new JLabel("Ventas Hasta");
		panelReporteVentas.add(lblVentasHasta, "2, 6");

		fechaVentasHasta = new JDateChooser();
		panelReporteVentas.add(fechaVentasHasta, "6, 6, fill, default");

		btnReporteVentas = new JButton("Ver Reporte de Ventas");
		panelReporteVentas.add(btnReporteVentas, "6, 12");

		JPanel panelInferior = new JPanel();
		panelInferior.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout fl_panelInferior = (FlowLayout) panelInferior.getLayout();
		fl_panelInferior.setVgap(80);
		fl_panelInferior.setHgap(25);
		add(panelInferior, BorderLayout.SOUTH);
	}

	public Date getFechaIngresoDesde() {
		return fechaIngresoDesde.getDate();
	}

	public Date getFechaIngresoHasta() {
		return fechaIngresoHasta.getDate();
	}

	public Date getFechaEgresoDesde() {
		return fechaEgresoDesde.getDate();
	}

	public Date getFechaEgresoHasta() {
		return fechaEgresoHasta.getDate();
	}

	public Date getFechaVentasDesde() {
		return fechaVentasDesde.getDate();
	}

	public Date getFechaVentasHasta() {
		return fechaVentasHasta.getDate();
	}

	public void setActionReporteIngresos(ActionListener listener) {
		this.btnReporteIngresos.addActionListener(listener);
	}

	public void setActionReporteEgresos(ActionListener listener) {
		this.btnReporteEgresos.addActionListener(listener);
	}

	public void setActionReporteVentas(ActionListener listener) {
		this.btnReporteVentas.addActionListener(listener);
	}

}
