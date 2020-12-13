package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

	public ReporteAutosVendidosFormView() {
		setTitle("Reporte Autos Vendidos");
		setBounds(100, 100, 506, 253);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.SOUTH);
		
		btnGenerarReporte = new JButton("GenerarReporte");
		panel.add(btnGenerarReporte);
		
		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1, BorderLayout.CENTER);
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
}
