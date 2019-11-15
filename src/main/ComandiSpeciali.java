package main;

import java.util.ArrayList;
import java.util.List;

import mazzo.Carta;
import partita.Partita;
import stampa.Stampa;

public class ComandiSpeciali {
	
	private List<String> comandiSpeciali = new ArrayList<>();
	
	public ComandiSpeciali(){
		comandiSpeciali.add("briscola");
		comandiSpeciali.add("le sue carte");
	}
	
	public List<String> getComandiSpeciali(){
		return this.comandiSpeciali;
	}

	public void esegui(Partita partita, String comando) {
		if(comando.equals("briscola"))
			briscola(partita);
		else if(comando.equals("le sue carte"))
			le_sue_carte(partita);
	}
	
	public void briscola(Partita partita){
		Stampa.print("La carta di briscola è: ");
		Stampa.println(partita.getBriscola().toString());
	}
	
	public void le_sue_carte(Partita partita){
		for(Carta c: partita.getTavolo().getCarteIA()){
			Stampa.println(c.toString());
		}
	}

}
