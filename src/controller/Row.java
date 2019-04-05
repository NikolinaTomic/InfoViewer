package controller;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import app.MyApp;
import model.Atributi;
import model.Tabela;
import model.TableModel;
/**
 * Klasa koja je povazana sa bazom podataka. Sluzi za slanje upita i preuzimanja podataka iz baze na osnovu tih upita.
 * Nasledjuje <b>JFrame</b> zbog rada sa <b>Swing</b> komponentama.
 * Ova klasa ima drugacije ponasanje u odnosu na <b>private boolean source</b>atribut (razlicito posanje u zavisnosti da li je JSON ili DB).
 * 
 * @author Nikolina
 * @version %I%, %G%
 * @see  HashMap
 * @see ResourceBundle
 * 
 * 
 * */
public class Row {
	/**
	 * Metoda kojom se preuzimaju heade-i tabela iz baze podataka
	 * */
	public String[] uzmiHeadere(Tabela t) {
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
		
			t.toString();

		String izmena=t.getTitle();
		
		if(t.getTitle().contains(" ")) {
			String str = " ";
			izmena=izmena.replace(str, "_");
			
			
		}
		
		if(izmena.equals("Country")) 
			izmena="Drzava";	
		if(izmena.equals("Place")) izmena="Naseljeno_mesto";
		
		String sql2="select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='"+izmena+"'";
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		
		ResultSet rset2 = pstmt2.executeQuery();
		
		int brojac3=0;
		while(rset2.next()) {
			brojac3++;
		}
		rset2 = pstmt2.executeQuery();
		
		String[] columns=new String[brojac3];
		int br=0;
		while(rset2.next()) { //NAZIVI KOLONA
			for(int i=0;i<1;i++) {
				//System.out.println(rset2.getString(i+1));
				for(Atributi s: t.getAttributes()) {
					if(s.getCode().equals(rset2.getString(i+1))) {
					
						if(s.getName().equals("Oznaka"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("oznaka"));
						}else if(s.getName().equals("Glavni grad"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("glavniGrad"));
						}else if(s.getName().equals("Naziv"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("naziv"));
						}else if(s.getName().equals("Drzava"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("drzava"));
						}else if(s.getName().equals("Post. broj"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("postBroj"));
						}else if(s.getName().equals("Grad"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("grad"));
						}
						else if(s.getName().equals("Broj stanovnika"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("brojStanovnika"));
						}
						else if(s.getName().equals("Indeks"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("indeks"));
						}
						else if(s.getName().equals("Ime"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("ime"));
						}
						else if(s.getName().equals("Prezime"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("prezime"));
						}
						else if(s.getName().equals("Drzava rodjenja"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("drzavaRodjenja"));
						}else if(s.getName().equals("Mesto rodjenja"))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("mestoRodjenja"));
						}else if(s.getName().equals("Drzava stan."))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("drzavaStanovanja"));
						}else if(s.getName().equals("Mesto stan."))
						{
							s.setName(MyApp.getInstance().getResourceBundle().getString("mestoStanovanja"));
						}
						
						
						columns[br]=s.getName();
						
					}
				}
			}
			br++;
		}
		rset2.close();
		pstmt2.close();
			return columns;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Ulazni parametar ove metode je tabela na koju je dvokliknuto u stablu.
	 * Povratna vrednost su njeni podaci preuzeti iz baze.
	 * Metoda je neophodna zbog iscrtavanja tabele u desnoj strani glavnog prozora
	 * */
	public Object[][] konekcijaSaBazom(Tabela t) {
		try {
				//otvaranje konekcije
			
				Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
		
				String izmena=t.getTitle();
				if(t.getTitle().contains(" ")) {
					String str = " ";
					izmena=izmena.replace(str, "_");
				}
			
				if(izmena.equals("Country")) izmena="Drzava";
				if(izmena.equals("Place")) izmena="Naseljeno_mesto";
				
				String sql2="select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='"+izmena+"'";
				String sql = "select * from "+izmena;
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
				
				int brPar=columns.length; //ivan promenio bilo je t.getAtr.sizr
				Object[][] data=new Object[brojac][brPar];
	
				int brojac2=0;
				while(rset.next()) {
					for(int i=0;i<brPar;i++) {
						data[brojac2][i]=rset.getString(i+1);
					}
					brojac2++;
				}

				rset.close();
				pstmt.close();
				return data;
			
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		
	}	//otvaranje konekcije
	
	/**
	 * Ulazni parametar ove metode je lista dece dvokliknute tabele iz stabla.
	 * Povratna vrednost je niz matrica popunjenih podacima za svako posebno dete.
	 * Metoda je neophodna zbog iscrtavanja dece tabele u desnoj strani glavnog prozora
	 * */
	public HashMap<Tabela, Object[][]> konekcijaSaBazomDeca(ArrayList<Tabela> deca){
		try {
			Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
			HashMap<Tabela, Object[][]> podaci=new HashMap<>();
		if(deca.size()!=0) {
		for(Tabela t: deca) {
			//System.out.println(t.getTitle());
			String izmena=t.getTitle();
			if(t.getTitle().contains(" ")) {
				String str = " ";
				izmena=izmena.replace(str, "_");
			}
			
			if(izmena.equals("Country")) izmena="Drzava";
			if(izmena.equals("Place")) izmena="Naseljeno_mesto";
			
			String sql = "select * from "+izmena;
			//System.out.println(izmena);
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
			
			int brPar=t.getAttributes().size();
			Object[][] data=new Object[brojac][brPar];
			
			int brojac2=0;
			while(rset.next()) {
				for(int i=0;i<brPar;i++) {
					data[brojac2][i]=rset.getString(i+1);
				}
				
				brojac2++;
			}
			podaci.put(t, data);
			rset.close();
			pstmt.close();
			
			
		
		
		}
		}////PROVERI IMA LI DECE
		return podaci;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Ulazni parametar ove metode je hash-mapa ciji je kljuc naziv tabele, a vrednost nova hash-mapa sa kljucem destination kljuceva sa njihovim vrednostima.
	 * Drugi ulazni parametar je lista svih tabela.
	 * Povratna vrednost je hash-mapa tabela i matrica sa njihovim predfiltriranim podacima.
	 * Metoda sluzi za funkionalnost prefiltracije {@link controller.Predfiltracija} . Vraca samo one vrednosti stranog kljuca koje su selektovane u parent tabeli kao primarni kljuc.
	 * */
	public HashMap<Tabela, Object[][]> konekcijaSaBazomDecaProbrano(HashMap<Tabela, HashMap<ArrayList<String>,String>> decaStrKljucVr,ArrayList<TableModel> tabeleModeli){
		//mapa dece sa kljucem naziv tabele deteta i 2. hashmapa je pozicija sa vrednoscu na tom kljucu
		try {
		
			Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
			HashMap<Tabela, Object[][]> podaci=new HashMap<>();
		if(decaStrKljucVr.size()!=0) { //ova mapa kljuc je tabela a value je mapa arraylista dest keyeva sa konkkretnom vrednoscu
		for(Tabela t: decaStrKljucVr.keySet()) {
			//System.out.println(t.getTitle());
			String nov="";
			int pozicija=0;
			String kolone="( ";
			String izmena=t.getTitle();
			if(t.getTitle().contains(" ")) {
				String str = " ";
				izmena=izmena.replace(str, "_");
			} 
			
			if(izmena.equals("Country")) izmena="Drzava";
			if(izmena.equals("Place")) izmena="Naseljeno_mesto";
			
					HashMap<String,String> kodVr=new HashMap<>();
					HashMap<ArrayList<String>,String> kljVr=decaStrKljucVr.get(t);
					for(ArrayList<String> listaNazKol : kljVr.keySet()) {
						ArrayList<TableModel> decaModeli=new ArrayList<>();
						for(TableModel tm: tabeleModeli) { //jer moze u razlicitim tabelama da budu isti nazivi obelezja
							if(t.getTitle().equals(tm.getTabela().getTitle())) {
								decaModeli.add(tm);
							}
						}
						for(TableModel tm:decaModeli) {
							for(Atributi aa :tm.getTabela().getAttributes()) {
								for(String nazKol : listaNazKol) {//dest tablovi
								if(aa.getCode().equals(nazKol)) {
							//onda uzmi kod jer je u bazi naziv kolone kod
									kodVr.put(aa.getCode(), kljVr.get(listaNazKol));
									kolone+=aa.getCode()+" like '%"+kljVr.get(listaNazKol)+"%'"+" or ";
								}
								}
							
								}
							}
						if(kolone.lastIndexOf("or")!=-1) {
						pozicija=kolone.lastIndexOf("or");
						kolone = kolone.substring(0, pozicija-1);
						kolone=kolone+") and (";
						}
						}
				

			pozicija=kolone.lastIndexOf("and");
			nov = kolone.substring(0, pozicija-1);
			String sql = "select * from "+izmena+" where "+nov; //kod like %naziv%
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
			
			int brPar=t.getAttributes().size();
			Object[][] data=new Object[brojac][brPar];

			int brojac2=0;
			while(rset.next()) {
				for(int i=0;i<brPar;i++) {
					data[brojac2][i]=rset.getString(i+1);
				}
				
				brojac2++;
			}
			podaci.put(t, data);
			rset.close();
			pstmt.close();
			
			
		
		
		}
		}////PROVERI IMA LI DECE
		return podaci;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	}

		
}
