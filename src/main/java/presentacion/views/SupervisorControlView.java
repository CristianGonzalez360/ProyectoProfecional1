package presentacion.views;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.beans.PropertyVetoException;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SupervisorControlView extends JInternalFrame {

	private static final long serialVersionUID = 4306672868994985561L;

	private static SupervisorControlView instance;
	private JTextField textField;
	private JTable table;
	
	private DefaultTableModel tableModelTurnos;
	
	public static SupervisorControlView getInstance() {
		if(instance == null) instance = new SupervisorControlView();
		return instance;
	}
	
	private SupervisorControlView() {
		
		setMaximizable(true);
		setIconifiable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Supervisor control view");
		setBounds(100, 100, 859, 647);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel clientesPanel = new JPanel();
		tabbedPane.addTab("Clientes", null, clientesPanel, null);

		JPanel turnosPanel = new JPanel();
		tabbedPane.addTab("Turnos", null, turnosPanel, null);
		turnosPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(20);
		turnosPanel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("DNI CLIENTE");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textField);
		textField.setColumns(20);
		
		JButton btnNewButton = new JButton("buscar turnos");
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		turnosPanel.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_2.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		turnosPanel.add(scrollPane, BorderLayout.CENTER);
		
		tableModelTurnos = new DefaultTableModel(null, new String [] {"NRO. TURNO", "NOMBRE CLIENTE", "DNI CLIENTE", "FECHA DE ALTA", "FECHA PROGRAMADA"});
		table = new JTable(tableModelTurnos);
		scrollPane.setViewportView(table);

		JPanel otPresupuestadasPanel = new JPanel();
		tabbedPane.addTab("OT presupuestadas", null, otPresupuestadasPanel, null);

		JPanel panel = new JPanel();
		tabbedPane.addTab("OT para cerrar", null, panel, null);
		
	}

	public void display() {
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}

	public void clearData() {
		// TODO Auto-generated method stub
		
	}
}
