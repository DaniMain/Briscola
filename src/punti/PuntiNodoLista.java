package punti;

import mazzo.*;

public class PuntiNodoLista{
	
	public Carta info;
	public PuntiNodoLista next;
	
	public PuntiNodoLista(Carta c, PuntiNodoLista p){
		info=c;
		next=p;
	}
	
}