package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OTPresupuestadasView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textDni;
	private JTextField textPatente;
	private JTextField textTotalPresupuestado;
	private JTextField textTrabajoSugerido;
	private JTextField textTrabajoSolicitado;
	private JTextField textTrabajosPorPresupuestar;
	private JTextField textTrabajosEnCurso;
	private JTextField textTrabajosTerminados;
	private  String[] columnasOrdenTrabajo = {"Nro OT","Fecha alta","Usuario de alta","Dni cliente","patente"};//aca se modifican las columnas de la tabla orden de trabajo
	private  String[] columnasPresupuesto = {"Fecha alta"};//aca se modifican las columnas de la tabla presupuesto
	private JTable tablaOrdenesDeTrabajo;
	private JTable tablaPresupuesto;

	public OTPresupuestadasView() {
		setBounds(100, 100, 750, 550);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 715, 47);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblDni = new JLabel("Dni");
		lblDni.setBounds(10, 11, 46, 14);
		panel.add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(66, 8, 86, 20);
		panel.add(textDni);
		textDni.setColumns(10);
		
		JLabel lblPatente = new JLabel("Patente vehiculo");
		lblPatente.setBounds(181, 11, 103, 14);
		panel.add(lblPatente);
		
		textPatente = new JTextField();
		textPatente.setBounds(294, 8, 86, 20);
		panel.add(textPatente);
		textPatente.setColumns(10);
		
		JCheckBox chckbxTodos = new JCheckBox("Todos");
		chckbxTodos.setBounds(409, 7, 97, 23);
		panel.add(chckbxTodos);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(515, 7, 89, 23);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 69, 307, 398);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnRegistrarEntrega = new JButton("Registrar entrega de vehiculo");
		btnRegistrarEntrega.setBounds(62, 364, 192, 23);
		panel_1.add(btnRegistrarEntrega);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 287, 241);
		panel_1.add(scrollPane);
		
		DefaultTableModel modelOrdenesDeTrabajo = (new DefaultTableModel(null,columnasOrdenTrabajo));
		tablaOrdenesDeTrabajo = new JTable(modelOrdenesDeTrabajo);
		scrollPane.setViewportView(tablaOrdenesDeTrabajo);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 263, 287, 90);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trabajos por presupuestar");
		lblNewLabel.setBounds(10, 12, 159, 14);
		panel_4.add(lblNewLabel);
		
		JLabel lblTrabajosEnCurso = new JLabel("Trabajos en curso");
		lblTrabajosEnCurso.setBounds(10, 38, 159, 14);
		panel_4.add(lblTrabajosEnCurso);
		
		JLabel lblTrabajosTerminados = new JLabel("Trabajos terminados");
		lblTrabajosTerminados.setBounds(10, 64, 159, 14);
		panel_4.add(lblTrabajosTerminados);
		
		textTrabajosPorPresupuestar = new JTextField();
		textTrabajosPorPresupuestar.setBounds(157, 9, 120, 20);
		panel_4.add(textTrabajosPorPresupuestar);
		textTrabajosPorPresupuestar.setColumns(10);
		
		textTrabajosEnCurso = new JTextField();
		textTrabajosEnCurso.setBounds(157, 35, 120, 20);
		panel_4.add(textTrabajosEnCurso);
		textTrabajosEnCurso.setColumns(10);
		
		textTrabajosTerminados = new JTextField();
		textTrabajosTerminados.setBounds(157, 61, 120, 20);
		panel_4.add(textTrabajosTerminados);
		textTrabajosTerminados.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(327, 69, 398, 83);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblTrabajoSugerido = new JLabel("Trabajo sugerido");
		lblTrabajoSugerido.setBounds(38, 11, 100, 14);
		panel_2.add(lblTrabajoSugerido);
		
		JLabel lblTrabajoSolicitado = new JLabel("Trabajo solicitado");
		lblTrabajoSolicitado.setBounds(38, 47, 100, 14);
		panel_2.add(lblTrabajoSolicitado);
		
		textTrabajoSugerido = new JTextField();
		textTrabajoSugerido.setBounds(163, 8, 172, 20);
		panel_2.add(textTrabajoSugerido);
		textTrabajoSugerido.setColumns(10);
		
		textTrabajoSolicitado = new JTextField();
		textTrabajoSolicitado.setBounds(163, 44, 172, 20);
		panel_2.add(textTrabajoSolicitado);
		textTrabajoSolicitado.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(327, 163, 398, 304);
		contentPanel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblPresupuestos = new JLabel("Presupuestos");
		lblPresupuestos.setBounds(10, 11, 92, 14);
		panel_3.add(lblPresupuestos);
		
		JButton btnEnviarFactura = new JButton("Enviar factura");
		btnEnviarFactura.setBounds(10, 270, 121, 23);
		panel_3.add(btnEnviarFactura);
		
		JButton btnRegistrarPago = new JButton("Registrar pago");
		btnRegistrarPago.setBounds(141, 270, 121, 23);
		panel_3.add(btnRegistrarPago);
		
		JButton btnConsultarDetalle = new JButton("Consultar detalle");
		btnConsultarDetalle.setBounds(272, 270, 116, 23);
		panel_3.add(btnConsultarDetalle);
		
		JLabel lblTotalPresupuestado = new JLabel("Total presupuestado");
		lblTotalPresupuestado.setBounds(10, 245, 121, 14);
		panel_3.add(lblTotalPresupuestado);
		
		textTotalPresupuestado = new JTextField();
		textTotalPresupuestado.setBounds(187, 239, 123, 20);
		panel_3.add(textTotalPresupuestado);
		textTotalPresupuestado.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 36, 378, 189);
		panel_3.add(scrollPane_1);
		
		
		DefaultTableModel modelPresupuestos = (new DefaultTableModel(null,columnasPresupuesto));
		tablaPresupuesto = new JTable(modelPresupuestos);
		scrollPane_1.setViewportView(tablaPresupuesto);
		
		
//		{//duda si va o no
//			JPanel buttonPane = new JPanel();
//			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				JButton cancelButton = new JButton("Cancel");
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
//		}
	}
}
