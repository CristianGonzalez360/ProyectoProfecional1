package presentacion.views.tecnico;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.RepuestoPlanificadoDTO;
import dto.TrabajoPresupuestadoDTO;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class AltaPresupuestoFormView extends JDialog {

	private static final long serialVersionUID = 2771968410097489023L;

	private static final String[] nombreColumnasRepuestos = { "Codigo", "Descripcion", "Marca", "Fabricante",
			"Cantidad" };
	private static final String[] nombreColumnasTrabajos = { "Codigo", "Descripcion" };

	private JPanel panelIzquierdo;
	private JScrollPane scrollPaneRepuestos;
	private DefaultTableModel modelTablaRepuestos;
	private JTable tablaRepuestos;
	private JButton btnPlanificarRepuestos;

	private JPanel panelDerecho;
	private JScrollPane scrollPanelTrabajos;
	private DefaultTableModel modelTablaTrabajos;
	private JTable tablaTrabajos;
	private JButton btnPlanificarTrabajos;
	private JPanel panel;
	private JPanel panel_1;
	private JTabbedPane tabbedPane;

	private static AltaPresupuestoFormView instance;
	private JButton btnGuardar;

	@SuppressWarnings("serial")
	private AltaPresupuestoFormView() {
		setBounds(100, 100, 700, 350);
		setTitle("Formulario de Alta de Presupuesto");
		getContentPane().setLayout(new BorderLayout());

		modelTablaRepuestos = new DefaultTableModel(null, nombreColumnasRepuestos) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		modelTablaTrabajos = new DefaultTableModel(null, nombreColumnasTrabajos) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setActionCommand("OK");
		buttonPane.add(btnGuardar);
		getRootPane().setDefaultButton(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("Cancel");
		buttonPane.add(btnCancelar);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		panelDerecho = new JPanel();
		tabbedPane.addTab("Trabajos", null, panelDerecho, null);
		panelDerecho.setLayout(new BorderLayout(0, 0));
		scrollPanelTrabajos = new JScrollPane();
		panelDerecho.add(scrollPanelTrabajos);

		tablaTrabajos = new JTable(modelTablaTrabajos);

		scrollPanelTrabajos.setViewportView(tablaTrabajos);

		panel_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelDerecho.add(panel_1, BorderLayout.SOUTH);

		btnPlanificarTrabajos = new JButton("Planificar");
		panel_1.add(btnPlanificarTrabajos);

		panelIzquierdo = new JPanel();
		tabbedPane.addTab("Repuestos", null, panelIzquierdo, null);
		panelIzquierdo.setLayout(new BorderLayout(0, 0));
		scrollPaneRepuestos = new JScrollPane();
		panelIzquierdo.add(scrollPaneRepuestos, BorderLayout.CENTER);

		tablaRepuestos = new JTable(modelTablaRepuestos);

		scrollPaneRepuestos.setViewportView(tablaRepuestos);

		panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelIzquierdo.add(panel, BorderLayout.SOUTH);

		btnPlanificarRepuestos = new JButton("Planificar");
		panel.add(btnPlanificarRepuestos);
		btnPlanificarRepuestos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		setVisible(false);
	}

	public static AltaPresupuestoFormView getInstance() {
		if (instance == null)
			instance = new AltaPresupuestoFormView();
		return instance;
	}

	public void setActionOnPlanificarRepuestos(ActionListener listener) {
		this.btnPlanificarRepuestos.addActionListener(listener);
	}

	public void setActionOnPlanificarTrabajos(ActionListener listener) {
		this.btnPlanificarTrabajos.addActionListener(listener);
	}

	public void setActionOnGuardar(ActionListener listener) {
		this.btnGuardar.addActionListener(listener);
	}

	public void setDataRepuestos(List<RepuestoPlanificadoDTO> repuestos) {
		// TODO llenar tabla repuestos
	}

	public void setDataTrabajos(List<TrabajoPresupuestadoDTO> trabajos) {
		for (TrabajoPresupuestadoDTO t : trabajos) {
			// TODO llenar tabla trabajos
		}
	}

	public void clearData() {
		// TODO Auto-generated method stub

	}

	public void display() {
		setVisible(true);
	}

}
