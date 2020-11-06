package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dto.TrabajoPresupuestadoDTO;

import javax.swing.JTable;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;

public class PlanificarTrabajosFormView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8919580357142104418L;
	
	private final String[] columnas = new String[] {"DESCRIPCIÓN", "ESFUERZO", "PRECIO"};
	private static PlanificarTrabajosFormView instance;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel modelo;
	private JButton btnAgregar;
	private JButton botonAceptar;
	private JButton cancelButton;

	public PlanificarTrabajosFormView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Trabajos Planificados", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		modelo = new DefaultTableModel(null, columnas);
		table.setModel(modelo);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(100);
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

		btnAgregar = new JButton("Agregar");
		panelBotones.add(btnAgregar);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		botonAceptar.setActionCommand("OK");
		buttonPane.add(botonAceptar);
		getRootPane().setDefaultButton(botonAceptar);

		cancelButton = new JButton("Cancelar");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		setVisible(false);
	}
	
	public void close() {
		setVisible(false);
	}

	public static PlanificarTrabajosFormView getInstance() {
		if(instance == null) instance = new PlanificarTrabajosFormView();
		return instance;
	}
	
	public void setActionOnAgregarTrabajo(ActionListener a) {
		this.btnAgregar.addActionListener(a);
	}
	
	public void clearData() {
		modelo.setRowCount(0);
	}

	public void display() {
		setVisible(true);
	}

	public void setData(List<TrabajoPresupuestadoDTO> trabajos) {
		modelo.setRowCount(0);
		for (TrabajoPresupuestadoDTO t : trabajos) {
			Object[] row = { t.getDescripcionTrabajo(), t.getTiempoEstTrabajo(), t.getPrecioTrabajo()};
			modelo.addRow(row);
		}
	}

	public void setActionOnAceptar(ActionListener listener) {
		this.botonAceptar.addActionListener(listener);		
	}


}
