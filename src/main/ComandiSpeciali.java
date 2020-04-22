package main;

import java.util.ArrayList;
import java.util.List;

import mazzo.Carta;
import partita.MainController;
import stampa.Stampa;

public class ComandiSpeciali {
	
	private List<String> comandiSpeciali = new ArrayList<>();
	
	public ComandiSpeciali(){
		comandiSpeciali.add("briscola");
		comandiSpeciali.add("le sue carte");
		comandiSpeciali.add("fine");
	}
	
	public List<String> getComandiSpeciali(){
		return this.comandiSpeciali;
	}

	public void esegui(MainController partita, String comando) {
		if(comando.equals("briscola"))
			briscola(partita);
		else if(comando.equals("le sue carte"))
			le_sue_carte(partita);
		else if(comando.equals("fine"))
			finisci_partita(partita);
	}
	
	public void briscola(MainController partita){
		Stampa.print("La carta di briscola è: ");
		Stampa.println(partita.getBriscola().toString());
	}
	
	public void le_sue_carte(MainController partita){
		for(Carta c: partita.getTavolo().getCarteIA()){
			Stampa.println(c.toString());
		}
	}
	
	public void finisci_partita(MainController partita){
		partita.setGiocataMia(null);
		partita.setFinePartita();
	}

}
