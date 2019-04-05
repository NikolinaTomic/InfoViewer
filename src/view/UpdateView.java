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
import java.util.LinkedHashMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.MyApp;
import controller.CRUDFactory;
import controller.UpdateData;
import model.Atributi;
import model.Tabela;
import model.TableModel;

public class UpdateView {
	public Tabela t;
	public LinkedHashMap<String, Object> glavno = new LinkedHashMap<>();
	public ArrayList<JTextField> polja = new ArrayList<>();
	public ArrayList<ButtonGroup> dugmad = new ArrayList<>();
	public ArrayList<JRadioButton> but = new ArrayList<>();
	public JRadioButton istina = new JRadioButton(MyApp.getInstance().getResourceBundle().getString("true"));
	public JRadioButton laz = new JRadioButton(MyApp.getInstance().getResourceBundle().getString("false"));
	public ButtonGroup grupa = new ButtonGroup();
	public JTable table;
	public boolean dete;
	public Tabela roditelj;
	public ArrayList<TableModel> listaTabela;
	public int w;
	public boolean search;
	public LinkedHashMap<String,String>unetaPolja = new LinkedHashMap<>();

	public int h;
	JTable tabela;
	
	int red;
	
	public UpdateView(JTable tabela,int red,Tabela t,int w,int h,ArrayList<TableModel> listaTabela,boolean dete,Tabela roditelj,boolean search) {
		this.search=search;
		this.tabela=tabela;
		this.red=red;
		this.t=t;
		this.h=h;
		this.w=w;
		this.listaTabela=listaTabela;
		this.dete=dete;
		this.roditelj=roditelj;
		updateDataPanel();
	}
	
	public void updateDataPanel() {
		// TODO Auto-generated method stub
		for (int i=0;i<tabela.getColumnCount();i++) {
			try {
			unetaPolja.put(tabela.getColumnName(i),tabela.getModel().getValueAt(red, i).toString());
			
			}
			catch(Exception e){
				unetaPolja.put(tabela.getColumnName(i),"");
						
			}
			}
		
		for (int i=0;i<tabela.getColumnCount();i++) {
			String kod="";

			try {
			for(Atributi at:t.getAttributes()) {
				if(at.getName().equals(tabela.getColumnName(i))) {
					kod=at.getCode();
				}
			}
			unetaPolja.put(kod,tabela.getModel().getValueAt(red, i).toString());
			}
			catch(Exception e){
				unetaPolja.put(kod,"");
						
			}
			}
	
		JPanel main = new JPanel();
		main.setSize(800, 600);
		istina.setName("istina");
		laz.setName("laz");
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		JFrame validationFrame=new JFrame(MyApp.getInstance().getResourceBundle().getString("updateD"));
		for(int i=0;i<t.getAttributes().size();i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			panel.setName(t.getAttributes().get(i).getCode());
			JLabel l=new JLabel(t.getAttributes().get(i).getName()+":" +" ");
			String a = t.getAttributes().get(i).getName()+"*  :" +" ";
			String tip = t.getAttributes().get(i).getType();
			String ime = t.getAttributes().get(i).getName() + ":";
			int duzina = t.getAttributes().get(i).getMaxString();
			
			if(t.getAttributes().get(i).isIs_mandatory()) {
				l.setText(a);
			    l.setName(t.getAttributes().get(i).getCode());
			}
			else {
			l.setText(ime);
		    l.setName(t.getAttributes().get(i).getCode());

			}
			JTextField jta = new JTextField();
			jta.setText(unetaPolja.get(i));
			if(t.getAttributes().get(i).isIs_mandatory()) {
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

			jta.setName(t.getAttributes().get(i).getCode());
		
			panel.add(l);
			grupa.clearSelection();
			
	
			
			if(t.getAttributes().get(i).getType().equals("boolean")) {
				
				 ButtonGroup bg = new ButtonGroup();
				 
				 JRadioButton b1 = new JRadioButton(MyApp.getInstance().getResourceBundle().getString("true"));
				 
			     JRadioButton b2 = new JRadioButton(MyApp.getInstance().getResourceBundle().getString("false"));
			     
			     for(String s:unetaPolja.keySet()) {
					 if(s.equals(t.getAttributes().get(i).getCode())) {
						 if(unetaPolja.get(s).equals("1"))
							 b1.setSelected(true);
						 else
							 b2.setSelected(true);
						 break;
					 }
				 }
			     
				if(t.getAttributes().get(i).isIs_mandatory()) {
				 
				 b1.setName(t.getAttributes().get(i).getCode()+"[tm]");
				 b2.setName(t.getAttributes().get(i).getCode()+"[fm]");	 
				}
				else {
					 b1.setName(t.getAttributes().get(i).getCode()+"[tn]");
					 b2.setName(t.getAttributes().get(i).getCode()+"[fn]");	 
						
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
					dugmad.add(bg);
				 glavno.put(t.getAttributes().get(i).getCode(), bg);
			 }
			 else {
				 for(String s:unetaPolja.keySet()) {
					 if(s.equals(jta.getName())) {
						 jta.setText(unetaPolja.get(s));
					 }
				 }
					panel.add(jta);
					 polja.add(jta);
					 glavno.put(t.getAttributes().get(i).getCode(),jta);
			 }
			
			main.add(panel);
			
			
		}
		
      JScrollPane pane = new JScrollPane();
      pane.setViewportView(main);
      
      
  	ArrayList<JTextField> mandatory = new ArrayList<>();
		JButton b=new JButton(MyApp.getInstance().getResourceBundle().getString("update"));
		main.add(b);
		b.addActionListener(new ActionListener() {
			
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
				
				
				if(flag==1)
					JOptionPane.showMessageDialog(null, MyApp.getInstance().getResourceBundle().getString("allMandatory"), MyApp.getInstance().getResourceBundle().getString("error"), 1);
				else {
				
				CRUDFactory ad = new UpdateData(polja,dugmad,but,t,glavno,dete,roditelj,listaTabela,w,h,unetaPolja,red,tabela,search);
				validationFrame.dispose();
				}
			}
		});
		b.setPreferredSize(new Dimension(110,30));
		validationFrame.setSize(900, 400);
	 	validationFrame.add(pane);
	     validationFrame.setLocationRelativeTo(null);
	     validationFrame.setVisible(true);
		
		
	}
	
	
}
