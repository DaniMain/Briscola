package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mazzo.Carta;
import partita.Partita;
import partita.Tavolo;
import stampa.Stampa;

public class Main {
	
	private static Partita partita;
	private static Tavolo tavolo;
	private static IA ia = new IA();
	private static Scanner scanner;
	
	public static void main(String[]args) throws InterruptedException{

		scanner = new Scanner(System.in);

		/* inizio della mano */
		Stampa.println("Realizzato da Daniele Mainella il 26/07/2019. Tutti i diritti sono riservati.");
		Stampa.println();
		Thread.sleep(1000);
		Stampa.println("Ciao, giochiamo a briscola!");
		Stampa.print("Salutami, e possiamo iniziare: ");
		String saluto = scanner.nextLine();
		Thread.sleep(200);
		Stampa.println(saluto + " anche a te!");
		Thread.sleep(1000);
		Stampa.print("Vuoi iniziare tu la mano (Scrivere 'si' o 'no')? ");
		String s = scanner.nextLine();
		while(!s.equals("si") && !s.equals("no")){
			Stampa.print("Vuoi iniziare tu la mano (Scrivere 'si' o 'no')? ");
			s = scanner.nextLine();
			Stampa.println();
		}
		partita=new Partita();
		if(s.equals("si"))
			partita.setIniziaIA(false);
		/* creazione della partita */
		partita.creaPartita();
		tavolo=partita.getTavolo();

		Thread.sleep(500);
		Stampa.println("\n======= LA CARTA DI BRISCOLA E': "+tavolo.getMazzo().getCarta(tavolo.getMazzo().getMazzo().size()-1)+" =======");	
		Stampa.println();
		
		while(!tavolo.getMazzo().isEmpty()){
			if (tavolo.getMazzo().getMazzo().size()<=2)
				Stampa.println("======= ULTIME DUE CARTE DEL MAZZO! La carta di briscola è "+ tavolo.getMazzo().getCarta(1).toString().toUpperCase()+" =======\n");
			if(partita.isPresoIo()){
				giocaManoIO();
			} else{
				giocaManoIA();
			}
		}
		
		while(tavolo.getCarteMie().size()>0){
			if(partita.isPresoIo()){
				giocaManoIO();
			} else{
				giocaManoIA();
			}
		}
		
		Stampa.println("======= FINE DELLA PARTITA =======");
		endGame();

	}

	private static void giocaManoIO() throws InterruptedException {
		Thread.sleep(200);
		manoIO();
		Thread.sleep(500);
		manoIA();
		Thread.sleep(750);
		partita.checkAndContinue();
		Thread.sleep(200);
	}

	private static void giocaManoIA() throws InterruptedException {
		Thread.sleep(200);
		manoIA();
		Thread.sleep(500);
		manoIO();
		Thread.sleep(200);
		partita.checkAndContinue();
		Thread.sleep(200);
	}
	
	private static void manoIA(){
		Carta cartaGiocataIA = null;
		if (partita.isPresoIo())
			cartaGiocataIA = ia.giocaDopo(partita, partita.getBriscola());
		else
			cartaGiocataIA = ia.giocaPrima(partita, partita.getBriscola());
		Stampa.println("Gioco: "+cartaGiocataIA.toString());
		partita.setGiocataIA(cartaGiocataIA);
	}
	
	private static void manoIO(){
		Stampa.println("\nLE TUE CARTE:");
		tavolo.stampaCarte();
		Stampa.print("\nCosa vuoi giocare...? ");
		String s = scanner.nextLine();
		List<String> carte = new ArrayList<String>();
		for(Carta c: tavolo.getCarteMie())
			carte.add(c.toString());
		while(! (carte.contains(s) || (isNumero(s) && Integer.parseInt(s)<=tavolo.getCarteMie().size()) ) ){
			Stampa.println("Scrivere una carta presente nelle tue carte, oppure il suo indice");
			s=scanner.nextLine();
		}
		partita.setGiocataMia(s);
	}

	private static void endGame() {
		Stampa.println("\nLe carte che hai collezionato sono:");
		Stampa.println(tavolo.getPuntiMiei().toString());
		int punteggio = tavolo.getPuntiMiei().contaPunti();
		Stampa.println("\n\nHai totalizzato "+punteggio+" punti\n");
		if (punteggio>60){
			Stampa.println("COMPLIMENTI! HAI VINTO!");
			Stampa.println();
			Stampa.println("Sei veramente bravo!");
			Stampa.println("Grazie per aver giocato con me!");
			Stampa.println("Spero mi concederai la rivincita un giorno...");
		}
		else if (punteggio<59){
			Stampa.println("HAHAHA! Ti ho battuto!");
			Stampa.println();
			Stampa.println("E' stata una bella partita ma alla fine ha vinto il più forte, cioè me xD");
			Stampa.println("Ritenta, magari la prossima volta sarai più fortunato!");
		}
		else if (punteggio==59){
			Stampa.println("HAI PERSO! HAI FATTO 59!!!");
			Stampa.println("Come si dice: 'Meglio star fuori quando piove...");
			Stampa.println("...che giocare a briscola e fare 59!'");
			Stampa.println("Ritenta, magari la prossima volta sarai più fortunato!");
		}
		else{
			Stampa.println("NON CI POSSO CREDERE: ABBIAMO PAREGGIATO!");
			Stampa.println("Strano, di solito è più facile leccarsi il gomito del braccio sinistro che pareggiare a briscola...");
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
