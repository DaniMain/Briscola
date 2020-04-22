package swing;

import java.awt.Component;
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
//	private Tavolo tavolo;
	private JLabel cartaIAgiocataLabel;
	private JLabel cartaMiaGiocataLabel;
	private JLabel outputLabel;

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
//		this.tavolo = this.partita.getTavolo();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Briscola 2.0");
		frame.setBounds(100, 100, 450, 300);
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
		outputLabel.setBounds(138, 49, 172, 44);
		frame.getContentPane().add(outputLabel);

		JLabel cartaDiBriscolaLabel = new JLabel("la carta di briscola \u00E8:");
		cartaDiBriscolaLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		cartaDiBriscolaLabel.setBounds(60, 15, 125, 14);
		frame.getContentPane().add(cartaDiBriscolaLabel);

		JLabel cartaDiBriscola = new JLabel(partita.getBriscola().toString());
		cartaDiBriscola.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cartaDiBriscola.setBounds(194, 2, 125, 36);
		frame.getContentPane().add(cartaDiBriscola);

		int pos1 = 0;
//		Carta cartaMia1 = partita.getTavolo().getCartaMie(pos1);
//		JButton carta1Button = new JButton(cartaMia1.toString());
		JButton carta1Button = new JButton();
		buttonImplementation(carta1Button, pos1);
//		carta1Button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				giocata(cartaMia1, carta1Button, pos1);
//			}
//		});
		carta1Button.setBounds(10, 190, 116, 35);
		frame.getContentPane().add(carta1Button);
//
		int pos2 = 1;
//		Carta cartaMia2 = partita.getTavolo().getCartaMie(pos2);		
//		JButton carta2Button = new JButton(cartaMia2.toString());		
		JButton carta2Button = new JButton();
		buttonImplementation(carta2Button, pos2);
//		carta2Button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				giocata(cartaMia2, carta2Button, pos2);
//			}
//		});
		carta2Button.setBounds(161, 190, 116, 35);
		frame.getContentPane().add(carta2Button);
//
		int pos3 = 2;
//		Carta cartaMia3 = partita.getTavolo().getCartaMie(pos3);
//		JButton carta3Button = new JButton(cartaMia3.toString());
		JButton carta3Button = new JButton();
		buttonImplementation(carta3Button, pos3);
//		carta3Button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				giocata(cartaMia3, carta3Button, pos3);
//			}
//		});
		carta3Button.setBounds(308, 190, 116, 35);
		frame.getContentPane().add(carta3Button);

//		
//		
//		JLabel prova1Label = new JLabel("prova1");
//		prova1Label.setBounds(10, 49, 46, 14);
//		frame.getContentPane().add(prova1Label);
//
//		JLabel prova2Label = new JLabel("prova2");
//		prova2Label.setBounds(60, 49, 46, 14);
//		frame.getContentPane().add(prova2Label);
//
//		JButton provaButton = new JButton("prova");
//		provaButton.setBounds(10, 65, 89, 23);
//		frame.getContentPane().add(provaButton);
//		provaButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				prova1Label.setText("1");
//				System.out.println("1");
//
//				Timer timer1 = new Timer(1000, firstListener(prova1Label, prova2Label));
//				timer1.setRepeats(false);
//				timer1.start();
//			}
//
//		});
//
//	}
//
//	private ActionListener firstListener(JLabel prova1Label, JLabel prova2Label) {
//		return new ActionListener() {
//			public void actionPerformed(ActionEvent event) {
//				prova2Label.setText("2");
//				System.out.println("2");
//
//				Timer timer2 = new Timer(2000, secondListener(prova1Label));
//				timer2.setRepeats(false);
//				timer2.start();
//			}
//		};
//	}
//
//	private ActionListener secondListener(JLabel prova1Label) {
//		return new ActionListener() {
//			public void actionPerformed(ActionEvent event) {
//				prova1Label.setText("3");
//				System.out.println("3");
//			}
//		};
	}

	private void buttonImplementation(JButton button, int pos) {
		try {
			Carta carta = partita.getTavolo().getCartaMie(pos);
			button.setText(carta.toString());
		} catch (Exception e) {
			button.setVisible(false);
		}
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				giocata(pos);
			}
		});
	}

	private void giocata(int pos) {
		Carta cartaGiocata = partita.getTavolo().getCartaMie(pos);
		/* dichiaro la carta che gioco */
//		cartaMiaGiocataLabel.setText(cartaGiocata.toString());
//		partita.giocaManoIO(cartaGiocata);
		Timer timer1 = new Timer(0, giocoCarta(cartaGiocata));
		timer1.setRepeats(false);
		timer1.start();
		System.out.println("1");
		
//		Carta cartaGiocata = partita.getTavolo().getCartaMie(pos);
//		/* dichiaro la carta che gioco */
//		cartaMiaGiocataLabel.setText(cartaGiocata.toString());
//		partita.giocaManoIO(cartaGiocata);
////		Timer timer1 = new Timer(0, giocoCarta(cartaGiocata));
////		timer1.setRepeats(false);
////		timer1.start();
//		System.out.println("1");
//
//		/* se tocca all'IA dichiara la carta che gioca */
//		if (partita.isPresoIo()) {
//			Carta cartaGiocataIA = partita.giocaManoIA();
//			Timer timer1b = new Timer(2000, dichiaraCartaIA(cartaIAgiocataLabel ,cartaGiocataIA.toString()));
//			timer1b.setRepeats(false);
//			timer1b.start();
//			System.out.println("1b");
//		}
//		
//		/* controllo chi ha preso */
//		String outputString = partita.checkAndContinue();
//		Timer timer2 = new Timer(2000, checkAndContinue(outputString));
//		timer2.setRepeats(false);
//		timer2.start();
//		System.out.println("2");
//
//		/* aggiorno le carte che ho in mano */
//		Timer timer3 = new Timer(2000, aggiornoCarteMie());
//		timer3.setRepeats(false);
//		timer3.start();
//		System.out.println("3");
//
//		/* se ha preso l'IA le faccio giocare la prossima mano */
//		if (!partita.isPresoIo()) {
//			partita.giocaManoIA();
//			Carta cartaGiocataIA = partita.getTavolo().getCartaGiocataIA();
//			Timer timer3b = new Timer(2000, aggiornoCartaGiocataIA(cartaIAgiocataLabel,
//					cartaMiaGiocataLabel,
//					cartaGiocataIA.toString()));
//			timer3b.setRepeats(false);
//			timer3b.start();
//			System.out.println("3b");
//		}
//		/* altrimenti svuoto tutte le label */
//		else {
//			Timer timer3c = new Timer(2000, svuotaLabel(cartaIAgiocataLabel, cartaMiaGiocataLabel));
//			timer3c.setRepeats(false);
//			timer3c.start();
//			System.out.println("3c");
//		}
		

//		Carta cartaGiocata = this.partita.getTavolo().getCartaMie(pos);
//		/* dichiaro la carta che gioco */
//		this.cartaMiaGiocataLabel.setText(cartaGiocata.toString());
//		this.partita.giocaManoIO(cartaGiocata);
//
//		/* se tocca all'IA dichiara la carta che gioca */
//		if (this.partita.isPresoIo()) {
//			this.cartaIAgiocataLabel.setText(this.partita.giocaManoIA().toString());
//		}
//		/* controllo chi ha preso */
//		this.outputLabel.setText(this.partita.checkAndContinue());
//
//		/* aggiorno le carte che ho in mano */
//		pos = 0;
//		for (Component c : this.frame.getContentPane().getComponents()) {
//			if (c.getClass() == JButton.class) {
//				((JButton) c).setText(this.partita.getTavolo().getCartaMie(pos).toString());
//				pos++;
//			}
//		}
//
//		/* se ha preso l'IA le faccio giocare la prossima mano */
//		if (!this.partita.isPresoIo()) {
//			this.partita.giocaManoIA();
//			this.cartaIAgiocataLabel.setText(this.partita.getTavolo().getCartaGiocataIA().toString());
//			this.cartaMiaGiocataLabel.setText("");
//		}

	}

	private ActionListener giocoCarta(Carta cartaGiocata) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartaMiaGiocataLabel.setText(cartaGiocata.toString());
				partita.giocaManoIO(cartaGiocata);
				
				/* se tocca all'IA dichiara la carta che gioca */
				if (partita.isPresoIo()) {
					Carta cartaGiocataIA = partita.giocaManoIA();
					Timer timer1b = new Timer(2000, dichiaraCartaIA(cartaIAgiocataLabel, cartaGiocataIA.toString()));
					timer1b.setRepeats(false);
					timer1b.start();
					System.out.println("1b");
				}
				else {
					/* controllo chi ha preso */
					String outputString = partita.checkAndContinue();
					Timer timer2 = new Timer(2000, checkAndContinue(outputString));
					timer2.setRepeats(false);
					timer2.start();
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
				Timer timer2 = new Timer(2000, checkAndContinue(outputString));
				timer2.setRepeats(false);
				timer2.start();
				System.out.println("2");
			}
		};

	}

	private ActionListener checkAndContinue(String outputString) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputLabel.setText(outputString);
				
				/* aggiorno le carte che ho in mano */
				Timer timer3 = new Timer(1000, aggiornoCarteMie());
				timer3.setRepeats(false);
				timer3.start();
				System.out.println("3");
			}
		};

	}

	private ActionListener aggiornoCarteMie() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pos = 0;
				for (Component c : frame.getContentPane().getComponents()) {
					if (c.getClass() == JButton.class) {
						((JButton) c).setText(partita.getTavolo().getCartaMie(pos).toString());
						pos++;
						if (pos==3) {
							break;
						}
					}
				}
				
				/* se ha preso l'IA le faccio giocare la prossima mano */
				if (!partita.isPresoIo()) {
					partita.giocaManoIA();
					Carta cartaGiocataIA = partita.getTavolo().getCartaGiocataIA();
					Timer timer3b = new Timer(0, aggiornoCartaGiocataIA(cartaIAgiocataLabel,
							cartaMiaGiocataLabel,
							cartaGiocataIA.toString()));
					timer3b.setRepeats(false);
					timer3b.start();
					System.out.println("3b");
				}
				/* altrimenti svuoto tutte le label */
				else {
					Timer timer3c = new Timer(0, svuotaLabel(cartaIAgiocataLabel, cartaMiaGiocataLabel));
					timer3c.setRepeats(false);
					timer3c.start();
					System.out.println("3c");
				}
			}
		};

	}

	private ActionListener aggiornoCartaGiocataIA(JLabel cartaIAgiocataLabel,
			JLabel cartaMiaGiocataLabel,
			String cartaGiocataIA) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartaIAgiocataLabel.setText(cartaGiocataIA.toString());
				cartaMiaGiocataLabel.setText("");
			}
		};
	}

	private ActionListener svuotaLabel(JLabel cartaIAgiocataLabel, JLabel cartaMiaGiocataLabel) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartaIAgiocataLabel.setText("");
				cartaMiaGiocataLabel.setText("");
			}
		};
	}

}
