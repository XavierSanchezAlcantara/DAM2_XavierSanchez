package MODEL;

import java.util.ArrayList;
import java.util.Date;

public class Comanda {
	// Atributs
	int idComanda;
	String idClient;
	float preu;
	Date dataObertura;
	Date dataFinalitzacio;
	char status;
	public ArrayList<Comanda> comandes=new ArrayList<Comanda>();
 	public ArrayList<LineaComanda> liniaComanda = new ArrayList<LineaComanda>();

	// Constructor
	public Comanda(int idComanda, String idClient, char status, ArrayList<LineaComanda> liniaComanda) {

		this.idComanda = idComanda;
		this.idClient = idClient;
		this.dataObertura = new Date();
		this.status = status;
		this.liniaComanda = liniaComanda;

	}
	// Constructor
	public Comanda(String idClient, char status, ArrayList<LineaComanda> liniaComanda) {

		this.idComanda = idComanda;
		this.idClient = idClient;
		this.dataObertura = new Date();
		this.status = status;
		this.liniaComanda = liniaComanda;

	}

	// Getter i Setters
	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}

	public String getIdClient() {
		return idClient;
	}

	public void setPreu(float preu) {
		this.preu = preu;
	}

	public float getPreu() {
		for (LineaComanda liniaComanda : liniaComanda) {
			preu += liniaComanda.preuLinia;
		}
		return preu;
	}

	public void setDataObertura(Date dataObertura) {
		this.dataObertura = dataObertura;
	}

	public Date getDataObertura() {
		return dataObertura;
	}

	public void setDataFinalitzacio(Date dataFinalitzacio) {
		this.dataFinalitzacio = dataFinalitzacio;
	}

	public Date getDataFinalitzacio() {
		return dataFinalitzacio;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getStatus() {
		return status;
	}

	public void setLineasComanda(ArrayList<LineaComanda> lineasComanda) {
		this.liniaComanda = lineasComanda;
	}
	
	public void setComanda(ArrayList<Comanda> comanda) {
		this.comandes = comandes;
	}
	public ArrayList<Comanda> getComanda() {
		return comandes;
	}
	
	// Linies Comanda
	public ArrayList<LineaComanda> getLineasComanda() {
		return liniaComanda;
	}

	@Override
	public String toString() {
		return "\nComanda [idComanda=" + idComanda + ", idClient=" + idClient + ", preu=" + getPreu() + ", dataObertura="
				+ dataObertura + ", dataFinalitzacio=" + dataFinalitzacio + ", status=" + status  + ", liniaComanda=" + liniaComanda + "]\n";
	}

}
