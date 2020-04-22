package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import partita.GamingController;
import javax.swing.JScrollPane;
import java.awt.Font;

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
		frame.setTitle("Fine della partita");
		frame.setBounds(400, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel resultLabel = new JLabel(partita.getRisultatoPartita());
		resultLabel.setFont(new Font("Roboto", Font.BOLD, 16));
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setBounds(10, 11, 414, 35);
		frame.getContentPane().add(resultLabel);

		JTextArea descrizioneTextArea = new JTextArea(partita.getDescrizioneFinale());
		descrizioneTextArea.setFont(new Font("Roboto", Font.PLAIN, 14));
		descrizioneTextArea.setEditable(false);
		descrizioneTextArea.setBounds(10, 49, 414, 86);
		frame.getContentPane().add(descrizioneTextArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 414, 104);
		frame.getContentPane().add(scrollPane);
		
				textArea = new JTextArea();
				textArea.setFont(new Font("Roboto", Font.PLAIN, 12));
				scrollPane.setViewportView(textArea);
				textArea.setEditable(false);
				implementTextArea(textArea);
				
				JLabel carteCollezionateLabel = new JLabel("Hai collezionato le seguenti carte:");
				carteCollezionateLabel.setFont(new Font("Roboto", Font.PLAIN, 11));
				scrollPane.setColumnHeaderView(carteCollezionateLabel);
	}

	private void implementTextArea(JTextArea textArea) {
		textArea.setText(partita.getTavolo().getPuntiMiei().toStringTextArea());
	}
}
