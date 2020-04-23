package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import mazzo.Carta;
import partita.GamingController;

public class GamingWindow {

	private JFrame frame;
	private GamingController partita;
	private JLabel cartaIAgiocataLabel;
	private JLabel cartaMiaGiocataLabel;
	private JLabel outputLabel;
	private int tempoRisposta;
	private JButton[] buttons;

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
	public GamingWindow(GamingController partita) {
		this.partita = partita;
		this.tempoRisposta = 1000;
		this.buttons = new JButton[3];
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Briscola 2.0");
		frame.setBounds(400, 200, 620, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel labelforIA = new JLabel("carta giocata IA:");
		labelforIA.setFont(new Font("Roboto", Font.PLAIN, 12));
		labelforIA.setHorizontalAlignment(SwingConstants.CENTER);
		labelforIA.setBounds(208, 75, 94, 14);
		frame.getContentPane().add(labelforIA);

		cartaIAgiocataLabel = new JLabel("");
		Carta cartaGiocataIA = partita.getTavolo().getCartaGiocataIA();
		if (cartaGiocataIA != null) {
			cartaIAgiocataLabel.setIcon(new ImageIcon(cartaGiocataIA.toStringPath()));
		}
		cartaIAgiocataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartaIAgiocataLabel.setBounds(218, 102, 86, 125);
		frame.getContentPane().add(cartaIAgiocataLabel);

		JLabel labelforme = new JLabel("carta giocata:");
		labelforme.setFont(new Font("Roboto", Font.PLAIN, 12));
		labelforme.setHorizontalAlignment(SwingConstants.CENTER);
		labelforme.setBounds(388, 75, 94, 14);
		frame.getContentPane().add(labelforme);

		cartaMiaGiocataLabel = new JLabel("");
		Carta cartaGiocataIO = partita.getTavolo().getCartaGiocataIO();
		if (cartaGiocataIO != null) {
			cartaMiaGiocataLabel.setIcon(new ImageIcon(cartaGiocataIO.toStringPath()));
		}
		cartaMiaGiocataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartaMiaGiocataLabel.setBounds(392, 99, 80, 128);
		frame.getContentPane().add(cartaMiaGiocataLabel);

		outputLabel = new JLabel("");
		outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		outputLabel.setFont(new Font("Roboto", Font.PLAIN, 24));
		outputLabel.setBounds(100, 11, 488, 53);
		frame.getContentPane().add(outputLabel);

		JLabel cartaDiBriscolaLabel = new JLabel("LE TUE CARTE");
		cartaDiBriscolaLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
		cartaDiBriscolaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartaDiBriscolaLabel.setBounds(289, 398, 124, 14);
		frame.getContentPane().add(cartaDiBriscolaLabel);

		JLabel cartaDiBriscola = new JLabel(new ImageIcon(partita.getBriscola().toStringPath()));
		cartaDiBriscola.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cartaDiBriscola.setBounds(40, 143, 94, 125);
		frame.getContentPane().add(cartaDiBriscola);

		JButton carta1Button = new JButton();
		buttonImplementation(carta1Button, 0);
		carta1Button.setBounds(222, 271, 80, 125);
		frame.getContentPane().add(carta1Button);

		JButton carta2Button = new JButton();
		buttonImplementation(carta2Button, 1);
		carta2Button.setBounds(313, 271, 80, 126);
		frame.getContentPane().add(carta2Button);

		JButton carta3Button = new JButton();
		buttonImplementation(carta3Button, 2);
		carta3Button.setBounds(403, 270, 80, 126);
		frame.getContentPane().add(carta3Button);

		JButton terminaButton = new JButton("termina");
		terminaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launchEndGame();
			}
		});
		terminaButton.setBounds(507, 412, 89, 23);
		frame.getContentPane().add(terminaButton);
		
		JLabel cartaDiBriscolaLabel_1 = new JLabel("la carta di briscola \u00E8:");
		cartaDiBriscolaLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		cartaDiBriscolaLabel_1.setFont(new Font("Roboto", Font.PLAIN, 12));
		cartaDiBriscolaLabel_1.setBounds(23, 118, 124, 14);
		frame.getContentPane().add(cartaDiBriscolaLabel_1);

	}

	private void buttonImplementation(JButton button, int pos) {
		button.setBackground(Color.WHITE);
		Carta carta = partita.getTavolo().getCarteMieReal()[pos];
		button.setIcon(new ImageIcon(carta.toStringPath()));
		buttons[pos] = button;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				button.setEnabled(false);
				if (partita.isInCorso()) {
					giocata(pos);
				}
			}
		});
	}

	private void giocata(int pos) {
		Carta cartaGiocata = partita.getTavolo().getCarteMieReal()[pos];

		/* dichiaro la carta che gioco */
		timing(new Timer(0, giocoCarta(cartaGiocata)));
	}

	private ActionListener giocoCarta(Carta cartaGiocata) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartaMiaGiocataLabel.setIcon(new ImageIcon(cartaGiocata.toStringPath()));
				partita.giocaManoIO(cartaGiocata);

				/* se tocca all'IA dichiara la carta che gioca e controllo chi ha preso */
				if (partita.isPresoIo()) {
					Carta cartaGiocataIA = partita.giocaManoIA();
					timing(new Timer(tempoRisposta, dichiaraCartaIA(cartaIAgiocataLabel, cartaGiocataIA)));
				}
				/* altrimenti controllo direttamente chi ha preso */
				else {
					String outputString = partita.checkAndContinue();
					timing(new Timer(tempoRisposta, checkAndContinue(outputString)));
				}
			}
		};
	}

	private ActionListener dichiaraCartaIA(JLabel cartaIAgiocataLabel, Carta cartaGiocataIA) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartaIAgiocataLabel.setIcon(new ImageIcon(cartaGiocataIA.toStringPath()));
				String outputString = partita.checkAndContinue();

				/* controllo chi ha preso */
				timing(new Timer(tempoRisposta, checkAndContinue(outputString)));
			}
		};

	}

	private ActionListener checkAndContinue(String outputString) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputLabel.setText(outputString);

				/* aggiorno le carte che ho in mano */
				timing(new Timer(tempoRisposta, aggiornoCarteMie()));
			}
		};

	}

	private ActionListener aggiornoCarteMie() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < buttons.length; i++) {
					updateButton(buttons[i], i);
				}

				/* se ha preso l'IA le faccio giocare la prossima mano */
				if (!partita.isPresoIo() && partita.isInCorso()) {
					partita.giocaManoIA();
					Carta cartaGiocataIA = partita.getTavolo().getCartaGiocataIA();
					timing(new Timer(0, aggiornoCartaGiocataIA(cartaGiocataIA)));
				}
				/* altrimenti svuoto tutte le label */
				else {
					timing(new Timer(0, svuotaLabel()));
				}
				

				if (partita.getTavolo().getMazzo().getMazzo().size() == 2) {
					timing(new Timer(tempoRisposta, ultimeDueCarte()));
				}

				/* se la partita è finita lancia EndWindow */
				if (!partita.isInCorso()) {
					launchEndGame();
				}
			}

		};

	}

	private ActionListener aggiornoCartaGiocataIA(Carta cartaGiocataIA) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartaIAgiocataLabel.setIcon(new ImageIcon(cartaGiocataIA.toStringPath()));
				cartaMiaGiocataLabel.setIcon(new ImageIcon());
				outputLabel.setIcon(new ImageIcon());
			}
		};
	}

	private ActionListener svuotaLabel() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartaIAgiocataLabel.setIcon(new ImageIcon());
				cartaMiaGiocataLabel.setIcon(new ImageIcon());
				outputLabel.setIcon(new ImageIcon());
			}
		};
	}
	
	private ActionListener ultimeDueCarte() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outputLabel.setText("ULTIME DUE CARTE DEL MAZZO");				
			}
		};
	}

	private void timing(Timer timer) {
		timer.setRepeats(false);
		timer.start();
	}

	private void launchEndGame() {
		partita.endGame();
		new EndWindow(this.partita).main();
		this.frame.setVisible(false);
	}

	private void updateButton(JButton button, int pos) {
		Carta[] carteMieReal = partita.getTavolo().getCarteMieReal();
		if (carteMieReal[pos] != null) {
			button.setIcon(new ImageIcon(carteMieReal[pos].toStringPath()));
			button.setEnabled(true);
		} else {
			button.setVisible(false);
		}
	}
}
