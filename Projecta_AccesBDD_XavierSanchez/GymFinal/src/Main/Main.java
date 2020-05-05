package Main;

import Dades.*;
import Model.*;
import Vista.*;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JDialog;


public class Main{
	public static void main(String[] args) throws SQLException {
				
		
		LoginView window = new LoginView();
		window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		window.setVisible(true);
		
	}
}