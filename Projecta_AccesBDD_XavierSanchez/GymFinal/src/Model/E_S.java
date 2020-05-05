package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class E_S {
	int moviment;
	String client;
	String gimnas;
	String data;
	String tipus;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public E_S(int moviment, String client, String gimnas, String data, String tipus) {
		this.moviment = moviment;
		this.client = client;
		this.gimnas = gimnas;
		this.data = data; 
		this.tipus = tipus;
	}
	
	public E_S(String client, String gimnas, String tipus) {
		this.client = client;
		this.gimnas = gimnas;
		this.data = sdf.format(new Date()); 
		this.tipus = tipus;
	}
	
	public E_S(int moviment) {
		this.moviment = moviment;
	}
	
	public E_S(E_S  mov) {
		this.moviment = mov.getMoviment();
		this.client = mov.getClient();
		this.gimnas = mov.getGimnas();
		this.data = mov.getData(); 
		this.tipus = mov.getTipus();
	}
	
	public E_S(String client) {
		this.client = client;
	}
	
	//--------------------GET & SET--------------------

	public int getMoviment() {
		return moviment;
	}

	public void setMoviment(int moviment) {
		this.moviment = moviment;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getGimnas() {
		return gimnas;
	}

	public void setGimnas(String gimnas) {
		this.gimnas = gimnas;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	@Override
	public String toString() {
		return "E_S [moviment=" + moviment + ", client=" + client + ", gimnas=" + gimnas + ", data=" + data + ", tipus="
				+ tipus + "]\n";
	}
	
	
}