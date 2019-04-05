package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.MyApp;
import model.Atributi;
import model.Tabela;
import model.TableModel;
import view.TableView;
/**
 * Klasa koja predstavlja jednu od <b>CRUD</b> akcija.
 * Radi tako sto se selektuje zeljeni red za izmenu i onda se klikom na dugme otvara dijalog za izmenu.
 * Implementira <b>CRUDFactory</b> interfejs.
 * @see CRUDFactory {@link controller.CRUDFactory}}
 * @author Ivan
 * @version %I%, %G%
 * 
 * */

public class UpdateData implements CRUDFactory{
    private LinkedHashMap<String, String> uneto = new LinkedHashMap<>();
	private ArrayList<JTextField> polja = new ArrayList<>();
	public LinkedHashMap<String, Object> glavno = new LinkedHashMap<>();
	private Tabela t = new Tabela();
	String query="";
	boolean dete;
	Tabela Tab = new Tabela();
	int w;
	int h;
	JTable tt= new JTable();
	int red;
	String nov="";
	boolean search;
	ArrayList<TableModel> listaTabela;
	Tabela roditelj;

	private ArrayList<ButtonGroup> dugmad = new ArrayList<>();
	private ArrayList<JRadioButton> but = new ArrayList<>();
	public UpdateData(ArrayList<JTextField> polja,ArrayList<ButtonGroup> dugmad,ArrayList<JRadioButton>but,Tabela t,LinkedHashMap<String,Object> g,boolean dete,Tabela roditelj,ArrayList<TableModel> listaTabela,int w,int h,LinkedHashMap<String,String> uneto,int red,JTable table,boolean search) {
		this.search=search;
		this.polja = polja;
		this.dugmad=dugmad;
		this.uneto=uneto;
		this.red=red;
		tt=table;
		this.but=but;
		this.t=t;
		Tab=t;
		this.dete=dete;
		this.w=w;
		this.h=h;
		this.roditelj=roditelj;
		this.listaTabela=listaTabela;
		glavno=g;
		updateData();
	}

	
	
	@Override
	public boolean addData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeData() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Metoda koja ostvaruje konekciju sa bazom podataka i preko upita azurira postojece stanje.
	 * @return true ako je sve proslo bez izuzetaka, false ako nije uspeo da upise podatke u bazu.
	 * */

	@Override
	public boolean updateData() {
		// TODO Auto-generated method stub
			String izmena=t.getTitle();
			if(t.getTitle().contains(" ")) {
				String str = " ";
				izmena=izmena.replace(str, "_");
			}
			query="update "+izmena+" set ";
			for(int i=0;i<t.getAttributes().size();i++) {
		
				
				query+=t.getAttributes().get(i).getCode()+" = ";
				
				
				int counter=-1;
				for(String s : glavno.keySet()) {
				
					if(s.equals(t.getAttributes().get(i).getCode())) {
						++counter;	
					if(glavno.get(s) instanceof JTextField) {
						JTextField f= (JTextField) glavno.get(s);
						if(t.getAttributes().get(i).getType().equals("number")) {
						if(f.getText().equals(""))
							query+="null"+" , ";
						else
							query+=f.getText() +" , ";
						break;
						}
						else {
							
							if(f.getText().equals(""))
								query+="\'\'"+", ";
							else
								query+="\'"+f.getText()+"\'"+", ";
							break;	
						}
					}
					else if(glavno.get(s) instanceof ButtonGroup) {
						int brojac;
						ButtonGroup bg = (ButtonGroup) glavno.get(s);
						brojac=0;
						 for (Enumeration<AbstractButton> buttons = bg.getElements();
								 buttons.hasMoreElements();) {
					         ++brojac;
					     
							 AbstractButton button = buttons.nextElement();

					            if (button.isSelected()) {
					                if(button.getText().equals("True"))
					                	query+="1"+", ";
					                if(button.getText().equals("False"))				                
					                	query+="0"+", ";
					                break;
					            }
					            
					         
					        
					        }
						 
					}
					
					
				}

			
				}
				
				}

			int poz = query.lastIndexOf(',');
			query=query.substring(0, poz);
			query=query.trim();
			query+=" where ";
			
			HashMap<String, String> kodoviKljuceva=new HashMap<>();
			HashMap<String, Integer> kodPozicijaDB=new HashMap<>();
			for(String k:t.getKey()) {
				for(Atributi a:t.getAttributes()) {
					if(a.getCode().equals(k)) {
						kodoviKljuceva.put(a.getCode(), a.getName());
					}
				}
			}
			Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");

			String sql2="select COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='"+izmena+"'";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			
			ResultSet rset2 = pstmt2.executeQuery();
			int br=0;
			while(rset2.next()) { //NAZIVI KOLONA
				for(int i=0;i<1;i++) {
					//System.out.println(rset2.getString(i+1));
					for(String s:kodoviKljuceva.keySet()) {
						if(s.equals(rset2.getString(i+1))) {
							kodPozicijaDB.put(s, br);
							//System.out.println(br);
						}
					}
				}
				br++;
			}
			rset2.close();
			pstmt2.close();
				
			HashMap<String, String> kodVrednost=new HashMap<>();
			
			for(String s: kodPozicijaDB.keySet()) {
				kodVrednost.put(s, tt.getValueAt(red, kodPozicijaDB.get(s)).toString());
				//System.out.println(s+" "+tabela.getValueAt(red, kodPozicijaDB.get(s)).toString());
			}
			int pozicija=0;
			
		
				for(String k:kodVrednost.keySet()) {
				query+=k+" = "+"\'"+kodVrednost.get(k)+"\'" + " and ";
			}
				pozicija=query.lastIndexOf("and");
				nov = query.substring(0, pozicija-1);
			
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			Connection conn2;
			try {
				conn2 = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
			
			
			PreparedStatement pstmt = conn2.prepareStatement(nov);
            pstmt.executeUpdate();
			pstmt.close();
			
			//ako je tabla dete razlicito se crta u tableView
			if(dete) {
				Row rw=new Row(); 
				Object[][] data=rw.konekcijaSaBazom(roditelj);
				ArrayList<TableModel> deca=new ArrayList<>();
				for(TableModel tm:listaTabela) {
					if(tm.getTabela().getTitle().equals(roditelj.getTitle())) {
						deca=tm.getDeca();
					}
				}
				ArrayList<Tabela> decaT=new ArrayList<>();
				for(TableModel tm: deca) {
					decaT.add(tm.getTabela());
				}
			HashMap<Tabela, Object[][]> data2=rw.konekcijaSaBazomDeca(decaT);
			TableView is=new TableView(listaTabela,roditelj.getTitle());
			JPanel pa=is.iscrtavanje(w,h,data,data2);
			MyApp.getInstance().addToCentralPanel2(pa);	 
			}
			else {
				Row rw=new Row(); 
				Object[][] data=rw.konekcijaSaBazom(t);
				ArrayList<TableModel> deca=new ArrayList<>();
				for(TableModel tm:listaTabela) {
					if(tm.getTabela().getTitle().equals(t.getTitle())) {
						deca=tm.getDeca();
					}
				}
				ArrayList<Tabela> decaT=new ArrayList<>();
				for(TableModel tm: deca) {
					decaT.add(tm.getTabela());
				}
			HashMap<Tabela, Object[][]> data2=rw.konekcijaSaBazomDeca(decaT);
			TableView is=new TableView(listaTabela,t.getTitle());
			JPanel pa=is.iscrtavanje(w,h,data,data2);
			MyApp.getInstance().addToCentralPanel2(pa);	 
			}
			if(search) {
				//System.out.println("USAO");
				Row rw=new Row(); 
				Object[][] data=rw.konekcijaSaBazom(t);
				String[] columnNames=rw.uzmiHeadere(t);
				SearchData ss = new SearchData(data,columnNames,t.getTitle(),t,listaTabela,w,h,dete,roditelj,tt);   				
				
			}
			JOptionPane.showMessageDialog(null, "Success!\n");
			
			}  catch (SQLException ex) {
				if(ex.getErrorCode()==2627)
					JOptionPane.showMessageDialog(null, MyApp.getInstance().getResourceBundle().getString("duplicate"));
				else
				ex.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, MyApp.getInstance().getResourceBundle().getString("error"));
			}

		return false;
	}

		

	@Override
	public void searchData() {
		// TODO Auto-generated method stub
		
	}

}
