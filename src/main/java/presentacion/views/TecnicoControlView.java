package presentacion.views;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TecnicoControlView extends JInternalFrame {

	private static final long serialVersionUID = 4704503289092275653L;

	private static TecnicoControlView instance;
	
	private String[] columnasOrdenTrabajo = {"tipo OT","Fecha alta","Usuario de alta","Fecha aprobacion"};//aca se modifican las columnas de la tabla orden de trabajo
	private JTable tablaOrdenesDeTrabajo;
	private String[] columnasRepuesto = {"Codigo","Precio","Marca","Stock"};//aca se modifican las columnas de la tabla repuesto
	private JTable tablaDeRepuesto;
	private String[] columnasTrabajoPresupuestado = {"Monto"};//aca se modifican las columnas de la tabla trabajo presupuestado
	private JTable tablaDeTrabajoPresupuestado;
	private String[] columnasOTParaPresupuestar = {"Fecha alta","Usuario de alta"};
	private JTable tablaDeOTParaPresupuestar;
	private JButton btnPresupuestar;
	
	private JTextField textFechaAlta;
	private JTextField textUsuarioAlta;
	private JTextField textFechaAprobacion;
	private JTextField textComentarioDeAlta;
	
	public static TecnicoControlView getInstance() {
		if (instance == null)
			instance = new TecnicoControlView();
		return instance;
	}

	private TecnicoControlView() {
		setClosable(false);
		setTitle("Tecnico control view");
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 800, 436);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel otConPresupuestosPagos = new JPanel();
		tabbedPane.addTab("OT con presupuestos pagos", null, otConPresupuestosPagos, null);
		otConPresupuestosPagos.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 298, 350);
		otConPresupuestosPagos.add(panel);
		panel.setLayout(null);
		
		JLabel lblOrdenesDeTrabajo = new JLabel("Ordenes de trabajo");
		lblOrdenesDeTrabajo.setBounds(10, 11, 117, 14);
		panel.add(lblOrdenesDeTrabajo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 278, 303);
		panel.add(scrollPane);
		
		DefaultTableModel modelOrdenesDeTrabajo = (new DefaultTableModel(null,columnasOrdenTrabajo));
		tablaOrdenesDeTrabajo = new JTable(modelOrdenesDeTrabajo);
		scrollPane.setViewportView(tablaOrdenesDeTrabajo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(308, 11, 461, 350);
		otConPresupuestosPagos.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblFechaAlta = new JLabel("Fecha alta");
		lblFechaAlta.setBounds(10, 11, 82, 14);
		panel_1.add(lblFechaAlta);
		
		JLabel lblUsuarioAlta = new JLabel("Usuario alta");
		lblUsuarioAlta.setBounds(10, 36, 82, 14);
		panel_1.add(lblUsuarioAlta);
		
		JLabel lblFechaAprobacion = new JLabel("Fecha aprobacion");
		lblFechaAprobacion.setBounds(242, 14, 106, 14);
		panel_1.add(lblFechaAprobacion);
		
		JLabel lblComentarioDeAlta = new JLabel("Comentario de alta");
		lblComentarioDeAlta.setBounds(10, 67, 106, 14);
		panel_1.add(lblComentarioDeAlta);
		
		textFechaAlta = new JTextField();
		textFechaAlta.setBounds(133, 8, 86, 20);
		panel_1.add(textFechaAlta);
		textFechaAlta.setColumns(10);
		
		textUsuarioAlta = new JTextField();
		textUsuarioAlta.setBounds(133, 33, 86, 20);
		panel_1.add(textUsuarioAlta);
		textUsuarioAlta.setColumns(10);
		
		textFechaAprobacion = new JTextField();
		textFechaAprobacion.setBounds(365, 11, 86, 20);
		panel_1.add(textFechaAprobacion);
		textFechaAprobacion.setColumns(10);
		
		textComentarioDeAlta = new JTextField();
		textComentarioDeAlta.setBounds(133, 64, 152, 20);
		panel_1.add(textComentarioDeAlta);
		textComentarioDeAlta.setColumns(10);
		
		JLabel lblRepuestosPlanificados = new JLabel("Repuestos planificados");
		lblRepuestosPlanificados.setBounds(10, 121, 121, 14);
		panel_1.add(lblRepuestosPlanificados);
		
		JLabel lblTrabajosPresupuestados = new JLabel("Trabajos presupuestados");
		lblTrabajosPresupuestados.setBounds(239, 121, 140, 14);
		panel_1.add(lblTrabajosPresupuestados);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 146, 209, 164);
		panel_1.add(scrollPane_1);
		
		DefaultTableModel modelRepuesto = (new DefaultTableModel(null,columnasRepuesto));
		tablaDeRepuesto = new JTable(modelRepuesto);
		scrollPane_1.setViewportView(tablaDeRepuesto);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(239, 146, 212, 164);
		panel_1.add(scrollPane_2);
		
		DefaultTableModel modelTrabajosPresupuestados = (new DefaultTableModel(null,columnasTrabajoPresupuestado));
		tablaDeTrabajoPresupuestado = new JTable(modelTrabajosPresupuestados);
		scrollPane_2.setViewportView(tablaDeTrabajoPresupuestado);
		
		JButton btnFinalizarPresupuesto = new JButton("Finalizar presupuesto");
		btnFinalizarPresupuesto.setBounds(146, 321, 169, 23);
		panel_1.add(btnFinalizarPresupuesto);
		

		JPanel otSinPresupuestar = new JPanel();
		tabbedPane.addTab("OT sin presupuestar", null, otSinPresupuestar, null);
		otSinPresupuestar.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 759, 356);
		otSinPresupuestar.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 11, 739, 298);
		panel_2.add(scrollPane_3);
		
		DefaultTableModel modelOTParaPresupuestar = (new DefaultTableModel(null,columnasOTParaPresupuestar));
		tablaDeOTParaPresupuestar = new JTable(modelOTParaPresupuestar);
		scrollPane_3.setViewportView(tablaDeOTParaPresupuestar);
		
		btnPresupuestar = new JButton("Presupuestar");
		btnPresupuestar.setBounds(597, 320, 152, 23);
		panel_2.add(btnPresupuestar);
		
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
	
	public void setActionRegistrarTurno(ActionListener listener) {
		this.btnPresupuestar.addActionListener(listener);
	}
	
}
