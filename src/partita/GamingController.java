package partita;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.IA;
import mazzo.Carta;
import mazzo.Mazzo;
import stampa.Stampa;
//import punti.*;

public class GamingController implements Partita{
	
//	private Mazzo mazzo;
	private Tavolo tavolo;
//	private int contatoreMazzo;
	private boolean iniziaIA,presoIo,fineMazzo,inCorso;
	private Carta briscola;
//	private Punti puntiMiei,puntiIA;
	private IA ia;
	private static Scanner scanner = new Scanner(System.in);
//	private static ComandiSpeciali CS = new ComandiSpeciali();
//	private static List<String> comandiSpeciali = CS.getComandiSpeciali();
	private String risultatoPartita;
	private String descrizioneFinale;

	public GamingController(){
		this.tavolo = new Tavolo();
		Mazzo mazzo = new Mazzo();
		mazzo.mischia();
		this.tavolo.setMazzo(mazzo);
		//per default si presume che inizia l'IA
		this.iniziaIA = true;
		this.presoIo = false;
		this.inCorso = true;
		this.ia = new IA();
//		this.contatoreMazzo = 0;
//		this.fineMazzo = false;
//		this.puntiMiei = new Punti();
//		this.puntiIA = new Punti();
	}
	
	private void creaPartitaManoIA(){
		Mazzo mazzo = this.tavolo.getMazzo();
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.briscola=mazzo.getCarta(0);
		mazzo.briscolaAllaFine();
//		contatoreMazzo = 6;
//		setBriscola(this.mazzo.getCarta(39).getSeme());
	}

	private void creaPartitaManoIo(){
		Mazzo mazzo = this.tavolo.getMazzo();
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.tavolo.aggiungiCarteMie(mazzo.pop());
		this.tavolo.aggiungiCarteIA(mazzo.pop());
		this.briscola=mazzo.getCarta(0);
		mazzo.briscolaAllaFine();
		presoIo=true;
//		contatoreMazzo = 6;
//		setBriscola(this.mazzo.getCarta(39).getSeme());
	}

//	public Punti getPuntiMiei() {
//		return puntiMiei;
//	}
//
//	public void setPuntiMiei(Carta c) {
//		this.puntiMiei.add(c);
//	}
//
//	public Punti getPuntiIA() {
//		return puntiIA;
//	}
//
//	public void setPuntiIA(Carta c) {
//		this.puntiIA.add(c);
//	}

	public Carta getBriscola() {
		return this.briscola;
	}

	public void setBriscola(Carta briscola) {
		this.briscola = briscola;
	}

//	public int getContatoreMazzo() {
//		return contatoreMazzo;
//	}
//
//	public void setContatoreMazzo(int contatoreMazzo) {
//		this.contatoreMazzo += contatoreMazzo;
//	}

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

//	public Mazzo getMazzo(){
//		return this.mazzo;
//	}

	public Tavolo getTavolo(){
		return this.tavolo;
	}
	
	public boolean getIniziaIA(){
		return this.iniziaIA;
	}
	
	public void setIniziaIA(boolean iniziaIA){
		this.iniziaIA = iniziaIA;
		this.presoIo = !iniziaIA;
	}
	
	public boolean isInCorso(){
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

	public void finePartita(){
		this.inCorso = false;
	}
	
	public void creaPartita(){
		if (this.iniziaIA)
			creaPartitaManoIA();
		else
			creaPartitaManoIo();
	}
	
//	public void giocaUnaMano() throws InterruptedException{
//		if(isPresoIo()){
//			iniziaManoIO();
//		} else{
//			inziaManoIA();
//		}
//		checkAndContinue();
//		Thread.sleep(200);
//	}
//
//	private void iniziaManoIO() throws InterruptedException {
//		Thread.sleep(200);
//		giocaManoIO();
//		Thread.sleep(500);
//		giocaManoIA();
//		Thread.sleep(750);
//	}
//
//	private void inziaManoIA() throws InterruptedException {
//		Thread.sleep(200);
//		giocaManoIA();
//		Thread.sleep(500);
//		giocaManoIO();
//		Thread.sleep(200);
//	}
	
	public Carta giocaManoIA(){
		Carta cartaGiocataIA = null;
		if (isPresoIo()){
			if(tavolo.getCartaGiocataIO()==null)
				return null;
			cartaGiocataIA = ia.giocaDopo(this, getBriscola().getSeme());
		}
		else
			cartaGiocataIA = ia.giocaPrima(this, getBriscola().getSeme());
//		Stampa.println("Gioco: "+cartaGiocataIA.toString());
		setGiocataIA(cartaGiocataIA);
		return cartaGiocataIA;
	}
	
	public void giocaManoIO(Carta cartaGiocata){
//		Stampa.println("\nLE TUE CARTE:");
//		tavolo.stampaCarteMie();
//		Stampa.print("\nCosa vuoi giocare...? ");
//		String cartaDaGiocare = scanner.nextLine();
		String cartaDaGiocare = cartaGiocata.toString();
		List<String> carte = new ArrayList<String>();
		for(Carta c: tavolo.getCarteMie())
			carte.add(c.toString());
		while(inCorso && 
				!(carte.contains(cartaDaGiocare)
						|| (isNumero(cartaDaGiocare) && Integer.parseInt(cartaDaGiocare)<=tavolo.getCarteMie().size()) ) )
		{
//			if(comandiSpeciali.contains(s)){
//				CS.esegui(this, s);
//			}
			if(inCorso){
				Stampa.println("Scrivere una carta presente nelle tue carte, oppure il suo indice");
				cartaDaGiocare=scanner.nextLine();				
			}
		}
		if(inCorso)
			setGiocataMia(cartaDaGiocare);
	}

	public void setGiocataMia(String s) {
		if(s==null){
			this.tavolo.setCartaGiocataIO(null);
			return;
		}
		if(s.length()==1){
			Carta cartaGiocataIO = this.tavolo.getCartaMie(Integer.parseInt(s)-1);
			this.tavolo.setCartaGiocataIO(cartaGiocataIO);
			this.tavolo.removeCartaMie(cartaGiocataIO.toString());
		}
		else{
			Carta cartaGiocataIO = null;
			for(Carta c: this.tavolo.getCarteMie())
				if (c.toString().equals(s)){
					cartaGiocataIO = c;
					break;
				}
			this.tavolo.setCartaGiocataIO(cartaGiocataIO);
			this.tavolo.removeCartaMie(cartaGiocataIO.toString());
		}
	}

	public void setGiocataIA(Carta cartaGiocataIA) {
		this.tavolo.setCartaGiocataIA(cartaGiocataIA);
		for(Carta c: this.tavolo.getCarteMie())
			if (c.toString().equals(cartaGiocataIA.toString())){
				cartaGiocataIA = c;
				break;
			}
		tavolo.removeCartaIA(cartaGiocataIA.toString());
	}

	public String checkAndContinue(){
		String outputForOutputLabel = null;
		if(tavolo.getCartaGiocataIO()==null)
			return outputForOutputLabel;
		boolean manoMia;
		if (this.isPresoIo()){
			if(this.tavolo.getCartaGiocataIO().isBetter(this.tavolo.getCartaGiocataIA(),this.briscola.getSeme())){
				manoMia=true;
			}
			else{
				manoMia=false;
			}
		}else{
			if(this.tavolo.getCartaGiocataIA().isBetter(this.tavolo.getCartaGiocataIO(),this.briscola.getSeme())){
				manoMia=false;
			}
			else{
				manoMia=true;
			}
		}
		if(manoMia){
			outputForOutputLabel = "HAI PRESO TU";
			this.tavolo.aggiungiPuntiMiei(this.tavolo.getCartaGiocataIO());
			this.tavolo.aggiungiPuntiMiei(this.tavolo.getCartaGiocataIA());
			if (!this.tavolo.getMazzo().isEmpty()){
				this.tavolo.aggiungiCarteMie(this.tavolo.getMazzo().pop());
				this.tavolo.aggiungiCarteIA(this.tavolo.getMazzo().pop());
			}
			this.setPresoIo(true);
		}else{
			outputForOutputLabel = "HO PRESO IO";
			this.tavolo.aggiungiPuntiIA(this.tavolo.getCartaGiocataIO());
			this.tavolo.aggiungiPuntiIA(this.tavolo.getCartaGiocataIA());
			if (!this.tavolo.getMazzo().isEmpty()){
				this.tavolo.aggiungiCarteIA(this.tavolo.getMazzo().pop());
				this.tavolo.aggiungiCarteMie(this.tavolo.getMazzo().pop());
			}
			this.setPresoIo(false);
		}
		this.tavolo.setCartaGiocataIO(null);
		this.tavolo.setCartaGiocataIA(null);
		return outputForOutputLabel;
	}

	public void endGame() {
		if(this.isInCorso()){
//			Stampa.println("\nPeccato... Mi stavo divertendo...");
//			Stampa.println("Ti aspetto per una partita vera!");
			this.setRisultatoPartita("HAI ABBANDONATO LA PARTITA");
			this.setDescrizioneFinale("Peccato... Mi stavo divertendo...\n"
					+ "Ti aspetto per una partita vera!");
			return;
		}
		finePartita();
//		Stampa.println("\nLe carte che hai collezionato sono:");
//		Stampa.println(tavolo.getPuntiMiei().toString());
		int punteggio = tavolo.getPuntiMiei().contaPunti();
//		Stampa.println("\n\nHai totalizzato "+punteggio+" punti\n");
		if (punteggio>60){
//			Stampa.println("COMPLIMENTI! HAI VINTO!");
//			Stampa.println();
//			Stampa.println("Sei veramente bravo!");
//			Stampa.println("Grazie per aver giocato con me!");
//			Stampa.println("Spero mi concederai la rivincita un giorno...");
			this.setRisultatoPartita("COMPLMENTI! HAI VINTO");
			this.setDescrizioneFinale("Hai collezionato " + punteggio + " punti\n"
					+ "\nSei veramente bravo!\n"
					+ "Grazie per aver giocato con me!\n"
					+ "Spero mi concederai la rivincita un giorno...");
		}
		else if (punteggio<59){
//			Stampa.println("HAHAHA! Ti ho battuto!");
//			Stampa.println();
//			Stampa.println("E' stata una bella partita ma alla fine ha vinto il pi� forte, cio� io!");
//			Stampa.println("Ritenta, magari la prossima volta sarai pi� fortunato!");
			this.setRisultatoPartita("TI HO BATTUTO!");
			this.setDescrizioneFinale("Hai collezionato " + punteggio + " punti\n"
					+ "\nE' stata  una bella partita ma alla fine ha vinto il pi� forte, cio� io!\n"
					+ "Ritenta, magari la prossima volta sarai pi� fortunato!");
		}
		else if (punteggio==59){
//			Stampa.println("HAI PERSO! HAI FATTO 59!!!");
//			Stampa.println("Come si dice: 'Meglio star fuori quando piove...");
//			Stampa.println("...che giocare a briscola e fare 59!'");
//			Stampa.println("Ritenta, magari la prossima volta sarai pi� fortunato!");
			this.setRisultatoPartita("HAI PERSO! HAI FATTO 59!!!");
			this.setDescrizioneFinale("Come si dice: 'Meglio star fuori quando piove...\n"
					+ "...che giocare a briscola e fare 59!'\n"
					+ "Ritenta, magari la prossima volta sarai pi� fortunato!");
		}
		else{
//			Stampa.println("NON CI POSSO CREDERE: ABBIAMO PAREGGIATO!");
//			Stampa.println("Strano, � pi� facile leccarsi il gomito del braccio che pareggiare a briscola...");
//			Stampa.println("E' stato comunque un piacere aver giocato con te.");
//			Stampa.println("La prossima volta vedremo chi la spunter�...rivincita?");
			this.setRisultatoPartita("NON CI POSSO CREDERE: ABBIAMO PAREGGIATO!");
			this.setDescrizioneFinale("Strano, � pi� facile leccarsi il gomito del braccio che pareggiare a briscola...\n"
					+ "E' stato comunque un piacere aver giocato con te.\n"
					+ "La prossima volta vedremo chi la spunter�... Rivincita?");
		}
	}

	private static boolean isNumero(String s) {
		if (s.equals(""))
			return false;
		for (int i=0;i<s.length();i++){
			if (s.charAt(i)<'1' || s.charAt(i)>'9')
				return false;
		}
		return true;
	}

}
