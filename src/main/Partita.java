package main;

import mazzo.Carta;
import partita.Tavolo;

public interface Partita {
	
	public Tavolo getTavolo();
	public Carta getBriscola();
	public void setBriscola(Carta briscola);
	public boolean isFineMazzo();
	public void setFineMazzo(boolean fineMazzo);
	public boolean isPresoIo();
	public void setPresoIo(boolean presoIo);
	public boolean getIniziaIA();
	public void setIniziaIA(boolean iniziaIA);
	public boolean isInCorso();
	public IA getIa();
	public void setIa(IA ia);
	public void finePartita();
	public void creaPartita();
	public void setGiocataMia(String s);
	public void setGiocataIA(Carta cartaGiocataIA);
	public void checkAndContinue();
	public void giocaUnaMano() throws InterruptedException;
	public void endGame() throws InterruptedException;
	
}
