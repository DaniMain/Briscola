package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import partita.GamingController;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setBounds(400, 200, 450, 338);
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
				
				JLabel nuovaPartitaLabel = new JLabel("Vuoi giocare un'altra partita?");
				nuovaPartitaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
				nuovaPartitaLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
				nuovaPartitaLabel.setBounds(99, 269, 203, 15);
				frame.getContentPane().add(nuovaPartitaLabel);
				
				JButton siButton = new JButton("SI");
				siButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						new StartWindow();
						StartWindow.main(null);
					}
				});
				siButton.setFont(new Font("Roboto", Font.PLAIN, 12));
				siButton.setBounds(372, 265, 52, 23);
				frame.getContentPane().add(siButton);
				
				JButton noButton = new JButton("NO");
				noButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				noButton.setFont(new Font("Roboto", Font.PLAIN, 12));
				noButton.setBounds(312, 265, 52, 23);
				frame.getContentPane().add(noButton);
	}

	private void implementTextArea(JTextArea textArea) {
		textArea.setText(partita.getTavolo().getPuntiMiei().toStringTextArea());
	}
}
