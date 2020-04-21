package main;

import mazzo.Carta;

public class Briscola {
	
	private IA IA;
	
	public Briscola(){
		IA = new IA();
	}
	
	public Carta lanciaIo(Partita p, int i){
		return p.getTavolo().getCartaMie(i);
	}
	
	public Carta lanciaIA(Partita p){
		Carta ia;
		if(p.isPresoIo())
			ia = IA.giocaDopo(p, p.getBriscola().getSeme());
		else
			ia = IA.giocaPrima(p, p.getBriscola().getSeme());
		return ia;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public String ConfrontoPresa(Partita p) throws Exception {
		String presoIo="Hai preso tu",presoIA="Ho preso io",presa;
		if (p.getTavolo().getCarteInGioco()[0]==null||p.getTavolo().getCarteInGioco()[1]==null)
			throw new NullPointerException();
		if(p.isPresoIo()){
			if(p.getTavolo().getCarteInGioco()[1].getSeme().equals(p.getTavolo().getCarteInGioco()[0].getSeme())){
				if(p.getTavolo().getCarteInGioco()[1].getPunteggio()>p.getTavolo().getCarteInGioco()[0].getPunteggio()){
					presaIo(p,p.getTavolo().getCarteInGioco());
					presa = presoIo;
				}
				else{
					presaIA(p,p.getTavolo().getCarteInGioco());
					presa = presoIA;
				}
			}
			else{
				if(p.getTavolo().getCarteInGioco()[0].getSeme().equals(p.getBriscola())){
					presaIA(p,p.getTavolo().getCarteInGioco());
					presa = presoIA;
				}
				else{
					presaIo(p,p.getTavolo().getCarteInGioco());
					presa = presoIo;
				}
			}
		}
		else{
			if(p.getTavolo().getCarteInGioco()[1].getSeme().equals(p.getTavolo().getCarteInGioco()[0].getSeme())){
				if(p.getTavolo().getCarteInGioco()[1].getPunteggio()>p.getTavolo().getCarteInGioco()[0].getPunteggio()){
					presaIo(p,p.getTavolo().getCarteInGioco());
					presa = presoIo;
				}
				else{
					presaIA(p,p.getTavolo().getCarteInGioco());
					presa = presoIA;
				}
			}
			else{
				if(p.getTavolo().getCarteInGioco()[1].getSeme().equals(p.getBriscola())){
					presaIo(p,p.getTavolo().getCarteInGioco());
					presa = presoIo;
				}
				else{
					presaIA(p,p.getTavolo().getCarteInGioco());
					presa = presoIA;
				}
			}
		}
		return presa;
	}
	
	public void presaIo(Partita p,Carta[] c){
//		p.getPuntiMiei().add(c[0]);
//		p.getPuntiIA().add(c[1]);
	}
	
	public void presaIA(Partita p,Carta[] c){
//		p.getPuntiIA().add(c[0]);
//		p.getPuntiMiei().add(c[1]);
	}

}
