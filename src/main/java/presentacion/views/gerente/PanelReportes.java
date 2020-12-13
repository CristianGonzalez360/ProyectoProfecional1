package presentacion.views.gerente;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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


	public PanelReportes() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Seleccione un reporte : ");
		add(lblNewLabel);
		
		JButton btnIngresosDiarios = new JButton("Reporte Ingresos Diarios");
		btnIngresosDiarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnIngresosDiarios);
		
		JButton btnEgresosDiarios = new JButton("Reporte Egresos Diarios");
		btnEgresosDiarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnEgresosDiarios);
		
		JButton btnIngresosMensuales = new JButton("Reporte Ingresos Mensuales");
		btnEgresosDiarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnIngresosMensuales);
		
		JButton btnIngresosSemanales = new JButton("Reporte Ingresos Semanales");
		btnEgresosDiarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnIngresosSemanales);
		
		JButton btnRepuestos = new JButton("Reporte De Repuestos");
		btnEgresosDiarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnRepuestos);
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
}
