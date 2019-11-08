package MODEL;

public class Proveidors {
	//Atributs
	String cif;
	String nom;
	String cognom;
	String adreca;
	String telefono;
	public Proveidors(String cif,String nom,String adreca,String telefono) {
		this.cif=cif;
		this.cognom=nom;
		this.adreca=adreca;
		this.telefono=telefono;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	void demanarComponent() {
		
	}
}
