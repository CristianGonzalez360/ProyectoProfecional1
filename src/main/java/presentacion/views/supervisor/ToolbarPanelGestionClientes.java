package presentacion.views.supervisor;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class ToolbarPanelGestionClientes extends JToolBar {
	
	private static final long serialVersionUID = -6763717003523971959L;

	private JButton btnRegistrarNuevoCliente;
	
	private JButton btnEditarCliente;
	
	private JButton btnRegistrarNuevoVehiculo;
	
	private JButton btnRegistrarOT;

	public ToolbarPanelGestionClientes() {
	
		setFloatable(false);

		btnRegistrarNuevoCliente = new JButton("Registrar nuevo cliente");
		add(btnRegistrarNuevoCliente);

		btnEditarCliente = new JButton("Editar cliente");
		add(btnEditarCliente);

		btnRegistrarNuevoVehiculo = new JButton("Registrar nuevo vehiculo");
		add(btnRegistrarNuevoVehiculo);
		
		btnRegistrarOT = new JButton("Registrar orden de trabajo");
		add(btnRegistrarOT);
	}
	
	public void setActionRegistrarNuevoCliente(ActionListener listener) {
		this.btnRegistrarNuevoCliente.addActionListener(listener);
	}
	
	public void setActionEditarCliente(ActionListener listener) {
		this.btnEditarCliente.addActionListener(listener);
	}
	
	public void setActionRegistrarNuevoVehiculo(ActionListener listener) {
		this.btnRegistrarNuevoVehiculo.addActionListener(listener);
	}
	
	public void setActionRegistrarOrdenDeTrabajo(ActionListener listener) {
		this.btnRegistrarOT.addActionListener(listener);
	}

	public void lockButtonRegistrarOrdenDeTrabajo() {
		this.btnRegistrarOT.setEnabled(false);		
	}
	
	public void unlockButtonRegistrarOrdenDeTrabajo() {
		this.btnRegistrarOT.setEnabled(true);	
	}
}
