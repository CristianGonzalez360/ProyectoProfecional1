package presentacion.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class AltaOrdenTtrabajoFormView extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public AltaOrdenTtrabajoFormView() {
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
		
		JEditorPane editorTrabajoSolicitado = new JEditorPane();
		
		JScrollPane scrollTS = new JScrollPane(editorTrabajoSolicitado);
		panelSuperior.add(scrollTS, BorderLayout.CENTER);
		
		JPanel panelInferior = new JPanel();
		splitPane.setRightComponent(panelInferior);
		panelInferior.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTrabajoSugerido = new JLabel("Trabajo Sugerido");
		lblTrabajoSugerido.setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(lblTrabajoSugerido, BorderLayout.NORTH);
		
		JEditorPane editorTrabajoSugerido = new JEditorPane();
		
		JScrollPane scrollTraSug = new JScrollPane(editorTrabajoSugerido);
		panelInferior.add(scrollTraSug, BorderLayout.CENTER);
		
		JPanel panelRadios = new JPanel();
		contentPanel.add(panelRadios, BorderLayout.SOUTH);
		
		JRadioButton rdbtnService = new JRadioButton("Service");
		panelRadios.add(rdbtnService);
		
		JRadioButton rdbtnTaller = new JRadioButton("Taller");
		panelRadios.add(rdbtnTaller);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rdbtnTaller);
		grupo.add(rdbtnService);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		JButton botonGuardar = new JButton("Guardar");
		botonGuardar.setActionCommand("OK");
		panelBotones.add(botonGuardar);
		getRootPane().setDefaultButton(botonGuardar);
	
	
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setActionCommand("Cancel");
		panelBotones.add(botonCancelar);
		
	}

}
