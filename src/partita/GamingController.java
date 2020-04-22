package partita;

import main.IA;
import mazzo.Carta;
import mazzo.Mazzo;

public class GamingController implements Partita {

	private Tavolo tavolo;
	private boolean iniziaIA, presoIo, fineMazzo, inCorso;
	private Carta briscola;
	private IA ia;
	private String risultatoPartita;
	private String descrizioneFinale;

	public GamingController() {
		this.tavolo = new Tavolo();
		Mazzo mazzo = new Mazzo();
		mazzo.mischia();
		this.tavolo.setMazzo(mazzo);
		this.iniziaIA = true; // per default si presume che inizia l'IA
		this.presoIo = false;
		this.inCorso = true;
		this.ia = new IA();
	}

	private void creaPartitaManoIA() {
		Mazzo mazzo = this.tavolo.getMazzo();
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.briscola = mazzo.getCarta(0);
		mazzo.briscolaAllaFine();
	}

	private void creaPartitaManoIo() {
		Mazzo mazzo = this.tavolo.getMazzo();
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.briscola = mazzo.getCarta(0);
		mazzo.briscolaAllaFine();
		presoIo = true;
	}

	public Carta getBriscola() {
		return this.briscola;
	}

	public void setBriscola(Carta briscola) {
		this.briscola = briscola;
	}

	public boolean isFineMazzo() {
		return this.fineMazzo;
	}

	public void setFineMazzo(boolean fineMazzo) {
		this.fineMazzo = fineMazzo;
	}

	public boolean isPresoIo() {
		return this.presoIo;
	}

	public void setPresoIo(boolean presoIo) {
		this.presoIo = presoIo;
	}

	public Tavolo getTavolo() {
		return this.tavolo;
	}

	public boolean getIniziaIA() {
		return this.iniziaIA;
	}

	public void setIniziaIA(boolean iniziaIA) {
		this.iniziaIA = iniziaIA;
		this.presoIo = !iniziaIA;
	}

	public boolean isInCorso() {
		return this.inCorso;
	}

	public IA getIa() {
		return this.ia;
	}

	public void setIa(IA ia) {
		this.ia = ia;
	}

	public String getRisultatoPartita() {
		return risultatoPartita;
	}

	public void setRisultatoPartita(String risultatoPartita) {
		this.risultatoPartita = risultatoPartita;
	}

	public String getDescrizioneFinale() {
		return descrizioneFinale;
	}

	public void setDescrizioneFinale(String descrizioneFinale) {
		this.descrizioneFinale = descrizioneFinale;
	}

	public void setFinePartita() {
		this.inCorso = false;
	}

	public void creaPartita() {
		if (this.iniziaIA)
			creaPartitaManoIA();
		else
			creaPartitaManoIo();
	}

	public Carta giocaManoIA() {
		Carta cartaGiocataIA = null;
		if (isPresoIo()) {
			if (tavolo.getCartaGiocataIO() == null)
				return null;
			cartaGiocataIA = ia.giocaDopo(this, getBriscola().getSeme());
		} else
			cartaGiocataIA = ia.giocaPrima(this, getBriscola().getSeme());
		setGiocataIA(cartaGiocataIA);
		return cartaGiocataIA;
	}

	public void giocaManoIO(Carta cartaGiocata) {
		String cartaDaGiocare = cartaGiocata.toString();
		if (inCorso) {
			setGiocataMia(cartaDaGiocare);
		}
	}

	public void setGiocataMia(String s) {
		Carta cartaGiocataIO = null;
		for (Carta c : this.tavolo.getCarteMie())
			if (c.toString().equals(s)) {
				cartaGiocataIO = c;
				break;
			}
		this.tavolo.setCartaGiocataIO(cartaGiocataIO);
		this.tavolo.removeCartaMieReal(cartaGiocataIO.toString());
	}

	public void setGiocataIA(Carta cartaGiocataIA) {
		this.tavolo.setCartaGiocataIA(cartaGiocataIA);
		tavolo.removeCartaIA(cartaGiocataIA.toString());
	}

	public String checkAndContinue() {
		String outputForOutputLabel = null;
		if (tavolo.getCartaGiocataIO() == null)
			return outputForOutputLabel;
		boolean manoMia;
		if (this.isPresoIo()) {
			if (this.tavolo.getCartaGiocataIO().isBetter(this.tavolo.getCartaGiocataIA(), this.briscola.getSeme())) {
				manoMia = true;
			} else {
				manoMia = false;
			}
		} else {
			if (this.tavolo.getCartaGiocataIA().isBetter(this.tavolo.getCartaGiocataIO(), this.briscola.getSeme())) {
				manoMia = false;
			} else {
				manoMia = true;
			}
		}
		if (manoMia) {
			outputForOutputLabel = "HAI PRESO TU";
			this.tavolo.aggiungiPuntiMiei(this.tavolo.getCartaGiocataIO());
			this.tavolo.aggiungiPuntiMiei(this.tavolo.getCartaGiocataIA());
			if (!this.tavolo.getMazzo().isEmpty()) {
				this.tavolo.aggiungiCarteMie(this.tavolo.getMazzo().pop());
				this.tavolo.aggiungiCarteIA(this.tavolo.getMazzo().pop());
			}
			this.setPresoIo(true);
		} else {
			outputForOutputLabel = "HO PRESO IO";
			this.tavolo.aggiungiPuntiIA(this.tavolo.getCartaGiocataIO());
			this.tavolo.aggiungiPuntiIA(this.tavolo.getCartaGiocataIA());
			if (!this.tavolo.getMazzo().isEmpty()) {
				this.tavolo.aggiungiCarteIA(this.tavolo.getMazzo().pop());
				this.tavolo.aggiungiCarteMie(this.tavolo.getMazzo().pop());
			}
			this.setPresoIo(false);
		}
		if (this.tavolo.getMazzo().isEmpty()
				&& (this.tavolo.getCarteMie().isEmpty() || this.tavolo.getCarteIA().isEmpty())) {
			setFinePartita();
		}
		this.tavolo.setCartaGiocataIO(null);
		this.tavolo.setCartaGiocataIA(null);
		return outputForOutputLabel;
	}

	public void endGame() {
		if (this.isInCorso()) {
			this.setRisultatoPartita("HAI ABBANDONATO LA PARTITA");
			this.setDescrizioneFinale("Peccato... Mi stavo divertendo...\n"
					+ "Ti aspetto per una partita vera!");
			return;
		}
		int punteggio = tavolo.getPuntiMiei().contaPunti();
		if (punteggio > 60) {
			this.setRisultatoPartita("COMPLMENTI! HAI VINTO");
			this.setDescrizioneFinale("Hai collezionato " + punteggio + " punti\n"
					+ "\nSei veramente bravo!\n"
					+ "Grazie per aver giocato con me!\n"
					+ "Spero mi concederai la rivincita un giorno...");
		} else if (punteggio < 59) {
			this.setRisultatoPartita("TI HO BATTUTO!");
			this.setDescrizioneFinale("Hai collezionato " + punteggio + " punti\n"
					+ "\nE' stata  una bella partita ma alla fine ha vinto\n"
					+ " il più forte, cioè io!\n"
					+ "Ritenta, magari la prossima volta sarai più fortunato!");
		} else if (punteggio == 59) {
			this.setRisultatoPartita("HAI PERSO! HAI FATTO 59!!!");
			this.setDescrizioneFinale("Come si dice: 'Meglio star fuori quando piove...\n"
					+ "...che giocare a briscola e fare 59!'\n"
					+ "Ritenta, magari la prossima volta sarai più fortunato!");
		} else {
			this.setRisultatoPartita("NON CI POSSO CREDERE: ABBIAMO PAREGGIATO!");
			this.setDescrizioneFinale("Strano, è più facile leccarsi il gomito del braccio che pareggiare\n"
					+ "a briscola...\n"
					+ "E' stato comunque un piacere aver giocato con te.\n"
					+ "La prossima volta vedremo chi la spunterà... Rivincita?");
		}
	}

}
