package swing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import mazzo.Carta;
import partita.Partita;

public class GamingWindow {

	private JFrame frame;
	private Partita partita;
	private JLabel cartaMiaGiocataLabel;

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
	public GamingWindow(Partita partita) {
		this.partita = partita;
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

		JLabel cartaIAgiocataLabel = new JLabel("");
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

		JLabel outputLabel = new JLabel("outputLabel");
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

		Carta cartaMia1 = partita.getTavolo().getCartaMie(0);
		JButton carta1Button = new JButton(cartaMia1.toString());
		carta1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				giocata(cartaMia1);
			}
		});
		carta1Button.setBounds(10, 190, 116, 35);
		frame.getContentPane().add(carta1Button);

		Carta cartaMia2 = partita.getTavolo().getCartaMie(1);
		JButton carta2Button = new JButton(cartaMia2.toString());
		carta2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giocata(cartaMia2);
			}
		});
		carta2Button.setBounds(161, 190, 116, 35);
		frame.getContentPane().add(carta2Button);

		Carta cartaMia3 = partita.getTavolo().getCartaMie(2);
		JButton carta3Button = new JButton(cartaMia3.toString());
		carta3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giocata(cartaMia3);
			}
		});
		carta3Button.setBounds(308, 190, 116, 35);
		frame.getContentPane().add(carta3Button);
	}

	private void giocata(Carta cartaGiocata) {
		cartaMiaGiocataLabel.setText(cartaGiocata.toString());
		partita.getTavolo().setCartaGiocataIO(cartaGiocata);
		if(partita.isPresoIo()) {
			//procedi con la giocata dell'IA e poi il controllo
		}
		else {
			//procedi con il controllo
		}
	}
	
	
	
}
