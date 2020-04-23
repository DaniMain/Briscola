package mazzo;

public class Carta{
	
	private String valore;
	private String seme;
	private int id;
	private int punteggio;
	
	public Carta(String val, String se, int iden, int punt){
		this.valore=val;
		this.seme=se;
		this.id=iden;
		this.punteggio=punt;
	}
	
	public String getValore(){
		return this.valore;
	}
	
	public String getSeme(){
		return this.seme;
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getPunteggio(){
		return this.punteggio;
	}
	
	public void setValore(String v){
		this.valore=v;
	}
	
	public void setSeme(String s){
		this.seme=s;
	}
	
	public void setId(int i){
		this.id=i;
	}
	
	@Override
	public boolean equals(Object o){
		Carta c = (Carta) o;
		return (this.getValore().equals(c.getValore())) && (this.getSeme().equals(c.getSeme()));
	}
	
	@Override
	public int hashCode(){
		return this.valore.hashCode()+this.seme.hashCode()+this.id+this.punteggio;
	}
	
	@Override
	public String toString(){
		return this.getValore()+" di "+this.getSeme();
	}
	
	public String toStringPath() {
		return "/carte/" + this.toString() + ".png";
	}

	public boolean isBetter(Carta carta, String briscola) {
		if (!this.getSeme().equals(carta.getSeme()) & !carta.getSeme().equals(briscola))
			return true;
		else if (this.getSeme().equals(carta.getSeme()) & this.getPunteggio()>carta.getPunteggio())
			return true;
		else if (this.getSeme().equals(briscola) & !carta.getSeme().equals(briscola))
			return true;
		else
			return false;
	}
	
}



