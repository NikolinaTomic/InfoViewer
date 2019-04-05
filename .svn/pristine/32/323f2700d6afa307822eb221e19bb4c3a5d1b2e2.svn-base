package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import app.MyApp;
import controller.Row;
import model.Atributi;
import model.Tabela;
import model.TableModel;

public class AddViewReferencingTable extends JFrame{
	private Tabela zaCrtanje = new Tabela();
	private JTable table = new JTable();
	private Tabela t= new Tabela();
	private Tabela tab = new Tabela();
	private String ime;
	ArrayList<TableModel>tables = new ArrayList<>();
	private ArrayList<String> codes = new ArrayList<>();
	private boolean dete;
	private boolean search;
	private int h,w;
	private Tabela roditelj;
	private ArrayList<String> codovi = new ArrayList<>();
	private String buttonName;
	
	public AddViewReferencingTable(JTable table,ArrayList<String>codes,ArrayList<TableModel>tm,Tabela t,Tabela tab,String ime,boolean dete,Tabela roditelj,int w,int h,boolean search,String buttonName) {
		this.table=table;
		this.dete=dete;
		this.roditelj=roditelj;
		this.w=w;
		this.h=h;
		this.search=search;
		this.codes=codes;
		this.ime=ime;
        this.tab = tab;
		this.t=t;
		this.buttonName=buttonName;
		tables=tm;
		
	}
	
	public void DrawTable() {
		
		JButton done = new JButton(MyApp.getInstance().getResourceBundle().getString("done"));
		
		JFrame frame = new JFrame(MyApp.getInstance().getResourceBundle().getString("tuple"));
		JPanel p = new JPanel();
		done.setPreferredSize(new Dimension(40, 20));
		
		p.setPreferredSize(new Dimension(800, 600));
		p.setLayout(new BorderLayout());
		JScrollPane pane = new JScrollPane();
		pane.setViewportView(table);
		p.add(pane,BorderLayout.NORTH);
		p.add(done,BorderLayout.SOUTH);
		frame.setPreferredSize(new Dimension(800,600));
		frame.add(p);
		frame.revalidate();
		frame.repaint();

		frame.pack();
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selektovaniRed=table.getSelectedRow();
				if(selektovaniRed==-1) {
				
					JOptionPane.showMessageDialog(null, MyApp.getInstance().getResourceBundle().getString("redSelekt"), "Error", 1);
				}else {
					zaCrtanje=t;

					for(int i=0;i<tab.getAttributes().size();i++) {
						for(int j=0;j<codes.size();j++) {
							if(tab.getAttributes().get(i).getCode().equals(codes.get(j))) {
								codovi.add(tab.getAttributes().get(i).getCode());
							}
						}
					}
					
					
					HashMap<String, String> kodVrednost=new HashMap<>();
					
					Connection conn;
					try {
						conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/psw-2018-tim2-4","psw-2018-tim2-4","tim2-413090834");
					

					String izmena=tab.getTitle();
					if(tab.getTitle().contains(" ")) {
						String str = " ";
						izmena=izmena.replace(str, "_");
					}
					
					Row row = new Row();
					String[] heders = row.uzmiHeadere(tab);
					Object[][] data = row.konekcijaSaBazom(tab);
				    JTable tabelaa = new JTable(data, heders);
				    String nazivKolone="";
				    for(int i=0;i<codes.size();i++) {
				    	for(Atributi at: tab.getAttributes()) {
				    		if(codes.get(i).equals(at.getCode())) {
				    			nazivKolone=at.getName();
				    		}
				    	}
				    String value = (String)tabelaa.getModel().getValueAt(selektovaniRed, getColumnByName(tabelaa, nazivKolone));
				    	kodVrednost.put(codes.get(i), value);
				    	}
 
				AddView2 ad2 = new AddView2(t,table,tables,kodVrednost,codes,zaCrtanje,ime,dete,roditelj,w,h,search,buttonName);
				frame.dispose();
				ad2.Draw();
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						System.out.println("ovde pukao");
					}
					
					
				}
			}	
				
			
		});
		
	}
	private int getColumnByName(JTable table, String name) {
	    for (int i = 0; i < table.getColumnCount(); ++i)
	        if (table.getColumnName(i).equals(name)) {
	            return i;
	        }
	    return 1 ;
	     
	    
	    
	    
	    
	}

}
