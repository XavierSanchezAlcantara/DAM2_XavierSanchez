package Model;

public class Gimnas  {

	String cif;
	String nom;
	String telf;
	String adresa;
	String correu;
	
	public Gimnas (String cif, String nom, String telf, String adresa, String correu) {
		this.cif = cif;
		this.nom = nom;
		this.telf = telf;
		this.adresa = adresa;
		this.correu = correu;
	}
	
	public Gimnas(String cif) {
		this.cif = cif;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTelf() {
		return telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}

	public String getadresa() {
		return adresa;
	}

	public void setadresa(String adresa) {
		this.adresa = adresa;
	}

	public String getCorreu() {
		return correu;
	}

	public void setCorreu(String correu) {
		this.correu = correu;
	}

	@Override
	public String toString() {
		return "\nGimnas [cif=" + cif + ", nom=" + nom + ", telf=" + telf + ", adresa=" + adresa + ", correu=" + correu
				+ "]";
	}
	
}