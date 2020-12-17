package presentacion.views.gerente;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridLayout;

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
		setLayout(new BorderLayout(0, 0));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setVgap(20);
		flowLayout_1.setHgap(20);
		add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Reporte de Ingresos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_3.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_3.add(lblNewLabel_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Reporte de Egresos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setVgap(20);
		flowLayout_3.setHgap(20);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel_4.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		panel_4.add(lblNewLabel_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Reporte de Venta de Unidad", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		FlowLayout flowLayout_4 = (FlowLayout) panel_5.getLayout();
		flowLayout_4.setVgap(20);
		flowLayout_4.setHgap(20);
		panel_1.add(panel_5);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		panel_5.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		panel_5.add(lblNewLabel_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(20);
		add(panel_2, BorderLayout.SOUTH);
//
//		JLabel lblNewLabel = new JLabel("Seleccione un reporte : ");
//		add(lblNewLabel);
//
//		btnIngresosDiarios = new JButton("Reporte Ingresos Diarios");
//		add(btnIngresosDiarios);
//
//		btnEgresosDiarios = new JButton("Reporte Egresos Diarios");
//		add(btnEgresosDiarios);
//
//		btnIngresosMensuales = new JButton("Reporte Ingresos Mensuales");
//		add(btnIngresosMensuales);
//
//		btnIngresosSemanales = new JButton("Reporte Ingresos Semanales");
//		add(btnIngresosSemanales);
//
//		btnRepuestos = new JButton("Reporte De Repuestos");
//		add(btnRepuestos);
//
//		btnAutosVendidos = new JButton("Reporte de Auto Vendidos");
//		add(btnAutosVendidos);
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
