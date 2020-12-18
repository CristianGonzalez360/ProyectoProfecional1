package presentacion.views.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import dto.UsuarioDTO;
import presentacion.views.vendedor.TablePanel;

public class PanelGestionUsuariosView extends JPanel {

	private static final long serialVersionUID = 3976756890877745568L;

	private TablePanel<UsuarioDTO> tableUsuarios;

	private DatosPersonalesPanel datosPanel;

	private CuentaUsuarioPanel cuentaPanel;

	private JButton btnRegistrarUsuario;

	public PanelGestionUsuariosView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panelSouth = new JPanel();
		panel_1.add(panelSouth, BorderLayout.SOUTH);

		btnRegistrarUsuario = new JButton("Registrar usuario");
		panelSouth.add(btnRegistrarUsuario);

		tableUsuarios = new TablePanel<UsuarioDTO>(new String[] { "LEGAJO", "NOMBRE COMPLETO", "DNI" }) {

			private static final long serialVersionUID = -5492436929768181798L;

			@Override
			public void setData(List<UsuarioDTO> data) {
				for (UsuarioDTO dto : data) {
					Object[] row = { dto.getId().toString(), dto.getDatos().getNombreCompleto() + " " + dto.getDatos().getApellido() ,
							dto.getDatos().getDni().toString() };
					model.addRow(row);
				}
			}

			@Override
			public UsuarioDTO getData() {
				UsuarioDTO ret = new UsuarioDTO();
				if (table.getSelectedRowCount() == 1) {
					int row = table.getSelectedRow();
					ret.setId(Integer.parseInt(model.getValueAt(row, 0).toString()));
				}
				return ret;
			}
		};
		panel_1.add(tableUsuarios, BorderLayout.CENTER);
		tableUsuarios.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Listado de usuarios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panel = new JPanel();
		cuentaPanel = new CuentaUsuarioPanel();
		datosPanel = new DatosPersonalesPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(datosPanel);
		panel.add(cuentaPanel);
		panel_1.add(panel, BorderLayout.WEST);
	}

	public void setActionSelectUsuario(ListSelectionListener listener) {
		tableUsuarios.setActionSelect(listener);
	}

	public Integer getData() {
		return tableUsuarios.getData().getId();
	}

	public void setData(List<UsuarioDTO> target) {
		this.tableUsuarios.setData(target);
	}

	public void setData(UsuarioDTO target) {
		datosPanel.setData(target.getDatos());
		cuentaPanel.setData(target.getCuenta());
	}

	public void clearUsuarioData() {
		datosPanel.clearData();
		cuentaPanel.clearData();
	}

	public void setActionRegistrarUsuario(ActionListener listener) {
		this.btnRegistrarUsuario.addActionListener(listener);
	}

	public void clearData() {
		this.datosPanel.clearData();
		this.tableUsuarios.clearData();
	}
}
