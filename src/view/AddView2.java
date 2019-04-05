package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.MyApp;
import controller.AddData;
import controller.CRUDFactory;
import controller.Row;
import model.Relacija;
import model.Tabela;
import model.TableModel;

public class AddView2 {
	public Tabela t;
	private HashMap<String, String>refbut = new HashMap<>();
	private Tabela zaC = new Tabela();
	public LinkedHashMap<String, Object> glavno = new LinkedHashMap<>();
	public ArrayList<JTextField> polja = new ArrayList<>();
	private ArrayList<Tabela> referencirajuceTabele= new ArrayList<>();
	public ArrayList<ButtonGroup> dugmad = new ArrayList<>();
	public ArrayList<JRadioButton> but = new ArrayList<>();
	public JRadioButton istina = new JRadioButton("True");
	public JRadioButton laz = new JRadioButton("False");
	public ButtonGroup grupa = new ButtonGroup();
	public JTable table;
	public boolean dete;
	private ArrayList<String>sourceNames = new ArrayList<>();
	public Tabela roditelj;
	public ArrayList<TableModel> listaTabela;
	public int w;
	public int h;
	public boolean search;
	private String ime="";
	private String buttonName="";
	public HashMap<String,String>kodvrednost = new HashMap<>();
	private ArrayList<String>codes = new ArrayList<>();
	public AddView2(Tabela t,JTable table,ArrayList<TableModel> tables,HashMap<String,String>kv,ArrayList<String>c,Tabela zaC,String ime,boolean dete,Tabela roditelj,int w,int h,boolean search,String buttonName) {
		this.t=t;
		this.w=w;
		this.h=h;
		this.dete=dete;
		this.buttonName=buttonName;
		this.roditelj=roditelj;
		this.search=search;
		this.zaC=zaC;
		this.table=table;
		listaTabela=tables;
		kodvrednost=kv;
		codes=c;
		this.ime=ime;
		
	}
	
	public void Draw() {
		
		for(TableModel tm:listaTabela) {
			for(Relacija r:tm.getTabela().getRelations()) {
				if(r.getDestinationTable().equals(ime)) {
					referencirajuceTabele.add(tm.getTabela());
					for(String pk: r.getSourceKey()) {
						sourceNames.add(pk);
					}
				}
			}
		}
		
		JPanel main = new JPanel();
		main.setSize(800, 600);
		istina.setName("istina");
		laz.setName("laz");
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		JFrame validationFrame=new JFrame(MyApp.getInstance().getResourceBundle().getString("addData"));
		for(int i=0;i<zaC.getAttributes().size();i++) {
			JLabel lab = new JLabel("");
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			panel.setName(zaC.getAttributes().get(i).getName());
			JLabel l=new JLabel(zaC.getAttributes().get(i).getName()+":" +" ");
			String a = zaC.getAttributes().get(i).getName()+"*  :" +" ";
			String tip = zaC.getAttributes().get(i).getType();
			String ime = zaC.getAttributes().get(i).getName() + ":";
			int duzina = zaC.getAttributes().get(i).getMaxString();
			JLabel strk=new JLabel(MyApp.getInstance().getResourceBundle().getString("foreignK"));
			String sve =strk.getText();
		
			HashMap<String, String> uniqueName=new HashMap<>();
			for(Tabela tab: referencirajuceTabele) {
				for(Relacija rr:tab.getRelations()) {
					if(rr.getDestinationTable().equals(t.getTitle())) {
					for(String ss:rr.getDestinationKey()) {
						if(ss.equals(t.getAttributes().get(i).getCode())){
							uniqueName.put(tab.getTitle(), "");
						}
					}
				}
				}
			}
			for(String ss:uniqueName.keySet()) {
				
				sve+= ss + " , ";
			}

			sve=sve.trim();
			if(sve.endsWith(",")) {
			int poz = sve.lastIndexOf(",");
			String tekst = sve.substring(0,poz-1);
			lab.setText(tekst);
			}
			if(zaC.getAttributes().get(i).isIs_mandatory()) {
				l.setText(a);
			    l.setName(a);
			    l.setToolTipText(zaC.getAttributes().get(i).getCode());
			    
			}
			else {
			l.setText(ime);
			  l.setToolTipText(zaC.getAttributes().get(i).getCode());
			}
			  JTextField jta = new JTextField();
			jta.setName(zaC.getAttributes().get(i).getCode());
			if(zaC.getAttributes().get(i).isIs_mandatory()) {
				jta.setToolTipText("mandatory");
			}
			else
			    jta.setToolTipText("no");
			
			 if(tip.equals("number")) {
				
				
			jta.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if (!((c >= '0') && (c <= '9') ||
			         (c == KeyEvent.VK_BACK_SPACE) ||
			         (c == KeyEvent.VK_DELETE)) || (jta.getText().length()>(duzina-1)))
			                                       
			      {
			       toolkit.beep();
			        e.consume();
			      }
			    }
			  });
			 }
	
				
				
			 if(tip.equals("string")) {
					
				
			jta.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			     if (jta.getText().length()>(duzina-1))		                                       
			      {
			       toolkit.beep();
			        e.consume();
			      }
			    }
			  });
			 }
			 
			  
			jta.setPreferredSize(new Dimension(250, 25));
	
			
			jta.setName(zaC.getAttributes().get(i).getCode());
			int flag=0;
			
			for(Tabela t: referencirajuceTabele) {
				if(t.getTitle().equals(buttonName)) {
				for(Relacija rr:t.getRelations()) {
					for(int k=0;k<rr.getDestinationKey().size();k++) {
						if(l.getToolTipText().equals(rr.getDestinationKey().get(k))) {
							for(String s:kodvrednost.keySet()) {
								for(String sk:rr.getSourceKey()) {
									if(sk.equals(s)) {
										flag=1;
									}
								}							
							}
						}
					}
				}
				}
			}
			
		
			if(flag==1) {
				
					for(Tabela t: referencirajuceTabele) {
						if(t.getTitle().equals(buttonName)){
						for(int d=0;d<t.getRelations().size();d++) {
							for(int k=0;k<t.getRelations().get(d).getDestinationKey().size();k++) {
								if(l.getToolTipText().equals(t.getRelations().get(d).getDestinationKey().get(k))) {
									jta.setText(kodvrednost.get(t.getRelations().get(d).getSourceKey().get(k)));
									System.out.println("SET "+t.getRelations().get(d).getSourceKey().get(k));
									MyApp.getInstance().getZapamcenoAdd().put(jta.getName(), jta.getText());
									l.setToolTipText(".");
									break;
								}
							}
						}
						}
					}
					
		
			}
			else {
				for(String S:MyApp.getInstance().getZapamcenoAdd().keySet()) {
					if(S.equals(jta.getName())) {
						jta.setText(MyApp.getInstance().getZapamcenoAdd().get(S));
					}
				}
			}
		//sourceatribute sa kodvrednost
		
			panel.add(l);
			grupa.clearSelection();
			
	
			
			if(zaC.getAttributes().get(i).getType().equals("boolean")) {
				 ButtonGroup bg = new ButtonGroup();
				 ArrayList<JRadioButton> bg2 = new ArrayList<>();
				 
				 JRadioButton b1 = new JRadioButton("True");
				 
			     JRadioButton b2 = new JRadioButton("False");
				if(zaC.getAttributes().get(i).isIs_mandatory()) {
				 
				 b1.setName(zaC.getAttributes().get(i).getCode()+"[tm]");
				 b1.setToolTipText(zaC.getAttributes().get(i).getCode());
				 b2.setName(zaC.getAttributes().get(i).getCode()+"[fm]");	 
				 b2.setToolTipText(zaC.getAttributes().get(i).getCode());
				}
				else {
					 b1.setName(zaC.getAttributes().get(i).getCode()+"[tn]");
					 b1.setToolTipText(zaC.getAttributes().get(i).getCode());
					 b2.setName(zaC.getAttributes().get(i).getCode()+"[fn]");
					 b2.setToolTipText(zaC.getAttributes().get(i).getCode());
						
				}
				 bg.add(b1);
				 bg.add(b2);
				 but.add(b1);
				 but.add(b2);
				 
				    JPanel istinalaz = new JPanel();
					
					istinalaz.setLayout(new BoxLayout(istinalaz, BoxLayout.X_AXIS));
					istinalaz.add(b1);
					istinalaz.add(b2);
					panel.add(istinalaz);
					
					for(Tabela t: referencirajuceTabele) {
						for(Relacija rr:t.getRelations()) {
							for(String ss:rr.getDestinationKey()) {
								if(b1.getToolTipText().equals(ss)) {
									panel.add(lab);
								}
							}
						}
					}
					
						dugmad.add(bg);
				 glavno.put(zaC.getAttributes().get(i).getCode(), bg);
			 }
			 else {
					panel.add(jta);
					
					for(Tabela t: referencirajuceTabele) {
						for(Relacija rr:t.getRelations()) {
							for(String ss:rr.getDestinationKey()) {
								if(ss.equals(jta.getName())) {
									panel.add(lab);
								}
							}
						}
					}
				
					jta.setSize(5, 5);
					 polja.add(jta);
					 glavno.put(zaC.getAttributes().get(i).getCode(),jta);
			 }
			
			main.add(panel);
			
			
		}
		
      
		JPanel zaDug = new JPanel();
		zaDug.setLayout(new FlowLayout());
		zaDug.add(new JLabel(MyApp.getInstance().getResourceBundle().getString("foreign")));
		JButton b=new JButton(MyApp.getInstance().getResourceBundle().getString("insert"));
		for(int i=0;i<referencirajuceTabele.size();i++) {
			refbut.put(referencirajuceTabele.get(i).getTitle(),"" );
		}
		for(String refbut2: refbut.keySet()) {
			JButton b2 = new JButton(refbut2);
			b2.setName(refbut2);
			b2.addActionListener(new ActionListener() {
				
				@Override
			
				
				
				
				
				
				
				public void actionPerformed(ActionEvent e) {
					ArrayList<String>codes = new ArrayList<>();
					// TODO Auto-generated method stub
				
					Tabela tab = new Tabela();
					for(int y=0;y<listaTabela.size();y++) {
						if(listaTabela.get(y).getTabela().getTitle().equals(b2.getName()))
							tab=listaTabela.get(y).getTabela();
					}
					for(int i=0;i<tab.getAttributes().size();i++) {
						for(int j=0;j<sourceNames.size();j++) {
							if(tab.getAttributes().get(i).getCode().equals(sourceNames.get(j))) {
								codes.add(sourceNames.get(j));
							}
						}
					}

					if(b2.getName().contains(" "))
						b2.getName().replace(" ", "_");
					String query = "select * from " + b2.getName();
					Row row = new Row();
					String[] heders = row.uzmiHeadere(tab);
					Object[][] data = row.konekcijaSaBazom(tab);
					try {
					JTable tabs = new JTable(data, heders);
					
					AddViewReferencingTable adrt = new AddViewReferencingTable(tabs,codes,listaTabela,zaC,tab,ime,dete,roditelj,w,h,search,b2.getText());
					
					adrt.DrawTable();
					validationFrame.dispose();
					}catch (Exception ess) {
						// TODO: handle exception
						ess.printStackTrace();
						System.out.println("ipak ovde");
					}
				}
			});
			zaDug.add(b2);
		}
		main.add(zaDug);
		main.add(b);
		b.addActionListener(new ActionListener() {
			private ArrayList<JTextField> mandatory = new ArrayList<>();
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				
				for(int i=0;i<polja.size();i++) {
					if(polja.get(i).getToolTipText().equals("mandatory") ) {
						mandatory.add(polja.get(i));
					}
				}
				
				int flag=0;
				
				for(int i=0;i<mandatory.size();i++) { 
					if(mandatory.get(i).getText().equals("")) {
						flag=1;
						mandatory.get(i).setBackground(Color.RED);
					}
				}
				
				for(int i=0;i<dugmad.size();i++) {
					if(dugmad.get(i).getSelection()==null) {
						flag=1;
						break;
					}
				}
				if(flag==1) {
					JOptionPane.showMessageDialog(null,MyApp.getInstance().getResourceBundle().getString("allMandatory"), "Error", 1);
				}else {
					System.out.println(zaC.getTitle()+table.getName());

				CRUDFactory ad = new AddData(polja,dugmad,but,t,glavno,dete,roditelj,listaTabela,w,h,search,table,ime);

				validationFrame.dispose();
				} 
			}
		});
		
		b.setPreferredSize(new Dimension(110,30));
		validationFrame.setSize(900, 400);
	 	validationFrame.add(main);
	     validationFrame.setLocationRelativeTo(null);
	     validationFrame.setVisible(true);
		
		
		
	}
	
	
	
	
	
	

}
