package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import app.MyApp;

public class Load_Shemu extends AbstractAction{

	  public Load_Shemu() {
		// TODO Auto-generated constructor stub
		  Icon icon = new ImageIcon(new ImageIcon("resources/loadR.jpg").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
			
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
			putValue(MNEMONIC_KEY, KeyEvent.VK_R);
			putValue(SMALL_ICON, icon);
			putValue(NAME, MyApp.getInstance().getResourceBundle().getString("loadShemu"));
			putValue(SHORT_DESCRIPTION, MyApp.getInstance().getResourceBundle().getString("loadShemu"));
		
	  }
	
	
	@Override
	public void actionPerformed(ActionEvent e) throws JSONException,NullPointerException{
		// TODO Auto-generated method stub
		
		JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
        fc.addChoosableFileFilter(filter);
        fc.showOpenDialog(null);
        
        try {
            File f = fc.getSelectedFile();
        	InputStream inputStream = new BufferedInputStream(new FileInputStream(f));
			JSONTokener token = new JSONTokener(inputStream);
			JSONArray json = new JSONArray(token);
		  MyApp.getInstance().getArej().setText(json.toString(2));
		  MyApp.getInstance().getArej().setEditable(true);
	      MyApp.getInstance().addToCentralPanel(new JTextArea());
		  MyApp.getInstance().getSacuvaj().setVisible(true);
		  MyApp.getInstance().getExit().setVisible(true);
		  MyApp.getInstance().getVal().setVisible(true);
		  MyApp.getInstance().getPar().setVisible(true);
        } catch (FileNotFoundException ea) {
			// TODO Auto-generated catch block
			
			ea.printStackTrace();
		}catch(NullPointerException e2) {
			
		}catch(JSONException e3) {
			int a=JOptionPane.showConfirmDialog(null, MyApp.getInstance().getResourceBundle().getString("greskeOpet"), MyApp.getInstance().getResourceBundle().getString("error"), JOptionPane.YES_NO_OPTION);
			
			if(a==JOptionPane.YES_OPTION) {
				InputStream inputStream;
				try {
					inputStream = new BufferedInputStream(new FileInputStream(fc.getSelectedFile()));
			
					
					  BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				        StringBuilder out = new StringBuilder();
				        String line;
				        while ((line = reader.readLine()) != null) {
				            out.append(line);
				        }
				        //Prints the string content read from input stream
				      reader.close();
				        MyApp.getInstance().getArej().setText(out.toString());    
			MyApp.getInstance().getArej().setEditable(true);
			MyApp.getInstance().getSacuvaj().setVisible(true);
			MyApp.getInstance().getVal().setVisible(true);
			MyApp.getInstance().getPar().setVisible(true);
				}
					
					
					
				 catch (FileNotFoundException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				} catch (IOException ee) {
					// TODO Auto-generated catch block
					ee.printStackTrace();
				}
			
			}
			
		} 	   

	}

}