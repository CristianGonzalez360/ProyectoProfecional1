package presentacion.views.admin;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dto.CuentaDTO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class CuentaUsuarioPanel extends JPanel {

	private static final long serialVersionUID = -7805947562511945929L;

	private JTextField textNombreUsuario;

	private JTextField textPassword;

	private JTextField textRole;

	public CuentaUsuarioPanel() {
		setBorder(new TitledBorder(null, "Datos de la Cuenta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel = new JLabel("Nombre de Usuario");
		add(lblNewLabel, "2, 2, left, default");

		textNombreUsuario = new JTextField();
		add(textNombreUsuario, "4, 2, fill, default");
		textNombreUsuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		add(lblNewLabel_1, "2, 4, left, default");

		textPassword = new JTextField();
		add(textPassword, "4, 4, fill, default");
		textPassword.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Rol");
		add(lblNewLabel_2, "2, 6, left, default");

		textRole = new JTextField();
		add(textRole, "4, 6, fill, default");
		textRole.setColumns(10);
		clearData();
	}

	public void setData(CuentaDTO cuenta) {
		this.textNombreUsuario.setText(cuenta.getNombreUsuario());
		this.textPassword.setText(cuenta.getPassword());
		this.textRole.setText(cuenta.getRole());
	}

	public CuentaDTO getData() {
		CuentaDTO ret = new CuentaDTO();
		ret.setNombreUsuario(this.textNombreUsuario.getText());
		ret.setRole(this.textRole.getText());
		ret.setPassword(this.textPassword.getText());
		return ret;
	}

	public void clearData() {
		this.textNombreUsuario.setText("");
		this.textPassword.setText("");
		this.textRole.setText("");
	}
}
