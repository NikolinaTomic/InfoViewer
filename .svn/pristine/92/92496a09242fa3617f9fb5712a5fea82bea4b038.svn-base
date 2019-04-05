package tree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import app.MyApp;
import controller.Row;
import model.Tabela;
import model.TableModel;
import view.TableView;
/**
 * Klasa koja se poziva dvoklikom na stablo. Iscrtavanje izgleda dvokliknute tabele iz stabla
 * @author Nikolina
 * @version %I%, %G%
 * @see ResourceBundle
 * 
 * 
 * */
public class TreeSelectionListener extends MouseAdapter{

	private int wi;
	private int h;
	public TreeSelectionListener(int wi,int h) {

		this.wi=wi;
		this.h=h;
		  
	}
	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getClickCount() == 2 && !e.isConsumed()) { //ovde vec ima dete
			JTree tree=(JTree) e.getSource();
			DefaultMutableTreeNode node=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if(node!=null && node.getUserObject() instanceof TableModel) {
				TableModel selectedNode=(TableModel) node.getUserObject();
				ArrayList<Tabela> deca=new ArrayList<>();
				for(TableModel tm:selectedNode.getDeca()) {
					System.out.println(tm.getTabela().getTitle()+" dete");
					deca.add(tm.getTabela());
				}

					JPanel pa=new JPanel();
					Row w=new Row(); 
					Object[][] data=w.konekcijaSaBazom(selectedNode.getTabela());
					HashMap<Tabela, Object[][]> data2=w.konekcijaSaBazomDeca(deca);
					TableView is=new TableView(MyApp.getInstance().getTabelaModeli(),selectedNode.getTabela().getTitle());
					System.out.println("TAB "+selectedNode.getTabela().getTitle()+" DECE "+data2.size());
					pa=is.iscrtavanje(wi,h,data,data2);
					MyApp.getInstance().addToCentralPanel2(pa);

				 MyApp.getInstance().getSacuvaj().setVisible(false);
				 MyApp.getInstance().getExit().setVisible(false);
				 MyApp.getInstance().getVal().setVisible(false);
				 MyApp.getInstance().getPar().setVisible(false);
				
			}
		}
	}

	
}
