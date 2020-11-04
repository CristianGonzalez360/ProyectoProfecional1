package presentacion.views;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PlanificarTrabajosView extends JDialog {
	private JTable table;
	public PlanificarTrabajosView() {
		setTitle("Planificación de Trabajos");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnQuitar = new JButton("Quitar");
		panel_1.add(btnQuitar);
		
		JButton btnEditar = new JButton("Editar");
		panel_1.add(btnEditar);
		
		JButton btnAgregar = new JButton("Agregar");
		panel_1.add(btnAgregar);
		
		table = new JTable();
		JScrollPane scroll = new JScrollPane(table);
		panel.add(scroll, BorderLayout.CENTER);
		
		JLabel lblTrabajosPlanificados = new JLabel("Trabajos Planificados");
		lblTrabajosPlanificados.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTrabajosPlanificados, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Aceptar");
		panel_2.add(btnAceptar);
	}

}
