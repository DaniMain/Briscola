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
			punteggio+=c.getPunteggio();
		return punteggio;
	}

}
