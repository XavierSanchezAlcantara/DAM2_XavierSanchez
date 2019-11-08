package CONTROLADOR;
import DADES.llegirXML;
import MODEL.Comanda;
public class Principal {
	static int idComanda;
	
	public static void main(String[] args) {
		idComanda=1;
		
		llegirXML Lector1=new llegirXML();
		//Comanda Comanda1= new Comanda(idComanda,Lector1.getIdClient(),Lector1.getStatusc(), Lector1.getLinies());
		System.out.println(Comanda1.toString());
		
		System.out.println("Preu: "+Comanda1.getPreu()+" €");
		System.out.println("--------------------------------------------------------------------------------------------");
		
		System.out.println("Nombre de Linies de Comanda:" +Comanda1.liniaComanda.size() );
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println(Comanda1.getLineasComanda());
	}
}
