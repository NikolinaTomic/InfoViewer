package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import app.MyApp;
import model.Tabela;
import view.AddView;
import view.UpdateView;

/**
 * Ova klasa sluzi za pretragu tabela pomocu upita koje se salju bazi.
 * 
 * 
 * */

public class SearchData extends JFrame implements CRUDFactory{
	private ArrayList<Object> elementi = new ArrayList<>();
	private ArrayList<JTextField> textfieldovi = new ArrayList<>();
	private ArrayList<JRadioButton> buttons = new ArrayList<>();
	private ArrayList<String>svikodovi = new ArrayList<>();
	JPanel pap = new JPanel();
	
	private LinkedList<JCheckBox>dugmici = new LinkedList<>();
	private JRadioButton date = new JRadioButton(MyApp.getInstance().getResourceBundle().getString("date"));
	private JRadioButton numeric = new JRadioButton(MyApp.getInstance().getResourceBundle().getString("num"));
	private JRadioButton text = new JRadioButton(MyApp.getInstance().getResourceBundle().getString("str")); 
	private JRadioButton num = new JRadioButton(MyApp.getInstance().getResourceBundle().getString("numeric"));
	private JRadioButton bul = new JRadioButton(MyApp.getInstance().getResourceBundle().getString("bool"));
	JLabel unesiOdDatum = new JLabel(MyApp.getInstance().getResourceBundle().getString("from"));
	private JTextArea unesiOdDatumText = new JTextArea();
	JLabel unesiDoDatum = new JLabel(MyApp.getInstance().getResourceBundle().getString("to"));
	private JTextArea unesiDoDatumText = new JTextArea();
	private ArrayList<String> naziviKolona = new ArrayList<>();
	JPanel panelcic = new JPanel();
	JScrollPane scrollPane = new JScrollPane();
	JFrame frame = new JFrame(MyApp.getInstance().getResourceBundle().getString("search"));
	private JTextArea unos = new JTextArea();
	private JTextArea unosFrom = new JTextArea();
	private Tabela tabela;
    private JTable ta;
    private String tipPolja;
    private String nazivPolja;
    String query="";
	private int flag=0;
	private JTable tt;
	private JPanel pa1 = new JPanel();
	private JPanel pa2 = new JPanel();
	 private JTextArea unosTo = new JTextArea();
	 public SearchData (Object[][] data, String[] names,String imeTabele,Tabela tabela,ArrayList<model.TableModel> listaTabela,int w,int h,boolean dete,Tabela roditelj,JTable tt) {
		 this.tabela=tabela;
		 this.tt=tt;
		 if(imeTabele.contains(" ")) {
	 			imeTabele=imeTabele.replace(" ", "_");
	 		}	
		 
		 
		 trazi(data,names,imeTabele,listaTabela,w,h,dete,roditelj);
	 }
	 
	 
	 
	 FieldFactory ff=new FieldFactory(tipPolja, nazivPolja);
	 
	 public void trazi(Object[][] data, String[] names,String imeTabele,ArrayList<model.TableModel> listaTabela,int w,int h,boolean dete,Tabela roditelj) {
		 for(int i=0;i<tt.getColumnCount();i++) {
     			naziviKolona.add(tt.getColumnName(i));
     	//		System.out.println(tt.getColumnName(i));
     		
     	}
		 JButton update = new JButton(MyApp.getInstance().getResourceBundle().getString("update"));
		 JButton remove = new JButton(MyApp.getInstance().getResourceBundle().getString("remove"));
		 JPanel operations = new JPanel();
		 JButton add = new JButton(MyApp.getInstance().getResourceBundle().getString("add"));
		 pap.setLayout(new BoxLayout(pap, BoxLayout.Y_AXIS));
		 operations.setLayout(new BoxLayout(operations, BoxLayout.X_AXIS));
			operations.setPreferredSize(new Dimension(230, 50));
		 operations.add(add);
		 operations.add(update);
		 operations.add(remove);
		 JTable t = new JTable(data,names);
		 t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 tt=t;
		 pap.setLayout(new BoxLayout(pap, BoxLayout.Y_AXIS));
		 
		 for(int i=0;i<naziviKolona.size();i++) {
			 JPanel panel = new JPanel();
			 panel.setLayout(new FlowLayout());
			 for(int j=0;j<tabela.getAttributes().size();j++) {
				 if(naziviKolona.get(i).equals(tabela.getAttributes().get(j).getName())) {
					 tipPolja=tabela.getAttributes().get(j).getType();
					System.out.println(tipPolja);
					 nazivPolja=tabela.getAttributes().get(j).getName();
					 
					
					 JPanel ffp=ff.CreateField(tipPolja, nazivPolja);

						 JPanel nazp=new JPanel(new FlowLayout());
						 JLabel lab = new JLabel(nazivPolja+": ");
						 nazp.add(lab);
						 nazp.add(ffp);
						 pap.add(nazp);
						
					 break;
				 }

	 
			 }
		 }
		 elementi = ff.getElements(0); 
		
		 for(int i=0;i<elementi.size();i++) {
			  
			 if(elementi.get(i) instanceof JTextField) {
			textfieldovi.add((JTextField)elementi.get(i));
			 }
			 else {
				 buttons.add((JRadioButton)elementi.get(i));
			 }
			 }
		 
		 System.out.println("aaa");
		 for(int i=0;i<elementi.size();i++) {
			  
			 if(elementi.get(i) instanceof JTextField) {
				 JTextField textF = (JTextField) elementi.get(i);
				 if(textF.getToolTipText().equals("string")) {
					 
					
					 textF.getDocument().addDocumentListener(new DocumentListener() {
						
						@Override
						public void removeUpdate(DocumentEvent e) {
							// TODO Auto-generated method stub
							query="select * from "+imeTabele+" where ";
							 if(textF.getText().matches((("[12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])")))) {
									for(int j=0;j<tabela.getAttributes().size();j++) {
										if(textF.getName().equals(tabela.getAttributes().get(j).getName())) {
											 query+=tabela.getAttributes().get(j).getCode()+ " = "+"\'"+textF.getText()+"\'" +" and ";
												}
									}
								 
								
							 }
							 else {
							for(int j=0;j<tabela.getAttributes().size();j++) {
								if(textF.getName().equals(tabela.getAttributes().get(j).getName())) {
									query+=tabela.getAttributes().get(j).getCode()+ " like "+"\'%"+textF.getText()+"%\'" +" and ";
								}
							}
							 }
							 
							for(int j=0;j<textfieldovi.size();j++) {
								if(!textfieldovi.get(j).getName().equals(textF.getName())) { 
									if(textfieldovi.get(j).getToolTipText().equals("string")) {
									
										
										 if(textfieldovi.get(j).getText().matches((("[12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])")))) {
												for(int k=0;k<tabela.getAttributes().size();k++) {
													if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
														 query+=tabela.getAttributes().get(k).getCode()+ " = "+"\'"+textfieldovi.get(j).getText()+"\'" +" and ";
															}
												}
											 
											
										 }
										
										 else {
										
										for(int k=0;k<tabela.getAttributes().size();k++) {
											if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
												query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
											}
										}
										 }
										
										
									}else if(textfieldovi.get(j).getToolTipText().equals("number")) {
										JTextField from = textfieldovi.get(j);
										JTextField to = textfieldovi.get(j+1);
										j++;
										if(!from.getText().equals("") && !to.getText().equals("")) {
									     String code1 ="";
									    System.out.println(from.getText() + "   "+to.getText());
									     
									     for(int k=0;k<tabela.getAttributes().size();k++) {
												if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
												code1=tabela.getAttributes().get(k).getCode();	
												}
											}
									    
									     query+= code1 + " between " +from.getText() +" and " +to.getText() + " and ";
									     
										}else if(!from.getText().equals("") && to.getText().equals("")) {
											   String code1 ="";
											    
											     
											     for(int k=0;k<tabela.getAttributes().size();k++) {
														if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
														code1=tabela.getAttributes().get(k).getCode();	
														}
													}
											     query+= code1 + " >= " + from.getText() + " and ";
										}else if(from.getText().equals("") && !to.getText().equals("")) {
											   String code1 ="";
											    
											     
											     for(int k=0;k<tabela.getAttributes().size();k++) {
														if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
														code1=tabela.getAttributes().get(k).getCode();	
														}
													}
											     query+= code1 + " <= " + to.getText() + " and ";
										}
									}
								}
							}
							int flag=0;
							for(int n=0;n<buttons.size();n++) {
								if(buttons.get(n).isSelected()) {
									flag=1;
									break;
								}
							}
							if(flag==1) {
							if(!buttons.isEmpty()) {
								for(int j=0;j<buttons.size();j++) {
									
									JRadioButton b1 = buttons.get(j);
									JRadioButton b2 = buttons.get(j+1);
									j++;
									
									 String code1 ="";
									    
								     
								     for(int k=0;k<tabela.getAttributes().size();k++) {
											if(b1.getName().equals(tabela.getAttributes().get(k).getName()+"True") || b1.getName().equals(tabela.getAttributes().get(k).getName()+"False")) {
											code1=tabela.getAttributes().get(k).getCode();	
											}
										}
								     if(b1.isSelected())
								    	 query+=code1 +"= 1 and ";
								     else
								    	 query+=code1 +"= 0 and ";
								     	 
									
								}
							}
							}
							int poz=0;
							poz = query.lastIndexOf("and");
							query=query.substring(0,poz-1);
							
							Connection conn2;
							try {
								conn2 = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
							
							
							PreparedStatement pstmt = conn2.prepareStatement(query);
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
						
						int brPar=tt.getColumnCount();
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
						
						 tt = new JTable(data, names);
							scrollPane.setViewportView(tt);
					    	
					    	
					    	panelcic.add(scrollPane);
					    //	panelcic.revalidate();
					    //	panelcic.repaint();
					      //	frame.removeAll();
					    	pap.add(operations);
					    	pap.add(scrollPane);
					    	
					    	//frame.add(pap);
							
						 
						 
							}catch (SQLException es) {
								// TODO: handle exception
								es.printStackTrace();
							}
							
							System.out.println(query);
						
								
						}
						
						@Override
						public void insertUpdate(DocumentEvent e) {
							query="select * from "+imeTabele+" where ";
							
							 if(textF.getText().matches((("[12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])")))) {
									for(int j=0;j<tabela.getAttributes().size();j++) {
										if(textF.getName().equals(tabela.getAttributes().get(j).getName())) {
											 query+=tabela.getAttributes().get(j).getCode()+ " = "+"\'"+textF.getText()+"\'" +" and ";
												}
									}
								 
								
							 }
							 else {
							for(int j=0;j<tabela.getAttributes().size();j++) {
								if(textF.getName().equals(tabela.getAttributes().get(j).getName())) {
									query+=tabela.getAttributes().get(j).getCode()+ " like "+"\'%"+textF.getText()+"%\'" +" and ";
								}
							}
							 }
							 
							 
							for(int j=0;j<textfieldovi.size();j++) {
								if(!textfieldovi.get(j).getName().equals(textF.getName())) { 
									if(textfieldovi.get(j).getToolTipText().equals("string")) {
										if(textfieldovi.get(j).getText().matches((("[12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])")))) {
											for(int k=0;k<tabela.getAttributes().size();k++) {
												if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
													query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
												}
											}
											 
										}
										else {
										for(int k=0;k<tabela.getAttributes().size();k++) {
											if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
												query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
											}
										}
										
										}
										
									}else if(textfieldovi.get(j).getToolTipText().equals("number")) {
										JTextField from = textfieldovi.get(j);
										JTextField to = textfieldovi.get(j+1);
										j++;
										if(!from.getText().equals("") && !to.getText().equals("")) {
									     String code1 ="";
									    System.out.println(from.getText() + "   "+to.getText());
									     
									     for(int k=0;k<tabela.getAttributes().size();k++) {
												if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
												code1=tabela.getAttributes().get(k).getCode();	
												}
											}
									    
									     query+= code1 + " between " +from.getText() +" and " +to.getText() + " and ";
									     
										}else if(!from.getText().equals("") && to.getText().equals("")) {
											   String code1 ="";
											    
											     
											     for(int k=0;k<tabela.getAttributes().size();k++) {
														if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
														code1=tabela.getAttributes().get(k).getCode();	
														}
													}
											     query+= code1 + " >= " + from.getText() + " and ";
										}else if(from.getText().equals("") && !to.getText().equals("")) {
											   String code1 ="";
											    
											     
											     for(int k=0;k<tabela.getAttributes().size();k++) {
														if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
														code1=tabela.getAttributes().get(k).getCode();	
														}
													}
											     query+= code1 + " <= " + to.getText() + " and ";
										}
									}
								}
							}
							
							int flag=0;
							for(int n=0;n<buttons.size();n++) {
								if(buttons.get(n).isSelected()) {
									flag=1;
									break;
								}
							}
							if(flag==1) {
							if(!buttons.isEmpty()) {
								for(int j=0;j<buttons.size();j++) {
									
									JRadioButton b1 = buttons.get(j);
									JRadioButton b2 = buttons.get(j+1);
									j++;
									
									 String code1 ="";
									    
								     
								     for(int k=0;k<tabela.getAttributes().size();k++) {
											if(b1.getName().equals(tabela.getAttributes().get(k).getName()+"True") || b1.getName().equals(tabela.getAttributes().get(k).getName()+"False")) {
											code1=tabela.getAttributes().get(k).getCode();	
											}
										}
								     if(b1.isSelected())
								    	 query+=code1 +"= 1 and ";
								     else
								    	 query+=code1 +"= 0 and ";
								     	 
									
								}
							}
							}
							int poz=0;
							poz = query.lastIndexOf("and");
							query=query.substring(0,poz-1);
							
							Connection conn2;
							try {
								conn2 = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
							
							
							PreparedStatement pstmt = conn2.prepareStatement(query);
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
						
						int brPar=tt.getColumnCount();
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
						
						 tt = new JTable(data, names);
							scrollPane.setViewportView(tt);
					    	
					    	
					    	panelcic.add(scrollPane);
					    //	panelcic.revalidate();
					    //	panelcic.repaint();
					      //	frame.removeAll();
					    	pap.add(operations);
					    	pap.add(scrollPane);
					    	
					    	//frame.add(pap);
							
						 
						 
							}catch (SQLException es) {
								// TODO: handle exception
								es.printStackTrace();
							}
							
							System.out.println(query);
						}
						
						@Override
						public void changedUpdate(DocumentEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
				 }else if(textF.getToolTipText().equals("number")) {
					 textF.getDocument().addDocumentListener(new DocumentListener() {
						
						@Override
						public void removeUpdate(DocumentEvent arg0) {
							// TODO Auto-generated method stub
							query="select * from "+imeTabele+" where ";
							char c =textF.getName().charAt(textF.getName().length()-1);
							System.out.println(c+"ffff");
							if(textF.getName().endsWith("F")) {
								
								for(int j=0;j<tabela.getAttributes().size();j++) {
									if(textF.getName().equals(tabela.getAttributes().get(j).getName()+"F")) {
										if(!textF.getText().equals(""))
										query+=tabela.getAttributes().get(j).getCode()+ " >= "+textF.getText() +" and ";
										else
											query+=tabela.getAttributes().get(j).getCode()+ " >= "+"0" +" and ";
											
									}
								}
								
								for(int j=0;j<textfieldovi.size();j++) {
									if(!textfieldovi.get(j).getName().equals(textF.getName()+"F")) { 
										if(textfieldovi.get(j).getToolTipText().equals("string")) {
											
											
												if(textfieldovi.get(j).getText().matches((("[12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])")))) {
													for(int k=0;k<tabela.getAttributes().size();k++) {
														if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
															query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
														}
													}
													 
												}
												else {
												for(int k=0;k<tabela.getAttributes().size();k++) {
													if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
														query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
													}
												}
												
												}
											
											
											
										}else if(textfieldovi.get(j).getToolTipText().equals("number")) {
											JTextField from = textfieldovi.get(j);
											JTextField to = textfieldovi.get(j+1);
											j++;
											if(!from.getText().equals("") && !to.getText().equals("")) {
										     String code1 ="";
										    System.out.println(from.getText() + "   "+to.getText());
										     
										     for(int k=0;k<tabela.getAttributes().size();k++) {
													if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
													code1=tabela.getAttributes().get(k).getCode();	
													}
												}
										    
										     query+= code1 + " between " +from.getText() +" and " +to.getText() + " and ";
										     
											}else if(!from.getText().equals("") && to.getText().equals("")) {
												   String code1 ="";
												    
												     
												     for(int k=0;k<tabela.getAttributes().size();k++) {
															if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
															code1=tabela.getAttributes().get(k).getCode();	
															}
														}
												     query+= code1 + " >= " + from.getText() + " and ";
											}else if(from.getText().equals("") && !to.getText().equals("")) {
												   String code1 ="";
												    
												     
												     for(int k=0;k<tabela.getAttributes().size();k++) {
															if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
															code1=tabela.getAttributes().get(k).getCode();	
															}
														}
												     query+= code1 + " <= " + to.getText() + " and ";
											}
										}
									}
								}
								
								int flag=0;
								for(int n=0;n<buttons.size();n++) {
									if(buttons.get(n).isSelected()) {
										flag=1;
										break;
									}
								}
								if(flag==1) {
								if(!buttons.isEmpty()) {
									for(int j=0;j<buttons.size();j++) {
										
										JRadioButton b1 = buttons.get(j);
										JRadioButton b2 = buttons.get(j+1);
										j++;
										
										 String code1 ="";
										    
									     
									     for(int k=0;k<tabela.getAttributes().size();k++) {
												if(b1.getName().equals(tabela.getAttributes().get(k).getName()+"True") || b1.getName().equals(tabela.getAttributes().get(k).getName()+"False")) {
												code1=tabela.getAttributes().get(k).getCode();	
												}
											}
									     if(b1.isSelected())
									    	 query+=code1 +"= 1 and ";
									     else
									    	 query+=code1 +"= 0 and ";
									     	 
										
									}
								}
								}
								int poz=0;
								poz = query.lastIndexOf("and");
								query=query.substring(0,poz-1);
								
								Connection conn2;
								try {
									conn2 = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
								
								
								PreparedStatement pstmt = conn2.prepareStatement(query);
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
							
							int brPar=tt.getColumnCount();
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
							
							 tt = new JTable(data, names);
								scrollPane.setViewportView(tt);
						    	
						    	
						    	panelcic.add(scrollPane);
						    //	panelcic.revalidate();
						    //	panelcic.repaint();
						      //	frame.removeAll();
						    	pap.add(operations);
						    	pap.add(scrollPane);
						    	
						    	//frame.add(pap);
								
							 
							 
								}catch (SQLException es) {
									// TODO: handle exception
									es.printStackTrace();
								}
								
								System.out.println(query);
								
							}else if(textF.getName().endsWith("T")) {
							
								for(int j=0;j<tabela.getAttributes().size();j++) {
									if(textF.getName().equals(tabela.getAttributes().get(j).getName()+"T")) {

									if(!textF.getText().equals(""))
										query+=tabela.getAttributes().get(j).getCode()+ " <= "+textF.getText() +" and ";
										else
											query+=tabela.getAttributes().get(j).getCode()+ " <= "+"9999999999" +" and ";
											
								}
								}
								
								for(int j=0;j<textfieldovi.size();j++) {
									if(!textfieldovi.get(j).getName().equals(textF.getName()+"T")) { 
										if(textfieldovi.get(j).getToolTipText().equals("string")) {
											
										
												if(textfieldovi.get(j).getText().matches((("[12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])")))) {
													for(int k=0;k<tabela.getAttributes().size();k++) {
														if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
															query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
														}
													}
													 
												}
												else {
												for(int k=0;k<tabela.getAttributes().size();k++) {
													if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
														query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
													}
												}
												
												}
											
											
											
										}else if(textfieldovi.get(j).getToolTipText().equals("number")) {
											JTextField from = textfieldovi.get(j);
											JTextField to = textfieldovi.get(j+1);
											j++;
											if(!from.getText().equals("") && !to.getText().equals("")) {
										     String code1 ="";
										    System.out.println(from.getText() + "   "+to.getText());
										     
										     for(int k=0;k<tabela.getAttributes().size();k++) {
													if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
													code1=tabela.getAttributes().get(k).getCode();	
													}
												}
										    
										     query+= code1 + " between " +from.getText() +" and " +to.getText() + " and ";
										     
											}else if(!from.getText().equals("") && to.getText().equals("")) {
												   String code1 ="";
												    
												     
												     for(int k=0;k<tabela.getAttributes().size();k++) {
															if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
															code1=tabela.getAttributes().get(k).getCode();	
															}
														}
												     query+= code1 + " >= " + from.getText() + " and ";
											}else if(from.getText().equals("") && !to.getText().equals("")) {
												   String code1 ="";
												    
												     
												     for(int k=0;k<tabela.getAttributes().size();k++) {
															if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
															code1=tabela.getAttributes().get(k).getCode();	
															}
														}
												     query+= code1 + " <= " + to.getText() + " and ";
											}
										}
									}
								}
								
								int flag=0;
								for(int n=0;n<buttons.size();n++) {
									if(buttons.get(n).isSelected()) {
										flag=1;
										break;
									}
								}
								if(flag==1) {
								if(!buttons.isEmpty()) {
									for(int j=0;j<buttons.size();j++) {
										
										JRadioButton b1 = buttons.get(j);
										JRadioButton b2 = buttons.get(j+1);
										j++;
										
										 String code1 ="";
										    
									     
									     for(int k=0;k<tabela.getAttributes().size();k++) {
												if(b1.getName().equals(tabela.getAttributes().get(k).getName()+"True") || b1.getName().equals(tabela.getAttributes().get(k).getName()+"False")) {
												code1=tabela.getAttributes().get(k).getCode();	
												}
											}
									     if(b1.isSelected())
									    	 query+=code1 +"= 1 and ";
									     else
									    	 query+=code1 +"= 0 and ";
									     	 
										
									}
								}
								}
								int poz=0;
								poz = query.lastIndexOf("and");
								query=query.substring(0,poz-1);
								
								Connection conn2;
								try {
									conn2 = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
								
								
								PreparedStatement pstmt = conn2.prepareStatement(query);
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
							
							int brPar=tt.getColumnCount();
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
							
							 tt = new JTable(data, names);
								scrollPane.setViewportView(tt);
						    	
						    	
						    	panelcic.add(scrollPane);
						    //	panelcic.revalidate();
						    //	panelcic.repaint();
						      //	frame.removeAll();
						    	pap.add(operations);
						    	pap.add(scrollPane);
						    	
						    	//frame.add(pap);
								
							 
							 
								}catch (SQLException es) {
									// TODO: handle exception
									es.printStackTrace();
								}
								
								System.out.println(query);
							}
							
							
						
						
						}
						
						@Override
						public void insertUpdate(DocumentEvent arg0) {
							// TODO Auto-generated method stub
							query="select * from "+imeTabele+" where ";
							char c =textF.getName().charAt(textF.getName().length()-1);
							System.out.println(c);
							if(textF.getName().endsWith("F")) {
								
								for(int j=0;j<tabela.getAttributes().size();j++) {
									if(textF.getName().equals(tabela.getAttributes().get(j).getName()+"F")) {
										query+=tabela.getAttributes().get(j).getCode()+ " >= "+textF.getText() +" and ";
									}
								}
								
								for(int j=0;j<textfieldovi.size();j++) {
									if(!textfieldovi.get(j).getName().equals(textF.getName()+"F")) { 
										if(textfieldovi.get(j).getToolTipText().equals("string")) {
											
												if(textfieldovi.get(j).getText().matches((("[12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])")))) {
													for(int k=0;k<tabela.getAttributes().size();k++) {
														if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
															query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
														}
													}
													 
												}
												else {
												for(int k=0;k<tabela.getAttributes().size();k++) {
													if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
														query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
													}
												}
												
												}
											
											
										}else if(textfieldovi.get(j).getToolTipText().equals("number")) {
											JTextField from = textfieldovi.get(j);
											JTextField to = textfieldovi.get(j+1);
											j++;
											if(!from.getText().equals("") && !to.getText().equals("")) {
										     String code1 ="";
										    System.out.println(from.getText() + "   "+to.getText());
										     
										     for(int k=0;k<tabela.getAttributes().size();k++) {
													if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
													code1=tabela.getAttributes().get(k).getCode();	
													}
												}
										    
										     query+= code1 + " between " +from.getText() +" and " +to.getText() + " and ";
										     
											}else if(!from.getText().equals("") && to.getText().equals("")) {
												   String code1 ="";
												    
												     
												     for(int k=0;k<tabela.getAttributes().size();k++) {
															if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
															code1=tabela.getAttributes().get(k).getCode();	
															}
														}
												     query+= code1 + " >= " + from.getText() + " and ";
											}else if(from.getText().equals("") && !to.getText().equals("")) {
												   String code1 ="";
												    
												     
												     for(int k=0;k<tabela.getAttributes().size();k++) {
															if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
															code1=tabela.getAttributes().get(k).getCode();	
															}
														}
												     query+= code1 + " <= " + to.getText() + " and ";
											}
										}
									}
								}
								int flag=0;
								for(int n=0;n<buttons.size();n++) {
									if(buttons.get(n).isSelected()) {
										flag=1;
										break;
									}
								}
								if(flag==1) {
								if(!buttons.isEmpty()) {
									for(int j=0;j<buttons.size();j++) {
										
										JRadioButton b1 = buttons.get(j);
										JRadioButton b2 = buttons.get(j+1);
										j++;
										
										 String code1 ="";
										    
									     
									     for(int k=0;k<tabela.getAttributes().size();k++) {
												if(b1.getName().equals(tabela.getAttributes().get(k).getName()+"True") || b1.getName().equals(tabela.getAttributes().get(k).getName()+"False")) {
												code1=tabela.getAttributes().get(k).getCode();	
												}
											}
									     if(b1.isSelected())
									    	 query+=code1 +"= 1 and ";
									     else
									    	 query+=code1 +"= 0 and ";
									     	 
										
									}
								}
								}
								int poz=0;
								poz = query.lastIndexOf("and");
								query=query.substring(0,poz-1);
								
								Connection conn2;
								try {
									conn2 = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
								
								
								PreparedStatement pstmt = conn2.prepareStatement(query);
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
							
							int brPar=tt.getColumnCount();
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
							
							 tt = new JTable(data, names);
								scrollPane.setViewportView(tt);
						    	
						    	
						    	panelcic.add(scrollPane);
						    //	panelcic.revalidate();
						    //	panelcic.repaint();
						      //	frame.removeAll();
						    	pap.add(operations);
						    	pap.add(scrollPane);
						    	
						    	//frame.add(pap);
								
							 
							 
								}catch (SQLException es) {
									// TODO: handle exception
									es.printStackTrace();
								}
								
								System.out.println(query);
								
							}else if(textF.getName().endsWith("T")) {
								
								for(int j=0;j<tabela.getAttributes().size();j++) {
									if(textF.getName().equals(tabela.getAttributes().get(j).getName()+"T")) {
										query+=tabela.getAttributes().get(j).getCode()+ " <= "+textF.getText() +" and ";
									}
								}
								
								for(int j=0;j<textfieldovi.size();j++) {
									if(!textfieldovi.get(j).getName().equals(textF.getName()+"T")) { 
										if(textfieldovi.get(j).getToolTipText().equals("string")) {
											
											
												if(textfieldovi.get(j).getText().matches((("[12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])")))) {
													for(int k=0;k<tabela.getAttributes().size();k++) {
														if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
															query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
														}
													}
													 
												}
												else {
												for(int k=0;k<tabela.getAttributes().size();k++) {
													if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
														query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
													}
												}
												
												}
											
											
											
										}else if(textfieldovi.get(j).getToolTipText().equals("number")) {
											JTextField from = textfieldovi.get(j);
											JTextField to = textfieldovi.get(j+1);
											j++;
											if(!from.getText().equals("") && !to.getText().equals("")) {
										     String code1 ="";
										    System.out.println(from.getText() + "   "+to.getText());
										     
										     for(int k=0;k<tabela.getAttributes().size();k++) {
													if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
													code1=tabela.getAttributes().get(k).getCode();	
													}
												}
										    
										     query+= code1 + " between " +from.getText() +" and " +to.getText() + " and ";
										     
											}else if(!from.getText().equals("") && to.getText().equals("")) {
												   String code1 ="";
												    
												     
												     for(int k=0;k<tabela.getAttributes().size();k++) {
															if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
															code1=tabela.getAttributes().get(k).getCode();	
															}
														}
												     query+= code1 + " >= " + from.getText() + " and ";
											}else if(from.getText().equals("") && !to.getText().equals("")) {
												   String code1 ="";
												    
												     
												     for(int k=0;k<tabela.getAttributes().size();k++) {
															if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
															code1=tabela.getAttributes().get(k).getCode();	
															}
														}
												     query+= code1 + " <= " + to.getText() + " and ";
											}
										}
									}
								}
								
								int flag=0;
								for(int n=0;n<buttons.size();n++) {
									if(buttons.get(n).isSelected()) {
										flag=1;
										break;
									}
								}
								if(flag==1) {
								if(!buttons.isEmpty()) {
									for(int j=0;j<buttons.size();j++) {
										
										JRadioButton b1 = buttons.get(j);
										JRadioButton b2 = buttons.get(j+1);
										j++;
										
										 String code1 ="";
										    
									     
									     for(int k=0;k<tabela.getAttributes().size();k++) {
												if(b1.getName().equals(tabela.getAttributes().get(k).getName()+"True") || b1.getName().equals(tabela.getAttributes().get(k).getName()+"False")) {
												code1=tabela.getAttributes().get(k).getCode();	
												}
											}
									     if(b1.isSelected())
									    	 query+=code1 +"= 1 and ";
									     else
									    	 query+=code1 +"= 0 and ";
									     	 
										
									}
								}
								}
								int poz=0;
								poz = query.lastIndexOf("and");
								query=query.substring(0,poz-1);
								
								Connection conn2;
								try {
									conn2 = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
								
								
								PreparedStatement pstmt = conn2.prepareStatement(query);
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
							
							int brPar=tt.getColumnCount();
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
							
							 tt = new JTable(data, names);
								scrollPane.setViewportView(tt);
						    	
						    	
						    	panelcic.add(scrollPane);
						    //	panelcic.revalidate();
						    //	panelcic.repaint();
						      //	frame.removeAll();
						    	pap.add(operations);
						    	pap.add(scrollPane);
						    	
						    	//frame.add(pap);
								
							 
							 
								}catch (SQLException es) {
									// TODO: handle exception
									es.printStackTrace();
								}
								
								System.out.println(query);
							}
							
							
						
						}
						
						@Override
						public void changedUpdate(DocumentEvent arg0) {
							// TODO Auto-generated method stub
							
						}
					});
				 }
			 }else if(elementi.get(i) instanceof JRadioButton) {
				 JRadioButton butt = (JRadioButton) elementi.get(i);
				 butt.addActionListener( new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						query="select * from "+imeTabele+" where ";
						 
								
							 String code11 ="";
							    
							 if(butt.isSelected()) {
						     
						     for(int k=0;k<tabela.getAttributes().size();k++) {
									if(butt.getName().equals(tabela.getAttributes().get(k).getName()+"True")) {
									code11=tabela.getAttributes().get(k).getCode();	
									query+=code11 +"= 1 and ";
									break;
									}
									if(butt.getName().equals(tabela.getAttributes().get(k).getName()+"False")) {
										code11=tabela.getAttributes().get(k).getCode();	
										query+=code11 +"= 0 and ";
										break;
									}
								}
						   
						     	 
							
						}
					 
					 
						for(int j=0;j<textfieldovi.size();j++) {
							
								if(textfieldovi.get(j).getToolTipText().equals("string")) {
									
									
										if(textfieldovi.get(j).getText().matches((("[12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])")))) {
											for(int k=0;k<tabela.getAttributes().size();k++) {
												if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
													query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
												}
											}
											 
										}
										else {
										for(int k=0;k<tabela.getAttributes().size();k++) {
											if(textfieldovi.get(j).getName().equals(tabela.getAttributes().get(k).getName())) {
												query+=tabela.getAttributes().get(k).getCode()+ " like "+ "\'%"+textfieldovi.get(j).getText()+"%\'" +" and ";
											}
										}
										
										}
									
									
									
								}else if(textfieldovi.get(j).getToolTipText().equals("number")) {
									JTextField from = textfieldovi.get(j);
									JTextField to = textfieldovi.get(j+1);
									j++;
									if(!from.getText().equals("") && !to.getText().equals("")) {
								     String code1 ="";
								    System.out.println(from.getText() + "   "+to.getText());
								     
								     for(int k=0;k<tabela.getAttributes().size();k++) {
											if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
											code1=tabela.getAttributes().get(k).getCode();	
											}
										}
								    
								     query+= code1 + " between " +from.getText() +" and " +to.getText() + " and ";
								     
									}else if(!from.getText().equals("") && to.getText().equals("")) {
										   String code1 ="";
										    
										     
										     for(int k=0;k<tabela.getAttributes().size();k++) {
													if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
													code1=tabela.getAttributes().get(k).getCode();	
													}
												}
										     query+= code1 + " >= " + from.getText() + " and ";
									}else if(from.getText().equals("") && !to.getText().equals("")) {
										   String code1 ="";
										    
										     
										     for(int k=0;k<tabela.getAttributes().size();k++) {
													if(from.getName().equals(tabela.getAttributes().get(k).getName()+"F")) {
													code1=tabela.getAttributes().get(k).getCode();	
													}
												}
										     query+= code1 + " <= " + to.getText() + " and ";
										     System.out.println("KVERI "+query);
									}
								}
							
						}
						
						int flag=0;
						for(int n=0;n<buttons.size();n++) {
							if(buttons.get(n).isSelected()) {
								flag=1;
								break;
							}
						}
						if(flag==1) {
						if(!buttons.isEmpty()) {
							for(int j=0;j<buttons.size();j++) {
								
								JRadioButton b1 = buttons.get(j);
								JRadioButton b2 = buttons.get(j+1);
								j++;
								
								 String code1 ="";
								    
							     
							     for(int k=0;k<tabela.getAttributes().size();k++) {
										if(b1.getName().equals(tabela.getAttributes().get(k).getName()+"True") || b1.getName().equals(tabela.getAttributes().get(k).getName()+"False")) {
										code1=tabela.getAttributes().get(k).getCode();	
										}
									}
							     if(b1.isSelected())
							    	 query+=code1 +"= 1 and ";
							     else
							    	 query+=code1 +"= 0 and ";
							     	 
								
							}
						}
						}
						int poz=0;
						poz = query.lastIndexOf("and");
						query=query.substring(0,poz-1);
						
						Connection conn2;
						try {
							conn2 = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
						
						
						PreparedStatement pstmt = conn2.prepareStatement(query);
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
					
					int brPar=tt.getColumnCount();
					Object[][] data2=new Object[brojac][brPar];

					int brojac2=0;
					while(rset.next()) {
						for(int v=0;v<brPar;v++) {
							data2[brojac2][v]=rset.getString(v+1);
						}
						brojac2++;
					}
					
					rset.close();
					pstmt.close();
					
					 tt = new JTable(data2, names);
						scrollPane.setViewportView(tt);
				    	
				    	
				    	panelcic.add(scrollPane);
				    //	panelcic.revalidate();
				    //	panelcic.repaint();
				      //	frame.removeAll();
				    	pap.add(operations);
				    	pap.add(scrollPane);
				    	
				    	//frame.add(pap);
						
					 
					 
						}catch (SQLException es) {
							// TODO: handle exception
							es.printStackTrace();
						}
						
						System.out.println(query);
						
					}
				});
				
				 
				 
			 }
		 }
		 
	
		 
		 
		 JTable kopija = t;
		 kopija.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 ta=t;
		 TableRowSorter<TableModel> rowSorter
         = new TableRowSorter<>(t.getModel());
		 t.setRowSorter(rowSorter);
		 JPanel glavniPanel = new JPanel(new BorderLayout());
		
		 add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JTable table=new JTable(data, names);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				AddView aa = new AddView(tabela,table,dete,roditelj,listaTabela,w,h,true,tabela.getTitle());
				frame.dispose();
			}
		});
		 add.setPreferredSize(new Dimension(65, 30));
		
		 remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
							// TODO Auto-generated method stub
							//ako je selektovanired 0 ne moze obrisati iskace joptionpane
							int selektovaniRed=tt.getSelectedRow();
							if(selektovaniRed==-1) {
							
								JOptionPane.showMessageDialog(null, MyApp.getInstance().getResourceBundle().getString("redSelekt"), MyApp.getInstance().getResourceBundle().getString("error"), 1);
							}else {
							CRUDFactory aa=new RemoveData(ta,tt.getSelectedRow(),tabela,w,h,listaTabela,dete,roditelj,true,false);
							frame.dispose();
							}
					

			}
		});
		 remove.setPreferredSize(new Dimension(65, 30));
		
		 update.setPreferredSize(new Dimension(65, 30));
		 update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selektovaniRed=tt.getSelectedRow();
				if(selektovaniRed==-1) {
				
					JOptionPane.showMessageDialog(null, MyApp.getInstance().getResourceBundle().getString("redSelekt"), MyApp.getInstance().getResourceBundle().getString("error"), 1);
				}else {
					UpdateView uw=new UpdateView(ta, tt.getSelectedRow(), tabela, w, h, listaTabela, dete, roditelj, true);
					frame.dispose();
				}
			}
		});
		
		
			

			panelcic.removeAll();
			panelcic.setPreferredSize(new Dimension(800, 600));
			panelcic.setLayout(new FlowLayout());
		
		    
			
		

		
	    	frame.setPreferredSize(new Dimension(800, 600));
	    	

	    
	    	scrollPane.setViewportView(tt);
	    	scrollPane.revalidate();
	    	scrollPane.repaint();
	    	
	    	panelcic.add(scrollPane);
	  
	    	pap.add(operations);
	    	pap.add(scrollPane);
	    	
	    	frame.add(pap);
			frame.revalidate();
			frame.repaint();

			frame.pack();
			frame.setLocationRelativeTo(null);
		
	    	frame.setVisible(true);

		 
		 
	 }
	public JRadioButton getText() {
		return text;
	}
	public void setText(JRadioButton text) {
		this.text = text;
	}
	public JRadioButton getNum() {
		return num;
	}
	public void setNum(JRadioButton num) {
		this.num = num;
	}
	public JRadioButton getBul() {
		return bul;
	}
	public void setBul(JRadioButton bul) {
		this.bul = bul;
	}
	public JTextArea getUnos() {
		return unos;
	}
	public void setUnos(JTextArea unos) {
		this.unos = unos;
	}
	public JTextArea getUnosFrom() {
		return unosFrom;
	}
	public void setUnosFrom(JTextArea unosFrom) {
		this.unosFrom = unosFrom;
	}
	public JTextArea getUnosTo() {
		return unosTo;
	}
	public void setUnosTo(JTextArea unosTo) {
		this.unosTo = unosTo;
	}

	public JPanel getPa1() {
		return pa1;
	}

	public void setPa1(JPanel pa1) {
		this.pa1 = pa1;
	}

	public JPanel getPa2() {
		return pa2;
	}

	public void setPa2(JPanel pa2) {
		this.pa2 = pa2;
	}





	public JRadioButton getDate() {
		return date;
	}





	public void setDate(JRadioButton date) {
		this.date = date;
	}





	public JRadioButton getNumeric() {
		return numeric;
	}





	public void setNumeric(JRadioButton numeric) {
		this.numeric = numeric;
	}





	public JTextArea getUnesiOdDatumText() {
		return unesiOdDatumText;
	}





	public void setUnesiOdDatumText(JTextArea unesiOdDatumText) {
		this.unesiOdDatumText = unesiOdDatumText;
	}





	public JTextArea getUnesiDoDatumText() {
		return unesiDoDatumText;
	}





	public void setUnesiDoDatumText(JTextArea unesiDoDatumText) {
		this.unesiDoDatumText = unesiDoDatumText;
	}





	public ArrayList<String> getSvikodovi() {
		return svikodovi;
	}





	public void setSvikodovi(ArrayList<String> svikodovi) {
		this.svikodovi = svikodovi;
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





	@Override
	public boolean updateData() {
		// TODO Auto-generated method stub
		return false;
	}





	@Override
	public void searchData() {
		// TODO Auto-generated method stub
		
	}
	
	
}
