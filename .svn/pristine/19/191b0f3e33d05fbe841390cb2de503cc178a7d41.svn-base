package model;

import java.util.ArrayList;

/**
 * Klasa <code>TableModel</code> predstavlja klasu Tabela
 * sa listom svih potomaka tabela.
 * 
 * @author Luka
 * @version %I%, %G%
 */
public class TableModel{
	
	private Tabela tabela;
	private ArrayList<TableModel> deca;
	
	public TableModel() {
		tabela=new Tabela();
		deca=new ArrayList<TableModel>();
	}
	
	public TableModel(Tabela tabela,ArrayList<TableModel> decaz ) {
		this.tabela=new Tabela(tabela.getTitle(),tabela.getParent(),tabela.getPaket(),tabela.getAttributes(),tabela.getKey(),tabela.getRelations());
		this.deca=new ArrayList<TableModel>();
		
		for(TableModel tm : decaz)
		{
			this.deca.add(tm);
		}
	}
	
	public Tabela getTabela() {
		return tabela;
	}
	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}
	public ArrayList<TableModel> getDeca() {
		return deca;
	}
	public void setDeca(ArrayList<TableModel> deca) {
		this.deca = deca;
	}
	
	public String toString() {
		String a = "";
		for(int i=0;i<deca.size();i++)
			a+=  "Dete:" +deca.get(i).getTabela().getTitle() ;
		return a;
	}

}