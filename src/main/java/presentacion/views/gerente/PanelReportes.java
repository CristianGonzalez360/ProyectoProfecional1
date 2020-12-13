package presentacion.views.gerente;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelReportes extends JPanel {
	private static final long serialVersionUID = 1L;
	private static PanelReportes instance;

	public static PanelReportes getInstance() {
		if (instance == null)
			instance = new PanelReportes();
		return instance;
	}

	private JButton btnIngresosDiarios;
	private JButton btnEgresosDiarios;
	private JButton btnIngresosMensuales;
	private JButton btnIngresosSemanales;
	private JButton btnRepuestos;

	private JButton btnAutosVendidos;

	public PanelReportes() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("Seleccione un reporte : ");
		add(lblNewLabel);

		btnIngresosDiarios = new JButton("Reporte Ingresos Diarios");
		add(btnIngresosDiarios);

		btnEgresosDiarios = new JButton("Reporte Egresos Diarios");
		add(btnEgresosDiarios);

		btnIngresosMensuales = new JButton("Reporte Ingresos Mensuales");
		add(btnIngresosMensuales);

		btnIngresosSemanales = new JButton("Reporte Ingresos Semanales");
		add(btnIngresosSemanales);

		btnRepuestos = new JButton("Reporte De Repuestos");
		add(btnRepuestos);

		btnAutosVendidos = new JButton("Reporte de Auto Vendidos");
		add(btnAutosVendidos);
	}

	public void setActionDisplayIngresosDiarios(ActionListener listener) {
		this.btnIngresosDiarios.addActionListener(listener);
	}

	public void setActionDisplayEgresosDiarios(ActionListener listener) {
		this.btnEgresosDiarios.addActionListener(listener);
	}

	public void setActionDisplayIngresoMensual(ActionListener listener) {
		this.btnIngresosMensuales.addActionListener(listener);
	}

	public void setActionDisplayIngresoSemanal(ActionListener listener) {
		this.btnIngresosSemanales.addActionListener(listener);
	}

	public void setActionDisplayreportes(ActionListener listener) {
		this.btnRepuestos.addActionListener(listener);
	}

	public void setActionDisplayReporteAutosVendidos(ActionListener listener) {
		this.btnAutosVendidos.addActionListener(listener);
	}
	
}
