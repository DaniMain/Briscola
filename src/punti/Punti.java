package punti;

import java.util.*;
import mazzo.*;

public class Punti {
	
	private List<Carta> punti;
	
	public Punti(){
		this.punti = new ArrayList<>();
	}

	public List<Carta> getPunti() {
		return punti;
	}

	public void setPunti(List<Carta> punti) {
		this.punti = punti;
	}
	
	public void add(Carta c){
		this.punti.add(c);
	}
	
	public int contaPunti(){
		int punteggio=0;
		for(Carta c: this.punti)
			if (c.getPunteggio()>0)
				punteggio+=c.getPunteggio();
		return punteggio;
	}
	
	@Override
	public String toString(){
		if(this.getPunti().isEmpty()) {
			return "Non hai collezionato nessuna carta!";
		}
		StringBuilder sb = new StringBuilder();
		for (Carta c: this.getPunti()){
			sb.append(c.toString());
			sb.append(", ");
		}
		int fine = sb.toString().length();
		int inizio = fine-2;
		sb.replace(inizio, fine, "");
		return sb.toString();
	}

	public String toStringTextArea(){
		if(this.getPunti().isEmpty()) {
			return "Non hai collezionato nessuna carta!";
		}
		StringBuilder sb = new StringBuilder();
		for (Carta c: this.getPunti()){
			sb.append(c.toString());
			sb.append("\n");
		}
		int fine = sb.toString().length();
		int inizio = fine-1;
		sb.replace(inizio, fine, "");
		return sb.toString();
	}

}
