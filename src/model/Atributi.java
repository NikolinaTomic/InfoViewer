package model;

/**
 * Klasa <code>Atributi</code> predstavlja podatke
 * o tabelama (deo klase Tabela) koji su potrebni prilikom mapiranja
 * JSON �eme na objekat Tabela.
 * 
 * @author Luka
 * @version %I%, %G%
 */
public class Atributi {
	
	private String name;
	private String code;
	private String type;
	private int maxString;
	private boolean is_mandatory;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isIs_mandatory() {
		return is_mandatory;
	}

	public void setIs_mandatory(boolean is_mandatory) {
		this.is_mandatory = is_mandatory;
	}

	public int getMaxString() {
		return maxString;
	}

	public void setMaxString(int maxString) {
		this.maxString = maxString;
	}

	@Override
	public String toString() {
		return "Atributi [name=" + name + ", code=" + code + ", type=" + type + ", maxString=" + maxString
				+ ", is_mandatory=" + is_mandatory + "]";
	}


}
