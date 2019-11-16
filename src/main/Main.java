package main;

import java.util.Scanner;

import partita.Partita;
import partita.Tavolo;
import stampa.Stampa;

public class Main {
	
	private static Partita partita;
	private static Tavolo tavolo;
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
		Stampa.println("\n======= LA CARTA DI BRISCOLA E': " + 
				tavolo.getMazzo().getCarta(tavolo.getMazzo().getMazzo().size()-1) + " =======");	
		Stampa.println();
		
		/* esegui tante mani di gioco fino a che non finisce la partita*/ 
		while(partita.isInCorso() && tavolo.getCarteMie().size()>0){ //!tavolo.getMazzo().isEmpty()){
			if (tavolo.getMazzo().getMazzo().size()==2)
				Stampa.println("======= ULTIME DUE CARTE DEL MAZZO! La carta di briscola è " +
						tavolo.getMazzo().getCarta(1).toString().toUpperCase() + " =======\n");
			partita.giocaUnaMano();
		}
		
//		/* esegui le ultime tre mani */
//		while(partita.isInCorso() && tavolo.getCarteMie().size()>0){
//			giocaUnaMano();
//		}
		
		/* fine della partita: conteggio dei punti e mostra il vincitore */
		Stampa.println("======= FINE DELLA PARTITA =======");
		partita.endGame();

	}	
	
}
