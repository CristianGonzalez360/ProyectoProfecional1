package presentacion.views;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JEditorPane;

public class AgregarTrabajoFormView extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField tfEsfuerzo;
	private JTextField tfMonto;
	
	public AgregarTrabajoFormView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPanel.add(panelBotones, BorderLayout.SOUTH);
		
		JButton btnGuardar = new JButton("Guardar");
		panelBotones.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		panelBotones.add(btnCancelar);
		
		JPanel panelPrincipal = new JPanel();
		contentPanel.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentral = new JPanel();
		panelPrincipal.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentralInferior = new JPanel();
		FlowLayout fl_panelCentralInferior = (FlowLayout) panelCentralInferior.getLayout();
		fl_panelCentralInferior.setAlignment(FlowLayout.LEFT);
		panelCentral.add(panelCentralInferior, BorderLayout.SOUTH);
		
		JLabel lblMonto = new JLabel("Monto:    ");
		panelCentralInferior.add(lblMonto);
		
		tfMonto = new JTextField();
		panelCentralInferior.add(tfMonto);
		tfMonto.setColumns(10);
		
		JPanel panelDescripción = new JPanel();
		panelCentral.add(panelDescripción, BorderLayout.CENTER);
		panelDescripción.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDescripcin = new JLabel("Descripción");
		panelDescripción.add(lblDescripcin, BorderLayout.NORTH);
		
		JEditorPane editorDescripcion = new JEditorPane();
		
		JScrollPane scroll = new JScrollPane(editorDescripcion);
		panelDescripción.add(scroll, BorderLayout.CENTER);
		
		JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
		
		JLabel lblEsfuerzo = new JLabel("Esfuerzo:");
		panelInferior.add(lblEsfuerzo);
		
		tfEsfuerzo = new JTextField();
		panelInferior.add(tfEsfuerzo);
		tfEsfuerzo.setColumns(10);
	}

}