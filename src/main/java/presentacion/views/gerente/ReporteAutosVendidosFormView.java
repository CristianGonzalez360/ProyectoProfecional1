package presentacion.views.gerente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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
		setBounds(100, 100, 400, 280);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panelCentral = new JPanel();
		panelCentral.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

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
}
