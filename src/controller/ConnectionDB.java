package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ConnectionDB implements DatabaseConnection{

	@Override
	public boolean connect(String ip,String name,String pass) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip+"/psw-2018-tim2-4",name,pass);
			ArrayList<String> podaci = new ArrayList();
			podaci.add(ip);
			podaci.add(name);
			podaci.add(pass);
            SQLData sql = new SQLData();
            sql.parse(podaci);
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
		
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", 0);
			return false;
		} catch (Exception ee) {
			// TODO: handle exception
	ee.printStackTrace();
	return false;
		}
		
		
		
	}

}
