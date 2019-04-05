package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import app.MyApp;
import model.Atributi;
import model.PaketModel;
import model.Relacija;
import model.TableModel;

public class SQLData implements Parser{
    private HashMap<HashMap<String, String>, String> josIIme = new HashMap<>();
	private HashMap<String,String>po_Koditab_Oznaka = new HashMap<>();
	private ArrayList<TableModel>tabele = new ArrayList<>();
	private ArrayList<PaketModel>paketi = new ArrayList<>();
	PaketModel glavni=new PaketModel();
	
	
	@Override
	public void parse(Object shema) {
	////Parsiranje podsistema///////////////////////////////////////////////////////
		ArrayList<String>podaci = (ArrayList<String>)shema;
		Connection conn; 
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"+podaci.get(0)+"/psw-2018-tim2-4",podaci.get(1),podaci.get(2));
		
	
		
		try {
			String sql2="select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='podsistem'";
			String sql = "select * from podsistem";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rset = pstmt.executeQuery();
			ResultSet rset2 = pstmt2.executeQuery();
			
			int brojac3=0;
			while(rset2.next()) {
				brojac3++;
			}
			rset2 = pstmt2.executeQuery();
			
			String[] columns=new String[brojac3];
			while(rset2.next()) { //NAZIVI KOLONA
				for(int i=0;i<1;i++) {
					//System.out.println(rset.getString(i+1));
					columns[i]=rset2.getString(i+1);
				}
			}
				
			int brojac=0;
			while(rset.next()) {
				brojac++;
			}
			rset = pstmt.executeQuery();
			//System.out.println(brojac);
			//provera da li ima podataka u resultset-u
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			
			Object[][] data=new Object[brojac][3];

			int brojac2=0;
			while(rset.next()) {
				for(int i=0;i<3;i++) {
					data[brojac2][i]=rset.getString(i+1);
				}
				brojac2++;
			}

			rset.close();
			pstmt.close();
			
			for(int i=0;i<data.length;i++) {
		    		PaketModel podP = new PaketModel();   	
					podP.getPaket().setOznaka((String)data[i][0]);
					podP.getPaket().setImePaketa((String)data[i][1]);
					podP.getPaket().setTip((String)data[i][2]);
			  
				
		    paketi.add(podP);
					
				
			}
	
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////Parsiranje strukture podsistema
		
		try {
			String sql2="select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='struktura_podsistema'";
			String sql = "select * from struktura_podsistema";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rset = pstmt.executeQuery();
			ResultSet rset2 = pstmt2.executeQuery();
			
			int brojac3=0;
			while(rset2.next()) {
				brojac3++;
			}
			rset2 = pstmt2.executeQuery();
			
			String[] columns=new String[brojac3];
			while(rset2.next()) { //NAZIVI KOLONA
				for(int i=0;i<1;i++) {
					//System.out.println(rset.getString(i+1));
					columns[i]=rset2.getString(i+1);
				}
			}
				
			int brojac=0;
			while(rset.next()) {
				brojac++;
			}
			rset = pstmt.executeQuery();
			//System.out.println(brojac);
			//provera da li ima podataka u resultset-u
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			
			Object[][] data=new Object[brojac][columns.length];

			int brojac2=0;
			while(rset.next()) {
				for(int i=0;i<columns.length;i++) {
					data[brojac2][i]=rset.getString(i+1);
				}
				brojac2++;
			}

			rset.close();
			pstmt.close();
	
		
			for(int i=0;i<data.length;i++) {
				
				String nadPaket = (String)data[i][0];
				String podPaket = (String)data[i][1];
				String imeNad = "";
				for(int j=0;j<paketi.size();j++) {
					if(paketi.get(j).getPaket().getOznaka().equals(nadPaket)) {
						imeNad=paketi.get(j).getPaket().getImePaketa();
					
						for(int x=0;x<paketi.size();x++) {
							if(paketi.get(x).getPaket().getOznaka().equals(podPaket)) {
								paketi.get(x).getPaket().setRoditelji(imeNad);
							}
						}
					}
				}
				
			
			PaketModel glavni = new PaketModel();
			for(int t=0;t<paketi.size();t++) {
				if(paketi.get(t).getPaket().getRoditelji()==null) {
					glavni = paketi.get(t);
				}
			}
			
			}
			
	
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////Parsiranje tabela

		try {
			String sql2="select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME ='tabele'";
			String sql = "select * from tabele where po_oznaka!=\'PMetaSemaUBazi\'";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rset = pstmt.executeQuery();
			ResultSet rset2 = pstmt2.executeQuery();
			
			int brojac3=0;
			while(rset2.next()) {
				brojac3++;
			}
			rset2 = pstmt2.executeQuery();
			
			String[] columns=new String[brojac3];
			while(rset2.next()) { //NAZIVI KOLONA
				for(int i=0;i<1;i++) {
					//System.out.println(rset.getString(i+1));
					columns[i]=rset2.getString(i+1);
				}
			}
				
			int brojac=0;
			while(rset.next()) {
				brojac++;
			}
			rset = pstmt.executeQuery();
			//System.out.println(brojac);
			//provera da li ima podataka u resultset-u
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			
			Object[][] data=new Object[brojac][columns.length];

			int brojac2=0;
			while(rset.next()) {
				for(int i=0;i<columns.length;i++) {
					data[brojac2][i]=rset.getString(i+1);
				}
				brojac2++;
			}

			rset.close();
			pstmt.close();
			
			for(int i=0;i<data.length;i++) {
				po_Koditab_Oznaka = new HashMap<>();
				po_Koditab_Oznaka.put((String)data[i][1], (String)data[i][0]);
				josIIme.put(po_Koditab_Oznaka, (String)data[i][2]);
			    TableModel tm = new TableModel();
			    tm.getTabela().setTitle((String)data[i][2]);
			    tm.getTabela().setTitleCode((String)data[i][1]);
			    tabele.add(tm);
			}
					
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////Parsiranje atributa

		
		try {
			String sql2="select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='atributi'";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			ResultSet rset2 = pstmt2.executeQuery();
			
			
			int brojac3=0;
			while(rset2.next()) {
				brojac3++;
			}
			rset2 = pstmt2.executeQuery();
			
			String[] columns=new String[brojac3];
			while(rset2.next()) { //NAZIVI KOLONA
				for(int i=0;i<1;i++) {
					//System.out.println(rset.getString(i+1));
					columns[i]=rset2.getString(i+1);
				}
			}
			
			
			
			String sql="";
			for(HashMap<String, String> s:josIIme.keySet()) {
			for(String ss:s.keySet()) {
			 sql = "select * from atributi where po_oznaka=\'" + s.get(ss) + "\' and tab_kod=\'"+ ss +"\'";
			}
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			
				
			int brojac=0;
			while(rset.next()) {
				brojac++;
			}
			rset = pstmt.executeQuery();
			//System.out.println(brojac);
			//provera da li ima podataka u resultset-u
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			
			Object[][] data=new Object[brojac][columns.length];

			int brojac2=0;
			while(rset.next()) {
				for(int i=0;i<columns.length;i++) {
					data[brojac2][i]=rset.getString(i+1);
				}
				brojac2++;
			}

			rset.close();
			pstmt.close();
			
			for(int i=0;i<data.length;i++) {
				Atributi a = new Atributi();
			a.setCode((String)data[i][2]);
			a.setName((String)data[i][3]);
			String s1 = (String)data[i][6];
     		a.setMaxString((Integer.parseInt(s1)));
     		String tip = (String)data[i][4];
     		//System.out.println(tip);
     		if(tip.equals("varchar") || tip.equals("char"))
			a.setType("string");
			if(tip.equals("bit"))
			a.setType("boolean");
			if(tip.equals("int"))
				a.setType("number");
			String s11 = (String)data[i][5];
			Integer s111=Integer.parseInt(s11);
			if(s111==0) {
			a.setIs_mandatory(false);
			}else {
				a.setIs_mandatory(true);
			}
			for(HashMap<String, String> iter:josIIme.keySet()) {
				for(String S:iter.keySet()) {
					if(S.equals(data[i][1]) && iter.get(S).equals(data[i][0])) {
						for(int j=0;j<tabele.size();j++) {
							if(josIIme.get(iter).equals(tabele.get(j).getTabela().getTitle())) {
								tabele.get(j).getTabela().getAttributes().add(a);
								for(PaketModel pm:paketi) {
									if(pm.getPaket().getOznaka().equals(iter.get(S))) {
										tabele.get(j).getTabela().setPaket(pm.getPaket().getImePaketa());

									}
								}
								String klj=(String) data[i][8];
								Integer klj1=Integer.parseInt(klj);
								if(klj1==1) {
									tabele.get(j).getTabela().getKey().add(a.getCode());
								}
			//					System.out.println(tabele.get(j).getTabela().toString());
							}
						}
					}
				}
			}
			
			}
			
			
			}
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException ee) {
			// TODO: handle exception
			ee.printStackTrace();
		}
		
		
		

		
	/////////////////Parsiranje stranog kljuca
		

		try {
			String sql2="select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='strani_kljuc'";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			ResultSet rset2 = pstmt2.executeQuery();
			
			
			int brojac3=0;
			while(rset2.next()) {
				brojac3++;
			}
			rset2 = pstmt2.executeQuery();
			
			String[] columns=new String[brojac3];
			while(rset2.next()) { //NAZIVI KOLONA
				for(int i=0;i<1;i++) {
					//System.out.println(rset.getString(i+1));
					columns[i]=rset2.getString(i+1);
				}
			}
		
			
			String sql="select * from strani_kljuc";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
			
				
			int brojac=0;
			while(rset.next()) {
				brojac++;
			}
			rset = pstmt.executeQuery();
			//System.out.println(brojac);
			//provera da li ima podataka u resultset-u
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			
			Object[][] data=new Object[brojac][columns.length];

			int brojac2=0;
			while(rset.next()) {
				for(int i=0;i<columns.length;i++) {
					data[brojac2][i]=rset.getString(i+1);
				}
				brojac2++;
			}

			rset.close();
			pstmt.close();
			
			
			for(int i=0;i<data.length;i++) {
				HashMap<String, String>prviPar = new HashMap<>();
				HashMap<String, String>drugiPar = new HashMap<>();
				prviPar.put((String)data[i][1], (String)data[i][0]);
				drugiPar.put((String)data[i][3], (String)data[i][2]);
				if(josIIme.containsKey(prviPar) && josIIme.containsKey(drugiPar)) {
					Relacija r = new Relacija();
					r.setRelation_code((String)data[i][4]);
					String s = (String)data[i][6];
					String ime="";
					r.setStrong_relationship(Boolean.parseBoolean(s));
					r.setRelation_title((String)data[i][5]);
					for(HashMap<String, String> it:josIIme.keySet()) {
						for(String s12 : it.keySet()) {
						for(String str:prviPar.keySet()) {
							if(str.equals(s12)  && prviPar.get(str).equals(it.get(s12))) {
								ime = josIIme.get(it); 
							}
						}
					}
				}
					for(int ii=0;ii<tabele.size();ii++) {
						if(tabele.get(ii).getTabela().getTitle().equals(ime)) { //drzava
							tabele.get(ii).getTabela().getRelations().add(r);
						
						} //ukloniti perente duplikate i bit pretvoriti u boolean kao i string..
						//parent je sad code a ne name
						else if(tabele.get(ii).getTabela().getTitleCode().equals((String)data[i][3])) {
							String klj=(String) data[i][6];
							Integer klj1=Integer.parseInt(klj);
							if(klj1==1) {
								for(TableModel tmm:tabele) {
									if(tmm.getTabela().getTitleCode().equals((String)data[i][1])){
										tabele.get(ii).getTabela().getParent().add(tmm.getTabela().getTitle());
									
									}
								}
							}
						}
					}
					
				}
				
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException ee) {
			// TODO: handle exception
			ee.printStackTrace();
		}
		
	
		
		//////////////////parsiranje kolona
		
		
	
		try {
			String sql2="select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='kolone_u_stranom_kljucu'";
			String sql = "select * from kolone_u_stranom_kljucu";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rset = pstmt.executeQuery();
			ResultSet rset2 = pstmt2.executeQuery();
			
			int brojac3=0;
			while(rset2.next()) {
				brojac3++;
			}
			rset2 = pstmt2.executeQuery();
			
			String[] columns=new String[brojac3];
			while(rset2.next()) { //NAZIVI KOLONA
				for(int i=0;i<1;i++) {
					//System.out.println(rset.getString(i+1));
					columns[i]=rset2.getString(i+1);
				}
			}
				
			int brojac=0;
			while(rset.next()) {
				brojac++;
			}
			rset = pstmt.executeQuery();
			//System.out.println(brojac);
			//provera da li ima podataka u resultset-u
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			
			Object[][] data=new Object[brojac][columns.length];

			int brojac2=0;
			while(rset.next()) {
				for(int i=0;i<columns.length;i++) {
					data[brojac2][i]=rset.getString(i+1);
				}
				brojac2++;
			}

			rset.close();
			pstmt.close();
	        String kljuc="";
	        String vrednost="";
			String imeOca="";
		    String imeDeteta="";
		    int flag=0;
		    int flag2=0;
			for(int i=0;i<data.length;i++) {
	        HashMap<String, String>prviPar = new HashMap<>();
	        HashMap<String, String>drugiPar = new HashMap<>();
	        prviPar.put((String)data[i][1], (String)data[i][0]);
	        drugiPar.put((String)data[i][3], (String)data[i][2]);
	        
	        for(HashMap<String, String> iter : josIIme.keySet()) {
	        	for(String sss : iter.keySet()) {
	        		
	        		for(String g : prviPar.keySet()) {
	        			kljuc=g;
	        			vrednost=prviPar.get(g);
	        		}
	        		
	        		if(sss.equals(kljuc) && iter.get(sss).equals(vrednost)) { //postoji prvi par
	        			flag=1;       			
	        			
	        		}
	    	   
	       }
	       }
	        
	        if(flag==1) {
	        	
	        	
	            for(HashMap<String, String> iter : josIIme.keySet()) {
		        	for(String sss : iter.keySet()) {
		  
		        		for(String g : drugiPar.keySet()) {
		        			kljuc=g;
		        			vrednost=drugiPar.get(g);
		        		}
		        		
		        		if(sss.equals(kljuc) && iter.get(sss).equals(vrednost)) {
		        			flag2=1;
		        					        			
		        		}		    	   
		       }
		       }
        	
	        }
	        for(TableModel tm:tabele) {
	        	if(tm.getTabela().getTitleCode().equals((String)data[i][1])) {
	        		imeOca=tm.getTabela().getTitle();
	        	}
	        }
	        
	        
	    	   for(int iq=0;iq<tabele.size();iq++) {
	    		   if(tabele.get(iq).getTabela().getTitle().equals(imeOca)) {
	    			   for(int jj=0;jj<tabele.get(iq).getTabela().getRelations().size();jj++) {
	    				   if(tabele.get(iq).getTabela().getRelations().get(jj).getRelation_code().equals((String)data[i][4])) {
	    					   tabele.get(iq).getTabela().getRelations().get(jj).getDestinationKey().add((String)data[i][6]);
	    					   tabele.get(iq).getTabela().getRelations().get(jj).getSourceKey().add((String)data[i][5]);
	    					   imeDeteta=josIIme.get(drugiPar);
	    					   tabele.get(iq).getTabela().getRelations().get(jj).setDestinationTable(imeDeteta);
	    					   }
	    				   
	    				   }
	    		     }
	    		   
	    	   
	       }
				
			}
		
	
	
		
		
		MyApp.getInstance().setTabelaModeli(tabele);
		MyApp.getInstance().setPaketModeli(paketi);
		MyApp.getInstance().initTree();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
	
	}

	
	
}
