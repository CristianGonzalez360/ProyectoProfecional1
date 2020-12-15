package presentacion.views.admin;

import javax.swing.JPanel;

import dto.UsuarioDTO;

import javax.swing.BoxLayout;

public class PanelDataUsuarios extends JPanel {

	private static final long serialVersionUID = -1126134422008154549L;

	private DatosPersonalesPanel usuarioPanelData;

	private CuentaUsuarioPanel cuentaUsuarioPanel;

	public PanelDataUsuarios() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		usuarioPanelData = new DatosPersonalesPanel();
		usuarioPanelData.setBorderTitle("Datos del usuario");
		panel.add(usuarioPanelData);

		cuentaUsuarioPanel = new CuentaUsuarioPanel();
		panel.add(cuentaUsuarioPanel);
	}

	public UsuarioDTO getData() {
		UsuarioDTO ret = new UsuarioDTO();
		ret.setDatosPersonales(usuarioPanelData.getData());
		ret.setCuenta(cuentaUsuarioPanel.getData());
		return ret;
	}

	public void setData(UsuarioDTO usuario) {
		usuarioPanelData.setData(usuario.getDatos());
		cuentaUsuarioPanel.setData(usuario.getCuenta());
	}

	public void clearData() {
		usuarioPanelData.clearData();
		cuentaUsuarioPanel.clearData();
	}
}
