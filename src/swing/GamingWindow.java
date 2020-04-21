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
import partita.MainController;
import partita.Tavolo;

public class GamingWindow {

	private JFrame frame;
	private MainController partita;
	private Tavolo tavolo;
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
	public GamingWindow(MainController partita) {
		this.partita = partita;
		this.tavolo = this.partita.getTavolo();
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

		outputLabel = new JLabel("outputLabel");
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
			setGiocataIA();
		}
		checkAndContinute();
		if(!partita.isPresoIo()) {
			setGiocataIA();
			cartaIAgiocataLabel.setText(partita.getTavolo().getCartaGiocataIA().toString());
		}
	}
	
	public void setGiocataIA() {
		Carta cartaGiocataIA = null;
		if (partita.isPresoIo()){
			if(tavolo.getCartaGiocataIO()==null)
				return;
			cartaGiocataIA = partita.getIa().giocaDopo(partita, partita.getBriscola().getSeme());
		}
		else
			cartaGiocataIA = partita.getIa().giocaPrima(partita, partita.getBriscola().getSeme());
		partita.setGiocataIA(cartaGiocataIA);
		cartaIAgiocataLabel.setText(cartaGiocataIA.toString());
	}
	
	private void checkAndContinute() {
		boolean manoMia;
		if (partita.isPresoIo()){
			if(tavolo.getCartaGiocataIO().isBetter(
					tavolo.getCartaGiocataIA(),partita.getBriscola().getSeme())){
				manoMia=true;
			}
			else{
				manoMia=false;
			}
		}else{
			if(tavolo.getCartaGiocataIA().isBetter(
					tavolo.getCartaGiocataIO(),partita.getBriscola().getSeme())){
				manoMia=false;
			}
			else{
				manoMia=true;
			}
		}
		if(manoMia){
			outputLabel.setText("Hai preso tu!");
			tavolo.aggiungiPuntiMiei(tavolo.getCartaGiocataIO());
			tavolo.aggiungiPuntiMiei(tavolo.getCartaGiocataIA());
			if (!tavolo.getMazzo().isEmpty()){
				tavolo.aggiungiCarteMie(tavolo.getMazzo().pop());
				tavolo.aggiungiCarteIA(tavolo.getMazzo().pop());
			}
			partita.setPresoIo(true);
		}else{
			outputLabel.setText("Ho preso io!");
			tavolo.aggiungiPuntiIA(tavolo.getCartaGiocataIO());
			tavolo.aggiungiPuntiIA(tavolo.getCartaGiocataIA());
			if (!tavolo.getMazzo().isEmpty()){
				tavolo.aggiungiCarteIA(tavolo.getMazzo().pop());
				tavolo.aggiungiCarteMie(tavolo.getMazzo().pop());
			}
			partita.setPresoIo(false);
		}
		tavolo.setCartaGiocataIO(null);
		tavolo.setCartaGiocataIA(null);		
	}
	
}
