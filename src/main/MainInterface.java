package main;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import partita.Partita;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MainInterface {

	protected Shell shell;
	private Partita partita;
	private Briscola briscola;
	private boolean ok;
	private Label lblCartaInGiocoSua,lblCartaInGiocoMia,lblNewLabel;
	private Button btnNewButton, btnNewButton_1, btnNewButton_2;

	public MainInterface(){
		this.partita = new Partita();
		this.briscola = new Briscola();
		this.ok = false;
		
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		this.lblCartaInGiocoSua = new Label(shell, SWT.NONE);
		lblCartaInGiocoSua.setVisible(false);
		lblCartaInGiocoSua.setBounds(211, 100, 109, 15);
		lblCartaInGiocoSua.setText("New Label");

		this.lblCartaInGiocoMia = new Label(shell, SWT.NONE);
		lblCartaInGiocoMia.setVisible(false);
		lblCartaInGiocoMia.setBounds(211, 131, 109, 15);
		lblCartaInGiocoMia.setText("New Label");
		
		this.lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setVisible(false);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(175, 165, 77, 15);
		lblNewLabel.setText("New Label");
		
		
		this.btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setVisible(false);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				partita.getTavolo().getCarteInGioco()[1]=briscola.lanciaIo(partita, 0);
				lblCartaInGiocoMia.setText(partita.getTavolo().getCarteInGioco()[1].toString());
				setOk(true);
			}
		});
		btnNewButton.setBounds(19, 212, 125, 25);
		btnNewButton.setText("New Button");

		this.btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setVisible(false);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				partita.getTavolo().getCarteInGioco()[1]=briscola.lanciaIo(partita, 1);
				lblCartaInGiocoMia.setText(partita.getTavolo().getCarteInGioco()[1].toString());
				setOk(true);
			}
		});
		btnNewButton_1.setBounds(150, 212, 138, 25);
		btnNewButton_1.setText("New Button");

		this.btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setVisible(false);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				partita.getTavolo().getCarteInGioco()[1]=briscola.lanciaIo(partita, 2);
				lblCartaInGiocoMia.setText(partita.getTavolo().getCarteInGioco()[1].toString());
				setOk(true);
			}
		});
		btnNewButton_2.setBounds(294, 212, 125, 25);
		btnNewButton_2.setText("New Button");
	}
	
	private boolean isOk(){
		return this.ok;
	}
	
	private void setOk(boolean ok){
		this.ok = ok;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//			MainInterface window = new MainInterface();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		

		Label lblVuoiIniziareTu = new Label(shell, SWT.NONE);
		lblVuoiIniziareTu.setAlignment(SWT.CENTER);
		lblVuoiIniziareTu.setBounds(150, 10, 138, 15);
		lblVuoiIniziareTu.setText("Vuoi iniziare tu la mano?");

		Label lblTiro = new Label(shell, SWT.NONE);
		lblTiro.setVisible(false);
		lblTiro.setAlignment(SWT.RIGHT);
		lblTiro.setBounds(150, 100, 55, 15);
		lblTiro.setText("Tiro:");
		
		Label lblLaTuaCarta = new Label(shell, SWT.NONE);
		lblLaTuaCarta.setVisible(false);
		lblLaTuaCarta.setAlignment(SWT.RIGHT);
		lblLaTuaCarta.setBounds(130, 131, 75, 15);
		lblLaTuaCarta.setText("La tua Carta:");
		
		Label lblBriscola = new Label(shell, SWT.NONE);
		lblBriscola.setVisible(false);
		lblBriscola.setAlignment(SWT.RIGHT);
		lblBriscola.setBounds(150, 42, 55, 25);
		lblBriscola.setText("Briscola:");
		
		Label lblCartaBriscola = new Label(shell, SWT.NONE);
		lblCartaBriscola.setVisible(false);
		lblCartaBriscola.setBounds(211, 42, 109, 15);
		lblCartaBriscola.setText("New Label");

		Button btnSi = new Button(shell, SWT.NONE);
		Button btnNo = new Button(shell, SWT.NONE);

		

		Button btnGuardaUltimaMano = new Button(shell, SWT.NONE);
		btnGuardaUltimaMano.setVisible(false);
		btnGuardaUltimaMano.setBounds(303, 121, 121, 25);
		btnGuardaUltimaMano.setText("Guarda ultima mano");

		btnSi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				partita.setIniziaIA(false);
				partita.setPresoIo(true);
				partita.creaPartita();
				lblVuoiIniziareTu.setVisible(false);
				btnSi.setVisible(false);
				btnNo.setVisible(false);
				lblTiro.setVisible(true);
				lblLaTuaCarta.setVisible(true);
				lblNewLabel.setVisible(true);
				lblCartaInGiocoSua.setVisible(true);
				lblCartaInGiocoMia.setVisible(true);
				lblBriscola.setVisible(true);
				lblCartaBriscola.setVisible(true);
				lblCartaBriscola.setText(partita.getTavolo().getMazzo().getCarta(39).toString());
				btnNewButton.setVisible(true);
				btnNewButton_1.setVisible(true);
				btnNewButton_2.setVisible(true);
				btnGuardaUltimaMano.setVisible(false);
				btnNewButton.setText(partita.getTavolo().getCartaMie(0).toString());
				btnNewButton_1.setText(partita.getTavolo().getCartaMie(1).toString());
				btnNewButton_2.setText(partita.getTavolo().getCartaMie(2).toString());
				try {
					gioca(partita);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSi.setBounds(93, 121, 75, 25);
		btnSi.setText("SI");

		btnNo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				partita.creaPartita();
				lblVuoiIniziareTu.setVisible(false);
				btnNo.setVisible(false);
				btnSi.setVisible(false);
				lblTiro.setVisible(true);
				lblLaTuaCarta.setVisible(true);
				lblNewLabel.setVisible(true);
				lblCartaInGiocoSua.setVisible(true);
				lblCartaInGiocoMia.setVisible(true);
				lblBriscola.setVisible(true);
				lblCartaBriscola.setVisible(true);
				lblCartaBriscola.setText(partita.getTavolo().getMazzo().getCarta(39).toString());
				btnNewButton.setVisible(true);
				btnNewButton_1.setVisible(true);
				btnNewButton_2.setVisible(true);
				btnGuardaUltimaMano.setVisible(false);
				btnNewButton.setText(partita.getTavolo().getCartaMie(0).toString());
				btnNewButton_1.setText(partita.getTavolo().getCartaMie(1).toString());
				btnNewButton_2.setText(partita.getTavolo().getCartaMie(2).toString());
				try {
					gioca(partita);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNo.setBounds(260, 121, 75, 25);
		btnNo.setText("NO");
		
	}

	public void gioca(Partita p) throws Exception{

		/* mega while per la partita vera e propria */
		while(p.getContatoreMazzo()!=38){

			if(!p.isPresoIo()){
				p.getTavolo().getCarteInGioco()[0]=briscola.lanciaIA(p);
				lblCartaInGiocoSua.setText(p.getTavolo().getCarteInGioco()[0].toString());
				if(isOk()==true){
					lblNewLabel.setText(briscola.ConfrontoPresa(p));
				}
			}
			else{
				if(isOk()==true){
					p.getTavolo().getCarteInGioco()[0]=briscola.lanciaIA(p);
					lblCartaInGiocoSua.setText(p.getTavolo().getCarteInGioco()[0].toString());
				}
				lblNewLabel.setText(briscola.ConfrontoPresa(p));
			}
			p.setContatoreMazzo(2);

		}

	}

}