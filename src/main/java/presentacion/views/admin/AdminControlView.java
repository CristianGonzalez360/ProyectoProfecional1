package presentacion.views.admin;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.event.ListSelectionListener;

import dto.MonedaDTO;
import dto.SucursalDTO;
import presentacion.PanelSucursales;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class AdminControlView extends JInternalFrame {

	private static final long serialVersionUID = -8187193486426314619L;

	private static AdminControlView instance;

	private PanelSucursales panelSucursales;

	private PanelGestionUsuariosView panelUsuarios;

	private PanelConfiguracionGeneral panelConfiguracion;

	public static AdminControlView getInstance() {
		if (instance == null)
			instance = new AdminControlView();
		return instance;
	}

	private AdminControlView() {
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Administrador Control View");
		setBounds(100, 100, 633, 424);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		panelSucursales = new PanelSucursales();
		tabbedPane.addTab("Consulta de Sucursales", null, panelSucursales, null);

		panelUsuarios = new PanelGestionUsuariosView();
		tabbedPane.addTab("Gestión de Usuarios", null, panelUsuarios, null);

		panelConfiguracion = new PanelConfiguracionGeneral();
		tabbedPane.addTab("Configuración General", null, panelConfiguracion, null);

		JPanel panel = new JPanel();
		panelUsuarios.add(panel, BorderLayout.SOUTH);
	}

	public void display() {
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		setVisible(true);
	}

	public String getDataNombrePais() {
		return panelSucursales.getDataNombrePais();
	}

	public void setActionBuscarSucursal(ActionListener listener) {
		panelSucursales.setActionBuscarSucursal(listener);
	}

	public void addPaisesDeBusqueda(String[] strings) {
		panelSucursales.addPaisesDeBusqueda(strings);
	}

	public void clearData() {
		panelSucursales.clearData();
		panelSucursales.clearDataMoneda();
	}

	public void setData(List<SucursalDTO> sucursales) {
		panelSucursales.setData(sucursales);
	}

	public void setActionSeleccionSucursal(ListSelectionListener listener) {
		panelSucursales.setActionSeleccionSucursal(listener);
	}

	public SucursalDTO getData() {
		return panelSucursales.getData();
	}

	public void setData(MonedaDTO moneda) {
		panelSucursales.setData(moneda);
	}

	public void clearDataMoneda() {
		panelSucursales.clearDataMoneda();
	}

	public void setActionEscogerTerminal(ActionListener listener) {
		panelSucursales.setActionEscogerTerminal(listener);
	}

	public void close() {
		setVisible(false);
	}

	public PanelGestionUsuariosView getUsuariosView() {
		return this.panelUsuarios;
	}

	public PanelConfiguracionGeneral getPanelConfiguracion() {
		return this.panelConfiguracion;
	}

	public void setActionRegistrarSucursal(ActionListener listener) {
		panelSucursales.setActionRegistrarSucursal(listener);
	}

	public void setActionRegistrarMoneda(ActionListener listener) {
		panelSucursales.setActionRegistrarMoneda(listener);
	}

	public MonedaDTO getDataMoneda() {
		return panelSucursales.getdataMoneda();
	}
}
