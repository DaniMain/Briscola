package main;

import java.util.Scanner;

import partita.Partita;
import partita.Tavolo;
import stampa.Stampa;

public class Main {
	
	private static Partita partita;
	private static Tavolo tavolo;
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
		Stampa.print("Vuoi iniziare tu la mano? Scrivere 'si' o 'no': ");
		String s = scanner.nextLine();
		while(!s.equals("si") && !s.equals("no")){
			Stampa.print("Vuoi iniziare tu la mano? Scrivere 'si' o 'no': ");
			s = scanner.nextLine();
			Stampa.println();
		}
		partita=new Partita();
		if(s.equals("si"))
			partita.setIniziaIA(false);
		/* creazione della partita */
		partita.creaPartita();
		tavolo=partita.getTavolo();
		
		while(!partita.getMazzo().isEmpty()){
			Stampa.println("LE TUE CARTE:");
			tavolo.stampaCarte();
		}
	}

}
