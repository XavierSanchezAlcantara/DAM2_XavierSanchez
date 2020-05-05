package Main;

import Dades.*;
import Model.*;
import Vista.*;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JDialog;

public class SQLtester{
	
	public static void main(String[] args) {
		
		
		
		ClientsSQL conector1 = new ClientsSQL();
		ClientsSQL conector2 = new ClientsSQL();
		ClientsSQL conector3 = new ClientsSQL();
	
		
		
		E_SSQL con1 = new E_SSQL();
		E_SSQL con2 = new E_SSQL();
		E_SSQL con3 = new E_SSQL();
		


		GimnasSQL conxio1 = new GimnasSQL();
		GimnasSQL conxio2 = new GimnasSQL();
		GimnasSQL conxio3 = new GimnasSQL();
		
		con1.conectar();
		Gimnas gim = new Gimnas("wwww");
		
		try {
			System.out.println();
			System.out.println("####################### 1 ####################");
			conxio1.insertaGimnas(gim);
			System.out.println(conxio1.consultaGimnas());

			System.out.println();
			System.out.println("####################### 2 ####################");
			gim = new Gimnas("wwww","gim","937785112","sdnfosa","sdkfbakud");
			conxio2.modificaGimnas(gim);
			System.out.println(conxio2.consultaGimnas());
			
			System.out.println();
			System.out.println("####################### 3 ####################");
			conxio3.deleteGimnas(gim);
			System.out.println(conxio3.consultaGimnas());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
			 
	
	
}