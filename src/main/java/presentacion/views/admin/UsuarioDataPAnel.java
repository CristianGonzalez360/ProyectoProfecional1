package presentacion.views.admin;

import javax.swing.JPanel;

import dto.UsuarioDTO;
import presentacion.views.supervisor.ClientePanelView;

import javax.swing.BoxLayout;

public class UsuarioDataPAnel extends JPanel {

	private static final long serialVersionUID = -1126134422008154549L;

	private ClientePanelView usuarioPanelData;
	
	private CuentaUsuarioPanel cuentaUsuarioPanel;
	
	public UsuarioDataPAnel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		usuarioPanelData = new ClientePanelView();
		usuarioPanelData.setTitledBorder("Datos del usuario");
		panel.add(usuarioPanelData);
		
		cuentaUsuarioPanel = new CuentaUsuarioPanel();
		panel.add(cuentaUsuarioPanel);
	}
	
	public UsuarioDTO getData() {
		UsuarioDTO ret = new UsuarioDTO();
		ret.setDatosPersonales(usuarioPanelData.getDataDatosPersonales());
		ret.setCuenta(cuentaUsuarioPanel.getData());
		return ret;
	}
	
	public void setData(UsuarioDTO usuario) {
		usuarioPanelData.setDatosPersonales(usuario.getDatos());
		cuentaUsuarioPanel.setData(usuario.getCuenta());
	}
	
	public void clearData() {
		usuarioPanelData.clearData();
		cuentaUsuarioPanel.clearData();
	}
}
