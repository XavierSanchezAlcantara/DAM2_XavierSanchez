package Model;

public class Client  {

	String dni;
	String password;
	String rol;
	String nom;
	String cognom;
	String adresa;
	String telf;
	String correu;
	String deutor;	
	
	public Client (String dni, String password, String rol, String nom,	String cognom, String adresa, String telf, String correu, String deutor	) {
		this.dni = dni;
		this.password = password;
		this.rol = rol;
		this.nom = nom;
		this.cognom = cognom;
		this.adresa = adresa;
		this.telf = telf;
		this.correu = correu;
		this.deutor = deutor;
	}
	
	public Client(String dni, String password) {
		this.dni = dni;
		this.password = password;
	}
	
	public Client(String dni) {
		this.dni = dni;
	}

	
	//--------------------GET & SET--------------------
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
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

	public String getDeutor() {
		return deutor;
	}

	public void setDeutor(String deutor) {
		this.deutor = deutor;
	}

	@Override
	public String toString() {
		return "Client [dni=" + dni + ", password=" + password + ", rol=" + rol + ", nom=" + nom + ", cognom=" + cognom
				+ ", adresa=" + adresa + ", telf=" + telf + ", correu=" + correu + ", deutor=" + deutor + "]\n";
	}
	
                                                                             	
}