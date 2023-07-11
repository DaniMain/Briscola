package main;

import java.util.List;

import mazzo.Carta;
import partita.Partita;

public class IA {
	
	public Carta giocaDopo(Partita p, String briscola){
		
		Carta cartaPiuAlta=p.getTavolo().getCartaIA(0),
				mia = p.getTavolo().getCartaGiocataIO(),
				sua=p.getTavolo().getCartaIA(0);
		Object[] briscoleInMano,stessoSemeInMano;
		int a, b, ii, indBriscolaPiuBassa, indStessoSemePiuAlto, indPunteggioPiuBasso, j, jj;
		List<Carta> carteIA = p.getTavolo().getCarteIA();
		boolean liscio,briscolaInsignificante;
		
		/* 1: se la mia carta � un liscio */
		if(!(mia.getSeme().equals(briscola)) && mia.getPunteggio()<0){
			a=contoStessoSeme(carteIA,mia.getSeme());
			b=contoBriscole(carteIA,briscola);
			if(a>0){// se ha carte dello stesso seme tira quella dal punteggio pi� alto
				if(a==1){// una carta dello stesso seme
				for(j=0;j<carteIA.size();j++)
					if(carteIA.get(j).getSeme().equals(mia.getSeme())){
						sua=carteIA.get(j);
						p.getTavolo().setIndcgs(j);
					}
				}
				else if(a==2){// due carte dello stesso seme
					jj=0;
					stessoSemeInMano=new Carta[2];
					for(j=0;j<carteIA.size();j++){
						if(carteIA.get(j).getSeme().equals(mia.getSeme())){
							stessoSemeInMano[jj]=carteIA.get(j);
							jj++;
						}
					}
					if(((Carta) stessoSemeInMano[0]).getPunteggio()> ((Carta) stessoSemeInMano[1]).getPunteggio()){
						sua=(Carta) stessoSemeInMano[0];
						for(ii=0;ii<carteIA.size();ii++)
							if(carteIA.get(ii).equals(sua))
								p.getTavolo().setIndcgs(ii);
					}
					else{
						sua=(Carta) stessoSemeInMano[1];
						for(ii=0;ii<carteIA.size();ii++)
							if(carteIA.get(ii).equals(sua))
								p.getTavolo().setIndcgs(ii);
					}
				}
				else if(a==3){// tre carte dello stesso seme
//					stessoSemeInMano=(Carta[]) carteIA.toArray();
					stessoSemeInMano=toArray(carteIA);
					indStessoSemePiuAlto=0;
					for(ii=1;ii<stessoSemeInMano.length;ii++)
						if(((Carta) stessoSemeInMano[ii]).getPunteggio()> ((Carta) stessoSemeInMano[indStessoSemePiuAlto]).getPunteggio())
							indStessoSemePiuAlto=ii;
					sua=(Carta) stessoSemeInMano[indStessoSemePiuAlto];
					for(j=0;j<carteIA.size();j++)
						if(carteIA.get(j).equals(sua))
							p.getTavolo().setIndcgs(j);
				}
			}
			
			else{// altrimenti se non ha una carta dello stesso seme
				liscio=liscio(carteIA,briscola);
				if(liscio){// se ha un liscio tira quello
					for(j=0;j<carteIA.size();j++)
						if(carteIA.get(j).getPunteggio()<0){
							sua=carteIA.get(j);
							p.getTavolo().setIndcgs(j);
						}
				}
				else if(b==1){// altirmenti se ha una briscola senza valore di punteggio tira quella
					briscolaInsignificante=false;
					for(j=0;j<carteIA.size();j++)// controllo se ho una briscola insignic�ficante
						if(carteIA.get(j).getSeme().equals(briscola) && carteIA.get(j).getPunteggio()<0)
							briscolaInsignificante=true;
					if(briscolaInsignificante)// se ho una briscola insignificante tiro quella
						for(j=0;j<carteIA.size();j++)
							if(carteIA.get(j).getSeme().equals(briscola) && carteIA.get(j).getPunteggio()<0){
								sua=carteIA.get(j);
								p.getTavolo().setIndcgs(j);
							}
					else{// altrimenti tiro la carta di punteggio pi� basso
						indPunteggioPiuBasso=0;
						for(ii=1;ii<carteIA.size();ii++)
							if(carteIA.get(ii).getPunteggio()<carteIA.get(indPunteggioPiuBasso).getPunteggio())
								indPunteggioPiuBasso=ii;
						sua=carteIA.get(indPunteggioPiuBasso);
						for(j=0;j<carteIA.size();j++)
							if(carteIA.get(j).equals(sua))
								p.getTavolo().setIndcgs(j);
					}
				}
				else if(b==2){// altrimenti se ha 2 briscole tira quella pi� bassa
					jj=0;
					briscoleInMano=new Carta[2];
					for(j=0;j<carteIA.size();j++){
						if(carteIA.get(j).getSeme().equals(briscola)){
							briscoleInMano[jj]=carteIA.get(j);
							jj++;
						}
					}
					if(((Carta) briscoleInMano[0]).getPunteggio()<((Carta)briscoleInMano[1]).getPunteggio()){// se il punteggio della prima � minore di quello della seconda tira quella
						sua=(Carta) briscoleInMano[0];
						for(ii=0;ii<carteIA.size();ii++)
							if(carteIA.get(ii).equals(sua))
								p.getTavolo().setIndcgs(ii);
					}
					else{
						sua=(Carta) briscoleInMano[1];
						for(ii=0;ii<carteIA.size();ii++)
							if(carteIA.get(ii).equals(sua))
								p.getTavolo().setIndcgs(ii);
					}
				}
				else if(b==3){// altrimenti se ha 3 briscole tira sempre quella pi� bassa
//					briscoleInMano=(Carta[]) carteIA.toArray();
					briscoleInMano=toArray(carteIA);
					indBriscolaPiuBassa=0;
					for(ii=1;ii<briscoleInMano.length;ii++)
						if(((Carta) briscoleInMano[ii]).getPunteggio()<((Carta) briscoleInMano[indBriscolaPiuBassa]).getPunteggio())
							indBriscolaPiuBassa=ii;
					sua=(Carta) briscoleInMano[indBriscolaPiuBassa];
					for(j=0;j<carteIA.size();j++)
						if(carteIA.get(j).equals(sua))
							p.getTavolo().setIndcgs(j);
				}
				else{// altrimenti tira la carta di valore di punteggio pi� basso
					indPunteggioPiuBasso=0;
					for(ii=1;ii<carteIA.size();ii++)
						if(carteIA.get(ii).getPunteggio()<carteIA.get(indPunteggioPiuBasso).getPunteggio())
							indPunteggioPiuBasso=ii;
					sua=carteIA.get(indPunteggioPiuBasso);
					for(j=0;j<carteIA.size();j++)
						if(carteIA.get(j).equals(sua))
							p.getTavolo().setIndcgs(j);
				}
			}
		}

		/* 2: se tiro una carta con punti */
		else if(mia.getPunteggio()>0){// altrimenti se la mia carta ha punti
			a=contoStessoSeme(carteIA,mia.getSeme());
			b=contoBriscole(carteIA,briscola);
			if(a>0){// se pu� ammazzarla lo fa con quella pi� alta
				if(a==1){// se ha una carta dello stesso seme controlla se pu� ammazzarla
					for(j=0;j<carteIA.size();j++)
						if(carteIA.get(j).getSeme().equals(mia.getSeme()))
							cartaPiuAlta=carteIA.get(j);
				}
				else if(a==2){// altrimenti se ha due carte dello stesso seme
					jj=0;
					stessoSemeInMano=new Carta[2];
					for(j=0;j<carteIA.size();j++){
						if(carteIA.get(j).getSeme().equals(mia.getSeme())){
							stessoSemeInMano[jj]=carteIA.get(j);
							jj++;
						}
					}
					if(((Carta) stessoSemeInMano[0]).getPunteggio()>((Carta) stessoSemeInMano[1]).getPunteggio())// se il punteggio della prima � maggiore di quello della seconda
						cartaPiuAlta=(Carta) stessoSemeInMano[0];
					else
						cartaPiuAlta=(Carta) stessoSemeInMano[1];
				}
				else if(a==3){// altrimenti se ha tre carte dello stesso seme
					cartaPiuAlta=null;
//					stessoSemeInMano=(Carta[]) carteIA.toArray();
					stessoSemeInMano=toArray(carteIA);
					indStessoSemePiuAlto=0;
					for(ii=1;ii<stessoSemeInMano.length;ii++)
						if(((Carta) stessoSemeInMano[ii]).getPunteggio()>((Carta) stessoSemeInMano[indStessoSemePiuAlto]).getPunteggio())
							indStessoSemePiuAlto=ii;
					cartaPiuAlta=(Carta) stessoSemeInMano[indStessoSemePiuAlto];
				}
				if(cartaPiuAlta.getPunteggio()>mia.getPunteggio()){// se la sua carta dello stesso seme � pi� alta tira quella
					sua=cartaPiuAlta;
					for(ii=0;ii<carteIA.size();ii++)
						if(carteIA.get(ii).equals(sua))
							p.getTavolo().setIndcgs(ii);
				}
				else{// controlla se ha una briscola
					if(b==1){
						for(j=0;j<carteIA.size();j++)
							if(carteIA.get(j).getSeme().equals(briscola) && carteIA.get(j).getPunteggio()<0){
								sua=carteIA.get(j);
								p.getTavolo().setIndcgs(j);
							}
					}
					else if(b==2){// altrimenti se ha 2 briscole tira quella pi� bassa
						jj=0;
						briscoleInMano=new Carta[2];
						for(j=0;j<carteIA.size();j++){
							if(carteIA.get(j).getSeme().equals(briscola)){
								briscoleInMano[jj]=carteIA.get(j);
								jj++;
							}
						}
						if(((Carta) briscoleInMano[0]).getPunteggio()<((Carta) briscoleInMano[1]).getPunteggio()){// se il punteggio della prima � minore di quello della seconda tira quella
							sua=(Carta) briscoleInMano[0];
							for(ii=0;ii<carteIA.size();ii++)
								if(carteIA.get(ii).equals(sua))
									p.getTavolo().setIndcgs(ii);
						}
						else{
							sua=(Carta) briscoleInMano[1];
							for(ii=0;ii<carteIA.size();ii++)
								if(carteIA.get(ii).equals(sua))
									p.getTavolo().setIndcgs(ii);
						}
					}
					else if(b==3){// altrimenti se ha 3 briscole tira sempre quella pi� bassa
//						briscoleInMano=(Carta[]) carteIA.toArray();
						briscoleInMano=toArray(carteIA);
						indBriscolaPiuBassa=0;
						for(ii=1;ii<briscoleInMano.length;ii++)
							if(((Carta) briscoleInMano[ii]).getPunteggio()<((Carta) briscoleInMano[indBriscolaPiuBassa]).getPunteggio())
								indBriscolaPiuBassa=ii;
						sua=(Carta) briscoleInMano[indBriscolaPiuBassa];
						for(j=0;j<carteIA.size();j++)
							if(carteIA.get(j).equals(sua))
								p.getTavolo().setIndcgs(j);
					}
					else{//altrimenti tira la carta dal valore pi� basso
						liscio=liscio(carteIA,briscola);
						if(liscio){// se ha un liscio tira quello
							for(j=0;j<carteIA.size();j++)
								if(carteIA.get(j).getPunteggio()<0){
									sua=carteIA.get(j);
									p.getTavolo().setIndcgs(j);
								}
						}
						else{
							indPunteggioPiuBasso=0;
							for(ii=1;ii<carteIA.size();ii++)
								if(carteIA.get(ii).getPunteggio()<carteIA.get(indPunteggioPiuBasso).getPunteggio())
									indPunteggioPiuBasso=ii;
							sua=carteIA.get(indPunteggioPiuBasso);
							for(j=0;j<carteIA.size();j++)
								if(carteIA.get(j).equals(sua))
									p.getTavolo().setIndcgs(j);
						}
					}
				}
			}
			else if(b>0){// altrimenti se ha una briscola tira quella pi� bassa
				if(b==1){
					for(j=0;j<carteIA.size();j++)
						if(carteIA.get(j).getSeme().equals(briscola) && carteIA.get(j).getPunteggio()<0){
							sua=carteIA.get(j);
							p.getTavolo().setIndcgs(j);
						}
				}
				else if(b==2){// altrimenti se ha 2 briscole tira quella pi� bassa
					jj=0;
					briscoleInMano=new Carta[2];
					for(j=0;j<carteIA.size();j++){
						if(carteIA.get(j).getSeme().equals(briscola)){
							briscoleInMano[jj]=carteIA.get(j);
							jj++;
						}
					}
					if(((Carta) briscoleInMano[0]).getPunteggio()<((Carta) briscoleInMano[1]).getPunteggio()){// se il punteggio della prima � minore di quello della seconda tira quella
						sua=(Carta) briscoleInMano[0];
						for(ii=0;ii<carteIA.size();ii++)
							if(carteIA.get(ii).equals(sua))
								p.getTavolo().setIndcgs(ii);
					}
					else{
						sua=(Carta) briscoleInMano[1];
						for(ii=0;ii<carteIA.size();ii++)
							if(carteIA.get(ii).equals(sua))
								p.getTavolo().setIndcgs(ii);
					}
				}
				else if(b==3){// altrimenti se ha 3 briscole tira sempre quella pi� bassa
//					briscoleInMano=(Carta[]) carteIA.toArray();
					briscoleInMano=toArray(carteIA);
					indBriscolaPiuBassa=0;
					for(ii=1;ii<briscoleInMano.length;ii++)
						if(((Carta) briscoleInMano[ii]).getPunteggio()<((Carta) briscoleInMano[indBriscolaPiuBassa]).getPunteggio())
							indBriscolaPiuBassa=ii;
					sua=(Carta) briscoleInMano[indBriscolaPiuBassa];
					for(j=0;j<carteIA.size();j++)
						if(carteIA.get(j).equals(sua))
							p.getTavolo().setIndcgs(j);
				}
			}
			else{// altrimenti tira la carta dal punteggio pi� basso
				liscio=liscio(carteIA,briscola);
				if(liscio){// se ha un liscio tira quello
					for(j=0;j<carteIA.size();j++)
						if(carteIA.get(j).getPunteggio()<0){
							sua=carteIA.get(j);
							p.getTavolo().setIndcgs(j);
						}
				}
				else{
					indPunteggioPiuBasso=0;
					for(ii=1;ii<carteIA.size();ii++)
						if(carteIA.get(ii).getPunteggio()<carteIA.get(indPunteggioPiuBasso).getPunteggio())
							indPunteggioPiuBasso=ii;
					sua=carteIA.get(indPunteggioPiuBasso);
					for(j=0;j<carteIA.size();j++)
						if(carteIA.get(j).equals(sua))
							p.getTavolo().setIndcgs(j);
				}
			}
		}

		/* 3: se tiro una briscola */
		else{// altrimenti tiro sicuramente una briscola
			a=contoStessoSeme(carteIA,mia.getSeme());
			b=contoBriscole(carteIA,briscola);
			liscio=liscio(carteIA,briscola);
			if(liscio){// se ha un liscio tira quello
				for(j=0;j<carteIA.size();j++)
					if(carteIA.get(j).getPunteggio()<0){
						sua=carteIA.get(j);
						p.getTavolo().setIndcgs(j);
					}
			}
			else if(b>=2){// altrimenti se ha due o pi� briscole tira quella pi� bassa
				if(b==2){// altrimenti se ha 2 briscole tira quella pi� bassa
					jj=0;
					briscoleInMano=new Carta[2];
					for(j=0;j<carteIA.size();j++){
						if(carteIA.get(j).getSeme().equals(briscola)){
							briscoleInMano[jj]=carteIA.get(j);
							jj++;
						}
					}
					if(((Carta) briscoleInMano[0]).getPunteggio()<((Carta) briscoleInMano[1]).getPunteggio()){// se il punteggio della prima � minore di quello della seconda tira quella
						sua=(Carta) briscoleInMano[0];
						for(ii=0;ii<carteIA.size();ii++)
							if(carteIA.get(ii).equals(sua))
								p.getTavolo().setIndcgs(ii);
					}
					else{
						sua=(Carta) briscoleInMano[1];
						for(ii=0;ii<carteIA.size();ii++)
							if(carteIA.get(ii).equals(sua))
								p.getTavolo().setIndcgs(ii);
					}
				}
				else if(b==3){// altrimenti se ha 3 briscole tira sempre quella pi� bassa
//					briscoleInMano=(Carta[]) carteIA.toArray();
					briscoleInMano=toArray(carteIA);
					indBriscolaPiuBassa=0;
					for(ii=1;ii<briscoleInMano.length;ii++)
						if(((Carta) briscoleInMano[ii]).getPunteggio()<((Carta) briscoleInMano[indBriscolaPiuBassa]).getPunteggio())
							indBriscolaPiuBassa=ii;
					sua=(Carta) briscoleInMano[indBriscolaPiuBassa];
					for(j=0;j<carteIA.size();j++)
						if(carteIA.get(j).equals(sua))
							p.getTavolo().setIndcgs(j);
				}
			}
			else if(b==1){// altrimenti se ha una briscola tira quella
				for(j=0;j<carteIA.size();j++)
					if(carteIA.get(j).getSeme().equals(briscola) && carteIA.get(j).getPunteggio()<0){
						sua=carteIA.get(j);
						p.getTavolo().setIndcgs(j);
					}
			}
			else{// altrimenti tira la carta dal punteggio pi� basso
				indPunteggioPiuBasso=0;
				for(ii=1;ii<carteIA.size();ii++)
					if(carteIA.get(ii).getPunteggio()<carteIA.get(indPunteggioPiuBasso).getPunteggio())
						indPunteggioPiuBasso=ii;
				sua=carteIA.get(indPunteggioPiuBasso);
				for(j=0;j<carteIA.size();j++)
					if(carteIA.get(j).equals(sua))
						p.getTavolo().setIndcgs(j);
			}
		}
		return sua;
		
	}
	
	public Carta giocaPrima(Partita p, String briscola){
		
		Carta sua=p.getTavolo().getCarteIA().get(0);
		Object[] briscoleInMano;
		int b,ii,indBriscolaPiuBassa,indPunteggioPiuBasso,j,jj;
		List<Carta> sue = p.getTavolo().getCarteIA();
		boolean liscio,haBriscola;
		
//		/* se inizia lui ed � la prima mano */
//		if(p.getIniziaIA()&&p.isPrimaMano()){
//			sua=sue[0];
//			mia=null;
////			indcgm=0;
////			indcgs=0;

		/* 1: se ha un liscio tira quello */
		liscio=liscio(sue,briscola);
		if(liscio){// se ha un liscio tira quello
			for(j=0;j<sue.size();j++)
				if(sue.get(j).getPunteggio()<0){
					sua=sue.get(j);
						p.getTavolo().setIndcgs(j);
				}
		}

		/* 2: se ha una briscola senza valore di punteggio tira quella */
		else{
			b=contoBriscole(sue,briscola);
			if(b>0){// se ha pi� di una briscola tira quella dal valore pi� basso
				if(b==1){// una briscola
					haBriscola=false;
					for(j=0;j<sue.size();j++)
						if(sue.get(j).getSeme().equals(briscola) && sue.get(j).getPunteggio()<0)// altrimenti se ha una briscola
							haBriscola=true;
					if(haBriscola){
						for(j=0;j<sue.size();j++){
							if(sue.get(j).getSeme().equals(briscola) && sue.get(j).getPunteggio()<0){
								sua=sue.get(j);
								p.getTavolo().setIndcgs(j);
							}
						}
					}
					else{// se non ha una briscola senza valore di punteggio tira la carta pi� bassa
						indPunteggioPiuBasso=0;
						for(ii=1;ii<sue.size();ii++)
							if(sue.get(ii).getPunteggio()<sue.get(indPunteggioPiuBasso).getPunteggio())
								indPunteggioPiuBasso=ii;
						sua=sue.get(indPunteggioPiuBasso);
						for(j=0;j<sue.size();j++)
							if(sue.get(j).equals(sua))
								p.getTavolo().setIndcgs(j);
					}
				}
				else if(b==2){// due briscole
					jj=0;
					briscoleInMano=new Carta[2];
					for(j=0;j<sue.size();j++){
						if(sue.get(j).getSeme().equals(briscola)){
							briscoleInMano[jj]=sue.get(j);
							jj++;
						}
					}
					if(((Carta) briscoleInMano[0]).getPunteggio()<((Carta) briscoleInMano[1]).getPunteggio()){// se il punteggio della prima � minore di quello della seconda tira quella
						sua=(Carta) briscoleInMano[0];
						for(ii=0;ii<sue.size();ii++)
							if(sue.get(ii).equals(sua))
								p.getTavolo().setIndcgs(ii);
					}
					else{
						sua=(Carta) briscoleInMano[1];
						for(ii=0;ii<sue.size();ii++)
							if(sue.get(ii).equals(sua))
								p.getTavolo().setIndcgs(ii);
					}
				}
				else if(b==3){// tre briscole
					briscoleInMano=sue.toArray();
					indBriscolaPiuBassa=0;
					for(ii=1;ii<briscoleInMano.length;ii++)
						if(((Carta) briscoleInMano[ii]).getPunteggio()<((Carta) briscoleInMano[indBriscolaPiuBassa]).getPunteggio())
							indBriscolaPiuBassa=ii;
					sua=(Carta) briscoleInMano[indBriscolaPiuBassa];
					for(j=0;j<sue.size();j++)
						if(sue.get(j).equals(sua))
							p.getTavolo().setIndcgs(j);
				}
			}

		/* 3: altrimenti tira la carta di valore di punteggio pi� bassa */
			else{
				indPunteggioPiuBasso=0;
				for(ii=1;ii<sue.size();ii++)
					if(sue.get(ii).getPunteggio()<sue.get(indPunteggioPiuBasso).getPunteggio())
						indPunteggioPiuBasso=ii;
				sua=sue.get(indPunteggioPiuBasso);
				for(j=0;j<sue.size();j++)
					if(sue.get(j).equals(sua))
						p.getTavolo().setIndcgs(j);
			}
		}
		
		return sua;
			
}
	
	public int indiceCartaGiocataIA(Partita p){
		int i=0,j;
		for (j=0;j<p.getTavolo().getCarteIA().size();j++)
			if (p.getTavolo().getCarteInGioco()[0].equals(p.getTavolo().getCarteIA().get(j))||p.getTavolo().getCarteInGioco()[1].equals(p.getTavolo().getCarteIA().get(j)))
				return i = j;
		return i;
	}
	
	public int contoStessoSeme(List<Carta> sue, String seme){
		int l=0;
		for(Carta c: sue)
			if(c.getSeme().equals(seme))
				l++;
		return l;
	}
	
	public int contoBriscole(List<Carta> sue, String briscola){
		int b=0;
		for(Carta c: sue)
			if(c.getSeme().equals(briscola))
				b++;
		return b;
	}
	
	public boolean liscio(List<Carta> sue, String briscola){
		boolean liscio = false;
		for(Carta c: sue)
			if(!(c.getSeme().equals(briscola)) && (c.getPunteggio()<0))// controlla se ha un liscio
				liscio=true;
		return liscio;
	}
	
	public Carta[] toArray(List<Carta> carte) {
		Carta[] carteArray = new Carta[carte.size()];
		int i=0;
		for(Carta c: carte) {
			carteArray[i] = c;
			i++;
		}
		return carteArray;
	}

}
