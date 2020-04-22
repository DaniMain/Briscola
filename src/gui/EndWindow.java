package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import partita.GamingController;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EndWindow {

	private JFrame frame;
	private GamingController partita;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EndWindow(GamingController partita) {
		this.partita = partita;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 28, 164, 117);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		implementTextArea(textArea);
	}
	
	private void implementTextArea(JTextArea textArea) {
		textArea.setText(partita.getTavolo().getPuntiMiei().toStringTextArea());
	}
	
}
