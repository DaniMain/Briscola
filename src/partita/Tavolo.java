package partita;

import java.util.ArrayList;
import java.util.List;

import mazzo.*;
import punti.*;
import stampa.Stampa;

public class Tavolo {

	private List<Carta> carteIA;
//	private List<Carta> carteMie;
	private Carta[] carteMie;
	private Carta[] carteInGioco;
	private Carta[] ultimaPresa;
	private Carta cartaGiocataIO;
	private Carta cartaGiocataIA;
	private Punti puntiIA;
	private Punti puntiMiei;
	private Mazzo mazzo;
	private int indcgm, indcgs;

	public Tavolo() {
		this.carteIA = new ArrayList<Carta>();
//		this.carteMie = new ArrayList<Carta>();
		this.carteMie = new Carta[3];
		this.carteInGioco = new Carta[2];
		this.ultimaPresa = new Carta[2];
		this.puntiIA = new Punti();
		this.puntiMiei = new Punti();
		this.indcgm = 0;
		this.indcgs = 0;
	}

	public Carta getCartaGiocataIO() {
		return cartaGiocataIO;
	}

	public void setCartaGiocataIO(Carta cartaGiocataIO) {
		this.cartaGiocataIO = cartaGiocataIO;
	}

	public Carta getCartaGiocataIA() {
		return cartaGiocataIA;
	}

	public void setCartaGiocataIA(Carta cartaGiocataIA) {
		this.cartaGiocataIA = cartaGiocataIA;
	}

	public int getIndcgm() {
		return indcgm;
	}

	public void setIndcgm(int indcgm) {
		this.indcgm = indcgm;
	}

	public int getIndcgs() {
		return indcgs;
	}

	public void setIndcgs(int indcgs) {
		this.indcgs = indcgs;
	}

	public Punti getPuntiIA() {
		return puntiIA;
	}

	public void aggiungiPuntiIA(Carta c) {
		this.puntiIA.add(c);
	}

	public Punti getPuntiMiei() {
		return puntiMiei;
	}

	public void aggiungiPuntiMiei(Carta c) {
		this.puntiMiei.add(c);
	}

	public void setUltimaPresa(Carta[] ultimaPresa) {
		this.ultimaPresa = ultimaPresa;
	}

	public Carta[] getUltimaPresa() {
		return this.ultimaPresa;
	}

	public void setUltimaPresa(Carta c, int pos) {
		this.ultimaPresa[pos] = c;
	}

	public Carta[] getCarteInGioco() {
		return carteInGioco;
	}

	public void aggiungiCarteInGioco(Carta c, int pos) {
		this.carteInGioco[pos] = c;
	}

	public void aggiungiCarteInGiocoSua(Carta c) { // il primo posto è riservato alla carta sua
		this.carteInGioco[0] = c;
	}

	public void aggiungiCarteInGiocoMia(Carta c) { // il secondo posto è riservato alla carta mia
		this.carteInGioco[1] = c;
	}

	public List<Carta> getCarteIA() {
		return carteIA;
	}

	public Carta getCartaIA(int pos) {
		return carteIA.get(pos);
	}

	public void aggiungiCarteIA(Carta c) {
//		this.carteIA[pos] = c;
//		if (this.carteIA.size()>pos)
//			this.carteIA.remove(pos);
		this.carteIA.add(c);
	}

	public List<Carta> getCarteMie() {
//		return this.carteMie;
		List<Carta> carteArray = new ArrayList<>();
		for (Carta c : this.carteMie) {
			if (c != null) {
				carteArray.add(c);
			}
		}
		return carteArray;
	}

	public Carta[] getCarteMieReal() {
		return this.carteMie;
	}

	public Carta getCartaMie(int pos) {
//		return this.carteMie.get(pos);

//		return this.carteMie[pos];

		return this.getCarteMie().get(pos);
	}

	public void aggiungiCarteMie(Carta c) {
//		this.carteMie.add(c);

//		this.carteMie[pos] = c;
//		if(this.carteMie.size()>pos)
//			this.carteMie.remove(pos);

		for (int i = 0; i < this.carteMie.length; i++) {
			if (carteMie[i] == null) {
				carteMie[i] = c;
				return;
			}
		}
	}

	public Mazzo getMazzo() {
		return this.mazzo;
	}

	public void setMazzo(Mazzo m) {
		this.mazzo = m;
	}

	public void stampaCarteMie() {
		int i = 1;
		for (Carta c : this.carteMie) {
			if (c != null) {
				Stampa.println(i + ") " + c.toString());
				i++;
			}
		}
	}

	public void removeCartaMie(String cartaDaRimuovere) {
//		int pos = 0;
//		for(Carta c: this.carteMie){
//			if (c.toString().equals(string))
//				break;
//			pos++;
//		}
//		this.carteMie.remove(pos);

		int nullable;
		for (nullable = 0; nullable < this.carteMie.length; nullable++) {
			if (carteMie[nullable] != null) {
				break;
			}
		}
		for (int i = nullable; i < this.carteMie.length; i++) {
			if (carteMie[i].toString().equals(cartaDaRimuovere)) {
				carteMie[i] = null;
				break;
			}
		}
	}

	public void removeCartaMieReal(String cartaDaRimuovere) {
		for (int i = 0; i < this.carteMie.length; i++) {
			Carta c = this.carteMie[i];
			if (c != null && c.toString().equals(cartaDaRimuovere)) {
				carteMie[i] = null;
				return;
			}
		}
	}

	public void removeCartaIA(String string) {
		int pos = 0;
		for (Carta c : this.carteIA) {
			if (c.toString().equals(string))
				break;
			pos++;
		}
		this.carteIA.remove(pos);
	}

}
