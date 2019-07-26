package partita;

import mazzo.*;
import punti.*;

public class Partita {
	
	private Mazzo mazzo;
	private Tavolo tavolo;
	private int contatoreMazzo;
	private boolean iniziaIA,presoIo,fineMazzo;
	private String briscola;
	private Punti puntiMiei,puntiIA;

	public Partita(){
		this.mazzo = new Mazzo();
		this.mazzo.mischia();
		this.tavolo = new Tavolo();
		this.tavolo.setMazzo(mazzo);
		this.iniziaIA = true;
		this.presoIo = false;
//		this.contatoreMazzo = 0;
//		this.fineMazzo = false;
		this.puntiMiei = new Punti();
		this.puntiIA = new Punti();
	}
	
	private void creaPartitaManoIA(){
		this.tavolo.aggiungiCarteIA(this.mazzo.pop(), 0);
		this.tavolo.aggiungiCarteMie(this.mazzo.pop(), 0);
		this.tavolo.aggiungiCarteIA(this.mazzo.pop(), 1);
		this.tavolo.aggiungiCarteMie(this.mazzo.pop(), 1);
		this.tavolo.aggiungiCarteIA(this.mazzo.pop(), 2);
		this.tavolo.aggiungiCarteMie(this.mazzo.pop(), 2);
		this.briscola=this.mazzo.getCarta(0).getSeme();
		this.mazzo.briscolaAllaFine();
//		contatoreMazzo = 6;
//		setBriscola(this.mazzo.getCarta(39).getSeme());
	}

	private void creaPartitaManoIo(){
		this.tavolo.aggiungiCarteMie(this.mazzo.pop(), 0);
		this.tavolo.aggiungiCarteIA(this.mazzo.pop(), 0);
		this.tavolo.aggiungiCarteMie(this.mazzo.pop(), 1);
		this.tavolo.aggiungiCarteIA(this.mazzo.pop(), 1);
		this.tavolo.aggiungiCarteMie(this.mazzo.pop(), 2);
		this.tavolo.aggiungiCarteIA(this.mazzo.pop(), 2);
		this.briscola=this.mazzo.getCarta(0).getSeme();
		this.mazzo.briscolaAllaFine();
		presoIo=true;
//		contatoreMazzo = 6;
//		setBriscola(this.mazzo.getCarta(39).getSeme());
	}

	public Punti getPuntiMiei() {
		return puntiMiei;
	}

	public void setPuntiMiei(Carta c) {
		this.puntiMiei.add(c);
	}

	public Punti getPuntiIA() {
		return puntiIA;
	}

	public void setPuntiIA(Carta c) {
		this.puntiIA.add(c);
	}

	public String getBriscola() {
		return briscola;
	}

	public void setBriscola(String briscola) {
		this.briscola = briscola;
	}

	public int getContatoreMazzo() {
		return contatoreMazzo;
	}

	public void setContatoreMazzo(int contatoreMazzo) {
		this.contatoreMazzo += contatoreMazzo;
	}

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

	public Mazzo getMazzo(){
		return this.mazzo;
	}

	public Tavolo getTavolo(){
		return this.tavolo;
	}
	
	public boolean getIniziaIA(){
		return this.iniziaIA;
	}
	
	public void setIniziaIA(boolean iniziaIA){
		this.iniziaIA = iniziaIA;
	}
	
	public void creaPartita(){
		if (this.iniziaIA)
			creaPartitaManoIA();
		else
			creaPartitaManoIo();
	}

}
