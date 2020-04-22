package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		frame.setBounds(400, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel labelforIA = new JLabel("carta giocata IA:");
		labelforIA.setHorizontalAlignment(SwingConstants.TRAILING);
		labelforIA.setBounds(75, 124, 94, 14);
		frame.getContentPane().add(labelforIA);

		cartaIAgiocataLabel = new JLabel("");
		Carta cartaGiocataIA = partita.getTavolo().getCartaGiocataIA();
		if (cartaGiocataIA != null) {
			cartaIAgiocataLabel.setText(cartaGiocataIA.toString());
		}
		cartaIAgiocataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartaIAgiocataLabel.setBounds(163, 120, 116, 23);
		frame.getContentPane().add(cartaIAgiocataLabel);

		JLabel labelforme = new JLabel("carta giocata:");
		labelforme.setHorizontalAlignment(SwingConstants.TRAILING);
		labelforme.setBounds(75, 147, 94, 14);
		frame.getContentPane().add(labelforme);

		cartaMiaGiocataLabel = new JLabel("");
		Carta cartaGiocataIO = partita.getTavolo().getCartaGiocataIO();
		if (cartaGiocataIO != null) {
			cartaMiaGiocataLabel.setText(cartaGiocataIO.toString());
		}
		cartaMiaGiocataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cartaMiaGiocataLabel.setBounds(163, 143, 116, 23);
		frame.getContentPane().add(cartaMiaGiocataLabel);

		outputLabel = new JLabel("");
		outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		outputLabel.setFont(new Font("Roboto", Font.PLAIN, 24));
		outputLabel.setBounds(10, 49, 414, 44);
		frame.getContentPane().add(outputLabel);

		JLabel cartaDiBriscolaLabel = new JLabel("la carta di briscola \u00E8:");
		cartaDiBriscolaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		cartaDiBriscolaLabel.setBounds(60, 15, 125, 14);
		frame.getContentPane().add(cartaDiBriscolaLabel);

		JLabel cartaDiBriscola = new JLabel(partita.getBriscola().toString());
		cartaDiBriscola.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cartaDiBriscola.setBounds(194, 2, 125, 36);
		frame.getContentPane().add(cartaDiBriscola);

		JButton carta1Button = new JButton();
		buttonImplementation(carta1Button, 0);
		carta1Button.setBounds(10, 190, 116, 35);
		frame.getContentPane().add(carta1Button);

		JButton carta2Button = new JButton();
		buttonImplementation(carta2Button, 1);
		carta2Button.setBounds(161, 190, 116, 35);
		frame.getContentPane().add(carta2Button);

		JButton carta3Button = new JButton();
		buttonImplementation(carta3Button, 2);
		carta3Button.setBounds(308, 190, 116, 35);
		frame.getContentPane().add(carta3Button);

		JButton terminaButton = new JButton("termina");
		terminaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launchEndGame();
			}
		});
		terminaButton.setBounds(335, 236, 89, 23);
		frame.getContentPane().add(terminaButton);

	}

	private void buttonImplementation(JButton button, int pos) {
//		try {
		Carta carta = partita.getTavolo().getCarteMieReal()[pos];
		button.setText(carta.toString());
//		} catch (Exception e) {
//			button.setVisible(false);
//		}
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
//		if (partita.getTavolo().getMazzo().getMazzo().size() == 2) {
//			outputLabel.setText("ULTIME DUE CARTE DEL MAZZO");
//		}
		/* dichiaro la carta che gioco */
		timing(new Timer(0, giocoCarta(cartaGiocata)));
		System.out.println("1");

	}

	private ActionListener giocoCarta(Carta cartaGiocata) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartaMiaGiocataLabel.setText(cartaGiocata.toString());
				partita.giocaManoIO(cartaGiocata);

				/* se tocca all'IA dichiara la carta che gioca e controllo chi ha preso */
				if (partita.isPresoIo()) {
					Carta cartaGiocataIA = partita.giocaManoIA();
					timing(new Timer(tempoRisposta, dichiaraCartaIA(cartaIAgiocataLabel, cartaGiocataIA.toString())));
					System.out.println("1b");
				}
				/* altrimenti controllo direttamente chi ha preso */
				else {
					String outputString = partita.checkAndContinue();
					timing(new Timer(tempoRisposta, checkAndContinue(outputString)));
					System.out.println("2");
				}
			}
		};
	}

	private ActionListener dichiaraCartaIA(JLabel cartaIAgiocataLabel, String cartaGiocataIA) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartaIAgiocataLabel.setText(cartaGiocataIA);
				String outputString = partita.checkAndContinue();

				/* controllo chi ha preso */
				timing(new Timer(tempoRisposta, checkAndContinue(outputString)));
				System.out.println("2");
			}
		};

	}

	private ActionListener checkAndContinue(String outputString) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputLabel.setText(outputString);

				/* aggiorno le carte che ho in mano */
				timing(new Timer(tempoRisposta, aggiornoCarteMie()));
				System.out.println("3");
			}
		};

	}

	private ActionListener aggiornoCarteMie() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				int pos = 0;
//				for (Component c : frame.getContentPane().getComponents()) {
//					if (c.getClass() == JButton.class) {
//						if (pos < partita.getTavolo().getCarteMie().size()
//								&& partita.getTavolo().getCartaMie(pos)!=null) {
//							((JButton) c).setText(partita.getTavolo().getCartaMie(pos).toString());
//							((JButton) c).setEnabled(true);
//						}
//						else {
//							((JButton) c).setVisible(false);
//						}
//						pos++;
//					}
//					if (pos==3) {
//						break;
//					}
//				}

				for (int i = 0; i < buttons.length; i++) {
					updateButton(buttons[i], i);
				}

				/* se ha preso l'IA le faccio giocare la prossima mano */
				if (!partita.isPresoIo() && partita.isInCorso()) {
					partita.giocaManoIA();
					Carta cartaGiocataIA = partita.getTavolo().getCartaGiocataIA();
					timing(new Timer(0, aggiornoCartaGiocataIA(cartaGiocataIA.toString())));
					System.out.println("3b");
				}
				/* altrimenti svuoto tutte le label */
				else {
					timing(new Timer(0, svuotaLabel()));
					System.out.println("3c");
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

	private ActionListener aggiornoCartaGiocataIA(String cartaGiocataIA) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartaIAgiocataLabel.setText(cartaGiocataIA.toString());
				cartaMiaGiocataLabel.setText("");
				outputLabel.setText("");
			}
		};
	}

	private ActionListener svuotaLabel() {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartaIAgiocataLabel.setText("");
				cartaMiaGiocataLabel.setText("");
				outputLabel.setText("");
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
			button.setText(carteMieReal[pos].toString());
			button.setEnabled(true);
		} else {
			button.setVisible(false);
		}
	}

}
