package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import app.MyApp;

public class FieldFactory implements FieldTypeFactory {
   
	private String Type;
	private String name;
	private ArrayList<Object> elementi = new ArrayList<>();
	
	public  FieldFactory(String tip,String name) {
		// TODO Auto-generated constructor stub
		Type=tip;
		this.name=name;

	}
	
	@Override
	public JPanel CreateField(String type,String name) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
	    if(type.equals("string")) {
	   // 	System.out.println("string");
		    JTextField tf=new JTextField();
		    tf.setPreferredSize(new Dimension(260, 30));
		    tf.setName(name);
		    tf.setToolTipText("string");
		    panel.add(tf);
		    getElements(tf);
	    	
	    }else if(type.equals("number")) {
	  //  	System.out.println("n");
		    JTextField tf3=new JTextField();
		    tf3.setPreferredSize(new Dimension(260, 30));
		    tf3.setName(name+"T");
		    tf3.setToolTipText("number");
	    	JLabel from=new JLabel(MyApp.getInstance().getResourceBundle().getString("from"));
	    	JLabel to=new JLabel(MyApp.getInstance().getResourceBundle().getString("to"));
	    	JTextField tf2=new JTextField();
	    	tf2.setName(name+"F");
	    	tf2.setToolTipText("number");
	    	   tf2.setPreferredSize(new Dimension(260, 30));
	    	  tf3.addKeyListener(new KeyAdapter() {
				    public void keyTyped(KeyEvent e) {
				      char c = e.getKeyChar();
				      if (!((c >= '0') && (c <= '9') || (c == '-') ||
				         (c == KeyEvent.VK_BACK_SPACE) ||
				         (c == KeyEvent.VK_DELETE))) {
				       toolkit.beep();
				        e.consume();
				      }
				    }
				  });
			
			
			 tf2.addKeyListener(new KeyAdapter() {
				    public void keyTyped(KeyEvent e) {
				      char c = e.getKeyChar();
				      if (!((c >= '0') && (c <= '9') ||
				         (c == KeyEvent.VK_BACK_SPACE) ||
				         (c == KeyEvent.VK_DELETE))) {
				       toolkit.beep();
				        e.consume();
				      }
				    }
				  });
			 getElements(tf2);
			 getElements(tf3);
	    	panel.add(from);
	    	panel.add(tf2);
	    	panel.add(to);
	    	panel.add(tf3);
	    	
	    }else {
	    //	System.out.println("b");
	    	ButtonGroup bg=new ButtonGroup();
	    	
	    	JRadioButton t=new JRadioButton(MyApp.getInstance().getResourceBundle().getString("true"));
	    	t.setName(name+"True");
	    	JRadioButton f=new JRadioButton(MyApp.getInstance().getResourceBundle().getString("false"));
	    	f.setName(name+"False");
	    	bg.add(t);
	    	bg.add(f);
	    	panel.add(t);
	    	panel.add(f);
	    	elementi.add(t);
	    	elementi.add(f);
	    	
	    }
		
		return panel;
		
	}

	public ArrayList<Object> getElements(Object o){
		if(o instanceof JTextField || o instanceof JRadioButton) {
		elementi.add(o);
		return elementi;
		}
		else
			return elementi;
	}


	

}
