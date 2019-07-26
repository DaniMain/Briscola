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
	
	public static void main(String[]args){

		scanner = new Scanner(System.in);

		/* inizio della mano */
		Stampa.println("Realizzato da Daniele Mainella il 26/07/2019. Tutti i diritti sono riservati.");
		Stampa.println();
		Stampa.println("Ciao, giochiamo a briscola!");
		Stampa.print("Salutami, e possiamo iniziare: ");
		String saluto = scanner.nextLine();
		Stampa.println(saluto + " anche a te!");
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
		
		Stampa.println("\n======= LA CARTA DI BRISCOLA E': "+tavolo.getMazzo().getCarta(tavolo.getMazzo().getMazzo().size()-1)+" =======");	
		Stampa.println();
		
		while(!tavolo.getMazzo().isEmpty()){
			if (tavolo.getMazzo().getMazzo().size()<=2)
				Stampa.println("======= ULTIME DUE CARTE DEL MAZZO! La carta di briscola � "+ tavolo.getMazzo().getCarta(1).toString().toUpperCase()+" =======\n");
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

	private static void giocaManoIO() {
		Stampa.println("LE TUE CARTE:");
		tavolo.stampaCarte();
		Stampa.print("\nCosa vuoi giocare...? ");
		String s = scanner.nextLine();
		List<String> carte = new ArrayList<String>();
		for(Carta c: tavolo.getCarteMie())
			carte.add(c.toString());
		
		while(! (carte.contains(s) || Integer.parseInt(s)<=tavolo.getCarteMie().size())){
			Stampa.println("Scrivere una carta presente nelle tue carte, oppure il suo indice");
			s=scanner.nextLine();
		}
		partita.setGiocataMia(s);
		Carta cartaGiocataIA = ia.giocaDopo(partita, partita.getBriscola());
		Stampa.println("Gioco: "+cartaGiocataIA.toString());
		partita.setGiocataIA(cartaGiocataIA);
		partita.checkAndContinue();
	}

	private static void giocaManoIA() {
		Carta cartaGiocataIA = ia.giocaPrima(partita, partita.getBriscola());
		Stampa.println("Gioco: "+cartaGiocataIA.toString());
		partita.setGiocataIA(cartaGiocataIA);
		Stampa.println("\nLE TUE CARTE:");
		tavolo.stampaCarte();
		Stampa.print("\nCosa vuoi giocare...? ");
		String s = scanner.nextLine();
		List<String> carte = new ArrayList<String>();
		for(Carta c: tavolo.getCarteMie())
			carte.add(c.toString());
		while(! (carte.contains(s) || Integer.parseInt(s)<=tavolo.getCarteMie().size())){
			Stampa.println("Scrivere una carta presente nelle tue carte, oppure il suo indice");
			s=scanner.nextLine();
		}
		partita.setGiocataMia(s);
		partita.checkAndContinue();
	}

	private static void endGame() {
		Stampa.println("\nLe carte che hai totalizzato sono:");
		Stampa.println(tavolo.getPuntiMiei().toString());
		int punteggio = tavolo.getPuntiMiei().contaPunti();
		Stampa.println("\n\nHai totalizzato "+punteggio+" punti\n");
		if (punteggio>60){
			Stampa.println("COMPLIMENTI! HAI VINTO!");
			Stampa.println();
			Stampa.println("Sei veramente bravo!");
			Stampa.println("Grazie per aver giocato con me! ;) ");
			Stampa.println("Spero mi concederai la rivincita un giorno...");
		}
		else if (punteggio<59){
			Stampa.println("HAHAHA! Ti ho battuto!");
			Stampa.println();
			Stampa.println("E' stata una bella partita ma alla fine ha vinto il pi� forte, cio� me xD");
			Stampa.println("Ritenta, magari la prossima volta sarai pi� fortunato!");
		}
		else if (punteggio==59){
			Stampa.println("HAI PERSO! HAI FATTO 59!!!");
			Stampa.println("Come si dice: 'Meglio star fuori quando piove...");
			Stampa.println("...che giocare a briscola e fare 59!'");
			Stampa.println("Ritenta, magari la prossima volta sarai pi� fortunato!");
		}
		else{
			Stampa.println("NON CI POSSO CREDERE: ABBIAMO PAREGGIATO!");
			Stampa.println("Strano, di solito � pi� facile leccarsi il gomito che pareggiare a briscola...");
			Stampa.println("E' stato comunque un piacere aver giocato con te.");
			Stampa.println("La prossima volta vedremo chi la spunter�...rivincita?");
		}
	}
	
}
