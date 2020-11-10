package presentacion.views.supervisor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.AltaOrdenDeTrabajoDTO;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class AltaOrdenTrabajoFormView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JEditorPane editorTrabajoSolicitado;
	private JEditorPane editorTrabajoSugerido;
	private ButtonGroup grupo;
	private JButton botonGuardar;
	private JButton botonCancelar;
	private JRadioButton rdbtnService;
	private JRadioButton rdbtnTaller;

	private static AltaOrdenTrabajoFormView instance;

	public static AltaOrdenTrabajoFormView getInstance() {
		if (instance == null)
			instance = new AltaOrdenTrabajoFormView();
		return instance;
	}

	private AltaOrdenTrabajoFormView() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPanel.add(splitPane, BorderLayout.CENTER);

		JPanel panelSuperior = new JPanel();
		splitPane.setLeftComponent(panelSuperior);
		panelSuperior.setLayout(new BorderLayout(0, 0));

		JLabel lblTrabajoSolicitado = new JLabel("Trabajo Solicitado");
		lblTrabajoSolicitado.setHorizontalAlignment(SwingConstants.CENTER);
		panelSuperior.add(lblTrabajoSolicitado, BorderLayout.NORTH);

		editorTrabajoSolicitado = new JEditorPane();

		JScrollPane scrollTS = new JScrollPane(editorTrabajoSolicitado);
		panelSuperior.add(scrollTS, BorderLayout.CENTER);

		JPanel panelInferior = new JPanel();
		splitPane.setRightComponent(panelInferior);
		panelInferior.setLayout(new BorderLayout(0, 0));

		JLabel lblTrabajoSugerido = new JLabel("Trabajo Sugerido");
		lblTrabajoSugerido.setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(lblTrabajoSugerido, BorderLayout.NORTH);

		editorTrabajoSugerido = new JEditorPane();

		JScrollPane scrollTraSug = new JScrollPane(editorTrabajoSugerido);
		panelInferior.add(scrollTraSug, BorderLayout.CENTER);

		JPanel panelRadios = new JPanel();
		contentPanel.add(panelRadios, BorderLayout.SOUTH);

		rdbtnService = new JRadioButton("Service");
		panelRadios.add(rdbtnService);

		rdbtnTaller = new JRadioButton("Taller");
		panelRadios.add(rdbtnTaller);

		grupo = new ButtonGroup();
		grupo.add(rdbtnTaller);
		grupo.add(rdbtnService);

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(panelBotones, BorderLayout.SOUTH);

		botonGuardar = new JButton("Guardar");
		botonGuardar.setActionCommand("OK");
		panelBotones.add(botonGuardar);
		getRootPane().setDefaultButton(botonGuardar);

		botonCancelar = new JButton("Cancelar");
		botonCancelar.setActionCommand("Cancel");
		panelBotones.add(botonCancelar);

		this.rdbtnService.setSelected(true);
		this.rdbtnTaller.setSelected(false);
		setModal(true);
	}

	public void clearData() {
		this.editorTrabajoSolicitado.setText("");
		this.editorTrabajoSugerido.setText("");
	}

	public AltaOrdenDeTrabajoDTO getData() {
		AltaOrdenDeTrabajoDTO dto = new AltaOrdenDeTrabajoDTO();
		dto.setTrabajoSugerido(this.editorTrabajoSugerido.getText());
		dto.setTrabajoSolicitado(this.editorTrabajoSolicitado.getText());
		if (this.rdbtnTaller.isSelected()) {
			dto.setTipoDeTrabajo("Taller");
		} else {
			dto.setTipoDeTrabajo("Service");
		}
		return dto;
	}

	public void display() {
		setVisible(true);
	}

	public void close() {
		System.out.println("Set visible false");
		setVisible(false);
	}

	public void setActionGuardar(ActionListener listener) {
		this.botonGuardar.addActionListener(listener);
	}

	public void setActionCancelar(ActionListener listener) {
		this.botonCancelar.addActionListener(listener);
	}
}
