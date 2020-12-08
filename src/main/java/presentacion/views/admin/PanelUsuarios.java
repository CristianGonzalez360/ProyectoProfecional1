package presentacion.views.admin;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;

import dto.UsuarioDTO;
import presentacion.views.vendedor.TablePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

public class PanelUsuarios extends JPanel {

	private static final long serialVersionUID = 3976756890877745568L;
	
	private TablePanel<UsuarioDTO> tableUsuarios;
	
	private UsuarioDataPAnel usuarioDataPanel;
		
	public PanelUsuarios() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSouth = new JPanel();
		panel_1.add(panelSouth, BorderLayout.SOUTH);
		
		usuarioDataPanel = new UsuarioDataPAnel();
		panel_1.add(usuarioDataPanel, BorderLayout.WEST);
		
		tableUsuarios = new TablePanel<UsuarioDTO>(new String [] {"Legajo", "Nombre", "DNI", "Pais"}) {

			private static final long serialVersionUID = -5492436929768181798L;

			@Override
			public void setData(List<UsuarioDTO> data) {
				for(UsuarioDTO dto : data) {
					Object [] row = {dto.getId().toString(), dto.getDatos().getNombreCompleto(), dto.getDatos().getDni().toString(), dto.getSucursal().getPais() };
					model.addRow(row);
				}
			}

			@Override
			public UsuarioDTO getData() {
				UsuarioDTO ret = new UsuarioDTO();
				if(table.getSelectedRowCount() == 1) {
					int row = table.getSelectedRow();
					ret.setId(Integer.parseInt(model.getValueAt(row, 0).toString()));
				}
				return ret;
			}			
		};
		panel_1.add(tableUsuarios, BorderLayout.CENTER);
		tableUsuarios.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Listado de usuarios",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	}
	
	public void setActionSelectUsuario(ListSelectionListener listener) {
		tableUsuarios.setActionSelect(listener);
	}
}
