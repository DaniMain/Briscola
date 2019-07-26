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
	private static IA ia;
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
		
		Stampa.println();
		Stampa.println("======= LA CARTA DI BRISCOLA E': "+tavolo.getMazzo().getCarta(tavolo.getMazzo().getMazzo().size()-1)+" =======");	
		Stampa.println();
		
		while(!tavolo.getMazzo().isEmpty()){
			if(partita.isPresoIo()){
				giocaManoIO();
			} else{
				giocaManoIA();
			}
			
			
			
//			Stampa.println("LE TUE CARTE:");
//			tavolo.stampaCarte();
			break;
		}
	}

	private static void giocaManoIO() {
		Stampa.println("LE TUE CARTE:");
		tavolo.stampaCarte();
		String s = scanner.nextLine();
		List<String> carte = new ArrayList<String>();
		for(Carta c: tavolo.getCarteMie())
			carte.add(c.toString());		
		while(! (carte.contains(s) || s.equals("1") || s.equals("2") || s.equals("3"))){
			Stampa.println("Scrivere una carta presente nelle tue carte, oppure il suo indice");
			s=scanner.nextLine();
		}
		partita.setGiocataMia(s);
		Carta cartaGiocataIA = ia.giocaDopo(partita, partita.getBriscola());
		Stampa.println("Gioco: "+cartaGiocataIA.toString());
		partita.setGiocataIA(cartaGiocataIA);
		partita.controllaGiocata();
	}

	private static void giocaManoIA() {
		
		Stampa.println("LE TUE CARTE:");
		tavolo.stampaCarte();
	}

}
