package presentacion.views;

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

public class WorkbenchView {

	private JFrame frame;

	static WorkbenchView vista;

	private Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icons/agenda.png"));

	private JDesktopPane desktopPane;

	private JMenuItem mntmSeedDb;

	private JMenuItem mntmItemLogin;

	private JMenuItem mntmNewMenuItem;
	
	private JTextField textSession;

	public static WorkbenchView getInstance() {
		if (vista == null)
			vista = new WorkbenchView();
		return vista;
	}

	WorkbenchView() {
		super();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1132, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Agenda");
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setIconImage(icon);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		panel.add(desktopPane, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) southPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		southPanel.setBackground(Color.BLACK);
		panel.add(southPanel, BorderLayout.SOUTH);
		
		textSession = new JTextField();
		textSession.setEnabled(false);
		textSession.setEditable(false);
		southPanel.add(textSession);
		textSession.setColumns(10);

		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Opciones");
		menuBar.add(mnNewMenu);
		
		mntmItemLogin = new JMenuItem("Login");
		mnNewMenu.add(mntmItemLogin);
		
		mntmNewMenuItem = new JMenuItem("Logout");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmItemSalir = new JMenuItem("Salir");
		mnNewMenu.add(mntmItemSalir);

		mntmSeedDb = new JMenuItem("SeedDB");
		mnNewMenu.add(mntmSeedDb);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public JMenuItem getMntmSeedDb() {
		return this.mntmSeedDb;
	}

	public void open() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres salir de la Agenda?",
						"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
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

	public void disableLoginButton() {
		this.mntmItemLogin.setEnabled(false);
	}
}
