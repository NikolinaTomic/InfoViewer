package model;

import java.util.ArrayList;


/**
 * Klasa <code>PaketModel</code> predstavlja klasu Paket
 * sa listom svih potomaka paketa i tabela.
 * 
 * @author Luka
 * @version %I%, %G%
 */
public class PaketModel{
	private Paket paket;
	private ArrayList<Object> deca;
	
	public PaketModel() {
		paket=new Paket();
		deca=new ArrayList<Object>();
	}
	public Paket getPaket() {
		return paket;
	}
	public void setPaket(Paket paket) {
		this.paket = paket;
	}
	public ArrayList<Object> getDeca() {
		return deca;
	}
	public void setDeca(ArrayList<Object> deca) {
		this.deca = deca;
	}
	
	@Override
	public String toString() {
		return "Paket [imePaketa=" + paket.getImePaketa() + ", roditelji=" + paket.getRoditelji() + "]";
	}


}