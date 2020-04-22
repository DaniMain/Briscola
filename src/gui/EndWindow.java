package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import partita.GamingController;
import javax.swing.JScrollPane;

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

		JLabel resultLabel = new JLabel(partita.getRisultatoPartita());
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setBounds(10, 11, 414, 46);
		frame.getContentPane().add(resultLabel);

		JTextArea descrizioneTextArea = new JTextArea(partita.getDescrizioneFinale());
		descrizioneTextArea.setEditable(false);
		descrizioneTextArea.setBounds(10, 68, 414, 80);
		frame.getContentPane().add(descrizioneTextArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 159, 414, 91);
		frame.getContentPane().add(scrollPane);
		
				textArea = new JTextArea();
				scrollPane.setViewportView(textArea);
				textArea.setEditable(false);
				implementTextArea(textArea);
				
				JLabel carteCollezionateLabel = new JLabel("Hai collezionato le seguenti carte:");
				scrollPane.setColumnHeaderView(carteCollezionateLabel);
	}

	private void implementTextArea(JTextArea textArea) {
		textArea.setText(partita.getTavolo().getPuntiMiei().toStringTextArea());
	}
}
