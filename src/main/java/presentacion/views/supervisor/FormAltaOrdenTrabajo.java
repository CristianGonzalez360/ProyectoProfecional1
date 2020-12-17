package presentacion.views.supervisor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.temporal.AltaOrdenDeTrabajoDTO;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FormAltaOrdenTrabajo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JEditorPane editorTrabajoSolicitado;
	private JEditorPane editorTrabajoSugerido;
	private ButtonGroup grupo;
	private JButton botonGuardar;
	private JRadioButton rdbtnGarantia;
	private JRadioButton rdbtnTaller;

	private static FormAltaOrdenTrabajo instance;
	private JTextField kilometrajeActual;

	public static FormAltaOrdenTrabajo getInstance() {
		if (instance == null)
			instance = new FormAltaOrdenTrabajo();
		return instance;
	}

	private FormAltaOrdenTrabajo() {
		setTitle("Form alta de orden de trabajo");
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

		rdbtnGarantia = new JRadioButton("Garantia");
		panelRadios.add(rdbtnGarantia);

		rdbtnTaller = new JRadioButton("Taller");
		panelRadios.add(rdbtnTaller);

		grupo = new ButtonGroup();
		grupo.add(rdbtnTaller);
		grupo.add(rdbtnGarantia);

		JPanel panelBotones = new JPanel();
		FlowLayout fl_panelBotones = new FlowLayout(FlowLayout.CENTER);
		fl_panelBotones.setHgap(20);
		panelBotones.setLayout(fl_panelBotones);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Kilometraje actual");
		panelBotones.add(lblNewLabel);
		
		kilometrajeActual = new JTextField();
		panelBotones.add(kilometrajeActual);
		kilometrajeActual.setColumns(10);

		botonGuardar = new JButton("Guardar");
		botonGuardar.setActionCommand("OK");
		panelBotones.add(botonGuardar);
		getRootPane().setDefaultButton(botonGuardar);

		this.rdbtnGarantia.setSelected(true);
		this.rdbtnTaller.setSelected(false);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				clearData();
			}
		});
		
		setModal(true);
	}

	public void clearData() {
		this.editorTrabajoSolicitado.setText("");
		this.editorTrabajoSugerido.setText("");
		this.kilometrajeActual.setText("");
		habilitarGarantia();
	}

	public AltaOrdenDeTrabajoDTO getData() {
		AltaOrdenDeTrabajoDTO dto = new AltaOrdenDeTrabajoDTO();
		dto.setKilometrajeActual(this.kilometrajeActual.getText());
		dto.setTrabajoSugerido(this.editorTrabajoSugerido.getText());
		dto.setTrabajoSolicitado(this.editorTrabajoSolicitado.getText());
		if (this.rdbtnTaller.isSelected()) {
			dto.setTipoDeTrabajo("Taller");
		} else {
			dto.setTipoDeTrabajo("Garantia");
		}
		return dto;
	}

	public void display() {
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}

	public void setActionGuardar(ActionListener listener) {
		this.botonGuardar.addActionListener(listener);
	}

	public void habilitarGarantia() {
		this.rdbtnGarantia.setEnabled(true);
	}

	public void deshabilitarGarantia() {
		this.rdbtnGarantia.setEnabled(false);
		this.rdbtnTaller.setSelected(true);
	}

	public void setKilometraje(String string) {
		this.kilometrajeActual.setText(string);
	}
}
