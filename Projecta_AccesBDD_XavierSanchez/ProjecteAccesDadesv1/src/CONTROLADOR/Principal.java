package CONTROLADOR;
import DADES.llegirXML;
import MODEL.Comanda;
import MODEL.LineaComanda;
public class Principal {
	static int idComanda;
	
	public static void main(String[] args) {
		llegirXML Lector1=new llegirXML();
		//System.out.println(Comanda1.toString());
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println(Lector1.getComandes());
		
		//System.out.println("Preu: "+Lector1. "€");
	//	System.out.println("--------------------------------------------------------------------------------------------");
		
		//System.out.println("Nombre de Linies de Comanda:" +Comanda.liniaComanda.size() );
		
		System.out.println("--------------------------------------------------------------------------------------------");
	//	System.out.println(Lector1.comandes.toString()+"\n");
		System.out.println("--------------------------------------------------------------------------------------------");
	//	System.out.println(Comanda.getLineasComanda());
		
	}
}
