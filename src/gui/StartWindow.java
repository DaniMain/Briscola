package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import partita.GamingController;

public class StartWindow {

	private JFrame frame;
	private JRadioButton iniziaRadio;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Benvenuto!");
		frame.setBounds(400, 200, 450, 177);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Roboto", Font.PLAIN, 12));
		textPane.setText("Realizzato da Daniele Mainella il 21/04/2020. Tutti i diritti sono riservati."
				+ "\n\nBenvenuto in Briscola 2.0!\n"
				+ "Per iniziare il gioco selezionare se vuoi iniziare tu la mano oppure no\n"
				+ "e clicca su \"START\"");
		textPane.setEditable(false);
		textPane.setBounds(10, 11, 414, 81);
		frame.getContentPane().add(textPane);
		
		iniziaRadio = new JRadioButton("Inizia tu la mano");
		iniziaRadio.setFont(new Font("Roboto", Font.PLAIN, 12));
		iniziaRadio.setBounds(10, 99, 161, 23);
		frame.getContentPane().add(iniziaRadio);
		
		JButton startButton = new JButton("START");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GamingController partita = new GamingController();
				partita.creaPartita();
				partita.setIniziaIA(!iniziaRadio.isSelected());
				if(!partita.isPresoIo()) {
					partita.giocaManoIA();
				}
				frame.setVisible(false);
				launchGame(partita);
			}
		});
		startButton.setBounds(335, 103, 89, 23);
		frame.getContentPane().add(startButton);
	}
	
	private void launchGame(GamingController partita) {
		new GamingWindow(partita).main();
	}
}
