package app;

import javax.swing.JToolBar;

import controller.ActionManager;

/**
 * Klasa <code>MyToolBar</code> nasleđuje klasu JToolBar i služi za kreiranje toolbara i dodavanja
 * akcija preko klase ActionManager.
 * 
 * @author Ivan
 * @version %I%, %G%
 */
public class MyToolBar extends JToolBar{
	public MyToolBar() {
		
		super(JToolBar.HORIZONTAL);
		setFloatable(false);
		
		add(ActionManager.getInstance().getShema());
		add(ActionManager.getInstance().getLd());
	    add(ActionManager.getInstance().getV());
	    add(ActionManager.getInstance().getP());
	}
}
