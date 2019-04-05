package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

public class ChangeLanguage extends JFrame{
	
	public ChangeLanguage(){
		ResourceBundle resourceBundle;	
		resourceBundle =
	            ResourceBundle.getBundle( "Languages.MessageResources", Locale.getDefault() );
	
		
		UIManager.put("OptionPane.yesButtonText", resourceBundle.getObject("yesOption"));
		UIManager.put("OptionPane.noButtonText", resourceBundle.getObject("noOption"));
		UIManager.put("OptionPane.okButtonText", resourceBundle.getObject("okOption"));
		UIManager.put("OptionPane.cancelButtonText", resourceBundle.getObject("cancelOption"));
		UIManager.put("OptionPane.openButtonText", resourceBundle.getObject("openOption"));

		JRadioButton srpski=new JRadioButton("Srpski");
		JRadioButton engleski=new JRadioButton("English");
		ButtonGroup b1 =new ButtonGroup();
		b1.add(engleski);
		b1.add(srpski);
		JFrame jez= new JFrame();
		JPanel izbJez= new JPanel();
		jez.setTitle("Izaberi jezik/Choose language");
		izbJez.setName("Izaberi jezik/Choose language");
		izbJez.setVisible(true);
		izbJez.add(srpski);
		izbJez.add(engleski);
		jez.add(izbJez);
		jez.setVisible(true);
		jez.setSize(300, 80);
	    jez.setLocationRelativeTo(null);
	    
	    srpski.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("sr", "RS"));
				ChooseSource cs = new ChooseSource();
				jez.dispose();	
				
			}
		});
		
		
		engleski.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en", "US"));
				ChooseSource cs = new ChooseSource();
				jez.dispose();	
		    }
		});

	}

}