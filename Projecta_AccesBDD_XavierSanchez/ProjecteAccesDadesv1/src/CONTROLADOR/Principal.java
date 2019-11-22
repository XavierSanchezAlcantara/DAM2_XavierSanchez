package CONTROLADOR;
import DADES.llegirXML;
import MODEL.Client;

import java.sql.SQLException;

import DADES.SQLClients;

public class Principal {
	static int idComanda;
	
	public static void main(String[] args) throws SQLException {
		llegirXML Lector1=new llegirXML();
		//System.out.println(Comanda1.toString());
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println(Lector1.getComandes());
		SQLClients conector= new SQLClients();
		conector.conectar();
		conector.insertaClients(new Client("2622521472D","sada","Calabró","tierra 32","57584","478569852"));
		conector.consultaClients("Clients");
		conector.editarClient("2622521472D","nom","PENELOPEasd");
		conector.eliminarClient("2622521472D");
		
		//System.out.println("--------------------------------------------------------------------------------------------");
	//	System.out.println(Lector1.comandes.toString()+"\n");
		System.out.println("--------------------------------------------------------------------------------------------");
	//	System.out.println(Comanda.getLineasComanda());
		
	}
}
