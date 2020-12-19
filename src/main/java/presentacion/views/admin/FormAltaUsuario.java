package presentacion.views.admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.UsuarioDTO;

import javax.swing.BoxLayout;

public class FormAltaUsuario extends JDialog {

	private static final long serialVersionUID = -5572151219348301926L;

	private final JPanel contentPanel = new JPanel();

	private PanelDataUsuarios data;

	private JButton okButton;

	private JButton cancelButton;

	private static FormAltaUsuario instance;

	public static FormAltaUsuario getInstance() {
		if (instance == null)
			instance = new FormAltaUsuario();
		return instance;
	}

	private FormAltaUsuario() {
		setTitle("Nuevo Usuario");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 379, 602);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			data = new PanelDataUsuarios();
			contentPanel.add(data);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						clearData();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		setModal(true);
		setResizable(false);
	}

	public UsuarioDTO getData() {
		return this.data.getData();
	}

	public void clearData() {
		this.data.clearData();
	}

	public void setActionOk(ActionListener listener) {
		this.okButton.addActionListener(listener);
	}

	public void setActionCancel(ActionListener listener) {
		this.cancelButton.addActionListener(listener);
	}

	public void open() {
		clearData();
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}
}
