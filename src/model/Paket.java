package model;

/**
 * Klasa <code>Paket</code> predstavlja podatke
 * o paketima koji su potrebni prilikom mapiranja
 * JSON �eme na objekat Paket.
 * 
 * @author Luka
 * @version %I%, %G%
 */
public class Paket {
	private String oznaka;
	private String tip;
	public Paket(String ime) {
		imePaketa=ime;
		roditelji="";
	}
	public Paket() {}
	
	private String imePaketa;
	private String roditelji;
	public String getImePaketa() {
		return imePaketa;
	}
	public void setImePaketa(String imePaketa) {
		this.imePaketa = imePaketa;
	}
	
	public String getRoditelji() {
		return roditelji;
	}
	public void setRoditelji(String roditelji) {
		this.roditelji = roditelji;
	}
	@Override
	public String toString() {
		return "Paket [imePaketa=" + imePaketa + ", roditelji=" + roditelji + "]";
	}
	public String getOznaka() {
		return oznaka;
	}
	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	

}
