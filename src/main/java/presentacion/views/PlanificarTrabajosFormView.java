package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class PlanificarTrabajosFormView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8919580357142104418L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	public PlanificarTrabajosFormView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNombreTabla = new JLabel("Listado de Trabajos Planificados");
		lblNombreTabla.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNombreTabla, BorderLayout.NORTH);

		table = new JTable();

		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel panelBotones = new JPanel();
		contentPanel.add(panelBotones, BorderLayout.SOUTH);

		JButton btnQuitarTodo = new JButton("Quitar todo");
		panelBotones.add(btnQuitarTodo);

		JButton btnQuitar = new JButton("Quitar");
		panelBotones.add(btnQuitar);

		JButton btnEditar = new JButton("Editar");
		panelBotones.add(btnEditar);

		JButton btnAgregar = new JButton("Agregar");
		panelBotones.add(btnAgregar);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setActionCommand("OK");
		buttonPane.add(botonAceptar);
		getRootPane().setDefaultButton(botonAceptar);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

}
