package mazzo;

import java.util.ArrayList;
import java.util.List;
import stampa.Stampa;

public class Mazzo{
	
	private List<Carta> mazzo;
	
	public Mazzo(){
		this.mazzo = this.completo();
	}
	
	public List<Carta> completo(){
		
		List<Carta> mazzo = new ArrayList<Carta>();
		
		int uno=1;
		int asso=11;
		int tre=10;
		int re=4;
		int cavallo=3;
		int fante=2;
		int sette=-1; // il punteggio è negativo per capire se, ad esempio, il 7 prende sul 2
		int sei=-2;
		int cinque=-3;
		int quattro=-4;
		int due=-5;
		String a="asso";
		String b="2";
		String c="3";
		String d="4";
		String e="5";
		String f="6";
		String g="7";
		String h="fante";
		String i="cavallo";
		String l="re";
		String denari="denari";
		String spade="spade";
		String coppe="coppe";
		String bastoni="bastoni";
		mazzo.add(new Carta(a,denari,uno,asso));
		mazzo.add(new Carta(b,denari,uno++,due));
		mazzo.add(new Carta(c,denari,uno++,tre));
		mazzo.add(new Carta(d,denari,uno++,quattro));
		mazzo.add(new Carta(e,denari,uno++,cinque));
		mazzo.add(new Carta(f,denari,uno++,sei));
		mazzo.add(new Carta(g,denari,uno++,sette));
		mazzo.add(new Carta(h,denari,uno++,fante));
		mazzo.add(new Carta(i,denari,uno++,cavallo));
		mazzo.add(new Carta(l,denari,uno++,re));
		mazzo.add(new Carta(a,spade,uno++,asso));
		mazzo.add(new Carta(b,spade,uno++,due));
		mazzo.add(new Carta(c,spade,uno++,tre));
		mazzo.add(new Carta(d,spade,uno++,quattro));
		mazzo.add(new Carta(e,spade,uno++,cinque));
		mazzo.add(new Carta(f,spade,uno++,sei));
		mazzo.add(new Carta(g,spade,uno++,sette));
		mazzo.add(new Carta(h,spade,uno++,fante));
		mazzo.add(new Carta(i,spade,uno++,cavallo));
		mazzo.add(new Carta(l,spade,uno++,re));
		mazzo.add(new Carta(a,coppe,uno++,asso));
		mazzo.add(new Carta(b,coppe,uno++,due));
		mazzo.add(new Carta(c,coppe,uno++,tre));
		mazzo.add(new Carta(d,coppe,uno++,quattro));
		mazzo.add(new Carta(e,coppe,uno++,cinque));
		mazzo.add(new Carta(f,coppe,uno++,sei));
		mazzo.add(new Carta(g,coppe,uno++,sette));
		mazzo.add(new Carta(h,coppe,uno++,fante));
		mazzo.add(new Carta(i,coppe,uno++,cavallo));
		mazzo.add(new Carta(l,coppe,uno++,re));
		mazzo.add(new Carta(a,bastoni,uno++,asso));
		mazzo.add(new Carta(b,bastoni,uno++,due));
		mazzo.add(new Carta(c,bastoni,uno++,tre));
		mazzo.add(new Carta(d,bastoni,uno++,quattro));
		mazzo.add(new Carta(e,bastoni,uno++,cinque));
		mazzo.add(new Carta(f,bastoni,uno++,sei));
		mazzo.add(new Carta(g,bastoni,uno++,sette));
		mazzo.add(new Carta(h,bastoni,uno++,fante));
		mazzo.add(new Carta(i,bastoni,uno++,cavallo));
		mazzo.add(new Carta(l,bastoni,uno++,re));
		return mazzo;
		
	}
	
	public void setMazzo(List<Carta> c){
		this.mazzo = c;
	}

	public List<Carta> mischiato(){
		int i,j,n,m;
		Object[] carte = this.completo().toArray();
		for(i=0;i<1000;i++){
			n=(int)(Math.random()*41);
			m=n;
			for(j=0;j<n && m<carte.length;j++){
				scambia(carte,j,m);
				m++;
			}			
		}
		
		List<Carta> mazzoMischiato = new ArrayList<>();
		for (i=0;i<carte.length;i++)
			mazzoMischiato.add((Carta) carte[i]);
		return mazzoMischiato;
	}
	
	public void mischia(){
		List<Carta> mazzoMischiato = this.mischiato();
		this.setMazzo(mazzoMischiato);
	}
	
	public void scambia(Object[] c, int i, int j){
		Object s = c[i];
		c[i] = c[j];
		c[j] = s;
	}

//	// per fare i test
//
//	public static void main (String[]args){
//		Mazzo m = new Mazzo();
//		Carta[] c = m.mischiato();
//		int i;
//		for(i=0;i<c.length;i++)
//			System.out.println(c[i].toString());
//	}

	public List<Carta> getMazzo() {
		return this.mazzo;
	}
	
	public void briscolaAllaFine(){
//		int i;
		Carta briscola = this.pop();
//		for(i=6;i<39;i++)
//			this.mazzo[i]=mazzo[i+1];
//		this.mazzo[39]=briscola;
		this.mazzo.add(briscola);
	}
	
	public void stampaMazzo(){
//		for (int i=0;i<this.mazzo.length;i++)
//			System.out.println(this.mazzo[i].toString());
		for (Carta c: this.mazzo)
			Stampa.println(c.toString());
	}

	public Carta getCarta(int i) {
		return this.mazzo.get(i);
	}
	
	public Carta pop(){
		Carta first = this.mazzo.get(0);
		this.mazzo.remove(0);
		return first;
	}

	public boolean isEmpty() {
		return this.mazzo.size()==0;
	}
	
}