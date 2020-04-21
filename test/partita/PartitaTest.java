package partita;

//import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mazzo.Mazzo;

public class PartitaTest {
	
	private MainController partita;
	private Mazzo mazzo;

	@Before
	public void setUp() throws Exception {
		this.partita = new MainController();
		this.mazzo = new Mazzo();
	}

	@Test
	public void testCominciaIa() {
//		mazzo = this.partita.getMazzo();
		partita.creaPartita();
		mazzo.stampaMazzo();
		System.out.println();
		for (int i=0;i<partita.getTavolo().getCarteIA().size();i++)
			System.out.println(partita.getTavolo().getCartaIA(i).toString());
		System.out.println();
		for (int i=0;i<partita.getTavolo().getCarteMie().size();i++)
			System.out.println(partita.getTavolo().getCartaMie(i).toString());
	}
	
	@Test
	public void testCominciaIo() {
//		mazzo = this.partita.getMazzo();
		mazzo.stampaMazzo();
		System.out.println();
		partita.setIniziaIA(false);
		partita.creaPartita();
		mazzo.stampaMazzo();
		System.out.println();
		for (int i=0;i<partita.getTavolo().getCarteIA().size();i++)
			System.out.println(partita.getTavolo().getCartaIA(i).toString());
		System.out.println();
		for (int i=0;i<partita.getTavolo().getCarteMie().size();i++)
			System.out.println(partita.getTavolo().getCartaMie(i).toString());
	}

}
