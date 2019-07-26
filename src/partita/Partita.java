package partita;

import mazzo.*;
//import punti.*;

public class Partita {
	
//	private Mazzo mazzo;
	private Tavolo tavolo;
//	private int contatoreMazzo;
	private boolean iniziaIA,presoIo,fineMazzo;
	private String briscola;
//	private Punti puntiMiei,puntiIA;

	public Partita(){
		this.tavolo = new Tavolo();
		Mazzo mazzo = new Mazzo();
		mazzo.mischia();
		this.tavolo.setMazzo(mazzo);
		//per default si presume che inizia l'IA
		this.iniziaIA = true;
		this.presoIo = false;
//		this.contatoreMazzo = 0;
//		this.fineMazzo = false;
//		this.puntiMiei = new Punti();
//		this.puntiIA = new Punti();
	}
	
	private void creaPartitaManoIA(){
		Mazzo mazzo = this.tavolo.getMazzo();
		this.tavolo.aggiungiCarteIA(mazzo.pop(), 0);
		this.tavolo.aggiungiCarteMie(mazzo.pop(), 0);
		this.tavolo.aggiungiCarteIA(mazzo.pop(), 1);
		this.tavolo.aggiungiCarteMie(mazzo.pop(), 1);
		this.tavolo.aggiungiCarteIA(mazzo.pop(), 2);
		this.tavolo.aggiungiCarteMie(mazzo.pop(), 2);
		this.briscola=mazzo.getCarta(0).getSeme();
		mazzo.briscolaAllaFine();
//		contatoreMazzo = 6;
//		setBriscola(this.mazzo.getCarta(39).getSeme());
	}

	private void creaPartitaManoIo(){
		Mazzo mazzo = this.tavolo.getMazzo();
		this.tavolo.aggiungiCarteMie(mazzo.pop(), 0);
		this.tavolo.aggiungiCarteIA(mazzo.pop(), 0);
		this.tavolo.aggiungiCarteMie(mazzo.pop(), 1);
		this.tavolo.aggiungiCarteIA(mazzo.pop(), 1);
		this.tavolo.aggiungiCarteMie(mazzo.pop(), 2);
		this.tavolo.aggiungiCarteIA(mazzo.pop(), 2);
		this.briscola=mazzo.getCarta(0).getSeme();
		mazzo.briscolaAllaFine();
		presoIo=true;
//		contatoreMazzo = 6;
//		setBriscola(this.mazzo.getCarta(39).getSeme());
	}

//	public Punti getPuntiMiei() {
//		return puntiMiei;
//	}
//
//	public void setPuntiMiei(Carta c) {
//		this.puntiMiei.add(c);
//	}
//
//	public Punti getPuntiIA() {
//		return puntiIA;
//	}
//
//	public void setPuntiIA(Carta c) {
//		this.puntiIA.add(c);
//	}

	public String getBriscola() {
		return briscola;
	}

	public void setBriscola(String briscola) {
		this.briscola = briscola;
	}

//	public int getContatoreMazzo() {
//		return contatoreMazzo;
//	}
//
//	public void setContatoreMazzo(int contatoreMazzo) {
//		this.contatoreMazzo += contatoreMazzo;
//	}

	public boolean isFineMazzo() {
		return fineMazzo;
	}

	public void setFineMazzo(boolean fineMazzo) {
		this.fineMazzo = fineMazzo;
	}
	
	public boolean isPresoIo() {
		return presoIo;
	}

	public void setPresoIo(boolean presoIo) {
		this.presoIo = presoIo;
	}

//	public Mazzo getMazzo(){
//		return this.mazzo;
//	}

	public Tavolo getTavolo(){
		return this.tavolo;
	}
	
	public boolean getIniziaIA(){
		return this.iniziaIA;
	}
	
	public void setIniziaIA(boolean iniziaIA){
		this.iniziaIA = iniziaIA;
		this.presoIo = !iniziaIA;
	}
	
	public void creaPartita(){
		if (this.iniziaIA)
			creaPartitaManoIA();
		else
			creaPartitaManoIo();
	}

	public void setGiocataMia(String s) {
		if(s.length()==1){
			Carta cartaGiocataIO = tavolo.getCartaMie(Integer.parseInt(s));
			tavolo.setCartaGiocataIA(cartaGiocataIO);
			tavolo.removeCartaMie(cartaGiocataIO.toString());
		}
		else{
			Carta cartaGiocataIO = null;
			for(Carta c: this.tavolo.getCarteMie())
				if (c.toString().equals(s)){
					cartaGiocataIO = c;
					break;
				}
			tavolo.setCartaGiocataIA(cartaGiocataIO);
			tavolo.removeCartaMie(cartaGiocataIO.toString());			
		}
	}

	public void setGiocataIA(Carta cartaGiocataIA) {
		this.tavolo.setCartaGiocataIA(cartaGiocataIA);
		for(Carta c: this.tavolo.getCarteMie())
			if (c.toString().equals(cartaGiocataIA.toString())){
				cartaGiocataIA = c;
				break;
			}
		tavolo.removeCartaIA(cartaGiocataIA.toString());
	}

	public void controllaGiocata() {
		if (this.presoIo){
			
		}else{
			
		}
	}

}
