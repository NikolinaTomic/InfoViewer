package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JTable;

import model.Atributi;
import model.Relacija;
import model.Tabela;
import model.TableModel;
/**
 * Klasa koja vrsi akciju presfiltracije. 
 * Na selektovanje reda u parent tabeli, potrebno je na osnovu vrednosti primarnog kljuca prefiltrirati child tabele.
 * Izlistati samo one vrednosti child tabela ciji je strani kljuc jednak vrednosti primarnog iz parent tabele.
 * @author Nikolina
 * @version %I%, %G%
 * @see  HashMap
 * 
 * 
 * */
public class Predfiltracija {
	/**
	 * Ulazni parametari ove metode su:  lista svih tabela, ime parent tabele na osnovu koje se vrsi predfiltracija i JTable tabela na koju je dvokliknuto.
	 * Povratna vrednost je hash-mapa tabela i matrica sa njihovim predfiltriranim podacima.
	 * */
	public HashMap<Tabela, Object[][]> filtriraj(ArrayList<TableModel> listaTabela,String ime,JTable table,int w,int h) {
			Tabela trazena=new Tabela();
			HashMap<Tabela, HashMap<ArrayList<String>,String>> DecaskVr= new HashMap<>();

			if(ime.equals("Country")) {
				ime="Drzava";
			}else if(ime.equals("Place")) {
				ime="Naseljeno mesto";
			}
			ArrayList<TableModel> deca=new ArrayList<TableModel>();
		    		for(TableModel t:listaTabela) { //koja je maticna tabela
		    			if(t.getTabela().getTitle().equals(ime)) {
		    				trazena=t.getTabela();
		    				deca=t.getDeca();
		    			}
		    		}
	    	ArrayList<Relacija> strani=trazena.getRelations();

		    for( TableModel tm:deca) {
			    HashMap<String,ArrayList<String>> sd=new HashMap<>(); //kljuc je source key vrednost dest

				Row row=new Row();
				String ime2="";
				String[] columnNames = row.uzmiHeadere(trazena);
    			for(Relacija s:strani) {
    				//udje samo u relaciju koja ima desttable ovo konkretno dete

    				if(tm.getTabela().getTitle().equals("Country")) {
    					ime2="Drzava";
    				}else if(tm.getTabela().getTitle().equals("Place")) {
    					ime2="Naseljeno mesto";
    				}else {
    					ime2=tm.getTabela().getTitle();
    				}
    				if(s.getDestinationTable().equals(ime2)) { //ako je dest key jednak detetu gledaj relacije
    					for(int i=0;i<s.getSourceKey().size();i++) {
    						if(sd.containsKey(s.getSourceKey().get(i))) {
    							ArrayList<String> lista=new ArrayList<>(); //dest keyevi
    							lista=sd.get(s.getSourceKey().get(i));
    							lista.add(s.getDestinationKey().get(i));
    							sd.put(s.getSourceKey().get(i),lista); 
    							//System.out.println(s.getDestinationKey().get(i));
    						}else {
    							ArrayList<String> lista=new ArrayList<>(); //dest keyevi
    							lista.add(s.getDestinationKey().get(i));
    							sd.put(s.getSourceKey().get(i),lista);
    	
    						}
    					}

    				
    				HashMap<ArrayList<String>, String> strKljucVr=new HashMap<>();
    				for(int i=0;i<columnNames.length;i++) {
    					//nadjeno po kom se obelezju vrsi filtracija
    					//mora se prolaziti kroz hedere iz baze
						String kod="";
    					for(String source: sd.keySet()) {
    						for(Atributi at:trazena.getAttributes()) {
    							if(at.getName().equals(columnNames[i])) {
    								kod=at.getCode();
    							}
    						}

    						if(source.equals(kod)) {
    							strKljucVr.put(sd.get(source), table.getModel().getValueAt(table.getSelectedRow(),i).toString()); //u mapu se ubacuje str kljuc sa vrednosti
    							DecaskVr.put(tm.getTabela(), strKljucVr);
    						}
    				}
    				}
    				}
    			}		
		    	
		    }
		 	Row rw=new Row();  
    		HashMap<Tabela, Object[][]> data2=rw.konekcijaSaBazomDecaProbrano(DecaskVr,listaTabela);
    	
    		return data2;
		  	}
	
}
