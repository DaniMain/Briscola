package partita;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.IA;
import main.Partita;
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
		return briscola;
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
		return fineMazzo;
	}

	public void setFineMazzo(boolean fineMazzo) {
		this.fineMazzo = fineMazzo;
	}
	
	public boolean isPresoIo() {
		return presoIo;
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
		return ia;
	}

	public void setIa(IA ia) {
		this.ia = ia;
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

	public void checkAndContinue(){
		if(tavolo.getCartaGiocataIO()==null)
			return;
		boolean manoMia;
		if (this.presoIo){
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
			Stampa.println("\nHAI PRESO TU!\n");
			this.tavolo.aggiungiPuntiMiei(this.tavolo.getCartaGiocataIO());
			this.tavolo.aggiungiPuntiMiei(this.tavolo.getCartaGiocataIA());
			if (!this.tavolo.getMazzo().isEmpty()){
				this.tavolo.aggiungiCarteMie(this.tavolo.getMazzo().pop());
				this.tavolo.aggiungiCarteIA(this.tavolo.getMazzo().pop());
			}
			this.setPresoIo(true);
		}else{
			Stampa.println("\nHO PRESO IO!\n");
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
	}
	
	public void giocaUnaMano() throws InterruptedException{
		if(isPresoIo()){
			iniziaManoIO();
		} else{
			inziaManoIA();
		}
		checkAndContinue();
		Thread.sleep(200);
	}

	private void iniziaManoIO() throws InterruptedException {
		Thread.sleep(200);
		giocaManoIO();
		Thread.sleep(500);
		giocaManoIA();
		Thread.sleep(750);
	}

	private void inziaManoIA() throws InterruptedException {
		Thread.sleep(200);
		giocaManoIA();
		Thread.sleep(500);
		giocaManoIO();
		Thread.sleep(200);
	}
	
	private void giocaManoIA(){
		Carta cartaGiocataIA = null;
		if (isPresoIo()){
			if(tavolo.getCartaGiocataIO()==null)
				return;
			cartaGiocataIA = ia.giocaDopo(this, getBriscola().getSeme());
		}
		else
			cartaGiocataIA = ia.giocaPrima(this, getBriscola().getSeme());
		Stampa.println("Gioco: "+cartaGiocataIA.toString());
		setGiocataIA(cartaGiocataIA);
	}
	
	private void giocaManoIO(){
		Stampa.println("\nLE TUE CARTE:");
		tavolo.stampaCarteMie();
		Stampa.print("\nCosa vuoi giocare...? ");
		String s = scanner.nextLine();
		List<String> carte = new ArrayList<String>();
		for(Carta c: tavolo.getCarteMie())
			carte.add(c.toString());
		while(inCorso && 
				!(carte.contains(s)
						|| (isNumero(s) && Integer.parseInt(s)<=tavolo.getCarteMie().size()) ) )
		{
//			if(comandiSpeciali.contains(s)){
//				CS.esegui(this, s);
//			}
			if(inCorso){
				Stampa.println("Scrivere una carta presente nelle tue carte, oppure il suo indice");
				s=scanner.nextLine();				
			}
		}
		if(inCorso)
			setGiocataMia(s);
	}

	public void endGame() throws InterruptedException {
		if(!inCorso){
			Stampa.println("\nPeccato... Mi stavo divertendo...");
			Stampa.println("Ti aspetto per una partita vera!");
			return;
		}
		finePartita();
		Stampa.println("\nLe carte che hai collezionato sono:");
		Stampa.println(tavolo.getPuntiMiei().toString());
		int punteggio = tavolo.getPuntiMiei().contaPunti();
		Stampa.println("\n\nHai totalizzato "+punteggio+" punti\n");
		if (punteggio>60){
			Stampa.println("COMPLIMENTI! HAI VINTO!");
			Stampa.println();
			Thread.sleep(1);
			Stampa.println("Sei veramente bravo!");
			Stampa.println("Grazie per aver giocato con me!");
			Stampa.println("Spero mi concederai la rivincita un giorno...");
		}
		else if (punteggio<59){
			Stampa.println("HAHAHA! Ti ho battuto!");
			Stampa.println();
			Thread.sleep(1);
			Stampa.println("E' stata una bella partita ma alla fine ha vinto il più forte, cioè io!");
			Stampa.println("Ritenta, magari la prossima volta sarai più fortunato!");
		}
		else if (punteggio==59){
			Stampa.println("HAI PERSO! HAI FATTO 59!!!");
			Thread.sleep(1);
			Stampa.println("Come si dice: 'Meglio star fuori quando piove...");
			Thread.sleep(2);
			Stampa.println("...che giocare a briscola e fare 59!'");
			Stampa.println("Ritenta, magari la prossima volta sarai più fortunato!");
		}
		else{
			Stampa.println("NON CI POSSO CREDERE: ABBIAMO PAREGGIATO!");
			Thread.sleep(1);
			Stampa.println("Strano, è più facile leccarsi il gomito del braccio che pareggiare a briscola...");
			Stampa.println("E' stato comunque un piacere aver giocato con te.");
			Stampa.println("La prossima volta vedremo chi la spunterà...rivincita?");
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
