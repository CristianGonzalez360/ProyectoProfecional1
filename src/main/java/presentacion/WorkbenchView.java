package presentacion;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class WorkbenchView {

	private JFrame frame;

	static WorkbenchView vista;

	private Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/iconox512.png"));

	private JDesktopPane desktopPane;

	private JMenuItem mntmItemLogin;

	private JMenuItem mntmNewMenuItem;

	private JTextField textSession;

	private JMenuItem mntmItemSalir;

	public static WorkbenchView getInstance() {
		if (vista == null)
			vista = new WorkbenchView();
		return vista;
	}

	WorkbenchView() {
		super();
		frame = new JFrame();
		// frame.setResizable(false);
		frame.setBounds(100, 100, 1132, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Concesionario");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setIconImage(icon);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		panel.add(desktopPane, BorderLayout.CENTER);

		JPanel southPanel = new JPanel();
		southPanel.setEnabled(false);
		southPanel.setForeground(Color.BLACK);
		southPanel.setBorder(new EmptyBorder(1, 0, 0, 0));
		FlowLayout flowLayout = (FlowLayout) southPanel.getLayout();
		flowLayout.setVgap(2);
		flowLayout.setAlignment(FlowLayout.TRAILING);
		southPanel.setBackground(UIManager.getColor("MenuBar.background"));
		panel.add(southPanel, BorderLayout.SOUTH);

		textSession = new JTextField();
		textSession.setDisabledTextColor(Color.DARK_GRAY);
		textSession.setHorizontalAlignment(SwingConstants.CENTER);
		textSession.setForeground(Color.BLACK);
		textSession.setBackground(SystemColor.activeCaptionBorder);
		textSession.setEnabled(false);
		textSession.setEditable(false);
		southPanel.add(textSession);
		textSession.setColumns(30);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Usuario");
		menuBar.add(mnNewMenu);

		mntmItemLogin = new JMenuItem("Login");
		mnNewMenu.add(mntmItemLogin);

		mntmNewMenuItem = new JMenuItem("Logout");
		mnNewMenu.add(mntmNewMenuItem);

		mntmItemSalir = new JMenuItem("Salir");
		mnNewMenu.add(mntmItemSalir);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void open() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres salir?", "Confirmación",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
		this.frame.setResizable(true);
	}

	public void setData(String sessionInfo) {
		this.textSession.setText(sessionInfo);
	}

	public void addFrames(JInternalFrame frame) {
		this.desktopPane.add(frame);
	}

	public void setActionOnLogin(ActionListener listener) {
		this.mntmItemLogin.addActionListener(listener);
	}

	public void setActionOnSalir(ActionListener listener) {
		this.mntmItemSalir.addActionListener(listener);
	}

	public void disableLoginButton() {
		this.mntmItemLogin.setEnabled(false);
	}

	public void setActionOnLogout(ActionListener actionListener) {
		this.mntmNewMenuItem.addActionListener(actionListener);
	}

	public void enableLoginButton() {
		this.mntmItemLogin.setEnabled(true);
	}

	public void clearData() {
		this.textSession.setText("");
	}
}
