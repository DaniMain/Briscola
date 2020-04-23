package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
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
	private JLabel cartaDiBriscolaLabel;
	private JLabel cartaDiBriscola;
	private JTextArea ultimeDueCarteLabel;

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
		frame.setTitle("Briscola 2.1");
		frame.setBounds(300, 150, 620, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		outputLabel = new JLabel("");
		outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		outputLabel.setFont(new Font("Roboto", Font.BOLD, 24));
		outputLabel.setBounds(100, 11, 488, 53);
		frame.getContentPane().add(outputLabel);
		
		ultimeDueCarteLabel = new JTextArea();
		ultimeDueCarteLabel.setEditable(false);
		ultimeDueCarteLabel.setFont(new Font("Roboto", Font.BOLD | Font.ITALIC, 12));
		ultimeDueCarteLabel.setBounds(37, 283, 124, 45);
		frame.getContentPane().add(ultimeDueCarteLabel);
		ultimeDueCarteLabel.setVisible(false);
		
		cartaDiBriscolaLabel = new JLabel("la carta di briscola \u00E8:");
		cartaDiBriscolaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartaDiBriscolaLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
		cartaDiBriscolaLabel.setBounds(37, 118, 124, 14);
		frame.getContentPane().add(cartaDiBriscolaLabel);

		cartaDiBriscola = new JLabel(
				new ImageIcon(
						Toolkit.getDefaultToolkit().getImage(getClass().getResource(
								partita.getBriscola().toStringPath()))));
		cartaDiBriscola.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cartaDiBriscola.setBounds(63, 143, 73, 133);
		frame.getContentPane().add(cartaDiBriscola);

		JLabel labelforIA = new JLabel("carta giocata IA:");
		labelforIA.setFont(new Font("Roboto", Font.PLAIN, 12));
		labelforIA.setHorizontalAlignment(SwingConstants.CENTER);
		labelforIA.setBounds(241, 75, 94, 14);
		frame.getContentPane().add(labelforIA);

		cartaIAgiocataLabel = new JLabel();
		Carta cartaGiocataIA = partita.getTavolo().getCartaGiocataIA();		
		if (cartaGiocataIA != null) {
			cartaIAgiocataLabel.setIcon(
					new ImageIcon(
							Toolkit.getDefaultToolkit().getImage(getClass().getResource(
									cartaGiocataIA.toStringPath()))));
		}
		cartaIAgiocataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartaIAgiocataLabel.setBounds(251, 102, 73, 133);
		frame.getContentPane().add(cartaIAgiocataLabel);

		JLabel labelforme = new JLabel("carta giocata:");
		labelforme.setFont(new Font("Roboto", Font.PLAIN, 12));
		labelforme.setHorizontalAlignment(SwingConstants.CENTER);
		labelforme.setBounds(357, 75, 94, 14);
		frame.getContentPane().add(labelforme);

		cartaMiaGiocataLabel = new JLabel();
		Carta cartaGiocataIO = partita.getTavolo().getCartaGiocataIO();
		if (cartaGiocataIO != null) {
			cartaMiaGiocataLabel.setIcon(
					new ImageIcon(
							Toolkit.getDefaultToolkit().getImage(getClass().getResource(
									cartaGiocataIO.toStringPath()))));
		}
		cartaMiaGiocataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartaMiaGiocataLabel.setBounds(367, 102, 73, 133);
		frame.getContentPane().add(cartaMiaGiocataLabel);

		JLabel cartaDiBriscolaLabel = new JLabel("LE TUE CARTE");
		cartaDiBriscolaLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
		cartaDiBriscolaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartaDiBriscolaLabel.setBounds(299, 399, 94, 14);
		frame.getContentPane().add(cartaDiBriscolaLabel);

		JButton carta1Button = new JButton();
		buttonImplementation(carta1Button, 0);
		carta1Button.setBounds(229, 263, 73, 133);
		frame.getContentPane().add(carta1Button);

		JButton carta2Button = new JButton();
		buttonImplementation(carta2Button, 1);
		carta2Button.setBounds(309, 263, 73, 133);
		frame.getContentPane().add(carta2Button);

		JButton carta3Button = new JButton();
		buttonImplementation(carta3Button, 2);
		carta3Button.setBounds(388, 263, 73, 133);
		frame.getContentPane().add(carta3Button);

		JButton terminaButton = new JButton("termina");
		terminaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launchEndGame();
			}
		});
		terminaButton.setBounds(507, 412, 89, 23);
		frame.getContentPane().add(terminaButton);

	}

	private void buttonImplementation(JButton button, int pos) {
		button.setBackground(Color.WHITE);
		Carta carta = partita.getTavolo().getCarteMieReal()[pos];
		button.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(getClass().getResource(
						carta.toStringPath()))));
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
				cartaMiaGiocataLabel.setIcon(
						new ImageIcon(
								Toolkit.getDefaultToolkit().getImage(getClass().getResource(
										cartaGiocata.toStringPath()))));
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
				cartaIAgiocataLabel.setIcon(
						new ImageIcon(
								Toolkit.getDefaultToolkit().getImage(getClass().getResource(
										cartaGiocataIA.toStringPath()))));
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
					timing(new Timer(0, svuotaLabelIA()));
					cartaIAgiocataLabel.setIcon(new ImageIcon());
				}
				
				/* effettua i controlli finali della mano */
				timing(new Timer(0, fineMano()));
				
			}

		};

	}

	private ActionListener aggiornoCartaGiocataIA(Carta cartaGiocataIA) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartaIAgiocataLabel.setIcon(
						new ImageIcon(
								Toolkit.getDefaultToolkit().getImage(getClass().getResource(
										cartaGiocataIA.toStringPath()))));
			}
		};
	}

	private ActionListener svuotaLabelIA() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartaIAgiocataLabel.setIcon(new ImageIcon());
			}
		};
		
	}

	private ActionListener fineMano() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/* se la partita è finita lancia EndWindow */
				if (!partita.isInCorso()) {
					launchEndGame();
					return;
				}
				
				/* svuota le label delle mie carte giocate */
				outputLabel.setText("");
				cartaMiaGiocataLabel.setIcon(new ImageIcon());
				
				/* se mancano due carte stampa nella label dedicata */
				timing(new Timer(0, ultimeDueCarte()));
				
				/* se il mazzo è vuoto non visualizzare la carta di briscola */
				if (partita.getTavolo().getMazzo().isEmpty()) {
					cartaDiBriscola.setVisible(false);
					cartaDiBriscolaLabel.setVisible(false);
				}
			}
		};
		
	}
	
	private ActionListener ultimeDueCarte() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (partita.getTavolo().getMazzo().getMazzo().size() == 2) {
					ultimeDueCarteLabel.setText("ULTIME DUE CARTE\nDEL MAZZO");
					ultimeDueCarteLabel.setVisible(true);
				}
				else {
					ultimeDueCarteLabel.setText("");
					ultimeDueCarteLabel.setVisible(false);
				}
					
			}
		};
	}

	private void timing(Timer timer) {
		timer.setRepeats(false);
		timer.start();
	}

	private void updateButton(JButton button, int pos) {
		Carta[] carteMieReal = partita.getTavolo().getCarteMieReal();
		if (carteMieReal[pos] != null) {
			button.setIcon(
					new ImageIcon(
							Toolkit.getDefaultToolkit().getImage(getClass().getResource(
									carteMieReal[pos].toStringPath()))));
			button.setEnabled(true);
		} else {
			button.setVisible(false);
		}
	}

	private void launchEndGame() {
		partita.endGame();
		this.frame.setVisible(false);
		new EndWindow(this.partita).main();
	}
}
