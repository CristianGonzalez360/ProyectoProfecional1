package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

public class AltaPresupuestoFormView extends JDialog {

	private static final long serialVersionUID = 2771968410097489023L;
	private final JPanel contentPanel = new JPanel();

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

	@SuppressWarnings("serial")
	public AltaPresupuestoFormView() {
		setBounds(100, 100, 700, 350);
		setTitle("Formulario de Alta de Presupuesto");
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);

		contentPanel.setLayout(null);

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			panelIzquierdo = new JPanel();
			panelIzquierdo
					.setBorder(new TitledBorder(null, "Repuestos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelIzquierdo.setBounds(10, 11, 330, 266);
			panelIzquierdo.setLayout(null);
			contentPanel.add(panelIzquierdo);
			{
				scrollPaneRepuestos = new JScrollPane();
				scrollPaneRepuestos.setBounds(10, 22, 310, 199);
				panelIzquierdo.add(scrollPaneRepuestos);

				modelTablaRepuestos = new DefaultTableModel(null, nombreColumnasRepuestos) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				tablaRepuestos = new JTable(modelTablaRepuestos);

				scrollPaneRepuestos.setViewportView(tablaRepuestos);
			}
			{
				btnPlanificarRepuestos = new JButton("Planificar");
				btnPlanificarRepuestos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				btnPlanificarRepuestos.setBounds(210, 232, 110, 23);
				panelIzquierdo.add(btnPlanificarRepuestos);
			}
		}
		{
			panelDerecho = new JPanel();
			panelDerecho
					.setBorder(new TitledBorder(null, "Trabajos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelDerecho.setBounds(350, 11, 334, 266);
			panelDerecho.setLayout(null);
			contentPanel.add(panelDerecho);
			{
				scrollPanelTrabajos = new JScrollPane();
				scrollPanelTrabajos.setBounds(10, 22, 314, 198);
				panelDerecho.add(scrollPanelTrabajos);

				modelTablaTrabajos = new DefaultTableModel(null, nombreColumnasTrabajos) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				tablaTrabajos = new JTable(modelTablaTrabajos);

				scrollPanelTrabajos.setViewportView(tablaTrabajos);
			}
			{
				btnPlanificarTrabajos = new JButton("Planificar");
				btnPlanificarTrabajos.setBounds(207, 232, 110, 23);
				panelDerecho.add(btnPlanificarTrabajos);
			}
		}
		{

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

}
